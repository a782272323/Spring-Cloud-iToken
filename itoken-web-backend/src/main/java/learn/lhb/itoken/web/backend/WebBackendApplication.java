package learn.lhb.itoken.web.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author 梁鸿斌
 * @date 2020/2/9.
 * @time 4:07 下午
 */
@SpringBootApplication(scanBasePackages = "learn.lhb.itoken")
@EnableDiscoveryClient
@EnableFeignClients
public class WebBackendApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebBackendApplication.class,args);
    }
}
