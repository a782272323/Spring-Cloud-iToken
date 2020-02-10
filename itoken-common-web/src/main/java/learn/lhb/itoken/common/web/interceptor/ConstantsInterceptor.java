package learn.lhb.itoken.common.web.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 初始化常量拦截器（使用AdminLTE模版的）
 *
 * @author 梁鸿斌
 * @date 2020/2/4.
 * @time 11:14 下午
 */
public class ConstantsInterceptor implements HandlerInterceptor {
    //常量
    //部署了CDN的服务器的IP：端口
    public static final String HOST_CDN = "http://47.95.206.128:6005";
    //AdminLTE模版的路径常量
    public static final String TEMPLATE_ADMIN_LTE = "adminlte/v2.4.3";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if (modelAndView != null)   {
            //拿到cdn的值，然后传给页面
            modelAndView.addObject("adminlte",HOST_CDN + "/" + TEMPLATE_ADMIN_LTE);
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

}
