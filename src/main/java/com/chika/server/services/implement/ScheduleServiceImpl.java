package com.chika.server.services.implement;

import com.chika.server.Formatter;
import com.chika.server.models.house.Script;
import com.chika.server.services.MqttService;
import com.chika.server.services.ScheduleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;

import java.util.concurrent.ScheduledFuture;

/**
 * @author Sy Nguyen
 * @version 1.0
 * @since 11-06-2020
 */
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
        String time = script.getTime();
        int index = time.indexOf(":");
        int hour = Integer.parseInt(time.substring(0, index)) - 7;
        String cron = String.format("0 %s %s ? * %s", time.substring(index + 1), hour, script.getDays());
        System.out.println(cron);
        ScheduledFuture<?> scheduledFuture = this.taskScheduler.schedule(this, new CronTrigger(cron));
        scheduledFutures.add(scheduledFuture);
    }

    @Override
    public void cancelAll() {
        scheduledFutures.forEach(scheduledFuture -> scheduledFuture.cancel(true));
    }

    @Override
    public void run() {
        logger.info("Schedule " + script.getName() + " is running at " + Formatter.formatTime(System.currentTimeMillis()));

        script.getDevices().forEach(scriptDevice -> {
            String message;
            if (scriptDevice.getType().contains("SW")) {
                message = scriptDevice.getState() ? "true" : "false";
            } else {
                message = "{\"type\":\"SR\"" +
                        ",\"button\":" + scriptDevice.getSwitchButton() +
                        ",\"state\":" + scriptDevice.getState() + "}";
            }
            mqttService.publish(scriptDevice.getTopic(), message);
        });
    }
}