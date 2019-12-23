package com.chika.server.services.implement;

import com.chika.server.exception.FileStorageException;
import com.chika.server.exception.ResourceNotFoundException;
import com.chika.server.models.file.Audio;
import com.chika.server.repositories.file.AudioRepository;
import com.chika.server.services.AudioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

/**
 * CRUD functions for Audio
 * @author Sy Nguyen
 * @version 1.0
 * @since 22-12-2019
 */
@Service
public class AudioServiceImpl implements AudioService {

    private final AudioRepository audioRepository;

    private static int random = -1;

    public AudioServiceImpl(AudioRepository audioRepository) {
        this.audioRepository = audioRepository;
    }

    @Override
    @Transactional
    public Audio getByName(String name) {
        return audioRepository.findByNameContains(name)
                .orElseThrow(() -> new ResourceNotFoundException("Audio", "name", name));
    }

    @Override
    @Transactional
    public Audio getByLabel(String label) {
        if (random++ == 2) {
            random = 0;
        }
        List<Audio> audioList = audioRepository.findAllByLabel(label);
        if (random >= audioList.size()) {
            return audioList.get(audioList.size() - 1);
        }
        return audioList.get(random);
    }

    @Override
    public Audio save(MultipartFile audioFile, String audioLabel) {
        String audioName = StringUtils.cleanPath(Objects.requireNonNull(audioFile.getOriginalFilename()));
        try {
            if (audioName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence" + audioName);
            }
            Audio audio = new Audio(audioName, audioFile.getContentType(), audioLabel, audioFile.getBytes());
            return audioRepository.save(audio);
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + audioName + ". Please try again!", ex);
        }
    }

    @Override
    public void deleteById(String id) {
        audioRepository.deleteById(id);
    }
}
