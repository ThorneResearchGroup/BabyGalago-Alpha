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
import tech.tresearchgroup.palila.model.Card;
import tech.tresearchgroup.schemas.galago.entities.*;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
public class BrowseEndpointsController extends BasicController {
    private final BookController bookController;
    private final GameController gameController;
    private final MovieController movieController;
    private final SongController songController;
    private final TvShowController tvShowController;
    private final UserController userController;
    private final SettingsController settingsController;
    private final ViewPage viewPage;

    public HttpResponse browseBook(HttpRequest httpRequest) throws IOException, SQLException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        ExtendedUserEntity userEntity = (ExtendedUserEntity) getUser(httpRequest, userController);
        if (!settingsController.isBookLibraryEnable()) {
            return redirect("/disabled");
        }
        UserSettingsEntity userSettingsEntity = null;
        if (userEntity != null) {
            userSettingsEntity = userEntity.getUserSettings();
        }
        boolean ascending = Objects.equals(httpRequest.getQueryParameter("ascending"), "on");
        int page = httpRequest.getQueryParameter("page") != null ? Integer.parseInt(Objects.requireNonNull(httpRequest.getQueryParameter("page"))) : 0;
        int maxResults = settingsController.getMaxBrowseResults(userSettingsEntity);
        long maxPage = bookController.getTotalPages(maxResults, httpRequest);
        String sortBy = httpRequest.getQueryParameter("sortBy");
        List<BookEntity> books = bookController.readOrderByPaginated(maxResults, page, sortBy, ascending, false, httpRequest);
        LinkedList<Card> cards = new LinkedList<>();
        if (books != null) {
            cards.addAll(CardConverter.convertBooks(books, "browse"));
        }
        boolean loggedIn = verifyApiKey(httpRequest);
        byte[] data = viewPage.render(loggedIn, "Books:", "book", "browse", cards, settingsController.getCardWidth(userSettingsEntity), page, maxPage, userEntity, BookEntity.class, ascending, sortBy, true);
        byte[] compressed = CompressionController.compress(data);
        return okResponseCompressed(compressed);
    }

    public HttpResponse browseGame(HttpRequest httpRequest) throws IOException, SQLException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        ExtendedUserEntity userEntity = (ExtendedUserEntity) getUser(httpRequest, userController);
        if (!settingsController.isGameLibraryEnable()) {
            return redirect("/disabled");
        }
        UserSettingsEntity userSettingsEntity = null;
        if (userEntity != null) {
            userSettingsEntity = userEntity.getUserSettings();
        }
        int page = httpRequest.getQueryParameter("page") != null ? Integer.parseInt(Objects.requireNonNull(httpRequest.getQueryParameter("page"))) : 0;
        int maxResults = settingsController.getMaxBrowseResults(userSettingsEntity);
        long maxPage = gameController.getTotalPages(maxResults, httpRequest);
        String sortBy = httpRequest.getQueryParameter("sortBy");
        boolean ascending = Objects.equals(httpRequest.getQueryParameter("ascending"), "on");
        List<GameEntity> games = gameController.readOrderByPaginated(maxResults, page, sortBy, ascending, false, httpRequest);
        LinkedList<Card> cards = new LinkedList<>();
        if (games != null) {
            cards.addAll(CardConverter.convertGames(games, "browse"));
        }
        boolean loggedIn = verifyApiKey(httpRequest);
        byte[] data = viewPage.render(loggedIn, "Games:", "game", "browse", cards, settingsController.getCardWidth(userSettingsEntity), page, maxPage, userEntity, GameEntity.class, ascending, sortBy, true);
        byte[] compressed = CompressionController.compress(data);
        return okResponseCompressed(compressed);
    }

    public HttpResponse browseMovie(HttpRequest httpRequest) throws IOException, SQLException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        ExtendedUserEntity userEntity = (ExtendedUserEntity) getUser(httpRequest, userController);
        if (!settingsController.isMovieLibraryEnable()) {
            return redirect("/disabled");
        }
        UserSettingsEntity userSettingsEntity = null;
        if (userEntity != null) {
            userSettingsEntity = userEntity.getUserSettings();
        }
        int maxResults = settingsController.getMaxBrowseResults(userSettingsEntity);
        int page = httpRequest.getQueryParameter("page") != null ? Integer.parseInt(Objects.requireNonNull(httpRequest.getQueryParameter("page"))) : 0;
        Long maxPage = movieController.getTotalPages(maxResults, httpRequest);
        String sortBy = httpRequest.getQueryParameter("sortBy");
        boolean ascending = Objects.equals(httpRequest.getQueryParameter("ascending"), "on");
        List<MovieEntity> movies = movieController.readOrderByPaginated(maxResults, page, sortBy, ascending, false, httpRequest);
        LinkedList<Card> cards = new LinkedList<>();
        if (movies != null) {
            cards.addAll(CardConverter.convertMovies(movies, "browse"));
        }
        boolean loggedIn = verifyApiKey(httpRequest);
        byte[] data = viewPage.render(loggedIn, "Movies:", "movie", "browse", cards, settingsController.getCardWidth(userSettingsEntity), page, maxPage, userEntity, MovieEntity.class, ascending, sortBy, true);
        byte[] compressed = CompressionController.compress(data);
        return okResponseCompressed(compressed);
    }

    public HttpResponse browseMusic(HttpRequest httpRequest) throws IOException, SQLException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        ExtendedUserEntity userEntity = (ExtendedUserEntity) getUser(httpRequest, userController);
        if (!settingsController.isMusicLibraryEnable()) {
            return redirect("/disabled");
        }
        UserSettingsEntity userSettingsEntity = null;
        if (userEntity != null) {
            userSettingsEntity = userEntity.getUserSettings();
        }
        int maxResults = settingsController.getMaxBrowseResults(userSettingsEntity);
        int page = httpRequest.getQueryParameter("page") != null ? Integer.parseInt(Objects.requireNonNull(httpRequest.getQueryParameter("page"))) : 0;
        long maxPage = songController.getTotalPages(maxResults, httpRequest);
        String sortBy = httpRequest.getQueryParameter("sortBy");
        boolean ascending = Objects.equals(httpRequest.getQueryParameter("ascending"), "on");
        List songs = songController.readOrderByPaginated(maxResults, page, sortBy, ascending, false, httpRequest);
        LinkedList<Card> cards = new LinkedList<>();
        if (songs != null) {
            cards.addAll(CardConverter.convertSongs(songs, "browse"));
        }
        boolean loggedIn = verifyApiKey(httpRequest);
        byte[] data = viewPage.render(loggedIn, "Music:", "music", "browse", cards, settingsController.getCardWidth(userSettingsEntity), page, maxPage, userEntity, SongEntity.class, ascending, sortBy, true);
        byte[] compressed = CompressionController.compress(data);
        return okResponseCompressed(compressed);
    }

    public HttpResponse browseTvShow(HttpRequest httpRequest) throws IOException, SQLException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        ExtendedUserEntity userEntity = (ExtendedUserEntity) getUser(httpRequest, userController);
        if (!settingsController.isTvShowLibraryEnable()) {
            return redirect("/disabled");
        }
        UserSettingsEntity userSettingsEntity = null;
        if (userEntity != null) {
            userSettingsEntity = userEntity.getUserSettings();
        }
        int maxResults = settingsController.getMaxBrowseResults(userSettingsEntity);
        int page = httpRequest.getQueryParameter("page") != null ? Integer.parseInt(Objects.requireNonNull(httpRequest.getQueryParameter("page"))) : 0;
        long maxPage = tvShowController.getTotalPages(maxResults, httpRequest);
        String sortBy = httpRequest.getQueryParameter("sortBy");
        boolean ascending = Objects.equals(httpRequest.getQueryParameter("ascending"), "on");
        List<TvShowEntity> tvShows = tvShowController.readOrderByPaginated(settingsController.getMaxBrowseResults(userSettingsEntity), page, sortBy, ascending, false, httpRequest);
        LinkedList<Card> cards = new LinkedList<>();
        if (tvShows != null) {
            cards.addAll(CardConverter.convertTvShows(tvShows, "browse"));
        }
        boolean loggedIn = verifyApiKey(httpRequest);
        byte[] data = viewPage.render(loggedIn, "Tv shows:", "tvshow", "browse", cards, settingsController.getCardWidth(userSettingsEntity), page, maxPage, userEntity, TvShowEntity.class, ascending, sortBy, true);
        byte[] compressed = CompressionController.compress(data);
        return okResponseCompressed(compressed);
    }
}
