package tech.tresearchgroup.colobus.controller;

import io.activej.http.HttpRequest;
import io.activej.http.HttpResponse;
import lombok.AllArgsConstructor;
import tech.tresearchgroup.babygalago.controller.controllers.UserController;
import tech.tresearchgroup.babygalago.model.ExtendedUserEntity;
import tech.tresearchgroup.colobus.view.IndexPage;
import tech.tresearchgroup.palila.controller.BasicController;
import tech.tresearchgroup.palila.model.enums.PermissionGroupEnum;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

@AllArgsConstructor
public class IndexController extends BasicController {
    private final UserController userController;
    private final IndexPage indexPage;

    public HttpResponse index(HttpRequest httpRequest) throws IOException, SQLException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        PermissionGroupEnum permissionGroupEnum = PermissionGroupEnum.ALL;
        ExtendedUserEntity userEntity = (ExtendedUserEntity) getUser(httpRequest, userController);
        if (userEntity != null) {
            permissionGroupEnum = userEntity.getPermissionGroup();
        }
        return ok(indexPage.render(permissionGroupEnum));
    }
}
