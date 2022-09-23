package tech.tresearchgroup.babygalago.view.endpoints.api;

import io.activej.http.*;
import io.activej.inject.annotation.Provides;
import io.activej.promise.Promisable;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import tech.tresearchgroup.babygalago.controller.SettingsController;
import tech.tresearchgroup.babygalago.controller.controllers.NotificationController;
import tech.tresearchgroup.palila.controller.BasicController;

import java.nio.charset.Charset;
import java.util.Objects;

@AllArgsConstructor
public class NotificationsEndpoints extends BasicController {
    private final NotificationController notificationsController;
    private final SettingsController settingsController;

    @Provides
    public RoutingServlet servlet() {
        return RoutingServlet.create()
            .map(HttpMethod.PUT, "/v1/notifications", this::putNotification)
            .map(HttpMethod.POST, "/v1/notifications", this::postNotification)
            .map(HttpMethod.GET, "/v1/notifications", this::getNotifications)
            .map(HttpMethod.OPTIONS, "/v1/notifications", this::optionsNotifications)
            .map(HttpMethod.DELETE, "/v1/notifications/:notificationId", this::deleteNotificationById)
            .map(HttpMethod.GET, "/v1/notifications/:notificationId", this::getNotificationById)
            .map(HttpMethod.OPTIONS, "/v1/notifications/:notificationId", this::optionsNotificationsById);
    }

    private @NotNull Promisable<HttpResponse> putNotification(@NotNull HttpRequest httpRequest) {
        try {
            String data = httpRequest.loadBody().getResult().asString(Charset.defaultCharset());
            return okResponseCompressed(notificationsController.createSecureAPIResponse(data, httpRequest));
        } catch (Exception e) {
            if (settingsController.isDebug()) {
                e.printStackTrace();
            }
        }
        return HttpResponse.ofCode(500);
    }

    private @NotNull Promisable<HttpResponse> postNotification(@NotNull HttpRequest httpRequest) {
        try {
            String data = httpRequest.loadBody().getResult().asString(Charset.defaultCharset());
            return okResponseCompressed(notificationsController.createSecureAPIResponse(data, httpRequest));
        } catch (Exception e) {
            if (settingsController.isDebug()) {
                e.printStackTrace();
            }
        }
        return HttpResponse.ofCode(500);
    }

    private @NotNull Promisable<HttpResponse> getNotifications(@NotNull HttpRequest httpRequest) {
        try {
            int page = httpRequest.getQueryParameter("page") != null ? Integer.parseInt(Objects.requireNonNull(httpRequest.getQueryParameter("page"))) : 0;
            int pageSize = httpRequest.getQueryParameter("pageSize") != null ? Integer.parseInt(Objects.requireNonNull(httpRequest.getQueryParameter("pageSize"))) : 0;
            return okResponseCompressed(notificationsController.readPaginatedAPIResponse(page, pageSize, httpRequest));
        } catch (Exception e) {
            if (settingsController.isDebug()) {
                e.printStackTrace();
            }
        }
        return HttpResponse.ofCode(500);
    }

    private @NotNull Promisable<HttpResponse> optionsNotifications(@NotNull HttpRequest httpRequest) {
        return HttpResponse.ok200().withHeader(HttpHeaders.ALLOW, HttpHeaderValue.of("PUT, POST, DELETE"));
    }

    private @NotNull Promisable<HttpResponse> deleteNotificationById(@NotNull HttpRequest httpRequest) {
        try {
            int notificationId = Integer.parseInt(httpRequest.getPathParameter("notificationId"));
            return ok(notificationsController.delete(notificationId, httpRequest));
        } catch (Exception e) {
            if (settingsController.isDebug()) {
                e.printStackTrace();
            }
        }
        return HttpResponse.ofCode(500);
    }

    private @NotNull Promisable<HttpResponse> getNotificationById(@NotNull HttpRequest httpRequest) {
        try {
            Long notificationId = Long.parseLong(httpRequest.getPathParameter("notificationId"));
            return okResponseCompressed(notificationsController.readSecureAPIResponse(notificationId, httpRequest));
        } catch (Exception e) {
            if (settingsController.isDebug()) {
                e.printStackTrace();
            }
        }
        return HttpResponse.ofCode(500);
    }

    private @NotNull Promisable<HttpResponse> optionsNotificationsById(@NotNull HttpRequest httpRequest) {
        return HttpResponse.ok200().withHeader(HttpHeaders.ALLOW, HttpHeaderValue.of("DELETE, GET"));
    }
}
