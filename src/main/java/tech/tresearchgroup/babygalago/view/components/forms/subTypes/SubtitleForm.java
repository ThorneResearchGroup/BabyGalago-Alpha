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
import tech.tresearchgroup.palila.controller.components.TextAreaComponent;
import tech.tresearchgroup.schemas.galago.entities.SubtitleEntity;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

import static j2html.TagCreator.*;

@AllArgsConstructor
public class SubtitleForm {
    private final SettingsController settingsController;
    private final NotificationController notificationController;

    public byte @NotNull [] render(boolean editable,
                                   String saveUrl,
                                   ExtendedUserEntity extendedUserEntity) throws SQLException {
        return render(editable, saveUrl, null, extendedUserEntity);
    }

    public byte @NotNull [] render(boolean editable,
                                   String saveUrl,
                                   SubtitleEntity subtitleEntity,
                                   ExtendedUserEntity extendedUserEntity) throws SQLException {
        long id = 0;
        String language = null;
        String data = null;
        Long views = null;
        if (subtitleEntity != null) {
            id = subtitleEntity.getId();
            if (subtitleEntity.getLanguage() != null) {
                language = subtitleEntity.getLanguage();
            }
            if (subtitleEntity.getData() != null) {
                data = subtitleEntity.getData();
            }
            if (subtitleEntity.getViews() != null) {
                views = subtitleEntity.getViews();
            }
        }
        return document(
            html(
                HeadComponent.render(settingsController.getServerName()),
                TopBarComponent.render(notificationController.getNumberOfUnread(extendedUserEntity), QueueController.getQueueSize(), true, extendedUserEntity.getPermissionGroup()),
                SideBarComponent.render(true,
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
                            br(),
                            iff(!editable,
                                a(" Edit").withClass("floatRight btn fas fa-edit").withHref("/edit/news/" + id)
                            ),
                            br(),
                            br(),
                            AutoCompleteDropDownBoxComponent.render(editable, "Language:", "language", language, EnumController.getLanguagesValues()),
                            br(),
                            TextAreaComponent.render(editable, "Data:", data, "data", "4", "20"),
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
