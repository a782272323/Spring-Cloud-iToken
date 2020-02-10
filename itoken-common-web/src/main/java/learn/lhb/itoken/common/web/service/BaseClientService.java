package learn.lhb.itoken.common.web.service;

import learn.lhb.itoken.common.hystrix.Fallback;

/**
 * @author 梁鸿斌
 * @date 2020/2/7.
 * @time 9:53 下午
 */
public interface BaseClientService {

    //TODO 百度 default
    default String page(int pageNum,int pageSize,String domainJson)  {
        return Fallback.badGateway();
    }
}
