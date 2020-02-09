package com.chika.server.services.implement;

import com.chika.server.models.CustomerFeedback;
import com.chika.server.repositories.CustomerFeedbackRepository;
import com.chika.server.services.CustomerFeedbackService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerFeedbackServiceImpl implements CustomerFeedbackService {

    private final CustomerFeedbackRepository customerFeedbackRepository;

    public CustomerFeedbackServiceImpl(CustomerFeedbackRepository customerFeedbackRepository) {
        this.customerFeedbackRepository = customerFeedbackRepository;
    }

    @Override
    public List<CustomerFeedback> getList(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "createdAt");
        return customerFeedbackRepository.findAllBy(pageable);
    }

    @Override
    public CustomerFeedback save(CustomerFeedback customerFeedback) {
        return customerFeedbackRepository.save(customerFeedback);
    }

    @Override
    public void delete(String id) {
        customerFeedbackRepository.deleteById(id);
    }
}
