package com.lidegou.lideshangmeng.mobile.data.entity;

import java.util.List;

/**
 * Created by Administrator on 2016/12/7.
 */

public class Classify {

    private int id;
    private String name;
    private String url;
    private String cat_img;
    private String haschild;
    private List<CatID> catIDList;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCat_img() {
        return cat_img;
    }

    public void setCat_img(String cat_img) {
        this.cat_img = cat_img;
    }

    public String getHaschild() {
        return haschild;
    }

    public void setHaschild(String haschild) {
        this.haschild = haschild;
    }

    public List<CatID> getCatIDList() {
        return catIDList;
    }

    public void setCatIDList(List<CatID> catIDList) {
        this.catIDList = catIDList;
    }

    public class CatID {
        private int id;
        private String name;
        private String url;
        private String cat_img;
        private String haschild;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getCat_img() {
            return cat_img;
        }

        public void setCat_img(String cat_img) {
            this.cat_img = cat_img;
        }

        public String getHaschild() {
            return haschild;
        }

        public void setHaschild(String haschild) {
            this.haschild = haschild;
        }
    }

    public static class Products {

        /**
         * org_price : 12099.00
         * model_price : 0
         * warehouse_price :
         * warehouse_promote_price :
         * region_price :
         * region_promote_price :
         * watermark_img : watermark_new_small
         * goods_id : 685
         * goods_name : 雷神（ThundeRobot）ST-Pro 15.6英寸游戏本（i7-6700HQ 16G 512G SSD GTX1060 6G win10 RGB背光键盘 IPS）雷神（ThundeRobot）S...
         * name : 雷神（ThundeRobot）ST-Pro 15.6英寸游戏本（i7-6700HQ 16G 512G SSD GTX1060 6G win10 RGB背光键盘 IPS）雷神（ThundeRobot）ST-Pro 15.6英寸游戏本（i7-6
         * goods_brief :
         * sales_volume : 0
         * comments_number : 0
         * is_promote : 0
         * zhekou :
         * jiesheng :
         * goods_style_name : 雷神（ThundeRobot）ST-Pro 15.6英寸游戏本（i7-6700HQ 16G 512G SSD GTX1060 6G win10 RGB背光键盘 IPS）雷神（ThundeRobot）ST-Pro 15.6英寸游戏本（i7-6
         * review_count :
         * market_price : 0.00
         * shop_price : 12099.00
         * type : 0
         * promote_price :
         * goods_thumb : ../images/201610/thumb_img/685_thumb_G_1476353586021.jpg
         * goods_img : ../images/201610/goods_img/685_G_1476353586801.jpg
         * url : /app/index.php?r=goods&id=685
         * prod : 1
         * goods_number : 1000
         * kf_type : 0
         * kf_ww : 小黄人
         * kf_qq : 2653927629
         * rz_shopName : 利得超市
         * user_id : 0
         * store_url : false
         * count : 0
         * zconments : {"score":5,"stars":5,"badReview":0,"middlReview":0,"goodReview":100,"allReview":100,"allmen":0,"badmen":0,"middlemen":0,"goodmen":0,"left":90}
         * spe : []
         */

        private String org_price;
        private String model_price;
        private String warehouse_price;
        private String warehouse_promote_price;
        private String region_price;
        private String region_promote_price;
        private String watermark_img;
        private String goods_id;
        private String goods_name;
        private String name;
        private String goods_brief;
        private String sales_volume;
        private String comments_number;
        private String is_promote;
        private String zhekou;
        private String jiesheng;
        private String goods_style_name;
        private String review_count;
        private String market_price;
        private String shop_price;
        private String type;
        private String promote_price;
        private String goods_thumb;
        private String goods_img;
        private String url;
        private int prod;
        private String goods_number;
        private String kf_type;
        private String kf_ww;
        private String kf_qq;
        private String rz_shopName;
        private String user_id;
        private boolean store_url;
        private int count;
        private List<ZconmentsBean> zconmentsBeanList;
        private List<?> spe;

        public String getOrg_price() {
            return org_price;
        }

        public void setOrg_price(String org_price) {
            this.org_price = org_price;
        }

        public String getModel_price() {
            return model_price;
        }

        public void setModel_price(String model_price) {
            this.model_price = model_price;
        }

        public String getWarehouse_price() {
            return warehouse_price;
        }

        public void setWarehouse_price(String warehouse_price) {
            this.warehouse_price = warehouse_price;
        }

        public String getWarehouse_promote_price() {
            return warehouse_promote_price;
        }

        public void setWarehouse_promote_price(String warehouse_promote_price) {
            this.warehouse_promote_price = warehouse_promote_price;
        }

        public String getRegion_price() {
            return region_price;
        }

        public void setRegion_price(String region_price) {
            this.region_price = region_price;
        }

        public String getRegion_promote_price() {
            return region_promote_price;
        }

        public void setRegion_promote_price(String region_promote_price) {
            this.region_promote_price = region_promote_price;
        }

        public String getWatermark_img() {
            return watermark_img;
        }

        public void setWatermark_img(String watermark_img) {
            this.watermark_img = watermark_img;
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

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getGoods_brief() {
            return goods_brief;
        }

        public void setGoods_brief(String goods_brief) {
            this.goods_brief = goods_brief;
        }

        public String getSales_volume() {
            return sales_volume;
        }

        public void setSales_volume(String sales_volume) {
            this.sales_volume = sales_volume;
        }

        public String getComments_number() {
            return comments_number;
        }

        public void setComments_number(String comments_number) {
            this.comments_number = comments_number;
        }

        public String getIs_promote() {
            return is_promote;
        }

        public void setIs_promote(String is_promote) {
            this.is_promote = is_promote;
        }

        public String getZhekou() {
            return zhekou;
        }

        public void setZhekou(String zhekou) {
            this.zhekou = zhekou;
        }

        public String getJiesheng() {
            return jiesheng;
        }

        public void setJiesheng(String jiesheng) {
            this.jiesheng = jiesheng;
        }

        public String getGoods_style_name() {
            return goods_style_name;
        }

        public void setGoods_style_name(String goods_style_name) {
            this.goods_style_name = goods_style_name;
        }

        public String getReview_count() {
            return review_count;
        }

        public void setReview_count(String review_count) {
            this.review_count = review_count;
        }

        public String getMarket_price() {
            return market_price;
        }

        public void setMarket_price(String market_price) {
            this.market_price = market_price;
        }

        public String getShop_price() {
            return shop_price;
        }

        public void setShop_price(String shop_price) {
            this.shop_price = shop_price;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getPromote_price() {
            return promote_price;
        }

        public void setPromote_price(String promote_price) {
            this.promote_price = promote_price;
        }

        public String getGoods_thumb() {
            return goods_thumb;
        }

        public void setGoods_thumb(String goods_thumb) {
            this.goods_thumb = goods_thumb;
        }

        public String getGoods_img() {
            return goods_img;
        }

        public void setGoods_img(String goods_img) {
            this.goods_img = goods_img;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getProd() {
            return prod;
        }

        public void setProd(int prod) {
            this.prod = prod;
        }

        public String getGoods_number() {
            return goods_number;
        }

        public void setGoods_number(String goods_number) {
            this.goods_number = goods_number;
        }

        public String getKf_type() {
            return kf_type;
        }

        public void setKf_type(String kf_type) {
            this.kf_type = kf_type;
        }

        public String getKf_ww() {
            return kf_ww;
        }

        public void setKf_ww(String kf_ww) {
            this.kf_ww = kf_ww;
        }

        public String getKf_qq() {
            return kf_qq;
        }

        public void setKf_qq(String kf_qq) {
            this.kf_qq = kf_qq;
        }

        public String getRz_shopName() {
            return rz_shopName;
        }

        public void setRz_shopName(String rz_shopName) {
            this.rz_shopName = rz_shopName;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public boolean isStore_url() {
            return store_url;
        }

        public void setStore_url(boolean store_url) {
            this.store_url = store_url;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public List<ZconmentsBean> getZconmentsBeanList() {
            return zconmentsBeanList;
        }

        public void setZconmentsBeanList(List<ZconmentsBean> zconmentsBeanList) {
            this.zconmentsBeanList = zconmentsBeanList;
        }

        public List<?> getSpe() {
            return spe;
        }

        public void setSpe(List<?> spe) {
            this.spe = spe;
        }

        public class ZconmentsBean {
            /**
             * score : 5
             * stars : 5
             * badReview : 0
             * middlReview : 0
             * goodReview : 100
             * allReview : 100
             * allmen : 0
             * badmen : 0
             * middlemen : 0
             * goodmen : 0
             * left : 90
             */

            private double score;
            private double stars;
            private double badReview;
            private double middlReview;
            private double goodReview;
            private double allReview;
            private double allmen;
            private double badmen;
            private double middlemen;
            private double goodmen;
            private double left;

            public double getScore() {
                return score;
            }

            public void setScore(double score) {
                this.score = score;
            }

            public double getStars() {
                return stars;
            }

            public void setStars(double stars) {
                this.stars = stars;
            }

            public double getBadReview() {
                return badReview;
            }

            public void setBadReview(double badReview) {
                this.badReview = badReview;
            }

            public double getMiddlReview() {
                return middlReview;
            }

            public void setMiddlReview(double middlReview) {
                this.middlReview = middlReview;
            }

            public double getGoodReview() {
                return goodReview;
            }

            public void setGoodReview(double goodReview) {
                this.goodReview = goodReview;
            }

            public double getAllReview() {
                return allReview;
            }

            public void setAllReview(double allReview) {
                this.allReview = allReview;
            }

            public double getAllmen() {
                return allmen;
            }

            public void setAllmen(double allmen) {
                this.allmen = allmen;
            }

            public double getBadmen() {
                return badmen;
            }

            public void setBadmen(double badmen) {
                this.badmen = badmen;
            }

            public double getMiddlemen() {
                return middlemen;
            }

            public void setMiddlemen(double middlemen) {
                this.middlemen = middlemen;
            }

            public double getGoodmen() {
                return goodmen;
            }

            public void setGoodmen(double goodmen) {
                this.goodmen = goodmen;
            }

            public double getLeft() {
                return left;
            }

            public void setLeft(double left) {
                this.left = left;
            }
        }
    }

}
