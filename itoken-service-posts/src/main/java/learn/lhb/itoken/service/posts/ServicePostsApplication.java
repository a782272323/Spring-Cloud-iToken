package learn.lhb.itoken.service.posts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author 梁鸿斌
 * @date 2020/2/6.
 * @time 10:07 下午
 */
@SpringBootApplication(scanBasePackages = "learn.lhb.itoken")
@EnableEurekaClient
@EnableSwagger2
@MapperScan(basePackages = {"learn.lhb.itoken.common.mapper", "learn.lhb.itoken.service.posts.mapper"})
public class ServicePostsApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServicePostsApplication.class,args);
    }
}
