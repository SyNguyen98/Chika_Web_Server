package com.chika.server.controllers;

import com.chika.server.models.file.Image;
import com.chika.server.payload.responses.FileResponse;
import com.chika.server.payload.responses.ImageResponse;
import com.chika.server.services.ImageService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 * To receive Image requests from client
 * @author Sy Nguyen
 * @version 1.0
 * @since 04-02-2019
 */
@RestController
@RequestMapping("/image")
public class ImageController {

    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Resource> downloadImage(@PathVariable String id) {
        Image image = imageService.getById(id);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(image.getType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + image.getName() + "\"")
                .body(new ByteArrayResource(image.getData()));
    }

    @PostMapping
    public ImageResponse save(@RequestParam("image") MultipartFile imageFile) {
        Image image = imageService.save(imageFile);

        String imageUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/image/")
                .path(image.getId())
                .toUriString();

        return new ImageResponse(image.getName(), imageUri, image.getType());
    }
}