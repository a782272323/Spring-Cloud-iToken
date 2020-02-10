package learn.lhb.itoken.web.backend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author 梁鸿斌
 * @date 2020/2/9.
 * @time 4:11 下午
 */
@Controller
public class MainController {

    //TODO 这里关于路由zuul跳转失败，暂时是因为跨服务器的问题，后续有空再补bug了

    /**
     * 统一的跳转路口，为了连接到zuul路由上面去
     */
    @GetMapping(value = "")
    public String main()    {
        return "main";
    }

    /**
     * 欢迎页面
     * @return
     */
    @GetMapping(value = "welcome")
    public String welcome() {
        return "welcome";
    }
}
