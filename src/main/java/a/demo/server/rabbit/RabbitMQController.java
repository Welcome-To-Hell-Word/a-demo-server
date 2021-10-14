package a.demo.server.rabbit;

import a.demo.server.tools.TheOther;
import a.demo.server.tools.TheResult;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("rabbit")
@RestController
public class RabbitMQController {
    private final RabbitMQTemplate rabbitMQTemplate;

    @RequestMapping(value = "sendMessage")
    public Object sendMessage(@RequestBody Map<String,Object> map){
        rabbitMQTemplate.SendMessage(
                new RabbitMQMessage(){{
                    setReceiver(TheOther.toString(map.get("receiver")));
                    setTitle(TheOther.toString(map.get("title")));
                    setContent(TheOther.toString(map.get("content")));
                }}
        );
        return new TheResult().success(map);
    }
}
