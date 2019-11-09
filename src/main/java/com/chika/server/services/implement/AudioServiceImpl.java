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

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

/**
 * @author Sy Nguyen
 * @version 1.0
 * @since 08-11-2019
 */
@Service
public class AudioServiceImpl implements AudioService {

    @Autowired
    private AudioRepository audioRepository;

    private static int random = -1;

    @Override
    public Audio storeAudio(MultipartFile audioFile, String audioLabel) {
        String audioName = StringUtils.cleanPath(audioFile.getOriginalFilename());
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
    @Transactional
    public Audio getAudioByName(String name) {
        return audioRepository.findByNameContains(name)
                .orElseThrow(() -> new ResourceNotFoundException("Audio", "name", name));
    }

    @Override
    @Transactional
    public Audio getAudioByLabel(String label) {
        if (random++ == 2) {
            random = 0;
        }
        List<Audio> audioList = audioRepository.findAllByLabel(label);
        if (audioList.isEmpty()) {
            audioList = audioRepository.findAllByLabel("Unknown");
        }
        if (random >= audioList.size()) {
            return audioList.get(audioList.size() - 1);
        }
        return audioList.get(random);
    }

    @Override
    public void deleteAudio(String id) {
        audioRepository.deleteById(id);
    }
}
