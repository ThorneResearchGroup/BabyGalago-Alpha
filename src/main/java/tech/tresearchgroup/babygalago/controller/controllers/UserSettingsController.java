package tech.tresearchgroup.babygalago.controller.controllers;

import com.google.gson.Gson;
import com.meilisearch.sdk.Client;
import com.zaxxer.hikari.HikariDataSource;
import io.activej.http.HttpRequest;
import io.activej.http.HttpResponse;
import io.activej.serializer.BinarySerializer;
import tech.tresearchgroup.palila.controller.GenericController;
import tech.tresearchgroup.palila.model.enums.PermissionGroupEnum;
import tech.tresearchgroup.schemas.galago.entities.UserSettingsEntity;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public class UserSettingsController extends GenericController {
    public UserSettingsController(HikariDataSource hikariDataSource,
                                  Gson gson,
                                  Client client,
                                  BinarySerializer<UserSettingsEntity> serializer,
                                  int reindexSize,
                                  Object object,
                                  UserController userController) throws Exception {
        super(
            hikariDataSource,
            gson,
            client,
            UserSettingsEntity.class,
            serializer,
            reindexSize,
            null,
            object,
            PermissionGroupEnum.USER,
            PermissionGroupEnum.USER,
            PermissionGroupEnum.USER,
            PermissionGroupEnum.USER,
            PermissionGroupEnum.USER,
            userController
        );
    }

    public HttpResponse read(HttpRequest httpRequest) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException {
        //Todo load user settings for UI
        return null;
    }

    public HttpResponse create(UserSettingsEntity userSettingsEntity, HttpRequest httpRequest) {
        //Todo create settings for the specified user and display form
        return null;
    }
}
