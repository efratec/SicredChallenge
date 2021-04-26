package br.com.sicredi.consumer.listen;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static br.com.sicredi.util.PropertiesUtil.getProperties;

@Component
public class Subscriber {

    private static final Logger LOGGER = LoggerFactory.getLogger(Subscriber.class);

    @RabbitListener(queues = "#{queue.getName()}")
    public void receive(final String message) {
        LOGGER.info("Listening messages from the queue!!");
        LOGGER.info("Received the following message from the queue= " + message);
        try {
            String comando = getProperties() + "sincronizar.jar " + " " + message;
            Runtime.getRuntime().exec(comando);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
