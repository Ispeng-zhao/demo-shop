package com.ispeng.my.shop.web.admin.web.controller;

import com.ispeng.my.shop.commons.constant.ConstantUtils;
import com.ispeng.my.shop.domain.TbUser;
import com.ispeng.my.shop.web.admin.service.TbUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {
    //自动装配
    @Autowired
    private TbUserService tbUserService ;

    /**
     * 跳转路径
     * @return
     */
    @RequestMapping(value = {"","login"}, method = RequestMethod.GET)
    public String login (){

        return "login";
    }
    @RequestMapping(value = {"login"},method = RequestMethod.POST)
    public String login (@RequestParam(required = true) String email, @RequestParam(required = true) String password, HttpServletRequest httpServletRequest, Model model){

        TbUser tbUser = tbUserService.login(email, password);
        //登录失败
        if (tbUser == null){
            model.addAttribute("message","账号或用户名密码错误");
            return login();
        }
        else {
//            将登录信息放入会话
            httpServletRequest.getSession().setAttribute(ConstantUtils.SESSION_USER,tbUser);
            return "redirect:/main";
        }
    }
    @RequestMapping(value = "logout",method = RequestMethod.GET)
    public String logout(HttpServletRequest httpServletRequest){
        httpServletRequest.getSession().invalidate();
        return "login";
    }

}
