package a.demo.server.socket;

import com.google.common.base.Strings;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("socket")
@Controller
public class ADemoControllerOfSocket {
    @RequestMapping(value = {"sendMessage/{message}","sendMessage/{message}/{key}"})
    public @ResponseBody Object sendMessage(@PathVariable(value = "message",required = false)String message,@PathVariable(value = "key",required = false)String key){
        if (Strings.isNullOrEmpty(message)){
            message="message";
        }
        if (Strings.isNullOrEmpty(key)){
            if (TheSocketPool.ONLINE_SOCKET_MAP.size()>0){
                for (SocketClient socketClient:TheSocketPool.ONLINE_SOCKET_MAP.values()){
                    SocketTools.sendMessage(socketClient,message);
                }
            }
            return null;
        }
        SocketTools.sendMessage(key,message);
        return null;
    }
}
