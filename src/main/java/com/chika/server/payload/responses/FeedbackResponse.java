package com.chika.server.payload.responses;

import com.chika.server.Formatter;
import com.chika.server.models.user.Feedback;
import lombok.Data;

@Data
public class FeedbackResponse {

    private String id;

    private String subject;

    private String content;

    private String name;

    private String phone;

    private String time;

    public FeedbackResponse(Feedback feedback) {
        this.id = feedback.getId();
        this.subject = feedback.getSubject();
        this.content = feedback.getContent();
        this.name = feedback.getName();
        this.phone = feedback.getPhone();
        this.time = Formatter.formatTime(feedback.getCreatedAt().getTime());
    }
}
