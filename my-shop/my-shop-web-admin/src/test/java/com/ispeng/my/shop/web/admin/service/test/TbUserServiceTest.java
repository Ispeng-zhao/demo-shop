package com.ispeng.my.shop.web.admin.service.test;

import com.ispeng.my.shop.domain.TbUser;
import com.ispeng.my.shop.web.admin.service.TbUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-context.xml","classpath:spring-context-druid.xml","classpath:spring-context-mybatis.xml"})
public class TbUserServiceTest {
    @Autowired
    private TbUserService tbUserService;

    @Test
    public void testSelectAll(){
        List<TbUser> tbUsers = tbUserService.selectAll();
        for (TbUser tbUser: tbUsers){
            System.out.println(tbUser.getUsername());
        }
    }
    @Test
    public void testInsert() {
        TbUser tbUser = new TbUser();
        tbUser.setUsername("ispeng");
        tbUser.setPhone("15888888888");
        tbUser.setEmail("10@qq.com");
        tbUser.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));
        tbUser.setCreated(new Date());
        tbUser.setUpdated(new Date());

        tbUserService.save(tbUser);
    }

    @Test
    public  void delete(){
        tbUserService.delete(38L);
    }

    @Test
    public void MD5_String(){
        String str = "123456";
        System.out.println(DigestUtils.md5DigestAsHex(str.getBytes()));
    }
    @Test
    public void getByEmail(){
        String str  = "admin@qq.com";
        TbUser login = tbUserService.login(str,"123456");
        System.out.println(login.getUsername());

    }

}
