package com.lening.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lening.entity.MeunBean;
import com.lening.entity.PostBean;
import com.lening.entity.PostBeanExample;
import com.lening.mapper.MeunMapper;
import com.lening.mapper.PostMapper;
import com.lening.service.PostService;
import com.lening.utils.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author 作者：陆超
 * @Date 时间：2021/4/8 18:44
 */
@Service
public class PostServiceImpl implements PostService {
    @Resource
    private PostMapper postMapper;
    @Resource
    private MeunMapper meunMapper;
    @Override
    public Page<PostBean> getPostListConn(Integer pageNum, Integer pageSize, PostBean postBean) {
        PageHelper.startPage(pageNum, pageSize);
        PostBeanExample example = new PostBeanExample();
        PostBeanExample.Criteria criteria = example.createCriteria();
        if (postBean!=null){
            if (postBean.getPostname()!=null&&postBean.getPostname().length()>=1){
                criteria.andPostnameLike("%"+postBean.getPostname()+"%");
            }
        }
        List<PostBean> list = postMapper.selectByExample(example);
        PageInfo pageInfo = new PageInfo(list);
        Long total = pageInfo.getTotal();
        Page page = new Page(pageInfo.getPageNum()+"",total.intValue(),pageInfo.getSize()+"");
        page.setList(list);
        return page;
    }

    @Override
    public List<MeunBean> getMeunListById(Long id) {
        List<MeunBean> mlist = meunMapper.selectByExample(null);
        List<Long> postMeunIds = postMapper.getPostMeunIds(id);
        if (postMeunIds!=null&&postMeunIds.size()>=1){
            for (Long meunId : postMeunIds) {
                for (MeunBean bean : mlist) {
                    if (meunId.equals(bean.getId())){
                        bean.setChecked(true);
                        break;
                    }
                }

            }
        }
        return mlist;
    }

    @Override
    public void savePostMeun(Long postid, Long[] ids) {
        postMapper.deletePostMeunByPostid(postid);
        if (ids!=null&&ids.length>=1){
            for (Long id : ids) {
                postMapper.savePostMeun(postid, id);
            }
        }
    }
}
