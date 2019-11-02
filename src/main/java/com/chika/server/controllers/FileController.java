package com.chika.server.controllers;

import com.chika.server.models.file.Audio;
import com.chika.server.models.file.Image;
import com.chika.server.payload.responses.UploadFileResponse;
import com.chika.server.services.AudioService;
import com.chika.server.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Objects;

@RestController
@RequestMapping
public class FileController {

    @Autowired
    private ImageService imageService;

    @Autowired
    private AudioService audioService;

    @PostMapping("/image")
    public UploadFileResponse uploadImage(@RequestParam("file") MultipartFile imageFile) {
        Image image = imageService.storeImage(imageFile);

        String imageUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/image/")
                .path(image.getId())
                .toUriString();

        return new UploadFileResponse(image.getName(), imageUri, Objects.requireNonNull(imageFile).getContentType(), imageFile.getSize());
    }

    @GetMapping("/image/{imageId}")
    public ResponseEntity<Resource> downloadImage(@PathVariable String imageId) {
        Image image = imageService.getImage(imageId);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(image.getType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + image.getName() + "\"")
                .body(new ByteArrayResource(image.getData()));
    }

    @PostMapping("/audio")
    public UploadFileResponse uploadAudio(@RequestParam("file") MultipartFile audioFile) {
        Audio audio = audioService.storeAudio(audioFile);

        String imageUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/audio/")
                .path(audio.getName())
                .toUriString();

        return new UploadFileResponse(audio.getName(), imageUri, Objects.requireNonNull(audioFile).getContentType(), audioFile.getSize());
    }

    @GetMapping("/audio/{audioId}")
    public ResponseEntity<Resource> downloadAudio(@PathVariable String audioId) {
        Audio audio = audioService.getAudio(audioId);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(audio.getType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + audio.getName() + "\"")
                .body(new ByteArrayResource(audio.getData()));
    }
}