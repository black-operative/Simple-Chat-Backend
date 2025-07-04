package com.black_opeartive.schat.controllers;

import com.black_opeartive.schat.models.FileDocument;
import com.black_opeartive.schat.repositories.FileRepository;
import com.black_opeartive.schat.services.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/files")
public class FileController {
    private static final String UPLOAD_DIR = "uploads";

    @Autowired
    private FileRepository fileRepository;

    @Autowired
    private S3Service s3Service;

    @PostMapping("/upload")
    public ResponseEntity<Map<String, String>> uploadFile(
        @RequestParam("file")     MultipartFile file,
        @RequestParam("username") String        username,
        @RequestParam("roomId")   String        roomId
    ) {
        Map<String, String> response = new HashMap<>();

        try {
            if (file.isEmpty()) {
                response.put("error", "File is Empty.");
                return ResponseEntity.badRequest().body(response);
            }

            String originalFilename = file.getOriginalFilename();
            byte[] fileBytes = file.getBytes();

            String fileURL = s3Service.uploadFile(
                    fileBytes,
                    originalFilename,
                    file.getContentType()
            );

            FileDocument document = new FileDocument();
            document.setOriginalName(originalFilename);
            document.setStoredName(fileURL.substring(fileURL.lastIndexOf("/") + 1));
            document.setUrl(fileURL);
            document.setSize(file.getSize());
            document.setContentType(file.getContentType());
            document.setUploadedAt(LocalDateTime.now());
            document.setUploadedBy(username);
            document.setRoomId(roomId);

            fileRepository.save(document);

            response.put("url", fileURL);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("error", "Upload failed");
            System.out.println(e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(500).body(response);
        }
    }
}
