package com.winowsi.studySwagger.util;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: Restful封装的数据类
 * @author: zhaoyao
 * @date: 2021/08/22
 */

public class Information {

    //返回一个状态码
    //100成功 200失败
    private int code;
    //返回一个状态信息
    private String msg;
    //返回数据
    private Map<String ,Object> extents=new HashMap<String,Object>();

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Map<String, Object> getExtents() {
        return extents;
    }

    public void setExtents(Map<String, Object> extents) {
        this.extents = extents;
    }

    public Information() {
    }

    public static Information Success(){
        Information information=new Information();
        information.setCode(100);
        information.setMsg("success!");
        return information;
    }
    public static Information Defeated(){
        Information information=new Information();
        information.setCode(200);
        information.setMsg("Be defeated!");
        return information;
    }
    public Information AddData(String key, Object object){
        this.extents.put(key,object);
        return this;
    }

}
