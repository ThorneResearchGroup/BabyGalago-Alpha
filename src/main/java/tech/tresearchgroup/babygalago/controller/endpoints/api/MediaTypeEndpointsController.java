package tech.tresearchgroup.babygalago.controller.endpoints.api;

import io.activej.http.HttpRequest;
import io.activej.http.HttpResponse;
import io.activej.promise.Promisable;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import tech.tresearchgroup.babygalago.controller.SettingsController;
import tech.tresearchgroup.palila.controller.BasicController;
import tech.tresearchgroup.palila.controller.GenericController;
import tech.tresearchgroup.palila.controller.ReflectionMethods;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.Charset;
import java.sql.SQLException;
import java.util.Map;
import java.util.Objects;

@AllArgsConstructor
public class MediaTypeEndpointsController extends BasicController {
    private final Map<String, GenericController> controllers;
    private final SettingsController settingsController;

    public Promisable<HttpResponse> getMediaRatings(HttpRequest httpRequest) {
        return notImplemented();
    }

    public @NotNull Promisable<HttpResponse> deleteMediaById(HttpRequest httpRequest) throws Exception {
        long mediaId = Long.parseLong(httpRequest.getPathParameter("mediaId"));
        String mediaType = httpRequest.getPathParameter("mediaType");
        Class className = ReflectionMethods.findClass(mediaType, settingsController.getEntityPackage());
        GenericController genericController = getController(className, controllers);
        if (genericController != null) {
            return ok(genericController.delete(mediaId, httpRequest));
        }
        return error();
    }

    public @NotNull Promisable<HttpResponse> patchMediaById(HttpRequest httpRequest) throws Exception {
        String data = httpRequest.loadBody().getResult().asString(Charset.defaultCharset());
        long id = Long.parseLong(httpRequest.getPathParameter("mediaId"));
        String mediaType = httpRequest.getPathParameter("mediaType");
        Class className = ReflectionMethods.findClass(mediaType, settingsController.getEntityPackage());
        GenericController genericController = getController(className, controllers);
        if (genericController != null) {
            return ok(genericController.update(id, data, httpRequest));
        }
        return error();
    }

    public @NotNull Promisable<HttpResponse> getMediaById(HttpRequest httpRequest) throws SQLException, IOException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        long mediaId = Long.parseLong(httpRequest.getPathParameter("mediaId"));
        String mediaType = httpRequest.getPathParameter("mediaType");
        Class className = ReflectionMethods.findClass(mediaType, settingsController.getEntityPackage());
        if (className == null) {
            return notFound();
        }
        GenericController genericController = getController(className, controllers);
        if (genericController != null) {
            byte[] data = genericController.readSecureAPIResponse(mediaId, httpRequest);
            if (data == null) {
                return notFound();
            }
            return okResponseCompressed(data);
        }
        return error();
    }

    public @NotNull Promisable<HttpResponse> search(HttpRequest httpRequest) throws SQLException, IOException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException, ClassNotFoundException {
        String query = httpRequest.getQueryParameter("q");
        String mediaType = httpRequest.getPathParameter("mediaType");
        Class theClass = ReflectionMethods.findClass(mediaType, settingsController.getEntityPackage());
        GenericController genericController = getController(theClass, controllers);
        if (genericController != null) {
            return okResponseCompressed(genericController.searchAPIResponse(query, "*", httpRequest));
        }
        return error();
    }

    public @NotNull HttpResponse putReindex(HttpRequest httpRequest) throws SQLException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException, ClassNotFoundException {
        String mediaType = httpRequest.getPathParameter("mediaType");
        Class theClass = ReflectionMethods.findClass(mediaType, settingsController.getEntityPackage());
        GenericController genericController = getController(theClass, controllers);
        if (genericController != null) {
            return ok(genericController.reindex(httpRequest));
        }
        return error();
    }

    public Promisable<HttpResponse> putDeleteIndex(HttpRequest httpRequest) throws Exception {
        String mediaType = httpRequest.getPathParameter("mediaType");
        Class theClass = ReflectionMethods.findClass(mediaType, settingsController.getEntityPackage());
        GenericController genericController = getController(theClass, controllers);
        if (genericController != null) {
            return genericController.deleteAllIndexes(httpRequest);
        }
        return error();
    }

    public Promisable<HttpResponse> getMediaSample(HttpRequest httpRequest) throws SQLException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException, IOException, ClassNotFoundException {
        String mediaType = httpRequest.getPathParameter("mediaType");
        Class theClass = ReflectionMethods.findClass(mediaType, settingsController.getEntityPackage());
        GenericController genericController = getController(theClass, controllers);
        if (genericController != null) {
            return ok(genericController.getSample(httpRequest));
        }
        return error();
    }

    public Promisable<HttpResponse> putMedia(HttpRequest httpRequest) throws Exception {
        String data = httpRequest.loadBody().getResult().asString(Charset.defaultCharset());
        String mediaType = httpRequest.getPathParameter("mediaType");
        Class theClass = ReflectionMethods.findClass(mediaType, settingsController.getEntityPackage());
        GenericController genericController = getController(theClass, controllers);
        if (genericController != null) {
            return ok(genericController.createSecureResponse(data, httpRequest) != null);
        }
        return error();
    }

    public Promisable<HttpResponse> postMedia(HttpRequest httpRequest) throws Exception {
        String data = httpRequest.loadBody().getResult().asString(Charset.defaultCharset());
        String mediaType = httpRequest.getPathParameter("mediaType");
        Class theClass = ReflectionMethods.findClass(mediaType, settingsController.getEntityPackage());
        GenericController genericController = getController(theClass, controllers);
        if (genericController != null) {
            return ok(genericController.createSecureAPIResponse(data, httpRequest));
        }
        return error();
    }

    public Promisable<HttpResponse> getMedia(HttpRequest httpRequest) throws SQLException, IOException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException, ClassNotFoundException {
        int page = httpRequest.getQueryParameter("page") != null ? Integer.parseInt(Objects.requireNonNull(httpRequest.getQueryParameter("page"))) : 0;
        int pageSize = httpRequest.getQueryParameter("pageSize") != null ? Integer.parseInt(Objects.requireNonNull(httpRequest.getQueryParameter("pageSize"))) : 0;
        String mediaType = httpRequest.getPathParameter("mediaType");
        Class theClass = ReflectionMethods.findClass(mediaType, settingsController.getEntityPackage());
        if (theClass != null) {
            GenericController genericController = getController(theClass, controllers);
            return okResponseCompressed(genericController.readPaginatedAPIResponse(page, pageSize, true, httpRequest));
        }
        return notFound();
    }
}
