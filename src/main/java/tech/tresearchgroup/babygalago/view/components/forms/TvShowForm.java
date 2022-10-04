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
import tech.tresearchgroup.schemas.galago.entities.*;
import tech.tresearchgroup.schemas.galago.enums.TvShowGenreEnum;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

import static j2html.TagCreator.*;

@AllArgsConstructor
public class TvShowForm {
    private final SettingsController settingsController;
    private final NotificationController notificationsController;

    public byte @NotNull [] render(boolean editable,
                                   boolean loggedIn,
                                   int size,
                                   String saveUrl,
                                   ExtendedUserEntity userEntity) throws SQLException {
        return render(editable, loggedIn, size, saveUrl, null, userEntity, null, null, null, null, null, null);
    }

    public byte @NotNull [] render(boolean editable,
                                   boolean loggedIn,
                                   int size,
                                   String saveUrl,
                                   TvShowEntity tvShowEntity,
                                   ExtendedUserEntity userEntity,
                                   List<TvShowGenreEnum> genreList,
                                   FileEntity trailerFile,
                                   FileEntity primaryImageFile,
                                   List<SeasonEntity> seasonEntities,
                                   List<ImageEntity> otherImageList,
                                   List<VideoEntity> otherVideosList) throws SQLException {
        long id = 0;
        String title = null;
        String description = null;
        String showStatus = null;
        String firstAired = null;
        String airsOn = null;
        String runtime = null;
        String genres = null;
        String languages = null;
        String trailer = null;
        String primaryImage = null;
        List<Card> seasons = null;
        List<Card> otherImages = null;
        List<Card> otherVideos = null;
        if (tvShowEntity != null) {
            id = tvShowEntity.getId();
            if (tvShowEntity.getTitle() != null) {
                title = tvShowEntity.getTitle();
            }
            if (tvShowEntity.getDescription() != null) {
                description = tvShowEntity.getDescription();
            }
            if (tvShowEntity.getShowStatus() != null) {
                showStatus = tvShowEntity.getShowStatus().toString();
            }
            if (tvShowEntity.getFirstAired() != null) {
                firstAired = tvShowEntity.getFirstAired();
            }
            if (tvShowEntity.getAirsOn() != null) {
                airsOn = tvShowEntity.getAirsOn();
            }
            runtime = String.valueOf(tvShowEntity.getRuntime());
            if (genreList != null) {
                genres = genreList.toString();
            }
            if (tvShowEntity.getLanguages() != null) {
                languages = tvShowEntity.getLanguages();
            }
            if (trailerFile != null) {
                trailer = trailerFile.getPath();
            }
            if (primaryImageFile != null) {
                primaryImage = primaryImageFile.getPath();
            }
            if (seasonEntities != null) {
                seasons = CardConverter.convertSeasons(seasonEntities, "add");
            }
            if (otherImageList != null) {
                otherImages = CardConverter.convertImages(otherImageList, "add");
            }
            if (otherVideosList != null) {
                otherVideos = CardConverter.convertVideos(otherVideosList, "add");
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
                                a(" Edit").withClass("floatRight btn fas fa-edit").withHref("/edit/tvshow/" + id)
                            ),
                            br(),
                            br(),
                            EditableFieldComponent.render(editable, "Description:", description, "description"),
                            br(),
                            EditableFieldComponent.render(editable, "Show Stats:", showStatus, "showStatus"),
                            br(),
                            EditableFieldComponent.render(editable, "First Aired:", firstAired, "firstAired"),
                            br(),
                            EditableFieldComponent.render(editable, "Airs On:", airsOn, "airsOn"),
                            br(),
                            EditableFieldComponent.render(editable, "Run Time:", runtime, "runtime"),
                            br(),
                            EditableFieldComponent.render(editable, "Genres:", genres, "genres"),
                            br(),
                            EditableFieldComponent.render(editable, "Languages:", languages, "languages"),
                            br(),
                            EditableFieldComponent.render(editable, "Trailer:", trailer, "trailer"),
                            br(),
                            EditableFieldComponent.render(editable, "Primary Image:", primaryImage, "primaryImage"),
                            br(),
                            br(),
                            EditableScrollingComponent.render(editable, "Seasons:", seasons, "/add/season", size),
                            br(),
                            EditableScrollingComponent.render(editable, "Images:", otherImages, "/add/image", size),
                            br(),
                            EditableScrollingComponent.render(editable, "Videos:", otherVideos, "/add/video", size),
                            br(),
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
