package learn.lhb.itoken.common.web.config;

import learn.lhb.itoken.common.web.interceptor.ConstantsInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 拦截器配置
 *
 * @author 梁鸿斌
 * @date 2020/2/4.
 * @time 11:26 下午
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册一个拦截器,拦截路径是所有的
        registry.addInterceptor(new ConstantsInterceptor()).addPathPatterns("/**");
    }
}