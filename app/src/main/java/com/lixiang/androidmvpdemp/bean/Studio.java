package com.lixiang.androidmvpdemp.bean;

import org.litepal.crud.LitePalSupport;

import java.io.Serializable;

/**
 * Copyright (C), 2019-2019, 国民健康科技有限公司
 *
 * @ProjectName: 国民健康
 * @Description: java类作用描述
 * @Author: lixiang
 * @CreateDate: 2019/11/5 18:19
 * @UpdateUser: 创造者
 * @UpdateDate: 2019/11/5 18:19
 * @UpdateRemark: 更新说明
 * @Version: 0.2
 */
public class Studio  extends LitePalSupport implements Serializable {

    private String name;
    private String age;
    private String sex;

    public Studio(String name, String age, String sex) {
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }


    @Override
    public String toString() {
        return "Studio{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", sex='" + sex + '\'' +
                '}';
    }
}
