package com.example.s3Demo.S3.web.controller;

import com.example.s3Demo.S3.exceptions.InvalidFileException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class FileValidator {

    private static final Map<String, List<String>> ALLOWED_EXTENSIONS = Map.of(
            "photo", List.of("jpg", "jpeg", "png", "gif", "svg", "webp", "bmp", "ico"),
            "premiumphoto", List.of("jpg", "jpeg", "png", "gif", "svg", "webp", "bmp", "ico"),
            "document", List.of("doc", "docx", "pdf", "txt", "jpg", "png", "xls", "xlsx", "csv", "bmp"),
            "video", List.of("mp4", "webm", "ogv", "ogg", "av1")
    );

    public void validate(MultipartFile file, String fileType) throws InvalidFileException {
        if (file.isEmpty() || file.getOriginalFilename() == null || file.getOriginalFilename().trim().isEmpty()) {
            log.error("File is empty or has no name.");
            throw new InvalidFileException("File is empty or has no name.");
        }

        String extension = FilenameUtils.getExtension(file.getOriginalFilename()).toLowerCase();
        if (!ALLOWED_EXTENSIONS.getOrDefault(fileType.toLowerCase(), Collections.emptyList()).contains(extension)) {
            log.error("File extension " + extension + " is not allowed for type " + fileType);
            throw new InvalidFileException("File extension " + extension + " is not allowed for type " + fileType);
        }
    }
}
