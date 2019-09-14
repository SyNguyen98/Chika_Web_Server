package com.chika.server.services;

import com.chika.server.models.house.Audio;
import org.springframework.stereotype.Service;

/**
 * @author Sy Nguyen
 * @version 1.0
 * @since 13-08-2019
 */
@Service
public interface AudioService {

    Audio findAudioById(String id);

    void playAudio(String id);
}
