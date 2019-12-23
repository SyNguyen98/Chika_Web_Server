package com.chika.server.services;

import com.chika.server.models.file.Image;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public interface ImageService {

    List<Image> getAllByLabel(String label);

    Image getById(String id);

    Image save(MultipartFile image, String label);

    void deleteById(String id);
}
