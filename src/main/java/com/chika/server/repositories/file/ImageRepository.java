package com.chika.server.repositories.file;

import com.chika.server.models.file.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Sy Nguyen
 * @version 1.0
 * @since 04-02-2019
 */
@Repository
public interface ImageRepository extends JpaRepository<Image, String> {

}
