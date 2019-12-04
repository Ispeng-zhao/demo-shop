package com.ispeng.my.shop.web.admin.service.Impl;

import com.ispeng.my.shop.commons.dto.BaseResult;
import com.ispeng.my.shop.commons.dto.PageInfo;
import com.ispeng.my.shop.commons.persistence.BaseEntity;
import com.ispeng.my.shop.commons.utils.RegexpUtils;
import com.ispeng.my.shop.domain.TbUser;
import com.ispeng.my.shop.web.admin.dao.TbUserDao;
import com.ispeng.my.shop.web.admin.service.TbUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TbUserServiceImpl implements TbUserService {

    @Autowired
    private TbUserDao tbUserDao;

    @Override
    public List<TbUser> selectAll() {
        return tbUserDao.selectAll();
    }

    /**
     * 新增用户
     * @param tbUser
     */
    @Override
    public BaseResult save(TbUser tbUser) {
        BaseResult baseResult = checkTbUSer(tbUser);

        //通过验证
        if (baseResult.getStatus() == BaseResult.STATUS_SUCCSESS){
            //设置用户更新时间
            tbUser.setUpdated(new Date());

            //新增用户
            if (tbUser.getId() == null){
                //密码需要加密
                tbUser.setPassword(DigestUtils.md5DigestAsHex(tbUser.getPassword().getBytes()));
                tbUser.setCreated(new Date());
                tbUserDao.insert(tbUser);
            }
            //编辑用户
            else {
                tbUserDao.update(tbUser);
            }

            baseResult.setMessage("保存用户信息成功");
        }

        return baseResult;
    }

    @Override
    public void delete(Long id) {
        tbUserDao.delete(id);
    }

    @Override
    public TbUser login(String email, String password) {
        TbUser  user = tbUserDao.getByEmail(email);

        //当前账号存在
        if (user != null){
            String Md5password = DigestUtils.md5DigestAsHex(password.getBytes());
            if (Md5password.equals(user.getPassword())){
                return user;
            }
        }

        return null;
    }

    @Override
    public TbUser getById(Long id) {
        return tbUserDao.getById(id);
    }

    @Override
    public List<TbUser> search(TbUser tbUser) {
        return tbUserDao.search(tbUser);
    }

    @Override
    public void deleteMulti(String[] ids) {
        tbUserDao.deleteMulti(ids);
    }

    @Override
    public PageInfo<TbUser> page(int start, int length,int draw) {
        PageInfo<TbUser> pageInfo = new PageInfo<>();
        int count =  tbUserDao.count();

        Map<String,Object> params = new HashMap<>();
        params.put("start",start);
        params.put("length",length);

        pageInfo.setDraw(draw);
        pageInfo.setRecordsTotal(count);
        pageInfo.setRecordsFiltered(count);
        pageInfo.setData(tbUserDao.page(params));


        return pageInfo;
    }

    /**
     * 查询总数
     * @return
     */
    @Override
    public int count() {
        return tbUserDao.count();
    }

    /**
     * 用户信息的有效性验证
     * @param tbUser
     */
    private BaseResult checkTbUSer(TbUser tbUser){

        BaseResult baseResult = BaseResult.success();

        //非空验证
        if (StringUtils.isBlank(tbUser.getEmail())){
            baseResult = BaseResult.fail("邮箱不能为空，请重新输入");
        }
        else if (!RegexpUtils.checkEmail(tbUser.getEmail())){
            baseResult = BaseResult.fail("邮箱格式不正确，请重新输入");
        }
        else  if (StringUtils.isBlank(tbUser.getUsername())){
            baseResult = BaseResult.fail("姓名不能为空，请重新输入");
        }

        else  if (StringUtils.isBlank(tbUser.getPassword())){
            baseResult = BaseResult.fail("密码不能为空，请重新输入");
        }

        else  if (StringUtils.isBlank(tbUser.getPhone())){
            baseResult = BaseResult.fail("手机号不能为空，请重新输入");
        }
        else if (!RegexpUtils.checkPhone(tbUser.getPhone())){
            baseResult = BaseResult.fail("手机格式不正确，请重新输入");
        }
        return baseResult;
    }


}
