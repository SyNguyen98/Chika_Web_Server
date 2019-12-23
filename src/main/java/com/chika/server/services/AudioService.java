package com.chika.server.services;

import com.chika.server.models.file.Audio;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface AudioService {

    Audio getByName(String name);

    Audio getByLabel(String label);

    Audio save(MultipartFile audio, String audioLabel);

    void deleteById(String id);
}
