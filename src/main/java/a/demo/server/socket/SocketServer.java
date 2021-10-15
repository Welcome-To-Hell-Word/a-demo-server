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
            log.info("已启动端口为 {} の服务",serverSocket.getLocalPort());
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }

        while (started){
            try {
                Socket socket=serverSocket.accept();
                socket.setKeepAlive(true);
                SocketClient socketClient=SocketTools.open(socket);
                log.info("客户端 {} 已连接",socketClient.getKey());
                log.info("当前在线 {}",TheSocketPool.ONLINE_SOCKET_MAP.size());
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
