package com.ispeng.my.shop.web.admin.web.controller;

import com.ispeng.my.shop.commons.dto.BaseResult;
import com.ispeng.my.shop.commons.dto.PageInfo;
import com.ispeng.my.shop.domain.TbUser;
import com.ispeng.my.shop.web.admin.service.TbUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "user")
public class UserController {

    @Autowired
    private TbUserService tbUserService;

    @ModelAttribute
    public TbUser getTbUser(Long id){
        TbUser tbUser = null;


        if (id != null){
            tbUser = tbUserService.getById(id);
        }
        else {
            tbUser =  new TbUser();
        }

        return  tbUser;
    }
    /**
     * 挑战用户列表页
     * @param model
     * @return
     */
    @RequestMapping(value = "list",method = RequestMethod.GET)
    public String list(Model model){
        List<TbUser> tbUsers = tbUserService.selectAll();
        model.addAttribute("tbUsers",tbUsers);
        return "user_list";
    }

    /**
     * 跳转用户表单页
     * @return
     */
    @RequestMapping(value = "form",method = RequestMethod.GET)
    public String form(){
        return "user_form";
    }
    @RequestMapping(value = "save",method = RequestMethod.POST)
    public String save (TbUser tbUser, RedirectAttributes redirectAttributes,Model model){
        BaseResult baseResult = tbUserService.save(tbUser);

        //保存成功
         if (baseResult.getStatus() == BaseResult.STATUS_SUCCSESS){
            redirectAttributes.addFlashAttribute("baseResult",baseResult);
            return "redirect:/user/list";
        }

         //保存失败
         else {
             model.addAttribute("baseResult",baseResult);
             return "user_form";
         }
    }
    @RequestMapping(value = "search", method = RequestMethod.POST)
    public String search(TbUser tbUser,Model model){
        List<TbUser> tbUsers = tbUserService.search(tbUser);
        model.addAttribute("tbUsers",tbUsers);
        return "user_list";
    }

    /**
     * 删除用户信息
     * @param ids
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public BaseResult delete(String ids){

        BaseResult baseResult =  null ;

        if (StringUtils.isNotBlank(ids)){
            String[] idArray = ids.split(",");
            tbUserService.deleteMulti(idArray);
            baseResult = BaseResult.success("删除数据成功");
        }

        else {
            baseResult = BaseResult.fail("删除数据失败");
        }
        return baseResult;
    }

    /**
     * 分页查询
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "page",method = RequestMethod.GET)
    public PageInfo<TbUser> page(HttpServletRequest request){

        String strDraw = request.getParameter("draw");
        String strStart = request.getParameter("start");
        String strLength = request.getParameter("length");

        int draw = strDraw == null ? 0 :Integer.parseInt(strDraw);
        int start = strDraw == null ? 0 :Integer.parseInt(strStart);
        int length = strDraw == null ? 10 :Integer.parseInt(strLength);

        //封装插件需要的数据格式

        PageInfo<TbUser> page = tbUserService.page(start, length, draw);
        return page;



    /*    //查看请求的
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()){
            System.out.println(String.format("key : %s   value: % s",parameterNames.nextElement(),request.getParameter(parameterNames.nextElement()));
        }
        return "";*/
    }
}
