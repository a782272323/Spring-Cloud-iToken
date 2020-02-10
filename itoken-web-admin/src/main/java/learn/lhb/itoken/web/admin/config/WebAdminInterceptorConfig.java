package learn.lhb.itoken.web.admin.config;

import learn.lhb.itoken.web.admin.interceptor.WebAdminInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 拦截器配置类
 *
 * @author 梁鸿斌
 * @date 2020/2/5.
 * @time 5:39 下午
 */
@Configuration
public class WebAdminInterceptorConfig implements WebMvcConfigurer {

    //这里是拦截器注入到spring里面，不能使用new到方式
    @Bean
    WebAdminInterceptor webAdminInterceptor()   {
        return new WebAdminInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //除了"/static"，全部拦截
        registry.addInterceptor(webAdminInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/static");
    }
}
