package com.lening.controller;

import com.lening.entity.MeunBean;
import com.lening.entity.PostBean;
import com.lening.service.PostService;
import com.lening.utils.Page;

import com.lening.utils.ResultInfo;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author 作者：陆超
 * @Date 时间：2021/4/8 18:42
 */
@RestController
@RequestMapping("/post")
public class PostController {
    @Resource
    private PostService postService;

    @RequestMapping("/getPostListConn")
    public Page<PostBean> getPostListConn(@RequestParam(defaultValue = "1") Integer pageNum,@RequestParam(defaultValue = "5") Integer pageSize,@RequestBody PostBean postBean){
        return postService.getPostListConn(pageNum,pageSize,postBean);
    }

    @RequestMapping("/getMeunListById")
    public List<MeunBean> getMeunListById(Long id){
        return postService.getMeunListById(id);
    }

    @RequestMapping("/savePostMeun")
    public ResultInfo savePostMeun(Long postid,@RequestBody Long[] ids){
        try {
            postService.savePostMeun(postid,ids);
            return new ResultInfo(true, "保存成功");
        }catch (Exception e){
            return new ResultInfo(false, "保存失败");
        }

    }
}
