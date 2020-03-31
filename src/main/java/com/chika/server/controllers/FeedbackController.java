package com.chika.server.controllers;

import com.chika.server.models.user.Feedback;
import com.chika.server.payload.responses.ApiResponse;
import com.chika.server.payload.responses.FeedbackResponse;
import com.chika.server.services.FeedbackService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * To receive Feedback requests from client
 * @author Sy Nguyen
 * @version 1.0
 * @since 31-03-2020
 */
@RestController
@RequestMapping("/feedback")
public class FeedbackController {

    private final FeedbackService feedbackService;

    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<FeedbackResponse> getAll() {
        return feedbackService.getAll().stream()
                        .map(FeedbackResponse::new)
                        .collect(Collectors.toList());
    }

    @GetMapping("/is_response")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> haveFeedbackNotResponse() {
        if (feedbackService.haveFeedbackNotResponse()) {
            return ResponseEntity.ok(new ApiResponse(true, "Having feedback did not response"));
        }
        return new ResponseEntity<>(new ApiResponse(false, "Don't have"), HttpStatus.BAD_REQUEST);
    }

    @PostMapping
    public FeedbackResponse save(@RequestBody Feedback feedback) {
        return new FeedbackResponse(feedbackService.save(feedback));
    }

    @PutMapping("/id/{id}/response/{response}")
    public FeedbackResponse updateResponse(@PathVariable String id, @PathVariable Boolean response) {
        return new FeedbackResponse(feedbackService.updateResponse(id, response));
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<?> deleteById(@PathVariable String id) {
        feedbackService.delete(id);
        return ResponseEntity.ok(new ApiResponse(true, "Review has been deleted"));
    }
}
