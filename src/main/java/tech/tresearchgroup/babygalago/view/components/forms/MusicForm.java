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
import tech.tresearchgroup.schemas.galago.entities.AlbumEntity;
import tech.tresearchgroup.schemas.galago.entities.FileEntity;
import tech.tresearchgroup.schemas.galago.entities.LyricsEntity;
import tech.tresearchgroup.schemas.galago.entities.SongEntity;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

import static j2html.TagCreator.*;

@AllArgsConstructor
public class MusicForm {
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
                                   SongEntity songEntity,
                                   ExtendedUserEntity userEntity,
                                   FileEntity fileEntity,
                                   List<LyricsEntity> lyricsEntityList,
                                   List<AlbumEntity> albumEntities) throws SQLException {
        long id = 0;
        String title = null;
        String file = null;
        String releaseDate = null;
        Long views = null;
        List<Card> lyrics = null;
        List<Card> albums = null;
        if (songEntity != null) {
            id = songEntity.getId();
            if (songEntity.getTitle() != null) {
                title = songEntity.getTitle();
            }
            if (fileEntity != null) {
                file = fileEntity.getPath();
            }
            if (songEntity.getReleaseDate() != null) {
                releaseDate = songEntity.getReleaseDate();
            }
            if (songEntity.getViews() != null) {
                views = songEntity.getViews();
            }
            if (lyricsEntityList != null) {
                lyrics = CardConverter.convertLyrics(lyricsEntityList, "view");
            }
            if (albumEntities != null) {
                albums = CardConverter.convertAlbum(albumEntities, "view");
            }
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
                            EditableTitleComponent.render(editable, title, "title"), br(),
                            br(),
                            iff(!editable,
                                a(" Edit").withClass("floatRight btn fas fa-edit").withHref("/edit/music/" + id)
                            ),
                            br(),
                            br(),
                            EditableFieldComponent.render(editable, "Release Date:", releaseDate, "releaseDate"),
                            br(),
                            EditableFieldComponent.render(editable, "File:", file, "file"),
                            br(),
                            EditableFieldComponent.render(editable, "Views:", String.valueOf(views), "views"),
                            br(),
                            br(),
                            EditableScrollingComponent.render(editable, "Lyrics:", lyrics, "/add/lyrics", size),
                            br(),
                            br(),
                            EditableScrollingComponent.render(editable, "Albums:", albums, "/add/album", size),
                            br(),
                            br(),
                            button("Save").withClass("floatRight")
                        )
                            .withAction(saveUrl)
                            .withMethod("POST")
                    ).withClass("body")
                )
            )
        ).getBytes();
    }
}
