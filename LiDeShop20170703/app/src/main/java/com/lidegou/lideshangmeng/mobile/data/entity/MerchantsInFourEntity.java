package com.lidegou.lideshangmeng.mobile.data.entity;

import java.util.List;

/**
 * Created by Y on 2017/2/6.
 */

public class MerchantsInFourEntity {
    private String cats_son;
    private List<CatsData> cats;
    private List<Cats_Son> category_info;
    private ShopInfo shop_info;

    public List<CatsData> getCats() {
        return cats;
    }

    public void setCats(List<CatsData> cats) {
        this.cats = cats;
    }

    public String getCats_son() {
        return cats_son;
    }

    public void setCats_son(String cats_son) {
        this.cats_son = cats_son;
    }

    public List<Cats_Son> getCategory_info() {
        return category_info;
    }

    public void setCategory_info(List<Cats_Son> category_info) {
        this.category_info = category_info;
    }

    public ShopInfo getShop_info() {
        return shop_info;
    }

    public void setShop_info(ShopInfo shop_info) {
        this.shop_info = shop_info;
    }

    public static class CatsData {
        private String cat_id;
        private String cat_name;
        private String parent_id;

        public String getCat_id() {
            return cat_id;
        }

        public void setCat_id(String cat_id) {
            this.cat_id = cat_id;
        }

        public String getCat_name() {
            return cat_name;
        }

        public void setCat_name(String cat_name) {
            this.cat_name = cat_name;
        }

        public String getParent_id() {
            return parent_id;
        }

        public void setParent_id(String parent_id) {
            this.parent_id = parent_id;
        }
    }

    public static class Cats_Son {
        private String cat_id;
        private String cat_name;
        private String parent_id;
        private String parent_name;
        private boolean isSelect = false;

        public String getCat_id() {
            return cat_id;
        }

        public void setCat_id(String cat_id) {
            this.cat_id = cat_id;
        }

        public String getCat_name() {
            return cat_name;
        }

        public void setCat_name(String cat_name) {
            this.cat_name = cat_name;
        }

        public String getParent_id() {
            return parent_id;
        }

        public void setParent_id(String parent_id) {
            this.parent_id = parent_id;
        }

        public String getParent_name() {
            return parent_name;
        }

        public void setParent_name(String parent_name) {
            this.parent_name = parent_name;
        }

        public boolean isSelect() {
            return isSelect;
        }

        public void setSelect(boolean select) {
            isSelect = select;
        }
    }

    public static class ShopInfo {
        private String shop_class_keyWords;
        private String rz_shopName;
        private String shop_categoryMain;

        public String getShop_class_keyWords() {
            return shop_class_keyWords;
        }

        public void setShop_class_keyWords(String shop_class_keyWords) {
            this.shop_class_keyWords = shop_class_keyWords;
        }

        public String getRz_shopName() {
            return rz_shopName;
        }

        public void setRz_shopName(String rz_shopName) {
            this.rz_shopName = rz_shopName;
        }

        public String getShop_categoryMain() {
            return shop_categoryMain;
        }

        public void setShop_categoryMain(String shop_categoryMain) {
            this.shop_categoryMain = shop_categoryMain;
        }
    }
}
