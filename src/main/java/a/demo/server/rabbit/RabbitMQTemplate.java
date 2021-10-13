package a.demo.server.rabbit;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQTemplate {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void SendMessage(RabbitMQMessage rabbitMQMessage){
        System.err.println("SendMessage ----- "+rabbitMQMessage);
        rabbitTemplate.convertAndSend(RabbitMQConfiguration.QUEUE,rabbitMQMessage);
        System.err.println("SendMessage...");
    }
}
