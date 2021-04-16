package com.lening.controller;

import com.lening.entity.DeptBean;
import com.lening.service.DeptService;
import com.lening.utils.Page;
import com.lening.utils.ResultInfo;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author 作者：陆超
 * @Date 时间：2021/4/8 16:39
 */
@RestController
@RequestMapping("/dept")
public class DeptController {
    @Resource
    private DeptService deptService;
    @RequestMapping("/getDeptListConn")
    public Page<DeptBean> getDeptListConn(@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "5") Integer pageSize, @RequestBody DeptBean deptBean){
        return  deptService.getDeptListConn(pageNum,pageSize,deptBean);
    }

    @RequestMapping("/getDeptByDeptid")
    public DeptBean getDeptByDeptid(Long deptid){
        return deptService.getDeptByDeptid(deptid);
    }


    @RequestMapping("/saveDeptPost")
    public ResultInfo saveDeptPost(Long deptid,@RequestBody Long[] postids){
        System.out.println(deptid);
        System.out.println(postids.length);
        try {
            deptService.saveDeptPost(deptid,postids);
            return new ResultInfo(true, "编辑成功");
        }catch (Exception e){
            e.printStackTrace();
            return  new ResultInfo(false, "编辑失败");
        }
    }
}
