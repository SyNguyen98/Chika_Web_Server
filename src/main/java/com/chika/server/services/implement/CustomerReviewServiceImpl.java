package com.chika.server.services.implement;

import com.chika.server.models.CustomerReview;
import com.chika.server.repositories.CustomerReviewRepository;
import com.chika.server.services.CustomerReviewService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerReviewServiceImpl implements CustomerReviewService {

    private final CustomerReviewRepository customerReviewRepository;

    public CustomerReviewServiceImpl(CustomerReviewRepository customerReviewRepository) {
        this.customerReviewRepository = customerReviewRepository;
    }

    @Override
    public List<CustomerReview> getList(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "createdAt");
        return customerReviewRepository.findAllBy(pageable);
    }

    @Override
    public CustomerReview save(CustomerReview customerReview) {
        return customerReviewRepository.save(customerReview);
    }

    @Override
    public void delete(String id) {
        customerReviewRepository.deleteById(id);
    }
}
