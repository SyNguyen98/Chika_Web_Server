package com.chika.server.services.implement;

import com.chika.server.Formatter;
import com.chika.server.models.script.Script;
import com.chika.server.services.MqttService;
import com.chika.server.services.ScheduleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;

import java.util.concurrent.ScheduledFuture;

@Service
public class ScheduleServiceImpl implements ScheduleService, Runnable {

    private static final Logger logger = LoggerFactory.getLogger(ScheduleServiceImpl.class);

    private TaskScheduler taskScheduler;
    private MqttService mqttService;

    private Script script;

    @Override
    public void initialize(Script script) {
        this.script = script;
        mqttService = MqttService.getInstance();
        if (taskScheduler == null) {
            this.taskScheduler = new ConcurrentTaskScheduler();
        }
        ScheduledFuture<?> scheduledFuture = this.taskScheduler.schedule(this, new CronTrigger(script.getTime()));
        scheduledFutures.add(scheduledFuture);
    }

    @Override
    public void cancelAll() {
        scheduledFutures.forEach(scheduledFuture -> scheduledFuture.cancel(true));
    }

    @Override
    public void run() {
        logger.info("Schedule " + script.getName() + " is running at " + Formatter.formatTime(System.currentTimeMillis()));
        script.getDevices().forEach(scriptDevice -> mqttService.publish(scriptDevice.getDeviceId(), scriptDevice.getValue()));
    }
}