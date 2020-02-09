package com.chika.server.payload.responses;

import com.chika.server.Formatter;
import com.chika.server.models.CustomerFeedback;
import lombok.Data;

@Data
public class FeedbackResponse {

    private String id;

    private String subject;

    private String content;

    private String name;

    private String phone;

    private String time;

    public FeedbackResponse(CustomerFeedback customerFeedback) {
        this.id = customerFeedback.getId();
        this.subject = customerFeedback.getSubject();
        this.content = customerFeedback.getContent();
        this.name = customerFeedback.getName();
        this.phone = customerFeedback.getPhone();
        this.time = Formatter.formatTime(customerFeedback.getCreatedAt().getTime());
    }
}
