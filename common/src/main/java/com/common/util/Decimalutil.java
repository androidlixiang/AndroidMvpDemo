package com.common.util;

/**
 * Copyright (C), 2019-2019, 国民健康科技有限公司
 *
 * @Description: 数字工具类
 * @Author: lixiang
 * @CreateDate: 2019/10/24 10:13
 */
public class Decimalutil {


    /**
     * 保留2位小数
     * @param value
     * @return
     */
    public static String to2(float value) {
//        DecimalFormat df = new DecimalFormat("#.00");
        return String.format("%.2f", value);
    }


}
