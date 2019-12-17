package com.chika.server.services;

import com.chika.server.models.script.Script;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledFuture;

@Service
public interface ScheduleService {

    @SuppressWarnings("rawtypes")
    List<ScheduledFuture> scheduledFutures = new ArrayList<>();

    void initialize(Script script);

    void cancelAll();
}
