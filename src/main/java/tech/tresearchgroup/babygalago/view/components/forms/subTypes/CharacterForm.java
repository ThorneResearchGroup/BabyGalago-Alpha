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
import tech.tresearchgroup.schemas.galago.entities.CharacterEntity;
import tech.tresearchgroup.schemas.galago.entities.PersonEntity;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

import static j2html.TagCreator.*;

@AllArgsConstructor
public class CharacterForm {
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
                                   CharacterEntity characterEntity,
                                   ExtendedUserEntity userEntity,
                                   PersonEntity playedByEntity) throws SQLException {
        long id = 0;
        String title = null;
        String firstName = null;
        String middleName = null;
        String lastName = null;
        String birthday = null;
        String alias = null;
        String playedBy = null;
        if (characterEntity != null) {
            id = characterEntity.getId();
            if (characterEntity.getTitle() != null) {
                title = characterEntity.getTitle();
            }
            if (characterEntity.getFirstName() != null) {
                firstName = characterEntity.getFirstName();
            }
            if (characterEntity.getMiddleName() != null) {
                middleName = characterEntity.getMiddleName();
            }
            if (characterEntity.getLastName() != null) {
                lastName = characterEntity.getLastName();
            }
            if (characterEntity.getBirthday() != null) {
                birthday = characterEntity.getBirthday();
            }
            if (characterEntity.getAlias() != null) {
                alias = characterEntity.getAlias();
            }
            if (playedByEntity != null) {
                playedBy = String.valueOf(playedByEntity.getId());
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
                                a(" Edit").withClass("floatRight btn fas fa-edit").withHref("/edit/character/" + id)
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
                            EditableFieldComponent.render(editable, "Alias:", alias, "alias"),
                            br(),
                            EditableFieldComponent.render(editable, "Played By:", playedBy, "playedBy"),
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
