package tech.tresearchgroup.babygalago.view.endpoints.api;

import io.activej.http.*;
import io.activej.inject.annotation.Provides;
import io.activej.promise.Promisable;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import tech.tresearchgroup.babygalago.controller.SettingsController;
import tech.tresearchgroup.babygalago.controller.controllers.UserController;
import tech.tresearchgroup.babygalago.model.ExtendedUserEntity;
import tech.tresearchgroup.palila.controller.BasicController;

import java.nio.charset.Charset;
import java.util.Objects;

@AllArgsConstructor
public class UserEndpoints extends BasicController {
    private final UserController userController;
    private final SettingsController settingsController;

    @Provides
    public RoutingServlet servlet() {
        return RoutingServlet.create()
            .map(HttpMethod.GET, "/v1/user", this::getUsers)
            .map(HttpMethod.POST, "/v1/user", this::postUser)
            .map(HttpMethod.PUT, "/v1/user", this::putUser)
            .map(HttpMethod.OPTIONS, "/v1/user", this::optionsUsers)
            .map(HttpMethod.GET, "/v1/user/:userId", this::getUserById)
            .map(HttpMethod.DELETE, "/v1/user/:userId", this::deleteUserById)
            .map(HttpMethod.POST, "/v1/user/:userId", this::postUserById)
            .map(HttpMethod.PATCH, "/v1/user/:userId", this::patchUser)
            .map(HttpMethod.OPTIONS, "/v1/user/:userId", this::optionsUserById);
    }

    private @NotNull Promisable<HttpResponse> getUsers(@NotNull HttpRequest httpRequest) {
        try {
            int page = httpRequest.getQueryParameter("page") != null ? Integer.parseInt(Objects.requireNonNull(httpRequest.getQueryParameter("page"))) : 0;
            int pageSize = httpRequest.getQueryParameter("pageSize") != null ? Integer.parseInt(Objects.requireNonNull(httpRequest.getQueryParameter("pageSize"))) : 0;
            return okResponseCompressed(userController.readPaginatedAPIResponse(page, pageSize, httpRequest));
        } catch (Exception e) {
            if (settingsController.isDebug()) {
                e.printStackTrace();
            }
        }
        return HttpResponse.ofCode(500);
    }

    private @NotNull Promisable<HttpResponse> postUser(@NotNull HttpRequest httpRequest) {
        try {
            if (settingsController.isAllowRegistration()) {
                String data = httpRequest.loadBody().getResult().asString(Charset.defaultCharset());
                return okResponseCompressed(userController.createSecureAPIResponse(data, httpRequest));
            }
            return HttpResponse.ofCode(503);
        } catch (Exception e) {
            if (settingsController.isDebug()) {
                e.printStackTrace();
            }
        }
        return HttpResponse.ofCode(500);
    }

    private @NotNull Promisable<HttpResponse> putUser(@NotNull HttpRequest httpRequest) {
        try {
            if (settingsController.isAllowRegistration()) {
                String data = httpRequest.loadBody().getResult().asString(Charset.defaultCharset());
                return ok(userController.createSecureResponse(data, httpRequest) != null);
            }
            return HttpResponse.ofCode(503);
        } catch (Exception e) {
            if (settingsController.isDebug()) {
                e.printStackTrace();
            }
        }
        return HttpResponse.ofCode(500);
    }

    private @NotNull Promisable<HttpResponse> getUserById(@NotNull HttpRequest httpRequest) {
        try {
            int id = Integer.parseInt(Objects.requireNonNull(httpRequest.getPathParameter("userId")));
            return okResponseCompressed(userController.readSecureAPIResponse(id, httpRequest));
        } catch (Exception e) {
            if (settingsController.isDebug()) {
                e.printStackTrace();
            }
        }
        return HttpResponse.ofCode(500);
    }

    private @NotNull Promisable<HttpResponse> patchUser(@NotNull HttpRequest httpRequest) {
        try {
            String data = httpRequest.loadBody().getResult().asString(Charset.defaultCharset());
            Long id = Long.parseLong(httpRequest.getPathParameter("userId"));
            return ok(userController.update(id, data, httpRequest));
        } catch (Exception e) {
            if (settingsController.isDebug()) {
                e.printStackTrace();
            }
        }
        return HttpResponse.ofCode(500);
    }

    private @NotNull Promisable<HttpResponse> optionsUsers(@NotNull HttpRequest httpRequest) {
        return HttpResponse.ok200().withHeader(HttpHeaders.ALLOW, HttpHeaderValue.of("GET, POST, PUT, PATCH"));
    }

    private @NotNull Promisable<HttpResponse> deleteUserById(@NotNull HttpRequest httpRequest) {
        try {
            int id = Integer.parseInt(httpRequest.getPathParameter("userId"));
            return ok(userController.delete(id, httpRequest));
        } catch (Exception e) {
            if (settingsController.isDebug()) {
                e.printStackTrace();
            }
        }
        return HttpResponse.ofCode(500);
    }

    private @NotNull Promisable<HttpResponse> postUserById(@NotNull HttpRequest httpRequest) {
        try {
            Long id = Long.parseLong(httpRequest.getPathParameter("userId"));
            ExtendedUserEntity extendedUserEntity = null;
            //Todo get the entity from the form
            return userController.createUIResponse(extendedUserEntity, httpRequest);
        } catch (Exception e) {
            if (settingsController.isDebug()) {
                e.printStackTrace();
            }
        }
        return HttpResponse.ofCode(500);
    }

    private @NotNull Promisable<HttpResponse> optionsUserById(@NotNull HttpRequest httpRequest) {
        return HttpResponse.ok200().withHeader(HttpHeaders.ALLOW, HttpHeaderValue.of("GET, DELETE, POST"));
    }
}
