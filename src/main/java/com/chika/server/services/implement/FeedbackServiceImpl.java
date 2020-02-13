package com.chika.server.services.implement;

import com.chika.server.models.user.Feedback;
import com.chika.server.repositories.user.FeedbackRepository;
import com.chika.server.services.FeedbackService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackServiceImpl implements FeedbackService {

    private final FeedbackRepository feedbackRepository;

    public FeedbackServiceImpl(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }

    @Override
    public List<Feedback> getList(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "createdAt");
        return feedbackRepository.findAllBy(pageable);
    }

    @Override
    public Feedback save(Feedback feedback) {
        return feedbackRepository.save(feedback);
    }

    @Override
    public void delete(String id) {
        feedbackRepository.deleteById(id);
    }
}
