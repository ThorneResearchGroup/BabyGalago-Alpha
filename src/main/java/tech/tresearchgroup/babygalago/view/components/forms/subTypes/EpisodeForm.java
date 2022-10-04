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
public class EpisodeForm {
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
                                   EpisodeEntity episodeEntity,
                                   ExtendedUserEntity userEntity,
                                   FileEntity imageFile,
                                   List<LocationEntity> filmLocationList,
                                   List<ImageEntity> otherImagesList,
                                   List<VideoEntity> otherVideosList) throws SQLException {
        long id = 0;
        String title = null;
        String description = null;
        String poster = null;
        String firstAired = null;
        String runTime = null;
        String languages = null;
        List<Card> filmLocations = null;
        List<Card> otherVideos = null;
        List<Card> otherImages = null;
        if (episodeEntity != null) {
            id = episodeEntity.getId();
            if (episodeEntity.getTitle() != null) {
                title = episodeEntity.getTitle();
            }
            if (episodeEntity.getDescription() != null) {
                description = episodeEntity.getDescription();
            }
            if (imageFile != null) {
                poster = imageFile.getPath();
            }
            if (episodeEntity.getFirstAired() != null) {
                firstAired = episodeEntity.getFirstAired();
            }
            if (episodeEntity.getRuntime() != null) {
                runTime = String.valueOf(episodeEntity.getRuntime());
            }
            if (episodeEntity.getLanguages() != null) {
                languages = episodeEntity.getLanguages();
            }
            if (filmLocationList != null) {
                filmLocations = CardConverter.convertLocations(filmLocationList, "add");
            }
            if (otherImagesList != null) {
                otherImages = CardConverter.convertImages(otherImagesList, "add");
            }
            if (otherVideosList != null) {
                otherVideos = CardConverter.convertVideos(otherVideosList, "add");
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
                                a(" Edit").withClass("floatRight btn fas fa-edit").withHref("/edit/episode/" + id)
                            ),
                            br(),
                            br(),
                            EditableFieldComponent.render(editable, "Description:", description, "description"),
                            br(),
                            EditableFieldComponent.render(editable, "First Aired:", firstAired, "firstAired"),
                            br(),
                            EditableFieldComponent.render(editable, "Run Time:", runTime, "runTime"),
                            br(),
                            EditableFieldComponent.render(editable, "Languages:", languages, "languages"),
                            br(),
                            EditableFieldComponent.render(editable, "Poster:", poster, "poster"),
                            br(),
                            br(),
                            EditableScrollingComponent.render(editable, "Film locations:", filmLocations, "/add/location", size),
                            br(),
                            EditableScrollingComponent.render(editable, "Images:", otherImages, "/add/image", size),
                            br(),
                            EditableScrollingComponent.render(editable, "Videos:", otherVideos, "/add/video", size),
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
