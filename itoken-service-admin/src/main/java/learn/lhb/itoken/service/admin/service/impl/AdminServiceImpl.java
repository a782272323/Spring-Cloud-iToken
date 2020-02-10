package learn.lhb.itoken.service.admin.service.impl;

import learn.lhb.itoken.common.domain.TbSysUser;
import learn.lhb.itoken.common.mapper.TbSysUserMapper;
import learn.lhb.itoken.common.service.impl.BaseServiceImpl;
import learn.lhb.itoken.service.admin.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import tk.mybatis.mapper.entity.Example;

@Service
@Transactional(readOnly = true)
public class AdminServiceImpl extends BaseServiceImpl<TbSysUser, TbSysUserMapper> implements AdminService<TbSysUser> {

    //在这里通用的增删改查就实现完成了,而特殊的增删改查就在本项目(itoken-service-admin的mapper下面完成)


}
