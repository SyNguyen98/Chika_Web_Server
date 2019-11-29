package com.chika.server.services;

import com.chika.server.models.file.Image;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author Sy Nguyen
 * @version 1.0
 * @since 11-10-2019
 */
@Service
public interface ImageService {

    Image storeImage(MultipartFile image, String label);

    Image getImage(String imageId);

    List<Image> getAllByLabel(String label);

    void deleteImage(String imageId);
}
