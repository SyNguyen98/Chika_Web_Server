package com.chika.server.controllers;

import com.chika.server.models.CustomerReview;
import com.chika.server.payload.responses.ApiResponse;
import com.chika.server.payload.responses.ReviewResponse;
import com.chika.server.services.CustomerReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/review")
public class CustomerReviewController {

    private final CustomerReviewService customerReviewService;

    public CustomerReviewController(CustomerReviewService customerReviewService) {
        this.customerReviewService = customerReviewService;
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<ReviewResponse> getAll(@RequestParam("page") int page, @RequestParam("size") int size) {
        List<ReviewResponse> reviewResponses = new ArrayList<>();
        List<CustomerReview> customerReviews = customerReviewService.getList(page, size);
        customerReviews.forEach(customerReview -> reviewResponses.add(new ReviewResponse(customerReview)));
        return reviewResponses;
    }

    @PostMapping
    public ReviewResponse save(@RequestBody CustomerReview customerReview) {
        return new ReviewResponse(customerReviewService.save(customerReview));
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<?> deleteById(@PathVariable String id) {
        customerReviewService.delete(id);
        return ResponseEntity.ok(new ApiResponse(true, "Review has been deleted"));
    }
}
