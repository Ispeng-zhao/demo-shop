package com.ispeng.my.shop.web.admin.service;

import com.ispeng.my.shop.commons.dto.BaseResult;
import com.ispeng.my.shop.commons.dto.PageInfo;
import com.ispeng.my.shop.domain.TbUser;

import java.util.List;

public interface TbUserService {

    public List<TbUser> selectAll();

    public BaseResult save(TbUser tbUser);

    public void delete(Long id);

    public TbUser login(String email , String password);

    public TbUser getById(Long id);

    public List<TbUser> search(TbUser tbUser);

    public void deleteMulti (String []ids);

    PageInfo<TbUser> page(int start, int length,int draw);

    int count();
}
