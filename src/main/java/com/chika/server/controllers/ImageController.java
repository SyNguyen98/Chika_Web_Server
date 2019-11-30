package com.chika.server.controllers;

import com.chika.server.models.file.Image;
import com.chika.server.payload.responses.FileResponse;
import com.chika.server.services.ImageService;
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

@RestController
@RequestMapping("/image")
public class ImageController {

    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping
    public FileResponse uploadImage(@RequestParam("image") MultipartFile imageFile, @RequestParam("label") String label) {
        Image image = imageService.storeImage(imageFile, label);

        String imageUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/image/")
                .path(image.getId())
                .toUriString();

        return new FileResponse(image.getName(), imageUri, image.getType(), image.getLabel());
    }

    @GetMapping("/{imageId}")
    public ResponseEntity<Resource> downloadImage(@PathVariable String imageId) {
        Image image = imageService.getImage(imageId);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(image.getType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + image.getName() + "\"")
                .body(new ByteArrayResource(image.getData()));
    }

    @GetMapping
    public List<String> getAllImageByLabel(@RequestParam("label") String label) {
        List<Image> images = imageService.getAllByLabel(label);
        images.sort(Comparator.comparing(Image::getName));
        List<String> imageUrls = new ArrayList<>();
        for (Image image : images) {
            String imageUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/image/")
                    .path(image.getId())
                    .toUriString();
            imageUrls.add(imageUri);
        }
        return imageUrls;
    }
}