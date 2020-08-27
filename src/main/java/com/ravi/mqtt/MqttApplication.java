package com.ravi.mqtt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.ravi.mqtt.service.MessagingService;

@SpringBootApplication
public class MqttApplication implements CommandLineRunner{
	
	@Autowired
	private MessagingService messagingService;
	
	@Autowired
	private ConfigurableApplicationContext context;

	public static void main(String[] args) {
		SpringApplication.run(MqttApplication.class, args);
	}
	
	@Override
	public void run(String ...args) throws Exception {
		String topic ="DemoTopic";
		messagingService.subscribe(topic);
		messagingService.publish(topic, "Sample msg", 0, true);
		context.close();
	}

}
