package learn.lhb.itoken.service.sso.mapper;

import learn.lhb.itoken.common.domain.TbSysUser;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.MyMapper;
import tk.mybatis.spring.annotation.MapperScan;

@Repository
@MapperScan
public interface TbSysUserMapper extends MyMapper<TbSysUser> {


}