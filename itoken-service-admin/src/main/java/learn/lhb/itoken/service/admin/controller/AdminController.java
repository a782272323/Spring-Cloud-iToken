package learn.lhb.itoken.service.admin.controller;

import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import learn.lhb.itoken.common.domain.TbSysUser;
import learn.lhb.itoken.common.dto.BaseResult;
import learn.lhb.itoken.common.utils.MapperUtils;
import learn.lhb.itoken.service.admin.service.AdminService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "v1/admins")
public class AdminController {

    //TODO 有空把增删改实现了
    //TODO 这里查询的数据为空的bug还未解决

    @Autowired
    private AdminService adminService;

    /**
     * 根据 ID 获取管理员
     * @param userCode
     * @return
     */
    @GetMapping(value = "{userCode}")
    public BaseResult get(@PathVariable(value = "userCode") String userCode)  {
        TbSysUser tbSysUser = new TbSysUser();
        tbSysUser.setUserCode(userCode);
        TbSysUser obj =(TbSysUser) adminService.selectOne(tbSysUser);
        return BaseResult.ok(obj);
    }


    @PostMapping
    public BaseResult save(
            @RequestParam(required = true) String tbSysUserJson,
            @RequestParam(required = true) String optsBy
    )    {

        int result = 0;

        TbSysUser tbSysUser = null;
        try {
            tbSysUser = MapperUtils.json2pojo(tbSysUserJson,TbSysUser.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (tbSysUser != null)  {
            //密码加密
            String password = DigestUtils.md5DigestAsHex(tbSysUser.getPassword().getBytes());
            tbSysUser.setPassword(password);

            //新增用户
            if (StringUtils.isBlank(tbSysUser.getUserCode()))   {
                tbSysUser.setUserCode((UUID.randomUUID().toString()));
                result = adminService.insert(tbSysUser,optsBy);
            } else {

                //修改用户
                result = adminService.update(tbSysUser,optsBy);
            }

            //最少有一行数据受影响，添加，修改操作则成功
            if (result > 0) {
                return BaseResult.ok("保存管理员成功");
            }
        }

        return  BaseResult.ok("保存管理员失败");
    }

    /**
     * 查询数据（分页）
     * @param pageNum
     * @param pageSize
     * @param tbSysUser
     * @return
     */
    @ApiOperation(value = "管理员服务分页查询接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum",value = "页码",required = true,dataType = "int",paramType = "path")
    })
    @GetMapping(value = "page/{pageNum}/{pageSize}")
    public BaseResult page(
            @PathVariable(required = true) int pageNum,
            @PathVariable(required = true) int pageSize,
            @RequestParam(required = false) TbSysUser tbSysUser
    ) throws Exception   {

        PageInfo pageInfo = adminService.page(pageNum, pageSize, tbSysUser);

        //分页查询后的结果集
        List<TbSysUser> list = pageInfo.getList();

        //封装 Cursor 对象
        BaseResult.Cursor cursor = new BaseResult.Cursor();
        cursor.setTotal(new Long(pageInfo.getTotal()).intValue());//总条数
        cursor.setOffset(pageInfo.getPageNum());//当前页数
        cursor.setLimit(pageInfo.getPageSize());//每页显示条数

        return BaseResult.ok(list,cursor);
    }

}

//TODO 做RESTful风格的笔记（结合百度，和单体炼狱里面的笔记）

//TODO 尽量一天解决一个todo，今天（2020.2.2）解决了spring注解，还剩mybatis，springboot，springcloud的，