package tech.tresearchgroup.babygalago.view.components.forms.subTypes;

import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import tech.tresearchgroup.babygalago.controller.EnumController;
import tech.tresearchgroup.babygalago.controller.SettingsController;
import tech.tresearchgroup.babygalago.controller.controllers.NotificationController;
import tech.tresearchgroup.babygalago.controller.controllers.QueueController;
import tech.tresearchgroup.babygalago.model.ExtendedUserEntity;
import tech.tresearchgroup.babygalago.view.components.HeadComponent;
import tech.tresearchgroup.babygalago.view.components.SideBarComponent;
import tech.tresearchgroup.babygalago.view.components.TopBarComponent;
import tech.tresearchgroup.palila.controller.components.AutoCompleteDropDownBoxComponent;
import tech.tresearchgroup.palila.controller.components.EditableFieldComponent;
import tech.tresearchgroup.schemas.galago.entities.GamePlatformReleaseEntity;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

import static j2html.TagCreator.*;

@AllArgsConstructor
public class GamePlatformReleaseForm {
    private final SettingsController settingsController;
    private final NotificationController notificationController;

    public byte @NotNull [] render(boolean editable,
                                   boolean loggedIn,
                                   String saveUrl,
                                   ExtendedUserEntity userEntity) throws SQLException {
        return render(editable, loggedIn, saveUrl, null, userEntity);
    }

    public byte @NotNull [] render(boolean editable,
                                   boolean loggedIn,
                                   String saveUrl,
                                   GamePlatformReleaseEntity gamePlatformReleaseEntity,
                                   ExtendedUserEntity userEntity) throws SQLException {
        long id = 0;
        String platform = null;
        String releaseDate = null;
        Long views = null;
        if (gamePlatformReleaseEntity != null) {
            id = gamePlatformReleaseEntity.getId();
            if (gamePlatformReleaseEntity.getPlatform() != null) {
                platform = gamePlatformReleaseEntity.getPlatform().toString();
            }
            if (gamePlatformReleaseEntity.getReleaseDate() != null) {
                releaseDate = gamePlatformReleaseEntity.getReleaseDate();
            }
            if (gamePlatformReleaseEntity.getViews() != null) {
                views = gamePlatformReleaseEntity.getViews();
            }
        }
        return document(
            html(
                HeadComponent.render(settingsController.getServerName()),
                TopBarComponent.render(notificationController.getNumberOfUnread(userEntity), QueueController.getQueueSize(), loggedIn, userEntity.getPermissionGroup()),
                SideBarComponent.render(loggedIn,
                    settingsController.isMovieLibraryEnable(),
                    settingsController.isTvShowLibraryEnable(),
                    settingsController.isGameLibraryEnable(),
                    settingsController.isMusicLibraryEnable(),
                    settingsController.isBookLibraryEnable()),
                body(
                    div(
                        form(
                            input().withType("text").withValue(String.valueOf(id)).withName("id").isHidden(),
                            br(),
                            iff(!editable,
                                a(" Edit").withClass("floatRight btn fas fa-edit").withHref("/edit/gameplatformrelease/" + id)
                            ),
                            br(),
                            br(),
                            AutoCompleteDropDownBoxComponent.render(editable, "Platform:", "platform", platform, EnumController.getGamePlatforms()),
                            br(),
                            EditableFieldComponent.render(editable, "Release date:", releaseDate, "releaseDate"),
                            br(),
                            EditableFieldComponent.render(editable, "Views:", String.valueOf(views), "views"),
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
