package tech.tresearchgroup.babygalago.view.pages;

import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import tech.tresearchgroup.babygalago.controller.SettingsController;
import tech.tresearchgroup.babygalago.controller.controllers.NotificationController;
import tech.tresearchgroup.babygalago.controller.controllers.QueueController;
import tech.tresearchgroup.babygalago.model.ExtendedUserEntity;
import tech.tresearchgroup.babygalago.view.components.HeadComponent;
import tech.tresearchgroup.babygalago.view.components.SideBarComponent;
import tech.tresearchgroup.babygalago.view.components.TopBarComponent;
import tech.tresearchgroup.palila.controller.components.EditableScrollingComponent;
import tech.tresearchgroup.palila.model.Card;
import tech.tresearchgroup.palila.model.enums.PermissionGroupEnum;

import java.sql.SQLException;
import java.util.List;

import static j2html.TagCreator.*;

@AllArgsConstructor
public class SearchPage {
    private final SettingsController settingsController;
    private final NotificationController notificationController;

    public byte @NotNull [] render(boolean loggedIn,
                                   List<Card> movieCards,
                                   List<Card> tvShowCards,
                                   List<Card> gameCards,
                                   List<Card> musicCards,
                                   List<Card> bookCards,
                                   long timeTaken,
                                   int size,
                                   ExtendedUserEntity userEntity) throws SQLException {
        PermissionGroupEnum permissionGroupEnum = PermissionGroupEnum.ALL;
        if (userEntity != null) {
            permissionGroupEnum = userEntity.getPermissionGroup();
        }
        return document(
            html(
                HeadComponent.render(settingsController.getServerName()),
                TopBarComponent.render(notificationController.getNumberOfUnread(userEntity), QueueController.getQueueSize(), loggedIn, permissionGroupEnum),
                SideBarComponent.render(loggedIn,
                    settingsController.isMovieLibraryEnable(),
                    settingsController.isTvShowLibraryEnable(),
                    settingsController.isGameLibraryEnable(),
                    settingsController.isMusicLibraryEnable(),
                    settingsController.isBookLibraryEnable()),
                body(
                    div(
                        label("Search results for: SEARCH").withClass("overviewLabel"),
                        br(),
                        br(),
                        EditableScrollingComponent.render(false, "Movies", movieCards, "/add/movie", size),
                        br(),
                        EditableScrollingComponent.render(false, "TV Shows", tvShowCards, "/add/tvshow", size),
                        br(),
                        EditableScrollingComponent.render(false, "Games", gameCards, "/add/game", size),
                        br(),
                        EditableScrollingComponent.render(false, "Music", musicCards, "/add/music", size),
                        br(),
                        EditableScrollingComponent.render(false, "Books", bookCards, "/add/book", size),
                        label("Time taken: " + timeTaken + "ms").withClass("subLabel"),
                        br(),
                        br(),
                        br()
                    ).withClass("body")
                )
            )
        ).getBytes();
    }
}
