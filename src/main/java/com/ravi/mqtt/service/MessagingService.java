package com.ravi.mqtt.service;

import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessagingService {

	@Autowired
	private IMqttClient client;

	public void publish(String topic, String payload, int qos, boolean retained)
			throws MqttPersistenceException, MqttException {
		MqttMessage message = new MqttMessage();
		message.setPayload(payload.getBytes());
		message.setQos(qos);
		message.setRetained(retained);
		client.publish(topic, message);
		// client.publish(topic, payload.getBytes(), qos, retained);
		System.out.println("Message sent");
		client.disconnect();
	}

	public void subscribe(String topic) throws MqttException {
		System.out.println("Message Recieved for topic -" + topic + " is :");
		client.subscribeWithResponse(topic, (top, msg) -> {
			System.out.println(msg.getId() + "->" + new String(msg.getPayload()));
		});
	}
}
