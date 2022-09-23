package tech.tresearchgroup.babygalago.controller.endpoints.ui;

import io.activej.http.HttpRequest;
import io.activej.http.HttpResponse;
import lombok.AllArgsConstructor;
import tech.tresearchgroup.babygalago.controller.CardConverter;
import tech.tresearchgroup.babygalago.controller.SettingsController;
import tech.tresearchgroup.babygalago.controller.controllers.*;
import tech.tresearchgroup.babygalago.model.ExtendedUserEntity;
import tech.tresearchgroup.babygalago.view.pages.ViewPage;
import tech.tresearchgroup.palila.controller.BasicController;
import tech.tresearchgroup.palila.controller.CompressionController;
import tech.tresearchgroup.palila.model.enums.PermissionGroupEnum;
import tech.tresearchgroup.schemas.galago.entities.*;
import tech.tresearchgroup.schemas.galago.ui.Card;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
public class NewEndpointsController extends BasicController {
    private final BookController bookController;
    private final GameController gameController;
    private final MovieController movieController;
    private final SongController songController;
    private final TvShowController tvShowController;
    private final UserController userController;
    private final SettingsController settingsController;
    private final ViewPage viewPage;

    public HttpResponse newBook(HttpRequest httpRequest) throws IOException, SQLException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        if (canAccess(httpRequest, PermissionGroupEnum.USER, userController)) {
            if (!settingsController.isBookLibraryEnable()) {
                return redirect("/disabled");
            }
            ExtendedUserEntity userEntity = (ExtendedUserEntity) getUser(httpRequest, userController);
            UserSettingsEntity userSettingsEntity = userEntity.getUserSettings();
            int page = httpRequest.getQueryParameter("page") != null ? Integer.parseInt(Objects.requireNonNull(httpRequest.getQueryParameter("page"))) : 0;
            int maxResults = settingsController.getMaxBrowseResults(userSettingsEntity);
            long maxPage = bookController.getTotalPages(settingsController.getMaxBrowseResults(userSettingsEntity), httpRequest);
            List<BookEntity> books = bookController.readNewestPaginated(maxResults, page, httpRequest);
            LinkedList<Card> cards = new LinkedList<>();
            if (books != null) {
                cards.addAll(CardConverter.convertBooks(books, "browse"));
            }
            boolean loggedIn = verifyApiKey(httpRequest);
            byte[] data = viewPage.render(loggedIn, "New books:", "book", "new", cards, settingsController.getCardWidth(userSettingsEntity), page, maxPage, userEntity);
            byte[] compressed = CompressionController.compress(data);
            return okResponseCompressed(compressed);
        } else {
            return redirect("/login");
        }
    }

    public HttpResponse newGame(HttpRequest httpRequest) throws IOException, SQLException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        if (canAccess(httpRequest, PermissionGroupEnum.USER, userController)) {
            if (!settingsController.isGameLibraryEnable()) {
                return redirect("/disabled");
            }
            ExtendedUserEntity userEntity = (ExtendedUserEntity) getUser(httpRequest, userController);
            UserSettingsEntity userSettingsEntity = userEntity.getUserSettings();
            int page = httpRequest.getQueryParameter("page") != null ? Integer.parseInt(Objects.requireNonNull(httpRequest.getQueryParameter("page"))) : 0;
            int maxResults = settingsController.getMaxBrowseResults(userSettingsEntity);
            long maxPage = gameController.getTotalPages(settingsController.getMaxBrowseResults(userSettingsEntity), httpRequest);
            List<GameEntity> games = gameController.readNewestPaginated(maxResults, page, httpRequest);
            LinkedList<Card> cards = new LinkedList<>();
            if (games != null) {
                cards.addAll(CardConverter.convertGames(games, "browse"));
            }
            boolean loggedIn = verifyApiKey(httpRequest);
            byte[] data = viewPage.render(loggedIn, "New games:", "game", "new", cards, settingsController.getCardWidth(userSettingsEntity), page, maxPage, userEntity);
            byte[] compressed = CompressionController.compress(data);
            return okResponseCompressed(compressed);
        } else {
            return redirect("/login");
        }
    }

    public HttpResponse newMovie(HttpRequest httpRequest) throws IOException, SQLException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        if (canAccess(httpRequest, PermissionGroupEnum.USER, userController)) {
            if (!settingsController.isMovieLibraryEnable()) {
                return redirect("/disabled");
            }
            ExtendedUserEntity userEntity = (ExtendedUserEntity) getUser(httpRequest, userController);
            UserSettingsEntity userSettingsEntity = userEntity.getUserSettings();
            int page = httpRequest.getQueryParameter("page") != null ? Integer.parseInt(Objects.requireNonNull(httpRequest.getQueryParameter("page"))) : 0;
            int maxResults = settingsController.getMaxBrowseResults(userSettingsEntity);
            long maxPage = movieController.getTotalPages(settingsController.getMaxBrowseResults(userSettingsEntity), httpRequest);
            List<MovieEntity> movies = movieController.readNewestPaginated(maxResults, page, httpRequest);
            LinkedList<Card> cards = new LinkedList<>();
            if (movies != null) {
                cards.addAll(CardConverter.convertMovies(movies, "browse"));
            }
            boolean loggedIn = verifyApiKey(httpRequest);
            byte[] data = viewPage.render(loggedIn, "New movies:", "movie", "new", cards, settingsController.getCardWidth(userSettingsEntity), page, maxPage, userEntity);
            byte[] compressed = CompressionController.compress(data);
            return okResponseCompressed(compressed);
        } else {
            return redirect("/login");
        }
    }

    public HttpResponse newMusic(HttpRequest httpRequest) throws IOException, SQLException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        if (canAccess(httpRequest, PermissionGroupEnum.USER, userController)) {
            if (!settingsController.isMusicLibraryEnable()) {
                return redirect("/disabled");
            }
            ExtendedUserEntity userEntity = (ExtendedUserEntity) getUser(httpRequest, userController);
            UserSettingsEntity userSettingsEntity = userEntity.getUserSettings();
            int page = httpRequest.getQueryParameter("page") != null ? Integer.parseInt(Objects.requireNonNull(httpRequest.getQueryParameter("page"))) : 0;
            int maxResults = settingsController.getMaxBrowseResults(userSettingsEntity);
            long maxPage = songController.getTotalPages(settingsController.getMaxBrowseResults(userSettingsEntity), httpRequest);
            List<SongEntity> songs = songController.readNewestPaginated(maxResults, page, httpRequest);
            LinkedList<Card> cards = new LinkedList<>();
            if (songs != null) {
                cards.addAll(CardConverter.convertSongs(songs, "browse"));
            }
            boolean loggedIn = verifyApiKey(httpRequest);
            byte[] data = viewPage.render(loggedIn, "New music:", "music", "new", cards, settingsController.getCardWidth(userSettingsEntity), page, maxPage, userEntity);
            byte[] compressed = CompressionController.compress(data);
            return okResponseCompressed(compressed);
        } else {
            return redirect("/login");
        }
    }

    public HttpResponse newTvShow(HttpRequest httpRequest) throws IOException, SQLException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        if (canAccess(httpRequest, PermissionGroupEnum.USER, userController)) {
            if (!settingsController.isTvShowLibraryEnable()) {
                return redirect("/login");
            }
            ExtendedUserEntity userEntity = (ExtendedUserEntity) getUser(httpRequest, userController);
            UserSettingsEntity userSettingsEntity = userEntity.getUserSettings();
            int page = httpRequest.getQueryParameter("page") != null ? Integer.parseInt(Objects.requireNonNull(httpRequest.getQueryParameter("page"))) : 0;
            int maxResults = settingsController.getMaxBrowseResults(userSettingsEntity);
            long maxPage = tvShowController.getTotalPages(settingsController.getMaxBrowseResults(userSettingsEntity), httpRequest);
            List<TvShowEntity> tvShows = tvShowController.readNewestPaginated(maxResults, page, httpRequest);
            LinkedList<Card> cards = new LinkedList<>();
            if (tvShows != null) {
                cards.addAll(CardConverter.convertTvShows(tvShows, "browse"));
            }
            boolean loggedIn = verifyApiKey(httpRequest);
            byte[] data = viewPage.render(loggedIn, "New tv shows:", "tvshow", "new", cards, settingsController.getCardWidth(userSettingsEntity), page, maxPage, userEntity);
            byte[] compressed = CompressionController.compress(data);
            return okResponseCompressed(compressed);
        } else {
            return redirect("/login");
        }
    }
}
