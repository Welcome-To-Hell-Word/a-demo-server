package a.demo.server.rabbit;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQListener {
    @RabbitListener(queues = {RabbitMQConfiguration.QUEUE})
    public void RabbitMQListener(RabbitMQMessage rabbitMQMessage){
        System.err.println("RabbitMQListener ----- "+rabbitMQMessage);
    }
}
