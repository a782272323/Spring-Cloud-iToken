package learn.lhb.itoken.web.posts.interceptor;

import learn.lhb.itoken.common.domain.TbSysUser;
import learn.lhb.itoken.common.utils.CookieUtils;
import learn.lhb.itoken.common.utils.MapperUtils;
import learn.lhb.itoken.common.web.constants.WebConstants;
import learn.lhb.itoken.common.web.utils.HttpServletUtils;
import learn.lhb.itoken.web.posts.service.RedisService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * itoken-web-admin专用的拦截器
 *
 * @author 梁鸿斌
 * @date 2020/2/5.
 * @time 5:37 下午
 */
public class WebPostsInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisService redisService;
// TODO 到时研究使用yml的注入值方式获取，云上配置文件的url
//    @Value("${}")
//    private String WebAdminUrl;
//
//    @Value()
//    private String ServiceSSOUrl;

    @Value(value = "${hosts.sso}")
    private String HOSTS_SSO;

//    @Value(value = "${hosts.web-posts}")
//    private String HOSTS_WEB_POSTS;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = CookieUtils.getCookieValue(request, WebConstants.SESSION_TOKEN);

        //token 为空表示一定没有登录,跳转sso单点登录页面
        if (StringUtils.isBlank(token)) {
            try {
                //response.sendRedirect("http://localhost:8503/login?url=http://localhost:8602");
//                response.sendRedirect();
                //response.sendRedirect(String.format("%s/login?url=%s",HOSTS_SSO,HOSTS_WEB_POSTS));
                // TODO 百度String的各种用法，比如 format
                //下面拿的是本机的地址，也就是服务器上面的
                response.sendRedirect(String.format("%s/login?url=%s",HOSTS_SSO, HttpServletUtils.getFullPath(request)));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return false;
        }

        return true;//这里为false代表就无法进入下面的方法
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        HttpSession session = request.getSession();
        TbSysUser tbSysUser =(TbSysUser) session.getAttribute(WebConstants.SESSION_USER);
        //TODO 这个单点登录的登录检查只做了账号的检查，要加上密码的检查
        //已经登录的状态
        if (tbSysUser != null)  {
            if (modelAndView != null)   {
                //TODO 百度modelAndView的用法，做成笔记
                modelAndView.addObject(WebConstants.SESSION_USER,tbSysUser);
            }
        } else {
            //未登录状态
            String token = CookieUtils.getCookieValue(request, WebConstants.SESSION_TOKEN);
            //检查token不为空，或者token没过期
            if (StringUtils.isNotBlank(token)) {
                String loginCode = redisService.get(token);
                if (StringUtils.isNotBlank(loginCode))  {
                    String json = redisService.get(loginCode);
                    if (StringUtils.isNotBlank(json))   {
                        try {
                            //有登录信息，已经登录状态,创建局部会话
                            tbSysUser = MapperUtils.json2pojo(json, TbSysUser.class);
                            if (modelAndView != null)   {
                                modelAndView.addObject(WebConstants.SESSION_USER,tbSysUser);
                            }
                            //放到session
                            request.getSession().setAttribute(WebConstants.SESSION_USER,tbSysUser);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

        }

        //二次确认是否有用户信息
        if (tbSysUser == null)  {
            try {
//                response.sendRedirect("http://localhost:8503/login?url=http://localhost:8602");
                response.sendRedirect(String.format("%s/login?url=%s",HOSTS_SSO, HttpServletUtils.getFullPath(request)));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
