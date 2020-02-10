package learn.lhb.itoken.service.posts.controller;

import com.github.pagehelper.PageInfo;
import learn.lhb.itoken.common.domain.TbPostsPost;
import learn.lhb.itoken.common.dto.BaseResult;
import learn.lhb.itoken.common.utils.MapperUtils;
import learn.lhb.itoken.service.posts.service.PostsService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * @author 梁鸿斌
 * @date 2020/2/6.
 * @time 8:12 下午
 */
@RestController
@RequestMapping(value = "v1/posts")
public class PostsController {
    //日志
    public static final Logger logger = LoggerFactory.getLogger(PostsController.class);

    @Autowired
    private PostsService<TbPostsPost> postsService1;

    @Autowired
    private PostsService postsService;

    /**
     * 根据 postGuid 获取文章
     * @param postGuid
     * @return
     */
    @GetMapping(value = "{postGuid}")
    public BaseResult get(@PathVariable(value = "postGuid") String postGuid)  {

        TbPostsPost tbPostsPost = new TbPostsPost();
        tbPostsPost.setPostGuid(postGuid);

        TbPostsPost obj = postsService1.selectOne(tbPostsPost);//默认使用属性来作为查询条件，若想自己的条件查询，使用Example
        return BaseResult.ok(obj);
    }

    /**
     * 保存文章
     *
     * @param tbPostsPostJson
     * @param optsBy
     * @return
     */
    @PostMapping
    public BaseResult save(
            @RequestParam(required = true,value = "tbPostsPostJson") String tbPostsPostJson,
            @RequestParam(required = true,value = "optsBy") String optsBy
    )    {


        int result = 0;

        TbPostsPost tbPostsPost = null;

        try {
            tbPostsPost = MapperUtils.json2pojo(tbPostsPostJson,TbPostsPost.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (tbPostsPost != null)    {

            //主键为空表示新增
            if (StringUtils.isBlank(tbPostsPost.getPostGuid())) {
                tbPostsPost.setPostGuid(UUID.randomUUID().toString());
                result = postsService.insert(tbPostsPost,optsBy);
            } else {

                //修改
                result = postsService.update(tbPostsPost,optsBy);
            }

            //最少受影响行数为1行，修改或添加操作成功
            if (result > 0) {
                return BaseResult.ok("保存文章成功");
            }
        }

        return BaseResult.ok("保存文章失败");
    }

    /**
     * 分页查询
     *
     * @param pageNum
     * @param pageSize
     * @param tbPostsPostJson
     * @return
     * @throws Exception
     */
    @GetMapping(value = "page/{pageNum}/{pageSize}")
    public BaseResult page(
            @PathVariable(required = true) int pageNum,
            @PathVariable(required = true) int pageSize,
            @RequestParam(required = false) String tbPostsPostJson
    ) throws Exception    {

        logger.info("pageNum = "+pageNum);
        logger.info("pageSize = "+pageSize);
        logger.info("tbPostsPostsJson = "+tbPostsPostJson);
        TbPostsPost tbPostsPost = null;
        if (StringUtils.isNotBlank(tbPostsPostJson))    {
            tbPostsPost = MapperUtils.json2pojo(tbPostsPostJson,TbPostsPost.class);
        }

        //这里若语句报错，则去自动生成的实体类那里，把@Table的注解上面只留表的名字，把数据库的去掉
        PageInfo pageInfo = postsService.page(pageNum,pageSize,tbPostsPost);

        //分页后的结果集
        List<TbPostsPost> list = pageInfo.getList();

        //封装 Cursor对象
        BaseResult.Cursor cursor = new BaseResult.Cursor();
        cursor.setTotal(new Long(pageInfo.getTotal()).intValue());
        cursor.setOffset(pageInfo.getPageNum());
        cursor.setLimit(pageInfo.getPageSize());

        return BaseResult.ok(list,cursor);
    }

}
