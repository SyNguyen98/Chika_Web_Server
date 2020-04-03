package com.chika.server.repositories.product;

import com.chika.server.models.product.HomeCenter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Sy Nguyen
 * @version 1.0
 * @since 03-04-2020
 */
@Repository
public interface HomeCenterRepository extends JpaRepository<HomeCenter, String> {

    List<HomeCenter> findAllByOrderByCreatedAtDesc();

    List<HomeCenter> findAllByUserId(Long userId);

    long countAllByUserId(Long userId);
}
