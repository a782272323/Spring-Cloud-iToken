package learn.lhb.itoken.service.sso.service.consumer;

import learn.lhb.itoken.service.sso.service.consumer.fallback.RedisServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 在这里是消费者Feign，使用Redis服务
 * @author 梁鸿斌
 * @date 2020/2/4.
 * @time 12:25 上午
 */
@FeignClient(value = "itoken-service-redis",fallback = RedisServiceFallback.class)
@Component
public interface RedisService {

    /**
     *
     * @param key
     * @param value
     * @param seconds
     * @return
     */
    @PostMapping(value = "put")
    public String put(
            @RequestParam(value = "key")String key,
            @RequestParam(value = "value")String value,
            @RequestParam(value = "seconds")long seconds);

    /**
     *
     * @param key
     * @return
     */
    @GetMapping(value = "get")
    public String get(@RequestParam(value = "key")String key);
}
