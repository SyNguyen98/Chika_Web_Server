package com.chika.server.services;

import org.eclipse.paho.client.mqttv3.MqttAsyncClient;

public interface MqttService {

    String MQTT_URL = "tcp://14.169.166.240:2502";
    String USERNAME = "chika";
    String PASSWORD = "2502";
    String CLIENT_ID = MqttAsyncClient.generateClientId();

    void publish(String topic, String message);

    void subscribe(String topic);
}
