package com.chika.server.repositories.user;

import com.chika.server.models.user.Feedback;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, String> {

    List<Feedback> findAllByOrderByCreatedAtDesc();
}
