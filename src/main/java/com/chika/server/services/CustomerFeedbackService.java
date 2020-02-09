package com.chika.server.services;

import com.chika.server.models.CustomerFeedback;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomerFeedbackService {

    List<CustomerFeedback> getList(int page, int size);

    CustomerFeedback save(CustomerFeedback customerFeedback);

    void delete(String id);
}
