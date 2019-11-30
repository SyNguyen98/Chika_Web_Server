package com.chika.server.repositories.device;

import com.chika.server.models.device.ModuleIr;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Sy Nguyen
 * @version 1.0
 * @since 30-11-2019
 */
@Repository
public interface ModuleIrRepository extends JpaRepository<ModuleIr, String> {

}
