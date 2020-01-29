package com.chika.server.payload.responses;

import com.chika.server.Formatter;
import com.chika.server.models.CustomerReview;
import lombok.Data;

@Data
public class ReviewResponse {

    private String id;

    private String subject;

    private String content;

    private String name;

    private String phone;

    private String time;

    public ReviewResponse(CustomerReview customerReview) {
        this.id = customerReview.getId();
        this.subject = customerReview.getSubject();
        this.content = customerReview.getContent();
        this.name = customerReview.getName();
        this.phone = customerReview.getPhone();
        this.time = Formatter.formatTime(customerReview.getCreatedAt().getTime());
    }
}
