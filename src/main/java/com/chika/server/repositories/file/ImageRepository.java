package com.chika.server.repositories.file;

import com.chika.server.models.file.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<Image, String> {

    List<Image> getAllByLabel(String label);
}
