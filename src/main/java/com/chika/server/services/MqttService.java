package com.chika.server.services;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MqttService implements MqttCallback {

    private static final String MQTT_URL = "tcp://soldier.cloudmqtt.com:16607";
    private static final String USERNAME = "pcnlljoy";
    private static final String PASSWORD = "q2zXZf4CSUUE";
    private static final String CLIENT_ID = MqttAsyncClient.generateClientId();

    private final Logger logger = LoggerFactory.getLogger(MqttService.class);
    private static MqttClient client;
    private static MqttService mqttService;

    public MqttService() {
        MqttConnectOptions connOpts = new MqttConnectOptions();
        connOpts.setCleanSession(true);
        connOpts.setKeepAliveInterval(1000);
        connOpts.setUserName(USERNAME);
        connOpts.setPassword(PASSWORD.toCharArray());

        try {
            client = new MqttClient(MQTT_URL, CLIENT_ID, new MemoryPersistence());
            client.setCallback(this);
            client.connect(connOpts);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public static MqttService getInstance() {
        if (mqttService == null) {
            mqttService = new MqttService();
        }
        return mqttService;
    }

    public void publish(String topic, String message) {
        MqttMessage mqttMessage = new MqttMessage(message.getBytes());
        mqttMessage.setQos(2);
        mqttMessage.setRetained(true);

        MqttTopic mqttTopic = client.getTopic(topic);
        try {
            mqttTopic.publish(mqttMessage);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void connectionLost(Throwable throwable) {
        logger.error("Connection lost because: " + throwable, throwable);
    }

    @Override
    public void messageArrived(String topic, MqttMessage message) {
        logger.info("Topic: " + topic + ";\t\tMessage: " + message);
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
        logger.info("MQTT Sent");
    }
}
