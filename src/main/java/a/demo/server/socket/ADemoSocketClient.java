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
                {
                    setName("线程1");
                }
                System.err.println(this+" "+this.getId()+" "+this.getName());
                try {
                    open(host,port);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
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
            String s=new String(bytes,"UTF-8");
            log.info("{} 收到信息 : {}",uuid,s);
            if (!s.isEmpty()){
                message+="-- ";
                message+=s;
                message+="\n";
            }
            FileWriter fileWriter=new FileWriter(new File("./target/"+uuid+".json"));
            fileWriter.write(message);
            fileWriter.flush();
        }
    }
}
