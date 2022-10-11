package tech.tresearchgroup.babygalago.view.endpoints.api;

import io.activej.http.*;
import io.activej.inject.annotation.Provides;
import io.activej.promise.Promisable;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import tech.tresearchgroup.babygalago.controller.SettingsController;
import tech.tresearchgroup.babygalago.controller.endpoints.api.SettingsEndpointsController;
import tech.tresearchgroup.palila.controller.HttpResponses;

@AllArgsConstructor
public class SettingsEndpoints extends HttpResponses {
    private final SettingsEndpointsController settingsEndpointsController;
    private final SettingsController settingsController;

    @Provides
    public RoutingServlet servlet() {
        return RoutingServlet.create()
            .map(HttpMethod.GET, "/v1/settings", this::getSettings)
            .map(HttpMethod.PATCH, "/v1/settings", this::patchSettings)
            .map(HttpMethod.OPTIONS, "/v1/settings", this::optionsSettings)
            .map(HttpMethod.GET, "/v1/user/settings", this::getUserSettings)
            .map(HttpMethod.POST, "/v1/user/settings", this::createUserSettings)
            .map(HttpMethod.PATCH, "/v1/user/settings", this::patchUserSettings)
            .map(HttpMethod.DELETE, "/v1/user/settings", this::deleteUserSettings)
            .map(HttpMethod.OPTIONS, "/v1/user/settings", this::optionsSettingsById);
    }

    private @NotNull Promisable<HttpResponse> getSettings(@NotNull HttpRequest httpRequest) {
        try {
            return settingsEndpointsController.getSettings(httpRequest);
        } catch (Exception e) {
            if (settingsController.isDebug()) {
                e.printStackTrace();
            }
        }
        return error();
    }

    private @NotNull Promisable<HttpResponse> patchSettings(@NotNull HttpRequest httpRequest) {
        return settingsEndpointsController.patchSettings(httpRequest);
    }

    private @NotNull Promisable<HttpResponse> optionsSettings(@NotNull HttpRequest httpRequest) {
        return HttpResponse.ok200().withHeader(HttpHeaders.ALLOW, HttpHeaderValue.of("GET, PATCH"));
    }

    private @NotNull Promisable<HttpResponse> getUserSettings(@NotNull HttpRequest httpRequest) {
        try {
            return settingsEndpointsController.getUserSettings(httpRequest);
        } catch (Exception e) {
            if (settingsController.isDebug()) {
                e.printStackTrace();
            }
        }
        return error();
    }

    private @NotNull Promisable<HttpResponse> createUserSettings(@NotNull HttpRequest httpRequest) {
        try {
            return settingsEndpointsController.createUserSettings(httpRequest);
        } catch (Exception e) {
            if (settingsController.isDebug()) {
                e.printStackTrace();
            }
        }
        return error();
    }

    private @NotNull Promisable<HttpResponse> patchUserSettings(@NotNull HttpRequest httpRequest) {
        try {
            return settingsEndpointsController.patchUserSettings(httpRequest);
        } catch (Exception e) {
            if (settingsController.isDebug()) {
                e.printStackTrace();
            }
        }
        return error();
    }

    private @NotNull Promisable<HttpResponse> deleteUserSettings(@NotNull HttpRequest httpRequest) {
        try {
            return settingsEndpointsController.deleteUserSettings(httpRequest);
        } catch (Exception e) {
            if (settingsController.isDebug()) {
                e.printStackTrace();
            }
        }
        return error();
    }

    private @NotNull Promisable<HttpResponse> optionsSettingsById(@NotNull HttpRequest httpRequest) {
        return HttpResponse.ok200().withHeader(HttpHeaders.ALLOW, HttpHeaderValue.of("GET, POST, PATCH, DELETE"));
    }
}
