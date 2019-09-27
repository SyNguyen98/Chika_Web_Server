package com.chika.server.services.implement;

import com.chika.server.models.house.Audio;
import com.chika.server.repositories.AudioRepository;
import com.chika.server.services.AudioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;

/**
 * @author Sy Nguyen
 * @version 1.0
 * @since 13-08-2019
 */
@Service
public class AudioServiceImpl implements AudioService {

    @Autowired
    private AudioRepository audioRepository;

    private Clip clip;

    @Override
    public Audio findAudioById(String id) {
        if (audioRepository.findById(id).isPresent()) {
            return audioRepository.findById(id).get();
        }
        return null;
    }

    @Override
    public void playAudio(String id) {
        Thread stopper = new Thread(() -> {
            try {
                Thread.sleep(2000);
                clip.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        Audio audio = findAudioById(id);
        String path = audio.getUri();
        try {
            stopper.start();
            clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(new File(path)));
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ignored) {
        }
    }
}
