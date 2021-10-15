package a.demo.server.socket;

import lombok.extern.slf4j.Slf4j;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

import static a.demo.server.socket.TheSocketPool.*;

@Slf4j
public class SocketTools {
    public static boolean isSocketClosed(SocketClient socketClient){
        try {
            socketClient.getSocket().sendUrgentData(1);
            log.info("检测 {} 状态",socketClient.getKey());
            return false;
        } catch (Exception e) {
            return true;
        }
    }
    public static void close(SocketClient socketClient){
        if (socketClient!=null){
            remove(socketClient.getKey());
            Socket socket=socketClient.getSocket();
            try {
                socket.shutdownInput();
                socket.shutdownOutput();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    socket.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static SocketClient open(Socket socket){
        SocketClient socketClient=new SocketClient();
        socketClient.setSocket(socket);
        try {
            socketClient.setDataInputStream(new DataInputStream(socket.getInputStream()));
            socketClient.setDataOutputStream(new DataOutputStream(socket.getOutputStream()));
            byte[]bytes=new byte[1024];
            socketClient.getDataInputStream().read(bytes);
            socketClient.setKey(new String(bytes,"UTF-8").trim());
            add(socketClient);
            return socketClient;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void sendMessage(SocketClient socketClient,String message){
        try {
            socketClient.getDataOutputStream().write(message.getBytes("UTF-8"));
            log.info("发送信息 {} 到 {}",message,socketClient.getKey());
        } catch (Exception e) {
            e.printStackTrace();
            close(socketClient);
        }
    }

    public static void sendMessage(String key,String message){
        SocketClient socketClient=ONLINE_SOCKET_MAP.get(key);
        if (socketClient==null)
            return;
        try {
            socketClient.getDataOutputStream().write(message.getBytes("UTF-8"));
            log.info("发送信息 {} 到 {}",message,socketClient.getKey());
        } catch (Exception e) {
            e.printStackTrace();
            close(socketClient);
        }
    }
}
