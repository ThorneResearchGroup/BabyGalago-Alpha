package tech.tresearchgroup.babygalago.controller.endpoints.ui;

import io.activej.csp.file.ChannelFileWriter;
import io.activej.http.HttpRequest;
import io.activej.http.HttpResponse;
import io.activej.http.MultipartDecoder;
import io.activej.promise.Promisable;
import lombok.AllArgsConstructor;
import org.bouncycastle.crypto.generators.BCrypt;
import org.bouncycastle.util.encoders.Hex;
import org.jetbrains.annotations.NotNull;
import tech.tresearchgroup.babygalago.controller.CardConverter;
import tech.tresearchgroup.babygalago.controller.SettingsController;
import tech.tresearchgroup.babygalago.controller.controllers.*;
import tech.tresearchgroup.babygalago.model.ExtendedUserEntity;
import tech.tresearchgroup.babygalago.model.SettingsFileEntity;
import tech.tresearchgroup.babygalago.view.pages.*;
import tech.tresearchgroup.palila.controller.BasicController;
import tech.tresearchgroup.palila.controller.CompressionController;
import tech.tresearchgroup.palila.model.enums.CompressionMethodEnum;
import tech.tresearchgroup.palila.model.enums.DatabaseTypeEnum;
import tech.tresearchgroup.palila.model.enums.PermissionGroupEnum;
import tech.tresearchgroup.palila.model.enums.SearchMethodEnum;
import tech.tresearchgroup.schemas.galago.entities.*;
import tech.tresearchgroup.schemas.galago.enums.*;
import tech.tresearchgroup.schemas.galago.ui.Card;
import tech.tresearchgroup.schemas.galago.ui.RegistrationErrorsEnum;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static java.util.concurrent.Executors.newSingleThreadExecutor;

@AllArgsConstructor
public class MainEndpointsController extends BasicController {
    private final MovieController movieController;
    private final TvShowController tvShowController;
    private final GameController gameController;
    private final SongController songController;
    private final BookController bookController;
    private final VideoController videoController;
    private final NotificationController notificationController;
    private final NewsArticleController newsArticleController;
    private final QueueController queueController;
    private final UserController userController;
    private final FileController fileController;
    private final SettingsController settingsController;
    private final AboutPage aboutPage;
    private final IndexPage indexPage;
    private final LoginPage loginPage;
    private final ResetPage resetPage;
    private final RegisterPage registerPage;
    private final LicensesPage licensesPage;
    private final NewsPage newsPage;
    private final NotificationsPage notificationsPage;
    private final ProfilePage profilePage;
    private final QueuePage queuePage;
    private final SearchPage searchPage;
    private final SettingsPage settingsPage;
    private final UploadPage uploadPage;
    private final DeniedPage deniedPage;

    public HttpResponse about(HttpRequest httpRequest) throws IOException, SQLException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        if (canAccess(httpRequest, PermissionGroupEnum.ALL, userController)) {
            boolean loggedIn = verifyApiKey(httpRequest);
            ExtendedUserEntity userEntity = (ExtendedUserEntity) getUser(httpRequest, userController);
            return ok(aboutPage.render(loggedIn, userEntity));
        } else {
            return redirect("/login");
        }
    }

    public HttpResponse index(HttpRequest httpRequest) throws IOException, SQLException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        if (canAccess(httpRequest, PermissionGroupEnum.USER, userController)) {
            ExtendedUserEntity userEntity = (ExtendedUserEntity) getUser(httpRequest, userController);
            UserSettingsEntity userSettingsEntity = null;
            List<Card> newBooksCards = null;
            List<Card> popularBooksCards = null;
            List<Card> newGamesCards = null;
            List<Card> popularGamesCards = null;
            List<Card> newMoviesCards = null;
            List<Card> popularMoviesCards = null;
            List<Card> newMusicCards = null;
            List<Card> popularMusicCards = null;
            List<Card> newTvShowsCards = null;
            List<Card> popularTvShowsCards = null;
            if (userEntity != null) {
                userSettingsEntity = userEntity.getUserSettings();
                newBooksCards = CardConverter.convertBooks(bookController.readNewestPaginated(settingsController.getMaxBrowseResults(userSettingsEntity), 0), "new");
                popularBooksCards = CardConverter.convertBooks(bookController.readPopularPaginated(settingsController.getMaxBrowseResults(userSettingsEntity), 0), "popular");
                newGamesCards = CardConverter.convertGames(gameController.readNewestPaginated(settingsController.getMaxBrowseResults(userSettingsEntity), 0), "new");
                popularGamesCards = CardConverter.convertGames(gameController.readPopularPaginated(settingsController.getMaxBrowseResults(userSettingsEntity), 0), "popular");
                newMoviesCards = CardConverter.convertMovies(movieController.readNewestPaginated(settingsController.getMaxBrowseResults(userSettingsEntity), 0), "new");
                popularMoviesCards = CardConverter.convertMovies(movieController.readPopularPaginated(settingsController.getMaxBrowseResults(userSettingsEntity), 0), "popular");
                newMusicCards = CardConverter.convertSongs(songController.readNewestPaginated(settingsController.getMaxBrowseResults(userSettingsEntity), 0), "new");
                popularMusicCards = CardConverter.convertSongs(songController.readPopularPaginated(settingsController.getMaxBrowseResults(userSettingsEntity), 0), "popular");
                newTvShowsCards = CardConverter.convertTvShows(tvShowController.readNewestPaginated(settingsController.getMaxBrowseResults(userSettingsEntity), 0), "new");
                popularTvShowsCards = CardConverter.convertTvShows(tvShowController.readPopularPaginated(settingsController.getMaxBrowseResults(userSettingsEntity), 0), "popular");
            }
            boolean loggedIn = verifyApiKey(httpRequest);
            byte[] data = indexPage.render(loggedIn, settingsController.getCardWidth(userSettingsEntity), newBooksCards, popularBooksCards, newGamesCards, popularGamesCards, newMoviesCards, popularMoviesCards, newMusicCards, popularMusicCards, newTvShowsCards, popularTvShowsCards, userEntity);
            byte[] compressed = CompressionController.compress(data);
            return okResponseCompressed(compressed);
        } else {
            return redirect("/login");
        }
    }

    public HttpResponse login(HttpRequest httpRequest) throws IOException, SQLException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        if (canAccess(httpRequest, PermissionGroupEnum.ALL, userController)) {
            String error = httpRequest.getQueryParameter("error");
            boolean isError = false;
            if (error != null) {
                if (error.equals("")) {
                    isError = true;
                }
            }
            boolean loggedIn = verifyApiKey(httpRequest);
            ExtendedUserEntity userEntity = (ExtendedUserEntity) getUser(httpRequest, userController);
            return ok(loginPage.render(isError, loggedIn, userEntity));
        }
        return error();
    }

    public HttpResponse reset(HttpRequest httpRequest) throws IOException, SQLException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        boolean loggedIn = verifyApiKey(httpRequest);
        ExtendedUserEntity userEntity = (ExtendedUserEntity) getUser(httpRequest, userController);
        String error = httpRequest.getQueryParameter("error");
        String success = httpRequest.getQueryParameter("success");
        String confirmationData = httpRequest.getQueryParameter("confirmation");
        boolean isError = false;
        boolean isSuccess = false;
        boolean wasConfirmed = false;
        if (error != null) {
            if (error.equals("")) {
                isError = true;
            }
        }
        if (success != null) {
            if (success.equals("")) {
                isSuccess = true;
            }
        }
        if (confirmationData != null) {
            if (!confirmationData.equals("")) {
                wasConfirmed = true;
            }
        }
        return ok(resetPage.render(loggedIn, userEntity, isError, isSuccess, wasConfirmed, confirmationData));
    }

    public HttpResponse postReset(HttpRequest httpRequest) throws SQLException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException, IOException {
        if (canAccess(httpRequest, PermissionGroupEnum.ALL, userController)) {
            boolean loggedIn = verifyApiKey(httpRequest);
            ExtendedUserEntity userEntity = (ExtendedUserEntity) getUser(httpRequest, userController);
            String email = httpRequest.getPostParameter("email");
            String password = httpRequest.getPostParameter("password");
            String passwordConfirm = httpRequest.getPostParameter("passwordConfirm");
            String confirmationData = httpRequest.getPostParameter("confirmation");
            if (confirmationData != null) {
                if (!confirmationData.equals("")) {
                    return redirect("/reset?confirmation=" + confirmationData + "&success");
                }
            }
            return redirect("/reset?confirmation=123");
        } else {
            return redirect("/login");
        }
    }

    public HttpResponse register(HttpRequest httpRequest) throws IOException, SQLException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        String error = httpRequest.getQueryParameter("error");
        ExtendedUserEntity userEntity = (ExtendedUserEntity) getUser(httpRequest, userController);
        if (error == null) {
            return ok(registerPage.render(null, userEntity));
        }
        return ok(registerPage.render(RegistrationErrorsEnum.valueOf(error), userEntity));
    }

    public @NotNull Promisable<HttpResponse> postRegister(@NotNull HttpRequest httpRequest) throws SQLException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, IOException, InstantiationException {
        String username = httpRequest.getPostParameter("username");
        String email = httpRequest.getPostParameter("email");
        String emailConfirm = httpRequest.getPostParameter("emailConfirm");
        String password = httpRequest.getPostParameter("password");
        String passwordConfirm = httpRequest.getPostParameter("passwordConfirm");
        if (password.equals(passwordConfirm)) {
            if (password.length() < 10) {
                return redirect("/register?error=PASSWORD_LENGTH");
            }
            if (email.equals(emailConfirm)) {
                String[] atParts = email.split("@");
                String[] periodParts = email.split("\\.");
                if (!email.contains("@") || !email.contains(".") || atParts.length != 2 || periodParts.length > 2) {
                    return redirect("/register?error=INCORRECT_EMAIL");
                }
                if (userController.getUserByUsername(username) != null) {
                    return redirect("/register?error=USERNAME_TAKEN");
                }
                ExtendedUserEntity userEntity = new ExtendedUserEntity();
                userEntity.setUsername(username);
                userEntity.setPassword(hashPassword(password));
                userEntity.setEmail(email);
                userEntity.setPermissionGroup(PermissionGroupEnum.USER);
                if (userController.createSecureResponse(userEntity, httpRequest) != null) {
                    return HttpResponse.redirect301("/");
                }
                return redirect("/register?error=ERROR_500");
            }
            return redirect("/register?error=EMAIL_MATCH");
        }
        return redirect("/register?error=PASSWORD_MATCH");
    }

    private String hashPassword(String password) {
        byte[] salt = new byte[16];
        return new String(Hex.encode(BCrypt.generate(password.getBytes(StandardCharsets.UTF_8), salt, 8)));
    }

    public HttpResponse licenses(HttpRequest httpRequest) throws IOException, SQLException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        if (canAccess(httpRequest, PermissionGroupEnum.USER, userController)) {
            boolean loggedIn = verifyApiKey(httpRequest);
            ExtendedUserEntity userEntity = (ExtendedUserEntity) getUser(httpRequest, userController);
            return ok(licensesPage.render(loggedIn, userEntity));
        } else {
            return redirect("/login");
        }
    }

    public HttpResponse news(HttpRequest httpRequest) throws IOException, SQLException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        if (canAccess(httpRequest, PermissionGroupEnum.USER, userController)) {
            UserSettingsEntity userSettingsEntity = null;
            ExtendedUserEntity userEntity = (ExtendedUserEntity) getUser(httpRequest, userController);
            if (userEntity != null) {
                userSettingsEntity = userEntity.getUserSettings();
            }
            int maxResults = settingsController.getMaxBrowseResults(userSettingsEntity);
            int page = httpRequest.getQueryParameter("page") != null ? Integer.parseInt(Objects.requireNonNull(httpRequest.getQueryParameter("page"))) : 0;
            long maxPage = newsArticleController.getTotalPages(maxResults);
            boolean loggedIn = verifyApiKey(httpRequest);
            return ok(newsPage.render(loggedIn, page, maxPage, userEntity));
        } else {
            return redirect("/login");
        }
    }

    public HttpResponse notifications(HttpRequest httpRequest) throws IOException, SQLException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        if (canAccess(httpRequest, PermissionGroupEnum.USER, userController)) {
            int page = httpRequest.getQueryParameter("page") != null ? Integer.parseInt(Objects.requireNonNull(httpRequest.getQueryParameter("page"))) : 0;
            ExtendedUserEntity userEntity = (ExtendedUserEntity) getUser(httpRequest, userController);
            if (userEntity != null) {
                long maxPage = notificationController.getTotalPages(settingsController.getMaxBrowseResults(userEntity.getUserSettings()), httpRequest);
                List<NotificationEntity> notificationEntityList = notificationController.readPaginated((int) maxPage, page, httpRequest);
                boolean loggedIn = verifyApiKey(httpRequest);
                return ok(notificationsPage.render(page, maxPage, notificationEntityList, loggedIn, userEntity));
            } else {
                return error();
            }
        } else {
            return redirect("/login");
        }
    }

    public HttpResponse profile(HttpRequest httpRequest) throws IOException, SQLException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        if (canAccess(httpRequest, PermissionGroupEnum.USER, userController)) {
            boolean loggedIn = verifyApiKey(httpRequest);
            ExtendedUserEntity userEntity = (ExtendedUserEntity) getUser(httpRequest, userController);
            return ok(profilePage.render(loggedIn, userEntity));
        } else {
            return redirect("/login");
        }
    }

    public HttpResponse postProfile(HttpRequest httpRequest) throws Exception {
        if (canAccess(httpRequest, PermissionGroupEnum.USER, userController)) {
            ExtendedUserEntity userEntity = (ExtendedUserEntity) getUser(httpRequest, userController);
            String formUsername = httpRequest.getPostParameter("username");
            String email = httpRequest.getPostParameter("email");
            String password = httpRequest.getPostParameter("password");
            String passwordConfirm = httpRequest.getPostParameter("passwordConfirm");

            if (formUsername != null) {
                userEntity.setUsername(userEntity.getUsername());
            }
            if (email != null) {
                userEntity.setEmail(email);
            }
            if (password != null && passwordConfirm != null) {
                userEntity.setPassword(hashPassword(password));
            }
            if (userController.update(userEntity.getId(), userEntity)) {
                boolean loggedIn = verifyApiKey(httpRequest);
                return ok(profilePage.render(loggedIn, userEntity));
            }
            return error();
        } else {
            return redirect("/login");
        }
    }

    public HttpResponse queue(HttpRequest httpRequest) throws IOException, SQLException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        if (canAccess(httpRequest, PermissionGroupEnum.USER, userController)) {
            UserSettingsEntity userSettingsEntity = null;
            ExtendedUserEntity userEntity = (ExtendedUserEntity) getUser(httpRequest, userController);
            if (userEntity != null) {
                userSettingsEntity = userEntity.getUserSettings();
            }
            int page = httpRequest.getQueryParameter("page") != null ? Integer.parseInt(Objects.requireNonNull(httpRequest.getQueryParameter("page"))) : 0;
            int maxResults = settingsController.getMaxBrowseResults(userSettingsEntity);
            long maxPage = queueController.getTotalPages(maxResults);
            List<QueueEntity> queueEntityList = queueController.readPaginatedResponse(maxResults, page);
            boolean loggedIn = verifyApiKey(httpRequest);
            return ok(queuePage.render(loggedIn, page, maxPage, queueEntityList, userEntity));
        } else {
            return redirect("/login");
        }
    }

    public HttpResponse search(HttpRequest httpRequest) throws Exception {
        if (canAccess(httpRequest, PermissionGroupEnum.USER, userController)) {
            UserSettingsEntity userSettingsEntity = null;
            ExtendedUserEntity userEntity = (ExtendedUserEntity) getUser(httpRequest, userController);
            if (userEntity != null) {
                userSettingsEntity = userEntity.getUserSettings();
            }
            long start = System.currentTimeMillis();
            String query = httpRequest.getPostParameter("query");

            List<MovieEntity> movieEntities = movieController.search(query, "*");
            List<TvShowEntity> tvShowEntities = tvShowController.search(query, "*");
            List<GameEntity> gameEntities = gameController.search(query, "*");
            List<SongEntity> songEntities = songController.search(query, "*");
            List<BookEntity> bookEntities = bookController.search(query, "*");

            List<Card> movieCards = CardConverter.convertMovies(movieEntities, "search");
            List<Card> tvShowCards = CardConverter.convertTvShows(tvShowEntities, "search");
            List<Card> gameCards = CardConverter.convertGames(gameEntities, "search");
            List<Card> songCards = CardConverter.convertSongs(songEntities, "search");
            List<Card> bookCards = CardConverter.convertBooks(bookEntities, "search");

            long timeTaken = System.currentTimeMillis() - start;
            boolean loggedIn = verifyApiKey(httpRequest);
            return ok(searchPage.render(loggedIn, movieCards, tvShowCards, gameCards, songCards, bookCards, timeTaken, settingsController.getCardWidth(userSettingsEntity), userEntity));
        } else {
            return redirect("/login");
        }
    }

    public HttpResponse settings(HttpRequest httpRequest) throws IOException, SQLException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        if (canAccess(httpRequest, PermissionGroupEnum.OPERATOR, userController)) {
            boolean loggedIn = verifyApiKey(httpRequest);
            ExtendedUserEntity userEntity = (ExtendedUserEntity) getUser(httpRequest, userController);
            return ok(settingsPage.render(loggedIn, userEntity));
        } else {
            return redirect("/login");
        }
    }

    public Promisable<HttpResponse> saveSettings(HttpRequest httpRequest,
                                                 InterfaceMethodEnum interfaceNetworkUsage,
                                                 PlaybackQualityEnum defaultPlaybackQuality,
                                                 boolean debugEnabled,
                                                 boolean maintenanceMode,
                                                 boolean securityEnabled,
                                                 CompressionMethodEnum compressionMethod,
                                                 int compressionQuality,
                                                 String securityIssuer,
                                                 String securitySecretKey,
                                                 String searchHost,
                                                 String searchKey,
                                                 DisplayModeEnum displayMode,
                                                 EncoderProgramEnum encoderProgram,
                                                 InspectorProgramEnum inspectorProgram,
                                                 AudioCodecEnum audioCodec,
                                                 AudioRateEnum audioRate,
                                                 EncoderPresetEnum audioPreset,
                                                 VideoContainerEnum videoContainer,
                                                 VideoCodecEnum videoCodec,
                                                 EncoderPresetEnum videoPreset,
                                                 boolean tuneFilm,
                                                 boolean tuneAnimation,
                                                 boolean tuneGrain,
                                                 boolean stillImage,
                                                 boolean fastDecode,
                                                 boolean zeroLatency,
                                                 boolean fastStart,
                                                 boolean tunePsnr,
                                                 boolean tuneSsnr,
                                                 int videoCrf,
                                                 boolean blackBorderRemoval,
                                                 boolean cudaAcceleration,
                                                 int oneFourFourPTranscodeBitrate,
                                                 int twoFourZeroPTranscodeBitrate,
                                                 int threeSixZeroPTranscodeBitrate,
                                                 int fourEightZeroPTranscodeBitrate,
                                                 int sevenTwoZeroPTranscodeBitrate,
                                                 int oneZeroEightZeroPTranscodeBitrate,
                                                 int twoKTranscodeBitrate,
                                                 int fourKTranscodeBitrate,
                                                 int eightKTranscodeBitrate,
                                                 boolean showPoster,
                                                 boolean showName,
                                                 boolean showRuntime,
                                                 boolean showGenre,
                                                 boolean showMpaaRating,
                                                 boolean showUserRating,
                                                 boolean showLanguage,
                                                 boolean showReleaseDate,
                                                 boolean showActions,
                                                 boolean bookLibraryEnable,
                                                 String bookLibraryPath,
                                                 boolean bookScanEnable,
                                                 int bookScanFrequencyTime,
                                                 ScanFrequencyEnum bookScanFrequencyType,
                                                 boolean gameLibraryEnable,
                                                 String gameLibraryPath,
                                                 boolean gameScanEnable,
                                                 int gameScanFrequencyTime,
                                                 ScanFrequencyEnum gameScanFrequencyType,
                                                 boolean movieLibraryEnable,
                                                 String movieLibraryPath,
                                                 boolean movieScanEnable,
                                                 boolean moviePreTranscodeEnable,
                                                 boolean moviePreTranscodeOneFourFourP,
                                                 boolean moviePreTranscodeTwoFourZeroP,
                                                 boolean moviePreTranscodeThreeSixZeroP,
                                                 boolean moviePreTranscodeFourEightZeroP,
                                                 boolean moviePreTranscodeSevenTwoZeroP,
                                                 boolean moviePreTranscodeOneZeroEightZeroP,
                                                 boolean moviePreTranscodeTwoK,
                                                 boolean moviePreTranscodeFourK,
                                                 boolean moviePreTranscodeEightK,
                                                 int movieScanFrequencyTime,
                                                 ScanFrequencyEnum movieScanFrequencyType,
                                                 String movieLibraryPreTranscodePath,
                                                 boolean musicLibraryEnable,
                                                 String musicLibraryPath,
                                                 boolean musicScanEnable,
                                                 boolean musicPreTranscodeEnable,
                                                 boolean musicPreTranscodeSixFourK,
                                                 boolean musicPreTranscodeNineSixK,
                                                 boolean musicPreTranscodeOneTwoEightK,
                                                 boolean musicPreTranscodeThreeTwoZeroK,
                                                 boolean musicPreTranscodeOneFourOneOneK,
                                                 int musicScanFrequencyTime,
                                                 ScanFrequencyEnum musicScanFrequencyType,
                                                 String musicPreTranscodeLibraryPath,
                                                 boolean tvShowLibraryEnable,
                                                 String tvShowLibraryPath,
                                                 boolean tvShowScanEnable,
                                                 boolean tvShowPreTranscodeEnable,
                                                 boolean tvPreTranscodeOneFourFourP,
                                                 boolean tvPreTranscodeTwoFourZeroP,
                                                 boolean tvPreTranscodeThreeSixZeroP,
                                                 boolean tvPreTranscodeFourEightZeroP,
                                                 boolean tvPreTranscodeSevenTwoZeroP,
                                                 boolean tvPreTranscodeOneZeroEightZeroP,
                                                 boolean tvPreTranscodeTwoK,
                                                 boolean tvPreTranscodeFourK,
                                                 boolean tvPreTranscodeEightK,
                                                 int tvShowScanFrequencyTime,
                                                 ScanFrequencyEnum tvShowScanFrequencyType,
                                                 String tvShowLibraryPreTranscodePath,
                                                 String serverName,
                                                 String fileToUpload,
                                                 boolean allowRegistration,
                                                 boolean showNewBooks,
                                                 boolean showNewGames,
                                                 boolean showNewMovies,
                                                 boolean showNewMusic,
                                                 boolean showNewTvShows,
                                                 boolean showPopularBooks,
                                                 boolean showPopularGames,
                                                 boolean showPopularMovies,
                                                 boolean showPopularMusic,
                                                 boolean showPopularTvShows,
                                                 SearchMethodEnum searchMethod,
                                                 int maxSearchResults,
                                                 int maxBrowseResults,
                                                 int fontSize,
                                                 String fontType,
                                                 String fontColor,
                                                 int cardWidth,
                                                 boolean stickyTopMenu,
                                                 boolean cacheEnable,
                                                 long apiCacheSize,
                                                 long databaseCacheSize,
                                                 long pageCacheSize,
                                                 int maxAssetCacheAge,
                                                 DatabaseTypeEnum databaseType,
                                                 String databaseName,
                                                 int minDatabaseConnections,
                                                 int maxDatabaseConnections) throws SQLException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, IOException, InstantiationException {
        if (canAccess(httpRequest, PermissionGroupEnum.OPERATOR, userController)) {
            if (SettingsController.saveSettings(
                new SettingsFileEntity(
                    interfaceNetworkUsage,
                    defaultPlaybackQuality,
                    debugEnabled,
                    maintenanceMode,
                    securityEnabled,
                    compressionMethod,
                    compressionQuality,
                    securityIssuer,
                    securitySecretKey,
                    searchHost,
                    searchKey,
                    displayMode,
                    encoderProgram,
                    inspectorProgram,
                    audioCodec,
                    audioRate,
                    audioPreset,
                    videoContainer,
                    videoCodec,
                    videoPreset,
                    tuneFilm,
                    tuneAnimation,
                    tuneGrain,
                    stillImage,
                    fastDecode,
                    zeroLatency,
                    fastStart,
                    tunePsnr,
                    tuneSsnr,
                    videoCrf,
                    blackBorderRemoval,
                    cudaAcceleration,
                    oneFourFourPTranscodeBitrate,
                    twoFourZeroPTranscodeBitrate,
                    threeSixZeroPTranscodeBitrate,
                    fourEightZeroPTranscodeBitrate,
                    sevenTwoZeroPTranscodeBitrate,
                    oneZeroEightZeroPTranscodeBitrate,
                    twoKTranscodeBitrate,
                    fourKTranscodeBitrate,
                    eightKTranscodeBitrate,
                    showPoster,
                    showName,
                    showRuntime,
                    showGenre,
                    showMpaaRating,
                    showUserRating,
                    showLanguage,
                    showReleaseDate,
                    showActions,
                    bookLibraryEnable,
                    bookLibraryPath,
                    bookScanEnable,
                    bookScanFrequencyTime,
                    bookScanFrequencyType,
                    gameLibraryEnable,
                    gameLibraryPath,
                    gameScanEnable,
                    gameScanFrequencyTime,
                    gameScanFrequencyType,
                    movieLibraryEnable,
                    movieLibraryPath,
                    movieScanEnable,
                    moviePreTranscodeEnable,
                    moviePreTranscodeOneFourFourP,
                    moviePreTranscodeTwoFourZeroP,
                    moviePreTranscodeThreeSixZeroP,
                    moviePreTranscodeFourEightZeroP,
                    moviePreTranscodeSevenTwoZeroP,
                    moviePreTranscodeOneZeroEightZeroP,
                    moviePreTranscodeTwoK,
                    moviePreTranscodeFourK,
                    moviePreTranscodeEightK,
                    movieScanFrequencyTime,
                    movieScanFrequencyType,
                    movieLibraryPreTranscodePath,
                    musicLibraryEnable,
                    musicLibraryPath,
                    musicScanEnable,
                    musicPreTranscodeEnable,
                    musicPreTranscodeSixFourK,
                    musicPreTranscodeNineSixK,
                    musicPreTranscodeOneTwoEightK,
                    musicPreTranscodeThreeTwoZeroK,
                    musicPreTranscodeOneFourOneOneK,
                    musicScanFrequencyTime,
                    musicScanFrequencyType,
                    musicPreTranscodeLibraryPath,
                    tvShowLibraryEnable,
                    tvShowLibraryPath,
                    tvShowScanEnable,
                    tvShowPreTranscodeEnable,
                    tvPreTranscodeOneFourFourP,
                    tvPreTranscodeTwoFourZeroP,
                    tvPreTranscodeThreeSixZeroP,
                    tvPreTranscodeFourEightZeroP,
                    tvPreTranscodeSevenTwoZeroP,
                    tvPreTranscodeOneZeroEightZeroP,
                    tvPreTranscodeTwoK,
                    tvPreTranscodeFourK,
                    tvPreTranscodeEightK,
                    tvShowScanFrequencyTime,
                    tvShowScanFrequencyType,
                    tvShowLibraryPreTranscodePath,
                    serverName,
                    fileToUpload,
                    allowRegistration,
                    showNewBooks,
                    showNewGames,
                    showNewMovies,
                    showNewMusic,
                    showNewTvShows,
                    showPopularBooks,
                    showPopularGames,
                    showPopularMovies,
                    showPopularMusic,
                    showPopularTvShows,
                    searchMethod,
                    maxSearchResults,
                    maxBrowseResults,
                    fontSize,
                    fontType,
                    fontColor,
                    cardWidth,
                    stickyTopMenu,
                    cacheEnable,
                    apiCacheSize,
                    databaseCacheSize,
                    pageCacheSize,
                    maxAssetCacheAge,
                    databaseType,
                    databaseName,
                    minDatabaseConnections,
                    maxDatabaseConnections
                )
            )) {
                settingsController.setInterfaceMethod(interfaceNetworkUsage);
                settingsController.setDefaultPlaybackQuality(defaultPlaybackQuality);
                settingsController.setDebug(debugEnabled);
                settingsController.setMaintenanceMode(maintenanceMode);
                settingsController.setEnableSecurity(securityEnabled);
                settingsController.setCompressionMethod(compressionMethod);
                settingsController.setIssuer(securityIssuer);
                settingsController.setSecretKey(securitySecretKey);
                settingsController.setSearchHost(searchHost);
                settingsController.setSearchKey(searchKey);
                settingsController.setDisplayMode(displayMode);
                settingsController.setEncoderProgram(encoderProgram);
                settingsController.setInspectorProgram(inspectorProgram);
                settingsController.setAudioCodec(audioCodec);
                settingsController.setAudioRate(audioRate);
                settingsController.setAudioPreset(audioPreset);
                settingsController.setVideoContainer(videoContainer);
                settingsController.setVideoCodec(videoCodec);
                settingsController.setEncoderPreset(videoPreset);
                settingsController.setVideoTuneFilm(tuneFilm);
                settingsController.setVideoTuneAnimation(tuneAnimation);
                settingsController.setVideoTuneGrain(tuneGrain);
                settingsController.setVideoTuneStillImage(stillImage);
                settingsController.setVideoTuneFastDecode(fastDecode);
                settingsController.setVideoTuneZeroLatency(zeroLatency);
                settingsController.setVideoFastStart(fastStart);
                settingsController.setVideoTunePsnr(tunePsnr);
                settingsController.setVideoTuneSsnr(tuneSsnr);
                settingsController.setVideoCrf(videoCrf);
                settingsController.setVideoBlackBorder(blackBorderRemoval);
                settingsController.setVideoCudaAcceleration(cudaAcceleration);
                settingsController.setOneFourFourVideoTranscodeBitrate(oneFourFourPTranscodeBitrate);
                settingsController.setTwoFourZeroVideoTranscodeBitrate(twoFourZeroPTranscodeBitrate);
                settingsController.setThreeSixZeroVideoTranscodeBitrate(threeSixZeroPTranscodeBitrate);
                settingsController.setFourEightZeroVideoTranscodeBitrate(fourEightZeroPTranscodeBitrate);
                settingsController.setSevenTwoZeroVideoTranscodeBitrate(sevenTwoZeroPTranscodeBitrate);
                settingsController.setOneZeroEightZeroVideoTranscodeBitrate(oneZeroEightZeroPTranscodeBitrate);
                settingsController.setTwoKVideoTranscodeBitrate(twoKTranscodeBitrate);
                settingsController.setFourKVideoTranscodeBitrate(fourKTranscodeBitrate);
                settingsController.setEightKVideoTranscodeBitrate(eightKTranscodeBitrate);
                settingsController.setTableShowPoster(showPoster);
                settingsController.setTableShowName(showName);
                settingsController.setTableShowRuntime(showRuntime);
                settingsController.setTableShowGenre(showGenre);
                settingsController.setTableShowMpaaRating(showMpaaRating);
                settingsController.setTableShowUserRating(showUserRating);
                settingsController.setTableShowLanguage(showLanguage);
                settingsController.setTableShowReleaseDate(showReleaseDate);
                settingsController.setTableShowActions(showActions);
                settingsController.setBookLibraryEnable(bookLibraryEnable);
                settingsController.setBookLibraryPath(bookLibraryPath);
                settingsController.setBookScanEnable(bookScanEnable);
                settingsController.setBookScanFrequencyTime(bookScanFrequencyTime);
                settingsController.setBookScanFrequencyType(bookScanFrequencyType);
                settingsController.setGameLibraryEnable(gameLibraryEnable);
                settingsController.setGameLibraryPath(gameLibraryPath);
                settingsController.setGameScanEnable(gameScanEnable);
                settingsController.setGameScanFrequencyTime(gameScanFrequencyTime);
                settingsController.setGameScanFrequencyType(gameScanFrequencyType);
                settingsController.setMovieLibraryEnable(movieLibraryEnable);
                settingsController.setMovieLibraryPath(movieLibraryPath);
                settingsController.setMovieScanEnable(movieScanEnable);
                settingsController.setMoviePreTranscodeEnable(moviePreTranscodeEnable);
                settingsController.setMoviePreTranscode144p(moviePreTranscodeOneFourFourP);
                settingsController.setMoviePreTranscode240p(moviePreTranscodeTwoFourZeroP);
                settingsController.setMoviePreTranscode360p(moviePreTranscodeThreeSixZeroP);
                settingsController.setMoviePreTranscode480p(moviePreTranscodeFourEightZeroP);
                settingsController.setMoviePreTranscode720p(moviePreTranscodeSevenTwoZeroP);
                settingsController.setMoviePreTranscode1080p(moviePreTranscodeOneZeroEightZeroP);
                settingsController.setMoviePreTranscode2k(moviePreTranscodeTwoK);
                settingsController.setMoviePreTranscode4k(moviePreTranscodeFourK);
                settingsController.setMoviePreTranscode8k(moviePreTranscodeEightK);
                settingsController.setMovieScanFrequencyTime(movieScanFrequencyTime);
                settingsController.setMovieScanFrequencyType(movieScanFrequencyType);
                settingsController.setMoviePreTranscodeLibraryPath(movieLibraryPreTranscodePath);
                settingsController.setMusicLibraryEnable(musicLibraryEnable);
                settingsController.setMusicLibraryPath(musicLibraryPath);
                settingsController.setMusicScanEnable(musicScanEnable);
                settingsController.setMusicPreTranscodeEnable(musicPreTranscodeEnable);
                settingsController.setMusicPreTranscode64k(musicPreTranscodeSixFourK);
                settingsController.setMusicPreTranscode96k(musicPreTranscodeNineSixK);
                settingsController.setMusicPreTranscode128k(musicPreTranscodeOneTwoEightK);
                settingsController.setMusicPreTranscode320k(musicPreTranscodeThreeTwoZeroK);
                settingsController.setMusicPreTranscode1411k(musicPreTranscodeOneFourOneOneK);
                settingsController.setMusicScanFrequencyTime(musicScanFrequencyTime);
                settingsController.setMusicScanFrequencyType(musicScanFrequencyType);
                settingsController.setMusicPreTranscodeLibraryPath(musicPreTranscodeLibraryPath);
                settingsController.setTvShowLibraryEnable(tvShowLibraryEnable);
                settingsController.setTvShowLibraryPath(tvShowLibraryPath);
                settingsController.setTvShowScanEnable(tvShowScanEnable);
                settingsController.setTvShowPreTranscodeEnable(tvShowPreTranscodeEnable);
                settingsController.setTvShowPreTranscode144p(tvPreTranscodeOneFourFourP);
                settingsController.setTvShowPreTranscode240p(tvPreTranscodeTwoFourZeroP);
                settingsController.setTvShowPreTranscode360p(tvPreTranscodeThreeSixZeroP);
                settingsController.setTvShowPreTranscode480p(tvPreTranscodeFourEightZeroP);
                settingsController.setTvShowPreTranscode720p(tvPreTranscodeSevenTwoZeroP);
                settingsController.setTvShowPreTranscode1080p(tvPreTranscodeOneZeroEightZeroP);
                settingsController.setTvShowPreTranscode2k(tvPreTranscodeTwoK);
                settingsController.setTvShowPreTranscode4k(tvPreTranscodeFourK);
                settingsController.setTvShowPreTranscode8k(tvPreTranscodeEightK);
                settingsController.setTvShowScanFrequencyTime(tvShowScanFrequencyTime);
                settingsController.setTvShowScanFrequencyType(tvShowScanFrequencyType);
                settingsController.setTvShowPreTranscodeLibraryPath(tvShowLibraryPreTranscodePath);
                settingsController.setServerName(serverName);
                settingsController.setServerFaviconLocation(fileToUpload);
                settingsController.setAllowRegistration(allowRegistration);
                settingsController.setHomePageShowNewBook(showNewBooks);
                settingsController.setHomePageShowNewGame(showNewGames);
                settingsController.setHomePageShowNewMovie(showNewMovies);
                settingsController.setHomePageShowNewMusic(showNewMusic);
                settingsController.setHomePageShowNewTvShow(showNewTvShows);
                settingsController.setHomePageShowPopularBook(showPopularBooks);
                settingsController.setHomePageShowPopularGame(showPopularGames);
                settingsController.setHomePageShowPopularMovie(showPopularMovies);
                settingsController.setHomePageShowPopularMusic(showPopularMusic);
                settingsController.setHomePageShowPopularTvShow(showPopularTvShows);
                settingsController.setSearchMethod(searchMethod);
                settingsController.setMaxSearchResults(maxSearchResults);
                settingsController.setMaxBrowseResults(maxBrowseResults);
                settingsController.setFontSize(fontSize);
                settingsController.setFontType(fontType);
                settingsController.setFontColor(fontColor);
                settingsController.setCardWidth(cardWidth);
                settingsController.setStickyTopMenu(stickyTopMenu);
                settingsController.setCacheEnable(cacheEnable);
                settingsController.setMaxAssetCacheAge(maxAssetCacheAge);
                return redirect("/settings");
            }
            return redirect("/settings");
        } else {
            return accessDenied();
        }
    }

    public HttpResponse upload(HttpRequest httpRequest) throws IOException, SQLException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        if (canAccess(httpRequest, PermissionGroupEnum.USER, userController)) {
            ExtendedUserEntity userEntity = (ExtendedUserEntity) getUser(httpRequest, userController);
            boolean loggedIn = verifyApiKey(httpRequest);
            return ok(uploadPage.render(true, loggedIn, userEntity));
        } else {
            return redirect("/login");
        }
    }

    public @NotNull Promisable<HttpResponse> postUpload(@NotNull HttpRequest httpRequest) throws SQLException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        if (canAccess(httpRequest, PermissionGroupEnum.USER, userController)) {
            String mediaType = httpRequest.getQueryParameter("mediaType");
            String library = "temp";
            switch (Objects.requireNonNull(mediaType).toUpperCase()) {
                case "BOOK" -> library = settingsController.getBookLibraryPath();
                case "MOVIE" -> library = settingsController.getMovieLibraryPath();
                case "TVSHOW" -> library = settingsController.getTvShowLibraryPath();
                case "GAME" -> library = settingsController.getGameLibraryPath();
                case "MUSIC" -> library = settingsController.getMusicLibraryPath();
            }
            UUID uuid = UUID.randomUUID();
            Path file = new File(library + "/" + uuid + ".tmp").toPath();
            String finalLibrary = library;
            if (settingsController.isDebug()) {
                System.out.println("Saving: " + finalLibrary + "/" + uuid + ".tmp");
            }
            return httpRequest.handleMultipart(MultipartDecoder.MultipartDataHandler.file(fileName ->
                    ChannelFileWriter.open(newSingleThreadExecutor(), new File(finalLibrary + "/" + uuid + ".tmp").toPath())))
                .map($ -> finalizeUpload(httpRequest, mediaType, file));
        } else {
            return accessDenied();
        }
    }

    private HttpResponse finalizeUpload(HttpRequest httpRequest, String mediaType, Path file) throws SQLException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        if (canAccess(httpRequest, PermissionGroupEnum.USER, userController)) {
            try {
                switch (Objects.requireNonNull(mediaType).toUpperCase()) {
                    case "BOOK" -> {
                        BookEntity book = new BookEntity();
                        book.setTitle(String.valueOf(file.getFileName()));
                        List<FileEntity> fileEntities = new LinkedList<>();
                        FileEntity fileEntity = new FileEntity();
                        fileEntity.setPath(String.valueOf(file.getParent().toAbsolutePath()));
                        if (fileController.createSecureResponse(fileEntity, httpRequest) != null) {
                            fileEntities.add(fileEntity);
                            //Todo fix
                            //book.setFiles(fileEntities);
                            if (bookController.createSecureResponse(book, httpRequest) != null) {
                                return ok();
                            } else {
                                return error();
                            }
                        } else {
                            return error();
                        }
                    }
                    case "MOVIE" -> {
                        VideoEntity videoEntity = new VideoEntity();
                        videoEntity.setPlaybackQualityEnum(PlaybackQualityEnum.ORIGINAL);
                        videoEntity.setFilePath(String.valueOf(file.toAbsolutePath()));
                        if (videoController.createSecureResponse(videoEntity, httpRequest) != null) {
                            MovieEntity movie = new MovieEntity();
                            movie.setTitle(String.valueOf(file.getFileName()));
                            List<VideoEntity> fileEntities = new LinkedList<>();
                            fileEntities.add(videoEntity);
                            //Todo fix
                            //movie.setFiles(fileEntities);
                            if (movieController.createSecureResponse(movie, httpRequest) != null) {
                                return ok();
                            } else {
                                return error();
                            }
                        }
                        return error();
                    }
                    case "TVSHOW" -> {
                        TvShowEntity tvShow = new TvShowEntity();
                        tvShow.setTitle(String.valueOf(file.getFileName()));
                        List<VideoEntity> fileEntities = new LinkedList<>();
                        VideoEntity videoEntity = new VideoEntity();
                        videoEntity.setPlaybackQualityEnum(PlaybackQualityEnum.ORIGINAL);
                        videoEntity.setFilePath(String.valueOf(file.toAbsolutePath()));
                        if (videoController.createSecureResponse(videoEntity, httpRequest) != null) {
                            fileEntities.add(videoEntity);
                            //Todo fix
                            //tvShow.setFiles(fileEntities);
                            if (tvShowController.createSecureResponse(tvShow, httpRequest) != null) {
                                return ok();
                            } else {
                                return error();
                            }
                        }
                        return error();
                    }
                    case "GAME" -> {
                        GameEntity game = new GameEntity();
                        game.setTitle(String.valueOf(file.getFileName()));
                        List<FileEntity> fileEntities = new LinkedList<>();
                        FileEntity fileEntity = new FileEntity();
                        fileEntity.setPath(String.valueOf(file.getParent().toAbsolutePath()));
                        if (fileController.createSecureResponse(fileEntity, httpRequest) != null) {
                            fileEntities.add(fileEntity);
                            //Todo fix
                            //game.setFiles(fileEntities);
                            if (gameController.createSecureResponse(game, httpRequest) != null) {
                                return ok();
                            } else {
                                return error();
                            }
                        } else {
                            return error();
                        }
                    }
                    case "MUSIC" -> {
                        SongEntity song = new SongEntity();
                        song.setTitle(String.valueOf(file.getFileName()));
                        FileEntity fileEntity = new FileEntity();
                        fileEntity.setPath(String.valueOf(file.toAbsolutePath()));
                        if (fileController.createSecureResponse(fileEntity, httpRequest) != null) {
                            //Todo fix
                            //song.setFile(fileEntity);
                            if (songController.createSecureResponse(song, httpRequest) != null) {
                                return ok();
                            } else {
                                return error();
                            }
                        } else {
                            return error();
                        }
                    }
                }
            } catch (Exception e) {
                if (settingsController.isDebug()) {
                    e.printStackTrace();
                }
                if (!file.toFile().delete()) {
                    System.err.println("Failed to delete: " + file.toFile().getAbsolutePath());
                }
            }
            return error();
        } else {
            return redirect("/login");
        }
    }

    public @NotNull Promisable<HttpResponse> getDeniedPage(@NotNull HttpRequest httpRequest) throws IOException, SQLException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        if (canAccess(httpRequest, PermissionGroupEnum.USER, userController)) {
            boolean loggedIn = verifyApiKey(httpRequest);
            ExtendedUserEntity userEntity = (ExtendedUserEntity) getUser(httpRequest, userController);
            return ok(deniedPage.render(loggedIn, userEntity));
        }
        ExtendedUserEntity blankUser = new ExtendedUserEntity();
        blankUser.setUsername("User");
        blankUser.setPermissionGroup(PermissionGroupEnum.ALL);
        return ok(deniedPage.render(false, blankUser));
    }
}
