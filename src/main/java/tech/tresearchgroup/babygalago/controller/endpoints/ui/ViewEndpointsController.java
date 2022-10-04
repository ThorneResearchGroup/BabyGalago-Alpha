package tech.tresearchgroup.babygalago.controller.endpoints.ui;

import io.activej.http.HttpRequest;
import io.activej.http.HttpResponse;
import io.activej.promise.Promisable;
import jdk.jshell.spi.ExecutionControl;
import lombok.AllArgsConstructor;
import tech.tresearchgroup.babygalago.controller.SettingsController;
import tech.tresearchgroup.babygalago.controller.controllers.*;
import tech.tresearchgroup.babygalago.model.ExtendedUserEntity;
import tech.tresearchgroup.babygalago.view.components.forms.*;
import tech.tresearchgroup.babygalago.view.components.forms.subTypes.*;
import tech.tresearchgroup.babygalago.view.pages.DisabledPage;
import tech.tresearchgroup.palila.controller.BasicController;
import tech.tresearchgroup.schemas.galago.entities.*;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

@AllArgsConstructor
public class ViewEndpointsController extends BasicController {
    private final BookController bookController;
    private final GameController gameController;
    private final MovieController movieController;
    private final SongController songController;
    private final TvShowController tvShowController;
    private final UserController userController;
    private final AlbumController albumController;
    private final ArtistController artistController;
    private final CharacterController characterController;
    private final CompanyController companyController;
    private final EpisodeController episodeController;
    private final GameEngineController gameEngineController;
    private final GamePlatformReleaseController gamePlatformReleaseController;
    private final GameSeriesController gameSeriesController;
    private final LocationController locationController;
    private final PersonController personController;
    private final SeasonController seasonController;
    private final ImageController imageController;
    private final SubtitleController subtitleController;
    private final VideoController videoController;
    private final SettingsController settingsController;
    private final DisabledPage disabledPage;
    private final BookForm bookForm;
    private final GameForm gameForm;
    private final MovieForm movieForm;
    private final MusicForm musicForm;
    private final TvShowForm tvShowForm;
    private final AlbumForm albumForm;
    private final ArtistForm artistForm;
    private final CharacterForm characterForm;
    private final CompanyForm companyForm;
    private final EpisodeForm episodeForm;
    private final GameEngineForm gameEngineForm;
    private final GamePlatformReleaseForm gamePlatformReleaseForm;
    private final GameSeriesForm gameSeriesForm;
    private final ImageForm imageForm;
    private final LocationForm locationForm;
    private final PersonForm personForm;
    private final SeasonForm seasonForm;
    private final SubtitleForm subtitleForm;
    private final VideoForm videoForm;

    public HttpResponse viewBook(HttpRequest httpRequest) throws IOException, SQLException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        long id = Long.parseLong(httpRequest.getPathParameter("id"));
        boolean loggedIn = verifyApiKey(httpRequest);
        UserSettingsEntity userSettingsEntity = null;
        ExtendedUserEntity userEntity = (ExtendedUserEntity) getUser(httpRequest, userController);
        if (userEntity != null) {
            userSettingsEntity = userEntity.getUserSettings();
        }
        if (!settingsController.isBookLibraryEnable()) {
            return ok(disabledPage.render(loggedIn, userEntity));
        }
        BookEntity bookEntity = (BookEntity) bookController.readSecureResponse(id, httpRequest);
        return ok(bookForm.render(false, loggedIn, settingsController.getCardWidth(userSettingsEntity), "#", bookEntity, userEntity, null, null, null));
    }

    public HttpResponse viewGame(HttpRequest httpRequest) throws IOException, SQLException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        long id = Long.parseLong(httpRequest.getPathParameter("id"));
        boolean loggedIn = verifyApiKey(httpRequest);
        UserSettingsEntity userSettingsEntity = null;
        ExtendedUserEntity userEntity = (ExtendedUserEntity) getUser(httpRequest, userController);
        if (userEntity != null) {
            userSettingsEntity = userEntity.getUserSettings();
        }
        if (!settingsController.isGameLibraryEnable()) {
            return ok(disabledPage.render(loggedIn, userEntity));
        }
        GameEntity gameEntity = (GameEntity) gameController.readSecureResponse(id, httpRequest);
        return ok(gameForm.render(false, loggedIn, settingsController.getCardWidth(userSettingsEntity), "#", gameEntity, userEntity, null, null, null, null, null, null, null, null, null, null, null, null));
    }

    public HttpResponse viewMovie(HttpRequest httpRequest) throws IOException, SQLException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException, ExecutionControl.NotImplementedException {
        long id = Long.parseLong(httpRequest.getPathParameter("id"));
        boolean loggedIn = verifyApiKey(httpRequest);
        UserSettingsEntity userSettingsEntity = null;
        ExtendedUserEntity userEntity = (ExtendedUserEntity) getUser(httpRequest, userController);
        if (userEntity != null) {
            userSettingsEntity = userEntity.getUserSettings();
        }
        if (!settingsController.isMovieLibraryEnable()) {
            return ok(disabledPage.render(loggedIn, userEntity));
        }
        MovieEntity movieEntity = (MovieEntity) movieController.readSecureResponse(id, httpRequest);
        if (movieEntity != null) {
            return ok(movieForm.render(false, loggedIn, "#", movieEntity, userEntity));
        }
        return ok(movieForm.render(false, loggedIn, "#", userEntity));
    }

    public HttpResponse viewMusic(HttpRequest httpRequest) throws IOException, SQLException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        long id = Long.parseLong(httpRequest.getPathParameter("id"));
        boolean loggedIn = verifyApiKey(httpRequest);
        UserSettingsEntity userSettingsEntity = null;
        ExtendedUserEntity userEntity = (ExtendedUserEntity) getUser(httpRequest, userController);
        if (userEntity != null) {
            userSettingsEntity = userEntity.getUserSettings();
        }
        if (!settingsController.isMusicLibraryEnable()) {
            return ok(disabledPage.render(loggedIn, userEntity));
        }
        SongEntity songEntity = (SongEntity) songController.readSecureResponse(id, httpRequest);
        return ok(musicForm.render(false, loggedIn, settingsController.getCardWidth(userSettingsEntity), "#", userEntity));
    }

    public HttpResponse viewTvShow(HttpRequest httpRequest) throws IOException, SQLException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        long id = Long.parseLong(httpRequest.getPathParameter("id"));
        boolean loggedIn = verifyApiKey(httpRequest);
        UserSettingsEntity userSettingsEntity = null;
        ExtendedUserEntity userEntity = (ExtendedUserEntity) getUser(httpRequest, userController);
        if (userEntity != null) {
            userSettingsEntity = userEntity.getUserSettings();
        }
        if (!settingsController.isTvShowLibraryEnable()) {
            return ok(disabledPage.render(loggedIn, userEntity));
        }
        TvShowEntity tvShowEntity = (TvShowEntity) tvShowController.readSecureResponse(id, httpRequest);
        return ok(tvShowForm.render(false, loggedIn, settingsController.getCardWidth(userSettingsEntity), "#", tvShowEntity, userEntity, null, null, null, null, null, null));
    }

    public Promisable<HttpResponse> viewAlbum(HttpRequest httpRequest) throws IOException, SQLException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        long id = Long.parseLong(httpRequest.getPathParameter("id"));
        UserSettingsEntity userSettingsEntity = null;
        ExtendedUserEntity userEntity = (ExtendedUserEntity) getUser(httpRequest, userController);
        if (userEntity != null) {
            userSettingsEntity = userEntity.getUserSettings();
        }
        AlbumEntity albumEntity = (AlbumEntity) albumController.readSecureResponse(id, httpRequest);
        boolean loggedIn = verifyApiKey(httpRequest);
        return ok(albumForm.render(false, loggedIn, settingsController.getCardWidth(userSettingsEntity), "#", albumEntity, userEntity, null, null, null, null));
    }

    public Promisable<HttpResponse> viewArtist(HttpRequest httpRequest) throws IOException, SQLException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        long id = Long.parseLong(httpRequest.getPathParameter("id"));
        UserSettingsEntity userSettingsEntity = null;
        ExtendedUserEntity userEntity = (ExtendedUserEntity) getUser(httpRequest, userController);
        if (userEntity != null) {
            userSettingsEntity = userEntity.getUserSettings();
        }
        ArtistEntity artistEntity = (ArtistEntity) artistController.readSecureResponse(id, httpRequest);
        boolean loggedIn = verifyApiKey(httpRequest);
        return ok(artistForm.render(false, loggedIn, settingsController.getCardWidth(userSettingsEntity), "#", artistEntity, userEntity, null, null, null, null));
    }

    public Promisable<HttpResponse> viewCharacter(HttpRequest httpRequest) throws IOException, SQLException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        long id = Long.parseLong(httpRequest.getPathParameter("id"));
        CharacterEntity characterEntity = (CharacterEntity) characterController.readSecureResponse(id, httpRequest);
        boolean loggedIn = verifyApiKey(httpRequest);
        ExtendedUserEntity userEntity = (ExtendedUserEntity) getUser(httpRequest, userController);
        return ok(characterForm.render(false, loggedIn, "#", characterEntity, userEntity, null));
    }

    public Promisable<HttpResponse> viewCompany(HttpRequest httpRequest) throws IOException, SQLException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        long id = Long.parseLong(httpRequest.getPathParameter("id"));
        CompanyEntity companyEntity = (CompanyEntity) companyController.readSecureResponse(id, httpRequest);
        boolean loggedIn = verifyApiKey(httpRequest);
        ExtendedUserEntity userEntity = (ExtendedUserEntity) getUser(httpRequest, userController);
        return ok(companyForm.render(false, loggedIn, "#", companyEntity, userEntity));
    }

    public Promisable<HttpResponse> viewEpisode(HttpRequest httpRequest) throws IOException, SQLException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        long id = Long.parseLong(httpRequest.getPathParameter("id"));
        UserSettingsEntity userSettingsEntity = null;
        ExtendedUserEntity userEntity = (ExtendedUserEntity) getUser(httpRequest, userController);
        if (userEntity != null) {
            userSettingsEntity = userEntity.getUserSettings();
        }
        EpisodeEntity episodeEntity = (EpisodeEntity) episodeController.readSecureResponse(id, httpRequest);
        boolean loggedIn = verifyApiKey(httpRequest);
        return ok(episodeForm.render(false, loggedIn, settingsController.getCardWidth(userSettingsEntity), "#", episodeEntity, userEntity, null, null, null, null));
    }

    public Promisable<HttpResponse> viewGameEngine(HttpRequest httpRequest) throws IOException, SQLException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        long id = Long.parseLong(httpRequest.getPathParameter("id"));
        GameEngineEntity gameEngineEntity = (GameEngineEntity) gameEngineController.readSecureResponse(id, httpRequest);
        boolean loggedIn = verifyApiKey(httpRequest);
        ExtendedUserEntity userEntity = (ExtendedUserEntity) getUser(httpRequest, userController);
        return ok(gameEngineForm.render(false, loggedIn, "#", gameEngineEntity, userEntity));
    }

    public Promisable<HttpResponse> viewGamePlatformRelease(HttpRequest httpRequest) throws IOException, SQLException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        long id = Long.parseLong(httpRequest.getPathParameter("id"));
        GamePlatformReleaseEntity gamePlatformReleaseEntity = (GamePlatformReleaseEntity) gamePlatformReleaseController.readSecureResponse(id, httpRequest);
        boolean loggedIn = verifyApiKey(httpRequest);
        ExtendedUserEntity userEntity = (ExtendedUserEntity) getUser(httpRequest, userController);
        return ok(gamePlatformReleaseForm.render(false, loggedIn, "#", gamePlatformReleaseEntity, userEntity));
    }

    public Promisable<HttpResponse> viewGameSeries(HttpRequest httpRequest) throws IOException, SQLException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        long id = Long.parseLong(httpRequest.getPathParameter("id"));
        GameSeriesEntity gameSeriesEntity = (GameSeriesEntity) gameSeriesController.readSecureResponse(id, httpRequest);
        boolean loggedIn = verifyApiKey(httpRequest);
        ExtendedUserEntity userEntity = (ExtendedUserEntity) getUser(httpRequest, userController);
        return ok(gameSeriesForm.render(false, loggedIn, "#", gameSeriesEntity, userEntity));
    }

    public Promisable<HttpResponse> viewImage(HttpRequest httpRequest) throws IOException, SQLException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        long id = Long.parseLong(httpRequest.getPathParameter("id"));
        ImageEntity imageEntity = (ImageEntity) imageController.readSecureResponse(id, httpRequest);
        boolean loggedIn = verifyApiKey(httpRequest);
        ExtendedUserEntity userEntity = (ExtendedUserEntity) getUser(httpRequest, userController);
        return ok(imageForm.render(false, loggedIn, "#", imageEntity, userEntity, null));
    }

    public Promisable<HttpResponse> viewLocation(HttpRequest httpRequest) throws IOException, SQLException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        long id = Long.parseLong(httpRequest.getPathParameter("id"));
        LocationEntity locationEntity = (LocationEntity) locationController.readSecureResponse(id, httpRequest);
        boolean loggedIn = verifyApiKey(httpRequest);
        ExtendedUserEntity userEntity = (ExtendedUserEntity) getUser(httpRequest, userController);
        return ok(locationForm.render(false, loggedIn, "#", locationEntity, userEntity));
    }

    public Promisable<HttpResponse> viewPerson(HttpRequest httpRequest) throws IOException, SQLException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        long id = Long.parseLong(httpRequest.getPathParameter("id"));
        PersonEntity personEntity = (PersonEntity) personController.readSecureResponse(id, httpRequest);
        boolean loggedIn = verifyApiKey(httpRequest);
        ExtendedUserEntity userEntity = (ExtendedUserEntity) getUser(httpRequest, userController);
        return ok(personForm.render(false, loggedIn, "#", personEntity, userEntity, null));
    }

    public Promisable<HttpResponse> viewSeason(HttpRequest httpRequest) throws IOException, SQLException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        long id = Long.parseLong(httpRequest.getPathParameter("id"));
        SeasonEntity seasonEntity = (SeasonEntity) seasonController.readSecureResponse(id, httpRequest);
        boolean loggedIn = verifyApiKey(httpRequest);
        ExtendedUserEntity userEntity = (ExtendedUserEntity) getUser(httpRequest, userController);
        return ok(seasonForm.render(false, loggedIn, "#", seasonEntity, userEntity));
    }

    public Promisable<HttpResponse> viewSubtitle(HttpRequest httpRequest) throws IOException, SQLException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        long id = Long.parseLong(httpRequest.getPathParameter("id"));
        SubtitleEntity subtitleEntity = (SubtitleEntity) subtitleController.readSecureResponse(id, httpRequest);
        ExtendedUserEntity userEntity = (ExtendedUserEntity) getUser(httpRequest, userController);
        return ok(subtitleForm.render(false, "#", subtitleEntity, userEntity));
    }

    public Promisable<HttpResponse> viewVideo(HttpRequest httpRequest) throws IOException, SQLException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        long id = Long.parseLong(httpRequest.getPathParameter("id"));
        VideoEntity videoEntity = (VideoEntity) videoController.readSecureResponse(id, httpRequest);
        boolean loggedIn = verifyApiKey(httpRequest);
        ExtendedUserEntity userEntity = (ExtendedUserEntity) getUser(httpRequest, userController);
        return ok(videoForm.render(false, loggedIn, "#", videoEntity, userEntity, null));
    }
}
