package tech.tresearchgroup.babygalago.model;

import lombok.Data;
import tech.tresearchgroup.palila.model.BaseSettings;
import tech.tresearchgroup.palila.model.enums.DatabaseTypeEnum;
import tech.tresearchgroup.schemas.galago.enums.*;

@Data
public class SettingsEntity extends BaseSettings {
    public static InterfaceMethodEnum interfaceMethod;
    public static PlaybackQualityEnum defaultPlaybackQuality;
    public static DisplayModeEnum displayMode;
    public static EncoderProgramEnum encoderProgram;
    public static InspectorProgramEnum inspectorProgram;
    public static AudioCodecEnum audioCodec;
    public static AudioRateEnum audioRate;
    public static EncoderPresetEnum audioPreset;
    public static VideoContainerEnum videoContainer;
    public static VideoCodecEnum videoCodec;
    public static EncoderPresetEnum encoderPreset;
    public static boolean videoTuneFilm;
    public static boolean videoTuneAnimation;
    public static boolean videoTuneGrain;
    public static boolean videoTuneStillImage;
    public static boolean videoTuneFastDecode;
    public static boolean videoTuneZeroLatency;
    public static boolean videoFastStart;
    public static boolean videoTunePsnr;
    public static boolean videoTuneSsnr;

    public static int videoCrf;
    public static boolean videoBlackBorder;
    public static boolean videoCudaAcceleration;

    public static int oneFourFourVideoTranscodeBitrate;

    public static int twoFourZeroVideoTranscodeBitrate;

    public static int threeSixZeroVideoTranscodeBitrate;

    public static int fourEightZeroVideoTranscodeBitrate;

    public static int sevenTwoZeroVideoTranscodeBitrate;

    public static int oneZeroEightZeroVideoTranscodeBitrate;

    public static int twoKVideoTranscodeBitrate;

    public static int fourKVideoTranscodeBitrate;

    public static int eightKVideoTranscodeBitrate;
    public static boolean tableShowPoster;
    public static boolean tableShowName;
    public static boolean tableShowRuntime;
    public static boolean tableShowGenre;
    public static boolean tableShowMpaaRating;
    public static boolean tableShowUserRating;
    public static boolean tableShowLanguage;
    public static boolean tableShowReleaseDate;
    public static boolean tableShowActions;
    public static boolean bookLibraryEnable;
    public static String bookLibraryPath;
    public static boolean bookScanEnable;

    public static int bookScanFrequencyTime;
    public static ScanFrequencyEnum bookScanFrequencyType;
    public static boolean gameLibraryEnable;
    public static String gameLibraryPath;
    public static boolean gameScanEnable;

    public static int gameScanFrequencyTime;
    public static ScanFrequencyEnum gameScanFrequencyType;
    public static boolean movieLibraryEnable;
    public static String movieLibraryPath;
    public static boolean movieScanEnable;
    public static boolean moviePreTranscodeEnable;
    public static boolean moviePreTranscode144p;
    public static boolean moviePreTranscode240p;
    public static boolean moviePreTranscode360p;
    public static boolean moviePreTranscode480p;
    public static boolean moviePreTranscode720p;
    public static boolean moviePreTranscode1080p;
    public static boolean moviePreTranscode2k;
    public static boolean moviePreTranscode4k;
    public static boolean moviePreTranscode8k;

    public static int movieScanFrequencyTime;
    public static ScanFrequencyEnum movieScanFrequencyType;
    public static String moviePreTranscodeLibraryPath;
    public static boolean musicLibraryEnable;
    public static String musicLibraryPath;
    public static boolean musicScanEnable;
    public static boolean musicPreTranscodeEnable;
    public static boolean musicPreTranscode64k;
    public static boolean musicPreTranscode96k;
    public static boolean musicPreTranscode128k;
    public static boolean musicPreTranscode320k;
    public static boolean musicPreTranscode1411k;

    public static int musicScanFrequencyTime;
    public static ScanFrequencyEnum musicScanFrequencyType;
    public static String musicPreTranscodeLibraryPath;
    public static boolean tvShowLibraryEnable;
    public static String tvShowLibraryPath;
    public static boolean tvShowScanEnable;
    public static boolean tvShowPreTranscodeEnable;
    public static boolean tvShowPreTranscode144p;
    public static boolean tvShowPreTranscode240p;
    public static boolean tvShowPreTranscode360p;
    public static boolean tvShowPreTranscode480p;
    public static boolean tvShowPreTranscode720p;
    public static boolean tvShowPreTranscode1080p;
    public static boolean tvShowPreTranscode2k;
    public static boolean tvShowPreTranscode4k;
    public static boolean tvShowPreTranscode8k;

    public static int tvShowScanFrequencyTime;
    public static ScanFrequencyEnum tvShowScanFrequencyType;
    public static String tvShowPreTranscodeLibraryPath;
    public static boolean allowRegistration;
    public static boolean homePageShowNewMovie;
    public static boolean homePageShowNewTvShow;
    public static boolean homePageShowNewGame;
    public static boolean homePageShowNewBook;
    public static boolean homePageShowNewMusic;
    public static boolean homePageShowPopularMovie;
    public static boolean homePageShowPopularTvShow;
    public static boolean homePageShowPopularGame;
    public static boolean homePageShowPopularBook;
    public static boolean homePageShowPopularMusic;

    public static int cardWidth;
    public static boolean stickyTopMenu;
    public static DatabaseTypeEnum databaseType;
    public static String databaseName;
    public static int minDatabaseConnections;
    public static int maxDatabaseConnections;
}