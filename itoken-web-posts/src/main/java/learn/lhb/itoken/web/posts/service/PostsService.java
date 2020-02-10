package learn.lhb.itoken.web.posts.service;

import learn.lhb.itoken.common.web.service.BaseClientService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author 梁鸿斌
 * @date 2020/2/7.
 * @time 9:52 下午
 */
@Component
@FeignClient(value = "itoken-service-posts")
public interface PostsService extends BaseClientService {

    /**
     * 分页
     * @param pageNum
     * @param pageSize
     * @param tbPostsPostJson
     * @return
     */
    @GetMapping(value = "v1/posts/page/{pageNum}/{pageSize}")
    public String page(
            @PathVariable(required = true,value = "pageNum") int pageNum,
            @PathVariable(required = true,value = "pageSize") int pageSize,
            @RequestParam(required = false,value = "tbPostsPostJson") String tbPostsPostJson
    );

    /**
     *
     * @param postGuid
     * @return
     */
    @GetMapping(value = "v1/posts/{postGuid}")
    public String get(@PathVariable(required = true,value = "postGuid")String postGuid);

    @PostMapping(value = "v1/posts")
    public String save(
            @RequestParam(required = true,value = "tbPostsPostJson")String tbPostsPostJson,
            @RequestParam(required = true,value = "optsBy") String optsBy
    );
}
