package tech.tresearchgroup.babygalago.view.pages.play;

import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import tech.tresearchgroup.babygalago.controller.SettingsController;
import tech.tresearchgroup.babygalago.controller.controllers.NotificationEntityController;
import tech.tresearchgroup.babygalago.controller.controllers.QueueEntityController;
import tech.tresearchgroup.babygalago.controller.controllers.VideoEntityController;
import tech.tresearchgroup.babygalago.view.components.HeadComponent;
import tech.tresearchgroup.babygalago.view.components.SideBarComponent;
import tech.tresearchgroup.babygalago.view.components.TopBarComponent;
import tech.tresearchgroup.palila.model.enums.PermissionGroupEnum;
import tech.tresearchgroup.schemas.galago.entities.ExtendedUserEntity;
import tech.tresearchgroup.schemas.galago.entities.MovieEntity;
import tech.tresearchgroup.schemas.galago.entities.UserSettingsEntity;
import tech.tresearchgroup.schemas.galago.entities.VideoEntity;

import java.sql.SQLException;
import java.util.List;

import static j2html.TagCreator.*;

@AllArgsConstructor
public class PlayMoviePage {
    private final SettingsController settingsController;
    private final NotificationEntityController notificationsController;
    private final VideoEntityController videoEntityController;

    public byte @NotNull [] render(boolean loggedIn,
                                   MovieEntity movieEntity,
                                   UserSettingsEntity userSettingsEntity,
                                   ExtendedUserEntity userEntity,
                                   List<VideoEntity> videoFiles) throws SQLException {
        PermissionGroupEnum permissionGroupEnum = PermissionGroupEnum.ALL;
        if (userEntity != null) {
            permissionGroupEnum = userEntity.getPermissionGroup();
        }
        VideoEntity videoEntity = videoEntityController.getVideo(videoFiles, userSettingsEntity);
        boolean validVideo = videoEntity != null;
        Long id = null;
        if (validVideo) {
            id = videoEntity.getId();
        }
        return document(
            html(
                HeadComponent.render(settingsController.getServerName()),
                TopBarComponent.render(notificationsController.getNumberOfUnread(userEntity), QueueEntityController.getQueueSize(), loggedIn, permissionGroupEnum, settingsController.isEnableUpload()),
                SideBarComponent.render(loggedIn,
                    settingsController.isMovieLibraryEnable(),
                    settingsController.isTvShowLibraryEnable(),
                    settingsController.isGameLibraryEnable(),
                    settingsController.isMusicLibraryEnable(),
                    settingsController.isBookLibraryEnable()),
                body(
                    div(
                        br(),
                        label(movieEntity.getTitle()).withClass("overviewLabel"),
                        br(),
                        br(),
                        iff(validVideo,
                            video(
                                source().withSrc("/v1/video/" + id).withType("video/mp4"),
                                text("Your browser does not support the video tag")
                            ).isControls().withHeight("%100").withWidth("%100")
                        ),
                        br(),
                        br(),
                        label("Runtime:").withClass("subLabel"),
                        label(String.valueOf(movieEntity.getRuntime())),
                        label("MPAA rating:").withClass("subLabel"),
                        label(String.valueOf(movieEntity.getMpaaRating())),
                        label("User rating:").withClass("subLabel"),
                        label(String.valueOf(movieEntity.getUserRating())),
                        br(),
                        br()
                    ).withClass("body")
                )
            )
        ).getBytes();
    }
}
