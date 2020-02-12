package com.chika.server.controllers;

import com.chika.server.models.user.Feedback;
import com.chika.server.payload.responses.ApiResponse;
import com.chika.server.payload.responses.FeedbackResponse;
import com.chika.server.services.FeedbackService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * To receive Feedback requests from client
 * @author Sy Nguyen
 * @version 1.0
 * @since 09-02-2020
 */
@RestController
@RequestMapping("/review")
public class FeedbackController {

    private final FeedbackService feedbackService;

    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<FeedbackResponse> getAll(@RequestParam("page") int page, @RequestParam("size") int size) {
        return feedbackService.getList(page, size).stream()
                        .map(FeedbackResponse::new)
                        .collect(Collectors.toList());
    }

    @PostMapping
    public FeedbackResponse save(@RequestBody Feedback feedback) {
        return new FeedbackResponse(feedbackService.save(feedback));
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<?> deleteById(@PathVariable String id) {
        feedbackService.delete(id);
        return ResponseEntity.ok(new ApiResponse(true, "Review has been deleted"));
    }
}
