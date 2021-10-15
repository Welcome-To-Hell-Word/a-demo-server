package a.demo.server.socket;

import java.util.concurrent.ConcurrentHashMap;

public class TheSocketPool {
    public static final ConcurrentHashMap<String,SocketClient>ONLINE_SOCKET_MAP=new ConcurrentHashMap<>();
    public static void add(SocketClient socketClient){
        if (socketClient!=null&&!socketClient.getKey().isEmpty()){
            ONLINE_SOCKET_MAP.put(socketClient.getKey(),socketClient);
        }
    }
    public static void remove(String key){
        if (!key.isEmpty()){
            ONLINE_SOCKET_MAP.remove(key);
        }
    }
}
