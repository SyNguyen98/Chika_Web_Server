package com.chika.server.services.implement;

import com.chika.server.exception.FileStorageException;
import com.chika.server.exception.ResourceNotFoundException;
import com.chika.server.models.file.Audio;
import com.chika.server.repositories.AudioRepository;
import com.chika.server.services.AudioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author Sy Nguyen
 * @version 1.0
 * @since 11-10-2019
 */
@Service
public class AudioServiceImpl implements AudioService {

    @Autowired
    private AudioRepository audioRepository;

    @Override
    public Audio storeAudio(MultipartFile audioFile) {
        String audioName = StringUtils.cleanPath(audioFile.getOriginalFilename());
        try {
            if (audioName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence" + audioName);
            }
            Audio audio = new Audio(audioName, audioFile.getContentType(), audioFile.getBytes());
            return audioRepository.save(audio);
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + audioName + ". Please try again!", ex);
        }
    }

    @Override
    public Audio getAudio(String id) {
        return audioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Audio", "id", id));
    }

    @Override
    public void deleteAudio(String id) {
        audioRepository.deleteById(id);
    }
}
