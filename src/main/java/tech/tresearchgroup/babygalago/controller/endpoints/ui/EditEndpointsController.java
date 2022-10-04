package tech.tresearchgroup.babygalago.controller.endpoints.ui;

import com.google.gson.Gson;
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
import tech.tresearchgroup.palila.controller.BasicController;
import tech.tresearchgroup.palila.controller.CompressionController;
import tech.tresearchgroup.palila.controller.GenericController;
import tech.tresearchgroup.schemas.galago.entities.*;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

@AllArgsConstructor
public class EditEndpointsController extends BasicController {
    private final BookController bookController;
    private final BookForm bookForm;
    private final GameController gameController;
    private final GameForm gameForm;
    private final MovieController movieController;
    private final MovieForm movieForm;
    private final SongController songController;
    private final MusicForm musicForm;
    private final TvShowController tvShowController;
    private final TvShowForm tvShowForm;
    private final AlbumController albumController;
    private final AlbumForm albumForm;
    private final ArtistController artistController;
    private final ArtistForm artistForm;
    private final GenericController characterController;
    private final CharacterForm characterForm;
    private final CompanyController companyController;
    private final CompanyForm companyForm;
    private final EpisodeController episodeController;
    private final EpisodeForm episodeForm;
    private final GameEngineController gameEngineController;
    private final GameEngineForm gameEngineForm;
    private final GamePlatformReleaseController gamePlatformReleaseController;
    private final GamePlatformReleaseForm gamePlatformReleaseForm;
    private final GameSeriesController gameSeriesController;
    private final GameSeriesForm gameSeriesForm;
    private final LocationController locationController;
    private final LocationForm locationForm;
    private final PersonController personController;
    private final PersonForm personForm;
    private final SeasonController seasonController;
    private final SeasonForm seasonForm;
    private final UserController userController;
    private final SettingsController settingsController;
    private final Gson gson;

    public HttpResponse editBook(HttpRequest httpRequest) throws IOException, SQLException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        ExtendedUserEntity userEntity = (ExtendedUserEntity) getUser(httpRequest, userController);
        long id = Long.parseLong(httpRequest.getPathParameter("id"));
        if (!settingsController.isBookLibraryEnable()) {
            return redirect("/disabled");
        }
        BookEntity bookEntity = (BookEntity) bookController.readSecureResponse(id, httpRequest);
        boolean loggedIn = verifyApiKey(httpRequest);
        byte[] data = bookForm.render(true, loggedIn, settingsController.getCardWidth(userEntity.getUserSettings()), "/edit/book/" + id, bookEntity, userEntity, null, null, null);
        byte[] compressed = CompressionController.compress(data);
        return okResponseCompressed(compressed);
    }

    public HttpResponse editGame(HttpRequest httpRequest) throws IOException, SQLException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        ExtendedUserEntity userEntity = (ExtendedUserEntity) getUser(httpRequest, userController);
        long id = Long.parseLong(httpRequest.getPathParameter("id"));
        if (!settingsController.isGameLibraryEnable()) {
            return redirect("/disabled");
        }
        GameEntity gameEntity = (GameEntity) gameController.readSecureResponse(id, httpRequest);
        boolean loggedIn = verifyApiKey(httpRequest);
        byte[] data = gameForm.render(true, loggedIn, settingsController.getCardWidth(userEntity.getUserSettings()), "/edit/game/" + id, gameEntity, userEntity, null, null, null, null, null, null, null, null, null, null, null, null);
        byte[] compressed = CompressionController.compress(data);
        return okResponseCompressed(compressed);
    }

    public HttpResponse editMovie(HttpRequest httpRequest) throws IOException, SQLException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException, ExecutionControl.NotImplementedException {
        ExtendedUserEntity userEntity = (ExtendedUserEntity) getUser(httpRequest, userController);
        long id = Long.parseLong(httpRequest.getPathParameter("id"));
        if (!settingsController.isMovieLibraryEnable()) {
            return redirect("/disabled");
        }
        MovieEntity movieEntity = (MovieEntity) movieController.readSecureResponse(id, httpRequest);
        boolean loggedIn = verifyApiKey(httpRequest);
        byte[] data = movieForm.render(true, loggedIn, "/edit/movie/" + id, movieEntity, userEntity);
        byte[] compressed = CompressionController.compress(data);
        return okResponseCompressed(compressed);
    }

    public Promisable<HttpResponse> postEditMovie(HttpRequest httpRequest) {
        int id = Integer.parseInt(httpRequest.getPathParameter("id"));
        if (!settingsController.isMovieLibraryEnable()) {
            return redirect("/disabled");
        }
        /*
        MovieEntity movieEntity = MovieForm.getFromForm(httpRequest);
        if (movieController.update(movieEntity.getId(), movieEntity, httpRequest)) {
            return redirect("/view/movie/" + id);
        }*/
        return error();
    }

    public HttpResponse editMusic(HttpRequest httpRequest) throws IOException, SQLException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        ExtendedUserEntity userEntity = (ExtendedUserEntity) getUser(httpRequest, userController);
        long id = Long.parseLong(httpRequest.getPathParameter("id"));
        if (!settingsController.isMusicLibraryEnable()) {
            return redirect("/disabled");
        }
        SongEntity songEntity = (SongEntity) songController.readSecureResponse(id, httpRequest);
        boolean loggedIn = verifyApiKey(httpRequest);
        byte[] data = musicForm.render(true, loggedIn, settingsController.getCardWidth(userEntity.getUserSettings()), "/edit/music/" + id, songEntity, userEntity, null, null, null);
        byte[] compressed = CompressionController.compress(data);
        return okResponseCompressed(compressed);
    }

    public HttpResponse editTvShow(HttpRequest httpRequest) throws IOException, SQLException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        ExtendedUserEntity userEntity = (ExtendedUserEntity) getUser(httpRequest, userController);
        long id = Long.parseLong(httpRequest.getPathParameter("id"));
        if (!settingsController.isTvShowLibraryEnable()) {
            return redirect("/disabled");
        }
        TvShowEntity tvShowEntity = (TvShowEntity) tvShowController.readSecureResponse(id, httpRequest);
        boolean loggedIn = verifyApiKey(httpRequest);
        byte[] data = tvShowForm.render(true, loggedIn, settingsController.getCardWidth(userEntity.getUserSettings()), "/edit/tvshow/" + id, tvShowEntity, userEntity, null, null, null, null, null, null);
        byte[] compressed = CompressionController.compress(data);
        return okResponseCompressed(compressed);
    }

    public Promisable<HttpResponse> editAlbum(HttpRequest httpRequest) throws IOException, SQLException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        ExtendedUserEntity userEntity = (ExtendedUserEntity) getUser(httpRequest, userController);
        long id = Long.parseLong(httpRequest.getPathParameter("id"));
        if (!settingsController.isMusicLibraryEnable()) {
            return redirect("/disabled");
        }
        AlbumEntity albumEntity = (AlbumEntity) albumController.readSecureResponse(id, httpRequest);
        boolean loggedIn = verifyApiKey(httpRequest);
        byte[] data = albumForm.render(true, loggedIn, settingsController.getCardWidth(userEntity.getUserSettings()), "/edit/album/" + id, albumEntity, userEntity, null, null, null, null);
        byte[] compressed = CompressionController.compress(data);
        return okResponseCompressed(compressed);
    }

    public Promisable<HttpResponse> editArtist(HttpRequest httpRequest) throws IOException, SQLException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        ExtendedUserEntity userEntity = (ExtendedUserEntity) getUser(httpRequest, userController);
        long id = Long.parseLong(httpRequest.getPathParameter("id"));
        if (!settingsController.isMusicLibraryEnable()) {
            return redirect("/disabled");
        }
        ArtistEntity artistEntity = (ArtistEntity) artistController.readSecureResponse(id, httpRequest);
        boolean loggedIn = verifyApiKey(httpRequest);
        byte[] data = artistForm.render(true, loggedIn, settingsController.getCardWidth(userEntity.getUserSettings()), "/edit/artist/" + id, artistEntity, userEntity, null, null, null, null);
        byte[] compressed = CompressionController.compress(data);
        return okResponseCompressed(compressed);
    }

    public Promisable<HttpResponse> editCharacter(HttpRequest httpRequest) throws IOException, SQLException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        ExtendedUserEntity userEntity = (ExtendedUserEntity) getUser(httpRequest, userController);
        long id = Long.parseLong(httpRequest.getPathParameter("id"));
        CharacterEntity characterEntity = (CharacterEntity) characterController.readSecureResponse(id, httpRequest);
        boolean loggedIn = verifyApiKey(httpRequest);
        byte[] data = characterForm.render(true, loggedIn, "/edit/character/" + id, characterEntity, userEntity, null);
        byte[] compressed = CompressionController.compress(data);
        return okResponseCompressed(compressed);
    }

    public Promisable<HttpResponse> editCompany(HttpRequest httpRequest) throws IOException, SQLException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        long id = Long.parseLong(httpRequest.getPathParameter("id"));
        CompanyEntity companyEntity = (CompanyEntity) companyController.readSecureResponse(id, httpRequest);
        boolean loggedIn = verifyApiKey(httpRequest);
        ExtendedUserEntity userEntity = (ExtendedUserEntity) getUser(httpRequest, userController);
        byte[] data = companyForm.render(true, loggedIn, "/edit/company/" + id, companyEntity, userEntity);
        byte[] compressed = CompressionController.compress(data);
        return okResponseCompressed(compressed);
    }

    public Promisable<HttpResponse> editEpisode(HttpRequest httpRequest) throws IOException, SQLException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        long id = Long.parseLong(httpRequest.getPathParameter("id"));
        EpisodeEntity episodeEntity = (EpisodeEntity) episodeController.readSecureResponse(id, httpRequest);
        boolean loggedIn = verifyApiKey(httpRequest);
        ExtendedUserEntity userEntity = (ExtendedUserEntity) getUser(httpRequest, userController);
        byte[] data = episodeForm.render(true, loggedIn, settingsController.getCardWidth(userEntity.getUserSettings()), "/edit/episode/" + id, episodeEntity, userEntity, null, null, null, null);
        byte[] compressed = CompressionController.compress(data);
        return okResponseCompressed(compressed);
    }

    public Promisable<HttpResponse> editGameEngine(HttpRequest httpRequest) throws IOException, SQLException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        long id = Long.parseLong(httpRequest.getPathParameter("id"));
        GameEngineEntity gameEngineEntity = (GameEngineEntity) gameEngineController.readSecureResponse(id, httpRequest);
        boolean loggedIn = verifyApiKey(httpRequest);
        ExtendedUserEntity userEntity = (ExtendedUserEntity) getUser(httpRequest, userController);
        byte[] data = gameEngineForm.render(true, loggedIn, "/edit/gameengine/" + id, gameEngineEntity, userEntity);
        byte[] compressed = CompressionController.compress(data);
        return okResponseCompressed(compressed);
    }

    public Promisable<HttpResponse> editGamePlatformRelease(HttpRequest httpRequest) throws IOException, SQLException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        long id = Long.parseLong(httpRequest.getPathParameter("id"));
        GamePlatformReleaseEntity gamePlatformReleaseEntity = (GamePlatformReleaseEntity) gamePlatformReleaseController.readSecureResponse(id, httpRequest);
        boolean loggedIn = verifyApiKey(httpRequest);
        ExtendedUserEntity userEntity = (ExtendedUserEntity) getUser(httpRequest, userController);
        byte[] data = gamePlatformReleaseForm.render(true, loggedIn, "/edit/gameplatformrelease/" + id, gamePlatformReleaseEntity, userEntity);
        byte[] compressed = CompressionController.compress(data);
        return okResponseCompressed(compressed);
    }

    public Promisable<HttpResponse> editGameSeries(HttpRequest httpRequest) throws IOException, SQLException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        long id = Long.parseLong(httpRequest.getPathParameter("id"));
        GameSeriesEntity gameSeriesEntity = (GameSeriesEntity) gameSeriesController.readSecureResponse(id, httpRequest);
        boolean loggedIn = verifyApiKey(httpRequest);
        ExtendedUserEntity userEntity = (ExtendedUserEntity) getUser(httpRequest, userController);
        byte[] data = gameSeriesForm.render(true, loggedIn, "/edit/gameseries/" + id, gameSeriesEntity, userEntity);
        byte[] compressed = CompressionController.compress(data);
        return okResponseCompressed(compressed);
    }

    public Promisable<HttpResponse> editLocation(HttpRequest httpRequest) throws IOException, SQLException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        long id = Long.parseLong(httpRequest.getPathParameter("id"));
        LocationEntity locationEntity = (LocationEntity) locationController.readSecureResponse(id, httpRequest);
        boolean loggedIn = verifyApiKey(httpRequest);
        ExtendedUserEntity userEntity = (ExtendedUserEntity) getUser(httpRequest, userController);
        byte[] data = locationForm.render(true, loggedIn, "/edit/location/" + id, locationEntity, userEntity);
        byte[] compressed = CompressionController.compress(data);
        return okResponseCompressed(compressed);
    }

    public Promisable<HttpResponse> editPerson(HttpRequest httpRequest) throws IOException, SQLException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        long id = Long.parseLong(httpRequest.getPathParameter("id"));
        PersonEntity personEntity = (PersonEntity) personController.readSecureResponse(id, httpRequest);
        boolean loggedIn = verifyApiKey(httpRequest);
        ExtendedUserEntity userEntity = (ExtendedUserEntity) getUser(httpRequest, userController);
        byte[] data = personForm.render(true, loggedIn, "/edit/season/" + id, personEntity, userEntity, null);
        byte[] compressed = CompressionController.compress(data);
        return okResponseCompressed(compressed);
    }

    public Promisable<HttpResponse> editSeason(HttpRequest httpRequest) throws IOException, SQLException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        long id = Long.parseLong(httpRequest.getPathParameter("id"));
        SeasonEntity seasonEntity = (SeasonEntity) seasonController.readSecureResponse(id, httpRequest);
        boolean loggedIn = verifyApiKey(httpRequest);
        ExtendedUserEntity userEntity = (ExtendedUserEntity) getUser(httpRequest, userController);
        byte[] data = seasonForm.render(true, loggedIn, "/edit/season/" + id, seasonEntity, userEntity);
        byte[] compressed = CompressionController.compress(data);
        return okResponseCompressed(compressed);
    }
}
