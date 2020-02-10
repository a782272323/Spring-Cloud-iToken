package learn.lhb.itoken.service.sso.service.impl;

import learn.lhb.itoken.common.domain.TbSysUser;
import learn.lhb.itoken.common.utils.MapperUtils;
import learn.lhb.itoken.service.sso.mapper.TbSysUserMapper;
import learn.lhb.itoken.service.sso.service.LoginService;
import learn.lhb.itoken.service.sso.service.consumer.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import tk.mybatis.mapper.entity.Example;

/**
 * @author 梁鸿斌
 * @date 2020/2/4.
 * @time 12:17 上午
 */
@Service
public class LoginServiceImpl implements LoginService {

    private static final Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);


    @Autowired
    private RedisService redisService;

    @Autowired
    private TbSysUserMapper tbSysUserMapper;

    @Override
    public TbSysUser login(String loginCode, String plantPassword) {
        //TODO 把IDEA调试模式做个笔记，越详细越好

        TbSysUser tbSysUser = null;

        //从redis查询有无该登录信息的缓存
        String json = redisService.get(loginCode);

        //若json为空，说明缓存无数据
        if (json == null)   {//从数据库取数据
            //TODO example不用百度了，直接把源码吃透，然后放到笔记里
            //查询loginCode和密码是否匹配
            Example example = new Example((TbSysUser.class));
            example.createCriteria().andEqualTo("loginCode",loginCode);

            //查询数据库
            tbSysUser = tbSysUserMapper.selectOneByExample(example);
            //加密密码
            String password = DigestUtils.md5DigestAsHex(plantPassword.getBytes());
            if (tbSysUser != null && password.equals(tbSysUser.getPassword()))   {
                //登录成功，把数据丢缓存
                try {
                    redisService.put(loginCode,MapperUtils.obj2json(tbSysUser), 60 * 60 * 24);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return tbSysUser;
            } else {
                return null;
            }
        } else {
            //缓存有数据，从缓存取数据,并封装成json格式
            try {
                tbSysUser = MapperUtils.json2pojo(json, TbSysUser.class);
            } catch (Exception e) {
                logger.warn("触发了熔断:{}", e.getMessage());
            }
        }

        return tbSysUser;

    }
}
