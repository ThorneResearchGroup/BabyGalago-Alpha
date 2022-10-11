package tech.tresearchgroup.babygalago.controller.modules;


import com.google.gson.Gson;
import io.activej.inject.annotation.Provides;
import io.activej.inject.module.AbstractModule;
import tech.tresearchgroup.babygalago.controller.SettingsController;
import tech.tresearchgroup.babygalago.controller.controllers.*;
import tech.tresearchgroup.babygalago.controller.endpoints.AssetEndpointController;
import tech.tresearchgroup.babygalago.controller.endpoints.api.*;
import tech.tresearchgroup.babygalago.controller.endpoints.ui.CRUDEndpointsController;
import tech.tresearchgroup.babygalago.controller.endpoints.ui.MainEndpointsController;
import tech.tresearchgroup.babygalago.controller.endpoints.ui.PlayEndpointsController;
import tech.tresearchgroup.babygalago.view.endpoints.AssetEndpoint;
import tech.tresearchgroup.babygalago.view.endpoints.api.*;
import tech.tresearchgroup.babygalago.view.endpoints.ui.CRUDEndpoints;
import tech.tresearchgroup.babygalago.view.endpoints.ui.MainEndpoints;
import tech.tresearchgroup.babygalago.view.endpoints.ui.PlayEndpoints;
import tech.tresearchgroup.babygalago.view.endpoints.ui.UIUserEndpoints;
import tech.tresearchgroup.colobus.controller.IndexController;

public class RestModule extends AbstractModule {
    @Provides
    RatingEndpoints ratingEndpoints(RatingEndpointsController ratingEndpointsController, SettingsController settingsController) {
        return new RatingEndpoints(ratingEndpointsController, settingsController);
    }

    @Provides
    GeneralEndpoints generalEndpoints(GeneralEndpointsController generalEndpointsController,
                                      RatingEntityController ratingEntityController,
                                      AlbumEntityController albumEntityController,
                                      ArtistEntityController artistEntityController,
                                      BookEntityController bookEntityController,
                                      CharacterEntityController characterEntityController,
                                      CompanyEntityController companyEntityController,
                                      GameEngineEntityController gameEngineEntityController,
                                      GameEntityController gameEntityController,
                                      GamePlatformReleaseEntityController gamePlatformReleaseEntityController,
                                      GameSeriesEntityController gameSeriesEntityController,
                                      ImageEntityController imageEntityController,
                                      LocationEntityController locationEntityController,
                                      LyricsEntityController lyricsEntityController,
                                      MovieEntityController movieEntityController,
                                      PersonEntityController personEntityController,
                                      SeasonEntityController seasonEntityController,
                                      SongEntityController songEntityController,
                                      SubtitleEntityController subtitleEntityController,
                                      TvShowEntityController tvShowEntityController,
                                      VideoEntityController videoEntityController,
                                      SettingsController settingsController) {
        return new GeneralEndpoints(
            generalEndpointsController,
            ratingEntityController,
            albumEntityController,
            artistEntityController,
            bookEntityController,
            characterEntityController,
            companyEntityController,
            gameEngineEntityController,
            gameEntityController,
            gamePlatformReleaseEntityController,
            gameSeriesEntityController,
            imageEntityController,
            locationEntityController,
            lyricsEntityController,
            movieEntityController,
            personEntityController,
            seasonEntityController,
            songEntityController,
            subtitleEntityController,
            tvShowEntityController,
            videoEntityController,
            settingsController
        );
    }

    @Provides
    MediaTypeEndpoints mediaTypeEndpoints(MediaTypeEndpointsController mediaTypeEndpointsController,
                                          SettingsController settingsController) {
        return new MediaTypeEndpoints(
            settingsController,
            mediaTypeEndpointsController
        );
    }

    @Provides
    NewsEndpoints newsEndpoints(NewsEndpointsController newsEndpointsController, SettingsController settingsController) {
        return new NewsEndpoints(newsEndpointsController, settingsController);
    }

    @Provides
    NotificationsEndpoints notificationsEndpoints(NotificationsEndpointsController notificationsEndpointsController, SettingsController settingsController) {
        return new NotificationsEndpoints(notificationsEndpointsController, settingsController);
    }

    @Provides
    QueueEndpoints queueEndpoints(QueueEndpointsController queueEndpointsController) {
        return new QueueEndpoints(queueEndpointsController);
    }

    @Provides
    SettingsEndpoints settingsEndpoints(SettingsEndpointsController settingsEndpointsController,
                                        SettingsController settingsController) {
        return new SettingsEndpoints(settingsEndpointsController, settingsController);
    }

    @Provides
    UserEndpoints userEndpoints(UserEndpointsController userEndpointsController, SettingsController settingsController) {
        return new UserEndpoints(userEndpointsController, settingsController);
    }

    @Provides
    LoginEndpoints loginEndpoints(LoginEndpointsController loginEndpointsController, SettingsController settingsController, Gson gson) {
        return new LoginEndpoints(loginEndpointsController, settingsController, gson);
    }

    @Provides
    CRUDEndpoints addEndpoints(CRUDEndpointsController CRUDEndpointsController, SettingsController settingsController) {
        return new CRUDEndpoints(CRUDEndpointsController, settingsController);
    }

    @Provides
    MainEndpoints mainEndpoints(MainEndpointsController mainEndpointsController, IndexController indexController, SettingsController settingsController) {
        return new MainEndpoints(mainEndpointsController, indexController, settingsController);
    }

    @Provides
    PlayEndpoints playEndpoints(PlayEndpointsController playEndpointsController, SettingsController settingsController) {
        return new PlayEndpoints(playEndpointsController, settingsController);
    }

    @Provides
    AssetEndpoint assetEndpoint(AssetEndpointController assetEndpointController, SettingsController settingsController) {
        return new AssetEndpoint(assetEndpointController, settingsController);
    }

    @Provides
    TaskEndpoints taskEndpoints(TaskEndpointsController taskEndpointsController) {
        return new TaskEndpoints(taskEndpointsController);
    }

    @Provides
    UIUserEndpoints uiUserEndpoints(UserSettingsEntityController userSettingsEntityController, SettingsController settingsController) {
        return new UIUserEndpoints(userSettingsEntityController, settingsController);
    }
}
