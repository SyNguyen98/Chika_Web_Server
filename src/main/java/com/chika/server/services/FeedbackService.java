package com.chika.server.services;

import com.chika.server.models.user.Feedback;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FeedbackService {

    List<Feedback> getAll();

    Feedback save(Feedback feedback);

    void delete(String id);
}
