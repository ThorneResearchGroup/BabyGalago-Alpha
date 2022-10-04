package tech.tresearchgroup.babygalago.controller.controllers;

import com.google.gson.Gson;
import com.meilisearch.sdk.Client;
import com.zaxxer.hikari.HikariDataSource;
import io.activej.serializer.BinarySerializer;
import tech.tresearchgroup.babygalago.controller.SettingsController;
import tech.tresearchgroup.palila.controller.GenericController;
import tech.tresearchgroup.palila.model.enums.PermissionGroupEnum;
import tech.tresearchgroup.schemas.galago.entities.UserSettingsEntity;
import tech.tresearchgroup.schemas.galago.entities.VideoEntity;

import java.util.List;

public class VideoController extends GenericController {
    private final SettingsController settingsController;

    public VideoController(HikariDataSource hikariDataSource,
                           Gson gson,
                           Client client,
                           BinarySerializer<VideoEntity> serializer,
                           int reindexSize,
                           SettingsController settingsController,
                           Object sample,
                           UserController userController) throws Exception {
        super(
            hikariDataSource,
            gson,
            client,
            VideoEntity.class,
            serializer,
            reindexSize,
            "title",
            sample,
            PermissionGroupEnum.USER,
            PermissionGroupEnum.USER,
            PermissionGroupEnum.USER,
            PermissionGroupEnum.USER,
            PermissionGroupEnum.USER,
            userController
        );
        this.settingsController = settingsController;
    }

    public VideoEntity getVideo(List<VideoEntity> availableVideos, UserSettingsEntity userSettingsEntity) {
        if (availableVideos != null) {
            for (VideoEntity video : availableVideos) {
                if (video.getPlaybackQualityEnum().equals(settingsController.getDefaultPlaybackQuality(userSettingsEntity))) {
                    return video;
                }
            }
        }
        return null;
    }

}
