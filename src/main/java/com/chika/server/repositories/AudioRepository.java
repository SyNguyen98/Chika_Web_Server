package com.chika.server.repositories;

import com.chika.server.models.file.Audio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Sy Nguyen
 * @version 1.0
 * @since 11-08-2019
 */
@Repository
public interface AudioRepository extends JpaRepository<Audio, String> {

}
