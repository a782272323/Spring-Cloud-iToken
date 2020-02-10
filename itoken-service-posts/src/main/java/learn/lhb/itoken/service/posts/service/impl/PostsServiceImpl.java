package learn.lhb.itoken.service.posts.service.impl;

import learn.lhb.itoken.common.domain.BaseDomain;
import learn.lhb.itoken.common.domain.TbPostsPost;
import learn.lhb.itoken.common.mapper.TbPostsPostMapper;
import learn.lhb.itoken.common.service.impl.BaseServiceImpl;
import learn.lhb.itoken.service.posts.service.PostsService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.MyMapper;

/**
 * 实现类
 *
 * @author 梁鸿斌
 * @date 2020/2/6.
 * @time 8:09 下午
 */
@Service
@Transactional(readOnly = true)
public class PostsServiceImpl extends BaseServiceImpl<TbPostsPost, TbPostsPostMapper> implements PostsService<TbPostsPost> {
}
