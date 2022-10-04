package tech.tresearchgroup.babygalago.view.endpoints.api;

import io.activej.http.*;
import io.activej.inject.annotation.Provides;
import io.activej.inject.module.AbstractModule;
import io.activej.promise.Promisable;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import tech.tresearchgroup.babygalago.controller.SettingsController;
import tech.tresearchgroup.babygalago.controller.controllers.*;
import tech.tresearchgroup.babygalago.controller.endpoints.api.GeneralEndpointsController;
import tech.tresearchgroup.schemas.galago.enums.MediaTypeEnum;

import java.util.Locale;
import java.util.Objects;

@AllArgsConstructor
public class GeneralEndpoints extends AbstractModule {
    private final GeneralEndpointsController generalEndpointsController;

    private final RatingController ratingController;

    private final AlbumController albumController;
    private final ArtistController artistController;
    private final BookController bookController;
    private final CharacterController characterController;
    private final CompanyController companyController;
    private final GameEngineController gameEngineController;
    private final GameController gameController;
    private final GamePlatformReleaseController gamePlatformReleaseController;
    private final GameSeriesController gameSeriesController;
    private final ImageController imageController;
    private final LocationController locationController;
    private final LyricsController lyricsController;
    private final MovieController movieController;
    private final PersonController personController;
    private final SeasonController seasonController;
    private final SongController songController;
    private final SubtitleController subtitleController;
    private final TvShowController tvShowController;
    private final VideoController videoController;
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
            return HttpResponse.ofCode(500);
        }
    }

    private @NotNull Promisable<HttpResponse> optionsVersion(@NotNull HttpRequest httpRequest) {
        try {
            return HttpResponse.ok200().withHeader(HttpHeaders.ALLOW, HttpHeaderValue.of("GET"));
        } catch (Exception e) {
            if (settingsController.isDebug()) {
                e.printStackTrace();
            }
            return HttpResponse.ofCode(500);
        }
    }

    private @NotNull Promisable<HttpResponse> getLatest(@NotNull HttpRequest httpRequest) {
        try {
            return generalEndpointsController.getLatest(httpRequest);
        } catch (Exception e) {
            if (settingsController.isDebug()) {
                e.printStackTrace();
            }
            return HttpResponse.ofCode(500);
        }
    }

    private @NotNull Promisable<HttpResponse> optionsVersionLatest(@NotNull HttpRequest httpRequest) {
        try {
            return HttpResponse.ok200().withHeader(HttpHeaders.ALLOW, HttpHeaderValue.of("GET"));
        } catch (Exception e) {
            if (settingsController.isDebug()) {
                e.printStackTrace();
            }
            return HttpResponse.ofCode(500);
        }
    }

    private @NotNull Promisable<HttpResponse> postUpload(@NotNull HttpRequest httpRequest) {
        try {
            return generalEndpointsController.postUpload(httpRequest);
        } catch (Exception e) {
            if (settingsController.isDebug()) {
                e.printStackTrace();
            }
            return HttpResponse.ofCode(500);
        }
    }

    private @NotNull Promisable<HttpResponse> optionsUpload(@NotNull HttpRequest httpRequest) {
        try {
            return HttpResponse.ok200().withHeader(HttpHeaders.ALLOW, HttpHeaderValue.of("POST"));
        } catch (Exception e) {
            if (settingsController.isDebug()) {
                e.printStackTrace();
            }
            return HttpResponse.ofCode(500);
        }
    }

    private @NotNull Promisable<HttpResponse> getSearch(@NotNull HttpRequest httpRequest) {
        try {
            String query = httpRequest.getQueryParameter("query");
            MediaTypeEnum mediaType = MediaTypeEnum.valueOf(Objects.requireNonNull(httpRequest.getQueryParameter("type")).toUpperCase(Locale.ROOT));
            return switch (mediaType) {
                case ALBUM -> generalEndpointsController.getSearch(albumController, query, httpRequest);
                case ARTIST -> generalEndpointsController.getSearch(artistController, query, httpRequest);
                case BOOK -> generalEndpointsController.getSearch(bookController, query, httpRequest);
                case CHARACTER -> generalEndpointsController.getSearch(characterController, query, httpRequest);
                case COMPANY -> generalEndpointsController.getSearch(companyController, query, httpRequest);
                case GAME -> generalEndpointsController.getSearch(gameController, query, httpRequest);
                case GAME_ENGINE -> generalEndpointsController.getSearch(gameEngineController, query, httpRequest);
                case GAME_PLATFORM_RELEASE -> generalEndpointsController.getSearch(gamePlatformReleaseController, query, httpRequest);
                case GAME_SERIES -> generalEndpointsController.getSearch(gameSeriesController, query, httpRequest);
                case IMAGE -> generalEndpointsController.getSearch(imageController, query, httpRequest);
                case LOCATION -> generalEndpointsController.getSearch(locationController, query, httpRequest);
                case LYRICS -> generalEndpointsController.getSearch(lyricsController, query, httpRequest);
                case MOVIE -> generalEndpointsController.getSearch(movieController, query, httpRequest);
                case PERSON -> generalEndpointsController.getSearch(personController, query, httpRequest);
                case RATING -> generalEndpointsController.getSearch(ratingController, query, httpRequest);
                case SEASON -> generalEndpointsController.getSearch(seasonController, query, httpRequest);
                case SONG -> generalEndpointsController.getSearch(songController, query, httpRequest);
                case SUBTITLE -> generalEndpointsController.getSearch(subtitleController, query, httpRequest);
                case TVSHOW -> generalEndpointsController.getSearch(tvShowController, query, httpRequest);
                case VIDEO -> generalEndpointsController.getSearch(videoController, query, httpRequest);
            };
        } catch (Exception e) {
            if (settingsController.isDebug()) {
                e.printStackTrace();
            }
            return HttpResponse.ofCode(500);
        }
    }

    private @NotNull Promisable<HttpResponse> optionsSearch(@NotNull HttpRequest httpRequest) {
        try {
            return HttpResponse.ok200().withHeader(HttpHeaders.ALLOW, HttpHeaderValue.of("GET"));
        } catch (Exception e) {
            if (settingsController.isDebug()) {
                e.printStackTrace();
            }
            return HttpResponse.ofCode(500);
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
            return HttpResponse.ofCode(500);
        }
    }

    private @NotNull Promisable<HttpResponse> optionsImageById(@NotNull HttpRequest httpRequest) {
        try {
            return HttpResponse.ok200().withHeader(HttpHeaders.ALLOW, HttpHeaderValue.of("GET"));
        } catch (Exception e) {
            if (settingsController.isDebug()) {
                e.printStackTrace();
            }
            return HttpResponse.ofCode(500);
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
        return HttpResponse.ofCode(500);
    }

    private @NotNull Promisable<HttpResponse> optionsVideoById(@NotNull HttpRequest httpRequest) {
        return HttpResponse.ok200().withHeader(HttpHeaders.ALLOW, HttpHeaderValue.of("GET"));
    }
}
