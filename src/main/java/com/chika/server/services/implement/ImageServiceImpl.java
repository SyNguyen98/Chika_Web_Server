package com.chika.server.services.implement;

import com.chika.server.exception.FileStorageException;
import com.chika.server.exception.ResourceNotFoundException;
import com.chika.server.models.file.Image;
import com.chika.server.repositories.ImageRepository;
import com.chika.server.services.ImageService;
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
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageRepository imageRepository;

    @Override
    public Image storeImage(MultipartFile imageFile) {
        String imageName = StringUtils.cleanPath(imageFile.getOriginalFilename());
        try {
            if (imageName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence" + imageName);
            }
            Image image = new Image(imageName, imageFile.getContentType(), imageFile.getBytes());
            return imageRepository.save(image);
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + imageName + ". Please try again!", ex);
        }
    }

    @Override
    public Image getImage(String imageId) {
        return imageRepository.findById(imageId)
                .orElseThrow(() -> new ResourceNotFoundException("Image", "id", imageId));
    }

    @Override
    public void deleteImage(String imageId) {
        imageRepository.deleteById(imageId);
    }
}
