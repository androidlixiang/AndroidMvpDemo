package com.lixiang.androidmvpdemp.statistic;

import android.support.annotation.Keep;

import java.io.Serializable;

/**
 * 项目 国民健康
 *
 * @Create by yexm
 * @创建日期 2019/1/2 18:01
 * @版本 0.2
 * @类说明: 统计的入口
 */
@Keep
public class StatisticsBean implements Serializable {

    /**
     * page_id : 3-1-1-5
     * return_title : 商城首页-Banner
     * page_desc : {"activity_id":"活动一","goods_id":"商品1,商品2,商品3","orders_no":"订单ID","health_article":"资讯ID或视频ID"}
     * page_type : page
     */

    public StatisticsBean() {

    }

    public StatisticsBean(String event_return_title, String event, String page_type, String page_id, String return_title) {
        this.page_id = page_id;
        this.event = event;
        this.return_title = return_title;
        this.event_return_title = event_return_title;
        this.page_type = page_type;
    }

    public StatisticsBean(String event_return_title, String event, String page_type, String page_id, String return_title, PageDescBean page_desc) {
        this.page_id = page_id;
        this.event = event;
        this.event_return_title = event_return_title;
        this.return_title = return_title;
        this.page_desc = page_desc;
        this.page_type = page_type;
    }

    private String page_id = "";
    private String event = "";
    private String event_return_title = "";
    private String return_title = "";
    private PageDescBean page_desc = new PageDescBean();
    private String page_type = "";


    public String getPage_id() {
        return page_id;
    }

    public void setPage_id(String page_id) {
        this.page_id = page_id;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getEvent_return_title() {
        return event_return_title;
    }

    public void setEvent_return_title(String event_return_title) {
        this.event_return_title = event_return_title;
    }

    public String getReturn_title() {
        return return_title;
    }

    public void setReturn_title(String return_title) {
        this.return_title = return_title;
    }

    public PageDescBean getPage_desc() {
        return page_desc;
    }

    public void setPage_desc(PageDescBean page_desc) {
        this.page_desc = page_desc;
    }

    public String getPage_type() {
        return page_type;
    }

    public void setPage_type(String page_type) {
        this.page_type = page_type;
    }

    @Override
    public String toString() {
        return "{" +
                "page_id='" + page_id + '\'' +
                ", event='" + event + '\'' +
                ", event_return_title='" + event_return_title + '\'' +
                ", return_title='" + return_title + '\'' +
                ", page_desc=" + page_desc +
                ", page_type='" + page_type + '\'' +
                '}';
    }


    public static class PageDescBean {

        public PageDescBean() {

        }
        /**
         * activity_id : 活动一
         * goods_id : 商品1,商品2,商品3
         * orders_no : 订单ID
         * health_article : 资讯ID或视频ID
         */

        private String activity_id = "";
        private String goods_id = "";
        private String exposure_goodsid = "";
        private String exposure_articleid = "";
        private String detail_id = "";
        private String orders_no = "";
        private String couponId = "";
        private String classifyId = "";
        private String classifyName = "";
        private String classfyLevel = "";
        private String key_word = "";
        private String health_article = "";
        private String article_type_id = "";

        public String getActivity_id() {
            return activity_id;
        }

        public void setActivity_id(String activity_id) {
            this.activity_id = activity_id;
        }

        public String getGoods_id() {
            return goods_id;
        }

        public void setGoods_id(String goods_id) {
            this.goods_id = goods_id;
        }

        public String getExposure_goodsid() {
            return exposure_goodsid;
        }

        public void setExposure_goodsid(String exposure_goodsid) {
            this.exposure_goodsid = exposure_goodsid;
        }

        public String getExposure_articleid() {
            return exposure_articleid;
        }

        public void setExposure_articleid(String exposure_articleid) {
            this.exposure_articleid = exposure_articleid;
        }

        public String getDetail_id() {
            return detail_id;
        }

        public void setDetail_id(String detail_id) {
            this.detail_id = detail_id;
        }

        public String getOrders_no() {
            return orders_no;
        }

        public void setOrders_no(String orders_no) {
            this.orders_no = orders_no;
        }

        public String getCouponId() {
            return couponId;
        }

        public void setCouponId(String couponId) {
            this.couponId = couponId;
        }

        public String getClassifyId() {
            return classifyId;
        }

        public void setClassifyId(String classifyId) {
            this.classifyId = classifyId;
        }

        public String getClassifyName() {
            return classifyName;
        }

        public void setClassifyName(String classifyName) {
            this.classifyName = classifyName;
        }

        public String getClassfyLevel() {
            return classfyLevel;
        }

        public void setClassfyLevel(String classfyLevel) {
            this.classfyLevel = classfyLevel;
        }

        public String getKey_word() {
            return key_word;
        }

        public void setKey_word(String key_word) {
            this.key_word = key_word;
        }

        public String getHealth_article() {
            return health_article;
        }

        public void setHealth_article(String health_article) {
            this.health_article = health_article;
        }

        public String getArticle_type_id() {
            return article_type_id;
        }

        public void setArticle_type_id(String article_type_id) {
            this.article_type_id = article_type_id;
        }

        @Override
        public String toString() {
            return "{" +
                    "activity_id='" + activity_id + '\'' +
                    ", goods_id='" + goods_id + '\'' +
                    ", exposure_goodsid='" + exposure_goodsid + '\'' +
                    ", exposure_articleid='" + exposure_articleid + '\'' +
                    ", detail_id='" + detail_id + '\'' +
                    ", orders_no='" + orders_no + '\'' +
                    ", couponId='" + couponId + '\'' +
                    ", classifyId='" + classifyId + '\'' +
                    ", classifyName='" + classifyName + '\'' +
                    ", classfyLevel='" + classfyLevel + '\'' +
                    ", key_word='" + key_word + '\'' +
                    ", health_article='" + health_article + '\'' +
                    ", article_type_id='" + article_type_id + '\'' +
                    '}';
        }
    }
}
