package tech.tresearchgroup.babygalago.controller.endpoints.api;

import io.activej.http.HttpRequest;
import io.activej.http.HttpResponse;
import lombok.AllArgsConstructor;
import tech.tresearchgroup.babygalago.controller.controllers.UserController;
import tech.tresearchgroup.palila.controller.BasicController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@AllArgsConstructor
public class SettingsEndpointsController extends BasicController {
    private final UserController userController;

    public HttpResponse getSettings(HttpRequest httpRequest) throws IOException {
        return ok(Files.readAllBytes(Path.of("Settings.json")));
    }
}
