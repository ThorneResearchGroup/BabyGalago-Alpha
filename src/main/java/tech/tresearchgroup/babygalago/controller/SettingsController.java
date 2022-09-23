package tech.tresearchgroup.babygalago.controller;

import com.google.gson.Gson;
import tech.tresearchgroup.babygalago.model.SettingsEntity;
import tech.tresearchgroup.babygalago.model.SettingsFileEntity;
import tech.tresearchgroup.palila.controller.BasicController;
import tech.tresearchgroup.palila.model.enums.CompressionMethodEnum;
import tech.tresearchgroup.palila.model.enums.SearchMethodEnum;
import tech.tresearchgroup.schemas.galago.entities.UserSettingsEntity;
import tech.tresearchgroup.schemas.galago.enums.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SettingsController extends BasicController {
    private static final Gson gson = new Gson();

    public InterfaceMethodEnum getInterfaceMethod(UserSettingsEntity userSettingsEntity) {
        if (userSettingsEntity != null) {
            return userSettingsEntity.getInterfaceMethod();
        }
        return SettingsEntity.interfaceMethod;
    }

    public PlaybackQualityEnum getDefaultPlaybackQuality(UserSettingsEntity userSettingsEntity) {
        if (userSettingsEntity != null) {
            return userSettingsEntity.getDefaultPlaybackQuality();
        }
        return SettingsEntity.defaultPlaybackQuality;
    }

    public boolean isDebug() {
        return SettingsEntity.debug;
    }

    public boolean isMaintenanceMode() {
        return SettingsEntity.maintenanceMode;
    }

    public boolean isEnableSecurity() {
        return SettingsEntity.enableSecurity;
    }

    public CompressionMethodEnum getCompressionMethod() {
        return SettingsEntity.compressionMethod;
    }

    public String getIssuer() {
        return SettingsEntity.issuer;
    }

    public String getSecretKey() {
        return SettingsEntity.secretKey;
    }

    public String getSearchHost() {
        return SettingsEntity.searchHost;
    }

    public String getSearchKey() {
        return SettingsEntity.searchKey;
    }

    public DisplayModeEnum getDisplayMode(UserSettingsEntity userSettingsEntity) {
        if (userSettingsEntity != null) {
            return userSettingsEntity.getDisplayMode();
        }
        return SettingsEntity.displayMode;
    }

    public EncoderProgramEnum getEncoderProgram() {
        return SettingsEntity.encoderProgram;
    }

    public InspectorProgramEnum getInspectorProgram() {
        return SettingsEntity.inspectorProgram;
    }

    public AudioCodecEnum getAudioCodec() {
        return SettingsEntity.audioCodec;
    }

    public AudioRateEnum getAudioRate() {
        return SettingsEntity.audioRate;
    }

    public EncoderPresetEnum getAudioPreset() {
        return SettingsEntity.audioPreset;
    }

    public VideoContainerEnum getVideoContainer() {
        return SettingsEntity.videoContainer;
    }

    public VideoCodecEnum getVideoCodec() {
        return SettingsEntity.videoCodec;
    }

    public EncoderPresetEnum getEncoderPreset() {
        return SettingsEntity.encoderPreset;
    }

    public boolean isVideoTuneFilm() {
        return SettingsEntity.videoTuneFilm;
    }

    public boolean isVideoTuneAnimation() {
        return SettingsEntity.videoTuneAnimation;
    }

    public boolean isVideoTuneGrain() {
        return SettingsEntity.videoTuneGrain;
    }

    public boolean isVideoTuneStillImage() {
        return SettingsEntity.videoTuneStillImage;
    }

    public boolean isVideoTuneFastDecode() {
        return SettingsEntity.videoTuneFastDecode;
    }

    public boolean isVideoTuneZeroLatency() {
        return SettingsEntity.videoTuneZeroLatency;
    }

    public boolean isVideoFastStart() {
        return SettingsEntity.videoFastStart;
    }

    public boolean isVideoTunePsnr() {
        return SettingsEntity.videoTunePsnr;
    }

    public boolean isVideoTuneSsnr() {
        return SettingsEntity.videoTuneSsnr;
    }

    public int getVideoCrf() {
        return SettingsEntity.videoCrf;
    }

    public boolean isVideoBlackBorder() {
        return SettingsEntity.videoBlackBorder;
    }

    public boolean isVideoCudaAcceleration() {
        return SettingsEntity.videoCudaAcceleration;
    }

    public int getOneFourFourVideoTranscodeBitrate() {
        return SettingsEntity.oneFourFourVideoTranscodeBitrate;
    }

    public int getTwoFourZeroVideoTranscodeBitrate() {
        return SettingsEntity.twoFourZeroVideoTranscodeBitrate;
    }

    public int getThreeSixZeroVideoTranscodeBitrate() {
        return SettingsEntity.threeSixZeroVideoTranscodeBitrate;
    }

    public int getFourEightZeroVideoTranscodeBitrate() {
        return SettingsEntity.fourEightZeroVideoTranscodeBitrate;
    }

    public int getSevenTwoZeroVideoTranscodeBitrate() {
        return SettingsEntity.sevenTwoZeroVideoTranscodeBitrate;
    }

    public int getOneZeroEightZeroVideoTranscodeBitrate() {
        return SettingsEntity.oneZeroEightZeroVideoTranscodeBitrate;
    }

    public int getTwoKVideoTranscodeBitrate() {
        return SettingsEntity.twoKVideoTranscodeBitrate;
    }

    public int getFourKVideoTranscodeBitrate() {
        return SettingsEntity.fourKVideoTranscodeBitrate;
    }

    public int getEightKVideoTranscodeBitrate() {
        return SettingsEntity.eightKVideoTranscodeBitrate;
    }

    public boolean isTableShowPoster(UserSettingsEntity userSettingsEntity) {
        if (userSettingsEntity != null) {
            return userSettingsEntity.isTableShowPoster();
        }
        return SettingsEntity.tableShowPoster;
    }

    public boolean isTableShowName(UserSettingsEntity userSettingsEntity) {
        if (userSettingsEntity != null) {
            return userSettingsEntity.isTableShowName();
        }
        return SettingsEntity.tableShowName;
    }

    public boolean isTableShowRuntime(UserSettingsEntity userSettingsEntity) {
        if (userSettingsEntity != null) {
            return userSettingsEntity.isTableShowRuntime();
        }
        return SettingsEntity.tableShowRuntime;
    }

    public boolean isTableShowGenre(UserSettingsEntity userSettingsEntity) {
        if (userSettingsEntity != null) {
            return userSettingsEntity.isTableShowGenre();
        }
        return SettingsEntity.tableShowGenre;
    }

    public boolean isTableShowMpaaRating(UserSettingsEntity userSettingsEntity) {
        if (userSettingsEntity != null) {
            return userSettingsEntity.isTableShowMpaaRating();
        }
        return SettingsEntity.tableShowMpaaRating;
    }

    public boolean isTableShowUserRating(UserSettingsEntity userSettingsEntity) {
        if (userSettingsEntity != null) {
            return userSettingsEntity.isTableShowUserRating();
        }
        return SettingsEntity.tableShowUserRating;
    }

    public boolean isTableShowLanguage(UserSettingsEntity userSettingsEntity) {
        if (userSettingsEntity != null) {
            return userSettingsEntity.isTableShowLanguage();
        }
        return SettingsEntity.tableShowLanguage;
    }

    public boolean isTableShowReleaseDate(UserSettingsEntity userSettingsEntity) {
        if (userSettingsEntity != null) {
            return userSettingsEntity.isTableShowReleaseDate();
        }
        return SettingsEntity.tableShowReleaseDate;
    }

    public boolean isTableShowActions(UserSettingsEntity userSettingsEntity) {
        if (userSettingsEntity != null) {
            return userSettingsEntity.isTableShowActions();
        }
        return SettingsEntity.tableShowActions;
    }

    public boolean isBookLibraryEnable() {
        return SettingsEntity.bookLibraryEnable;
    }

    public String getBookLibraryPath() {
        return SettingsEntity.bookLibraryPath;
    }

    public boolean isBookScanEnable() {
        return SettingsEntity.bookScanEnable;
    }

    public int getBookScanFrequencyTime() {
        return SettingsEntity.bookScanFrequencyTime;
    }

    public ScanFrequencyEnum getBookScanFrequencyType() {
        return SettingsEntity.bookScanFrequencyType;
    }

    public boolean isGameLibraryEnable() {
        return SettingsEntity.gameLibraryEnable;
    }

    public String getGameLibraryPath() {
        return SettingsEntity.gameLibraryPath;
    }

    public boolean isGameScanEnable() {
        return SettingsEntity.gameScanEnable;
    }

    public int getGameScanFrequencyTime() {
        return SettingsEntity.gameScanFrequencyTime;
    }

    public ScanFrequencyEnum getGameScanFrequencyType() {
        return SettingsEntity.gameScanFrequencyType;
    }

    public boolean isMovieLibraryEnable() {
        return SettingsEntity.movieLibraryEnable;
    }

    public String getMovieLibraryPath() {
        return SettingsEntity.movieLibraryPath;
    }

    public boolean isMovieScanEnable() {
        return SettingsEntity.movieScanEnable;
    }

    public boolean isMoviePreTranscodeEnable() {
        return SettingsEntity.moviePreTranscodeEnable;
    }

    public boolean isMoviePreTranscode144p() {
        return SettingsEntity.moviePreTranscode144p;
    }

    public boolean isMoviePreTranscode240p() {
        return SettingsEntity.moviePreTranscode240p;
    }

    public boolean isMoviePreTranscode360p() {
        return SettingsEntity.moviePreTranscode360p;
    }

    public boolean isMoviePreTranscode480p() {
        return SettingsEntity.moviePreTranscode480p;
    }

    public boolean isMoviePreTranscode720p() {
        return SettingsEntity.moviePreTranscode720p;
    }

    public boolean isMoviePreTranscode1080p() {
        return SettingsEntity.moviePreTranscode1080p;
    }

    public boolean isMoviePreTranscode2k() {
        return SettingsEntity.moviePreTranscode2k;
    }

    public boolean isMoviePreTranscode4k() {
        return SettingsEntity.moviePreTranscode4k;
    }

    public boolean isMoviePreTranscode8k() {
        return SettingsEntity.moviePreTranscode8k;
    }

    public int getMovieScanFrequencyTime() {
        return SettingsEntity.movieScanFrequencyTime;
    }

    public ScanFrequencyEnum getMovieScanFrequencyType() {
        return SettingsEntity.movieScanFrequencyType;
    }

    public String getMoviePreTranscodeLibraryPath() {
        return SettingsEntity.moviePreTranscodeLibraryPath;
    }

    public boolean isMusicLibraryEnable() {
        return SettingsEntity.musicLibraryEnable;
    }

    public String getMusicLibraryPath() {
        return SettingsEntity.musicLibraryPath;
    }

    public boolean isMusicScanEnable() {
        return SettingsEntity.musicScanEnable;
    }

    public boolean isMusicPreTranscodeEnable() {
        return SettingsEntity.musicPreTranscodeEnable;
    }

    public boolean isMusicPreTranscode64k() {
        return SettingsEntity.musicPreTranscode64k;
    }

    public boolean isMusicPreTranscode96k() {
        return SettingsEntity.musicPreTranscode96k;
    }

    public boolean isMusicPreTranscode128k() {
        return SettingsEntity.musicPreTranscode128k;
    }

    public boolean isMusicPreTranscode320k() {
        return SettingsEntity.musicPreTranscode320k;
    }

    public boolean isMusicPreTranscode1411k() {
        return SettingsEntity.musicPreTranscode1411k;
    }

    public int getMusicScanFrequencyTime() {
        return SettingsEntity.musicScanFrequencyTime;
    }

    public ScanFrequencyEnum getMusicScanFrequencyType() {
        return SettingsEntity.musicScanFrequencyType;
    }

    public String getMusicPreTranscodeLibraryPath() {
        return SettingsEntity.musicPreTranscodeLibraryPath;
    }

    public boolean isTvShowLibraryEnable() {
        return SettingsEntity.tvShowLibraryEnable;
    }

    public String getTvShowLibraryPath() {
        return SettingsEntity.tvShowLibraryPath;
    }

    public boolean isTvShowScanEnable() {
        return SettingsEntity.tvShowScanEnable;
    }

    public boolean isTvShowPreTranscodeEnable() {
        return SettingsEntity.tvShowPreTranscodeEnable;
    }

    public boolean isTvShowPreTranscode144p() {
        return SettingsEntity.tvShowPreTranscode144p;
    }

    public boolean isTvShowPreTranscode240p() {
        return SettingsEntity.tvShowPreTranscode240p;
    }

    public boolean isTvShowPreTranscode360p() {
        return SettingsEntity.tvShowPreTranscode360p;
    }

    public boolean isTvShowPreTranscode480p() {
        return SettingsEntity.tvShowPreTranscode480p;
    }

    public boolean isTvShowPreTranscode720p() {
        return SettingsEntity.tvShowPreTranscode720p;
    }

    public boolean isTvShowPreTranscode1080p() {
        return SettingsEntity.tvShowPreTranscode1080p;
    }

    public boolean isTvShowPreTranscode2k() {
        return SettingsEntity.tvShowPreTranscode2k;
    }

    public boolean isTvShowPreTranscode4k() {
        return SettingsEntity.tvShowPreTranscode4k;
    }

    public boolean isTvShowPreTranscode8k() {
        return SettingsEntity.tvShowPreTranscode8k;
    }

    public int getTvShowScanFrequencyTime() {
        return SettingsEntity.tvShowScanFrequencyTime;
    }

    public ScanFrequencyEnum getTvShowScanFrequencyType() {
        return SettingsEntity.tvShowScanFrequencyType;
    }

    public String getTvShowPreTranscodeLibraryPath() {
        return SettingsEntity.tvShowPreTranscodeLibraryPath;
    }

    public String getServerName() {
        return SettingsEntity.serverName;
    }

    public boolean isAllowRegistration() {
        return SettingsEntity.allowRegistration;
    }

    public boolean isHomePageShowNewMovie(UserSettingsEntity userSettingsEntity) {
        if (userSettingsEntity != null) {
            return userSettingsEntity.isHomePageShowNewMovie();
        }
        return SettingsEntity.homePageShowNewMovie;
    }

    public boolean isHomePageShowNewTvShow(UserSettingsEntity userSettingsEntity) {
        if (userSettingsEntity != null) {
            return userSettingsEntity.isHomePageShowNewTvShow();
        }
        return SettingsEntity.homePageShowNewTvShow;
    }

    public boolean isHomePageShowNewGame(UserSettingsEntity userSettingsEntity) {
        if (userSettingsEntity != null) {
            return userSettingsEntity.isHomePageShowNewGame();
        }
        return SettingsEntity.homePageShowNewGame;
    }

    public boolean isHomePageShowNewBook(UserSettingsEntity userSettingsEntity) {
        if (userSettingsEntity != null) {
            return userSettingsEntity.isHomePageShowNewBook();
        }
        return SettingsEntity.homePageShowNewBook;
    }

    public boolean isHomePageShowNewMusic(UserSettingsEntity userSettingsEntity) {
        if (userSettingsEntity != null) {
            return userSettingsEntity.isHomePageShowNewMusic();
        }
        return SettingsEntity.homePageShowNewMusic;
    }

    public boolean isHomePageShowPopularMovie(UserSettingsEntity userSettingsEntity) {
        if (userSettingsEntity != null) {
            return userSettingsEntity.isHomePageShowPopularMovie();
        }
        return SettingsEntity.homePageShowPopularMovie;
    }

    public boolean isHomePageShowPopularTvShow(UserSettingsEntity userSettingsEntity) {
        if (userSettingsEntity != null) {
            return userSettingsEntity.isHomePageShowPopularTvShow();
        }
        return SettingsEntity.homePageShowPopularTvShow;
    }

    public boolean isHomePageShowPopularGame(UserSettingsEntity userSettingsEntity) {
        if (userSettingsEntity != null) {
            return userSettingsEntity.isHomePageShowPopularGame();
        }
        return SettingsEntity.homePageShowPopularGame;
    }

    public boolean isHomePageShowPopularBook(UserSettingsEntity userSettingsEntity) {
        if (userSettingsEntity != null) {
            return userSettingsEntity.isHomePageShowPopularBook();
        }
        return SettingsEntity.homePageShowPopularBook;
    }

    public boolean isHomePageShowPopularMusic(UserSettingsEntity userSettingsEntity) {
        if (userSettingsEntity != null) {
            return userSettingsEntity.isHomePageShowPopularMusic();
        }
        return SettingsEntity.homePageShowPopularMusic;
    }

    public SearchMethodEnum getSearchMethod() {
        return SettingsEntity.searchMethod;
    }

    public int getMaxSearchResults(UserSettingsEntity userSettingsEntity) {
        if (userSettingsEntity != null) {
            return userSettingsEntity.getMaxSearchResults();
        }
        return SettingsEntity.maxSearchResults;
    }

    public int getMaxBrowseResults(UserSettingsEntity userSettingsEntity) {
        if (userSettingsEntity != null) {
            System.out.println(userSettingsEntity);
            return userSettingsEntity.getMaxBrowseResults();
        }
        return SettingsEntity.maxUIBrowseResults;
    }

    public int getCardWidth(UserSettingsEntity userSettingsEntity) {
        if (userSettingsEntity != null) {
            return userSettingsEntity.getCardWidth();
        }
        return SettingsEntity.cardWidth;
    }

    public boolean isStickyTopMenu(UserSettingsEntity userSettingsEntity) {
        if (userSettingsEntity != null) {
            return userSettingsEntity.isStickyTopMenu();
        }
        return SettingsEntity.stickyTopMenu;
    }

    public boolean isCacheEnable() {
        return SettingsEntity.cacheEnable;
    }

    public int getMaxAssetCacheAge() {
        return SettingsEntity.maxAssetCacheAge;
    }

    public void setInterfaceMethod(InterfaceMethodEnum interfaceMethod) {
        SettingsEntity.interfaceMethod = interfaceMethod;
    }

    public void setDefaultPlaybackQuality(PlaybackQualityEnum defaultPlaybackQuality) {
        SettingsEntity.defaultPlaybackQuality = defaultPlaybackQuality;
    }

    public void setDebug(boolean debug) {
        SettingsEntity.debug = debug;
    }

    public void setMaintenanceMode(boolean maintenanceMode) {
        SettingsEntity.maintenanceMode = maintenanceMode;
    }

    public void setEnableSecurity(boolean enableSecurity) {
        SettingsEntity.enableSecurity = enableSecurity;
    }

    public void setCompressionMethod(CompressionMethodEnum compressionMethod) {
        SettingsEntity.compressionMethod = compressionMethod;
    }

    public void setIssuer(String issuer) {
        SettingsEntity.issuer = issuer;
    }

    public void setSecretKey(String secretKey) {
        SettingsEntity.secretKey = secretKey;
    }

    public void setSearchHost(String searchHost) {
        SettingsEntity.searchHost = searchHost;
    }

    public void setSearchKey(String searchKey) {
        SettingsEntity.searchKey = searchKey;
    }

    public void setDisplayMode(DisplayModeEnum displayMode) {
        SettingsEntity.displayMode = displayMode;
    }

    public void setEncoderProgram(EncoderProgramEnum encoderProgram) {
        SettingsEntity.encoderProgram = encoderProgram;
    }

    public void setInspectorProgram(InspectorProgramEnum inspectorProgram) {
        SettingsEntity.inspectorProgram = inspectorProgram;
    }

    public void setAudioCodec(AudioCodecEnum audioCodec) {
        SettingsEntity.audioCodec = audioCodec;
    }

    public void setAudioRate(AudioRateEnum audioRate) {
        SettingsEntity.audioRate = audioRate;
    }

    public void setAudioPreset(EncoderPresetEnum audioPreset) {
        SettingsEntity.audioPreset = audioPreset;
    }

    public void setVideoContainer(VideoContainerEnum videoContainer) {
        SettingsEntity.videoContainer = videoContainer;
    }

    public void setVideoCodec(VideoCodecEnum videoCodec) {
        SettingsEntity.videoCodec = videoCodec;
    }

    public void setEncoderPreset(EncoderPresetEnum encoderPreset) {
        SettingsEntity.encoderPreset = encoderPreset;
    }

    public void setVideoTuneFilm(boolean videoTuneFilm) {
        SettingsEntity.videoTuneFilm = videoTuneFilm;
    }

    public void setVideoTuneAnimation(boolean videoTuneAnimation) {
        SettingsEntity.videoTuneAnimation = videoTuneAnimation;
    }

    public void setVideoTuneGrain(boolean videoTuneGrain) {
        SettingsEntity.videoTuneGrain = videoTuneGrain;
    }

    public void setVideoTuneStillImage(boolean videoTuneStillImage) {
        SettingsEntity.videoTuneStillImage = videoTuneStillImage;
    }

    public void setVideoTuneFastDecode(boolean videoTuneFastDecode) {
        SettingsEntity.videoTuneFastDecode = videoTuneFastDecode;
    }

    public void setVideoTuneZeroLatency(boolean videoTuneZeroLatency) {
        SettingsEntity.videoTuneZeroLatency = videoTuneZeroLatency;
    }

    public void setVideoFastStart(boolean videoFastStart) {
        SettingsEntity.videoFastStart = videoFastStart;
    }

    public void setVideoTunePsnr(boolean videoTunePsnr) {
        SettingsEntity.videoTunePsnr = videoTunePsnr;
    }

    public void setVideoTuneSsnr(boolean videoTuneSsnr) {
        SettingsEntity.videoTuneSsnr = videoTuneSsnr;
    }

    public void setVideoCrf(int videoCrf) {
        SettingsEntity.videoCrf = videoCrf;
    }

    public void setVideoBlackBorder(boolean videoBlackBorder) {
        SettingsEntity.videoBlackBorder = videoBlackBorder;
    }

    public void setVideoCudaAcceleration(boolean videoCudaAcceleration) {
        SettingsEntity.videoCudaAcceleration = videoCudaAcceleration;
    }

    public void setOneFourFourVideoTranscodeBitrate(int oneFourFourVideoTranscodeBitrate) {
        SettingsEntity.oneFourFourVideoTranscodeBitrate = oneFourFourVideoTranscodeBitrate;
    }

    public void setTwoFourZeroVideoTranscodeBitrate(int twoFourZeroVideoTranscodeBitrate) {
        SettingsEntity.twoFourZeroVideoTranscodeBitrate = twoFourZeroVideoTranscodeBitrate;
    }

    public void setThreeSixZeroVideoTranscodeBitrate(int threeSixZeroVideoTranscodeBitrate) {
        SettingsEntity.threeSixZeroVideoTranscodeBitrate = threeSixZeroVideoTranscodeBitrate;
    }

    public void setFourEightZeroVideoTranscodeBitrate(int fourEightZeroVideoTranscodeBitrate) {
        SettingsEntity.fourEightZeroVideoTranscodeBitrate = fourEightZeroVideoTranscodeBitrate;
    }

    public void setSevenTwoZeroVideoTranscodeBitrate(int sevenTwoZeroVideoTranscodeBitrate) {
        SettingsEntity.sevenTwoZeroVideoTranscodeBitrate = sevenTwoZeroVideoTranscodeBitrate;
    }

    public void setOneZeroEightZeroVideoTranscodeBitrate(int oneZeroEightZeroVideoTranscodeBitrate) {
        SettingsEntity.oneZeroEightZeroVideoTranscodeBitrate = oneZeroEightZeroVideoTranscodeBitrate;
    }

    public void setTwoKVideoTranscodeBitrate(int twoKVideoTranscodeBitrate) {
        SettingsEntity.twoKVideoTranscodeBitrate = twoKVideoTranscodeBitrate;
    }

    public void setFourKVideoTranscodeBitrate(int fourKVideoTranscodeBitrate) {
        SettingsEntity.fourKVideoTranscodeBitrate = fourKVideoTranscodeBitrate;
    }

    public void setEightKVideoTranscodeBitrate(int eightKVideoTranscodeBitrate) {
        SettingsEntity.eightKVideoTranscodeBitrate = eightKVideoTranscodeBitrate;
    }

    public void setTableShowPoster(boolean tableShowPoster) {
        SettingsEntity.tableShowPoster = tableShowPoster;
    }

    public void setTableShowName(boolean tableShowName) {
        SettingsEntity.tableShowName = tableShowName;
    }

    public void setTableShowRuntime(boolean tableShowRuntime) {
        SettingsEntity.tableShowRuntime = tableShowRuntime;
    }

    public void setTableShowGenre(boolean tableShowGenre) {
        SettingsEntity.tableShowGenre = tableShowGenre;
    }

    public void setTableShowMpaaRating(boolean tableShowMpaaRating) {
        SettingsEntity.tableShowMpaaRating = tableShowMpaaRating;
    }

    public void setTableShowUserRating(boolean tableShowUserRating) {
        SettingsEntity.tableShowUserRating = tableShowUserRating;
    }

    public void setTableShowLanguage(boolean tableShowLanguage) {
        SettingsEntity.tableShowLanguage = tableShowLanguage;
    }

    public void setTableShowReleaseDate(boolean tableShowReleaseDate) {
        SettingsEntity.tableShowReleaseDate = tableShowReleaseDate;
    }

    public void setTableShowActions(boolean tableShowActions) {
        SettingsEntity.tableShowActions = tableShowActions;
    }

    public void setBookLibraryEnable(boolean bookLibraryEnable) {
        SettingsEntity.bookLibraryEnable = bookLibraryEnable;
    }

    public void setBookLibraryPath(String bookLibraryPath) {
        SettingsEntity.bookLibraryPath = bookLibraryPath;
    }

    public void setBookScanEnable(boolean bookScanEnable) {
        SettingsEntity.bookScanEnable = bookScanEnable;
    }

    public void setBookScanFrequencyTime(int bookScanFrequencyTime) {
        SettingsEntity.bookScanFrequencyTime = bookScanFrequencyTime;
    }

    public void setBookScanFrequencyType(ScanFrequencyEnum bookScanFrequencyType) {
        SettingsEntity.bookScanFrequencyType = bookScanFrequencyType;
    }

    public void setGameLibraryEnable(boolean gameLibraryEnable) {
        SettingsEntity.gameLibraryEnable = gameLibraryEnable;
    }

    public void setGameLibraryPath(String gameLibraryPath) {
        SettingsEntity.gameLibraryPath = gameLibraryPath;
    }

    public void setGameScanEnable(boolean gameScanEnable) {
        SettingsEntity.gameScanEnable = gameScanEnable;
    }

    public void setGameScanFrequencyTime(int gameScanFrequencyTime) {
        SettingsEntity.gameScanFrequencyTime = gameScanFrequencyTime;
    }

    public void setGameScanFrequencyType(ScanFrequencyEnum gameScanFrequencyType) {
        SettingsEntity.gameScanFrequencyType = gameScanFrequencyType;
    }

    public void setMovieLibraryEnable(boolean movieLibraryEnable) {
        SettingsEntity.movieLibraryEnable = movieLibraryEnable;
    }

    public void setMovieLibraryPath(String movieLibraryPath) {
        SettingsEntity.movieLibraryPath = movieLibraryPath;
    }

    public void setMovieScanEnable(boolean movieScanEnable) {
        SettingsEntity.movieScanEnable = movieScanEnable;
    }

    public void setMoviePreTranscodeEnable(boolean moviePreTranscodeEnable) {
        SettingsEntity.moviePreTranscodeEnable = moviePreTranscodeEnable;
    }

    public void setMoviePreTranscode144p(boolean moviePreTranscode144p) {
        SettingsEntity.moviePreTranscode144p = moviePreTranscode144p;
    }

    public void setMoviePreTranscode240p(boolean moviePreTranscode240p) {
        SettingsEntity.moviePreTranscode240p = moviePreTranscode240p;
    }

    public void setMoviePreTranscode360p(boolean moviePreTranscode360p) {
        SettingsEntity.moviePreTranscode360p = moviePreTranscode360p;
    }

    public void setMoviePreTranscode480p(boolean moviePreTranscode480p) {
        SettingsEntity.moviePreTranscode480p = moviePreTranscode480p;
    }

    public void setMoviePreTranscode720p(boolean moviePreTranscode720p) {
        SettingsEntity.moviePreTranscode720p = moviePreTranscode720p;
    }

    public void setMoviePreTranscode1080p(boolean moviePreTranscode1080p) {
        SettingsEntity.moviePreTranscode1080p = moviePreTranscode1080p;
    }

    public void setMoviePreTranscode2k(boolean moviePreTranscode2k) {
        SettingsEntity.moviePreTranscode2k = moviePreTranscode2k;
    }

    public void setMoviePreTranscode4k(boolean moviePreTranscode4k) {
        SettingsEntity.moviePreTranscode4k = moviePreTranscode4k;
    }

    public void setMoviePreTranscode8k(boolean moviePreTranscode8k) {
        SettingsEntity.moviePreTranscode8k = moviePreTranscode8k;
    }

    public void setMovieScanFrequencyTime(int movieScanFrequencyTime) {
        SettingsEntity.movieScanFrequencyTime = movieScanFrequencyTime;
    }

    public void setMovieScanFrequencyType(ScanFrequencyEnum movieScanFrequencyType) {
        SettingsEntity.movieScanFrequencyType = movieScanFrequencyType;
    }

    public void setMoviePreTranscodeLibraryPath(String moviePreTranscodeLibraryPath) {
        SettingsEntity.moviePreTranscodeLibraryPath = moviePreTranscodeLibraryPath;
    }

    public void setMusicLibraryEnable(boolean musicLibraryEnable) {
        SettingsEntity.musicLibraryEnable = musicLibraryEnable;
    }

    public void setMusicLibraryPath(String musicLibraryPath) {
        SettingsEntity.musicLibraryPath = musicLibraryPath;
    }

    public void setMusicScanEnable(boolean musicScanEnable) {
        SettingsEntity.musicScanEnable = musicScanEnable;
    }

    public void setMusicPreTranscodeEnable(boolean musicPreTranscodeEnable) {
        SettingsEntity.musicPreTranscodeEnable = musicPreTranscodeEnable;
    }

    public void setMusicPreTranscode64k(boolean musicPreTranscode64k) {
        SettingsEntity.musicPreTranscode64k = musicPreTranscode64k;
    }

    public void setMusicPreTranscode96k(boolean musicPreTranscode96k) {
        SettingsEntity.musicPreTranscode96k = musicPreTranscode96k;
    }

    public void setMusicPreTranscode128k(boolean musicPreTranscode128k) {
        SettingsEntity.musicPreTranscode128k = musicPreTranscode128k;
    }

    public void setMusicPreTranscode320k(boolean musicPreTranscode320k) {
        SettingsEntity.musicPreTranscode320k = musicPreTranscode320k;
    }

    public void setMusicPreTranscode1411k(boolean musicPreTranscode1411k) {
        SettingsEntity.musicPreTranscode1411k = musicPreTranscode1411k;
    }

    public void setMusicScanFrequencyTime(int musicScanFrequencyTime) {
        SettingsEntity.musicScanFrequencyTime = musicScanFrequencyTime;
    }

    public void setMusicScanFrequencyType(ScanFrequencyEnum musicScanFrequencyType) {
        SettingsEntity.musicScanFrequencyType = musicScanFrequencyType;
    }

    public void setMusicPreTranscodeLibraryPath(String musicPreTranscodeLibraryPath) {
        SettingsEntity.musicPreTranscodeLibraryPath = musicPreTranscodeLibraryPath;
    }

    public void setTvShowLibraryEnable(boolean tvShowLibraryEnable) {
        SettingsEntity.tvShowLibraryEnable = tvShowLibraryEnable;
    }

    public void setTvShowLibraryPath(String tvShowLibraryPath) {
        SettingsEntity.tvShowLibraryPath = tvShowLibraryPath;
    }

    public void setTvShowScanEnable(boolean tvShowScanEnable) {
        SettingsEntity.tvShowScanEnable = tvShowScanEnable;
    }

    public void setTvShowPreTranscodeEnable(boolean tvShowPreTranscodeEnable) {
        SettingsEntity.tvShowPreTranscodeEnable = tvShowPreTranscodeEnable;
    }

    public void setTvShowPreTranscode144p(boolean tvShowPreTranscode144p) {
        SettingsEntity.tvShowPreTranscode144p = tvShowPreTranscode144p;
    }

    public void setTvShowPreTranscode240p(boolean tvShowPreTranscode240p) {
        SettingsEntity.tvShowPreTranscode240p = tvShowPreTranscode240p;
    }

    public void setTvShowPreTranscode360p(boolean tvShowPreTranscode360p) {
        SettingsEntity.tvShowPreTranscode360p = tvShowPreTranscode360p;
    }

    public void setTvShowPreTranscode480p(boolean tvShowPreTranscode480p) {
        SettingsEntity.tvShowPreTranscode480p = tvShowPreTranscode480p;
    }

    public void setTvShowPreTranscode720p(boolean tvShowPreTranscode720p) {
        SettingsEntity.tvShowPreTranscode720p = tvShowPreTranscode720p;
    }

    public void setTvShowPreTranscode1080p(boolean tvShowPreTranscode1080p) {
        SettingsEntity.tvShowPreTranscode1080p = tvShowPreTranscode1080p;
    }

    public void setTvShowPreTranscode2k(boolean tvShowPreTranscode2k) {
        SettingsEntity.tvShowPreTranscode2k = tvShowPreTranscode2k;
    }

    public void setTvShowPreTranscode4k(boolean tvShowPreTranscode4k) {
        SettingsEntity.tvShowPreTranscode4k = tvShowPreTranscode4k;
    }

    public void setTvShowPreTranscode8k(boolean tvShowPreTranscode8k) {
        SettingsEntity.tvShowPreTranscode8k = tvShowPreTranscode8k;
    }

    public void setTvShowScanFrequencyTime(int tvShowScanFrequencyTime) {
        SettingsEntity.tvShowScanFrequencyTime = tvShowScanFrequencyTime;
    }

    public void setTvShowScanFrequencyType(ScanFrequencyEnum tvShowScanFrequencyType) {
        SettingsEntity.tvShowScanFrequencyType = tvShowScanFrequencyType;
    }

    public void setTvShowPreTranscodeLibraryPath(String tvShowPreTranscodeLibraryPath) {
        SettingsEntity.tvShowPreTranscodeLibraryPath = tvShowPreTranscodeLibraryPath;
    }

    public void setServerName(String serverName) {
        SettingsEntity.serverName = serverName;
    }

    public void setAllowRegistration(boolean allowRegistration) {
        SettingsEntity.allowRegistration = allowRegistration;
    }

    public void setHomePageShowNewMovie(boolean homePageShowNewMovie) {
        SettingsEntity.homePageShowNewMovie = homePageShowNewMovie;
    }

    public void setHomePageShowNewTvShow(boolean homePageShowNewTvShow) {
        SettingsEntity.homePageShowNewTvShow = homePageShowNewTvShow;
    }

    public void setHomePageShowNewGame(boolean homePageShowNewGame) {
        SettingsEntity.homePageShowNewGame = homePageShowNewGame;
    }

    public void setHomePageShowNewBook(boolean homePageShowNewBook) {
        SettingsEntity.homePageShowNewBook = homePageShowNewBook;
    }

    public void setHomePageShowNewMusic(boolean homePageShowNewMusic) {
        SettingsEntity.homePageShowNewMusic = homePageShowNewMusic;
    }

    public void setHomePageShowPopularMovie(boolean homePageShowPopularMovie) {
        SettingsEntity.homePageShowPopularMovie = homePageShowPopularMovie;
    }

    public void setHomePageShowPopularTvShow(boolean homePageShowPopularTvShow) {
        SettingsEntity.homePageShowPopularTvShow = homePageShowPopularTvShow;
    }

    public void setHomePageShowPopularGame(boolean homePageShowPopularGame) {
        SettingsEntity.homePageShowPopularGame = homePageShowPopularGame;
    }

    public void setHomePageShowPopularBook(boolean homePageShowPopularBook) {
        SettingsEntity.homePageShowPopularBook = homePageShowPopularBook;
    }

    public void setHomePageShowPopularMusic(boolean homePageShowPopularMusic) {
        SettingsEntity.homePageShowPopularMusic = homePageShowPopularMusic;
    }

    public void setSearchMethod(SearchMethodEnum searchMethod) {
        SettingsEntity.searchMethod = searchMethod;
    }

    public void setMaxSearchResults(int maxSearchResults) {
        SettingsEntity.maxSearchResults = maxSearchResults;
    }

    public void setMaxUIBrowseResults(int maxUIBrowseResults) {
        SettingsEntity.maxUIBrowseResults = maxUIBrowseResults;
    }

    public void setMaxAPIBrowseResults(int maxAPIBrowseResults) {
        SettingsEntity.maxAPIBrowseResults = maxAPIBrowseResults;
    }

    public void setCardWidth(int cardWidth) {
        SettingsEntity.cardWidth = cardWidth;
    }

    public void setStickyTopMenu(boolean stickyTopMenu) {
        SettingsEntity.stickyTopMenu = stickyTopMenu;
    }

    public void setCacheEnable(boolean cacheEnable) {
        SettingsEntity.cacheEnable = cacheEnable;
    }

    public void setMaxAssetCacheAge(int maxAssetCacheAge) {
        SettingsEntity.maxAssetCacheAge = maxAssetCacheAge;
    }

    public String getDatabaseName() {
        return SettingsEntity.databaseName;
    }

    public void setDatabaseName(String databaseName) {
        SettingsEntity.databaseName = databaseName;
    }

    public int getMinDatabaseConnections() {
        return SettingsEntity.minDatabaseConnections;
    }

    public void setMinDatabaseConnections(int minDatabaseConnections) {
        SettingsEntity.minDatabaseConnections = minDatabaseConnections;
    }

    public int getMaxDatabaseConnections() {
        return SettingsEntity.maxDatabaseConnections;
    }

    public void setMaxDatabaseConnections(int maxDatabaseConnections) {
        SettingsEntity.maxDatabaseConnections = maxDatabaseConnections;
    }

    public boolean isLoggingEnabled() {
        return SettingsEntity.loggingEnabled;
    }

    public void setLoggingEnabled(boolean loggingEnabled) {
        SettingsEntity.loggingEnabled = loggingEnabled;
    }

    public static void loadSettings() throws IOException {
        Path file = Paths.get("Settings.json");
        if(!file.toFile().exists()) {
            SettingsFileEntity settingsFileEntity = new SettingsFileEntity();
            settingsFileEntity.setDefaults();
            Files.write(file, gson.toJson(settingsFileEntity).getBytes());
        }
        String json = Files.readString(file);
        SettingsFileEntity settingsFileEntity = new Gson().fromJson(json, SettingsFileEntity.class);
        SettingsEntity.interfaceMethod = settingsFileEntity.getInterfaceMethod();
        SettingsEntity.defaultPlaybackQuality = settingsFileEntity.getDefaultPlaybackQuality();
        SettingsEntity.debug = settingsFileEntity.isDebug();
        SettingsEntity.maintenanceMode = settingsFileEntity.isMaintenanceMode();
        SettingsEntity.enableSecurity = settingsFileEntity.isEnableSecurity();
        SettingsEntity.compressionMethod = settingsFileEntity.getCompressionMethod();
        SettingsEntity.compressionQuality = settingsFileEntity.getCompressionQuality();
        SettingsEntity.issuer = settingsFileEntity.getIssuer();
        SettingsEntity.secretKey = settingsFileEntity.getSecretKey();
        SettingsEntity.searchHost = settingsFileEntity.getSearchHost();
        SettingsEntity.searchKey = settingsFileEntity.getSearchKey();
        SettingsEntity.displayMode = settingsFileEntity.getDisplayMode();
        SettingsEntity.encoderProgram = settingsFileEntity.getEncoderProgram();
        SettingsEntity.inspectorProgram = settingsFileEntity.getInspectorProgram();
        SettingsEntity.audioCodec = settingsFileEntity.getAudioCodec();
        SettingsEntity.audioRate = settingsFileEntity.getAudioRate();
        SettingsEntity.audioPreset = settingsFileEntity.getAudioPreset();
        SettingsEntity.videoContainer = settingsFileEntity.getVideoContainer();
        SettingsEntity.videoCodec = settingsFileEntity.getVideoCodec();
        SettingsEntity.encoderPreset = settingsFileEntity.getEncoderPreset();
        SettingsEntity.videoTuneFilm = settingsFileEntity.isVideoTuneFilm();
        SettingsEntity.videoTuneAnimation = settingsFileEntity.isVideoTuneAnimation();
        SettingsEntity.videoTuneGrain = settingsFileEntity.isVideoTuneGrain();
        SettingsEntity.videoTuneStillImage = settingsFileEntity.isVideoTuneStillImage();
        SettingsEntity.videoTuneFastDecode = settingsFileEntity.isVideoTuneFastDecode();
        SettingsEntity.videoTuneZeroLatency = settingsFileEntity.isVideoTuneZeroLatency();
        SettingsEntity.videoFastStart = settingsFileEntity.isVideoFastStart();
        SettingsEntity.videoTunePsnr = settingsFileEntity.isVideoTunePsnr();
        SettingsEntity.videoTuneSsnr = settingsFileEntity.isVideoTuneSsnr();
        SettingsEntity.videoCrf = settingsFileEntity.getVideoCrf();
        SettingsEntity.videoBlackBorder = settingsFileEntity.isVideoBlackBorder();
        SettingsEntity.videoCudaAcceleration = settingsFileEntity.isVideoCudaAcceleration();
        SettingsEntity.oneFourFourVideoTranscodeBitrate = settingsFileEntity.getOneFourFourVideoTranscodeBitrate();
        SettingsEntity.twoFourZeroVideoTranscodeBitrate = settingsFileEntity.getTwoFourZeroVideoTranscodeBitrate();
        SettingsEntity.threeSixZeroVideoTranscodeBitrate = settingsFileEntity.getThreeSixZeroVideoTranscodeBitrate();
        SettingsEntity.fourEightZeroVideoTranscodeBitrate = settingsFileEntity.getFourEightZeroVideoTranscodeBitrate();
        SettingsEntity.sevenTwoZeroVideoTranscodeBitrate = settingsFileEntity.getSevenTwoZeroVideoTranscodeBitrate();
        SettingsEntity.oneZeroEightZeroVideoTranscodeBitrate = settingsFileEntity.getOneZeroEightZeroVideoTranscodeBitrate();
        SettingsEntity.twoKVideoTranscodeBitrate = settingsFileEntity.getTwoKVideoTranscodeBitrate();
        SettingsEntity.fourKVideoTranscodeBitrate = settingsFileEntity.getFourKVideoTranscodeBitrate();
        SettingsEntity.eightKVideoTranscodeBitrate = settingsFileEntity.getEightKVideoTranscodeBitrate();
        SettingsEntity.tableShowPoster = settingsFileEntity.isTableShowPoster();
        SettingsEntity.tableShowName = settingsFileEntity.isTableShowName();
        SettingsEntity.tableShowRuntime = settingsFileEntity.isTableShowRuntime();
        SettingsEntity.tableShowGenre = settingsFileEntity.isTableShowGenre();
        SettingsEntity.tableShowMpaaRating = settingsFileEntity.isTableShowMpaaRating();
        SettingsEntity.tableShowUserRating = settingsFileEntity.isTableShowUserRating();
        SettingsEntity.tableShowLanguage = settingsFileEntity.isTableShowLanguage();
        SettingsEntity.tableShowReleaseDate = settingsFileEntity.isTableShowReleaseDate();
        SettingsEntity.tableShowActions = settingsFileEntity.isTableShowActions();
        SettingsEntity.bookLibraryEnable = settingsFileEntity.isBookLibraryEnable();
        SettingsEntity.bookLibraryPath = settingsFileEntity.getBookLibraryPath();
        File bookPath = new File(SettingsEntity.bookLibraryPath);
        if(!bookPath.exists()) {
            bookPath.mkdirs();
        }
        SettingsEntity.bookScanEnable = settingsFileEntity.isBookScanEnable();
        SettingsEntity.bookScanFrequencyTime = settingsFileEntity.getBookScanFrequencyTime();
        SettingsEntity.bookScanFrequencyType = settingsFileEntity.getBookScanFrequencyType();
        SettingsEntity.gameLibraryEnable = settingsFileEntity.isGameLibraryEnable();
        SettingsEntity.gameLibraryPath = settingsFileEntity.getGameLibraryPath();
        File gamePath = new File(SettingsEntity.gameLibraryPath);
        if(!gamePath.exists()) {
            gamePath.mkdirs();
        }
        SettingsEntity.gameScanEnable = settingsFileEntity.isGameScanEnable();
        SettingsEntity.gameScanFrequencyTime = settingsFileEntity.getGameScanFrequencyTime();
        SettingsEntity.gameScanFrequencyType = settingsFileEntity.getGameScanFrequencyType();
        SettingsEntity.movieLibraryEnable = settingsFileEntity.isMovieLibraryEnable();
        SettingsEntity.movieLibraryPath = settingsFileEntity.getMovieLibraryPath();
        File moviePath = new File(SettingsEntity.movieLibraryPath);
        if(!moviePath.exists()) {
            moviePath.mkdirs();
        }
        SettingsEntity.movieScanEnable = settingsFileEntity.isMovieScanEnable();
        SettingsEntity.moviePreTranscodeEnable = settingsFileEntity.isMoviePreTranscodeEnable();
        SettingsEntity.moviePreTranscode144p = settingsFileEntity.isMoviePreTranscode144p();
        SettingsEntity.moviePreTranscode240p = settingsFileEntity.isMoviePreTranscode240p();
        SettingsEntity.moviePreTranscode360p = settingsFileEntity.isMoviePreTranscode360p();
        SettingsEntity.moviePreTranscode480p = settingsFileEntity.isMoviePreTranscode480p();
        SettingsEntity.moviePreTranscode720p = settingsFileEntity.isMoviePreTranscode720p();
        SettingsEntity.moviePreTranscode1080p = settingsFileEntity.isMoviePreTranscode1080p();
        SettingsEntity.moviePreTranscode2k = settingsFileEntity.isMoviePreTranscode2k();
        SettingsEntity.moviePreTranscode4k = settingsFileEntity.isMoviePreTranscode4k();
        SettingsEntity.moviePreTranscode8k = settingsFileEntity.isMoviePreTranscode8k();
        SettingsEntity.movieScanFrequencyTime = settingsFileEntity.getMovieScanFrequencyTime();
        SettingsEntity.movieScanFrequencyType = settingsFileEntity.getMovieScanFrequencyType();
        SettingsEntity.moviePreTranscodeLibraryPath = settingsFileEntity.getMoviePreTranscodeLibraryPath();
        File movieTransPath = new File(SettingsEntity.moviePreTranscodeLibraryPath);
        if(!movieTransPath.exists()) {
            movieTransPath.mkdirs();
        }
        SettingsEntity.musicLibraryEnable = settingsFileEntity.isMusicLibraryEnable();
        SettingsEntity.musicLibraryPath = settingsFileEntity.getMusicLibraryPath();
        File musicPath = new File(SettingsEntity.musicLibraryPath);
        if(!musicPath.exists()) {
            musicPath.mkdirs();
        }
        SettingsEntity.musicScanEnable = settingsFileEntity.isMusicScanEnable();
        SettingsEntity.musicPreTranscodeEnable = settingsFileEntity.isMusicPreTranscodeEnable();
        SettingsEntity.musicPreTranscode64k = settingsFileEntity.isMusicPreTranscode64k();
        SettingsEntity.musicPreTranscode96k = settingsFileEntity.isMusicPreTranscode96k();
        SettingsEntity.musicPreTranscode128k = settingsFileEntity.isMusicPreTranscode128k();
        SettingsEntity.musicPreTranscode320k = settingsFileEntity.isMusicPreTranscode320k();
        SettingsEntity.musicPreTranscode1411k = settingsFileEntity.isMusicPreTranscode1411k();
        SettingsEntity.musicScanFrequencyTime = settingsFileEntity.getMusicScanFrequencyTime();
        SettingsEntity.musicScanFrequencyType = settingsFileEntity.getMusicScanFrequencyType();
        SettingsEntity.musicPreTranscodeLibraryPath = settingsFileEntity.getMusicPreTranscodeLibraryPath();
        File musicTransPath = new File(SettingsEntity.musicPreTranscodeLibraryPath);
        if(!musicTransPath.exists()) {
            musicTransPath.mkdirs();
        }
        SettingsEntity.tvShowLibraryEnable = settingsFileEntity.isTvShowLibraryEnable();
        SettingsEntity.tvShowLibraryPath = settingsFileEntity.getTvShowLibraryPath();
        File tvPath = new File(SettingsEntity.tvShowLibraryPath);
        if(!tvPath.exists()) {
            tvPath.mkdirs();
        }
        SettingsEntity.tvShowScanEnable = settingsFileEntity.isTvShowScanEnable();
        SettingsEntity.tvShowPreTranscodeEnable = settingsFileEntity.isTvShowPreTranscodeEnable();
        SettingsEntity.tvShowPreTranscode144p = settingsFileEntity.isTvShowPreTranscode144p();
        SettingsEntity.tvShowPreTranscode240p = settingsFileEntity.isTvShowPreTranscode240p();
        SettingsEntity.tvShowPreTranscode360p = settingsFileEntity.isTvShowPreTranscode360p();
        SettingsEntity.tvShowPreTranscode480p = settingsFileEntity.isTvShowPreTranscode480p();
        SettingsEntity.tvShowPreTranscode720p = settingsFileEntity.isTvShowPreTranscode720p();
        SettingsEntity.tvShowPreTranscode1080p = settingsFileEntity.isTvShowPreTranscode1080p();
        SettingsEntity.tvShowPreTranscode2k = settingsFileEntity.isTvShowPreTranscode2k();
        SettingsEntity.tvShowPreTranscode4k = settingsFileEntity.isTvShowPreTranscode4k();
        SettingsEntity.tvShowPreTranscode8k = settingsFileEntity.isTvShowPreTranscode8k();
        SettingsEntity.tvShowScanFrequencyTime = settingsFileEntity.getTvShowScanFrequencyTime();
        SettingsEntity.tvShowScanFrequencyType = settingsFileEntity.getTvShowScanFrequencyType();
        SettingsEntity.tvShowPreTranscodeLibraryPath = settingsFileEntity.getTvShowPreTranscodeLibraryPath();
        File tvTransPath = new File(SettingsEntity.tvShowPreTranscodeLibraryPath);
        if(!tvTransPath.exists()) {
            tvTransPath.mkdirs();
        }
        SettingsEntity.serverName = settingsFileEntity.getServerName();
        SettingsEntity.allowRegistration = settingsFileEntity.isAllowRegistration();
        SettingsEntity.homePageShowNewBook = settingsFileEntity.isHomePageShowNewBook();
        SettingsEntity.homePageShowNewGame = settingsFileEntity.isHomePageShowNewGame();
        SettingsEntity.homePageShowNewMovie = settingsFileEntity.isHomePageShowNewMovie();
        SettingsEntity.homePageShowNewMusic = settingsFileEntity.isHomePageShowNewMusic();
        SettingsEntity.homePageShowNewTvShow = settingsFileEntity.isHomePageShowNewTvShow();
        SettingsEntity.homePageShowPopularBook = settingsFileEntity.isHomePageShowPopularBook();
        SettingsEntity.homePageShowPopularGame = settingsFileEntity.isHomePageShowPopularGame();
        SettingsEntity.homePageShowPopularMovie = settingsFileEntity.isHomePageShowPopularMovie();
        SettingsEntity.homePageShowPopularMusic = settingsFileEntity.isHomePageShowPopularMusic();
        SettingsEntity.homePageShowPopularTvShow = settingsFileEntity.isHomePageShowPopularTvShow();
        SettingsEntity.searchMethod = settingsFileEntity.getSearchMethod();
        SettingsEntity.maxSearchResults = settingsFileEntity.getMaxSearchResults();
        SettingsEntity.maxUIBrowseResults = settingsFileEntity.getMaxUIBrowseResults();
        SettingsEntity.maxAPIBrowseResults = settingsFileEntity.getMaxAPIBrowseResults();
        SettingsEntity.cardWidth = settingsFileEntity.getCardWidth();
        SettingsEntity.stickyTopMenu = settingsFileEntity.isStickyTopMenu();
        SettingsEntity.cacheEnable = settingsFileEntity.isCacheEnable();
        SettingsEntity.apiCacheSize = settingsFileEntity.getApiCacheSize();
        SettingsEntity.databaseCacheSize = settingsFileEntity.getDatabaseCacheSize();
        SettingsEntity.maxAssetCacheAge = settingsFileEntity.getMaxAssetCacheAge();
        SettingsEntity.pageCacheSize = settingsFileEntity.getPageCacheSize();
        SettingsEntity.cacheMethodEnum = settingsFileEntity.getCacheMethodEnum();
        SettingsEntity.databaseType = settingsFileEntity.getDatabaseType();
        SettingsEntity.databaseName = settingsFileEntity.getDatabaseName();
        SettingsEntity.minDatabaseConnections = settingsFileEntity.getMinDatabaseConnections();
        SettingsEntity.maxDatabaseConnections = settingsFileEntity.getMaxDatabaseConnections();
        SettingsEntity.loggingEnabled = settingsFileEntity.isLoggingEnabled();
    }

    public static boolean saveSettings(SettingsFileEntity settingsFileEntity) {
        String json = gson.toJson(settingsFileEntity);
        try {
            FileOutputStream outputStream = new FileOutputStream("Settings.json");
            byte[] strToBytes = json.getBytes();
            outputStream.write(strToBytes);
            outputStream.close();
            return true;
        } catch (IOException e) {
            if (SettingsEntity.debug) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
