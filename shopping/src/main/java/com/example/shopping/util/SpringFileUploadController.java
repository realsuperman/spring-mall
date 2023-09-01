package com.example.shopping.util;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.StorageClient;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/upload")
public class SpringFileUploadController {

    private final StorageClient storageClient;

    public SpringFileUploadController(Environment environment) {
        try {
            InputStream serviceAccount = getClass().getResourceAsStream("/serviceAccountKey.json");
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setStorageBucket(environment.getProperty("google.firebase.storageBucket"))
                    .build();
            FirebaseApp.initializeApp(options);
            storageClient = StorageClient.getInstance();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping
    public String handleFileUpload(@RequestParam("file") MultipartFile file) throws IOException {
        if(file.isEmpty()) throw new RuntimeException();
        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        storageClient.bucket().create(fileName, file.getBytes(), file.getContentType());
        return fileName;
    }
}