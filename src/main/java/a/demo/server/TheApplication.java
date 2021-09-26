package a.demo.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@MapperScan("a.demo.server.module.mapper")
@SpringBootApplication
public class TheApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context=SpringApplication.run(TheApplication.class,args);
        System.err.println(context);
    }
}
