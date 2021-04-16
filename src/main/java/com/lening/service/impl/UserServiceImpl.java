package com.lening.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lening.entity.*;
import com.lening.mapper.DeptMapper;
import com.lening.mapper.MeunMapper;
import com.lening.mapper.UserMapper;
import com.lening.service.UserService;
import com.lening.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author 作者：陆超
 * @Date 时间：2021/4/6 19:24
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private MeunMapper meunMapper;
    @Autowired
    private DeptMapper deptMapper;

    @Override
    public List<UserBean> getUserList() {
        return userMapper.selectByExample(null);
    }

    @Override
    public Page<UserBean> getUserListConn(UserBean ub, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        UserBeanExample example = new UserBeanExample();
        UserBeanExample.Criteria criteria = example.createCriteria();
        if (ub!=null){
            if (ub.getUname()!=null&&ub.getUname().length()>=1){
                criteria.andUnameLike("%"+ub.getUname()+"%");
            }
            if (ub.getAge()!=null){
                //大于或等于
                criteria.andAgeGreaterThanOrEqualTo(ub.getAge());
            }
            if (ub.getEage()!=null){
                //小于或等于
                criteria.andAgeLessThanOrEqualTo(ub.getEage());
            }
        }
        List<UserBean> list = userMapper.selectByExample(example);
        PageInfo<UserBean> pageInfo = new PageInfo<>(list);
        Long total=pageInfo.getTotal();
        Page page = new Page(pageInfo.getPageNum()+"",total.intValue(),pageInfo.getPageSize()+"");
        page.setList(list);
        return page;
    }

    @Override
    public List<MeunBean> getMeunList() {
        return meunMapper.selectByExample(null);
    }

    @Override
    public UserBean toUserDept(Long id) {
        UserBean bean = userMapper.selectByPrimaryKey(id);
        Long[] longs = userMapper.getUserDeptidsById(id);
        bean.setDeptids(longs);
        List<DeptBean> list = deptMapper.selectByExample(null);
        bean.setDlist(list);
        return bean;
    }

    /**
     * 修改科室
     * @param deptids 科室数组
     * @param id 用户id
     */
    @Override
    public void saveUserDept(Long id, Long[] deptids) {
        userMapper.deleteUserDept(id);
        userMapper.deleteUserPost(id);
        if (deptids!=null&&deptids.length>=1){
            for (Long deptid:deptids) {
                userMapper.insertUserDept(id, deptid);
            }

        }
    }

    @Override
    public UserBean toUserpost(Long id) {
        UserBean userBean = userMapper.selectByPrimaryKey(id);
        //查询用户有没有部门
        List<DeptBean> dlist = userMapper.getUserDeptById(id);
        //开始循环部门，查询部门里有没有职位
        if (dlist!=null&&dlist.size()>=1){
            for (DeptBean bean : dlist) {
                List<PostBean> plist = deptMapper.getDeptPostList(bean.getId());
                Long[] posrids = deptMapper.getUserPostByIdAndDeptid(id, bean.getId());
                bean.setPlist(plist);
                bean.setPostids(posrids);
            }
        }
        userBean.setDlist(dlist);
        return userBean;
    }

    @Override
    public void saveUserPost(UserBean userBean) {
        userMapper.deleteUserPost(userBean.getId());
        if (userBean.getDlist()!=null&&userBean.getDlist().size()>=1){
            for (DeptBean deptBean : userBean.getDlist()) {
                if (deptBean.getPostids()!=null&&deptBean.getPostids().length>=1){
                    for (Long postid : deptBean.getPostids()) {
                        userMapper.saveUserPost(userBean.getId(),postid);
                    }
                }
            }
        }
    }
}
