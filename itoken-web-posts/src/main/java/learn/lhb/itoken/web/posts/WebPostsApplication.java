package learn.lhb.itoken.web.posts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author 梁鸿斌
 * @date 2020/2/7.
 * @time 2:01 下午
 */
@SpringBootApplication(scanBasePackages = "learn.lhb.itoken")
@EnableFeignClients
@EnableDiscoveryClient
public class WebPostsApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebPostsApplication.class,args);
    }
}
