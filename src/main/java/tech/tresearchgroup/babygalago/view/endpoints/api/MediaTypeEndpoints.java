package tech.tresearchgroup.babygalago.view.endpoints.api;

import io.activej.http.*;
import io.activej.inject.annotation.Provides;
import io.activej.promise.Promisable;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import tech.tresearchgroup.babygalago.controller.SettingsController;
import tech.tresearchgroup.babygalago.controller.controllers.*;
import tech.tresearchgroup.palila.controller.HttpResponses;

import java.nio.charset.Charset;
import java.util.Objects;

@AllArgsConstructor
public class MediaTypeEndpoints extends HttpResponses {
    private final AlbumController albumEndpointsController;
    private final ArtistController artistEndpointsController;
    private final BookController bookEndpointsController;
    private final CharacterController characterEndpointsController;
    private final CompanyController companyEndpointsController;
    private final GameController gameEndpointsController;
    private final GameEngineController gameEngineEndpointsController;
    private final GamePlatformReleaseController gamePlatformReleaseEndpointsController;
    private final GameSeriesController gameSeriesEndpointsController;
    private final ImageController imageEndpointsController;
    private final LocationController locationEndpointsController;
    private final LyricsController lyricsEndpointsController;
    private final MovieController movieEndpointsController;
    private final PersonController personEndpointsController;
    private final SeasonController seasonEndpointsController;
    private final SongController songEndpointsController;
    private final SubtitleController subtitleEndpointsController;
    private final TvShowController tvShowEndpointsController;
    private final VideoController videoEndpointsController;
    private final RatingController ratingEndpointsController;
    private final SettingsController settingsController;

    @Provides
    public RoutingServlet servlet() {
        return RoutingServlet.create()
            .map(HttpMethod.GET, "/v1/:mediaType", this::getMedia)
            .map(HttpMethod.POST, "/v1/:mediaType", this::postMedia)
            .map(HttpMethod.PUT, "/v1/:mediaType", this::putMedia)
            .map(HttpMethod.OPTIONS, "/v1/:mediaType", this::optionsMediaType)
            .map(HttpMethod.GET, "/v1/:mediaType/sample", this::getMediaSample)
            .map(HttpMethod.PUT, "/v1/:mediaType/deleteindex", this::putDeleteIndex)
            .map(HttpMethod.OPTIONS, "/v1/:mediaType/deleteindex", this::optionsDeleteIndex)
            .map(HttpMethod.PUT, "/v1/:mediaType/reindex", this::putReindex)
            .map(HttpMethod.OPTIONS, "/v1/:mediaType/reindex", this::optionsReIndex)
            .map(HttpMethod.GET, "/v1/:mediaType/search", this::search)
            .map(HttpMethod.OPTIONS, "/v1/:mediaType/search", this::optionsSearch)
            .map(HttpMethod.GET, "/v1/:mediaType/:mediaId", this::getMediaById)
            .map(HttpMethod.PATCH, "/v1/:mediaType/:mediaId", this::patchMediaById)
            .map(HttpMethod.DELETE, "/v1/:mediaType/:mediaId", this::deleteMediaById)
            .map(HttpMethod.OPTIONS, "/v1/:mediaType/:mediaId", this::optionsMediaTypeById)
            .map(HttpMethod.GET, "/v1/:mediaType/:mediaId/ratings", this::getMediaRatings)
            .map(HttpMethod.OPTIONS, "/v1/:mediaType/:mediaId/ratings", this::optionsMediaTypeRatingsById);
    }

    private @NotNull Promisable<HttpResponse> getMedia(@NotNull HttpRequest httpRequest) {
        try {
            String mediaType = httpRequest.getPathParameter("mediaType");
            int page = httpRequest.getQueryParameter("page") != null ? Integer.parseInt(Objects.requireNonNull(httpRequest.getQueryParameter("page"))) : 0;
            int pageSize = httpRequest.getQueryParameter("pageSize") != null ? Integer.parseInt(Objects.requireNonNull(httpRequest.getQueryParameter("pageSize"))) : 0;
            return switch (mediaType) {
                case "album" ->
                    okResponseCompressed(albumEndpointsController.readPaginatedAPIResponse(page, pageSize, true, httpRequest));
                case "artist" ->
                    okResponseCompressed(artistEndpointsController.readPaginatedAPIResponse(page, pageSize, true, httpRequest));
                case "book" ->
                    okResponseCompressed(bookEndpointsController.readPaginatedAPIResponse(page, pageSize, true, httpRequest));
                case "character" ->
                    okResponseCompressed(characterEndpointsController.readPaginatedAPIResponse(page, pageSize, true, httpRequest));
                case "company" ->
                    okResponseCompressed(companyEndpointsController.readPaginatedAPIResponse(page, pageSize, true, httpRequest));
                case "game" ->
                    okResponseCompressed(gameEndpointsController.readPaginatedAPIResponse(page, pageSize, true, httpRequest));
                case "gameengine" ->
                    okResponseCompressed(gameEngineEndpointsController.readPaginatedAPIResponse(page, pageSize, true, httpRequest));
                case "gameplatformrelease" ->
                    okResponseCompressed(gamePlatformReleaseEndpointsController.readPaginatedAPIResponse(page, pageSize, true, httpRequest));
                case "gameseries" ->
                    okResponseCompressed(gameSeriesEndpointsController.readPaginatedAPIResponse(page, pageSize, true, httpRequest));
                case "image" ->
                    okResponseCompressed(imageEndpointsController.readPaginatedAPIResponse(page, pageSize, true, httpRequest));
                case "location" ->
                    okResponseCompressed(locationEndpointsController.readPaginatedAPIResponse(page, pageSize, true, httpRequest));
                case "lyrics" ->
                    okResponseCompressed(lyricsEndpointsController.readPaginatedAPIResponse(page, pageSize, true, httpRequest));
                case "movie" ->
                    okResponseCompressed(movieEndpointsController.readPaginatedAPIResponse(page, pageSize, true, httpRequest));
                case "person" ->
                    okResponseCompressed(personEndpointsController.readPaginatedAPIResponse(page, pageSize, true, httpRequest));
                case "season" ->
                    okResponseCompressed(seasonEndpointsController.readPaginatedAPIResponse(page, pageSize, true, httpRequest));
                case "song" ->
                    okResponseCompressed(songEndpointsController.readPaginatedAPIResponse(page, pageSize, true, httpRequest));
                case "subtitle" ->
                    okResponseCompressed(subtitleEndpointsController.readPaginatedAPIResponse(page, pageSize, true, httpRequest));
                case "tvshow" ->
                    okResponseCompressed(tvShowEndpointsController.readPaginatedAPIResponse(page, pageSize, true, httpRequest));
                case "video" ->
                    okResponseCompressed(videoEndpointsController.readPaginatedAPIResponse(page, pageSize, true, httpRequest));
                default -> HttpResponse.ofCode(404);
            };
        } catch (Exception e) {
            if (settingsController.isDebug()) {
                e.printStackTrace();
            }
        }
        return HttpResponse.ofCode(500);
    }

    private @NotNull Promisable<HttpResponse> postMedia(@NotNull HttpRequest httpRequest) {
        try {
            String type = httpRequest.getPathParameter("mediaType");
            String data = httpRequest.loadBody().getResult().asString(Charset.defaultCharset());
            return switch (type) {
                case "album" -> ok(albumEndpointsController.createSecureAPIResponse(data, httpRequest));
                case "artist" -> ok(artistEndpointsController.createSecureAPIResponse(data, httpRequest));
                case "book" -> ok(bookEndpointsController.createSecureAPIResponse(data, httpRequest));
                case "character" -> ok(characterEndpointsController.createSecureAPIResponse(data, httpRequest));
                case "company" -> ok(companyEndpointsController.createSecureAPIResponse(data, httpRequest));
                case "game" -> ok(gameEndpointsController.createSecureAPIResponse(data, httpRequest));
                case "gameengine" -> ok(gameEngineEndpointsController.createSecureAPIResponse(data, httpRequest));
                case "gameplatformrelease" ->
                    ok(gamePlatformReleaseEndpointsController.createSecureAPIResponse(data, httpRequest));
                case "gameseries" -> ok(gameSeriesEndpointsController.createSecureAPIResponse(data, httpRequest));
                case "image" -> ok(imageEndpointsController.createSecureAPIResponse(data, httpRequest));
                case "location" -> ok(locationEndpointsController.createSecureAPIResponse(data, httpRequest));
                case "lyrics" -> ok(lyricsEndpointsController.createSecureAPIResponse(data, httpRequest));
                case "movie" -> ok(movieEndpointsController.createSecureAPIResponse(data, httpRequest));
                case "person" -> ok(personEndpointsController.createSecureAPIResponse(data, httpRequest));
                case "season" -> ok(seasonEndpointsController.createSecureAPIResponse(data, httpRequest));
                case "song" -> ok(songEndpointsController.createSecureAPIResponse(data, httpRequest));
                case "subtitle" -> ok(subtitleEndpointsController.createSecureAPIResponse(data, httpRequest));
                case "tvshow" -> ok(tvShowEndpointsController.createSecureAPIResponse(data, httpRequest));
                case "video" -> ok(videoEndpointsController.createSecureAPIResponse(data, httpRequest));
                default -> HttpResponse.ofCode(404);
            };
        } catch (Exception e) {
            if (settingsController.isDebug()) {
                e.printStackTrace();
            }
        }
        return HttpResponse.ofCode(500);
    }

    private @NotNull Promisable<HttpResponse> putMedia(@NotNull HttpRequest httpRequest) {
        try {
            String type = httpRequest.getPathParameter("mediaType");
            String data = httpRequest.loadBody().getResult().asString(Charset.defaultCharset());
            return switch (type) {
                case "album" -> ok(albumEndpointsController.createSecureResponse(data, httpRequest) != null);
                case "artist" -> ok(artistEndpointsController.createSecureResponse(data, httpRequest) != null);
                case "book" -> ok(bookEndpointsController.createSecureResponse(data, httpRequest) != null);
                case "character" -> ok(characterEndpointsController.createSecureResponse(data, httpRequest) != null);
                case "company" -> ok(companyEndpointsController.createSecureResponse(data, httpRequest) != null);
                case "game" -> ok(gameEndpointsController.createSecureResponse(data, httpRequest) != null);
                case "gameengine" -> ok(gameEngineEndpointsController.createSecureResponse(data, httpRequest) != null);
                case "gameplatformrelease" ->
                    ok(gamePlatformReleaseEndpointsController.createSecureResponse(data, httpRequest) != null);
                case "gameseries" -> ok(gameSeriesEndpointsController.createSecureResponse(data, httpRequest) != null);
                case "image" -> ok(imageEndpointsController.createSecureResponse(data, httpRequest) != null);
                case "location" -> ok(locationEndpointsController.createSecureResponse(data, httpRequest) != null);
                case "lyrics" -> ok(lyricsEndpointsController.createSecureResponse(data, httpRequest) != null);
                case "movie" -> ok(movieEndpointsController.createSecureResponse(data, httpRequest) != null);
                case "person" -> ok(personEndpointsController.createSecureResponse(data, httpRequest) != null);
                case "season" -> ok(seasonEndpointsController.createSecureResponse(data, httpRequest) != null);
                case "song" -> ok(songEndpointsController.createSecureResponse(data, httpRequest) != null);
                case "subtitle" -> ok(subtitleEndpointsController.createSecureResponse(data, httpRequest) != null);
                case "tvshow" -> ok(tvShowEndpointsController.createSecureResponse(data, httpRequest) != null);
                case "video" -> ok(videoEndpointsController.createSecureResponse(data, httpRequest) != null);
                default -> HttpResponse.ofCode(404);
            };
        } catch (Exception e) {
            if (settingsController.isDebug()) {
                e.printStackTrace();
            }
        }
        return HttpResponse.ofCode(500);
    }

    private @NotNull Promisable<HttpResponse> optionsMediaType(@NotNull HttpRequest httpRequest) {
        return HttpResponse.ok200().withHeader(HttpHeaders.ALLOW, HttpHeaderValue.of("GET, POST, PUT"));
    }

    private @NotNull Promisable<HttpResponse> getMediaSample(@NotNull HttpRequest httpRequest) {
        try {
            String type = httpRequest.getPathParameter("mediaType");
            return switch (type) {
                case "album" -> okResponseCompressed(albumEndpointsController.getSample(httpRequest));
                case "artist" -> okResponseCompressed(artistEndpointsController.getSample(httpRequest));
                case "book" -> okResponseCompressed(bookEndpointsController.getSample(httpRequest));
                case "character" -> okResponseCompressed(characterEndpointsController.getSample(httpRequest));
                case "company" -> okResponseCompressed(companyEndpointsController.getSample(httpRequest));
                case "game" -> okResponseCompressed(gameEndpointsController.getSample(httpRequest));
                case "gameengine" -> okResponseCompressed(gameEngineEndpointsController.getSample(httpRequest));
                case "gameplatformrelease" ->
                    okResponseCompressed(gamePlatformReleaseEndpointsController.getSample(httpRequest));
                case "gameseries" -> okResponseCompressed(gameSeriesEndpointsController.getSample(httpRequest));
                case "image" -> okResponseCompressed(imageEndpointsController.getSample(httpRequest));
                case "location" -> okResponseCompressed(locationEndpointsController.getSample(httpRequest));
                case "lyrics" -> okResponseCompressed(lyricsEndpointsController.getSample(httpRequest));
                case "movie" -> okResponseCompressed(movieEndpointsController.getSample(httpRequest));
                case "person" -> okResponseCompressed(personEndpointsController.getSample(httpRequest));
                case "season" -> okResponseCompressed(seasonEndpointsController.getSample(httpRequest));
                case "song" -> okResponseCompressed(songEndpointsController.getSample(httpRequest));
                case "subtitle" -> okResponseCompressed(subtitleEndpointsController.getSample(httpRequest));
                case "tvshow" -> okResponseCompressed(tvShowEndpointsController.getSample(httpRequest));
                case "video" -> okResponseCompressed(videoEndpointsController.getSample(httpRequest));
                default -> HttpResponse.ofCode(404);
            };
        } catch (Exception e) {
            if (settingsController.isDebug()) {
                e.printStackTrace();
            }
        }
        return HttpResponse.ofCode(500);
    }

    private @NotNull Promisable<HttpResponse> putDeleteIndex(@NotNull HttpRequest httpRequest) {
        try {
            String type = httpRequest.getPathParameter("mediaType");
            return switch (type) {
                case "album" -> albumEndpointsController.deleteAllIndexes(httpRequest);
                case "artist" -> artistEndpointsController.deleteAllIndexes(httpRequest);
                case "book" -> bookEndpointsController.deleteAllIndexes(httpRequest);
                case "character" -> characterEndpointsController.deleteAllIndexes(httpRequest);
                case "company" -> companyEndpointsController.deleteAllIndexes(httpRequest);
                case "game" -> gameEndpointsController.deleteAllIndexes(httpRequest);
                case "gameengine" -> gameEngineEndpointsController.deleteAllIndexes(httpRequest);
                case "gameplatformrelease" -> gamePlatformReleaseEndpointsController.deleteAllIndexes(httpRequest);
                case "gameseries" -> gameSeriesEndpointsController.deleteAllIndexes(httpRequest);
                case "image" -> imageEndpointsController.deleteAllIndexes(httpRequest);
                case "location" -> locationEndpointsController.deleteAllIndexes(httpRequest);
                case "lyrics" -> lyricsEndpointsController.deleteAllIndexes(httpRequest);
                case "movie" -> movieEndpointsController.deleteAllIndexes(httpRequest);
                case "person" -> personEndpointsController.deleteAllIndexes(httpRequest);
                case "season" -> seasonEndpointsController.deleteAllIndexes(httpRequest);
                case "song" -> songEndpointsController.deleteAllIndexes(httpRequest);
                case "tvshow" -> tvShowEndpointsController.deleteAllIndexes(httpRequest);
                case "video" -> videoEndpointsController.deleteAllIndexes(httpRequest);
                default -> HttpResponse.ofCode(500);
            };
        } catch (Exception e) {
            if (settingsController.isDebug()) {
                e.printStackTrace();
            }
        }
        return HttpResponse.ofCode(500);
    }

    private @NotNull Promisable<HttpResponse> optionsDeleteIndex(@NotNull HttpRequest httpRequest) {
        return HttpResponse.ok200().withHeader(HttpHeaders.ALLOW, HttpHeaderValue.of("PUT"));
    }

    private @NotNull Promisable<HttpResponse> putReindex(@NotNull HttpRequest httpRequest) {
        try {
            String type = httpRequest.getPathParameter("mediaType");
            switch (type) {
                case "album" -> ok(albumEndpointsController.reindex(httpRequest));
                case "artist" -> ok(artistEndpointsController.reindex(httpRequest));
                case "book" -> ok(bookEndpointsController.reindex(httpRequest));
                case "character" -> ok(characterEndpointsController.reindex(httpRequest));
                case "company" -> ok(companyEndpointsController.reindex(httpRequest));
                case "game" -> ok(gameEndpointsController.reindex(httpRequest));
                case "gameengine" -> ok(gameEngineEndpointsController.reindex(httpRequest));
                case "gameplatformrelease" -> ok(gamePlatformReleaseEndpointsController.reindex(httpRequest));
                case "gameseries" -> ok(gameSeriesEndpointsController.reindex(httpRequest));
                case "image" -> ok(imageEndpointsController.reindex(httpRequest));
                case "location" -> ok(locationEndpointsController.reindex(httpRequest));
                case "lyrics" -> ok(lyricsEndpointsController.reindex(httpRequest));
                case "movie" -> ok(movieEndpointsController.reindex(httpRequest));
                case "person" -> ok(personEndpointsController.reindex(httpRequest));
                case "season" -> ok(seasonEndpointsController.reindex(httpRequest));
                case "song" -> ok(songEndpointsController.reindex(httpRequest));
                case "tvshow" -> ok(tvShowEndpointsController.reindex(httpRequest));
                case "video" -> ok(videoEndpointsController.reindex(httpRequest));
            }
            return HttpResponse.ok200();
        } catch (Exception e) {
            if (settingsController.isDebug()) {
                e.printStackTrace();
            }
        }
        return HttpResponse.ofCode(500);
    }

    private @NotNull Promisable<HttpResponse> optionsReIndex(@NotNull HttpRequest httpRequest) {
        return HttpResponse.ok200().withHeader(HttpHeaders.ALLOW, HttpHeaderValue.of("PUT"));
    }

    private @NotNull Promisable<HttpResponse> search(@NotNull HttpRequest httpRequest) {
        try {
            String type = httpRequest.getPathParameter("mediaType");
            String query = httpRequest.getQueryParameter("q");
            return switch (type) {
                case "album" -> ok(albumEndpointsController.searchAPIResponse(query, "*", httpRequest));
                case "artist" -> ok(artistEndpointsController.searchAPIResponse(query, "*", httpRequest));
                case "book" -> ok(bookEndpointsController.searchAPIResponse(query, "*", httpRequest));
                case "character" -> ok(characterEndpointsController.searchAPIResponse(query, "*", httpRequest));
                case "company" -> ok(companyEndpointsController.searchAPIResponse(query, "*", httpRequest));
                case "game" -> ok(gameEndpointsController.searchAPIResponse(query, "*", httpRequest));
                case "gameengine" -> ok(gameEngineEndpointsController.searchAPIResponse(query, "*", httpRequest));
                case "gameplatformrelease" ->
                    ok(gamePlatformReleaseEndpointsController.searchAPIResponse(query, "*", httpRequest));
                case "gameseries" -> ok(gameSeriesEndpointsController.searchAPIResponse(query, "*", httpRequest));
                case "image" -> ok(imageEndpointsController.searchAPIResponse(query, "*", httpRequest));
                case "location" -> ok(locationEndpointsController.searchAPIResponse(query, "*", httpRequest));
                case "lyrics" -> ok(lyricsEndpointsController.searchAPIResponse(query, "*", httpRequest));
                case "movie" -> ok(movieEndpointsController.searchAPIResponse(query, "*", httpRequest));
                case "person" -> ok(personEndpointsController.searchAPIResponse(query, "*", httpRequest));
                case "season" -> ok(seasonEndpointsController.searchAPIResponse(query, "*", httpRequest));
                case "subtitle" -> ok(subtitleEndpointsController.searchAPIResponse(query, "*", httpRequest));
                case "song" -> ok(songEndpointsController.searchAPIResponse(query, "*", httpRequest));
                case "tvshow" -> ok(tvShowEndpointsController.searchAPIResponse(query, "*", httpRequest));
                case "video" -> ok(videoEndpointsController.searchAPIResponse(query, "*", httpRequest));
                default -> HttpResponse.notFound404();
            };
        } catch (Exception e) {
            if (settingsController.isDebug()) {
                e.printStackTrace();
            }
        }
        return HttpResponse.ofCode(500);
    }

    private @NotNull Promisable<HttpResponse> optionsSearch(@NotNull HttpRequest httpRequest) {
        return HttpResponse.ok200().withHeader(HttpHeaders.ALLOW, HttpHeaderValue.of("GET"));
    }

    private @NotNull Promisable<HttpResponse> getMediaById(@NotNull HttpRequest httpRequest) {
        try {
            String type = httpRequest.getPathParameter("mediaType");
            long mediaId = Long.parseLong(httpRequest.getPathParameter("mediaId"));
            return switch (type) {
                case "album" ->
                    okResponseCompressed(albumEndpointsController.readSecureAPIResponse(mediaId, httpRequest));
                case "artist" ->
                    okResponseCompressed(artistEndpointsController.readSecureAPIResponse(mediaId, httpRequest));
                case "book" ->
                    okResponseCompressed(bookEndpointsController.readSecureAPIResponse(mediaId, httpRequest));
                case "character" ->
                    okResponseCompressed(characterEndpointsController.readSecureAPIResponse(mediaId, httpRequest));
                case "company" ->
                    okResponseCompressed(companyEndpointsController.readSecureAPIResponse(mediaId, httpRequest));
                case "game" ->
                    okResponseCompressed(gameEndpointsController.readSecureAPIResponse(mediaId, httpRequest));
                case "gameengine" ->
                    okResponseCompressed(gameEngineEndpointsController.readSecureAPIResponse(mediaId, httpRequest));
                case "gameplatformrelease" ->
                    okResponseCompressed(gamePlatformReleaseEndpointsController.readSecureAPIResponse(mediaId, httpRequest));
                case "gameseries" ->
                    okResponseCompressed(gameSeriesEndpointsController.readSecureAPIResponse(mediaId, httpRequest));
                case "image" ->
                    okResponseCompressed(imageEndpointsController.readSecureAPIResponse(mediaId, httpRequest));
                case "location" ->
                    okResponseCompressed(locationEndpointsController.readSecureAPIResponse(mediaId, httpRequest));
                case "lyrics" ->
                    okResponseCompressed(lyricsEndpointsController.readSecureAPIResponse(mediaId, httpRequest));
                case "movie" ->
                    okResponseCompressed(movieEndpointsController.readSecureAPIResponse(mediaId, httpRequest));
                case "person" ->
                    okResponseCompressed(personEndpointsController.readSecureAPIResponse(mediaId, httpRequest));
                case "season" ->
                    okResponseCompressed(seasonEndpointsController.readSecureAPIResponse(mediaId, httpRequest));
                case "song" ->
                    okResponseCompressed(songEndpointsController.readSecureAPIResponse(mediaId, httpRequest));
                case "subtitle" ->
                    okResponseCompressed(subtitleEndpointsController.readSecureAPIResponse(mediaId, httpRequest));
                case "tvshow" ->
                    okResponseCompressed(tvShowEndpointsController.readSecureAPIResponse(mediaId, httpRequest));
                case "video" ->
                    okResponseCompressed(videoEndpointsController.readSecureAPIResponse(mediaId, httpRequest));
                default -> HttpResponse.ofCode(404);
            };
        } catch (Exception e) {
            if (settingsController.isDebug()) {
                e.printStackTrace();
            }
        }
        return HttpResponse.ofCode(500);
    }

    private @NotNull Promisable<HttpResponse> patchMediaById(@NotNull HttpRequest httpRequest) {
        try {
            String type = httpRequest.getPathParameter("mediaType");
            String data = httpRequest.loadBody().getResult().asString(Charset.defaultCharset());
            long id = Long.parseLong(httpRequest.getPathParameter("mediaId"));
            return switch (type) {
                case "album" -> ok(albumEndpointsController.update(id, data, httpRequest));
                case "artist" -> ok(artistEndpointsController.update(id, data, httpRequest));
                case "book" -> ok(bookEndpointsController.update(id, data, httpRequest));
                case "character" -> ok(characterEndpointsController.update(id, data, httpRequest));
                case "company" -> ok(companyEndpointsController.update(id, data, httpRequest));
                case "game" -> ok(gameEndpointsController.update(id, data, httpRequest));
                case "gameengine" -> ok(gameEngineEndpointsController.update(id, data, httpRequest));
                case "gameplatformrelease" -> ok(gamePlatformReleaseEndpointsController.update(id, data, httpRequest));
                case "gameseries" -> ok(gameSeriesEndpointsController.update(id, data, httpRequest));
                case "image" -> ok(imageEndpointsController.update(id, data, httpRequest));
                case "location" -> ok(locationEndpointsController.update(id, data, httpRequest));
                case "lyrics" -> ok(lyricsEndpointsController.update(id, data, httpRequest));
                case "movie" -> ok(movieEndpointsController.update(id, data, httpRequest));
                case "person" -> ok(personEndpointsController.update(id, data, httpRequest));
                case "season" -> ok(seasonEndpointsController.update(id, data, httpRequest));
                case "song" -> ok(songEndpointsController.update(id, data, httpRequest));
                case "subtitle" -> ok(subtitleEndpointsController.update(id, data, httpRequest));
                case "tvshow" -> ok(tvShowEndpointsController.update(id, data, httpRequest));
                case "video" -> ok(videoEndpointsController.update(id, data, httpRequest));
                default -> HttpResponse.ofCode(404);
            };
        } catch (Exception e) {
            if (settingsController.isDebug()) {
                e.printStackTrace();
            }
        }
        return HttpResponse.ofCode(500);
    }

    private @NotNull Promisable<HttpResponse> deleteMediaById(@NotNull HttpRequest httpRequest) {
        try {
            String type = httpRequest.getPathParameter("mediaType");
            long mediaId = Long.parseLong(httpRequest.getPathParameter("mediaId"));
            return switch (type) {
                case "album" -> ok(albumEndpointsController.delete(mediaId, httpRequest));
                case "artist" -> ok(artistEndpointsController.delete(mediaId, httpRequest));
                case "book" -> ok(bookEndpointsController.delete(mediaId, httpRequest));
                case "character" -> ok(characterEndpointsController.delete(mediaId, httpRequest));
                case "company" -> ok(companyEndpointsController.delete(mediaId, httpRequest));
                case "game" -> ok(gameEndpointsController.delete(mediaId, httpRequest));
                case "gameengine" -> ok(gameEngineEndpointsController.delete(mediaId, httpRequest));
                case "gameplatformrelease" -> ok(gamePlatformReleaseEndpointsController.delete(mediaId, httpRequest));
                case "gameseries" -> ok(gameSeriesEndpointsController.delete(mediaId, httpRequest));
                case "image" -> ok(imageEndpointsController.delete(mediaId, httpRequest));
                case "location" -> ok(locationEndpointsController.delete(mediaId, httpRequest));
                case "lyrics" -> ok(lyricsEndpointsController.delete(mediaId, httpRequest));
                case "movie" -> ok(movieEndpointsController.delete(mediaId, httpRequest));
                case "person" -> ok(personEndpointsController.delete(mediaId, httpRequest));
                case "season" -> ok(seasonEndpointsController.delete(mediaId, httpRequest));
                case "song" -> ok(songEndpointsController.delete(mediaId, httpRequest));
                case "subtitle" -> ok(subtitleEndpointsController.delete(mediaId, httpRequest));
                case "tvshow" -> ok(tvShowEndpointsController.delete(mediaId, httpRequest));
                case "video" -> ok(videoEndpointsController.delete(mediaId, httpRequest));
                default -> HttpResponse.ofCode(404);
            };
        } catch (Exception e) {
            if (settingsController.isDebug()) {
                e.printStackTrace();
            }
        }
        return HttpResponse.ofCode(500);
    }

    private @NotNull Promisable<HttpResponse> optionsMediaTypeById(@NotNull HttpRequest httpRequest) {
        return HttpResponse.ok200().withHeader(HttpHeaders.ALLOW, HttpHeaderValue.of("GET, PATCH, DELETE"));
    }

    private @NotNull Promisable<HttpResponse> getMediaRatings(@NotNull HttpRequest httpRequest) {
        try {
            String type = httpRequest.getPathParameter("mediaType");
            int mediaId = Integer.parseInt(httpRequest.getPathParameter("mediaId"));
            return switch (type) {
                //TODO replace with ENUM
                case "album", "subtitle", "season", "song", "tvshow", "video", "person", "movie", "location", "lyrics",
                    "language", "image", "gameseries", "gameplatformrelease", "gameengine", "company", "book", "artist",
                    "character", "game" -> ratingEndpointsController.getRatings(type, mediaId);
                default -> HttpResponse.ofCode(404);
            };
        } catch (Exception e) {
            if (settingsController.isDebug()) {
                e.printStackTrace();
            }
        }
        return HttpResponse.ofCode(500);
    }

    private @NotNull Promisable<HttpResponse> optionsMediaTypeRatingsById(@NotNull HttpRequest httpRequest) {
        return HttpResponse.ok200().withHeader(HttpHeaders.ALLOW, HttpHeaderValue.of("GET"));
    }
}
