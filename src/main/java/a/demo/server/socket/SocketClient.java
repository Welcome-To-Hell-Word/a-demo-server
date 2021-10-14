package a.demo.server.socket;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

import static a.demo.server.socket.SocketTools.*;

@Slf4j
@Data
public class SocketClient implements Runnable {
    private Socket socket;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;
    private String key;
    private String message;
    @Override
    public void run() {
        while (true){
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (isSocketClosed(this)){
                close(this);
                log.info("{}",this.getKey());
                break;
            }
        }
    }
}
