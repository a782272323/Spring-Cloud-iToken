package learn.lhb.itoken.service.admin.mapper;

import learn.lhb.itoken.common.domain.TbSysUser;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.MyMapper;

/**
 * 除了增删改查之外，拓展的业务需求的mapper类
 *
 * @author 梁鸿斌
 * @date 2020/2/6.
 * @time 2:50 下午
 */
@Repository
public interface TbSysUserExtendMapper extends MyMapper<TbSysUser> {
}
