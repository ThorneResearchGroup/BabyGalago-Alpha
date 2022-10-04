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
import tech.tresearchgroup.schemas.galago.entities.PersonEntity;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

import static j2html.TagCreator.*;

@AllArgsConstructor
public class PersonForm {
    private final SettingsController settingsController;
    private final NotificationController notificationController;

    public byte @NotNull [] render(boolean editable,
                                   boolean loggedIn,
                                   String saveUrl,
                                   ExtendedUserEntity userEntity) throws SQLException {
        return render(editable, loggedIn, saveUrl, null, userEntity, null);
    }

    public byte @NotNull [] render(boolean editable,
                                   boolean loggedIn,
                                   String saveUrl,
                                   PersonEntity personEntity,
                                   ExtendedUserEntity userEntity,
                                   LocationEntity birthPlaceLocation) throws SQLException {
        long id = 0;
        String title = null;
        String firstName = null;
        String middleName = null;
        String lastName = null;
        String birthday = null;
        String birthPlace = null;
        String alias = null;

        if (personEntity != null) {
            id = personEntity.getId();
            if (personEntity.getTitle() != null) {
                title = personEntity.getTitle();
            }
            if (personEntity.getMiddleName() != null) {
                middleName = personEntity.getMiddleName();
            }
            if (personEntity.getLastName() != null) {
                lastName = personEntity.getLastName();
            }
            if (personEntity.getBirthday() != null) {
                birthday = personEntity.getBirthday();
            }
            if (birthPlaceLocation != null) {
                birthPlace = birthPlaceLocation.getName();
            }
            if (personEntity.getAlias() != null) {
                alias = personEntity.getAlias();
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
                            EditableTitleComponent.render(editable, title, "title"),
                            br(),
                            br(),
                            iff(!editable,
                                a(" Edit").withClass("floatRight btn fas fa-edit").withHref("/edit/person/" + id)
                            ),
                            br(),
                            br(),
                            EditableFieldComponent.render(editable, "First Name:", firstName, "firstName"),
                            br(),
                            EditableFieldComponent.render(editable, "Middle Name:", middleName, "middleName"),
                            br(),
                            EditableFieldComponent.render(editable, "Last Name:", lastName, "lastName"),
                            br(),
                            EditableFieldComponent.render(editable, "Birthday:", birthday, "birthday"),
                            br(),
                            EditableFieldComponent.render(editable, "Birth Place:", birthPlace, "birthPlace"),
                            br(),
                            EditableFieldComponent.render(editable, "Alias:", alias, "alias"),
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
