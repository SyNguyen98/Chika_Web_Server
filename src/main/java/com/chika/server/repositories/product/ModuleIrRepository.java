package com.chika.server.repositories.product;

import com.chika.server.models.product.ModuleIr;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Sy Nguyen
 * @version 1.0
 * @since 19-02-2020
 */
@Repository
public interface ModuleIrRepository extends JpaRepository<ModuleIr, String> {

    List<ModuleIr> findAllByOrderByCreatedAtDesc();
}
