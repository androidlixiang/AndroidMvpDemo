package com.common.util;

/**
 * Created by cloudplug on 17/3/22.
 */
public class SPUtilImpl {

    //切换正式测试环境
    public void setHuanJing(String position) {
        SPUtil.put("huanjing", position);
    }

    public String getHuanJing() {
        return SPUtil.getString("huanjing");
    }

}
