package com.chika.server.controllers;

import com.chika.server.models.CustomerFeedback;
import com.chika.server.payload.responses.ApiResponse;
import com.chika.server.payload.responses.FeedbackResponse;
import com.chika.server.services.CustomerFeedbackService;
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
public class CustomerFeedbackController {

    private final CustomerFeedbackService customerFeedbackService;

    public CustomerFeedbackController(CustomerFeedbackService customerFeedbackService) {
        this.customerFeedbackService = customerFeedbackService;
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<FeedbackResponse> getAll(@RequestParam("page") int page, @RequestParam("size") int size) {
        return customerFeedbackService.getList(page, size).stream()
                        .map(FeedbackResponse::new)
                        .collect(Collectors.toList());
    }

    @PostMapping
    public FeedbackResponse save(@RequestBody CustomerFeedback customerFeedback) {
        return new FeedbackResponse(customerFeedbackService.save(customerFeedback));
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<?> deleteById(@PathVariable String id) {
        customerFeedbackService.delete(id);
        return ResponseEntity.ok(new ApiResponse(true, "Review has been deleted"));
    }
}
