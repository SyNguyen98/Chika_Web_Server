package com.chika.server.services.implement;

import com.chika.server.services.MqttService;
import lombok.Data;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

@Data
public class MqttServiceImpl implements MqttService, MqttCallback {

    private MqttClient client;
    private String topicName;
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
    public void publish(String message) {
        MqttMessage mqttMessage = new MqttMessage(message.getBytes());
        mqttMessage.setQos(qos);
        mqttMessage.setRetained(true);

        MqttTopic topic = client.getTopic(topicName);
        try {
            topic.publish(mqttMessage);
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
