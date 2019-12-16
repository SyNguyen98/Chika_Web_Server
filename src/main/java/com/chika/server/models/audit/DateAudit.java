package com.chika.server.models.audit;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.Instant;

/**
 * @author Sy Nguyen
 * @version 1.0
 * @since 16-12-2019
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(
        value = "createdAt",
        allowGetters = true
)
@Data
public abstract class DateAudit implements Serializable {

    @CreatedDate
    private Timestamp createdAt;
}