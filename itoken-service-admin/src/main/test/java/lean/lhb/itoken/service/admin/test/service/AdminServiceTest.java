package lean.lhb.itoken.service.admin.test.service;


import learn.lhb.itoken.common.domain.TbSysUser;
import learn.lhb.itoken.service.admin.ServiceAdminApplication;

import learn.lhb.itoken.service.admin.service.AdminService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ServiceAdminApplication.class)
@ActiveProfiles(value = "prod") //jar运行环境
@Transactional
@Rollback //TODO 把spring事务管理的注解给补了
public class AdminServiceTest {

    @Autowired
    private AdminService adminService;

    @Test
    public void register()  {
        TbSysUser tbSysUser = new TbSysUser();
        tbSysUser.setUserCode(UUID.randomUUID().toString());
        tbSysUser.setUserName("LHB");
        tbSysUser.setLoginCode("LHB@qq.com");
        tbSysUser.setPassword("123456");
        tbSysUser.setUserType("0");
        tbSysUser.setMgrType("1");
        tbSysUser.setStatus("0");
        tbSysUser.setCreateDate(new Date());
        tbSysUser.setCreateBy(tbSysUser.getUserCode());
        tbSysUser.setUpdateDate(new Date());
        tbSysUser.setUpdateBy(tbSysUser.getUserCode());
        tbSysUser.setCorpCode("0");
        tbSysUser.setCorpName("iToken");
//        adminService.register(tbSysUser);
    }

    @Test
    public void login() {
//        TbSysUser tbSysUser = adminService.login("LHB@qq.com","123456");
        //断言的使用，忘记了去看笔记
//        Assert.assertNotNull(tbSysUser);
    }
}
