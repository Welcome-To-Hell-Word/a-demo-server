package a.demo.server.tools;

import java.net.Socket;

public class TheOther {
    public static boolean isValidPort(int port){
        return 0<=port&&port<=65535;
    }
    public static boolean isAvailablePort(int port){
        if (!isValidPort(port)){
            return false;
        } else {
            try {
                new Socket("127.0.0.1",port).close();
                return false;
            } catch (Exception e) {
                return true;
            }
        }
    }
    public static String toString(Object o){
        return o==null?"":o.toString();
    }
}
