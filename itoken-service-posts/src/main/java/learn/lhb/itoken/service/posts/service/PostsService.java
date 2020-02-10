package learn.lhb.itoken.service.posts.service;

import learn.lhb.itoken.common.domain.BaseDomain;
import learn.lhb.itoken.common.service.BaseService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

/**
 * @author 梁鸿斌
 * @date 2020/2/6.
 * @time 10:31 下午
 */
public interface PostsService<T extends BaseDomain> extends BaseService<T> {
}
