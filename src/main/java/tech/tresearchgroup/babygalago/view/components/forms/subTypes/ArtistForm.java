package tech.tresearchgroup.babygalago.view.components.forms.subTypes;

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
import tech.tresearchgroup.schemas.galago.entities.*;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

import static j2html.TagCreator.*;

@AllArgsConstructor
public class ArtistForm {
    private final SettingsController settingsController;
    private final NotificationController notificationController;

    public byte @NotNull [] render(boolean editable,
                                   boolean loggedIn,
                                   int size,
                                   String saveUrl,
                                   ExtendedUserEntity userEntity) throws SQLException {
        return render(editable, loggedIn, size, saveUrl, null, userEntity, null, null, null, null);
    }

    public byte @NotNull [] render(boolean editable,
                                   boolean loggedIn,
                                   int size,
                                   String saveUrl,
                                   ArtistEntity artistEntity,
                                   ExtendedUserEntity userEntity,
                                   FileEntity primaryImageFile,
                                   List<ImageEntity> otherImageList,
                                   List<PersonEntity> membersList,
                                   List<AlbumEntity> albumEntityList) throws SQLException {
        long id = 0;
        String name = null;
        String primaryImage = null;
        List<Card> images = null;
        List<Card> members = null;
        List<Card> albums = null;
        if (artistEntity != null) {
            id = artistEntity.getId();
            if (artistEntity.getName() != null) {
                name = artistEntity.getName();
            }
            if (primaryImageFile != null) {
                primaryImage = primaryImageFile.getPath();
            }
            if (otherImageList != null) {
                images = CardConverter.convertImages(otherImageList, "add");
            }
            if (membersList != null) {
                members = CardConverter.convertPeople(membersList, "add");
            }
            if (albumEntityList != null) {
                albums = CardConverter.convertAlbum(albumEntityList, "add");
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
                                a(" Edit").withClass("floatRight btn fas fa-edit").withHref("/edit/artist/" + id)
                            ),
                            br(),
                            br(),
                            EditableFieldComponent.render(editable, "Primary Image:", primaryImage, "primaryImage"),
                            br(),
                            EditableScrollingComponent.render(editable, "Other Images:", images, "/add/image", size),
                            br(),
                            EditableScrollingComponent.render(editable, "Members:", members, "/add/person", size),
                            br(),
                            EditableScrollingComponent.render(editable, "Albums:", albums, "/add/album", size),
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
