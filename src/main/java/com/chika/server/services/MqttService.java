package com.chika.server.services;

public interface MqttService {

    String MQTT_URL = "tcp://14.169.216.12:2502";
    String USERNAME = "chika";
    String PASSWORD = "2502";
    String CLIENT_ID = "Chika Server";

    void publish(String message);

    void subscribe(String topic);
}
