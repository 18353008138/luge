package com.lening.service;

import com.lening.entity.MeunBean;

import java.util.List;

/**
 * @Author 作者：陆超
 * @Date 时间：2021/4/8 19:35
 */
public interface MeunService {
    List<MeunBean> getMeunListByPid(Long pid);

    void saveMeun(MeunBean meunBean);

    void deleteMeunById(Long id);
}
