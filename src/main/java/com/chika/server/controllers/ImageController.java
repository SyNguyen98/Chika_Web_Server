package com.chika.server.controllers;

import com.chika.server.models.file.Image;
import com.chika.server.payload.responses.FileResponse;
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

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * To receive Image requests from client
 * @author Sy Nguyen
 * @version 1.0
 * @since 22-12-2019
 */
@RestController
@RequestMapping("/image")
public class ImageController {

    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping
    public List<FileResponse> getAllImageByLabel(@RequestParam("label") String label) {
        List<Image> images = imageService.getAllByLabel(label);
        List<FileResponse> fileResponses = new ArrayList<>();
        for (Image image : images) {
            String imageUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/image/")
                    .path(image.getId())
                    .toUriString();
            fileResponses.add(new FileResponse(image.getName(), imageUri, image.getType(), image.getLabel()));
        }
        fileResponses.sort(Comparator.comparing(FileResponse::getFileName));
        return fileResponses;
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
    public FileResponse save(@RequestParam("image") MultipartFile imageFile, @RequestParam("label") String label) {
        Image image = imageService.save(imageFile, label);

        String imageUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/image/")
                .path(image.getId())
                .toUriString();

        return new FileResponse(image.getName(), imageUri, image.getType(), image.getLabel());
    }
}