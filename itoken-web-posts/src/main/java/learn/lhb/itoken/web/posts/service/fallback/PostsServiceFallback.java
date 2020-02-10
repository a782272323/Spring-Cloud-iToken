package learn.lhb.itoken.web.posts.service.fallback;

import learn.lhb.itoken.common.hystrix.Fallback;
import learn.lhb.itoken.web.posts.service.PostsService;
import org.springframework.stereotype.Component;

/**
 * @author 梁鸿斌
 * @date 2020/2/7.
 * @time 9:59 下午
 */
@Component
public class PostsServiceFallback implements PostsService {

    @Override
    public String page(int pageNum, int pageSize, String tbPostsPostJson) {
        return Fallback.badGateway();
    }

    @Override
    public String get(String postGuid) {
        return Fallback.badGateway();
    }

    @Override
    public String save(String tbPostsPostJson, String optsBy) {
        return Fallback.badGateway();
    }
}
