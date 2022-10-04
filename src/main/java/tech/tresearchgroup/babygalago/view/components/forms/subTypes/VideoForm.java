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
import tech.tresearchgroup.schemas.galago.entities.FileEntity;
import tech.tresearchgroup.schemas.galago.entities.VideoEntity;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

import static j2html.TagCreator.*;

@AllArgsConstructor
public class VideoForm {
    private final SettingsController settingsController;
    private final NotificationController notificationController;

    public byte @NotNull [] render(boolean editable,
                                   boolean loggedIn,
                                   String saveUrl,
                                   ExtendedUserEntity userEntity) throws SQLException {
        return render(editable, loggedIn, saveUrl, null, userEntity, null);
    }

    public byte @NotNull [] render(boolean editable,
                                   boolean loggedIn,
                                   String saveUrl,
                                   VideoEntity videoEntity,
                                   ExtendedUserEntity userEntity,
                                   FileEntity primaryImageFile) throws SQLException {
        long id = 0;
        String primaryImage = null;
        String filePath = null;
        String playbackQualityEnum = null;
        Long views = null;
        if (videoEntity != null) {
            id = videoEntity.getId();
            if (primaryImageFile != null) {
                primaryImage = primaryImageFile.getPath();
            }
            if (videoEntity.getFilePath() != null) {
                filePath = videoEntity.getFilePath();
            }
            if (videoEntity.getViews() != null) {
                views = videoEntity.getViews();
            }
            if (videoEntity.getPlaybackQualityEnum() != null) {
                playbackQualityEnum = videoEntity.getPlaybackQualityEnum().toString();
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
                            br(),
                            iff(!editable,
                                a(" Edit").withClass("floatRight btn fas fa-edit").withHref("/edit/news/" + id)
                            ),
                            br(),
                            br(),
                            EditableFieldComponent.render(editable, "Primary image:", primaryImage, "primaryImage"),
                            br(),
                            EditableFieldComponent.render(editable, "File path:", filePath, "filePath"),
                            br(),
                            AutoCompleteDropDownBoxComponent.render(editable, "Quality:", "playbackQuality", playbackQualityEnum, EnumController.getPlaybackQuality()),
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
