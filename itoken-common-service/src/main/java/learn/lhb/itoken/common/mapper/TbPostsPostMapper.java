package learn.lhb.itoken.common.mapper;

import learn.lhb.itoken.common.domain.TbPostsPost;
import learn.lhb.itoken.common.utils.RedisCache;
import org.apache.ibatis.annotations.CacheNamespace;
import tk.mybatis.mapper.MyMapper;

@CacheNamespace(implementation = RedisCache.class)
public interface TbPostsPostMapper extends MyMapper<TbPostsPost> {
}