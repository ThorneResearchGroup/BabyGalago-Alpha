package tech.tresearchgroup.babygalago.view.endpoints.api;

import com.google.gson.Gson;
import io.activej.bytebuf.ByteBuf;
import io.activej.http.*;
import io.activej.inject.annotation.Provides;
import io.activej.inject.module.AbstractModule;
import io.activej.promise.Promisable;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import tech.tresearchgroup.babygalago.controller.SettingsController;
import tech.tresearchgroup.babygalago.controller.endpoints.api.LoginEndpointsController;
import tech.tresearchgroup.babygalago.model.ExtendedUserEntity;

import java.nio.charset.Charset;

@AllArgsConstructor
public class LoginEndpoints extends AbstractModule {
    private final LoginEndpointsController loginEndpointsController;
    private final SettingsController settingsController;
    private final Gson gson;

    @Provides
    public RoutingServlet servlet() {
        return RoutingServlet.create()
            .map(HttpMethod.POST, "/v1/login", this::apiLogin)
            .map(HttpMethod.POST, "/login", this::uiLogin)
            .map(HttpMethod.GET, "/logout", this::logout);
    }

    private @NotNull Promisable<HttpResponse> apiLogin(@NotNull HttpRequest httpRequest) {
        try {
            String data = httpRequest.loadBody().getResult().asString(Charset.defaultCharset());
            ExtendedUserEntity jsonUser = gson.fromJson(data, ExtendedUserEntity.class);
            ExtendedUserEntity userEntity = loginEndpointsController.getUser(jsonUser.getUsername(), jsonUser.getPassword(), httpRequest);
            if (userEntity != null) {
                return HttpResponse.ok200().withBody(loginEndpointsController.login(userEntity));
            }
        } catch (Exception e) {
            if (settingsController.isDebug()) {
                e.printStackTrace();
            }
        }
        return HttpResponse.ofCode(500);
    }

    private @NotNull Promisable<HttpResponse> uiLogin(@NotNull HttpRequest httpRequest) {
        try {
            ByteBuf data = httpRequest.loadBody().getResult();
            if (data.canRead()) {
                String username = httpRequest.getPostParameter("username");
                String password = httpRequest.getPostParameter("password");
                ExtendedUserEntity userEntity = loginEndpointsController.getUser(username, password, httpRequest);
                if (userEntity != null) {
                    return HttpResponse.redirect301("/").withCookie(HttpCookie.of("authorization", userEntity.getApiKey()));
                }
            }
        } catch (Exception e) {
            if (settingsController.isDebug()) {
                e.printStackTrace();
            }
        }
        return HttpResponse.redirect301("/login?error");
    }

    private @NotNull Promisable<HttpResponse> logout(@NotNull HttpRequest httpRequest) {
        try {
            HttpCookie cookie = HttpCookie.of("authorization", "123");
            cookie.setMaxAge(0);
            return HttpResponse.redirect301("/login").withCookie(cookie);
        } catch (Exception e) {
            if (settingsController.isDebug()) {
                e.printStackTrace();
            }
        }
        return HttpResponse.ofCode(500);
    }
}
