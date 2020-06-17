package com.chika.server.services;

import com.chika.server.models.house.Script;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;

@Service
public interface ScheduleService {

    Map<String, ScheduledFuture<?>> scheduledFutures = new HashMap<>();

    void initialize(Script script);

    void cancel(String scriptId);

    void cancelAll();
}
