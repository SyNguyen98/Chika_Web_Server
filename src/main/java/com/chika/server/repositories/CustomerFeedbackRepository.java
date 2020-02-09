package com.chika.server.repositories;

import com.chika.server.models.CustomerFeedback;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerFeedbackRepository extends JpaRepository<CustomerFeedback, String> {

    List<CustomerFeedback> findAllBy(Pageable pageable);
}
