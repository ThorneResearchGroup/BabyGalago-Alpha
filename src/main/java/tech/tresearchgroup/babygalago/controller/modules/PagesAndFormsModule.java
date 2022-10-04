package tech.tresearchgroup.babygalago.controller.modules;

import io.activej.inject.annotation.Provides;
import io.activej.inject.module.AbstractModule;
import tech.tresearchgroup.babygalago.controller.SettingsController;
import tech.tresearchgroup.babygalago.controller.controllers.MovieController;
import tech.tresearchgroup.babygalago.controller.controllers.NotificationController;
import tech.tresearchgroup.babygalago.controller.controllers.VideoController;
import tech.tresearchgroup.babygalago.view.components.forms.*;
import tech.tresearchgroup.babygalago.view.components.forms.subTypes.*;
import tech.tresearchgroup.babygalago.view.pages.*;
import tech.tresearchgroup.babygalago.view.pages.play.*;

public class PagesAndFormsModule extends AbstractModule {
    @Provides
    AboutPage aboutPage(SettingsController settingsController, NotificationController notificationController) {
        return new AboutPage(settingsController, notificationController);
    }

    @Provides
    DeniedPage deniedPage(SettingsController settingsController, NotificationController notificationController) {
        return new DeniedPage(settingsController, notificationController);
    }

    @Provides
    DisabledPage disabledPage(SettingsController settingsController, NotificationController notificationController) {
        return new DisabledPage(settingsController, notificationController);
    }

    @Provides
    IndexPage indexPage(SettingsController settingsController, NotificationController notificationController) {
        return new IndexPage(settingsController, notificationController);
    }

    @Provides
    LicensesPage licensesPage(SettingsController settingsController, NotificationController notificationController) {
        return new LicensesPage(settingsController, notificationController);
    }

    @Provides
    LoginPage loginPage(SettingsController settingsController, NotificationController notificationController) {
        return new LoginPage(settingsController, notificationController);
    }

    @Provides
    MaintenancePage maintenancePage(SettingsController settingsController) {
        return new MaintenancePage(settingsController);
    }

    @Provides
    NewsPage newsPage(SettingsController settingsController, NotificationController notificationController) {
        return new NewsPage(settingsController, notificationController);
    }

    @Provides
    NotificationsPage notificationsPage(SettingsController settingsController, NotificationController notificationController) {
        return new NotificationsPage(settingsController, notificationController);
    }

    @Provides
    ProfilePage profilePage(SettingsController settingsController, NotificationController notificationController) {
        return new ProfilePage(settingsController, notificationController);
    }

    @Provides
    QueuePage queuePage(SettingsController settingsController, NotificationController notificationController) {
        return new QueuePage(settingsController, notificationController);
    }

    @Provides
    RegisterPage registerPage(SettingsController settingsController, NotificationController notificationController) {
        return new RegisterPage(settingsController, notificationController);
    }

    @Provides
    ResetPage resetPage(SettingsController settingsController, NotificationController notificationController) {
        return new ResetPage(settingsController, notificationController);
    }

    @Provides
    SearchPage searchPage(SettingsController settingsController, NotificationController notificationController) {
        return new SearchPage(settingsController, notificationController);
    }

    @Provides
    UploadPage uploadPage(SettingsController settingsController, NotificationController notificationController) {
        return new UploadPage(settingsController, notificationController);
    }

    @Provides
    ViewPage viewPage(SettingsController settingsController, NotificationController notificationController) {
        return new ViewPage(settingsController, notificationController);
    }

    @Provides
    PlayBookPage playBookPage(SettingsController settingsController, NotificationController notificationController) {
        return new PlayBookPage(settingsController, notificationController);
    }

    @Provides
    PlayGamePage playGamePage(SettingsController settingsController, NotificationController notificationController) {
        return new PlayGamePage(settingsController, notificationController);
    }

    @Provides
    PlayMoviePage playMoviePage(SettingsController settingsController, NotificationController notificationController, VideoController videoController) {
        return new PlayMoviePage(settingsController, notificationController, videoController);
    }

    @Provides
    PlayMusicPage playMusicPage(SettingsController settingsController, NotificationController notificationController) {
        return new PlayMusicPage(settingsController, notificationController);
    }

    @Provides
    PlayTvShowPage playTvShowPage(SettingsController settingsController, NotificationController notificationController) {
        return new PlayTvShowPage(settingsController, notificationController);
    }

    @Provides
    BookForm bookForm(SettingsController settingsController, NotificationController notificationController) {
        return new BookForm(settingsController, notificationController);
    }

    @Provides
    GameForm gameForm(SettingsController settingsController, NotificationController notificationController) {
        return new GameForm(settingsController, notificationController);
    }

    @Provides
    MovieForm movieForm(SettingsController settingsController, NotificationController notificationController, MovieController movieController) {
        return new MovieForm(settingsController, notificationController, movieController);
    }

    @Provides
    MusicForm musicForm(SettingsController settingsController, NotificationController notificationController) {
        return new MusicForm(settingsController, notificationController);
    }

    @Provides
    TvShowForm tvShowForm(SettingsController settingsController, NotificationController notificationController) {
        return new TvShowForm(settingsController, notificationController);
    }

    @Provides
    AlbumForm albumForm(SettingsController settingsController, NotificationController notificationController) {
        return new AlbumForm(settingsController, notificationController);
    }

    @Provides
    ArtistForm artistForm(SettingsController settingsController, NotificationController notificationController) {
        return new ArtistForm(settingsController, notificationController);
    }

    @Provides
    CharacterForm characterForm(SettingsController settingsController, NotificationController notificationController) {
        return new CharacterForm(settingsController, notificationController);
    }

    @Provides
    CompanyForm companyForm(SettingsController settingsController, NotificationController notificationController) {
        return new CompanyForm(settingsController, notificationController);
    }

    @Provides
    EpisodeForm episodeForm(SettingsController settingsController, NotificationController notificationController) {
        return new EpisodeForm(settingsController, notificationController);
    }

    @Provides
    GameEngineForm gameEngineForm(SettingsController settingsController, NotificationController notificationController) {
        return new GameEngineForm(settingsController, notificationController);
    }

    @Provides
    GamePlatformReleaseForm gamePlatformReleaseForm(SettingsController settingsController, NotificationController notificationController) {
        return new GamePlatformReleaseForm(settingsController, notificationController);
    }

    @Provides
    GameSeriesForm gameSeriesForm(SettingsController settingsController, NotificationController notificationController) {
        return new GameSeriesForm(settingsController, notificationController);
    }

    @Provides
    ImageForm imageForm(SettingsController settingsController, NotificationController notificationController) {
        return new ImageForm(settingsController, notificationController);
    }

    @Provides
    LocationForm locationForm(SettingsController settingsController, NotificationController notificationController) {
        return new LocationForm(settingsController, notificationController);
    }

    @Provides
    LyricsForm lyricsForm(SettingsController settingsController, NotificationController notificationController) {
        return new LyricsForm(settingsController, notificationController);
    }

    @Provides
    PersonForm personForm(SettingsController settingsController, NotificationController notificationController) {
        return new PersonForm(settingsController, notificationController);
    }

    @Provides
    SeasonForm seasonForm(SettingsController settingsController, NotificationController notificationController) {
        return new SeasonForm(settingsController, notificationController);
    }

    @Provides
    SubtitleForm subtitleForm(SettingsController settingsController, NotificationController notificationController) {
        return new SubtitleForm(settingsController, notificationController);
    }

    @Provides
    VideoForm videoForm(SettingsController settingsController, NotificationController notificationController) {
        return new VideoForm(settingsController, notificationController);
    }

    @Provides
    SettingsPage settingsPage(NotificationController notificationController) {
        return new SettingsPage(notificationController);
    }

    @Provides
    UserSettingsPage userSettingsPage(SettingsController settingsController, NotificationController notificationController) {
        return new UserSettingsPage(settingsController, notificationController);
    }

    @Provides
    tech.tresearchgroup.colobus.view.IndexPage colobusIndexPage(SettingsController settingsController) {
        return new tech.tresearchgroup.colobus.view.IndexPage(settingsController);
    }
}
