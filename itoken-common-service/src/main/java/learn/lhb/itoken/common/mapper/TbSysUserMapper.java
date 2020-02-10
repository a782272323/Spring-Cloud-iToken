package learn.lhb.itoken.common.mapper;

import learn.lhb.itoken.common.domain.TbSysUser;
import learn.lhb.itoken.common.utils.RedisCache;
import org.apache.ibatis.annotations.CacheNamespace;
import tk.mybatis.mapper.MyMapper;

@CacheNamespace(implementation = RedisCache.class)
public interface TbSysUserMapper extends MyMapper<TbSysUser> {
}