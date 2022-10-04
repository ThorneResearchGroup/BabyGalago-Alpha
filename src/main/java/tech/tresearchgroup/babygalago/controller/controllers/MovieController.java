package tech.tresearchgroup.babygalago.controller.controllers;

import com.google.gson.Gson;
import com.meilisearch.sdk.Client;
import com.zaxxer.hikari.HikariDataSource;
import io.activej.serializer.BinarySerializer;
import tech.tresearchgroup.palila.controller.GenericController;
import tech.tresearchgroup.palila.model.enums.PermissionGroupEnum;
import tech.tresearchgroup.schemas.galago.entities.MovieEntity;

public class MovieController extends GenericController {
    public MovieController(HikariDataSource hikariDataSource,
                           Gson gson,
                           Client client,
                           BinarySerializer<MovieEntity> serializer,
                           int reindexSize,
                           Object sample,
                           UserController userController) throws Exception {
        super(
            hikariDataSource,
            gson,
            client,
            MovieEntity.class,
            serializer,
            reindexSize,
            "title",
            sample,
            PermissionGroupEnum.USER,
            PermissionGroupEnum.USER,
            PermissionGroupEnum.USER,
            PermissionGroupEnum.USER,
            PermissionGroupEnum.USER,
            userController
        );
    }
}
