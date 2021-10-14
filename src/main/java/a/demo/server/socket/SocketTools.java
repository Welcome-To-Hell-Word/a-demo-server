package a.demo.server.socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

import static a.demo.server.socket.TheSocketPool.*;

public class SocketTools {
    public static boolean isSocketClosed(SocketClient socketClient){
        try {
            socketClient.getSocket().sendUrgentData(1);
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
            socketClient.setKey(new String(bytes,"UTF-8"));
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
        } catch (Exception e) {
            e.printStackTrace();
            close(socketClient);
        }
    }
}
