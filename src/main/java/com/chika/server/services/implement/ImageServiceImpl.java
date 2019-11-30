package com.chika.server.services.implement;

import com.chika.server.exception.FileStorageException;
import com.chika.server.exception.ResourceNotFoundException;
import com.chika.server.models.file.Image;
import com.chika.server.repositories.file.ImageRepository;
import com.chika.server.services.ImageService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

/**
 * @author Sy Nguyen
 * @version 1.0
 * @since 11-10-2019
 */
@Service
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;

    public ImageServiceImpl(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @Override
    public Image storeImage(MultipartFile imageFile, String label) {
        String imageName = StringUtils.cleanPath(Objects.requireNonNull(imageFile.getOriginalFilename()));
        try {
            if (imageName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence" + imageName);
            }
            Image image = new Image(imageName, imageFile.getContentType(), label, imageFile.getBytes());
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
    @Transactional
    public List<Image> getAllByLabel(String label) {
        return imageRepository.getAllByLabel(label);
    }

    @Override
    public void deleteImage(String imageId) {
        imageRepository.deleteById(imageId);
    }
}
