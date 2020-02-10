package learn.lhb.itoken.web.admin.service;

import learn.lhb.itoken.web.admin.service.fallback.RedisServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Redis服务的消费者
 *
 * @author 梁鸿斌
 * @date 2020/2/5.
 * @time 6:02 下午
 */
@Component
@FeignClient(value = "itoken-service-redis",fallback = RedisServiceFallback.class)
public interface RedisService {

    @PostMapping(value = "put")
    public String put(
            @RequestParam(value = "key") String key,
            @RequestParam(value = "value") String value,
            @RequestParam(value = "seconds") long seconds);

    @GetMapping(value = "get")
    public String get(@RequestParam(value = "key") String key);
}
