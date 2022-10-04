package tech.tresearchgroup.babygalago.controller.controllers;

import com.google.gson.Gson;
import com.meilisearch.sdk.Client;
import com.zaxxer.hikari.HikariDataSource;
import io.activej.serializer.BinarySerializer;
import tech.tresearchgroup.palila.controller.GenericController;
import tech.tresearchgroup.palila.model.enums.PermissionGroupEnum;
import tech.tresearchgroup.schemas.galago.entities.PersonEntity;

public class PersonController extends GenericController {
    public PersonController(HikariDataSource hikariDataSource,
                            Gson gson,
                            Client client,
                            BinarySerializer<PersonEntity> serializer,
                            int reindexSize,
                            Object sample,
                            UserController userController) throws Exception {
        super(
            hikariDataSource,
            gson,
            client,
            PersonEntity.class,
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
