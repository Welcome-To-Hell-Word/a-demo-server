package a.demo.server.socket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
@Component
public class SocketServer {
    @Value("${socket.port}")
    private Integer port;
    private Boolean started;
    private ServerSocket serverSocket;
    private ExecutorService executorService=Executors.newCachedThreadPool();

    public void start(){
        start(null);
    }
    public void start(Integer port){
        try {
            serverSocket=new ServerSocket(port==null?this.port:port);
            started=true;
            log.info("{}",serverSocket.getLocalPort());
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }

        while (started){
            try {
                Socket socket=serverSocket.accept();
                socket.setKeepAlive(true);
                SocketClient socketClient=SocketTools.open(socket);
                log.info("{}",socketClient.getKey());
                SocketTools.sendMessage(socketClient,"a-demo-server-message");
                if (socketClient!=null){
                    executorService.submit(socketClient);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
