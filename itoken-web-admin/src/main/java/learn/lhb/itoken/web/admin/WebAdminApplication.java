package learn.lhb.itoken.web.admin;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

//TODO 有空把springboot整合Thymeleaf的笔记补全了

@SpringBootApplication(scanBasePackages = "learn.lhb.itoken")
@EnableDiscoveryClient
@EnableFeignClients
public class WebAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebAdminApplication.class,args);
    }
}
