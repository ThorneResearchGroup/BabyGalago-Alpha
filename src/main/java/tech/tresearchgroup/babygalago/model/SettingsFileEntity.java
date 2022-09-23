package tech.tresearchgroup.babygalago.model;

import lombok.ToString;
import tech.tresearchgroup.palila.controller.cache.CacheMethodEnum;
import tech.tresearchgroup.palila.model.enums.CompressionMethodEnum;
import tech.tresearchgroup.palila.model.enums.DatabaseTypeEnum;
import tech.tresearchgroup.palila.model.enums.SearchMethodEnum;
import tech.tresearchgroup.schemas.galago.enums.*;

@ToString
public class SettingsFileEntity {
    private InterfaceMethodEnum interfaceMethod;
    private PlaybackQualityEnum defaultPlaybackQuality;
    private boolean debug;
    private boolean maintenanceMode;
    private boolean enableSecurity;
    private CompressionMethodEnum compressionMethod;
    private int compressionQuality;
    private String issuer;
    private String secretKey;
    private String searchHost;
    private String searchKey;
    private DisplayModeEnum displayMode;
    private EncoderProgramEnum encoderProgram;
    private InspectorProgramEnum inspectorProgram;
    private AudioCodecEnum audioCodec;
    private AudioRateEnum audioRate;
    private EncoderPresetEnum audioPreset;
    private VideoContainerEnum videoContainer;
    private VideoCodecEnum videoCodec;
    private EncoderPresetEnum encoderPreset;
    private boolean videoTuneFilm;
    private boolean videoTuneAnimation;
    private boolean videoTuneGrain;
    private boolean videoTuneStillImage;
    private boolean videoTuneFastDecode;
    private boolean videoTuneZeroLatency;
    private boolean videoFastStart;
    private boolean videoTunePsnr;
    private boolean videoTuneSsnr;
    private int videoCrf;
    private boolean videoBlackBorder;
    private boolean videoCudaAcceleration;

    private int oneFourFourVideoTranscodeBitrate;

    private int twoFourZeroVideoTranscodeBitrate;

    private int threeSixZeroVideoTranscodeBitrate;

    private int fourEightZeroVideoTranscodeBitrate;


    private int sevenTwoZeroVideoTranscodeBitrate;

    private int oneZeroEightZeroVideoTranscodeBitrate;

    private int twoKVideoTranscodeBitrate;
    private int fourKVideoTranscodeBitrate;
    private int eightKVideoTranscodeBitrate;
    private boolean tableShowPoster;
    private boolean tableShowName;
    private boolean tableShowRuntime;
    private boolean tableShowGenre;
    private boolean tableShowMpaaRating;
    private boolean tableShowUserRating;
    private boolean tableShowLanguage;
    private boolean tableShowReleaseDate;
    private boolean tableShowActions;
    private boolean bookLibraryEnable;
    private String bookLibraryPath;
    private boolean bookScanEnable;

    private int bookScanFrequencyTime;
    private ScanFrequencyEnum bookScanFrequencyType;
    private boolean gameLibraryEnable;
    private String gameLibraryPath;
    private boolean gameScanEnable;

    private int gameScanFrequencyTime;
    private ScanFrequencyEnum gameScanFrequencyType;
    private boolean movieLibraryEnable;
    private String movieLibraryPath;
    private boolean movieScanEnable;
    private boolean moviePreTranscodeEnable;
    private boolean moviePreTranscode144p;
    private boolean moviePreTranscode240p;
    private boolean moviePreTranscode360p;
    private boolean moviePreTranscode480p;
    private boolean moviePreTranscode720p;
    private boolean moviePreTranscode1080p;
    private boolean moviePreTranscode2k;
    private boolean moviePreTranscode4k;
    private boolean moviePreTranscode8k;

    private int movieScanFrequencyTime;
    private ScanFrequencyEnum movieScanFrequencyType;
    private String moviePreTranscodeLibraryPath;
    private boolean musicLibraryEnable;
    private String musicLibraryPath;
    private boolean musicScanEnable;
    private boolean musicPreTranscodeEnable;
    private boolean musicPreTranscode64k;
    private boolean musicPreTranscode96k;
    private boolean musicPreTranscode128k;
    private boolean musicPreTranscode320k;
    private boolean musicPreTranscode1411k;

    private int musicScanFrequencyTime;
    private ScanFrequencyEnum musicScanFrequencyType;
    private String musicPreTranscodeLibraryPath;
    private boolean tvShowLibraryEnable;
    private String tvShowLibraryPath;
    private boolean tvShowScanEnable;
    private boolean tvShowPreTranscodeEnable;
    private boolean tvShowPreTranscode144p;
    private boolean tvShowPreTranscode240p;
    private boolean tvShowPreTranscode360p;
    private boolean tvShowPreTranscode480p;
    private boolean tvShowPreTranscode720p;
    private boolean tvShowPreTranscode1080p;
    private boolean tvShowPreTranscode2k;
    private boolean tvShowPreTranscode4k;
    private boolean tvShowPreTranscode8k;

    private int tvShowScanFrequencyTime;
    private ScanFrequencyEnum tvShowScanFrequencyType;
    private String tvShowPreTranscodeLibraryPath;
    private String serverName;
    private boolean allowRegistration;
    private boolean homePageShowNewMovie;
    private boolean homePageShowNewTvShow;
    private boolean homePageShowNewGame;
    private boolean homePageShowNewBook;
    private boolean homePageShowNewMusic;
    private boolean homePageShowPopularMovie;
    private boolean homePageShowPopularTvShow;
    private boolean homePageShowPopularGame;
    private boolean homePageShowPopularBook;
    private boolean homePageShowPopularMusic;
    private SearchMethodEnum searchMethod;

    private int maxSearchResults;

    private int maxUIBrowseResults;
    private int maxAPIBrowseResults;

    private int cardWidth;
    private boolean stickyTopMenu;
    private boolean cacheEnable;
    private long apiCacheSize;
    private long databaseCacheSize;
    private long pageCacheSize;
    private int maxAssetCacheAge;
    private CacheMethodEnum cacheMethodEnum;
    private DatabaseTypeEnum databaseType;
    private String databaseName;
    private int minDatabaseConnections;
    private int maxDatabaseConnections;
    private boolean loggingEnabled;

    public SettingsFileEntity() {
    }

    public SettingsFileEntity(InterfaceMethodEnum interfaceMethod,
                              PlaybackQualityEnum defaultPlaybackQuality,
                              boolean debug,
                              boolean maintenanceMode,
                              boolean enableSecurity,
                              CompressionMethodEnum compressionMethod,
                              int compressionQuality,
                              String issuer,
                              String secretKey,
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
                              EncoderPresetEnum encoderPreset,
                              boolean videoTuneFilm,
                              boolean videoTuneAnimation,
                              boolean videoTuneGrain,
                              boolean videoTuneStillImage,
                              boolean videoTuneFastDecode,
                              boolean videoTuneZeroLatency,
                              boolean videoFastStart,
                              boolean videoTunePsnr,
                              boolean videoTuneSsnr,
                              int videoCrf,
                              boolean videoBlackBorder,
                              boolean videoCudaAcceleration,
                              int oneFourFourVideoTranscodeBitrate,
                              int twoFourZeroVideoTranscodeBitrate,
                              int threeSixZeroVideoTranscodeBitrate,
                              int fourEightZeroVideoTranscodeBitrate,
                              int sevenTwoZeroVideoTranscodeBitrate,
                              int oneZeroEightZeroVideoTranscodeBitrate,
                              int twoKVideoTranscodeBitrate,
                              int fourKVideoTranscodeBitrate,
                              int eightKVideoTranscodeBitrate,
                              boolean tableShowPoster,
                              boolean tableShowName,
                              boolean tableShowRuntime,
                              boolean tableShowGenre,
                              boolean tableShowMpaaRating,
                              boolean tableShowUserRating,
                              boolean tableShowLanguage,
                              boolean tableShowReleaseDate,
                              boolean tableShowActions,
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
                              boolean moviePreTranscode144p,
                              boolean moviePreTranscode240p,
                              boolean moviePreTranscode360p,
                              boolean moviePreTranscode480p,
                              boolean moviePreTranscode720p,
                              boolean moviePreTranscode1080p,
                              boolean moviePreTranscode2k,
                              boolean moviePreTranscode4k,
                              boolean moviePreTranscode8k,
                              int movieScanFrequencyTime,
                              ScanFrequencyEnum movieScanFrequencyType,
                              String moviePreTranscodeLibraryPath,
                              boolean musicLibraryEnable,
                              String musicLibraryPath,
                              boolean musicScanEnable,
                              boolean musicPreTranscodeEnable,
                              boolean musicPreTranscode64k,
                              boolean musicPreTranscode96k,
                              boolean musicPreTranscode128k,
                              boolean musicPreTranscode320k,
                              boolean musicPreTranscode1411k,
                              int musicScanFrequencyTime,
                              ScanFrequencyEnum musicScanFrequencyType,
                              String musicPreTranscodeLibraryPath,
                              boolean tvShowLibraryEnable,
                              String tvShowLibraryPath,
                              boolean tvShowScanEnable,
                              boolean tvShowPreTranscodeEnable,
                              boolean tvShowPreTranscode144p,
                              boolean tvShowPreTranscode240p,
                              boolean tvShowPreTranscode360p,
                              boolean tvShowPreTranscode480p,
                              boolean tvShowPreTranscode720p,
                              boolean tvShowPreTranscode1080p,
                              boolean tvShowPreTranscode2k,
                              boolean tvShowPreTranscode4k,
                              boolean tvShowPreTranscode8k,
                              int tvShowScanFrequencyTime,
                              ScanFrequencyEnum tvShowScanFrequencyType,
                              String tvShowPreTranscodeLibraryPath,
                              String serverName,
                              boolean allowRegistration,
                              boolean homePageShowNewMovie,
                              boolean homePageShowNewTvShow,
                              boolean homePageShowNewGame,
                              boolean homePageShowNewBook,
                              boolean homePageShowNewMusic,
                              boolean homePageShowPopularMovie,
                              boolean homePageShowPopularTvShow,
                              boolean homePageShowPopularGame,
                              boolean homePageShowPopularBook,
                              boolean homePageShowPopularMusic,
                              SearchMethodEnum searchMethod,
                              int maxSearchResults,
                              int maxUIBrowseResults,
                              int maxAPIBrowseResults,
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
                              int maxDatabaseConnections,
                              boolean loggingEnabled) {
        this.interfaceMethod = interfaceMethod;
        this.defaultPlaybackQuality = defaultPlaybackQuality;
        this.debug = debug;
        this.maintenanceMode = maintenanceMode;
        this.enableSecurity = enableSecurity;
        this.compressionMethod = compressionMethod;
        this.compressionQuality = compressionQuality;
        this.issuer = issuer;
        this.secretKey = secretKey;
        this.searchHost = searchHost;
        this.searchKey = searchKey;
        this.displayMode = displayMode;
        this.encoderProgram = encoderProgram;
        this.inspectorProgram = inspectorProgram;
        this.audioCodec = audioCodec;
        this.audioRate = audioRate;
        this.audioPreset = audioPreset;
        this.videoContainer = videoContainer;
        this.videoCodec = videoCodec;
        this.encoderPreset = encoderPreset;
        this.videoTuneFilm = videoTuneFilm;
        this.videoTuneAnimation = videoTuneAnimation;
        this.videoTuneGrain = videoTuneGrain;
        this.videoTuneStillImage = videoTuneStillImage;
        this.videoTuneFastDecode = videoTuneFastDecode;
        this.videoTuneZeroLatency = videoTuneZeroLatency;
        this.videoFastStart = videoFastStart;
        this.videoTunePsnr = videoTunePsnr;
        this.videoTuneSsnr = videoTuneSsnr;
        this.videoCrf = videoCrf;
        this.videoBlackBorder = videoBlackBorder;
        this.videoCudaAcceleration = videoCudaAcceleration;
        this.oneFourFourVideoTranscodeBitrate = oneFourFourVideoTranscodeBitrate;
        this.twoFourZeroVideoTranscodeBitrate = twoFourZeroVideoTranscodeBitrate;
        this.threeSixZeroVideoTranscodeBitrate = threeSixZeroVideoTranscodeBitrate;
        this.fourEightZeroVideoTranscodeBitrate = fourEightZeroVideoTranscodeBitrate;
        this.sevenTwoZeroVideoTranscodeBitrate = sevenTwoZeroVideoTranscodeBitrate;
        this.oneZeroEightZeroVideoTranscodeBitrate = oneZeroEightZeroVideoTranscodeBitrate;
        this.twoKVideoTranscodeBitrate = twoKVideoTranscodeBitrate;
        this.fourKVideoTranscodeBitrate = fourKVideoTranscodeBitrate;
        this.eightKVideoTranscodeBitrate = eightKVideoTranscodeBitrate;
        this.tableShowPoster = tableShowPoster;
        this.tableShowName = tableShowName;
        this.tableShowRuntime = tableShowRuntime;
        this.tableShowGenre = tableShowGenre;
        this.tableShowMpaaRating = tableShowMpaaRating;
        this.tableShowUserRating = tableShowUserRating;
        this.tableShowLanguage = tableShowLanguage;
        this.tableShowReleaseDate = tableShowReleaseDate;
        this.tableShowActions = tableShowActions;
        this.bookLibraryEnable = bookLibraryEnable;
        this.bookLibraryPath = bookLibraryPath;
        this.bookScanEnable = bookScanEnable;
        this.bookScanFrequencyTime = bookScanFrequencyTime;
        this.bookScanFrequencyType = bookScanFrequencyType;
        this.gameLibraryEnable = gameLibraryEnable;
        this.gameLibraryPath = gameLibraryPath;
        this.gameScanEnable = gameScanEnable;
        this.gameScanFrequencyTime = gameScanFrequencyTime;
        this.gameScanFrequencyType = gameScanFrequencyType;
        this.movieLibraryEnable = movieLibraryEnable;
        this.movieLibraryPath = movieLibraryPath;
        this.movieScanEnable = movieScanEnable;
        this.moviePreTranscodeEnable = moviePreTranscodeEnable;
        this.moviePreTranscode144p = moviePreTranscode144p;
        this.moviePreTranscode240p = moviePreTranscode240p;
        this.moviePreTranscode360p = moviePreTranscode360p;
        this.moviePreTranscode480p = moviePreTranscode480p;
        this.moviePreTranscode720p = moviePreTranscode720p;
        this.moviePreTranscode1080p = moviePreTranscode1080p;
        this.moviePreTranscode2k = moviePreTranscode2k;
        this.moviePreTranscode4k = moviePreTranscode4k;
        this.moviePreTranscode8k = moviePreTranscode8k;
        this.movieScanFrequencyTime = movieScanFrequencyTime;
        this.movieScanFrequencyType = movieScanFrequencyType;
        this.moviePreTranscodeLibraryPath = moviePreTranscodeLibraryPath;
        this.musicLibraryEnable = musicLibraryEnable;
        this.musicLibraryPath = musicLibraryPath;
        this.musicScanEnable = musicScanEnable;
        this.musicPreTranscodeEnable = musicPreTranscodeEnable;
        this.musicPreTranscode64k = musicPreTranscode64k;
        this.musicPreTranscode96k = musicPreTranscode96k;
        this.musicPreTranscode128k = musicPreTranscode128k;
        this.musicPreTranscode320k = musicPreTranscode320k;
        this.musicPreTranscode1411k = musicPreTranscode1411k;
        this.musicScanFrequencyTime = musicScanFrequencyTime;
        this.musicScanFrequencyType = musicScanFrequencyType;
        this.musicPreTranscodeLibraryPath = musicPreTranscodeLibraryPath;
        this.tvShowLibraryEnable = tvShowLibraryEnable;
        this.tvShowLibraryPath = tvShowLibraryPath;
        this.tvShowScanEnable = tvShowScanEnable;
        this.tvShowPreTranscodeEnable = tvShowPreTranscodeEnable;
        this.tvShowPreTranscode144p = tvShowPreTranscode144p;
        this.tvShowPreTranscode240p = tvShowPreTranscode240p;
        this.tvShowPreTranscode360p = tvShowPreTranscode360p;
        this.tvShowPreTranscode480p = tvShowPreTranscode480p;
        this.tvShowPreTranscode720p = tvShowPreTranscode720p;
        this.tvShowPreTranscode1080p = tvShowPreTranscode1080p;
        this.tvShowPreTranscode2k = tvShowPreTranscode2k;
        this.tvShowPreTranscode4k = tvShowPreTranscode4k;
        this.tvShowPreTranscode8k = tvShowPreTranscode8k;
        this.tvShowScanFrequencyTime = tvShowScanFrequencyTime;
        this.tvShowScanFrequencyType = tvShowScanFrequencyType;
        this.tvShowPreTranscodeLibraryPath = tvShowPreTranscodeLibraryPath;
        this.serverName = serverName;
        this.allowRegistration = allowRegistration;
        this.homePageShowNewMovie = homePageShowNewMovie;
        this.homePageShowNewTvShow = homePageShowNewTvShow;
        this.homePageShowNewGame = homePageShowNewGame;
        this.homePageShowNewBook = homePageShowNewBook;
        this.homePageShowNewMusic = homePageShowNewMusic;
        this.homePageShowPopularMovie = homePageShowPopularMovie;
        this.homePageShowPopularTvShow = homePageShowPopularTvShow;
        this.homePageShowPopularGame = homePageShowPopularGame;
        this.homePageShowPopularBook = homePageShowPopularBook;
        this.homePageShowPopularMusic = homePageShowPopularMusic;
        this.searchMethod = searchMethod;
        this.maxSearchResults = maxSearchResults;
        this.maxUIBrowseResults = maxUIBrowseResults;
        this.maxAPIBrowseResults = maxAPIBrowseResults;
        this.cardWidth = cardWidth;
        this.stickyTopMenu = stickyTopMenu;
        this.cacheEnable = cacheEnable;
        this.apiCacheSize = apiCacheSize;
        this.databaseCacheSize = databaseCacheSize;
        this.pageCacheSize = pageCacheSize;
        this.maxAssetCacheAge = maxAssetCacheAge;
        this.databaseType = databaseType;
        this.databaseName = databaseName;
        this.minDatabaseConnections = minDatabaseConnections;
        this.maxDatabaseConnections = maxDatabaseConnections;
        this.loggingEnabled = loggingEnabled;
    }

    public InterfaceMethodEnum getInterfaceMethod() {
        return interfaceMethod;
    }

    public void setInterfaceMethod(InterfaceMethodEnum interfaceMethod) {
        this.interfaceMethod = interfaceMethod;
    }

    public PlaybackQualityEnum getDefaultPlaybackQuality() {
        return defaultPlaybackQuality;
    }

    public void setDefaultPlaybackQuality(PlaybackQualityEnum defaultPlaybackQuality) {
        this.defaultPlaybackQuality = defaultPlaybackQuality;
    }

    public boolean isDebug() {
        return debug;
    }

    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    public boolean isMaintenanceMode() {
        return maintenanceMode;
    }

    public void setMaintenanceMode(boolean maintenanceMode) {
        this.maintenanceMode = maintenanceMode;
    }

    public boolean isEnableSecurity() {
        return enableSecurity;
    }

    public void setEnableSecurity(boolean enableSecurity) {
        this.enableSecurity = enableSecurity;
    }

    public CompressionMethodEnum getCompressionMethod() {
        return compressionMethod;
    }

    public void setCompressionMethod(CompressionMethodEnum compressionMethod) {
        this.compressionMethod = compressionMethod;
    }

    public int getCompressionQuality() {
        return compressionQuality;
    }

    public void setCompressionQuality(int compressionQuality) {
        this.compressionQuality = compressionQuality;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getSearchHost() {
        return searchHost;
    }

    public void setSearchHost(String searchHost) {
        this.searchHost = searchHost;
    }

    public String getSearchKey() {
        return searchKey;
    }

    public void setSearchKey(String searchKey) {
        this.searchKey = searchKey;
    }

    public DisplayModeEnum getDisplayMode() {
        return displayMode;
    }

    public void setDisplayMode(DisplayModeEnum displayMode) {
        this.displayMode = displayMode;
    }

    public EncoderProgramEnum getEncoderProgram() {
        return encoderProgram;
    }

    public void setEncoderProgram(EncoderProgramEnum encoderProgram) {
        this.encoderProgram = encoderProgram;
    }

    public InspectorProgramEnum getInspectorProgram() {
        return inspectorProgram;
    }

    public void setInspectorProgram(InspectorProgramEnum inspectorProgram) {
        this.inspectorProgram = inspectorProgram;
    }

    public AudioCodecEnum getAudioCodec() {
        return audioCodec;
    }

    public void setAudioCodec(AudioCodecEnum audioCodec) {
        this.audioCodec = audioCodec;
    }

    public AudioRateEnum getAudioRate() {
        return audioRate;
    }

    public void setAudioRate(AudioRateEnum audioRate) {
        this.audioRate = audioRate;
    }

    public EncoderPresetEnum getAudioPreset() {
        return audioPreset;
    }

    public void setAudioPreset(EncoderPresetEnum audioPreset) {
        this.audioPreset = audioPreset;
    }

    public VideoContainerEnum getVideoContainer() {
        return videoContainer;
    }

    public void setVideoContainer(VideoContainerEnum videoContainer) {
        this.videoContainer = videoContainer;
    }

    public VideoCodecEnum getVideoCodec() {
        return videoCodec;
    }

    public void setVideoCodec(VideoCodecEnum videoCodec) {
        this.videoCodec = videoCodec;
    }

    public EncoderPresetEnum getEncoderPreset() {
        return encoderPreset;
    }

    public void setEncoderPreset(EncoderPresetEnum encoderPreset) {
        this.encoderPreset = encoderPreset;
    }

    public boolean isVideoTuneFilm() {
        return videoTuneFilm;
    }

    public void setVideoTuneFilm(boolean videoTuneFilm) {
        this.videoTuneFilm = videoTuneFilm;
    }

    public boolean isVideoTuneAnimation() {
        return videoTuneAnimation;
    }

    public void setVideoTuneAnimation(boolean videoTuneAnimation) {
        this.videoTuneAnimation = videoTuneAnimation;
    }

    public boolean isVideoTuneGrain() {
        return videoTuneGrain;
    }

    public void setVideoTuneGrain(boolean videoTuneGrain) {
        this.videoTuneGrain = videoTuneGrain;
    }

    public boolean isVideoTuneStillImage() {
        return videoTuneStillImage;
    }

    public void setVideoTuneStillImage(boolean videoTuneStillImage) {
        this.videoTuneStillImage = videoTuneStillImage;
    }

    public boolean isVideoTuneFastDecode() {
        return videoTuneFastDecode;
    }

    public void setVideoTuneFastDecode(boolean videoTuneFastDecode) {
        this.videoTuneFastDecode = videoTuneFastDecode;
    }

    public boolean isVideoTuneZeroLatency() {
        return videoTuneZeroLatency;
    }

    public void setVideoTuneZeroLatency(boolean videoTuneZeroLatency) {
        this.videoTuneZeroLatency = videoTuneZeroLatency;
    }

    public boolean isVideoFastStart() {
        return videoFastStart;
    }

    public void setVideoFastStart(boolean videoFastStart) {
        this.videoFastStart = videoFastStart;
    }

    public boolean isVideoTunePsnr() {
        return videoTunePsnr;
    }

    public void setVideoTunePsnr(boolean videoTunePsnr) {
        this.videoTunePsnr = videoTunePsnr;
    }

    public boolean isVideoTuneSsnr() {
        return videoTuneSsnr;
    }

    public void setVideoTuneSsnr(boolean videoTuneSsnr) {
        this.videoTuneSsnr = videoTuneSsnr;
    }

    public int getVideoCrf() {
        return videoCrf;
    }

    public void setVideoCrf(int videoCrf) {
        this.videoCrf = videoCrf;
    }

    public boolean isVideoBlackBorder() {
        return videoBlackBorder;
    }

    public void setVideoBlackBorder(boolean videoBlackBorder) {
        this.videoBlackBorder = videoBlackBorder;
    }

    public boolean isVideoCudaAcceleration() {
        return videoCudaAcceleration;
    }

    public void setVideoCudaAcceleration(boolean videoCudaAcceleration) {
        this.videoCudaAcceleration = videoCudaAcceleration;
    }

    public int getOneFourFourVideoTranscodeBitrate() {
        return oneFourFourVideoTranscodeBitrate;
    }

    public void setOneFourFourVideoTranscodeBitrate(int oneFourFourVideoTranscodeBitrate) {
        this.oneFourFourVideoTranscodeBitrate = oneFourFourVideoTranscodeBitrate;
    }

    public int getTwoFourZeroVideoTranscodeBitrate() {
        return twoFourZeroVideoTranscodeBitrate;
    }

    public void setTwoFourZeroVideoTranscodeBitrate(int twoFourZeroVideoTranscodeBitrate) {
        this.twoFourZeroVideoTranscodeBitrate = twoFourZeroVideoTranscodeBitrate;
    }

    public int getThreeSixZeroVideoTranscodeBitrate() {
        return threeSixZeroVideoTranscodeBitrate;
    }

    public void setThreeSixZeroVideoTranscodeBitrate(int threeSixZeroVideoTranscodeBitrate) {
        this.threeSixZeroVideoTranscodeBitrate = threeSixZeroVideoTranscodeBitrate;
    }

    public int getFourEightZeroVideoTranscodeBitrate() {
        return fourEightZeroVideoTranscodeBitrate;
    }

    public void setFourEightZeroVideoTranscodeBitrate(int fourEightZeroVideoTranscodeBitrate) {
        this.fourEightZeroVideoTranscodeBitrate = fourEightZeroVideoTranscodeBitrate;
    }

    public int getSevenTwoZeroVideoTranscodeBitrate() {
        return sevenTwoZeroVideoTranscodeBitrate;
    }

    public void setSevenTwoZeroVideoTranscodeBitrate(int sevenTwoZeroVideoTranscodeBitrate) {
        this.sevenTwoZeroVideoTranscodeBitrate = sevenTwoZeroVideoTranscodeBitrate;
    }

    public int getOneZeroEightZeroVideoTranscodeBitrate() {
        return oneZeroEightZeroVideoTranscodeBitrate;
    }

    public void setOneZeroEightZeroVideoTranscodeBitrate(int oneZeroEightZeroVideoTranscodeBitrate) {
        this.oneZeroEightZeroVideoTranscodeBitrate = oneZeroEightZeroVideoTranscodeBitrate;
    }

    public int getTwoKVideoTranscodeBitrate() {
        return twoKVideoTranscodeBitrate;
    }

    public void setTwoKVideoTranscodeBitrate(int twoKVideoTranscodeBitrate) {
        this.twoKVideoTranscodeBitrate = twoKVideoTranscodeBitrate;
    }

    public int getFourKVideoTranscodeBitrate() {
        return fourKVideoTranscodeBitrate;
    }

    public void setFourKVideoTranscodeBitrate(int fourKVideoTranscodeBitrate) {
        this.fourKVideoTranscodeBitrate = fourKVideoTranscodeBitrate;
    }

    public int getEightKVideoTranscodeBitrate() {
        return eightKVideoTranscodeBitrate;
    }

    public void setEightKVideoTranscodeBitrate(int eightKVideoTranscodeBitrate) {
        this.eightKVideoTranscodeBitrate = eightKVideoTranscodeBitrate;
    }

    public boolean isTableShowPoster() {
        return tableShowPoster;
    }

    public void setTableShowPoster(boolean tableShowPoster) {
        this.tableShowPoster = tableShowPoster;
    }

    public boolean isTableShowName() {
        return tableShowName;
    }

    public void setTableShowName(boolean tableShowName) {
        this.tableShowName = tableShowName;
    }

    public boolean isTableShowRuntime() {
        return tableShowRuntime;
    }

    public void setTableShowRuntime(boolean tableShowRuntime) {
        this.tableShowRuntime = tableShowRuntime;
    }

    public boolean isTableShowGenre() {
        return tableShowGenre;
    }

    public void setTableShowGenre(boolean tableShowGenre) {
        this.tableShowGenre = tableShowGenre;
    }

    public boolean isTableShowMpaaRating() {
        return tableShowMpaaRating;
    }

    public void setTableShowMpaaRating(boolean tableShowMpaaRating) {
        this.tableShowMpaaRating = tableShowMpaaRating;
    }

    public boolean isTableShowUserRating() {
        return tableShowUserRating;
    }

    public void setTableShowUserRating(boolean tableShowUserRating) {
        this.tableShowUserRating = tableShowUserRating;
    }

    public boolean isTableShowLanguage() {
        return tableShowLanguage;
    }

    public void setTableShowLanguage(boolean tableShowLanguage) {
        this.tableShowLanguage = tableShowLanguage;
    }

    public boolean isTableShowReleaseDate() {
        return tableShowReleaseDate;
    }

    public void setTableShowReleaseDate(boolean tableShowReleaseDate) {
        this.tableShowReleaseDate = tableShowReleaseDate;
    }

    public boolean isTableShowActions() {
        return tableShowActions;
    }

    public void setTableShowActions(boolean tableShowActions) {
        this.tableShowActions = tableShowActions;
    }

    public boolean isBookLibraryEnable() {
        return bookLibraryEnable;
    }

    public void setBookLibraryEnable(boolean bookLibraryEnable) {
        this.bookLibraryEnable = bookLibraryEnable;
    }

    public String getBookLibraryPath() {
        return bookLibraryPath;
    }

    public void setBookLibraryPath(String bookLibraryPath) {
        this.bookLibraryPath = bookLibraryPath;
    }

    public boolean isBookScanEnable() {
        return bookScanEnable;
    }

    public void setBookScanEnable(boolean bookScanEnable) {
        this.bookScanEnable = bookScanEnable;
    }

    public int getBookScanFrequencyTime() {
        return bookScanFrequencyTime;
    }

    public void setBookScanFrequencyTime(int bookScanFrequencyTime) {
        this.bookScanFrequencyTime = bookScanFrequencyTime;
    }

    public ScanFrequencyEnum getBookScanFrequencyType() {
        return bookScanFrequencyType;
    }

    public void setBookScanFrequencyType(ScanFrequencyEnum bookScanFrequencyType) {
        this.bookScanFrequencyType = bookScanFrequencyType;
    }

    public boolean isGameLibraryEnable() {
        return gameLibraryEnable;
    }

    public void setGameLibraryEnable(boolean gameLibraryEnable) {
        this.gameLibraryEnable = gameLibraryEnable;
    }

    public String getGameLibraryPath() {
        return gameLibraryPath;
    }

    public void setGameLibraryPath(String gameLibraryPath) {
        this.gameLibraryPath = gameLibraryPath;
    }

    public boolean isGameScanEnable() {
        return gameScanEnable;
    }

    public void setGameScanEnable(boolean gameScanEnable) {
        this.gameScanEnable = gameScanEnable;
    }

    public int getGameScanFrequencyTime() {
        return gameScanFrequencyTime;
    }

    public void setGameScanFrequencyTime(int gameScanFrequencyTime) {
        this.gameScanFrequencyTime = gameScanFrequencyTime;
    }

    public ScanFrequencyEnum getGameScanFrequencyType() {
        return gameScanFrequencyType;
    }

    public void setGameScanFrequencyType(ScanFrequencyEnum gameScanFrequencyType) {
        this.gameScanFrequencyType = gameScanFrequencyType;
    }

    public boolean isMovieLibraryEnable() {
        return movieLibraryEnable;
    }

    public void setMovieLibraryEnable(boolean movieLibraryEnable) {
        this.movieLibraryEnable = movieLibraryEnable;
    }

    public String getMovieLibraryPath() {
        return movieLibraryPath;
    }

    public void setMovieLibraryPath(String movieLibraryPath) {
        this.movieLibraryPath = movieLibraryPath;
    }

    public boolean isMovieScanEnable() {
        return movieScanEnable;
    }

    public void setMovieScanEnable(boolean movieScanEnable) {
        this.movieScanEnable = movieScanEnable;
    }

    public boolean isMoviePreTranscodeEnable() {
        return moviePreTranscodeEnable;
    }

    public void setMoviePreTranscodeEnable(boolean moviePreTranscodeEnable) {
        this.moviePreTranscodeEnable = moviePreTranscodeEnable;
    }

    public boolean isMoviePreTranscode144p() {
        return moviePreTranscode144p;
    }

    public void setMoviePreTranscode144p(boolean moviePreTranscode144p) {
        this.moviePreTranscode144p = moviePreTranscode144p;
    }

    public boolean isMoviePreTranscode240p() {
        return moviePreTranscode240p;
    }

    public void setMoviePreTranscode240p(boolean moviePreTranscode240p) {
        this.moviePreTranscode240p = moviePreTranscode240p;
    }

    public boolean isMoviePreTranscode360p() {
        return moviePreTranscode360p;
    }

    public void setMoviePreTranscode360p(boolean moviePreTranscode360p) {
        this.moviePreTranscode360p = moviePreTranscode360p;
    }

    public boolean isMoviePreTranscode480p() {
        return moviePreTranscode480p;
    }

    public void setMoviePreTranscode480p(boolean moviePreTranscode480p) {
        this.moviePreTranscode480p = moviePreTranscode480p;
    }

    public boolean isMoviePreTranscode720p() {
        return moviePreTranscode720p;
    }

    public void setMoviePreTranscode720p(boolean moviePreTranscode720p) {
        this.moviePreTranscode720p = moviePreTranscode720p;
    }

    public boolean isMoviePreTranscode1080p() {
        return moviePreTranscode1080p;
    }

    public void setMoviePreTranscode1080p(boolean moviePreTranscode1080p) {
        this.moviePreTranscode1080p = moviePreTranscode1080p;
    }

    public boolean isMoviePreTranscode2k() {
        return moviePreTranscode2k;
    }

    public void setMoviePreTranscode2k(boolean moviePreTranscode2k) {
        this.moviePreTranscode2k = moviePreTranscode2k;
    }

    public boolean isMoviePreTranscode4k() {
        return moviePreTranscode4k;
    }

    public void setMoviePreTranscode4k(boolean moviePreTranscode4k) {
        this.moviePreTranscode4k = moviePreTranscode4k;
    }

    public boolean isMoviePreTranscode8k() {
        return moviePreTranscode8k;
    }

    public void setMoviePreTranscode8k(boolean moviePreTranscode8k) {
        this.moviePreTranscode8k = moviePreTranscode8k;
    }

    public int getMovieScanFrequencyTime() {
        return movieScanFrequencyTime;
    }

    public void setMovieScanFrequencyTime(int movieScanFrequencyTime) {
        this.movieScanFrequencyTime = movieScanFrequencyTime;
    }

    public ScanFrequencyEnum getMovieScanFrequencyType() {
        return movieScanFrequencyType;
    }

    public void setMovieScanFrequencyType(ScanFrequencyEnum movieScanFrequencyType) {
        this.movieScanFrequencyType = movieScanFrequencyType;
    }

    public String getMoviePreTranscodeLibraryPath() {
        return moviePreTranscodeLibraryPath;
    }

    public void setMoviePreTranscodeLibraryPath(String moviePreTranscodeLibraryPath) {
        this.moviePreTranscodeLibraryPath = moviePreTranscodeLibraryPath;
    }

    public boolean isMusicLibraryEnable() {
        return musicLibraryEnable;
    }

    public void setMusicLibraryEnable(boolean musicLibraryEnable) {
        this.musicLibraryEnable = musicLibraryEnable;
    }

    public String getMusicLibraryPath() {
        return musicLibraryPath;
    }

    public void setMusicLibraryPath(String musicLibraryPath) {
        this.musicLibraryPath = musicLibraryPath;
    }

    public boolean isMusicScanEnable() {
        return musicScanEnable;
    }

    public void setMusicScanEnable(boolean musicScanEnable) {
        this.musicScanEnable = musicScanEnable;
    }

    public boolean isMusicPreTranscodeEnable() {
        return musicPreTranscodeEnable;
    }

    public void setMusicPreTranscodeEnable(boolean musicPreTranscodeEnable) {
        this.musicPreTranscodeEnable = musicPreTranscodeEnable;
    }

    public boolean isMusicPreTranscode64k() {
        return musicPreTranscode64k;
    }

    public void setMusicPreTranscode64k(boolean musicPreTranscode64k) {
        this.musicPreTranscode64k = musicPreTranscode64k;
    }

    public boolean isMusicPreTranscode96k() {
        return musicPreTranscode96k;
    }

    public void setMusicPreTranscode96k(boolean musicPreTranscode96k) {
        this.musicPreTranscode96k = musicPreTranscode96k;
    }

    public boolean isMusicPreTranscode128k() {
        return musicPreTranscode128k;
    }

    public void setMusicPreTranscode128k(boolean musicPreTranscode128k) {
        this.musicPreTranscode128k = musicPreTranscode128k;
    }

    public boolean isMusicPreTranscode320k() {
        return musicPreTranscode320k;
    }

    public void setMusicPreTranscode320k(boolean musicPreTranscode320k) {
        this.musicPreTranscode320k = musicPreTranscode320k;
    }

    public boolean isMusicPreTranscode1411k() {
        return musicPreTranscode1411k;
    }

    public void setMusicPreTranscode1411k(boolean musicPreTranscode1411k) {
        this.musicPreTranscode1411k = musicPreTranscode1411k;
    }

    public int getMusicScanFrequencyTime() {
        return musicScanFrequencyTime;
    }

    public void setMusicScanFrequencyTime(int musicScanFrequencyTime) {
        this.musicScanFrequencyTime = musicScanFrequencyTime;
    }

    public ScanFrequencyEnum getMusicScanFrequencyType() {
        return musicScanFrequencyType;
    }

    public void setMusicScanFrequencyType(ScanFrequencyEnum musicScanFrequencyType) {
        this.musicScanFrequencyType = musicScanFrequencyType;
    }

    public String getMusicPreTranscodeLibraryPath() {
        return musicPreTranscodeLibraryPath;
    }

    public void setMusicPreTranscodeLibraryPath(String musicPreTranscodeLibraryPath) {
        this.musicPreTranscodeLibraryPath = musicPreTranscodeLibraryPath;
    }

    public boolean isTvShowLibraryEnable() {
        return tvShowLibraryEnable;
    }

    public void setTvShowLibraryEnable(boolean tvShowLibraryEnable) {
        this.tvShowLibraryEnable = tvShowLibraryEnable;
    }

    public String getTvShowLibraryPath() {
        return tvShowLibraryPath;
    }

    public void setTvShowLibraryPath(String tvShowLibraryPath) {
        this.tvShowLibraryPath = tvShowLibraryPath;
    }

    public boolean isTvShowScanEnable() {
        return tvShowScanEnable;
    }

    public void setTvShowScanEnable(boolean tvShowScanEnable) {
        this.tvShowScanEnable = tvShowScanEnable;
    }

    public boolean isTvShowPreTranscodeEnable() {
        return tvShowPreTranscodeEnable;
    }

    public void setTvShowPreTranscodeEnable(boolean tvShowPreTranscodeEnable) {
        this.tvShowPreTranscodeEnable = tvShowPreTranscodeEnable;
    }

    public boolean isTvShowPreTranscode144p() {
        return tvShowPreTranscode144p;
    }

    public void setTvShowPreTranscode144p(boolean tvShowPreTranscode144p) {
        this.tvShowPreTranscode144p = tvShowPreTranscode144p;
    }

    public boolean isTvShowPreTranscode240p() {
        return tvShowPreTranscode240p;
    }

    public void setTvShowPreTranscode240p(boolean tvShowPreTranscode240p) {
        this.tvShowPreTranscode240p = tvShowPreTranscode240p;
    }

    public boolean isTvShowPreTranscode360p() {
        return tvShowPreTranscode360p;
    }

    public void setTvShowPreTranscode360p(boolean tvShowPreTranscode360p) {
        this.tvShowPreTranscode360p = tvShowPreTranscode360p;
    }

    public boolean isTvShowPreTranscode480p() {
        return tvShowPreTranscode480p;
    }

    public void setTvShowPreTranscode480p(boolean tvShowPreTranscode480p) {
        this.tvShowPreTranscode480p = tvShowPreTranscode480p;
    }

    public boolean isTvShowPreTranscode720p() {
        return tvShowPreTranscode720p;
    }

    public void setTvShowPreTranscode720p(boolean tvShowPreTranscode720p) {
        this.tvShowPreTranscode720p = tvShowPreTranscode720p;
    }

    public boolean isTvShowPreTranscode1080p() {
        return tvShowPreTranscode1080p;
    }

    public void setTvShowPreTranscode1080p(boolean tvShowPreTranscode1080p) {
        this.tvShowPreTranscode1080p = tvShowPreTranscode1080p;
    }

    public boolean isTvShowPreTranscode2k() {
        return tvShowPreTranscode2k;
    }

    public void setTvShowPreTranscode2k(boolean tvShowPreTranscode2k) {
        this.tvShowPreTranscode2k = tvShowPreTranscode2k;
    }

    public boolean isTvShowPreTranscode4k() {
        return tvShowPreTranscode4k;
    }

    public void setTvShowPreTranscode4k(boolean tvShowPreTranscode4k) {
        this.tvShowPreTranscode4k = tvShowPreTranscode4k;
    }

    public boolean isTvShowPreTranscode8k() {
        return tvShowPreTranscode8k;
    }

    public void setTvShowPreTranscode8k(boolean tvShowPreTranscode8k) {
        this.tvShowPreTranscode8k = tvShowPreTranscode8k;
    }

    public int getTvShowScanFrequencyTime() {
        return tvShowScanFrequencyTime;
    }

    public void setTvShowScanFrequencyTime(int tvShowScanFrequencyTime) {
        this.tvShowScanFrequencyTime = tvShowScanFrequencyTime;
    }

    public ScanFrequencyEnum getTvShowScanFrequencyType() {
        return tvShowScanFrequencyType;
    }

    public void setTvShowScanFrequencyType(ScanFrequencyEnum tvShowScanFrequencyType) {
        this.tvShowScanFrequencyType = tvShowScanFrequencyType;
    }

    public String getTvShowPreTranscodeLibraryPath() {
        return tvShowPreTranscodeLibraryPath;
    }

    public void setTvShowPreTranscodeLibraryPath(String tvShowPreTranscodeLibraryPath) {
        this.tvShowPreTranscodeLibraryPath = tvShowPreTranscodeLibraryPath;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public boolean isAllowRegistration() {
        return allowRegistration;
    }

    public void setAllowRegistration(boolean allowRegistration) {
        this.allowRegistration = allowRegistration;
    }

    public boolean isHomePageShowNewMovie() {
        return homePageShowNewMovie;
    }

    public void setHomePageShowNewMovie(boolean homePageShowNewMovie) {
        this.homePageShowNewMovie = homePageShowNewMovie;
    }

    public boolean isHomePageShowNewTvShow() {
        return homePageShowNewTvShow;
    }

    public void setHomePageShowNewTvShow(boolean homePageShowNewTvShow) {
        this.homePageShowNewTvShow = homePageShowNewTvShow;
    }

    public boolean isHomePageShowNewGame() {
        return homePageShowNewGame;
    }

    public void setHomePageShowNewGame(boolean homePageShowNewGame) {
        this.homePageShowNewGame = homePageShowNewGame;
    }

    public boolean isHomePageShowNewBook() {
        return homePageShowNewBook;
    }

    public void setHomePageShowNewBook(boolean homePageShowNewBook) {
        this.homePageShowNewBook = homePageShowNewBook;
    }

    public boolean isHomePageShowNewMusic() {
        return homePageShowNewMusic;
    }

    public void setHomePageShowNewMusic(boolean homePageShowNewMusic) {
        this.homePageShowNewMusic = homePageShowNewMusic;
    }

    public boolean isHomePageShowPopularMovie() {
        return homePageShowPopularMovie;
    }

    public void setHomePageShowPopularMovie(boolean homePageShowPopularMovie) {
        this.homePageShowPopularMovie = homePageShowPopularMovie;
    }

    public boolean isHomePageShowPopularTvShow() {
        return homePageShowPopularTvShow;
    }

    public void setHomePageShowPopularTvShow(boolean homePageShowPopularTvShow) {
        this.homePageShowPopularTvShow = homePageShowPopularTvShow;
    }

    public boolean isHomePageShowPopularGame() {
        return homePageShowPopularGame;
    }

    public void setHomePageShowPopularGame(boolean homePageShowPopularGame) {
        this.homePageShowPopularGame = homePageShowPopularGame;
    }

    public boolean isHomePageShowPopularBook() {
        return homePageShowPopularBook;
    }

    public void setHomePageShowPopularBook(boolean homePageShowPopularBook) {
        this.homePageShowPopularBook = homePageShowPopularBook;
    }

    public boolean isHomePageShowPopularMusic() {
        return homePageShowPopularMusic;
    }

    public void setHomePageShowPopularMusic(boolean homePageShowPopularMusic) {
        this.homePageShowPopularMusic = homePageShowPopularMusic;
    }

    public SearchMethodEnum getSearchMethod() {
        return searchMethod;
    }

    public void setSearchMethod(SearchMethodEnum searchMethod) {
        this.searchMethod = searchMethod;
    }

    public int getMaxSearchResults() {
        return maxSearchResults;
    }

    public void setMaxSearchResults(int maxSearchResults) {
        this.maxSearchResults = maxSearchResults;
    }

    public int getMaxUIBrowseResults() {
        return maxUIBrowseResults;
    }

    public void setMaxUIBrowseResults(int maxUIBrowseResults) {
        this.maxUIBrowseResults = maxUIBrowseResults;
    }

    public int getMaxAPIBrowseResults() {
        return maxAPIBrowseResults;
    }

    public void setMaxAPIBrowseResults(int maxAPIBrowseResults) {
        this.maxAPIBrowseResults = maxAPIBrowseResults;
    }

    public int getCardWidth() {
        return cardWidth;
    }

    public void setCardWidth(int cardWidth) {
        this.cardWidth = cardWidth;
    }

    public boolean isStickyTopMenu() {
        return stickyTopMenu;
    }

    public void setStickyTopMenu(boolean stickyTopMenu) {
        this.stickyTopMenu = stickyTopMenu;
    }

    public boolean isCacheEnable() {
        return cacheEnable;
    }

    public void setCacheEnable(boolean cacheEnable) {
        this.cacheEnable = cacheEnable;
    }

    public int getMaxAssetCacheAge() {
        return maxAssetCacheAge;
    }

    public void setMaxAssetCacheAge(int maxAssetCacheAge) {
        this.maxAssetCacheAge = maxAssetCacheAge;
    }

    public CacheMethodEnum getCacheMethodEnum() {
        return cacheMethodEnum;
    }

    public void setCacheMethodEnum(CacheMethodEnum cacheMethodEnum) {
        this.cacheMethodEnum = cacheMethodEnum;
    }

    public long getApiCacheSize() {
        return apiCacheSize;
    }


    public void setApiCacheSize(long apiCacheSize) {
        this.apiCacheSize = apiCacheSize;
    }

    public long getDatabaseCacheSize() {
        return databaseCacheSize;
    }

    public void setDatabaseCacheSize(long databaseCacheSize) {
        this.databaseCacheSize = databaseCacheSize;
    }

    public long getPageCacheSize() {
        return pageCacheSize;
    }

    public void setPageCacheSize(long pageCacheSize) {
        this.pageCacheSize = pageCacheSize;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    public int getMinDatabaseConnections() {
        return minDatabaseConnections;
    }

    public void setMinDatabaseConnections(int minDatabaseConnections) {
        this.minDatabaseConnections = minDatabaseConnections;
    }

    public int getMaxDatabaseConnections() {
        return maxDatabaseConnections;
    }

    public void setMaxDatabaseConnections(int maxDatabaseConnections) {
        this.maxDatabaseConnections = maxDatabaseConnections;
    }

    public DatabaseTypeEnum getDatabaseType() {
        return databaseType;
    }

    public void setDatabaseType(DatabaseTypeEnum databaseType) {
        this.databaseType = databaseType;
    }

    public boolean isLoggingEnabled() {
        return loggingEnabled;
    }

    public void setLoggingEnabled(boolean loggingEnabled) {
        this.loggingEnabled = loggingEnabled;
    }

    public void setDefaults() {
        this.interfaceMethod = InterfaceMethodEnum.MODAL;
        this.defaultPlaybackQuality = PlaybackQualityEnum.ORIGINAL;
        this.displayMode = DisplayModeEnum.POSTER;
        this.encoderProgram = EncoderProgramEnum.FFMPEG;
        this.inspectorProgram = InspectorProgramEnum.MEDIAINFO;
        this.audioCodec = AudioCodecEnum.AC3;
        this.audioRate = AudioRateEnum.fourEightK;
        this.audioPreset = EncoderPresetEnum.VERYFAST;
        this.videoContainer = VideoContainerEnum.MP4;
        this.videoCodec = VideoCodecEnum.H264;
        this.encoderPreset = EncoderPresetEnum.VERYFAST;
        this.bookLibraryEnable = true;
        this.gameLibraryEnable = true;
        this.movieLibraryEnable = true;
        this.musicLibraryEnable = true;
        this.tvShowLibraryEnable = true;
        this.serverName = "Baby Galago Server";
        this.allowRegistration = true;
        this.homePageShowNewBook = true;
        this.homePageShowNewGame = true;
        this.homePageShowNewMovie = true;
        this.homePageShowNewMusic = true;
        this.homePageShowNewTvShow = true;
        this.homePageShowPopularBook = true;
        this.homePageShowPopularGame = true;
        this.homePageShowPopularMovie = true;
        this.homePageShowPopularMusic = true;
        this.homePageShowPopularTvShow = true;
        this.searchMethod = SearchMethodEnum.DATABASE_SEARCH;
        this.maxSearchResults = 24;
        this.maxUIBrowseResults = 24;
        this.maxAPIBrowseResults = 50;
        this.cardWidth = 240;
        this.cacheEnable = true;
        this.maxAssetCacheAge = 604800;
        this.secretKey = "CHANGEME";
        this.issuer = "CHANGEME";
        this.compressionMethod = CompressionMethodEnum.BR;
        this.compressionQuality = 9;
        this.enableSecurity = true;
        this.apiCacheSize = 1000;
        this.databaseCacheSize = 1000;
        this.pageCacheSize = 1000;
        this.databaseType = DatabaseTypeEnum.AUTO;
        this.databaseName = "babygalago";
        this.minDatabaseConnections = 2;
        this.maxDatabaseConnections = 8;
        this.bookLibraryPath = "library/books";
        this.gameLibraryPath = "library/game";
        this.musicLibraryPath = "library/music";
        this.movieLibraryPath = "library/movie";
        this.tvShowLibraryPath = "library/tvShow";
        this.tvShowPreTranscodeLibraryPath = "library/tvShow/pretranscode";
        this.moviePreTranscodeLibraryPath = "library/movie/pretranscode";
        this.musicPreTranscodeLibraryPath = "library/music/pretranscode";
    }
}