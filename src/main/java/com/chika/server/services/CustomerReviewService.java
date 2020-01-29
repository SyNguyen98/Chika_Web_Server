package com.chika.server.services;

import com.chika.server.models.CustomerReview;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomerReviewService {

    List<CustomerReview> getList(int page, int size);

    CustomerReview save(CustomerReview customerReview);

    void delete(String id);
}
