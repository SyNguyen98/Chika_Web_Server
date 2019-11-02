package com.chika.server.repositories;

import com.chika.server.models.file.Audio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Sy Nguyen
 * @version 1.0
 * @since 02-11-2019
 */
@Repository
public interface AudioRepository extends JpaRepository<Audio, String> {

    Optional<Audio> findByName(String name);
}
