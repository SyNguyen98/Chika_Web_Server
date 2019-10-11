package com.chika.server.services;

import com.chika.server.models.file.Image;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Sy Nguyen
 * @version 1.0
 * @since 11-10-2019
 */
@Service
public interface ImageService {

    Image storeImage(MultipartFile image);

    Image getImage(String imageId);

    void deleteImage(String imageId);
}
