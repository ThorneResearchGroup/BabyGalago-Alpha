package tech.tresearchgroup.babygalago.view.endpoints.api;

import io.activej.http.*;
import io.activej.inject.annotation.Provides;
import io.activej.promise.Promisable;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import tech.tresearchgroup.babygalago.controller.SettingsController;
import tech.tresearchgroup.babygalago.controller.controllers.NewsArticleController;
import tech.tresearchgroup.palila.controller.BasicController;

import java.nio.charset.Charset;
import java.util.Objects;

@AllArgsConstructor
public class NewsEndpoints extends BasicController {
    private final NewsArticleController newsArticleController;
    private final SettingsController settingsController;

    @Provides
    public RoutingServlet servlet() {
        return RoutingServlet.create()
            .map(HttpMethod.PUT, "/v1/news", this::putNews)
            .map(HttpMethod.POST, "/v1/news", this::postNews)
            .map(HttpMethod.GET, "/v1/news", this::getNews)
            .map(HttpMethod.OPTIONS, "/v1/news", this::optionsNews)
            .map(HttpMethod.GET, "/v1/news/:newsId", this::getNewsById)
            .map(HttpMethod.DELETE, "/v1/news/:newsId", this::deleteNewsById)
            .map(HttpMethod.PATCH, "/v1/news/:newsId", this::patchNews)
            .map(HttpMethod.OPTIONS, "/v1/news/:newsId", this::optionsNewsById);
    }

    private @NotNull Promisable<HttpResponse> putNews(@NotNull HttpRequest httpRequest) {
        try {
            String data = httpRequest.loadBody().getResult().asString(Charset.defaultCharset());
            return ok(newsArticleController.createSecureAPIResponse(data, httpRequest) != null);
        } catch (Exception e) {
            if (settingsController.isDebug()) {
                e.printStackTrace();
            }
        }
        return HttpResponse.ofCode(500);
    }

    private @NotNull Promisable<HttpResponse> postNews(@NotNull HttpRequest httpRequest) {
        try {
            String data = httpRequest.loadBody().getResult().asString(Charset.defaultCharset());
            return okResponseCompressed(newsArticleController.createSecureAPIResponse(data, httpRequest));
        } catch (Exception e) {
            if (settingsController.isDebug()) {
                e.printStackTrace();
            }
        }
        return HttpResponse.ofCode(500);
    }

    private @NotNull Promisable<HttpResponse> getNews(@NotNull HttpRequest httpRequest) {
        try {
            int page = httpRequest.getQueryParameter("page") != null ? Integer.parseInt(Objects.requireNonNull(httpRequest.getQueryParameter("page"))) : 0;
            int pageSize = httpRequest.getQueryParameter("pageSize") != null ? Integer.parseInt(Objects.requireNonNull(httpRequest.getQueryParameter("pageSize"))) : 0;
            return okResponseCompressed(newsArticleController.readPaginatedAPIResponse(page, pageSize, httpRequest));
        } catch (Exception e) {
            if (settingsController.isDebug()) {
                e.printStackTrace();
            }
        }
        return HttpResponse.ofCode(500);
    }

    private @NotNull Promisable<HttpResponse> patchNews(@NotNull HttpRequest httpRequest) {
        try {
            String data = httpRequest.loadBody().getResult().asString(Charset.defaultCharset());
            Long id = Long.valueOf(httpRequest.getPathParameter("newsId"));
            return ok(newsArticleController.update(id, data, httpRequest));
        } catch (Exception e) {
            if (settingsController.isDebug()) {
                e.printStackTrace();
            }
        }
        return HttpResponse.ofCode(500);
    }

    private @NotNull Promisable<HttpResponse> optionsNews(@NotNull HttpRequest httpRequest) {
        return HttpResponse.ok200().withHeader(HttpHeaders.ALLOW, HttpHeaderValue.of("PUT, POST, PATCH, GET"));
    }

    private @NotNull Promisable<HttpResponse> getNewsById(@NotNull HttpRequest httpRequest) {
        try {
            Long newsId = Long.parseLong(httpRequest.getPathParameter("newsId"));
            return okResponseCompressed(newsArticleController.readSecureAPIResponse(newsId, httpRequest));
        } catch (Exception e) {
            if (settingsController.isDebug()) {
                e.printStackTrace();
            }
        }
        return HttpResponse.ofCode(500);
    }

    private @NotNull Promisable<HttpResponse> deleteNewsById(@NotNull HttpRequest httpRequest) {
        try {
            int newsId = Integer.parseInt(httpRequest.getPathParameter("newsId"));
            return ok(newsArticleController.delete(newsId, httpRequest));
        } catch (Exception e) {
            if (settingsController.isDebug()) {
                e.printStackTrace();
            }
        }
        return HttpResponse.ofCode(500);
    }

    private @NotNull Promisable<HttpResponse> optionsNewsById(@NotNull HttpRequest httpRequest) {
        return HttpResponse.ok200().withHeader(HttpHeaders.ALLOW, HttpHeaderValue.of("GET, DELETE"));
    }
}
