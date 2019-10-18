package com.chika.server.services.implement;

import com.chika.server.services.MqttService;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.stereotype.Service;

@Service
public class MqttServiceImpl implements MqttService, MqttCallback {

    private static MqttClient client;
    private int qos = 1;

    public MqttServiceImpl() {
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

    @Override
    public void publish(String topic, String message) {
        MqttMessage mqttMessage = new MqttMessage(message.getBytes());
        mqttMessage.setQos(qos);
        mqttMessage.setRetained(true);

        MqttTopic mqttTopic = client.getTopic(topic);
        try {
            mqttTopic.publish(mqttMessage);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void subscribe(String topic) {
        try {
            client.subscribe(topic, qos);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void connectionLost(Throwable throwable) {
        System.out.println("Connection lost because: " + throwable);
    }

    @Override
    public void messageArrived(String topic, MqttMessage message) {
        System.out.println("Topic: " + topic + ";\tMessage: " + message);
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
        System.out.println("MQTT Sent");
    }
}
