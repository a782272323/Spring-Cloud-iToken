package learn.lhb.itoken.service.redis.controller;

import learn.lhb.itoken.service.redis.service.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedisController {
    //日志
    private static final Logger logger = LoggerFactory.getLogger(RedisController.class);

    /**
     * service-admin会先到service-redis取数据，没有再去访问数据库
     */

    @Autowired
    private RedisService redisService;

    @PostMapping(value = "put")
    public String put(String key,String value,long seconds) {
        redisService.put(key,value,seconds);
        logger.info("Rides服务：注册key");
        return "ok";
    }

    @GetMapping(value = "get")
    public Object get(String key)   {

        Object o = redisService.get(key);
        if (o != null)  {

            String json = String.valueOf(o);
            logger.info("获取key成功,json = ",json);
            return json;
        }
        logger.info("获取key失败");
        return null;
    }
}
