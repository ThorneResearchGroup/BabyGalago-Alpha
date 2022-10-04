package tech.tresearchgroup.babygalago.view.pages;

import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import tech.tresearchgroup.babygalago.controller.SettingsController;
import tech.tresearchgroup.babygalago.controller.controllers.NotificationController;
import tech.tresearchgroup.babygalago.controller.controllers.QueueController;
import tech.tresearchgroup.babygalago.model.ExtendedUserEntity;
import tech.tresearchgroup.babygalago.view.components.HeadComponent;
import tech.tresearchgroup.babygalago.view.components.SideBarComponent;
import tech.tresearchgroup.babygalago.view.components.TopBarComponent;
import tech.tresearchgroup.palila.controller.components.UploadComponent;
import tech.tresearchgroup.palila.model.enums.PermissionGroupEnum;

import java.sql.SQLException;

import static j2html.TagCreator.*;

@AllArgsConstructor
public class UploadPage {
    private final SettingsController settingsController;
    private final NotificationController notificationController;

    public byte @NotNull [] render(boolean editable, boolean loggedIn, ExtendedUserEntity userEntity) throws SQLException {
        PermissionGroupEnum permissionGroupEnum = PermissionGroupEnum.ALL;
        if (userEntity != null) {
            permissionGroupEnum = userEntity.getPermissionGroup();
        }
        return document(
            html(
                HeadComponent.render(settingsController.getServerName()),
                script().withSrc("/assets/upload.js"),
                TopBarComponent.render(notificationController.getNumberOfUnread(userEntity), QueueController.getQueueSize(), loggedIn, permissionGroupEnum),
                SideBarComponent.render(loggedIn,
                    settingsController.isMovieLibraryEnable(),
                    settingsController.isTvShowLibraryEnable(),
                    settingsController.isGameLibraryEnable(),
                    settingsController.isMusicLibraryEnable(),
                    settingsController.isBookLibraryEnable()),
                body(
                    div(
                        label("Upload file to server").withClass("overviewLabel"),
                        br(),
                        br(),
                        br(),
                        form(
                            label("Upload to:"),
                            select(
                                option("Books").withValue("book"),
                                option("Games").withValue("game"),
                                option("Movies").withValue("movie"),
                                option("Music").withValue("music"),
                                option("TV Shows").withValue("tvshow")
                            ).withName("mediaType").withId("mediaType"),
                            br(),
                            UploadComponent.render(editable, null, "file1")
                        ).withId("upload_form").withEnctype("multipart/form-data").withMethod("POST")
                    ).withClass("body")
                )
            )
        ).getBytes();
    }
}
