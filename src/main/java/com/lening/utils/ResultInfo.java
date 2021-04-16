package com.lening.utils;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author 作者：陆超
 * @Date 时间：2021/4/8 20:06
 */
@Data
public class ResultInfo implements Serializable {
    private boolean flag;
    private String msg;

    public ResultInfo() {
    }

    public ResultInfo(boolean flag, String msg) {
        this.flag = flag;
        this.msg = msg;
    }
}
