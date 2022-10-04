package tech.tresearchgroup.babygalago.controller.endpoints.ui;

import io.activej.http.HttpRequest;
import io.activej.http.HttpResponse;
import lombok.AllArgsConstructor;
import tech.tresearchgroup.babygalago.controller.SettingsController;
import tech.tresearchgroup.babygalago.controller.controllers.*;
import tech.tresearchgroup.babygalago.model.ExtendedUserEntity;
import tech.tresearchgroup.babygalago.view.pages.play.*;
import tech.tresearchgroup.palila.controller.BasicController;
import tech.tresearchgroup.palila.controller.CompressionController;
import tech.tresearchgroup.schemas.galago.entities.MovieEntity;
import tech.tresearchgroup.schemas.galago.entities.UserSettingsEntity;
import tech.tresearchgroup.schemas.galago.entities.VideoEntity;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

@AllArgsConstructor
public class PlayEndpointsController extends BasicController {
    private final BookController bookController;
    private final GameController gameController;
    private final MovieController movieController;
    private final SongController songController;
    private final TvShowController tvShowController;
    private final UserController userController;
    private final SettingsController settingsController;
    private final VideoController videoController;
    private final PlayBookPage playBookPage;
    private final PlayGamePage playGamePage;
    private final PlayMoviePage playMoviePage;
    private final PlayMusicPage playMusicPage;
    private final PlayTvShowPage playTvShowPage;

    public HttpResponse playBook(HttpRequest httpRequest) throws IOException, SQLException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        if (!settingsController.isBookLibraryEnable()) {
            return redirect("/disabled");
        }
        int id = Integer.parseInt(httpRequest.getPathParameter("id"));
        boolean loggedIn = verifyApiKey(httpRequest);
        ExtendedUserEntity userEntity = (ExtendedUserEntity) getUser(httpRequest, userController);
        byte[] data = playBookPage.render(loggedIn, userEntity);
        byte[] compressed = CompressionController.compress(data);
        return okResponseCompressed(compressed);
    }

    public HttpResponse playGame(HttpRequest httpRequest) throws IOException, SQLException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        if (!settingsController.isGameLibraryEnable()) {
            return redirect("/disabled");
        }
        int id = Integer.parseInt(httpRequest.getPathParameter("id"));
        boolean loggedIn = verifyApiKey(httpRequest);
        ExtendedUserEntity userEntity = (ExtendedUserEntity) getUser(httpRequest, userController);
        byte[] data = playGamePage.render(loggedIn, userEntity);
        byte[] compressed = CompressionController.compress(data);
        return okResponseCompressed(compressed);
    }

    public HttpResponse playMovie(HttpRequest httpRequest) throws IOException, SQLException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        if (!settingsController.isMovieLibraryEnable()) {
            return redirect("/disabled");
        }
        long id = Long.parseLong(httpRequest.getPathParameter("id"));
        MovieEntity movieEntity = (MovieEntity) movieController.readSecureResponse(id, httpRequest);
        if (movieEntity != null) {
            UserSettingsEntity userSettingsEntity = null;
            ExtendedUserEntity userEntity = (ExtendedUserEntity) getUser(httpRequest, userController);
            if (userEntity != null) {
                userSettingsEntity = userEntity.getUserSettings();
            }
            boolean loggedIn = verifyApiKey(httpRequest);
            List videos = new LinkedList<>();
            for (VideoEntity videoEntity : movieEntity.getFiles()) {
                videos.add(videoController.readSecureResponse(videoEntity.getId(), httpRequest));
            }
            byte[] data = playMoviePage.render(loggedIn, movieEntity, userSettingsEntity, userEntity, videos);
            byte[] compressed = CompressionController.compress(data);
            return okResponseCompressed(compressed);
        }
        return error();
    }

    public HttpResponse playMusic(HttpRequest httpRequest) throws IOException, SQLException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        if (!settingsController.isMusicLibraryEnable()) {
            return redirect("/disabled");
        }
        int id = Integer.parseInt(httpRequest.getPathParameter("id"));
        boolean loggedIn = verifyApiKey(httpRequest);
        ExtendedUserEntity userEntity = (ExtendedUserEntity) getUser(httpRequest, userController);
        byte[] data = playMusicPage.render(loggedIn, userEntity);
        byte[] compressed = CompressionController.compress(data);
        return okResponseCompressed(compressed);
    }

    public HttpResponse playTvShow(HttpRequest httpRequest) throws IOException, SQLException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        if (!settingsController.isTvShowLibraryEnable()) {
            return redirect("/disabled");
        }
        boolean loggedIn = verifyApiKey(httpRequest);
        ExtendedUserEntity userEntity = (ExtendedUserEntity) getUser(httpRequest, userController);
        byte[] data = playTvShowPage.render(loggedIn, userEntity);
        byte[] compressed = CompressionController.compress(data);
        return okResponseCompressed(compressed);
    }
}
