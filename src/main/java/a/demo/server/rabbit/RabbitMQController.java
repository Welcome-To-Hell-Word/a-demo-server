package a.demo.server.rabbit;

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

    public Object sendMessage(@RequestBody Map<String,Object> map){
        return null;
    }
}
