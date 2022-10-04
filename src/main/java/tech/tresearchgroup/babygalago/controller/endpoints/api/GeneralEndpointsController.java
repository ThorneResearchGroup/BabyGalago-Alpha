package tech.tresearchgroup.babygalago.controller.endpoints.api;

import com.google.gson.Gson;
import io.activej.csp.file.ChannelFileWriter;
import io.activej.http.HttpHeaders;
import io.activej.http.HttpRequest;
import io.activej.http.HttpResponse;
import io.activej.http.MultipartDecoder;
import io.activej.inject.annotation.Provides;
import io.activej.promise.Promisable;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import tech.tresearchgroup.babygalago.Main;
import tech.tresearchgroup.babygalago.controller.controllers.FileController;
import tech.tresearchgroup.babygalago.controller.controllers.ImageController;
import tech.tresearchgroup.babygalago.controller.controllers.UserController;
import tech.tresearchgroup.babygalago.controller.controllers.VideoController;
import tech.tresearchgroup.palila.controller.BasicController;
import tech.tresearchgroup.palila.controller.GenericController;
import tech.tresearchgroup.palila.model.enums.PermissionGroupEnum;
import tech.tresearchgroup.schemas.galago.entities.FileEntity;
import tech.tresearchgroup.schemas.galago.entities.ImageEntity;
import tech.tresearchgroup.schemas.galago.entities.VideoEntity;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.UUID;
import java.util.concurrent.Executor;

import static java.util.concurrent.Executors.newSingleThreadExecutor;

@AllArgsConstructor
public class GeneralEndpointsController extends BasicController {
    private static final int CHUNK = 1024 * 2048;
    private final ImageController imageController;
    private final VideoController videoController;
    private final FileController fileController;
    private final UserController userController;
    private final Gson gson;

    @Provides
    static Executor executor() {
        return newSingleThreadExecutor();
    }

    public HttpResponse getLatest(HttpRequest httpRequest) throws IOException {
        //Todo return the latest version from Mama Galago
        return ok(Main.VERSION.getBytes());
    }

    public HttpResponse putUpdate(HttpRequest httpRequest) {
        //Todo attempt to update from Mama Galago
        return notImplemented();
    }

    public @NotNull Promisable<HttpResponse> postUpload(HttpRequest httpRequest) throws SQLException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        if (canAccess(httpRequest, PermissionGroupEnum.USER, userController)) {
            UUID uuid = UUID.randomUUID();
            Path file = new File("temp/" + uuid + ".tmp").toPath();
            return httpRequest.handleMultipart(MultipartDecoder.MultipartDataHandler.file(fileName ->
                    ChannelFileWriter.open(executor(), file)))
                .map($ -> ok(String.valueOf(file.getFileName()).getBytes()));
        }
        return unauthorized();
    }

    public HttpResponse getSearch(GenericController genericController, String query, HttpRequest httpRequest) throws Exception {
        return ok(gson.toJson(genericController.search(query, "*", httpRequest)).getBytes());
    }

    public HttpResponse getImageById(long imageId, HttpRequest httpRequest) throws IOException, SQLException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        ImageEntity imageEntity = (ImageEntity) imageController.readSecureResponse(imageId, httpRequest);
        FileEntity fileEntity = (FileEntity) fileController.readSecureResponse(imageEntity.getFile().getId(), httpRequest);
        return ok(Files.readAllBytes(Paths.get(fileEntity.getPath())));
    }

    public HttpResponse getVideoById(Long videoId, HttpRequest httpRequest) throws IOException, SQLException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        VideoEntity videoEntity = (VideoEntity) videoController.readSecureResponse(videoId, httpRequest);
        Path path = Paths.get(videoEntity.getFilePath());
        if (path.toFile().exists()) {
            long fileSize = Files.size(path);
            String range = httpRequest.getHeader(HttpHeaders.RANGE);
            if (range != null) {
                String[] ranges = range.replace("bytes=", "").split("-");
                long startValue = Long.parseLong(ranges[0]);
                if (ranges.length == 1) {
                    long end = startValue + CHUNK;
                    if (end > fileSize) {
                        end = fileSize;
                    }
                    return HttpResponse.ok206().withHeader(HttpHeaders.CONTENT_DISPOSITION, "inline").withHeader(HttpHeaders.CONTENT_TYPE, "multipart/byteranges").withHeader(HttpHeaders.CONTENT_RANGE, "bytes " + startValue + "-" + (fileSize - 1) + "/" + fileSize).withBody(readByteRange(path, startValue, end));
                }
                long endValue = Long.parseLong(ranges[1]);
                //if (CacheController.existsInCache(startValue, endValue, videoId)) {
                //    CachedEntity cachedEntity = CacheController.get(Integer.parseInt(start), endValue, videoId);
                //    return HttpResponse.ok206().withHeader(HttpHeaders.CONTENT_RANGE, "bytes " + startValue + "-" + endValue + "/" + fileSize).withBody(cachedEntity.getData());
                //}
                //CacheController.put(startValue, endValue, videoId, data);
                if (endValue > (startValue + CHUNK)) {
                    endValue = (startValue + CHUNK);
                }
                if (endValue > fileSize) {
                    endValue = fileSize;
                }
                return HttpResponse.ok206().withHeader(HttpHeaders.CONTENT_DISPOSITION, "inline").withHeader(HttpHeaders.CONTENT_TYPE, "video/mp4").withHeader(HttpHeaders.CONTENT_RANGE, "bytes " + startValue + "-" + endValue + "/" + fileSize).withBody(readByteRange(path, startValue, endValue));
            }
            //if (CacheController.existsInCache(0, CHUNK, videoId)) {
            //    return HttpResponse.ok206().withHeader(HttpHeaders.CONTENT_RANGE, "bytes " + 0 + "-" + CHUNK + "/" + fileSize).withBody(CacheController.get(0, CHUNK, videoId).getData());
            //}
            //CacheController.put(0, CHUNK, videoId, data);
            return HttpResponse.ok206().withHeader(HttpHeaders.CONTENT_DISPOSITION, "inline").withHeader(HttpHeaders.CONTENT_TYPE, "video/mp4").withHeader(HttpHeaders.CONTENT_RANGE, "bytes " + 0 + "-" + CHUNK + "/" + fileSize).withBody(readByteRange(path, 0, CHUNK));
        } else {
            return notFound();
        }
    }

    public byte[] readByteRange(Path file, long start, long end) throws IOException {
        end = end + 1;
        FileInputStream fis = new FileInputStream(file.toFile());
        int size = (int) (end - start);
        if (size < 0) {
            size = 0;
        }
        ByteBuffer byteBuffer = ByteBuffer.allocate(size);
        fis.getChannel().read(byteBuffer, start);
        fis.close();
        return byteBuffer.array();
    }

    public HttpResponse getVersion(HttpRequest httpRequest) throws IOException {
        return ok(Main.VERSION.getBytes());
    }
}
