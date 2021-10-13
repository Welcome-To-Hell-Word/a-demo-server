package a.demo.server.rabbit;

import lombok.Data;

@Data
public class RabbitMQMessage {
    private String receiver;
    private String title;
    private String content;
}
