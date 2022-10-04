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
import tech.tresearchgroup.schemas.galago.enums.GameContentWarningEnum;
import tech.tresearchgroup.schemas.galago.enums.GameGenreEnum;
import tech.tresearchgroup.schemas.galago.enums.GameModeEnum;
import tech.tresearchgroup.schemas.galago.enums.GamePlatformEnum;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

import static j2html.TagCreator.*;

@AllArgsConstructor
public class GameForm {
    private final SettingsController settingsController;
    private final NotificationController notificationsController;

    public byte @NotNull [] render(boolean editable,
                                   boolean loggedIn,
                                   int size,
                                   String saveUrl,
                                   ExtendedUserEntity userEntity) throws SQLException {
        return render(editable, loggedIn, size, saveUrl, null, userEntity, null, null, null, null, null, null, null, null, null, null, null, null);
    }

    public byte @NotNull [] render(boolean editable,
                                   boolean loggedIn,
                                   int size,
                                   String saveUrl,
                                   GameEntity gameEntity,
                                   ExtendedUserEntity userEntity,
                                   FileEntity posterImageFile,
                                   VideoEntity trailerVideo,
                                   List<GameGenreEnum> genreList,
                                   List<GameContentWarningEnum> contentWarningList,
                                   List<GamePlatformEnum> platformList,
                                   List<GameModeEnum> gameModeList,
                                   List<GameSeriesEntity> gameSeriesList,
                                   List<GameEngineEntity> gameEngineList,
                                   List<CompanyEntity> developersList,
                                   List<CompanyEntity> publisherList,
                                   List<ImageEntity> otherImageList,
                                   List<VideoEntity> otherVideosList) throws SQLException {
        String title = null;
        String description = null;
        String primaryImage = null;
        String trailer = null;
        String genres = null;
        String esrbRating = null;
        String contentWarnings = null;
        String platforms = null;
        String gameModes = null;
        String gameSeries = null;
        String gameEngine = null;
        String gamePlayerPerspective = null;
        String gameUserRating = null;
        String inAppPurchases = "false";
        String microTransactions = "false";
        String expectedCompletionTime = null;
        List<Card> developers = null;
        List<Card> publishers = null;
        List<Card> otherImages = null;
        List<Card> otherVideos = null;
        long id = 0;
        if (gameEntity != null) {
            id = gameEntity.getId();
            if (gameEntity.getTitle() != null) {
                title = gameEntity.getTitle();
            }
            if (gameEntity.getDescription() != null) {
                description = gameEntity.getDescription();
            }
            if (posterImageFile != null) {
                primaryImage = posterImageFile.getPath();
            }
            if (trailerVideo != null) {
                trailer = trailerVideo.getFilePath();
            }
            if (genreList != null) {
                genres = genreList.toString();
            }
            if (gameEntity.getEsrbRating() != null) {
                esrbRating = gameEntity.getEsrbRating().toString();
            }
            if (contentWarningList != null) {
                contentWarnings = contentWarningList.toString();
            }
            if (platformList != null) {
                platforms = platformList.toString();
            }
            if (gameModeList != null) {
                gameModes = gameModeList.toString();
            }
            if (gameSeriesList != null) {
                gameSeries = gameSeriesList.toString();
            }
            if (gameEngineList != null) {
                gameEngine = gameEngineList.toString();
            }
            if (gameEntity.getGamePlayerPerspective() != null) {
                gamePlayerPerspective = gameEntity.getGamePlayerPerspective().toString();
            }
            gameUserRating = String.valueOf(gameEntity.getGameUserRating());
            if (gameEntity.isInAppPurchases()) {
                inAppPurchases = "true";
            }
            if (gameEntity.isMicroTransactions()) {
                microTransactions = "true";
            }
            expectedCompletionTime = String.valueOf(gameEntity.getExpectedCompletionTime());
            if (developersList != null) {
                developers = CardConverter.convertCompany(developersList, "add");
            }
            if (publisherList != null) {
                publishers = CardConverter.convertCompany(publisherList, "add");
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
                                a(" Edit").withClass("floatRight btn fas fa-edit").withHref("/edit/game/" + id)
                            ),
                            br(),
                            br(),
                            EditableFieldComponent.render(editable, "Description:", description, "description"),
                            br(),
                            EditableFieldComponent.render(editable, "Primary Image:", primaryImage, "primaryImage"),
                            br(),
                            EditableFieldComponent.render(editable, "Trailer:", trailer, "trailer"),
                            br(),
                            EditableFieldComponent.render(editable, "Genres:", genres, "genres"),
                            br(),
                            EditableFieldComponent.render(editable, "ESRB Rating:", esrbRating, "esrbRating"),
                            br(),
                            EditableFieldComponent.render(editable, "Content Warnings:", contentWarnings, "contentWarnings"),
                            br(),
                            EditableFieldComponent.render(editable, "Platforms:", platforms, "platforms"),
                            br(),
                            EditableFieldComponent.render(editable, "Game Modes:", gameModes, "gameModes"),
                            br(),
                            EditableFieldComponent.render(editable, "Game Series:", gameSeries, "gameSeries"),
                            br(),
                            EditableFieldComponent.render(editable, "Game Engine:", gameEngine, "gameEngine"),
                            br(),
                            EditableFieldComponent.render(editable, "Player Perspective:", gamePlayerPerspective, "gamePlayerPerspective"),
                            br(),
                            EditableFieldComponent.render(editable, "User Ratings:", gameUserRating, "gameUserRating"),
                            br(),
                            EditableFieldComponent.render(editable, "In App Purchases:", inAppPurchases, "inAppPurchases"),
                            br(),
                            EditableFieldComponent.render(editable, "Micro Transactions:", microTransactions, "microTransactions"),
                            br(),
                            EditableFieldComponent.render(editable, "Expected Completion Time:", expectedCompletionTime, "expectedCompletionTime"),
                            br(),
                            br(),
                            EditableScrollingComponent.render(editable, "Developers:", developers, "/add/company", size),
                            br(),
                            EditableScrollingComponent.render(editable, "Publishers:", publishers, "/add/company", size),
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
