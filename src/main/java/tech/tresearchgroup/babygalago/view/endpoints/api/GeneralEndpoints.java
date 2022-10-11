package tech.tresearchgroup.babygalago.view.endpoints.api;

import io.activej.http.*;
import io.activej.inject.annotation.Provides;
import io.activej.promise.Promisable;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import tech.tresearchgroup.babygalago.controller.SettingsController;
import tech.tresearchgroup.babygalago.controller.controllers.*;
import tech.tresearchgroup.babygalago.controller.endpoints.api.GeneralEndpointsController;
import tech.tresearchgroup.palila.controller.HttpResponses;
import tech.tresearchgroup.schemas.galago.enums.MediaTypeEnum;

import java.util.Locale;
import java.util.Objects;

@AllArgsConstructor
public class GeneralEndpoints extends HttpResponses {
    private final GeneralEndpointsController generalEndpointsController;

    private final RatingEntityController ratingEntityController;

    private final AlbumEntityController albumEntityController;
    private final ArtistEntityController artistEntityController;
    private final BookEntityController bookEntityController;
    private final CharacterEntityController characterEntityController;
    private final CompanyEntityController companyEntityController;
    private final GameEngineEntityController gameEngineEntityController;
    private final GameEntityController gameEntityController;
    private final GamePlatformReleaseEntityController gamePlatformReleaseEntityController;
    private final GameSeriesEntityController gameSeriesEntityController;
    private final ImageEntityController imageEntityController;
    private final LocationEntityController locationEntityController;
    private final LyricsEntityController lyricsEntityController;
    private final MovieEntityController movieEntityController;
    private final PersonEntityController personEntityController;
    private final SeasonEntityController seasonEntityController;
    private final SongEntityController songEntityController;
    private final SubtitleEntityController subtitleEntityController;
    private final TvShowEntityController tvShowEntityController;
    private final VideoEntityController videoEntityController;
    private final SettingsController settingsController;

    @Provides
    public RoutingServlet servlet() {
        return RoutingServlet.create()
            .map(HttpMethod.GET, "/v1/version", this::getVersion)
            .map(HttpMethod.OPTIONS, "/v1/version", this::optionsVersion)
            .map(HttpMethod.GET, "/v1/version/latest", this::getLatest)
            .map(HttpMethod.OPTIONS, "/v1/version/latest", this::optionsVersionLatest)
            .map(HttpMethod.POST, "/v1/upload", this::postUpload)
            .map(HttpMethod.OPTIONS, "/v1/upload", this::optionsUpload)
            .map(HttpMethod.GET, "/v1/search", this::getSearch)
            .map(HttpMethod.OPTIONS, "/v1/search", this::optionsSearch)
            .map(HttpMethod.GET, "/v1/image/:imageId", this::getImageById)
            .map(HttpMethod.OPTIONS, "/v1/image/:imageId", this::optionsImageById)
            .map(HttpMethod.GET, "/v1/video/:videoId", this::getVideoById)
            .map(HttpMethod.OPTIONS, "/v1/video/:videoId", this::optionsVideoById);
    }

    private @NotNull Promisable<HttpResponse> getVersion(@NotNull HttpRequest httpRequest) {
        try {
            return generalEndpointsController.getVersion(httpRequest);
        } catch (Exception e) {
            if (settingsController.isDebug()) {
                e.printStackTrace();
            }
            return error();
        }
    }

    private @NotNull Promisable<HttpResponse> optionsVersion(@NotNull HttpRequest httpRequest) {
        try {
            return HttpResponse.ok200().withHeader(HttpHeaders.ALLOW, HttpHeaderValue.of("GET"));
        } catch (Exception e) {
            if (settingsController.isDebug()) {
                e.printStackTrace();
            }
            return error();
        }
    }

    private @NotNull Promisable<HttpResponse> getLatest(@NotNull HttpRequest httpRequest) {
        try {
            return generalEndpointsController.getLatest(httpRequest);
        } catch (Exception e) {
            if (settingsController.isDebug()) {
                e.printStackTrace();
            }
            return error();
        }
    }

    private @NotNull Promisable<HttpResponse> optionsVersionLatest(@NotNull HttpRequest httpRequest) {
        try {
            return HttpResponse.ok200().withHeader(HttpHeaders.ALLOW, HttpHeaderValue.of("GET"));
        } catch (Exception e) {
            if (settingsController.isDebug()) {
                e.printStackTrace();
            }
            return error();
        }
    }

    private @NotNull Promisable<HttpResponse> postUpload(@NotNull HttpRequest httpRequest) {
        try {
            return generalEndpointsController.postUpload(httpRequest);
        } catch (Exception e) {
            if (settingsController.isDebug()) {
                e.printStackTrace();
            }
            return error();
        }
    }

    private @NotNull Promisable<HttpResponse> optionsUpload(@NotNull HttpRequest httpRequest) {
        try {
            return HttpResponse.ok200().withHeader(HttpHeaders.ALLOW, HttpHeaderValue.of("POST"));
        } catch (Exception e) {
            if (settingsController.isDebug()) {
                e.printStackTrace();
            }
            return error();
        }
    }

    private @NotNull Promisable<HttpResponse> getSearch(@NotNull HttpRequest httpRequest) {
        try {
            String query = httpRequest.getQueryParameter("query");
            MediaTypeEnum mediaType = MediaTypeEnum.valueOf(Objects.requireNonNull(httpRequest.getQueryParameter("type")).toUpperCase(Locale.ROOT));
            return switch (mediaType) {
                case ALBUM -> generalEndpointsController.getSearch(albumEntityController, query, httpRequest);
                case ARTIST -> generalEndpointsController.getSearch(artistEntityController, query, httpRequest);
                case BOOK -> generalEndpointsController.getSearch(bookEntityController, query, httpRequest);
                case CHARACTER -> generalEndpointsController.getSearch(characterEntityController, query, httpRequest);
                case COMPANY -> generalEndpointsController.getSearch(companyEntityController, query, httpRequest);
                case GAME -> generalEndpointsController.getSearch(gameEntityController, query, httpRequest);
                case GAME_ENGINE ->
                    generalEndpointsController.getSearch(gameEngineEntityController, query, httpRequest);
                case GAME_PLATFORM_RELEASE ->
                    generalEndpointsController.getSearch(gamePlatformReleaseEntityController, query, httpRequest);
                case GAME_SERIES ->
                    generalEndpointsController.getSearch(gameSeriesEntityController, query, httpRequest);
                case IMAGE -> generalEndpointsController.getSearch(imageEntityController, query, httpRequest);
                case LOCATION -> generalEndpointsController.getSearch(locationEntityController, query, httpRequest);
                case LYRICS -> generalEndpointsController.getSearch(lyricsEntityController, query, httpRequest);
                case MOVIE -> generalEndpointsController.getSearch(movieEntityController, query, httpRequest);
                case PERSON -> generalEndpointsController.getSearch(personEntityController, query, httpRequest);
                case RATING -> generalEndpointsController.getSearch(ratingEntityController, query, httpRequest);
                case SEASON -> generalEndpointsController.getSearch(seasonEntityController, query, httpRequest);
                case SONG -> generalEndpointsController.getSearch(songEntityController, query, httpRequest);
                case SUBTITLE -> generalEndpointsController.getSearch(subtitleEntityController, query, httpRequest);
                case TVSHOW -> generalEndpointsController.getSearch(tvShowEntityController, query, httpRequest);
                case VIDEO -> generalEndpointsController.getSearch(videoEntityController, query, httpRequest);
            };
        } catch (Exception e) {
            if (settingsController.isDebug()) {
                e.printStackTrace();
            }
            return error();
        }
    }

    private @NotNull Promisable<HttpResponse> optionsSearch(@NotNull HttpRequest httpRequest) {
        try {
            return HttpResponse.ok200().withHeader(HttpHeaders.ALLOW, HttpHeaderValue.of("GET"));
        } catch (Exception e) {
            if (settingsController.isDebug()) {
                e.printStackTrace();
            }
            return error();
        }
    }

    private @NotNull Promisable<HttpResponse> getImageById(@NotNull HttpRequest httpRequest) {
        try {
            int imageId = httpRequest.getQueryParameter("imageId") != null ? Integer.parseInt(Objects.requireNonNull(httpRequest.getQueryParameter("imageId"))) : 0;
            return generalEndpointsController.getImageById(imageId, httpRequest);
        } catch (Exception e) {
            if (settingsController.isDebug()) {
                e.printStackTrace();
            }
            return error();
        }
    }

    private @NotNull Promisable<HttpResponse> optionsImageById(@NotNull HttpRequest httpRequest) {
        try {
            return HttpResponse.ok200().withHeader(HttpHeaders.ALLOW, HttpHeaderValue.of("GET"));
        } catch (Exception e) {
            if (settingsController.isDebug()) {
                e.printStackTrace();
            }
            return error();
        }
    }

    private @NotNull Promisable<HttpResponse> getVideoById(@NotNull HttpRequest httpRequest) {
        try {
            String videoId = httpRequest.getPathParameter("videoId");
            return generalEndpointsController.getVideoById(Long.valueOf(videoId), httpRequest);
        } catch (Exception e) {
            if (settingsController.isDebug()) {
                e.printStackTrace();
            }
        }
        return error();
    }

    private @NotNull Promisable<HttpResponse> optionsVideoById(@NotNull HttpRequest httpRequest) {
        return HttpResponse.ok200().withHeader(HttpHeaders.ALLOW, HttpHeaderValue.of("GET"));
    }
}
