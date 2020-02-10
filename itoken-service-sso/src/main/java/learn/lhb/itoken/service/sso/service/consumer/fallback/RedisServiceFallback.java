package learn.lhb.itoken.service.sso.service.consumer.fallback;

import com.google.common.collect.Lists;
import learn.lhb.itoken.common.constants.HttpStatusConstants;
import learn.lhb.itoken.common.dto.BaseResult;
import learn.lhb.itoken.common.hystrix.Fallback;
import learn.lhb.itoken.common.utils.MapperUtils;
import learn.lhb.itoken.service.sso.service.consumer.RedisService;
import org.springframework.stereotype.Component;

/**
 * 获取Redis服务失败的熔断器代码
 * 同时是接口类RedisService的实现类
 * @author 梁鸿斌
 * @date 2020/2/4.
 * @time 12:28 上午
 */
@Component //被spring识别加载
public class RedisServiceFallback implements RedisService {


    /**
     *
     * @param key
     * @param value
     * @param seconds
     * @return
     */
    @Override
    public String put(String key, String value, long seconds) {
        //什么也不返回
        return null;
        //返回熔断信息
        //return Fallback.badGateway();
    }

    /**
     *
     * @param key
     * @return
     */
    @Override
    public String get(String key) {
        //什么也不返回
        return null;
        //返回熔断信息
        //return Fallback.badGateway();
    }
}
