package com.lening.controller;

import com.github.pagehelper.PageInfo;
import com.lening.entity.MeunBean;
import com.lening.entity.PostBean;
import com.lening.entity.UserBean;
import com.lening.mapper.MeunMapper;
import com.lening.service.UserService;
import com.lening.utils.Page;
import com.lening.utils.ResultInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author 作者：陆超
 * @Date 时间：2021/4/6 19:20
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;


    /**
     * 登录
     * @param ub
     * @return
     */
    @RequestMapping("/getLogin")
    public String getLogin(UserBean ub){
        return "ok";
    }
    @RequestMapping("/getUserList")
    public List<UserBean> getUserList(){
        List<UserBean> userList = userService.getUserList();
        return userList;
    }

    @RequestMapping("/getUserListConn")
    public Page<UserBean> getUserListConn(@RequestBody UserBean ub,@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "5")Integer pageSize) {
        Page<UserBean> userListConn = userService.getUserListConn(ub, pageNum, pageSize);
        return userListConn;
    }

    @RequestMapping("/getMeunList")
    public List<MeunBean> getMeunList(){
        return userService.getMeunList();
    }

    /**
     * 数据回显
     * @param id
     * @return
     */
    @RequestMapping("/toUserDept")
    public UserBean toUserDept(Long id){
        return userService.toUserDept(id);
    }



     @RequestMapping("/saveUserDept")
     public ResultInfo saveUserDept(Long id,@RequestBody Long[] deptids){

         try {
             userService.saveUserDept(id,deptids);
             return new ResultInfo(true, "编辑成功");
         }catch (Exception e){
             e.printStackTrace();
             return new ResultInfo(false, "编辑失败");
         }
     }


    /**
     *  分配职位回显
     */
     @RequestMapping("/toUserPost")
    public UserBean toUserPost(Long id){
         UserBean userBean = userService.toUserpost(id);
         return userBean;
     }

     @RequestMapping("/saveUserPost")
    public ResultInfo saveUserPost(@RequestBody UserBean userBean){
         try {
             userService.saveUserPost(userBean);
             return new ResultInfo(true, "保存成功");
         }catch (Exception e){
             return new ResultInfo(false, "保存失败");
         }
     }
}
