package com.lening.service;

import com.lening.entity.DeptBean;
import com.lening.utils.Page;
import com.lening.utils.ResultInfo;

/**
 * @Author 作者：陆超
 * @Date 时间：2021/4/8 16:40
 */
public interface DeptService {
    Page<DeptBean> getDeptListConn(Integer pageNum, Integer pageSize, DeptBean deptBean);



    void saveDeptPost(Long deptid, Long[] postids);

    DeptBean getDeptByDeptid(Long deptid);
}
