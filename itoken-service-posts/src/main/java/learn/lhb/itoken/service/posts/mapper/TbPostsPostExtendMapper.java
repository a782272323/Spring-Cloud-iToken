package learn.lhb.itoken.service.posts.mapper;

import learn.lhb.itoken.common.domain.TbPostsPost;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.MyMapper;

@Repository
@Primary //不加这个会报错找到多个bean，但只需要注入一个
public interface TbPostsPostExtendMapper extends MyMapper<TbPostsPost> {
}