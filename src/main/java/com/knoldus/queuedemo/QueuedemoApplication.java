package com.knoldus.queuedemo;

import com.azure.identity.DefaultAzureCredentialBuilder;
import com.azure.storage.queue.QueueClient;
import com.azure.storage.queue.QueueClientBuilder;
import com.azure.storage.queue.models.SendMessageResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class QueuedemoApplication {
	static final String queueName = "queue-demo";
	static final String endpoint = "https://queueanshika.queue.core.windows.net/queue-demo";

	static final String connectionString = "EndpointSuffix=core.windows.net";

	private final static Logger logger = LoggerFactory.getLogger(QueuedemoApplication.class);


	public static void main(String[] args) {
		QueueClient queueClient = new QueueClientBuilder()
				.endpoint(endpoint)
				.credential(new DefaultAzureCredentialBuilder().build())
				.queueName(queueName)
				.connectionString(connectionString)
				.buildClient();

		SendMessageResult sendMessageResult = queueClient.sendMessage("Hello world");
		logger.info("Send message id: {}", sendMessageResult.getMessageId());

		System.out.println("Press any key to end the application");
		try {
			System.in.read();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
