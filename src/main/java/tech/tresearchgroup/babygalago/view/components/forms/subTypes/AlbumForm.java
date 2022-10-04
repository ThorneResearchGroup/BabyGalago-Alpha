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
public class AlbumForm {
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
                                   AlbumEntity albumEntity,
                                   ExtendedUserEntity userEntity,
                                   FileEntity albumPosterFile,
                                   List<ImageEntity> otherImageList,
                                   List<ArtistEntity> artistList,
                                   List<SongEntity> songEntityList) throws SQLException {
        long id = 0;
        String name = null;
        String poster = null;
        String releaseDate = null;
        List<Card> otherImages = null;
        List<Card> artists = null;
        List<Card> songs = null;
        if (albumEntity != null) {
            id = albumEntity.getId();
            if (albumEntity.getName() != null) {
                name = albumEntity.getName();
            }
            if (albumPosterFile != null) {
                poster = albumPosterFile.getPath();
            }
            if (albumEntity.getReleaseDate() != null) {
                releaseDate = albumEntity.getReleaseDate();
            }
            if (otherImageList != null) {
                otherImages = CardConverter.convertImages(otherImageList, "add");
            }
            if (artistList != null) {
                artists = CardConverter.convertArtists(artistList, "add");
            }
            if (songEntityList != null) {
                songs = CardConverter.convertSongs(songEntityList, "add");
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
                            br(),
                            EditableTitleComponent.render(editable, name, "name"),
                            br(),
                            br(),
                            iff(!editable,
                                a(" Edit").withClass("floatRight btn fas fa-edit").withHref("/edit/album/" + id)
                            ),
                            br(),
                            br(),
                            EditableFieldComponent.render(editable, "Poster:", poster, "poster"),
                            br(),
                            EditableFieldComponent.render(editable, "Release Date:", releaseDate, "releaseDate"),
                            br(),
                            br(),
                            EditableScrollingComponent.render(editable, "Other images:", otherImages, "/add/image", size),
                            br(),
                            EditableScrollingComponent.render(editable, "Artists:", artists, "/add/artist", size),
                            br(),
                            EditableScrollingComponent.render(editable, "Songs:", songs, "/add/song", size),
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
