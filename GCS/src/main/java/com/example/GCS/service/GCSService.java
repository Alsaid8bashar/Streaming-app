package com.example.GCS.service;

import com.google.cloud.ReadChannel;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.Channels;

@Service
public class GCSService {
    private final Storage storage;

    @Autowired
    public GCSService(Storage storage) {
        this.storage = storage;
    }

    public ResponseEntity<String> uploadFile(MultipartFile videoFile, String videoName) throws IOException {
        BlobId blobId = BlobId.of("bashar-bucket", videoName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();
        byte[] content = videoFile.getBytes();
        storage.create(blobInfo, content);
        return new ResponseEntity<>("File uploaded successfully", HttpStatus.OK);
    }

    public StreamingResponseBody getVideo(String videoName) throws IOException {

        BlobId blobId = BlobId.of("bashar-bucket", videoName);
        Blob blob = storage.get(blobId);
        ReadChannel reader = blob.reader();
        InputStream inputStream = Channels.newInputStream(reader);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf("video/mp4"));
        headers.setContentLength(blob.getSize());

        StreamingResponseBody responseBody = outputStream -> {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            inputStream.close();
        };

        return responseBody;
    }


}
