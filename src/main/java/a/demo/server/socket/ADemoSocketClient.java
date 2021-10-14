package a.demo.server.socket;

import lombok.extern.slf4j.Slf4j;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.net.Socket;
import java.util.UUID;

@Slf4j
public class ADemoSocketClient {
    static String host="127.0.0.1";
    static int port=40404;
    public static void main(String[] args) {
        new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    open(host,port);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                {
                    setName("1");
                }
            }
        }.run();
    }

    private static void open(String host,int port) throws Exception {
        Socket socket=new Socket(host,port);
        socket.setOOBInline(true);

        DataInputStream dataInputStream=new DataInputStream(socket.getInputStream());
        DataOutputStream dataOutputStream=new DataOutputStream(socket.getOutputStream());
        String uuid=UUID.randomUUID().toString();
        dataOutputStream.write(uuid.getBytes());
        String message="";
        while (true){
            byte[]bytes=new byte[1024];
            dataInputStream.read(bytes);
            message+=new String(bytes,"UTF-8");
            FileWriter fileWriter=new FileWriter(new File("./target/json.json"));
            fileWriter.write(message);
            fileWriter.flush();
        }
    }
}
