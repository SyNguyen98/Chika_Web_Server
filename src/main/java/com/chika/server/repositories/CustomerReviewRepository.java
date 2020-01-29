package com.chika.server.repositories;

import com.chika.server.models.CustomerReview;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerReviewRepository extends JpaRepository<CustomerReview, String> {

    List<CustomerReview> findAllBy(Pageable pageable);
}
