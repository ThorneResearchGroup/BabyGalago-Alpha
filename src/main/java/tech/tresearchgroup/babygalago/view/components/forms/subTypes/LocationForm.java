package tech.tresearchgroup.babygalago.view.components.forms.subTypes;

import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import tech.tresearchgroup.babygalago.controller.SettingsController;
import tech.tresearchgroup.babygalago.controller.controllers.NotificationController;
import tech.tresearchgroup.babygalago.controller.controllers.QueueController;
import tech.tresearchgroup.babygalago.model.ExtendedUserEntity;
import tech.tresearchgroup.babygalago.view.components.HeadComponent;
import tech.tresearchgroup.babygalago.view.components.SideBarComponent;
import tech.tresearchgroup.babygalago.view.components.TopBarComponent;
import tech.tresearchgroup.palila.controller.components.EditableFieldComponent;
import tech.tresearchgroup.palila.controller.components.EditableTitleComponent;
import tech.tresearchgroup.schemas.galago.entities.LocationEntity;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

import static j2html.TagCreator.*;

@AllArgsConstructor
public class LocationForm {
    private final SettingsController settingsController;
    private final NotificationController notificationController;

    public byte @NotNull [] render(boolean editable,
                                   boolean loggedIn,
                                   String saveUrl,
                                   ExtendedUserEntity userEntity) throws SQLException {
        return render(editable, loggedIn, saveUrl, null, userEntity);
    }

    public byte @NotNull [] render(boolean editable,
                                   boolean loggedIn,
                                   String saveUrl,
                                   LocationEntity locationEntity,
                                   ExtendedUserEntity userEntity) throws SQLException {
        long id = 0;
        String name = null;
        String latitude = null;
        String longitude = null;
        if (locationEntity != null) {
            id = locationEntity.getId();
            if (locationEntity.getName() != null) {
                name = locationEntity.getName();
            }
            if (locationEntity.getLatitude() != null) {
                latitude = locationEntity.getLatitude();
            }
            if (locationEntity.getLongitude() != null) {
                longitude = locationEntity.getLongitude();
            }
        }
        return document(
            html(
                HeadComponent.render(settingsController.getServerName()),
                TopBarComponent.render(notificationController.getNumberOfUnread(userEntity), QueueController.getQueueSize(), loggedIn, userEntity.getPermissionGroup()),
                SideBarComponent.render(loggedIn,
                    settingsController.isMovieLibraryEnable(),
                    settingsController.isTvShowLibraryEnable(),
                    settingsController.isGameLibraryEnable(),
                    settingsController.isMusicLibraryEnable(),
                    settingsController.isBookLibraryEnable()),
                body(
                    div(
                        form(
                            input().withType("text").withValue(String.valueOf(id)).withName("id").isHidden(),
                            EditableTitleComponent.render(editable, name, "title"),
                            br(),
                            br(),
                            iff(!editable,
                                a(" Edit").withClass("floatRight btn fas fa-edit").withHref("/edit/location/" + id)
                            ),
                            br(),
                            br(),
                            EditableFieldComponent.render(editable, "Latitude:", latitude, "latitude"),
                            br(),
                            EditableFieldComponent.render(editable, "Longitude:", longitude, "longitude"),
                            br(),
                            br(),
                            iff(editable,
                                button("Save").withClass("floatRight")
                            )
                        )
                            .withAction(saveUrl)
                            .withMethod("POST")
                    ).withClass("body")
                )
            )
        ).getBytes();
    }
}
