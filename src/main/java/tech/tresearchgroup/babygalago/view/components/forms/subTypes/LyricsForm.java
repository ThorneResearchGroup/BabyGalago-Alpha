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
import tech.tresearchgroup.palila.controller.components.EditableTitleComponent;
import tech.tresearchgroup.palila.controller.components.TextAreaComponent;
import tech.tresearchgroup.schemas.galago.entities.LyricsEntity;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

import static j2html.TagCreator.*;

@AllArgsConstructor
public class LyricsForm {
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
                                   LyricsEntity lyricsEntity,
                                   ExtendedUserEntity userEntity) throws SQLException {
        long id = 0;
        String data = null;
        Long views = null;
        String language = null;
        if (lyricsEntity != null) {
            id = lyricsEntity.getId();
            if (lyricsEntity.getData() != null) {
                data = lyricsEntity.getData();
            }
            if (lyricsEntity.getViews() != null) {
                views = lyricsEntity.getViews();
            }
            if (lyricsEntity.getLanguage() != null) {
                language = lyricsEntity.getLanguage().name();
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
                            EditableTitleComponent.render(editable, "Lyrics:", "title"),
                            br(),
                            br(),
                            iff(!editable,
                                a(" Edit").withClass("floatRight btn fas fa-edit").withHref("/edit/lyrics/" + id)
                            ),
                            br(),
                            br(),
                            TextAreaComponent.render(editable, "Data:", data, "data", "4", "20"),
                            br(),
                            EditableFieldComponent.render(editable, "Views:", String.valueOf(views), "views"),
                            br(),
                            AutoCompleteDropDownBoxComponent.render(editable, "Language:", "language", language, EnumController.getLanguagesValues()),
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
