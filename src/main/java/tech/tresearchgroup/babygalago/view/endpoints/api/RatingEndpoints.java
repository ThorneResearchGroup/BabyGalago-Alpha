package tech.tresearchgroup.babygalago.view.endpoints.api;

import io.activej.http.*;
import io.activej.inject.annotation.Provides;
import io.activej.promise.Promisable;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import tech.tresearchgroup.babygalago.controller.SettingsController;
import tech.tresearchgroup.babygalago.controller.controllers.RatingController;
import tech.tresearchgroup.palila.controller.BasicController;

import java.nio.charset.Charset;

@AllArgsConstructor
public class RatingEndpoints extends BasicController {
    private final RatingController ratingController;
    private final SettingsController settingsController;

    @Provides
    public RoutingServlet servlet() {
        return RoutingServlet.create()
            .map(HttpMethod.GET, "/v1/ratings/:ratingId", this::getRating)
            .map(HttpMethod.PATCH, "/v1/ratings/:ratingId", this::patchRating)
            .map(HttpMethod.DELETE, "/v1/ratings/:ratingId", this::deleteRating)
            .map(HttpMethod.PUT, "/v1/ratings/:ratingId", this::putRating)
            .map(HttpMethod.POST, "/v1/ratings/:ratingId", this::postRating)
            .map(HttpMethod.OPTIONS, "/v1/ratings/:ratingId", this::optionsRatingById);
    }

    private @NotNull Promisable<HttpResponse> getRating(@NotNull HttpRequest httpRequest) {
        try {
            long ratingId = Long.parseLong(httpRequest.getPathParameter("ratingId"));
            byte[] data = ratingController.readSecureAPIResponse(ratingId, httpRequest);
            if(data != null) {
                return okResponseCompressed(data);
            }
            return notFound();
        } catch (Exception e) {
            if (settingsController.isDebug()) {
                e.printStackTrace();
            }
        }
        return error();
    }

    private @NotNull Promisable<HttpResponse> patchRating(@NotNull HttpRequest httpRequest) {
        try {
            String data = httpRequest.loadBody().getResult().asString(Charset.defaultCharset());
            long id = Long.parseLong(httpRequest.getPathParameter("ratingId"));
            return ok(ratingController.update(id, data, httpRequest));
        } catch (Exception e) {
            if (settingsController.isDebug()) {
                e.printStackTrace();
            }
        }
        return error();
    }

    private @NotNull Promisable<HttpResponse> deleteRating(@NotNull HttpRequest httpRequest) {
        try {
            int ratingId = Integer.parseInt(httpRequest.getPathParameter("ratingId"));
            return ok(ratingController.delete(ratingId, httpRequest));
        } catch (Exception e) {
            if (settingsController.isDebug()) {
                e.printStackTrace();
            }
        }
        return error();
    }

    private @NotNull Promisable<HttpResponse> putRating(@NotNull HttpRequest httpRequest) {
        try {
            String data = httpRequest.loadBody().getResult().asString(Charset.defaultCharset());
            return ok(ratingController.createSecureAPIResponse(data, httpRequest) != null);
        } catch (Exception e) {
            if (settingsController.isDebug()) {
                e.printStackTrace();
            }
        }
        return error();
    }

    private @NotNull Promisable<HttpResponse> postRating(@NotNull HttpRequest httpRequest) {
        try {
            String data = httpRequest.loadBody().getResult().asString(Charset.defaultCharset());
            return okResponseCompressed(ratingController.createSecureAPIResponse(data, httpRequest));
        } catch (Exception e) {
            if (settingsController.isDebug()) {
                return error(e);
            }
        }
        return error();
    }

    private @NotNull Promisable<HttpResponse> optionsRatingById(@NotNull HttpRequest httpRequest) {
        return HttpResponse.ok200().withHeader(HttpHeaders.ALLOW, HttpHeaderValue.of("GET, PATCH, DELETE, PUT, POST"));
    }
}
