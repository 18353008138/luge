package com.lening.controller;

import com.lening.entity.MeunBean;
import com.lening.service.MeunService;
import com.lening.utils.ResultInfo;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author 作者：陆超
 * @Date 时间：2021/4/8 19:34
 */
@RestController
@RequestMapping("/meun")
public class MeunController {
    @Resource
    private MeunService meunService;

    @RequestMapping("/getMeunListByPid")
    public List<MeunBean> getMeunListByPid(@RequestParam(defaultValue = "1") Long pid){
        System.out.println(pid);
        return meunService.getMeunListByPid(pid);
    }

    /**
     * 添加
     * @param meunBean
     * @return
     */
    @RequestMapping("/saveMeun")
    public ResultInfo saveMeun(@RequestBody MeunBean meunBean){
        try {
            meunService.saveMeun(meunBean);
            return new ResultInfo(true, "编辑成功");
        }catch (Exception e){
            e.printStackTrace();
            return new ResultInfo(false, "编辑失败");
        }
    }

    @RequestMapping("/deleteMeunById")
    public ResultInfo deleteMeunById(Long id){
        try {
            meunService.deleteMeunById(id);
            return new ResultInfo(true, "删除成功");
        }catch (Exception e){
            e.printStackTrace();
            return new ResultInfo(false, "删除失败");
        }
    }
}
