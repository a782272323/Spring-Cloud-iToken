package learn.lhb.itoken.service.redis.service;


import org.springframework.beans.factory.annotation.Autowired;

public interface RedisService {


    /**
     *
     * @param key
     * @param value
     * @param seconds 超时时间
     */
    public void put(String key,Object value,long seconds);

    /**
     *
     * @param key
     */
    public Object get(String key);
}
