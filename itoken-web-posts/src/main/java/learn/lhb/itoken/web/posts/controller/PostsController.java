package learn.lhb.itoken.web.posts.controller;

import learn.lhb.itoken.common.domain.TbPostsPost;
import learn.lhb.itoken.common.domain.TbSysUser;
import learn.lhb.itoken.common.dto.BaseResult;
import learn.lhb.itoken.common.utils.MapperUtils;
import learn.lhb.itoken.common.web.constants.WebConstants;
import learn.lhb.itoken.common.web.controller.BaseController;
import learn.lhb.itoken.web.posts.service.PostsService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.UUID;

/**
 * @author 梁鸿斌
 * @date 2020/2/7.
 * @time 3:12 下午
 */
@Controller
public class PostsController extends BaseController<TbPostsPost, PostsService> {

    @Autowired
    private PostsService postsService;

    //TODO 百度 ModelAttribute
    @ModelAttribute
    public TbPostsPost tbPostsPost(String postGuid)    {
        TbPostsPost tbPostsPost = null;
        if (StringUtils.isBlank(postGuid))  {
            tbPostsPost = new TbPostsPost();
        } else {
            String json = postsService.get(postGuid);
            try {
                tbPostsPost = MapperUtils.json2pojo(json, TbPostsPost.class);
            } catch (Exception e) {
                e.printStackTrace();
            }

            //二次确认是否为空
            if (tbPostsPost == null)    {
                tbPostsPost = new TbPostsPost();
            }
        }

        return tbPostsPost;
    }

    /**
     * 跳转首页
     * @return
     */
//    @GetMapping(value = {"","index"})
    //TODO 可能是熔断给挡下来了
    @RequestMapping(value = {"","main"},method = RequestMethod.GET)
    public String main()   {
        return "main";
    }


    /**
     * 跳转列表页
     * @return
     */
    @GetMapping(value = "index")
    public String incex()   {
        return "index";
    }

    /**
     * 跳转表单页面
     * @return
     */
    @GetMapping(value = {"form"})
    public String form()    {
        return "form";
    }

    /**
     * 保存文章
     * @param tbPostsPost
     * @return
     */
    // TODO 百度 RedirectAttributes
    @PostMapping(value = "save")
    public String save(TbPostsPost tbPostsPost, HttpServletRequest request, RedirectAttributes redirectAttributes) throws Exception{
        //初始化
//        tbPostsPost.setPostGuid(UUID.randomUUID().toString());
        //提交时间
        tbPostsPost.setTimePublished(new Date());
        //状态
        tbPostsPost.setStatus("1");
        //更新者
        tbPostsPost.setUpdateBy("梁鸿斌");
        //更新时间
        tbPostsPost.setUpdateDate(new Date());

        //从session拿值
        TbSysUser admin =(TbSysUser) request.getSession().getAttribute(WebConstants.SESSION_USER);
        String tbPostsPostJson = MapperUtils.obj2json(tbPostsPost);

        String json = postsService.save(tbPostsPostJson,admin.getUserCode());
        BaseResult baseResult = MapperUtils.json2pojo(json, BaseResult.class);

        redirectAttributes.addFlashAttribute("baseResult",baseResult);

        if (baseResult.getSuccess().endsWith("成功")) {
            //回到文章首页
            return "redirect:/index";
        }
        //回到form
        return "redirect:form";
    }
}
