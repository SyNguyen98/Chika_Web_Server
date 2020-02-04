package com.chika.server.services;

import com.chika.server.models.file.Image;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface ImageService {

    Image getById(String id);

    Image save(MultipartFile image);

    void deleteById(String id);
}
