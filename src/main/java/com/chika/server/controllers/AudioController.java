package com.chika.server.controllers;

import com.chika.server.models.file.Audio;
import com.chika.server.payload.responses.FileResponse;
import com.chika.server.services.AudioService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping
public class AudioController {

    private final AudioService audioService;

    public AudioController(AudioService audioService) {
        this.audioService = audioService;
    }

    @PostMapping("/audio")
    public FileResponse uploadAudio(@RequestParam("audio") MultipartFile audioFile, @RequestParam("label") String label) {
        Audio audio = audioService.storeAudio(audioFile, label);

        String audioUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/audio/")
                .path(audio.getName())
                .toUriString();

        return new FileResponse(audio.getName(), audioUri, audio.getType(), audio.getLabel());
    }

    @GetMapping("/audio/{name}")
    public ResponseEntity<Resource> downloadAudio(@PathVariable String name) {
        Audio audio = audioService.getAudioByName(name);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(audio.getType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + audio.getName() + "\"")
                .body(new ByteArrayResource(audio.getData()));
    }

    @GetMapping("/audio")
    public ResponseEntity<Resource> downloadAudioByLabel(@RequestParam("label") String label) {
        Audio audio = audioService.getAudioByLabel(label);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(audio.getType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + audio.getName() + "\"")
                .body(new ByteArrayResource(audio.getData()));
    }
}
