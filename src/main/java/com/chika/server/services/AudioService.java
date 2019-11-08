package com.chika.server.services;

import com.chika.server.models.file.Audio;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Sy Nguyen
 * @version 1.0
 * @since 02-11-2019
 */
@Service
public interface AudioService {

    Audio storeAudio(MultipartFile audio, String audioLabel);

    Audio getAudioByName(String name);

    Audio getAudioByLabel(String label);

    void deleteAudio(String id);
}
