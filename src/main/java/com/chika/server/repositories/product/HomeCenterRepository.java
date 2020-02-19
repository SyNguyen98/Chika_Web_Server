package com.chika.server.repositories.product;

import com.chika.server.models.product.HomeCenter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HomeCenterRepository extends JpaRepository<HomeCenter, String> {

    List<HomeCenter> findAllByOrderByCreatedAtDesc();
}
