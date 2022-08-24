package com.ratz.bonsaicorner.service.impl;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Acl;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Bucket;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.StorageClient;
import com.ratz.bonsaicorner.exceptions.ImageUploadFailedException;
import com.ratz.bonsaicorner.exceptions.ResourceNotFoundException;
import com.ratz.bonsaicorner.service.FileService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static com.ratz.bonsaicorner.utils.APIConstants.BUCKET_URL;
import static com.ratz.bonsaicorner.utils.APIConstants.MIME_TYPE;

@Service
public class FileServiceImpl implements FileService {


    @PostConstruct
    private void init() throws IOException {

        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("mybonsaicorner-firebase.json");

        assert inputStream != null;
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(inputStream))
                .setStorageBucket("mybonsaicorner.appspot.com")
                .build();

        FirebaseApp.initializeApp(options);
    }

    @Override
    public String uploadSingleImage(String image) {

        Bucket bucket = StorageClient.getInstance().bucket();

        String fileName = UUID.randomUUID().toString();

        checkIfItsValidAndUpload(bucket, image, fileName);

        return String.format("https://storage.googleapis.com/%s/%s", bucket.getName(), fileName);
    }

    @Override
    public Set<String> uploadBonsaiImages(Set<String> images) {

        Bucket bucket = StorageClient.getInstance().bucket();

        Set<String> links = new HashSet();

        for (String image : images) {

            String fileName = UUID.randomUUID().toString();

            checkIfItsValidAndUpload(bucket, image, fileName);

            links.add(String.format("https://storage.googleapis.com/%s/%s", bucket.getName(), fileName));
        }

        return links;
    }

    @Override
    public void deleteImage(String url) {

        Bucket bucket = StorageClient.getInstance().bucket();

        deleteImage(bucket, url);

    }

    @Override
    public void deleteMultipleImages(Set<String> images) {

        Bucket bucket = StorageClient.getInstance().bucket();

        for (String image : images) {
            deleteImage(bucket, image);
        }
    }

    //helper methods
    private void checkIfItsValidAndUpload(Bucket bucket, String image, String fileName) {

        if (!checkIfItsBase64(image.getBytes(StandardCharsets.UTF_8)))
            throw new ImageUploadFailedException("The image is not in Base64");

        byte[] bytes = Base64.getDecoder().decode(image);

        Blob blob = bucket.create(fileName, bytes, MIME_TYPE);
        blob.createAcl(Acl.of(Acl.User.ofAllUsers(), Acl.Role.READER));
    }


    private boolean checkIfItsBase64(byte[] bytes) {

        return org.apache.commons.codec.binary.Base64.isBase64(bytes);
    }


    private void deleteImage(Bucket bucket, String image) {

        StringBuilder uuid = new StringBuilder(image.replace(BUCKET_URL, ""));

        Blob fileExists = bucket.get(uuid.toString());

        if (fileExists.exists()) {

            try {

                fileExists.delete();

            } catch (Exception ex) {
                throw new ImageUploadFailedException("Image uploading failed! Please try again later.");
            }
        } else {
            throw new ResourceNotFoundException("File with the UUID " + uuid + " not found.");
        }
    }
}
