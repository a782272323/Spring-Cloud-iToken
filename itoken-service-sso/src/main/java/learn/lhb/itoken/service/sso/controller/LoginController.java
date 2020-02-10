package learn.lhb.itoken.service.sso.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import learn.lhb.itoken.common.domain.TbSysUser;
import learn.lhb.itoken.common.utils.CookieUtils;
import learn.lhb.itoken.common.utils.MapperUtils;
import learn.lhb.itoken.service.sso.service.LoginService;
import learn.lhb.itoken.service.sso.service.consumer.RedisService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * @author 梁鸿斌
 * @date 2020/2/4.
 * @time 1:15 上午
 */
@Controller //要跳转登录页面的
public class LoginController {
    //日志
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private RedisService redisService;

    @Autowired
    private LoginService loginService;

    /**
     * 跳转登录页面
     * @return
     */
    @GetMapping(value = "login")
    public String login(@RequestParam(required = false) String url,HttpServletRequest request,
                        Model model)   {
        String token = CookieUtils.getCookieValue(request, "token");

        //token 不为空,可能已经登录
        if (StringUtils.isNotBlank(token)) {
            String loginCode = redisService.get(token);
            //判断登录的用户名是否为空
            if (StringUtils.isNotBlank(loginCode)) {
                String json = redisService.get(loginCode);
                if (StringUtils.isNotBlank(json)) {
                    // TODO 有空去研究jackson和fastjson的工具类
//                    try {
//                        TbSysUser tbSysUser = MapperUtils.json2pojo(loginCode, TbSysUser.class);//到了这一步说明已经登录成功了
//
//                        if (tbSysUser != null)  {
//                            //如果在这里有url过来，说明子系统登录的用户是登录过的，就返回子系统的页面
//                            if (StringUtils.isNotBlank(url))    {
//                                return "redirect: " + url;
//                            }
//                        }
//
//                            //没有url过来，就将登录信息传到登录页面
//                            model.addAttribute("tbSysUser", tbSysUser);
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }

                    try {
                        TbSysUser tbSysUser = JSON.parseObject(json,new TypeReference<TbSysUser>(){});
                        if (tbSysUser != null)  {
                            //如果在这里有url传过来，说明子系统登录的用户是已经登录过的，就直接返回到子系统的界面
                            if (StringUtils.isNotBlank(url)) {
                                return "redirect: "+url;
                            }
                        }

                        //没有url过来，就转到已经登录的欢迎界面
                        model.addAttribute("tbSysUser",tbSysUser);
                    } catch (Exception e) {
                        model.addAttribute("message","发生了熔断，请稍后重试！");
                        e.printStackTrace();
                    }

                }

            }
        }

        if(StringUtils.isNotBlank(url)){
            model.addAttribute("url",url);
        }
        return "login";
    }

    /**
     * 登录业务
     * @param loginCode
     * @param password
     * @param request
     * @param response
     * @return
     */
    @PostMapping(value = "login")
    public String login(@RequestParam(required = true)String loginCode,
                        @RequestParam(required = true)String password,
                        @RequestParam(required = false) String url,
                        HttpServletRequest request,
                        HttpServletResponse response, RedirectAttributes redirectAttributes)   {
        //TODO 百度Model的用法，最好把源码给解析了
        //TODO 百度RedirectAttributes的用法，最好把源码给解析了
        TbSysUser tbSysUser = loginService.login(loginCode, password);

        //登录失败
        if (tbSysUser == null)  {
            //model.addAttribute("message","用户名或密码错误，请重新输入!");
            redirectAttributes.addFlashAttribute("message","用户名或密码错误，请重新输入！");
        } else {
            //登录成功
            String token = UUID.randomUUID().toString();
            String result = redisService.put(token, loginCode, 60 * 60 * 24);//放在redis上，24有效时期
            //将token放入缓存，这里做了熔断的判断
            if (StringUtils.isNotBlank(result) && "ok".equals(result)) {
                CookieUtils.setCookie(request, response, "token", token,60 * 60 * 24);//cookie时长一天
                if (StringUtils.isNotBlank(url))    {
                    //返回子系统请求登录的页面的路径
                    return "redirect:"+url;
                }
            }   else {
                //发生了熔断时的处理
                redirectAttributes.addFlashAttribute("message","服务器异常,请稍后重试!");
            }
        }

        //重新登录
        return "redirect:/login";
    }

    //TODO 到时页面加个注销的功能
    /**
     * 单点注销
     * @param url
     * @param request
     * @param response
     * @param model
     * @return
     */
    @GetMapping(value = "logout")
    public String logout(@RequestParam(required = false) String url,HttpServletRequest request,
                         HttpServletResponse response,Model model)  {
        //删除token，注销单点登录
        try {
            CookieUtils.deleteCookie(request,response,"token");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return login(url,request,model);
    }
}
