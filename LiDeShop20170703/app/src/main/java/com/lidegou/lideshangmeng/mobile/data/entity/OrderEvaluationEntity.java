package com.lidegou.lideshangmeng.mobile.data.entity;

import java.util.List;

/**
 * Created by Y on 2017/1/25.
 */

public class OrderEvaluationEntity {
    private int totalPage;
    private List<Data> data;

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    public static class Data {
        private String rec_id;
        private String order_id;
        private String goods_id;
        private String goods_name;
        private String add_time;
        private String goods_thumb;
        private String goods_product_tag;
        private String ru_id;

        public String getRec_id() {
            return rec_id;
        }

        public void setRec_id(String rec_id) {
            this.rec_id = rec_id;
        }

        public String getOrder_id() {
            return order_id;
        }

        public void setOrder_id(String order_id) {
            this.order_id = order_id;
        }

        public String getGoods_id() {
            return goods_id;
        }

        public void setGoods_id(String goods_id) {
            this.goods_id = goods_id;
        }

        public String getGoods_name() {
            return goods_name;
        }

        public void setGoods_name(String goods_name) {
            this.goods_name = goods_name;
        }

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }

        public String getGoods_thumb() {
            return goods_thumb;
        }

        public void setGoods_thumb(String goods_thumb) {
            this.goods_thumb = goods_thumb;
        }

        public String getGoods_product_tag() {
            return goods_product_tag;
        }

        public void setGoods_product_tag(String goods_product_tag) {
            this.goods_product_tag = goods_product_tag;
        }

        public String getRu_id() {
            return ru_id;
        }

        public void setRu_id(String ru_id) {
            this.ru_id = ru_id;
        }
    }
}
