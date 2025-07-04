package com.black_opeartive.schat.controllers;

import com.black_opeartive.schat.models.FileDocument;
import com.black_opeartive.schat.repositories.FileRepository;
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
@CrossOrigin(origins = "http://localhost:5173")
public class FileController {
    private static final String UPLOAD_DIR = "uploads";

    @Autowired
    private FileRepository fileRepository;

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

            Path uploadPath = Paths.get(UPLOAD_DIR);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            String originalFilename = file.getOriginalFilename();
            String storedFilename   = UUID.randomUUID() + "_" + originalFilename;

            Path filePath = uploadPath.resolve(storedFilename);
            file.transferTo(filePath);

            String fileURL = "http://localhost:8080/uploads/" + storedFilename;

            FileDocument document = new FileDocument();
            document.setOriginalName(originalFilename);
            document.setStoredName(storedFilename);
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
            return ResponseEntity.status(500).body(response);
        }
    }
}
