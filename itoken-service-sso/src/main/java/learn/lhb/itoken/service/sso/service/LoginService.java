package learn.lhb.itoken.service.sso.service;

import learn.lhb.itoken.common.domain.TbSysUser;
import learn.lhb.itoken.service.sso.service.consumer.RedisService;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author 梁鸿斌
 * @date 2020/2/3.
 * @time 11:38 下午
 */
public interface LoginService {


    /**
     * 登录
     * @param loginCode
     * @param plantPassword
     * @return
     */
    public TbSysUser login(String loginCode,String plantPassword);
}
