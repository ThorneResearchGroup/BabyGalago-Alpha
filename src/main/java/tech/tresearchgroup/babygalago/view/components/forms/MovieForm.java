package tech.tresearchgroup.babygalago.view.components.forms;

import j2html.tags.DomContent;
import jdk.jshell.spi.ExecutionControl;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import tech.tresearchgroup.babygalago.controller.SettingsController;
import tech.tresearchgroup.babygalago.controller.controllers.MovieController;
import tech.tresearchgroup.babygalago.controller.controllers.NotificationController;
import tech.tresearchgroup.babygalago.controller.controllers.QueueController;
import tech.tresearchgroup.babygalago.model.ExtendedUserEntity;
import tech.tresearchgroup.babygalago.view.components.HeadComponent;
import tech.tresearchgroup.babygalago.view.components.SideBarComponent;
import tech.tresearchgroup.babygalago.view.components.TopBarComponent;
import tech.tresearchgroup.palila.model.enums.PermissionGroupEnum;
import tech.tresearchgroup.schemas.galago.entities.*;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

import static j2html.TagCreator.*;

@AllArgsConstructor
public class MovieForm {
    private final SettingsController settingsController;
    private final NotificationController notificationsController;

    private final MovieController movieController;

    public byte @NotNull [] render(boolean editable,
                                   boolean loggedIn,
                                   String saveUrl,
                                   ExtendedUserEntity userEntity) throws SQLException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, ExecutionControl.NotImplementedException, InstantiationException {
        return render(editable, loggedIn, saveUrl, null, userEntity);
    }

    public byte @NotNull [] render(boolean editable,
                                   boolean loggedIn,
                                   String saveUrl,
                                   MovieEntity movieEntity,
                                   ExtendedUserEntity userEntity) throws SQLException, ExecutionControl.NotImplementedException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException {
        Long id = null;

        PermissionGroupEnum permissionGroupEnum = PermissionGroupEnum.ALL;
        if (userEntity != null) {
            permissionGroupEnum = userEntity.getPermissionGroup();
        }

        if (movieEntity != null) {
            id = movieEntity.getId();
        }

        List<DomContent> contentList = movieController.toForm(editable, movieEntity);
        return document(
            html(
                HeadComponent.render(settingsController.getServerName()),
                TopBarComponent.render(notificationsController.getNumberOfUnread(userEntity), QueueController.getQueueSize(), loggedIn, permissionGroupEnum),
                SideBarComponent.render(loggedIn,
                    settingsController.isMovieLibraryEnable(),
                    settingsController.isTvShowLibraryEnable(),
                    settingsController.isGameLibraryEnable(),
                    settingsController.isMusicLibraryEnable(),
                    settingsController.isBookLibraryEnable()),
                script().withSrc("/assets/autocompletetextbox.js"),
                body(
                    div(
                        form(
                            iff(!editable,
                                a(" Edit").withClass("floatRight btn fas fa-edit").withHref("/edit/movie/" + id)
                            ),
                            br(),
                            each(contentList, content -> content),
                            br(),
                            br(),
                            iff(editable,
                                button("Save").withClass("floatRight")
                            )
                        )
                            .withAction(saveUrl)
                            .withMethod("POST")
                    ).withClass("body")
                )
            )
        ).getBytes();
    }
}
