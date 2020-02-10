package learn.lhb.itoken.web.posts.service.fallback;


import learn.lhb.itoken.web.posts.service.RedisService;
import org.springframework.stereotype.Component;

/**
 * 消费者RedisService的熔断器类
 *
 * @author 梁鸿斌
 * @date 2020/2/5.
 * @time 6:03 下午
 */
@Component
public class RedisServiceFallback implements RedisService {
    @Override
    public String put(String key, String value, long seconds) {
        return null;
    }

    @Override
    public String get(String key) {
        return null;
    }
}
