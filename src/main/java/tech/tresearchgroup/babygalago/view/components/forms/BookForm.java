package tech.tresearchgroup.babygalago.view.components.forms;

import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import tech.tresearchgroup.babygalago.controller.CardConverter;
import tech.tresearchgroup.babygalago.controller.SettingsController;
import tech.tresearchgroup.babygalago.controller.controllers.NotificationController;
import tech.tresearchgroup.babygalago.controller.controllers.QueueController;
import tech.tresearchgroup.babygalago.model.ExtendedUserEntity;
import tech.tresearchgroup.babygalago.view.components.HeadComponent;
import tech.tresearchgroup.babygalago.view.components.SideBarComponent;
import tech.tresearchgroup.babygalago.view.components.TopBarComponent;
import tech.tresearchgroup.palila.controller.components.EditableFieldComponent;
import tech.tresearchgroup.palila.controller.components.EditableScrollingComponent;
import tech.tresearchgroup.palila.controller.components.EditableTitleComponent;
import tech.tresearchgroup.palila.model.Card;
import tech.tresearchgroup.schemas.galago.entities.BookEntity;
import tech.tresearchgroup.schemas.galago.entities.FileEntity;
import tech.tresearchgroup.schemas.galago.entities.ImageEntity;
import tech.tresearchgroup.schemas.galago.entities.PersonEntity;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

import static j2html.TagCreator.*;

@AllArgsConstructor
public class BookForm {
    private final SettingsController settingsController;
    private final NotificationController notificationsController;

    public byte @NotNull [] render(boolean editable,
                                   boolean loggedIn,
                                   int size,
                                   String saveUrl,
                                   ExtendedUserEntity userEntity) throws SQLException {
        return render(editable, loggedIn, size, saveUrl, null, userEntity, null, null, null);
    }

    public byte @NotNull [] render(boolean editable,
                                   boolean loggedIn,
                                   int size,
                                   String saveUrl,
                                   BookEntity bookEntity,
                                   ExtendedUserEntity userEntity,
                                   List<PersonEntity> authorList,
                                   List<ImageEntity> otherImageList,
                                   FileEntity primaryImageFile) throws SQLException {
        Long id = null;
        String title = null;
        String primaryImage = null;
        String description = null;
        List<Card> otherImages = null;
        List<Card> authors = null;

        if (bookEntity != null) {
            id = bookEntity.getId();
            title = bookEntity.getTitle();
            description = bookEntity.getDescription();
        }

        if (primaryImageFile != null) {
            primaryImage = primaryImageFile.getPath();
        }

        if (otherImageList != null) {
            otherImages = CardConverter.convertImages(otherImageList, "view");
        }

        if (authorList != null) {
            authors = CardConverter.convertPeople(authorList, "view");
        }

        return document(
            html(
                HeadComponent.render(settingsController.getServerName()),
                TopBarComponent.render(notificationsController.getNumberOfUnread(userEntity), QueueController.getQueueSize(), loggedIn, userEntity.getPermissionGroup()),
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
                            br(),
                            EditableTitleComponent.render(editable, title, "title"),
                            br(),
                            br(),
                            iff(!editable,
                                a(" Edit").withClass("floatRight btn fas fa-edit").withHref("/edit/book/" + id)
                            ),
                            br(),
                            br(),
                            EditableFieldComponent.render(editable, "Primary Image:", primaryImage, "primaryImage"),
                            br(),
                            EditableFieldComponent.render(editable, "Description:", description, "description"),
                            br(),
                            EditableScrollingComponent.render(editable, "Images:", otherImages, "/add/image", size),
                            br(),
                            EditableScrollingComponent.render(editable, "Authors:", authors, "/add/person", size),
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
