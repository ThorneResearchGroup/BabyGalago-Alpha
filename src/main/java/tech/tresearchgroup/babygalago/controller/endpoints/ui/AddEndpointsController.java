package tech.tresearchgroup.babygalago.controller.endpoints.ui;

import io.activej.http.HttpRequest;
import io.activej.http.HttpResponse;
import io.activej.promise.Promisable;
import jdk.jshell.spi.ExecutionControl;
import lombok.AllArgsConstructor;
import tech.tresearchgroup.babygalago.controller.SettingsController;
import tech.tresearchgroup.babygalago.controller.controllers.UserController;
import tech.tresearchgroup.babygalago.model.ExtendedUserEntity;
import tech.tresearchgroup.babygalago.view.components.forms.*;
import tech.tresearchgroup.babygalago.view.components.forms.subTypes.*;
import tech.tresearchgroup.babygalago.view.pages.DisabledPage;
import tech.tresearchgroup.palila.controller.BasicController;
import tech.tresearchgroup.palila.controller.CompressionController;
import tech.tresearchgroup.palila.controller.cache.StaticCAO;
import tech.tresearchgroup.schemas.galago.entities.UserSettingsEntity;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

@AllArgsConstructor
public class AddEndpointsController extends BasicController {
    private final UserController userController;
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
    private final LyricsForm lyricsForm;
    private final PersonForm personForm;
    private final SeasonForm seasonForm;
    private final SubtitleForm subtitleForm;
    private final VideoForm videoForm;

    public Promisable<HttpResponse> addBook(HttpRequest httpRequest) throws IOException, SQLException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        ExtendedUserEntity userEntity = (ExtendedUserEntity) getUser(httpRequest, userController);
        UserSettingsEntity userSettingsEntity = userEntity.getUserSettings();
        if (!settingsController.isBookLibraryEnable()) {
            return redirect("/disabled");
        }
        byte[] cachedPage = StaticCAO.read("/add/book");
        if (cachedPage != null) {
            return okResponseCompressed(cachedPage);
        }
        boolean loggedIn = verifyApiKey(httpRequest);
        byte[] data = bookForm.render(true, loggedIn, settingsController.getCardWidth(userSettingsEntity), "/add/book", userEntity);
        byte[] compressed = CompressionController.compress(data);
        StaticCAO.create("/add/book", compressed);
        return okResponseCompressed(compressed);
    }

    public Promisable<HttpResponse> addGame(HttpRequest httpRequest) throws IOException, SQLException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        ExtendedUserEntity userEntity = (ExtendedUserEntity) getUser(httpRequest, userController);
        UserSettingsEntity userSettingsEntity = userEntity.getUserSettings();
        if (!settingsController.isGameLibraryEnable()) {
            return redirect("/disabled");
        }
        byte[] cachedPage = StaticCAO.read("/add/game");
        if (cachedPage != null) {
            return okResponseCompressed(cachedPage);
        }
        boolean loggedIn = verifyApiKey(httpRequest);
        byte[] data = gameForm.render(true, loggedIn, settingsController.getCardWidth(userSettingsEntity), "/add/game", userEntity);
        byte[] compressed = CompressionController.compress(data);
        StaticCAO.create("/add/game", compressed);
        return okResponseCompressed(compressed);
    }

    public Promisable<HttpResponse> addMovie(HttpRequest httpRequest) throws IOException, SQLException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException, ExecutionControl.NotImplementedException {
        ExtendedUserEntity userEntity = (ExtendedUserEntity) getUser(httpRequest, userController);
        UserSettingsEntity userSettingsEntity = userEntity.getUserSettings();
        boolean loggedIn = verifyApiKey(httpRequest);
        if (!settingsController.isMovieLibraryEnable()) {
            return ok(disabledPage.render(loggedIn, userEntity));
        }
        byte[] cachedPage = StaticCAO.read("/add/movie");
        if (cachedPage != null) {
            return okResponseCompressed(cachedPage);
        }
        byte[] data = movieForm.render(true, loggedIn, "/add/movie", userEntity);
        byte[] compressed = CompressionController.compress(data);
        StaticCAO.create("/add/movie", compressed);
        return okResponseCompressed(compressed);
    }

    public Promisable<HttpResponse> addMusic(HttpRequest httpRequest) throws IOException, SQLException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        ExtendedUserEntity userEntity = (ExtendedUserEntity) getUser(httpRequest, userController);
        UserSettingsEntity userSettingsEntity = userEntity.getUserSettings();
        boolean loggedIn = verifyApiKey(httpRequest);
        if (!settingsController.isMusicLibraryEnable()) {
            return ok(disabledPage.render(loggedIn, userEntity));
        }
        byte[] cachedPage = StaticCAO.read("/add/music");
        if (cachedPage != null) {
            return okResponseCompressed(cachedPage);
        }
        byte[] data = musicForm.render(true, loggedIn, settingsController.getCardWidth(userSettingsEntity), "/add/music", userEntity);
        byte[] compressed = CompressionController.compress(data);
        StaticCAO.create("/add/music", compressed);
        return okResponseCompressed(compressed);
    }

    public Promisable<HttpResponse> addTvShow(HttpRequest httpRequest) throws IOException, SQLException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        ExtendedUserEntity userEntity = (ExtendedUserEntity) getUser(httpRequest, userController);
        UserSettingsEntity userSettingsEntity = userEntity.getUserSettings();
        boolean loggedIn = verifyApiKey(httpRequest);
        if (!settingsController.isTvShowLibraryEnable()) {
            return ok(disabledPage.render(loggedIn, userEntity));
        }
        byte[] cachedPage = StaticCAO.read("/add/tvshow");
        if (cachedPage != null) {
            return okResponseCompressed(cachedPage);
        }
        byte[] data = tvShowForm.render(true, loggedIn, settingsController.getCardWidth(userSettingsEntity), "/add/tvshow", userEntity);
        byte[] compressed = CompressionController.compress(data);
        StaticCAO.create("/add/tvshow", compressed);
        return okResponseCompressed(compressed);
    }

    public Promisable<HttpResponse> addAlbum(HttpRequest httpRequest) throws IOException, SQLException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        ExtendedUserEntity userEntity = (ExtendedUserEntity) getUser(httpRequest, userController);
        byte[] cachedPage = StaticCAO.read("/add/album");
        if (cachedPage != null) {
            return okResponseCompressed(cachedPage);
        }
        UserSettingsEntity userSettingsEntity = userEntity.getUserSettings();
        boolean loggedIn = verifyApiKey(httpRequest);
        byte[] data = albumForm.render(true, loggedIn, settingsController.getCardWidth(userSettingsEntity), "/add/album", userEntity);
        byte[] compressed = CompressionController.compress(data);
        StaticCAO.create("/add/album", compressed);
        return okResponseCompressed(compressed);
    }

    public Promisable<HttpResponse> addArtist(HttpRequest httpRequest) throws IOException, SQLException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        ExtendedUserEntity userEntity = (ExtendedUserEntity) getUser(httpRequest, userController);
        byte[] cachedPage = StaticCAO.read("/add/artist");
        if (cachedPage != null) {
            return okResponseCompressed(cachedPage);
        }
        UserSettingsEntity userSettingsEntity = userEntity.getUserSettings();
        boolean loggedIn = verifyApiKey(httpRequest);
        byte[] data = artistForm.render(true, loggedIn, settingsController.getCardWidth(userSettingsEntity), "/add/artist", userEntity);
        byte[] compressed = CompressionController.compress(data);
        StaticCAO.create("/add/artist", compressed);
        return okResponseCompressed(compressed);
    }

    public Promisable<HttpResponse> addCharacter(HttpRequest httpRequest) throws IOException, SQLException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        ExtendedUserEntity userEntity = (ExtendedUserEntity) getUser(httpRequest, userController);
        byte[] cachedPage = StaticCAO.read("/add/character");
        if (cachedPage != null) {
            return okResponseCompressed(cachedPage);
        }
        boolean loggedIn = verifyApiKey(httpRequest);
        byte[] data = characterForm.render(true, loggedIn, "/add/character", userEntity);
        byte[] compressed = CompressionController.compress(data);
        StaticCAO.create("/add/character", compressed);
        return okResponseCompressed(compressed);
    }

    public Promisable<HttpResponse> addCompany(HttpRequest httpRequest) throws IOException, SQLException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        ExtendedUserEntity userEntity = (ExtendedUserEntity) getUser(httpRequest, userController);
        byte[] cachedPage = StaticCAO.read("/add/company");
        if (cachedPage != null) {
            return okResponseCompressed(cachedPage);
        }
        boolean loggedIn = verifyApiKey(httpRequest);
        byte[] data = companyForm.render(true, loggedIn, "/add/company", userEntity);
        byte[] compressed = CompressionController.compress(data);
        StaticCAO.create("/add/company", compressed);
        return okResponseCompressed(compressed);
    }

    public Promisable<HttpResponse> addEpisode(HttpRequest httpRequest) throws IOException, SQLException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        ExtendedUserEntity userEntity = (ExtendedUserEntity) getUser(httpRequest, userController);
        byte[] cachedPage = StaticCAO.read("/add/episode");
        if (cachedPage != null) {
            return okResponseCompressed(cachedPage);
        }
        UserSettingsEntity userSettingsEntity = userEntity.getUserSettings();
        boolean loggedIn = verifyApiKey(httpRequest);
        byte[] data = episodeForm.render(true, loggedIn, settingsController.getCardWidth(userSettingsEntity), "/add/episode", userEntity);
        byte[] compressed = CompressionController.compress(data);
        StaticCAO.create("/add/episode", compressed);
        return okResponseCompressed(compressed);
    }

    public Promisable<HttpResponse> addGameEngine(HttpRequest httpRequest) throws IOException, SQLException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        ExtendedUserEntity userEntity = (ExtendedUserEntity) getUser(httpRequest, userController);
        byte[] cachedPage = StaticCAO.read("/add/gameengine");
        if (cachedPage != null) {
            return okResponseCompressed(cachedPage);
        }
        boolean loggedIn = verifyApiKey(httpRequest);
        byte[] data = gameEngineForm.render(true, loggedIn, "/add/gameengine", userEntity);
        byte[] compressed = CompressionController.compress(data);
        StaticCAO.create("/add/gameengine", compressed);
        return okResponseCompressed(compressed);
    }

    public Promisable<HttpResponse> addGamePlatformRelease(HttpRequest httpRequest) throws IOException, SQLException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        ExtendedUserEntity userEntity = (ExtendedUserEntity) getUser(httpRequest, userController);
        byte[] cachedPage = StaticCAO.read("/add/gameplatformrelease");
        if (cachedPage != null) {
            return okResponseCompressed(cachedPage);
        }
        boolean loggedIn = verifyApiKey(httpRequest);
        byte[] data = gamePlatformReleaseForm.render(true, loggedIn, "/add/gameplatformrelease", userEntity);
        byte[] compressed = CompressionController.compress(data);
        StaticCAO.create("/add/gameplatformrelease", compressed);
        return okResponseCompressed(compressed);
    }

    public Promisable<HttpResponse> addGameSeries(HttpRequest httpRequest) throws IOException, SQLException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        ExtendedUserEntity userEntity = (ExtendedUserEntity) getUser(httpRequest, userController);
        byte[] cachedPage = StaticCAO.read("/add/gameseries");
        if (cachedPage != null) {
            return okResponseCompressed(cachedPage);
        }
        boolean loggedIn = verifyApiKey(httpRequest);
        byte[] data = gameSeriesForm.render(true, loggedIn, "/add/gameseries", userEntity);
        byte[] compressed = CompressionController.compress(data);
        StaticCAO.create("/add/gameseries", compressed);
        return okResponseCompressed(compressed);
    }

    public Promisable<HttpResponse> addImage(HttpRequest httpRequest) throws IOException, SQLException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        ExtendedUserEntity userEntity = (ExtendedUserEntity) getUser(httpRequest, userController);
        byte[] cachedPage = StaticCAO.read("/add/image");
        if (cachedPage != null) {
            return okResponseCompressed(cachedPage);
        }
        boolean loggedIn = verifyApiKey(httpRequest);
        byte[] data = imageForm.render(true, loggedIn, "/add/image", userEntity);
        byte[] compressed = CompressionController.compress(data);
        StaticCAO.create("/add/image", compressed);
        return okResponseCompressed(compressed);
    }

    public Promisable<HttpResponse> addLocation(HttpRequest httpRequest) throws IOException, SQLException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        ExtendedUserEntity userEntity = (ExtendedUserEntity) getUser(httpRequest, userController);
        byte[] cachedPage = StaticCAO.read("/add/location");
        if (cachedPage != null) {
            return okResponseCompressed(cachedPage);
        }
        boolean loggedIn = verifyApiKey(httpRequest);
        byte[] data = locationForm.render(true, loggedIn, "/add/location", userEntity);
        byte[] compressed = CompressionController.compress(data);
        StaticCAO.create("/add/location", compressed);
        return okResponseCompressed(compressed);
    }

    public Promisable<HttpResponse> addPerson(HttpRequest httpRequest) throws IOException, SQLException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        ExtendedUserEntity userEntity = (ExtendedUserEntity) getUser(httpRequest, userController);
        byte[] cachedPage = StaticCAO.read("/add/person");
        if (cachedPage != null) {
            return okResponseCompressed(cachedPage);
        }
        boolean loggedIn = verifyApiKey(httpRequest);
        byte[] data = personForm.render(true, loggedIn, "/add/person", userEntity);
        byte[] compressed = CompressionController.compress(data);
        StaticCAO.create("/add/person", compressed);
        return okResponseCompressed(compressed);
    }

    public Promisable<HttpResponse> addSeason(HttpRequest httpRequest) throws IOException, SQLException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        ExtendedUserEntity userEntity = (ExtendedUserEntity) getUser(httpRequest, userController);
        byte[] cachedPage = StaticCAO.read("/add/season");
        if (cachedPage != null) {
            return okResponseCompressed(cachedPage);
        }
        boolean loggedIn = verifyApiKey(httpRequest);
        byte[] data = seasonForm.render(true, loggedIn, "/add/season", userEntity);
        byte[] compressed = CompressionController.compress(data);
        StaticCAO.create("/add/season", compressed);
        return okResponseCompressed(compressed);
    }

    public Promisable<HttpResponse> addLyrics(HttpRequest httpRequest) throws IOException, SQLException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        ExtendedUserEntity userEntity = (ExtendedUserEntity) getUser(httpRequest, userController);
        byte[] cachedPage = StaticCAO.read("/add/lyrics");
        if (cachedPage != null) {
            return okResponseCompressed(cachedPage);
        }
        boolean loggedIn = verifyApiKey(httpRequest);
        byte[] data = lyricsForm.render(true, loggedIn, "/add/lyrics", userEntity);
        byte[] compressed = CompressionController.compress(data);
        StaticCAO.create("/add/lyrics", compressed);
        return okResponseCompressed(compressed);
    }

    public Promisable<HttpResponse> addSubtitle(HttpRequest httpRequest) throws IOException, SQLException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        ExtendedUserEntity userEntity = (ExtendedUserEntity) getUser(httpRequest, userController);
        byte[] cachedPage = StaticCAO.read("/add/subtitle");
        if (cachedPage != null) {
            return okResponseCompressed(cachedPage);
        }
        byte[] data = subtitleForm.render(true, "/add/subtitle", userEntity);
        byte[] compressed = CompressionController.compress(data);
        StaticCAO.create("/add/subtitle", compressed);
        return okResponseCompressed(compressed);
    }

    public Promisable<HttpResponse> addVideo(HttpRequest httpRequest) throws IOException, SQLException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        ExtendedUserEntity userEntity = (ExtendedUserEntity) getUser(httpRequest, userController);
        byte[] cachedPage = StaticCAO.read("/add/video");
        if (cachedPage != null) {
            return okResponseCompressed(cachedPage);
        }
        boolean loggedIn = verifyApiKey(httpRequest);
        byte[] data = videoForm.render(true, loggedIn, "/add/video", userEntity);
        byte[] compressed = CompressionController.compress(data);
        StaticCAO.create("/add/video", compressed);
        return okResponseCompressed(compressed);
    }
}
