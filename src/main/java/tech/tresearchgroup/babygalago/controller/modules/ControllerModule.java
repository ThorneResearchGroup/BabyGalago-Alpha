package tech.tresearchgroup.babygalago.controller.modules;


import com.google.gson.Gson;
import com.meilisearch.sdk.Client;
import com.zaxxer.hikari.HikariDataSource;
import io.activej.inject.annotation.Provides;
import io.activej.inject.module.AbstractModule;
import io.activej.serializer.BinarySerializer;
import io.activej.serializer.SerializerBuilder;
import org.quartz.SchedulerException;
import tech.tresearchgroup.babygalago.controller.SettingsController;
import tech.tresearchgroup.babygalago.controller.TaskController;
import tech.tresearchgroup.babygalago.controller.controllers.*;
import tech.tresearchgroup.babygalago.controller.endpoints.AssetEndpointController;
import tech.tresearchgroup.babygalago.controller.endpoints.api.*;
import tech.tresearchgroup.babygalago.controller.endpoints.ui.*;
import tech.tresearchgroup.babygalago.model.ExtendedUserEntity;
import tech.tresearchgroup.babygalago.view.components.forms.*;
import tech.tresearchgroup.babygalago.view.components.forms.subTypes.*;
import tech.tresearchgroup.babygalago.view.pages.*;
import tech.tresearchgroup.babygalago.view.pages.play.*;
import tech.tresearchgroup.schemas.galago.entities.*;
import tech.tresearchgroup.schemas.galago.enums.*;

import java.util.List;

public class ControllerModule extends AbstractModule {
    @Provides
    TaskController scheduleController() throws SchedulerException {
        return new TaskController();
    }

    @Provides
    AddEndpointsController addEndpointsController(UserController userController,
                                                  SettingsController settingsController,
                                                  DisabledPage disabledPage,
                                                  BookForm bookForm,
                                                  GameForm gameForm,
                                                  MovieForm movieForm,
                                                  MusicForm musicForm,
                                                  TvShowForm tvShowForm,
                                                  AlbumForm albumForm,
                                                  ArtistForm artistForm,
                                                  CharacterForm characterForm,
                                                  CompanyForm companyForm,
                                                  EpisodeForm episodeForm,
                                                  GameEngineForm gameEngineForm,
                                                  GamePlatformReleaseForm gamePlatformReleaseForm,
                                                  GameSeriesForm gameSeriesForm,
                                                  ImageForm imageForm,
                                                  LocationForm locationForm,
                                                  LyricsForm lyricsForm,
                                                  PersonForm personForm,
                                                  SeasonForm seasonForm,
                                                  SubtitleForm subtitleForm,
                                                  VideoForm videoForm) {
        return new AddEndpointsController(
            userController,
            settingsController,
            disabledPage,
            bookForm,
            gameForm,
            movieForm,
            musicForm,
            tvShowForm,
            albumForm,
            artistForm,
            characterForm,
            companyForm,
            episodeForm,
            gameEngineForm,
            gamePlatformReleaseForm,
            gameSeriesForm,
            imageForm,
            locationForm,
            lyricsForm,
            personForm,
            seasonForm,
            subtitleForm,
            videoForm
        );
    }

    @Provides
    AssetEndpointController assetEndpointController(SettingsController settingsController) {
        return new AssetEndpointController(settingsController);
    }

    @Provides
    BrowseEndpointsController browseEndpointsController(BookController bookController,
                                                        GameController gameController,
                                                        MovieController movieController,
                                                        SongController songController,
                                                        TvShowController tvShowController,
                                                        UserController userController,
                                                        SettingsController settingsController,
                                                        ViewPage viewPage) {
        return new BrowseEndpointsController(
            bookController,
            gameController,
            movieController,
            songController,
            tvShowController,
            userController,
            settingsController,
            viewPage
        );
    }

    @Provides
    EditEndpointsController editEndpointsController(BookController bookController,
                                                    BookForm bookForm,
                                                    GameController gameController,
                                                    GameForm gameForm,
                                                    MovieController movieController,
                                                    MovieForm movieForm,
                                                    SongController songController,
                                                    MusicForm musicForm,
                                                    TvShowController tvShowController,
                                                    TvShowForm tvShowForm,
                                                    AlbumController albumController,
                                                    AlbumForm albumForm,
                                                    ArtistController artistController,
                                                    ArtistForm artistForm,
                                                    CharacterController characterController,
                                                    CharacterForm characterForm,
                                                    CompanyController companyController,
                                                    CompanyForm companyForm,
                                                    EpisodeController episodeController,
                                                    EpisodeForm episodeForm,
                                                    GameEngineController gameEngineController,
                                                    GameEngineForm gameEngineForm,
                                                    GamePlatformReleaseController gamePlatformReleaseController,
                                                    GamePlatformReleaseForm gamePlatformReleaseForm,
                                                    GameSeriesController gameSeriesController,
                                                    GameSeriesForm gameSeriesForm,
                                                    LocationController locationController,
                                                    LocationForm locationForm,
                                                    PersonController personController,
                                                    PersonForm personForm,
                                                    SeasonController seasonController,
                                                    SeasonForm seasonForm,
                                                    UserController userController,
                                                    SettingsController settingsController,
                                                    Gson gson) {
        return new EditEndpointsController(
            bookController,
            bookForm,
            gameController,
            gameForm,
            movieController,
            movieForm,
            songController,
            musicForm,
            tvShowController,
            tvShowForm,
            albumController,
            albumForm,
            artistController,
            artistForm,
            characterController,
            characterForm,
            companyController,
            companyForm,
            episodeController,
            episodeForm,
            gameEngineController,
            gameEngineForm,
            gamePlatformReleaseController,
            gamePlatformReleaseForm,
            gameSeriesController,
            gameSeriesForm,
            locationController,
            locationForm,
            personController,
            personForm,
            seasonController,
            seasonForm,
            userController,
            settingsController,
            gson
        );
    }

    @Provides
    MainEndpointsController mainEndpointsController(MovieController movieController,
                                                    TvShowController tvShowController,
                                                    GameController gameController,
                                                    SongController songController,
                                                    BookController bookController,
                                                    NotificationController notificationController,
                                                    NewsArticleController newsArticleController,
                                                    QueueController queueController,
                                                    UserController userController,
                                                    SettingsController settingsController,
                                                    AboutPage aboutPage,
                                                    IndexPage indexPage,
                                                    LoginPage loginPage,
                                                    ResetPage resetPage,
                                                    RegisterPage registerPage,
                                                    LicensesPage licensesPage,
                                                    NewsPage newsPage,
                                                    NotificationsPage notificationsPage,
                                                    ProfilePage profilePage,
                                                    QueuePage queuePage,
                                                    SearchPage searchPage,
                                                    SettingsPage settingsPage,
                                                    UploadPage uploadPage,
                                                    DeniedPage deniedPage) {
        return new MainEndpointsController(
            movieController,
            tvShowController,
            gameController,
            songController,
            bookController,
            notificationController,
            newsArticleController,
            queueController,
            userController,
            settingsController,
            aboutPage,
            indexPage,
            loginPage,
            resetPage,
            registerPage,
            licensesPage,
            newsPage,
            notificationsPage,
            profilePage,
            queuePage,
            searchPage,
            settingsPage,
            uploadPage,
            deniedPage);
    }

    @Provides
    NewEndpointsController nouveauEndpointsController(BookController bookController,
                                                      GameController gameController,
                                                      MovieController movieController,
                                                      SongController songController,
                                                      TvShowController tvShowController,
                                                      UserController userController,
                                                      SettingsController settingsController,
                                                      ViewPage viewPage,
                                                      BinarySerializer<ExtendedUserEntity> userSerializer) {
        return new NewEndpointsController(
            bookController,
            gameController,
            movieController,
            songController,
            tvShowController,
            userController,
            settingsController,
            viewPage
        );
    }

    @Provides
    PlayEndpointsController playEndpointsController(BookController bookController,
                                                    GameController gameController,
                                                    MovieController movieController,
                                                    SongController songController,
                                                    TvShowController tvShowController,
                                                    UserController userController,
                                                    SettingsController settingsController,
                                                    VideoController videoController,
                                                    PlayBookPage playBookPage,
                                                    PlayGamePage playGamePage,
                                                    PlayMoviePage playMoviePage,
                                                    PlayMusicPage playMusicPage,
                                                    PlayTvShowPage playTvShowPage) {
        return new PlayEndpointsController(
            bookController,
            gameController,
            movieController,
            songController,
            tvShowController,
            userController,
            settingsController,
            videoController,
            playBookPage,
            playGamePage,
            playMoviePage,
            playMusicPage,
            playTvShowPage
        );
    }

    @Provides
    PopularEndpointsController popularEndpointsController(BookController bookController,
                                                          GameController gameController,
                                                          MovieController movieController,
                                                          SongController songController,
                                                          TvShowController tvShowController,
                                                          UserController userController,
                                                          SettingsController settingsController,
                                                          ViewPage viewPage) {
        return new PopularEndpointsController(
            bookController,
            gameController,
            movieController,
            songController,
            tvShowController,
            userController,
            settingsController,
            viewPage
        );
    }

    @Provides
    ViewEndpointsController viewEndpointsController(BookController bookController,
                                                    GameController gameController,
                                                    MovieController movieController,
                                                    SongController songController,
                                                    TvShowController tvShowController,
                                                    UserController userController,
                                                    AlbumController albumController,
                                                    ArtistController artistController,
                                                    CharacterController characterController,
                                                    CompanyController companyController,
                                                    EpisodeController episodeController,
                                                    GameEngineController gameEngineController,
                                                    GamePlatformReleaseController gamePlatformReleaseController,
                                                    GameSeriesController gameSeriesController,
                                                    LocationController locationController,
                                                    PersonController personController,
                                                    SeasonController seasonController,
                                                    ImageController imageController,
                                                    SubtitleController subtitleController,
                                                    VideoController videoController,
                                                    SettingsController settingsController,
                                                    DisabledPage disabledPage,
                                                    BookForm bookForm,
                                                    GameForm gameForm,
                                                    MovieForm movieForm,
                                                    MusicForm musicForm,
                                                    TvShowForm tvShowForm,
                                                    AlbumForm albumForm,
                                                    ArtistForm artistForm,
                                                    CharacterForm characterForm,
                                                    CompanyForm companyForm,
                                                    EpisodeForm episodeForm,
                                                    GameEngineForm gameEngineForm,
                                                    GamePlatformReleaseForm gamePlatformReleaseForm,
                                                    GameSeriesForm gameSeriesForm,
                                                    ImageForm imageForm,
                                                    LocationForm locationForm,
                                                    PersonForm personForm,
                                                    SeasonForm seasonForm,
                                                    SubtitleForm subtitleForm,
                                                    VideoForm videoForm) {
        return new ViewEndpointsController(
            bookController,
            gameController,
            movieController,
            songController,
            tvShowController,
            userController,
            albumController,
            artistController,
            characterController,
            companyController,
            episodeController,
            gameEngineController,
            gamePlatformReleaseController,
            gameSeriesController,
            locationController,
            personController,
            seasonController,
            imageController,
            subtitleController,
            videoController,
            settingsController,
            disabledPage,
            bookForm,
            gameForm,
            movieForm,
            musicForm,
            tvShowForm,
            albumForm,
            artistForm,
            characterForm,
            companyForm,
            episodeForm,
            gameEngineForm,
            gamePlatformReleaseForm,
            gameSeriesForm,
            imageForm,
            locationForm,
            personForm,
            seasonForm,
            subtitleForm,
            videoForm
        );
    }

    @Provides
    UserController userController(HikariDataSource hikariDataSource,
                                  Gson gson,
                                  Client client) throws Exception {
        return new UserController(
            hikariDataSource,
            gson,
            client,
            SerializerBuilder.create().build(ExtendedUserEntity.class),
            20,
            new ExtendedUserEntity()
        );
    }

    @Provides
    TaskEndpointsController taskEndpointsController(TaskController scheduleController,
                                                    UserController userController) {
        return new TaskEndpointsController(
            scheduleController,
            userController
        );
    }

    @Provides
    SettingsEndpointsController settingsEndpointsController(UserController userController) {
        return new SettingsEndpointsController(
            userController
        );
    }

    @Provides
    QueueEndpointsController queueEndpointsController(QueueController queueController,
                                                      UserController userController) {
        return new QueueEndpointsController(
            queueController,
            userController
        );
    }

    @Provides
    MovieController movieController(HikariDataSource hikariDataSource,
                                    Gson gson,
                                    Client client,
                                    UserController userController) throws Exception {
        return new MovieController(
            hikariDataSource,
            gson,
            client,
            SerializerBuilder.create()
                .withSubclasses("files", List.of(FileEntity.class))
                .withSubclasses("otherImages", List.of(ImageEntity.class))
                .withSubclasses("otherVideos", List.of(VideoEntity.class))
                .withSubclasses("subtitles", List.of(SubtitleEntity.class))
                .withSubclasses("directors", List.of(PersonEntity.class))
                .withSubclasses("writers", List.of(PersonEntity.class))
                .withSubclasses("cast", List.of(PersonEntity.class))
                .withSubclasses("productionCompany", List.of(CompanyEntity.class))
                .build(MovieEntity.class),
            20,
            new MovieEntity(),
            userController
        );
    }

    @Provides
    SubtitleController subtitleController(HikariDataSource hikariDataSource,
                                          Gson gson,
                                          Client client,
                                          UserController userController) throws Exception {
        return new SubtitleController(
            hikariDataSource,
            gson,
            client,
            SerializerBuilder.create().build(SubtitleEntity.class),
            20,
            new SubtitleEntity(),
            userController
        );
    }

    @Provides
    GamePlatformReleaseController gamePlatformReleaseController(HikariDataSource hikariDataSource,
                                                                Gson gson,
                                                                Client client,
                                                                UserController userController) throws Exception {
        return new GamePlatformReleaseController(
            hikariDataSource,
            gson,
            client,
            SerializerBuilder.create().build(GamePlatformReleaseEntity.class),
            20,
            new GamePlatformReleaseEntity(),
            userController
        );
    }

    @Provides
    SongController songController(HikariDataSource hikariDataSource,
                                  Gson gson,
                                  Client client,
                                  UserController userController) throws Exception {
        return new SongController(
            hikariDataSource,
            gson,
            client,
            SerializerBuilder.create()
                .withSubclasses("inAlbums", List.of(AlbumEntity.class))
                .withSubclasses("lyrics", List.of(LyricsEntity.class))
                .build(SongEntity.class),
            20,
            new SongEntity(),
            userController
        );
    }

    @Provides
    RatingController ratingController(HikariDataSource hikariDataSource,
                                      Gson gson,
                                      Client client,
                                      UserController userController) throws Exception {
        return new RatingController(
            hikariDataSource,
            gson,
            client,
            SerializerBuilder.create().build(RatingEntity.class),
            20,
            new RatingEntity(),
            userController
        );
    }

    @Provides
    BookController bookController(HikariDataSource hikariDataSource,
                                  Gson gson,
                                  Client client,
                                  UserController userController) throws Exception {
        return new BookController(
            hikariDataSource,
            gson,
            client,
            SerializerBuilder.create()
                .withSubclasses("otherImages", List.of(ImageEntity.class))
                .withSubclasses("files", List.of(FileEntity.class))
                .withSubclasses("authors", List.of(PersonEntity.class))
                .build(BookEntity.class),
            20,
            new BookEntity(),
            userController
        );
    }

    @Provides
    GameSeriesController gameSeriesController(HikariDataSource hikariDataSource,
                                              Gson gson,
                                              Client client,
                                              UserController userController) throws Exception {
        return new GameSeriesController(
            hikariDataSource,
            gson,
            client,
            SerializerBuilder.create().build(GameSeriesEntity.class),
            20,
            new GameSeriesEntity(),
            userController
        );
    }

    @Provides
    GameEngineController gameEngineController(HikariDataSource hikariDataSource,
                                              Gson gson,
                                              Client client,
                                              UserController userController) throws Exception {
        return new GameEngineController(
            hikariDataSource,
            gson,
            client,
            SerializerBuilder.create().build(GameEngineEntity.class),
            20,
            new GameEngineEntity(),
            userController
        );
    }

    @Provides
    AlbumController albumController(HikariDataSource hikariDataSource,
                                    Gson gson,
                                    Client client,
                                    UserController userController) throws Exception {
        return new AlbumController(
            hikariDataSource,
            gson,
            client,
            SerializerBuilder.create()
                .withSubclasses("otherImages", List.of(ImageEntity.class))
                .withSubclasses("artists", List.of(ArtistEntity.class))
                .withSubclasses("songs", List.of(SongEntity.class))
                .build(AlbumEntity.class),
            20,
            new AlbumEntity(),
            userController
        );
    }

    @Provides
    TvShowController tvShowController(HikariDataSource hikariDataSource,
                                      Gson gson,
                                      Client client,
                                      UserController userController) throws Exception {
        return new TvShowController(
            hikariDataSource,
            gson,
            client,
            SerializerBuilder.create()
                .withSubclasses("files", List.of(FileEntity.class))
                .withSubclasses("genres", List.of(TvShowGenreEnum.class))
                .withSubclasses("filmLocations", List.of(LocationEntity.class))
                .withSubclasses("seasons", List.of(SeasonEntity.class))
                .withSubclasses("otherVideos", List.of(VideoEntity.class))
                .withSubclasses("otherImages", List.of(ImageEntity.class))
                .build(TvShowEntity.class),
            20,
            new TvShowEntity(),
            userController
        );
    }

    @Provides
    PersonController personController(HikariDataSource hikariDataSource,
                                      Gson gson,
                                      Client client,
                                      UserController userController) throws Exception {
        return new PersonController(
            hikariDataSource,
            gson,
            client,
            SerializerBuilder.create().build(PersonEntity.class),
            20,
            new PersonEntity(),
            userController
        );
    }

    @Provides
    SeasonController seasonController(HikariDataSource hikariDataSource,
                                      Gson gson,
                                      Client client,
                                      UserController userController) throws Exception {
        return new SeasonController(
            hikariDataSource,
            gson,
            client,
            SerializerBuilder.create().build(SeasonEntity.class),
            20,
            new SeasonEntity(),
            userController
        );
    }

    @Provides
    GameController gameController(HikariDataSource hikariDataSource,
                                  Gson gson,
                                  Client client,
                                  UserController userController) throws Exception {
        return new GameController(
            hikariDataSource,
            gson,
            client,
            SerializerBuilder.create()
                .withSubclasses("files", List.of(FileEntity.class))
                .withSubclasses("otherImages", List.of(ImageEntity.class))
                .withSubclasses("otherVideos", List.of(VideoEntity.class))
                .withSubclasses("genres", List.of(GameGenreEnum.class))
                .withSubclasses("contentWarnings", List.of(GameContentWarningEnum.class))
                .withSubclasses("platforms", List.of(GamePlatformEnum.class))
                .withSubclasses("developers", List.of(CompanyEntity.class))
                .withSubclasses("publishers", List.of(CompanyEntity.class))
                .withSubclasses("gameModes", List.of(GameModeEnum.class))
                .withSubclasses("gamePlayerPerspective", List.of(GamePlayerPerspectiveEnum.class))
                .build(GameEntity.class),
            20,
            new GameEntity(),
            userController
        );
    }

    @Provides
    ArtistController artistController(HikariDataSource hikariDataSource,
                                      Gson gson,
                                      Client client,
                                      UserController userController) throws Exception {
        return new ArtistController(
            hikariDataSource,
            gson,
            client,
            SerializerBuilder.create()
                .withSubclasses("otherImages", List.of(ImageEntity.class))
                .withSubclasses("members", List.of(PersonEntity.class))
                .withSubclasses("albums", List.of(AlbumEntity.class))
                .build(ArtistEntity.class),
            20,
            new ArtistEntity(),
            userController
        );
    }

    @Provides
    ImageController imageController(HikariDataSource hikariDataSource,
                                    Gson gson,
                                    Client client,
                                    UserController userController) throws Exception {
        return new ImageController(
            hikariDataSource,
            gson,
            client,
            SerializerBuilder.create().build(ImageEntity.class),
            20,
            new ImageEntity(),
            userController
        );
    }

    @Provides
    NotificationController notificationController(HikariDataSource hikariDataSource,
                                                  Gson gson,
                                                  Client client,
                                                  UserController userController) throws Exception {
        return new NotificationController(
            hikariDataSource,
            gson,
            client,
            SerializerBuilder.create().build(NotificationEntity.class),
            20,
            new NotificationEntity(),
            userController
        );
    }

    @Provides
    LyricsController lyricsController(HikariDataSource hikariDataSource,
                                      Gson gson,
                                      Client client,
                                      UserController userController) throws Exception {
        return new LyricsController(
            hikariDataSource,
            gson,
            client,
            SerializerBuilder.create().build(LyricsEntity.class),
            20,
            new LyricsEntity(),
            userController
        );
    }

    @Provides
    CompanyController companyController(HikariDataSource hikariDataSource,
                                        Gson gson,
                                        Client client,
                                        UserController userController) throws Exception {
        return new CompanyController(
            hikariDataSource,
            gson,
            client,
            SerializerBuilder.create().build(CompanyEntity.class),
            20,
            new CompanyEntity(),
            userController
        );
    }

    @Provides
    LoginEndpointsController loginEndpointsController(UserController userController,
                                                      Gson gson) {
        return new LoginEndpointsController(
            userController,
            gson
        );
    }

    @Provides
    NewsArticleController newsArticleController(HikariDataSource hikariDataSource,
                                                Gson gson,
                                                Client client,
                                                UserController userController) throws Exception {
        return new NewsArticleController(
            hikariDataSource,
            gson,
            client,
            SerializerBuilder.create().build(NewsArticleEntity.class),
            20,
            new NewsArticleEntity(),
            userController
        );
    }

    @Provides
    LocationController locationController(HikariDataSource hikariDataSource,
                                          Gson gson,
                                          Client client,
                                          UserController userController) throws Exception {
        return new LocationController(
            hikariDataSource,
            gson,
            client,
            SerializerBuilder.create().build(LocationEntity.class),
            20,
            new LocationEntity(),
            userController
        );
    }

    @Provides
    VideoController videoController(HikariDataSource hikariDataSource,
                                    Gson gson,
                                    Client client,
                                    SettingsController settingsController,
                                    UserController userController) throws Exception {
        return new VideoController(
            hikariDataSource,
            gson,
            client,
            SerializerBuilder.create().build(VideoEntity.class),
            20,
            settingsController,
            new VideoEntity(),
            userController
        );
    }

    @Provides
    CharacterController characterController(HikariDataSource hikariDataSource,
                                            Gson gson,
                                            Client client,
                                            UserController userController) throws Exception {
        return new CharacterController(
            hikariDataSource,
            gson,
            client,
            SerializerBuilder.create().build(CharacterEntity.class),
            20,
            new CharacterEntity(),
            userController
        );
    }

    @Provides
    GeneralEndpointsController generalEndpointsController(ImageController imageController,
                                                          VideoController videoController,
                                                          FileController fileController,
                                                          UserController userController,
                                                          Gson gson) {
        return new GeneralEndpointsController(
            imageController,
            videoController,
            fileController,
            userController,
            gson
        );
    }

    @Provides
    QueueController queueController(HikariDataSource hikariDataSource,
                                    Gson gson,
                                    Client client,
                                    SettingsController settingsController,
                                    UserController userController) throws Exception {
        return new QueueController(
            hikariDataSource,
            gson,
            client,
            SerializerBuilder.create().build(QueueEntity.class),
            20,
            settingsController,
            userController
        );
    }

    @Provides
    EpisodeController episodeController(HikariDataSource hikariDataSource,
                                        Gson gson,
                                        Client client,
                                        UserController userController) throws Exception {
        return new EpisodeController(
            hikariDataSource,
            gson,
            client,
            SerializerBuilder.create()
                .withSubclasses("filmLocations", List.of(LocationEntity.class))
                .withSubclasses("otherImages", List.of(ImageEntity.class))
                .withSubclasses("otherVideos", List.of(VideoEntity.class))
                .build(EpisodeEntity.class),
            20,
            new EpisodeEntity(),
            userController
        );
    }

    @Provides
    FileController fileController(HikariDataSource hikariDataSource,
                                  Gson gson,
                                  Client client,
                                  UserController userController) throws Exception {
        return new FileController(
            hikariDataSource,
            gson,
            client,
            SerializerBuilder.create().build(FileEntity.class),
            20,
            new FileEntity(),
            userController
        );
    }

    @Provides
    UserSettingsController userSettingsController(HikariDataSource hikariDataSource,
                                                  Gson gson,
                                                  Client client,
                                                  UserController userController) throws Exception {
        return new UserSettingsController(
            hikariDataSource,
            gson,
            client,
            SerializerBuilder.create().build(UserSettingsEntity.class),
            20,
            new UserSettingsEntity(),
            userController);
    }
}
