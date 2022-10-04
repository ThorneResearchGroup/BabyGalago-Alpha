package tech.tresearchgroup.babygalago.controller.controllers;

import com.google.gson.Gson;
import com.meilisearch.sdk.Client;
import com.zaxxer.hikari.HikariDataSource;
import io.activej.http.HttpRequest;
import io.activej.http.HttpResponse;
import io.activej.serializer.BinarySerializer;
import tech.tresearchgroup.babygalago.model.ExtendedUserEntity;
import tech.tresearchgroup.palila.controller.BasicUserController;
import tech.tresearchgroup.palila.model.enums.PermissionGroupEnum;
import tech.tresearchgroup.schemas.galago.entities.UserSettingsEntity;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserController extends BasicUserController {
    public UserController(HikariDataSource hikariDataSource,
                          Gson gson,
                          Client client,
                          BinarySerializer<ExtendedUserEntity> serializer,
                          int reindexSize,
                          Object sample) throws Exception {
        super(
            hikariDataSource,
            gson,
            client,
            ExtendedUserEntity.class,
            reindexSize,
            serializer,
            null,
            sample,
            PermissionGroupEnum.OPERATOR,
            PermissionGroupEnum.USER,
            PermissionGroupEnum.USER,
            PermissionGroupEnum.USER,
            PermissionGroupEnum.OPERATOR
        );
    }

    public ExtendedUserEntity getUserByUsernameAndPassword(String username, String password) throws SQLException, InvocationTargetException, IllegalAccessException, InstantiationException {
        Connection connection = hikariDataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM " + theClass.getSimpleName() + " WHERE username=? and password=?");
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);
        preparedStatement.execute();
        ResultSet resultSet = preparedStatement.getResultSet();
        connection.close();
        if (resultSet.next()) {
            ExtendedUserEntity extendedUserEntity = new ExtendedUserEntity();
            Object object = genericDAO.getFromResultSet(resultSet, extendedUserEntity);
            return (ExtendedUserEntity) object;
        }
        return null;
    }

    public ExtendedUserEntity getUserByUsername(String username) throws SQLException, InvocationTargetException, IllegalAccessException, InstantiationException {
        Connection connection = hikariDataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM " + theClass.getSimpleName() + " WHERE username=?");
        preparedStatement.setString(1, username);
        preparedStatement.execute();
        ResultSet resultSet = preparedStatement.getResultSet();
        connection.close();
        if (resultSet.next()) {
            return (ExtendedUserEntity) genericDAO.getFromResultSet(resultSet, ExtendedUserEntity.class);
        }
        return null;
    }

    public HttpResponse createUIResponse(ExtendedUserEntity extendedUserEntity, HttpRequest httpRequest) throws SQLException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        if (createSecureResponse(extendedUserEntity, httpRequest) != null) {
            return ok();
        }
        return error();
    }

    public UserSettingsEntity getUserSettings(Long id) {
        System.out.println("Reading: " + id);
        return null;
    }
}
