package com.lening.service;

import com.lening.entity.MeunBean;
import com.lening.entity.UserBean;
import com.lening.utils.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author 作者：陆超
 * @Date 时间：2021/4/6 19:23
 */
public interface UserService {

    List<UserBean> getUserList();

    Page<UserBean> getUserListConn(UserBean ub,Integer pageNum,Integer pageSize);

    List<MeunBean> getMeunList();

    UserBean toUserDept(Long id);


    void saveUserDept(Long id, Long[] deptids);

    UserBean toUserpost(Long id);

    void saveUserPost(UserBean userBean);
}
