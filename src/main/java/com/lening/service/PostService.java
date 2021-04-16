package com.lening.service;

import com.lening.entity.MeunBean;
import com.lening.entity.PostBean;
import com.lening.utils.Page;

import java.util.List;

/**
 * @Author 作者：陆超
 * @Date 时间：2021/4/8 18:43
 */
public interface PostService {
    Page<PostBean> getPostListConn(Integer pageNum, Integer pageSize, PostBean postBean);

    List<MeunBean> getMeunListById(Long id);

    void savePostMeun(Long postid, Long[] ids);
}
