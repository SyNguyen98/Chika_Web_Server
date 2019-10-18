package com.chika.server.services;

import org.springframework.stereotype.Service;

@Service
public interface MqttService {

    String MQTT_URL = "tcp://14.169.216.12:2502";
    String USERNAME = "chika";
    String PASSWORD = "2502";
    String CLIENT_ID = "Chika Server";

    void publish(String topic, String message);

    void subscribe(String topic);
}
