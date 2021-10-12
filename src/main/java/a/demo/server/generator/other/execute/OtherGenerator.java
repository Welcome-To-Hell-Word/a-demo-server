package a.demo.server.generator.other.execute;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.Properties;

public class OtherGenerator {

    final static String FILE_PATH=System.getProperty("user.dir")+"/src/main/java/a/demo/server/generator/other/execute/file/";
    final static String TEMPLATE_PATH=System.getProperty("user.dir")+"/src/main/java/a/demo/server/generator/other/execute/template/vm/";

    public static void main(String[] args) throws Exception {
        FileOutputStream fileOutputStream=new FileOutputStream(new File(FILE_PATH+"vm.txt"));
        OutputStreamWriter outputStreamWriter=new OutputStreamWriter(fileOutputStream,"UTF-8");
        BufferedWriter bufferedWriter=new BufferedWriter(outputStreamWriter);
        Template template=new VelocityEngine(
                new Properties(){{
                    this.setProperty(Velocity.FILE_RESOURCE_LOADER_PATH,TEMPLATE_PATH);
                    this.setProperty(Velocity.ENCODING_DEFAULT,"UTF-8");
                    this.setProperty(Velocity.OUTPUT_ENCODING,"UTF-8");
                }}
        ).getTemplate("vm.vm","UTF-8");
        template.merge(
                new VelocityContext(){{
                    put("key","value");
                }},
                bufferedWriter
        );
        bufferedWriter.flush();
        bufferedWriter.close();
        outputStreamWriter.close();
        fileOutputStream.close();

        System.err.println("a demo");
    }
}
