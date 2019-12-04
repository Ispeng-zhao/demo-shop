package com.ispeng.my.shop.web.admin.dao;

import com.ispeng.my.shop.domain.TbUser;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface TbUserDao {
    /**
     * 查询用户表全部信息
     *
     * @return
     */
    List<TbUser> selectAll();

    void insert(TbUser tbUser);

    void delete(Long id);

    TbUser getByEmail(String email);

    TbUser getById(Long id);

    void update(TbUser tbUser);

    /**
     * 搜索
     * @param tbUser
     * @return
     */
    List<TbUser> search(TbUser tbUser);
    /**
     * 批量删除
     */
    void deleteMulti (String []ids);

    /**
     *分页查询
     * @param params MAP start 和 length
     * @return
     */
    List<TbUser> page(Map<String,Object> params);

    /**
     * 查询总数
     * @return
     */
    int count();
}
