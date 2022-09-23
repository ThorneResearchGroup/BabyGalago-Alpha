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
public class PopularEndpointsController extends BasicController {
    private final BookController bookController;
    private final GameController gameController;
    private final MovieController movieController;
    private final SongController songController;
    private final TvShowController tvShowController;
    private final UserController userController;
    private final SettingsController settingsController;
    private final ViewPage viewPage;

    public HttpResponse popularBook(HttpRequest httpRequest) throws IOException, SQLException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        if (canAccess(httpRequest, PermissionGroupEnum.USER, userController)) {
            if (!settingsController.isBookLibraryEnable()) {
                return redirect("/disabled");
            }
            ExtendedUserEntity userEntity = (ExtendedUserEntity) getUser(httpRequest, userController);
            UserSettingsEntity userSettingsEntity = userEntity.getUserSettings();
            int page = httpRequest.getQueryParameter("page") != null ? Integer.parseInt(Objects.requireNonNull(httpRequest.getQueryParameter("page"))) : 0;
            int maxResults = settingsController.getMaxBrowseResults(userSettingsEntity);
            long maxPage = bookController.getTotalPages(userSettingsEntity.getMaxBrowseResults(), httpRequest);
            List<BookEntity> books = bookController.readPopularPaginated(maxResults, page, httpRequest);
            LinkedList<Card> cards = new LinkedList<>();
            if (books != null) {
                cards.addAll(CardConverter.convertBooks(books, "browse"));
            }
            boolean loggedIn = verifyApiKey(httpRequest);
            byte[] data = viewPage.render(loggedIn, "Popular books:", "book", "popular", cards, settingsController.getCardWidth(userSettingsEntity), page, maxPage, userEntity);
            byte[] compressed = CompressionController.compress(data);
            return okResponseCompressed(compressed);
        } else {
            return redirect("/login");
        }
    }

    public HttpResponse popularGame(HttpRequest httpRequest) throws IOException, SQLException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        if (canAccess(httpRequest, PermissionGroupEnum.USER, userController)) {
            if (!settingsController.isGameLibraryEnable()) {
                return redirect("/disabled");
            }
            ExtendedUserEntity userEntity = (ExtendedUserEntity) getUser(httpRequest, userController);
            UserSettingsEntity userSettingsEntity = userEntity.getUserSettings();
            int page = httpRequest.getQueryParameter("page") != null ? Integer.parseInt(Objects.requireNonNull(httpRequest.getQueryParameter("page"))) : 0;
            int maxResults = settingsController.getMaxBrowseResults(userSettingsEntity);
            long maxPage = gameController.getTotalPages(userSettingsEntity.getMaxBrowseResults(), httpRequest);
            List<GameEntity> games = gameController.readPopularPaginated(maxResults, page, httpRequest);
            LinkedList<Card> cards = new LinkedList<>();
            if (games != null) {
                cards.addAll(CardConverter.convertGames(games, "browse"));
            }
            boolean loggedIn = verifyApiKey(httpRequest);
            byte[] data = viewPage.render(loggedIn, "Popular games:", "game", "popular", cards, settingsController.getCardWidth(userSettingsEntity), page, maxPage, userEntity);
            byte[] compressed = CompressionController.compress(data);
            return okResponseCompressed(compressed);
        } else {
            return redirect("/login");
        }
    }

    public HttpResponse popularMovie(HttpRequest httpRequest) throws IOException, SQLException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        if (canAccess(httpRequest, PermissionGroupEnum.USER, userController)) {
            if (!settingsController.isMovieLibraryEnable()) {
                return redirect("/disabled");
            }
            ExtendedUserEntity userEntity = (ExtendedUserEntity) getUser(httpRequest, userController);
            UserSettingsEntity userSettingsEntity = userEntity.getUserSettings();
            int page = httpRequest.getQueryParameter("page") != null ? Integer.parseInt(Objects.requireNonNull(httpRequest.getQueryParameter("page"))) : 0;
            int maxResults = settingsController.getMaxBrowseResults(userSettingsEntity);
            long maxPage = movieController.getTotalPages(userSettingsEntity.getMaxBrowseResults(), httpRequest);
            List<MovieEntity> movies = movieController.readPopularPaginated(maxResults, page, httpRequest);
            LinkedList<Card> cards = new LinkedList<>();
            if (movies != null) {
                cards.addAll(CardConverter.convertMovies(movies, "browse"));
            }
            boolean loggedIn = verifyApiKey(httpRequest);
            byte[] data = viewPage.render(loggedIn, "Popular movies:", "movie", "popular", cards, settingsController.getCardWidth(userSettingsEntity), page, maxPage, userEntity);
            byte[] compressed = CompressionController.compress(data);
            return okResponseCompressed(compressed);
        } else {
            return redirect("/login");
        }
    }

    public HttpResponse popularMusic(HttpRequest httpRequest) throws IOException, SQLException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        if (canAccess(httpRequest, PermissionGroupEnum.USER, userController)) {
            if (!settingsController.isMusicLibraryEnable()) {
                return redirect("/disabled");
            }
            ExtendedUserEntity userEntity = (ExtendedUserEntity) getUser(httpRequest, userController);
            UserSettingsEntity userSettingsEntity = userEntity.getUserSettings();
            int page = httpRequest.getQueryParameter("page") != null ? Integer.parseInt(Objects.requireNonNull(httpRequest.getQueryParameter("page"))) : 0;
            int maxResults = settingsController.getMaxBrowseResults(userSettingsEntity);
            long maxPage = songController.getTotalPages(userSettingsEntity.getMaxBrowseResults(), httpRequest);
            List<SongEntity> songs = songController.readPopularPaginated(maxResults, page, httpRequest);
            LinkedList<Card> cards = new LinkedList<>();
            if (songs != null) {
                cards.addAll(CardConverter.convertSongs(songs, "browse"));
            }
            boolean loggedIn = verifyApiKey(httpRequest);
            byte[] data = viewPage.render(loggedIn, "Popular music:", "music", "popular", cards, settingsController.getCardWidth(userSettingsEntity), page, maxPage, userEntity);
            byte[] compressed = CompressionController.compress(data);
            return okResponseCompressed(compressed);
        } else {
            return redirect("/login");
        }
    }

    public HttpResponse popularTvShow(HttpRequest httpRequest) throws IOException, SQLException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        if (canAccess(httpRequest, PermissionGroupEnum.USER, userController)) {
            if (!settingsController.isTvShowLibraryEnable()) {
                return redirect("/disabled");
            }
            ExtendedUserEntity userEntity = (ExtendedUserEntity) getUser(httpRequest, userController);
            UserSettingsEntity userSettingsEntity = userEntity.getUserSettings();
            int page = httpRequest.getQueryParameter("page") != null ? Integer.parseInt(Objects.requireNonNull(httpRequest.getQueryParameter("page"))) : 0;
            int maxResults = settingsController.getMaxBrowseResults(userSettingsEntity);
            long maxPage = tvShowController.getTotalPages(userSettingsEntity.getMaxBrowseResults(), httpRequest);
            List<TvShowEntity> tvShows = tvShowController.readPopularPaginated(maxResults, page, httpRequest);
            LinkedList<Card> cards = new LinkedList<>();
            if (tvShows != null) {
                cards.addAll(CardConverter.convertTvShows(tvShows, "browse"));
            }
            boolean loggedIn = verifyApiKey(httpRequest);
            byte[] data = viewPage.render(loggedIn, "Popular tv shows:", "tvshow", "popular", cards, settingsController.getCardWidth(userSettingsEntity), page, maxPage, userEntity);
            byte[] compressed = CompressionController.compress(data);
            return okResponseCompressed(compressed);
        } else {
            return redirect("/login");
        }
    }
}
