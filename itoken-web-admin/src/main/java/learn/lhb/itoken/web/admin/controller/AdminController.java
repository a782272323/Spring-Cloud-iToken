package learn.lhb.itoken.web.admin.controller;


import learn.lhb.itoken.web.admin.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller //TODO RestController是涉及到转发，这个只是跳转,具体百度清除了，并把笔记补全了
public class AdminController {
    //这里我的理解是，由于前后端不分离（前后端聚合），所以这里理论上讲是跳转到指定到html界面

    //TODO 还有，把所有的注解整到一起，然后一个个百度，属于spring的，mybatis的，log4的等等分类好

    //TODO 先快速把Spring Cloud iToken的基础过一遍，然后把所有笔记补全，同时补上微服务对应的页面，和使用SSM把项目给过一遍，同时优化jsp的项目，然后使用springboot把myshop给实现了，最后看oracle和mongoDB。

    @GetMapping(value = {"","index"})
    public String index()   {
        return "index";
    }
}
