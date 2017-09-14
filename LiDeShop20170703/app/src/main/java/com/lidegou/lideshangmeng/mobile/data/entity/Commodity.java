package com.lidegou.lideshangmeng.mobile.data.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/12/6.
 */

public class Commodity implements Serializable {
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
        private String goods_id;
        private String user_id;
        private String goods_name;
        private String warehouse_price;
        private String warehouse_promote_price;
        private String region_price;
        private String region_promote_price;
        private String model_price;
        private String model_attr;
        private String goods_name_style;
        private String comments_number;
        private String sales_volume;
        private String market_price;
        private String is_new;
        private String is_best;
        private String is_hot;
        private String goods_number;
        private String org_price;
        private String shop_price;
        private String promote_price;
        private String goods_type;
        private String promote_start_date;
        private String promote_end_date;
        private String is_promote;
        private String goods_brief;
        private String goods_thumb;
        private String goods_img;
        private String url;


        public String getGoods_id() {
            return goods_id;
        }

        public void setGoods_id(String goods_id) {
            this.goods_id = goods_id;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getGoods_name() {
            return goods_name;
        }

        public void setGoods_name(String goods_name) {
            this.goods_name = goods_name;
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

        public String getModel_price() {
            return model_price;
        }

        public void setModel_price(String model_price) {
            this.model_price = model_price;
        }

        public String getModel_attr() {
            return model_attr;
        }

        public void setModel_attr(String model_attr) {
            this.model_attr = model_attr;
        }

        public String getGoods_name_style() {
            return goods_name_style;
        }

        public void setGoods_name_style(String goods_name_style) {
            this.goods_name_style = goods_name_style;
        }

        public String getComments_number() {
            return comments_number;
        }

        public void setComments_number(String comments_number) {
            this.comments_number = comments_number;
        }

        public String getSales_volume() {
            return sales_volume;
        }

        public void setSales_volume(String sales_volume) {
            this.sales_volume = sales_volume;
        }

        public String getMarket_price() {
            return market_price;
        }

        public void setMarket_price(String market_price) {
            this.market_price = market_price;
        }

        public String getIs_new() {
            return is_new;
        }

        public void setIs_new(String is_new) {
            this.is_new = is_new;
        }

        public String getIs_best() {
            return is_best;
        }

        public void setIs_best(String is_best) {
            this.is_best = is_best;
        }

        public String getIs_hot() {
            return is_hot;
        }

        public void setIs_hot(String is_hot) {
            this.is_hot = is_hot;
        }

        public String getGoods_number() {
            return goods_number;
        }

        public void setGoods_number(String goods_number) {
            this.goods_number = goods_number;
        }

        public String getOrg_price() {
            return org_price;
        }

        public void setOrg_price(String org_price) {
            this.org_price = org_price;
        }

        public String getShop_price() {
            return shop_price;
        }

        public void setShop_price(String shop_price) {
            this.shop_price = shop_price;
        }

        public String getPromote_price() {
            return promote_price;
        }

        public void setPromote_price(String promote_price) {
            this.promote_price = promote_price;
        }

        public String getGoods_type() {
            return goods_type;
        }

        public void setGoods_type(String goods_type) {
            this.goods_type = goods_type;
        }

        public String getPromote_start_date() {
            return promote_start_date;
        }

        public void setPromote_start_date(String promote_start_date) {
            this.promote_start_date = promote_start_date;
        }

        public String getPromote_end_date() {
            return promote_end_date;
        }

        public void setPromote_end_date(String promote_end_date) {
            this.promote_end_date = promote_end_date;
        }

        public String getIs_promote() {
            return is_promote;
        }

        public void setIs_promote(String is_promote) {
            this.is_promote = is_promote;
        }

        public String getGoods_brief() {
            return goods_brief;
        }

        public void setGoods_brief(String goods_brief) {
            this.goods_brief = goods_brief;
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


        public static class CommodityDetails {


            /**
             * code : 100
             * goods : {"goods_id":"707","cat_id":"1493","user_id":"0","goods_sn":"LDG000707","goods_name":"Apple iPhone 6 Plus (A1524) 16GB 银色 移动联通电信4G手机","goods_name_style":"+","click_count":"10","brand_id":"0","provider_name":"","goods_number":"1000","goods_weight":"0","default_shipping":"0","market_price":"4882.00","shop_price":"4069","promote_price":"","promote_start_date":"0","promote_end_date":"0","warn_number":"1","keywords":"","goods_brief":"","goods_desc":"","goods_thumb":"../images/201610/thumb_img/707_thumb_G_1477039425954.jpg","goods_img":"../images/201610/goods_img/707_G_1477039425537.jpg","original_img":"images/201610/source_img/707_G_1477039425824.jpg","is_real":"1","extension_code":"","is_on_sale":"1","is_alone_sale":"1","is_shipping":"0","integral":8138,"add_time":"2016-10-19 13:05:53","sort_order":"100","is_delete":"0","is_best":"1","is_new":"1","is_hot":"1","is_promote":"0","bonus_type_id":"0","last_update":"1477039425","goods_type":"0","seller_note":"","give_integral":"0","rank_integral":"0","suppliers_id":"0","is_check":"","store_hot":"0","store_new":"0","store_best":"0","group_number":"0","is_xiangou":"0","xiangou_start_date":"0","xiangou_end_date":"0","xiangou_num":"0","review_status":"5","review_content":"","goods_shipai":"","comments_number":"0","sales_volume":"0","comment_num":"0","model_price":"0","model_inventory":"0","model_attr":"0","largest_amount":"0.00","pinyin_keyword":"Apple iPhone 6 Plus (A1524) 16GB yinse yidongliantongdianxin4Gshouji","goods_product_tag":"","warehouse_price":"","warehouse_promote_price":"","region_price":"","region_promote_price":"","measure_unit":"","brand_logo":"","goods_brand":"","bonus_money":0,"comment_rank":5,"rank_price":"4069","zhekou":8.3,"jiesheng":813,"watermark_img":"watermark_new","promote_price_org":0,"gmt_end_time":0,"promote_end_time":0,"attr_number":"1000","marketPrice":"4882.00","shop_price_formated":"4069.00","goods_price":"4069","goodsWeight":"0.000","isHas_attr":0,"rz_shopName":"利得超市","store_url":"/app/index.php?r=store/index/shop_info&id=0","shopinfo":{"shop_name":"利得超市","check_sellername":"0","shopname_audit":"1","shop_logo":"","logo_thumb":"","brand_thumb":"/app/themes/default/img/no_image.png"},"goods_url":"/app/index.php?r=goods&id=707","consumption":[],"conshipping":[],"suppliers_name":"","end_time":"","collect_count":"","is_collect":"","goods_style_name":"Apple iPhone 6 Plus (A1524) 16GB 银色 移动联通电信4G手机"}
             * properties : []
             * shippingFee : {"shipping_id":"16","shipping_code":"sf_express","shipping_name":"顺丰速运","shipping_type":"1","shipping_fee":"","shipping_fee_formated":"0.00","free_money":"0.00","is_shipping":1}
             * goods_collect :
             * goods_region : {"country":1,"province":"26","city":"342","district":"2905"}
             * cart_num : 0
             * comment_all : {"score":5,"stars":5,"badReview":0,"middlReview":0,"goodReview":100,"allReview":100,"allmen":0,"badmen":0,"middlemen":0,"goodmen":0,"left":90}
             * good_comment : []
             * basic_info : {"province":"内蒙古","city":"通辽市","kf_type":"0","kf_ww":"小黄人","kf_qq":"2653927629","shop_name":"利得超市"}
             * region_id : 2
             * area_id : 20
             * quick : 1
             */

            private String code;
            private GoodsBean goods;
            private ShippingFeeBean shippingFee;
            private String goods_collect;
            private GoodsRegionBean goods_region;
            private int cart_num;
            private CommentAllBean comment_all;
            private BasicInfoBean basic_info;
            private String region_id;
            private String area_id;
            private int quick;
            private String url;
            private List<PicturesBean> pictures;
            private List<?> properties;
            private List<?> good_comment;


            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public GoodsBean getGoods() {
                return goods;
            }

            public void setGoods(GoodsBean goods) {
                this.goods = goods;
            }

            public ShippingFeeBean getShippingFee() {
                return shippingFee;
            }

            public void setShippingFee(ShippingFeeBean shippingFee) {
                this.shippingFee = shippingFee;
            }

            public String getGoods_collect() {
                return goods_collect;
            }

            public void setGoods_collect(String goods_collect) {
                this.goods_collect = goods_collect;
            }

            public GoodsRegionBean getGoods_region() {
                return goods_region;
            }

            public void setGoods_region(GoodsRegionBean goods_region) {
                this.goods_region = goods_region;
            }

            public int getCart_num() {
                return cart_num;
            }

            public void setCart_num(int cart_num) {
                this.cart_num = cart_num;
            }

            public CommentAllBean getComment_all() {
                return comment_all;
            }

            public void setComment_all(CommentAllBean comment_all) {
                this.comment_all = comment_all;
            }

            public BasicInfoBean getBasic_info() {
                return basic_info;
            }

            public void setBasic_info(BasicInfoBean basic_info) {
                this.basic_info = basic_info;
            }

            public String getRegion_id() {
                return region_id;
            }

            public void setRegion_id(String region_id) {
                this.region_id = region_id;
            }

            public String getArea_id() {
                return area_id;
            }

            public void setArea_id(String area_id) {
                this.area_id = area_id;
            }

            public int getQuick() {
                return quick;
            }

            public void setQuick(int quick) {
                this.quick = quick;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public List<PicturesBean> getPictures() {
                return pictures;
            }

            public void setPictures(List<PicturesBean> pictures) {
                this.pictures = pictures;
            }

            public List<?> getProperties() {
                return properties;
            }

            public void setProperties(List<?> properties) {
                this.properties = properties;
            }

            public List<?> getGood_comment() {
                return good_comment;
            }

            public void setGood_comment(List<?> good_comment) {
                this.good_comment = good_comment;
            }

            public class GoodsBean {
                /**
                 * goods_id : 707
                 * cat_id : 1493
                 * user_id : 0
                 * goods_sn : LDG000707
                 * goods_name : Apple iPhone 6 Plus (A1524) 16GB 银色 移动联通电信4G手机
                 * goods_name_style : +
                 * click_count : 10
                 * brand_id : 0
                 * provider_name :
                 * goods_number : 1000
                 * goods_weight : 0
                 * default_shipping : 0
                 * market_price : 4882.00
                 * shop_price : 4069
                 * promote_price :
                 * promote_start_date : 0
                 * promote_end_date : 0
                 * warn_number : 1
                 * keywords :
                 * goods_brief :
                 * goods_desc :
                 * goods_thumb : ../images/201610/thumb_img/707_thumb_G_1477039425954.jpg
                 * goods_img : ../images/201610/goods_img/707_G_1477039425537.jpg
                 * original_img : images/201610/source_img/707_G_1477039425824.jpg
                 * is_real : 1
                 * extension_code :
                 * is_on_sale : 1
                 * is_alone_sale : 1
                 * is_shipping : 0
                 * integral : 8138
                 * add_time : 2016-10-19 13:05:53
                 * sort_order : 100
                 * is_delete : 0
                 * is_best : 1
                 * is_new : 1
                 * is_hot : 1
                 * is_promote : 0
                 * bonus_type_id : 0
                 * last_update : 1477039425
                 * goods_type : 0
                 * seller_note :
                 * give_integral : 0
                 * rank_integral : 0
                 * suppliers_id : 0
                 * is_check :
                 * store_hot : 0
                 * store_new : 0
                 * store_best : 0
                 * group_number : 0
                 * is_xiangou : 0
                 * xiangou_start_date : 0
                 * xiangou_end_date : 0
                 * xiangou_num : 0
                 * review_status : 5
                 * review_content :
                 * goods_shipai :
                 * comments_number : 0
                 * sales_volume : 0
                 * comment_num : 0
                 * model_price : 0
                 * model_inventory : 0
                 * model_attr : 0
                 * largest_amount : 0.00
                 * pinyin_keyword : Apple iPhone 6 Plus (A1524) 16GB yinse yidongliantongdianxin4Gshouji
                 * goods_product_tag :
                 * warehouse_price :
                 * warehouse_promote_price :
                 * region_price :
                 * region_promote_price :
                 * measure_unit :
                 * brand_logo :
                 * goods_brand :
                 * bonus_money : 0
                 * comment_rank : 5
                 * rank_price : 4069
                 * zhekou : 8.3
                 * jiesheng : 813
                 * watermark_img : watermark_new
                 * promote_price_org : 0
                 * gmt_end_time : 0
                 * promote_end_time : 0
                 * attr_number : 1000
                 * marketPrice : 4882.00
                 * shop_price_formated : 4069.00
                 * goods_price : 4069
                 * goodsWeight : 0.000
                 * isHas_attr : 0
                 * rz_shopName : 利得超市
                 * store_url : /app/index.php?r=store/index/shop_info&id=0
                 * shopinfo : {"shop_name":"利得超市","check_sellername":"0","shopname_audit":"1","shop_logo":"","logo_thumb":"","brand_thumb":"/app/themes/default/img/no_image.png"}
                 * goods_url : /app/index.php?r=goods&id=707
                 * consumption : []
                 * conshipping : []
                 * suppliers_name :
                 * end_time :
                 * collect_count :
                 * is_collect :
                 * goods_style_name : Apple iPhone 6 Plus (A1524) 16GB 银色 移动联通电信4G手机
                 */

                private String goods_id;
                private String cat_id;
                private String user_id;
                private String goods_sn;
                private String goods_name;
                private String goods_name_style;
                private String click_count;
                private String brand_id;
                private String provider_name;
                private String goods_number;
                private String goods_weight;
                private String default_shipping;
                private String market_price;
                private String shop_price;
                private String promote_price;
                private String promote_start_date;
                private String promote_end_date;
                private String warn_number;
                private String keywords;
                private String goods_brief;
                private String goods_desc;
                private String goods_thumb;
                private String goods_img;
                private String original_img;
                private String is_real;
                private String extension_code;
                private String is_on_sale;
                private String is_alone_sale;
                private String is_shipping;
                private int integral;
                private String add_time;
                private String sort_order;
                private String is_delete;
                private String is_best;
                private String is_new;
                private String is_hot;
                private String is_promote;
                private String bonus_type_id;
                private String last_update;
                private String goods_type;
                private String seller_note;
                private String give_integral;
                private String rank_integral;
                private String suppliers_id;
                private String is_check;
                private String store_hot;
                private String store_new;
                private String store_best;
                private String group_number;
                private String is_xiangou;
                private String xiangou_start_date;
                private String xiangou_end_date;
                private String xiangou_num;
                private String review_status;
                private String review_content;
                private String goods_shipai;
                private String comments_number;
                private String sales_volume;
                private String comment_num;
                private String model_price;
                private String model_inventory;
                private String model_attr;
                private String largest_amount;
                private String pinyin_keyword;
                private String goods_product_tag;
                private String warehouse_price;
                private String warehouse_promote_price;
                private String region_price;
                private String region_promote_price;
                private String measure_unit;
                private String brand_logo;
                private String goods_brand;
                private int bonus_money;
                private int comment_rank;
                private String rank_price;
                private double zhekou;
                private String jiesheng;
                private String watermark_img;
                private int promote_price_org;
                private int gmt_end_time;
                private int promote_end_time;
                private String attr_number;
                private String marketPrice;
                private String shop_price_formated;
                private String goods_price;
                private String goodsWeight;
                private int isHas_attr;
                private String rz_shopName;
                private String store_url;
                private ShopinfoBean shopinfo;
                private String goods_url;
                private String suppliers_name;
                private String end_time;
                private String collect_count;
                private String is_collect;
                private String goods_style_name;
                private List<?> consumption;
                private List<?> conshipping;

                public String getGoods_id() {
                    return goods_id;
                }

                public void setGoods_id(String goods_id) {
                    this.goods_id = goods_id;
                }

                public String getCat_id() {
                    return cat_id;
                }

                public void setCat_id(String cat_id) {
                    this.cat_id = cat_id;
                }

                public String getUser_id() {
                    return user_id;
                }

                public void setUser_id(String user_id) {
                    this.user_id = user_id;
                }

                public String getGoods_sn() {
                    return goods_sn;
                }

                public void setGoods_sn(String goods_sn) {
                    this.goods_sn = goods_sn;
                }

                public String getGoods_name() {
                    return goods_name;
                }

                public void setGoods_name(String goods_name) {
                    this.goods_name = goods_name;
                }

                public String getGoods_name_style() {
                    return goods_name_style;
                }

                public void setGoods_name_style(String goods_name_style) {
                    this.goods_name_style = goods_name_style;
                }

                public String getClick_count() {
                    return click_count;
                }

                public void setClick_count(String click_count) {
                    this.click_count = click_count;
                }

                public String getBrand_id() {
                    return brand_id;
                }

                public void setBrand_id(String brand_id) {
                    this.brand_id = brand_id;
                }

                public String getProvider_name() {
                    return provider_name;
                }

                public void setProvider_name(String provider_name) {
                    this.provider_name = provider_name;
                }

                public String getGoods_number() {
                    return goods_number;
                }

                public void setGoods_number(String goods_number) {
                    this.goods_number = goods_number;
                }

                public String getGoods_weight() {
                    return goods_weight;
                }

                public void setGoods_weight(String goods_weight) {
                    this.goods_weight = goods_weight;
                }

                public String getDefault_shipping() {
                    return default_shipping;
                }

                public void setDefault_shipping(String default_shipping) {
                    this.default_shipping = default_shipping;
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

                public String getPromote_price() {
                    return promote_price;
                }

                public void setPromote_price(String promote_price) {
                    this.promote_price = promote_price;
                }

                public String getPromote_start_date() {
                    return promote_start_date;
                }

                public void setPromote_start_date(String promote_start_date) {
                    this.promote_start_date = promote_start_date;
                }

                public String getPromote_end_date() {
                    return promote_end_date;
                }

                public void setPromote_end_date(String promote_end_date) {
                    this.promote_end_date = promote_end_date;
                }

                public String getWarn_number() {
                    return warn_number;
                }

                public void setWarn_number(String warn_number) {
                    this.warn_number = warn_number;
                }

                public String getKeywords() {
                    return keywords;
                }

                public void setKeywords(String keywords) {
                    this.keywords = keywords;
                }

                public String getGoods_brief() {
                    return goods_brief;
                }

                public void setGoods_brief(String goods_brief) {
                    this.goods_brief = goods_brief;
                }

                public String getGoods_desc() {
                    return goods_desc;
                }

                public void setGoods_desc(String goods_desc) {
                    this.goods_desc = goods_desc;
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

                public String getOriginal_img() {
                    return original_img;
                }

                public void setOriginal_img(String original_img) {
                    this.original_img = original_img;
                }

                public String getIs_real() {
                    return is_real;
                }

                public void setIs_real(String is_real) {
                    this.is_real = is_real;
                }

                public String getExtension_code() {
                    return extension_code;
                }

                public void setExtension_code(String extension_code) {
                    this.extension_code = extension_code;
                }

                public String getIs_on_sale() {
                    return is_on_sale;
                }

                public void setIs_on_sale(String is_on_sale) {
                    this.is_on_sale = is_on_sale;
                }

                public String getIs_alone_sale() {
                    return is_alone_sale;
                }

                public void setIs_alone_sale(String is_alone_sale) {
                    this.is_alone_sale = is_alone_sale;
                }

                public String getIs_shipping() {
                    return is_shipping;
                }

                public void setIs_shipping(String is_shipping) {
                    this.is_shipping = is_shipping;
                }

                public int getIntegral() {
                    return integral;
                }

                public void setIntegral(int integral) {
                    this.integral = integral;
                }

                public String getAdd_time() {
                    return add_time;
                }

                public void setAdd_time(String add_time) {
                    this.add_time = add_time;
                }

                public String getSort_order() {
                    return sort_order;
                }

                public void setSort_order(String sort_order) {
                    this.sort_order = sort_order;
                }

                public String getIs_delete() {
                    return is_delete;
                }

                public void setIs_delete(String is_delete) {
                    this.is_delete = is_delete;
                }

                public String getIs_best() {
                    return is_best;
                }

                public void setIs_best(String is_best) {
                    this.is_best = is_best;
                }

                public String getIs_new() {
                    return is_new;
                }

                public void setIs_new(String is_new) {
                    this.is_new = is_new;
                }

                public String getIs_hot() {
                    return is_hot;
                }

                public void setIs_hot(String is_hot) {
                    this.is_hot = is_hot;
                }

                public String getIs_promote() {
                    return is_promote;
                }

                public void setIs_promote(String is_promote) {
                    this.is_promote = is_promote;
                }

                public String getBonus_type_id() {
                    return bonus_type_id;
                }

                public void setBonus_type_id(String bonus_type_id) {
                    this.bonus_type_id = bonus_type_id;
                }

                public String getLast_update() {
                    return last_update;
                }

                public void setLast_update(String last_update) {
                    this.last_update = last_update;
                }

                public String getGoods_type() {
                    return goods_type;
                }

                public void setGoods_type(String goods_type) {
                    this.goods_type = goods_type;
                }

                public String getSeller_note() {
                    return seller_note;
                }

                public void setSeller_note(String seller_note) {
                    this.seller_note = seller_note;
                }

                public String getGive_integral() {
                    return give_integral;
                }

                public void setGive_integral(String give_integral) {
                    this.give_integral = give_integral;
                }

                public String getRank_integral() {
                    return rank_integral;
                }

                public void setRank_integral(String rank_integral) {
                    this.rank_integral = rank_integral;
                }

                public String getSuppliers_id() {
                    return suppliers_id;
                }

                public void setSuppliers_id(String suppliers_id) {
                    this.suppliers_id = suppliers_id;
                }

                public String getIs_check() {
                    return is_check;
                }

                public void setIs_check(String is_check) {
                    this.is_check = is_check;
                }

                public String getStore_hot() {
                    return store_hot;
                }

                public void setStore_hot(String store_hot) {
                    this.store_hot = store_hot;
                }

                public String getStore_new() {
                    return store_new;
                }

                public void setStore_new(String store_new) {
                    this.store_new = store_new;
                }

                public String getStore_best() {
                    return store_best;
                }

                public void setStore_best(String store_best) {
                    this.store_best = store_best;
                }

                public String getGroup_number() {
                    return group_number;
                }

                public void setGroup_number(String group_number) {
                    this.group_number = group_number;
                }

                public String getIs_xiangou() {
                    return is_xiangou;
                }

                public void setIs_xiangou(String is_xiangou) {
                    this.is_xiangou = is_xiangou;
                }

                public String getXiangou_start_date() {
                    return xiangou_start_date;
                }

                public void setXiangou_start_date(String xiangou_start_date) {
                    this.xiangou_start_date = xiangou_start_date;
                }

                public String getXiangou_end_date() {
                    return xiangou_end_date;
                }

                public void setXiangou_end_date(String xiangou_end_date) {
                    this.xiangou_end_date = xiangou_end_date;
                }

                public String getXiangou_num() {
                    return xiangou_num;
                }

                public void setXiangou_num(String xiangou_num) {
                    this.xiangou_num = xiangou_num;
                }

                public String getReview_status() {
                    return review_status;
                }

                public void setReview_status(String review_status) {
                    this.review_status = review_status;
                }

                public String getReview_content() {
                    return review_content;
                }

                public void setReview_content(String review_content) {
                    this.review_content = review_content;
                }

                public String getGoods_shipai() {
                    return goods_shipai;
                }

                public void setGoods_shipai(String goods_shipai) {
                    this.goods_shipai = goods_shipai;
                }

                public String getComments_number() {
                    return comments_number;
                }

                public void setComments_number(String comments_number) {
                    this.comments_number = comments_number;
                }

                public String getSales_volume() {
                    return sales_volume;
                }

                public void setSales_volume(String sales_volume) {
                    this.sales_volume = sales_volume;
                }

                public String getComment_num() {
                    return comment_num;
                }

                public void setComment_num(String comment_num) {
                    this.comment_num = comment_num;
                }

                public String getModel_price() {
                    return model_price;
                }

                public void setModel_price(String model_price) {
                    this.model_price = model_price;
                }

                public String getModel_inventory() {
                    return model_inventory;
                }

                public void setModel_inventory(String model_inventory) {
                    this.model_inventory = model_inventory;
                }

                public String getModel_attr() {
                    return model_attr;
                }

                public void setModel_attr(String model_attr) {
                    this.model_attr = model_attr;
                }

                public String getLargest_amount() {
                    return largest_amount;
                }

                public void setLargest_amount(String largest_amount) {
                    this.largest_amount = largest_amount;
                }

                public String getPinyin_keyword() {
                    return pinyin_keyword;
                }

                public void setPinyin_keyword(String pinyin_keyword) {
                    this.pinyin_keyword = pinyin_keyword;
                }

                public String getGoods_product_tag() {
                    return goods_product_tag;
                }

                public void setGoods_product_tag(String goods_product_tag) {
                    this.goods_product_tag = goods_product_tag;
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

                public String getMeasure_unit() {
                    return measure_unit;
                }

                public void setMeasure_unit(String measure_unit) {
                    this.measure_unit = measure_unit;
                }

                public String getBrand_logo() {
                    return brand_logo;
                }

                public void setBrand_logo(String brand_logo) {
                    this.brand_logo = brand_logo;
                }

                public String getGoods_brand() {
                    return goods_brand;
                }

                public void setGoods_brand(String goods_brand) {
                    this.goods_brand = goods_brand;
                }

                public int getBonus_money() {
                    return bonus_money;
                }

                public void setBonus_money(int bonus_money) {
                    this.bonus_money = bonus_money;
                }

                public int getComment_rank() {
                    return comment_rank;
                }

                public void setComment_rank(int comment_rank) {
                    this.comment_rank = comment_rank;
                }

                public String getRank_price() {
                    return rank_price;
                }

                public void setRank_price(String rank_price) {
                    this.rank_price = rank_price;
                }

                public double getZhekou() {
                    return zhekou;
                }

                public void setZhekou(double zhekou) {
                    this.zhekou = zhekou;
                }

                public String getJiesheng() {
                    return jiesheng;
                }

                public void setJiesheng(String jiesheng) {
                    this.jiesheng = jiesheng;
                }

                public String getWatermark_img() {
                    return watermark_img;
                }

                public void setWatermark_img(String watermark_img) {
                    this.watermark_img = watermark_img;
                }

                public int getPromote_price_org() {
                    return promote_price_org;
                }

                public void setPromote_price_org(int promote_price_org) {
                    this.promote_price_org = promote_price_org;
                }

                public int getGmt_end_time() {
                    return gmt_end_time;
                }

                public void setGmt_end_time(int gmt_end_time) {
                    this.gmt_end_time = gmt_end_time;
                }

                public int getPromote_end_time() {
                    return promote_end_time;
                }

                public void setPromote_end_time(int promote_end_time) {
                    this.promote_end_time = promote_end_time;
                }

                public String getAttr_number() {
                    return attr_number;
                }

                public void setAttr_number(String attr_number) {
                    this.attr_number = attr_number;
                }

                public String getMarketPrice() {
                    return marketPrice;
                }

                public void setMarketPrice(String marketPrice) {
                    this.marketPrice = marketPrice;
                }

                public String getShop_price_formated() {
                    return shop_price_formated;
                }

                public void setShop_price_formated(String shop_price_formated) {
                    this.shop_price_formated = shop_price_formated;
                }

                public String getGoods_price() {
                    return goods_price;
                }

                public void setGoods_price(String goods_price) {
                    this.goods_price = goods_price;
                }

                public String getGoodsWeight() {
                    return goodsWeight;
                }

                public void setGoodsWeight(String goodsWeight) {
                    this.goodsWeight = goodsWeight;
                }

                public int getIsHas_attr() {
                    return isHas_attr;
                }

                public void setIsHas_attr(int isHas_attr) {
                    this.isHas_attr = isHas_attr;
                }

                public String getRz_shopName() {
                    return rz_shopName;
                }

                public void setRz_shopName(String rz_shopName) {
                    this.rz_shopName = rz_shopName;
                }

                public String getStore_url() {
                    return store_url;
                }

                public void setStore_url(String store_url) {
                    this.store_url = store_url;
                }

                public ShopinfoBean getShopinfo() {
                    return shopinfo;
                }

                public void setShopinfo(ShopinfoBean shopinfo) {
                    this.shopinfo = shopinfo;
                }

                public String getGoods_url() {
                    return goods_url;
                }

                public void setGoods_url(String goods_url) {
                    this.goods_url = goods_url;
                }

                public String getSuppliers_name() {
                    return suppliers_name;
                }

                public void setSuppliers_name(String suppliers_name) {
                    this.suppliers_name = suppliers_name;
                }

                public String getEnd_time() {
                    return end_time;
                }

                public void setEnd_time(String end_time) {
                    this.end_time = end_time;
                }

                public String getCollect_count() {
                    return collect_count;
                }

                public void setCollect_count(String collect_count) {
                    this.collect_count = collect_count;
                }

                public String getIs_collect() {
                    return is_collect;
                }

                public void setIs_collect(String is_collect) {
                    this.is_collect = is_collect;
                }

                public String getGoods_style_name() {
                    return goods_style_name;
                }

                public void setGoods_style_name(String goods_style_name) {
                    this.goods_style_name = goods_style_name;
                }

                public List<?> getConsumption() {
                    return consumption;
                }

                public void setConsumption(List<?> consumption) {
                    this.consumption = consumption;
                }

                public List<?> getConshipping() {
                    return conshipping;
                }

                public void setConshipping(List<?> conshipping) {
                    this.conshipping = conshipping;
                }

                public class ShopinfoBean {
                    /**
                     * shop_name : 利得超市
                     * check_sellername : 0
                     * shopname_audit : 1
                     * shop_logo :
                     * logo_thumb :
                     * brand_thumb : /app/themes/default/img/no_image.png
                     */

                    private String shop_name;
                    private String check_sellername;
                    private String shopname_audit;
                    private String shop_logo;
                    private String logo_thumb;
                    private String brand_thumb;

                    public String getShop_name() {
                        return shop_name;
                    }

                    public void setShop_name(String shop_name) {
                        this.shop_name = shop_name;
                    }

                    public String getCheck_sellername() {
                        return check_sellername;
                    }

                    public void setCheck_sellername(String check_sellername) {
                        this.check_sellername = check_sellername;
                    }

                    public String getShopname_audit() {
                        return shopname_audit;
                    }

                    public void setShopname_audit(String shopname_audit) {
                        this.shopname_audit = shopname_audit;
                    }

                    public String getShop_logo() {
                        return shop_logo;
                    }

                    public void setShop_logo(String shop_logo) {
                        this.shop_logo = shop_logo;
                    }

                    public String getLogo_thumb() {
                        return logo_thumb;
                    }

                    public void setLogo_thumb(String logo_thumb) {
                        this.logo_thumb = logo_thumb;
                    }

                    public String getBrand_thumb() {
                        return brand_thumb;
                    }

                    public void setBrand_thumb(String brand_thumb) {
                        this.brand_thumb = brand_thumb;
                    }
                }
            }

            public class ShippingFeeBean {
                /**
                 * shipping_id : 16
                 * shipping_code : sf_express
                 * shipping_name : 顺丰速运
                 * shipping_type : 1
                 * shipping_fee :
                 * shipping_fee_formated : 0.00
                 * free_money : 0.00
                 * is_shipping : 1
                 */

                private String shipping_id;
                private String shipping_code;
                private String shipping_name;
                private String shipping_type;
                private String shipping_fee;
                private String shipping_fee_formated;
                private String free_money;
                private int is_shipping;

                public String getShipping_id() {
                    return shipping_id;
                }

                public void setShipping_id(String shipping_id) {
                    this.shipping_id = shipping_id;
                }

                public String getShipping_code() {
                    return shipping_code;
                }

                public void setShipping_code(String shipping_code) {
                    this.shipping_code = shipping_code;
                }

                public String getShipping_name() {
                    return shipping_name;
                }

                public void setShipping_name(String shipping_name) {
                    this.shipping_name = shipping_name;
                }

                public String getShipping_type() {
                    return shipping_type;
                }

                public void setShipping_type(String shipping_type) {
                    this.shipping_type = shipping_type;
                }

                public String getShipping_fee() {
                    return shipping_fee;
                }

                public void setShipping_fee(String shipping_fee) {
                    this.shipping_fee = shipping_fee;
                }

                public String getShipping_fee_formated() {
                    return shipping_fee_formated;
                }

                public void setShipping_fee_formated(String shipping_fee_formated) {
                    this.shipping_fee_formated = shipping_fee_formated;
                }

                public String getFree_money() {
                    return free_money;
                }

                public void setFree_money(String free_money) {
                    this.free_money = free_money;
                }

                public int getIs_shipping() {
                    return is_shipping;
                }

                public void setIs_shipping(int is_shipping) {
                    this.is_shipping = is_shipping;
                }
            }

            public class GoodsRegionBean {
                /**
                 * country : 1
                 * province : 26
                 * city : 342
                 * district : 2905
                 */

                private int country;
                private String province;
                private String city;
                private String district;

                public int getCountry() {
                    return country;
                }

                public void setCountry(int country) {
                    this.country = country;
                }

                public String getProvince() {
                    return province;
                }

                public void setProvince(String province) {
                    this.province = province;
                }

                public String getCity() {
                    return city;
                }

                public void setCity(String city) {
                    this.city = city;
                }

                public String getDistrict() {
                    return district;
                }

                public void setDistrict(String district) {
                    this.district = district;
                }
            }

            public class CommentAllBean {
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

            public class BasicInfoBean {
                /**
                 * province : 内蒙古
                 * city : 通辽市
                 * kf_type : 0
                 * kf_ww : 小黄人
                 * kf_qq : 2653927629
                 * shop_name : 利得超市
                 */

                private String province;
                private String city;
                private String kf_type;
                private String kf_ww;
                private String kf_qq;
                private String shop_name;

                public String getProvince() {
                    return province;
                }

                public void setProvince(String province) {
                    this.province = province;
                }

                public String getCity() {
                    return city;
                }

                public void setCity(String city) {
                    this.city = city;
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

                public String getShop_name() {
                    return shop_name;
                }

                public void setShop_name(String shop_name) {
                    this.shop_name = shop_name;
                }
            }

            public class PicturesBean {

                /**
                 * img_id : 1298
                 * img_url : http://www.lidegou.com/images/201610/goods_img/707_P_1477020356940.jpg
                 * thumb_url : http://www.lidegou.com/images/201610/thumb_img/707_thumb_P_1477020356777.jpg
                 * img_desc :
                 */

                private String img_id;
                private String img_url;
                private String thumb_url;
                private String img_desc;

                public String getImg_id() {
                    return img_id;
                }

                public void setImg_id(String img_id) {
                    this.img_id = img_id;
                }

                public String getImg_url() {
                    return img_url;
                }

                public void setImg_url(String img_url) {
                    this.img_url = img_url;
                }

                public String getThumb_url() {
                    return thumb_url;
                }

                public void setThumb_url(String thumb_url) {
                    this.thumb_url = thumb_url;
                }

                public String getImg_desc() {
                    return img_desc;
                }

                public void setImg_desc(String img_desc) {
                    this.img_desc = img_desc;
                }
            }

        }


        public static class CollectionCommodity {


            /**
             * data : [{"goods_number":"1000","rec_id":"117","is_attention":"0","goods_id":"720","goods_name":"OPPO R9 4GB+64GB内存版 玫瑰金 全网通4G手机 双卡双待","market_price":"3297.00","shop_price":"2748.00","promote_price":"","url":"/app/index.php?r=goods&id=720","goods_thumb":"http://www.lidegou.com/images/201610/thumb_img/720_thumb_G_1477018981670.jpg","add_time":"2016-11-03 10:58:59","del":"/app/index.php?r=user/index/delcollection&rec_id=117","zconments":{"score":5,"stars":5,"badReview":0,"middlReview":0,"goodReview":100,"allReview":100,"allmen":0,"badmen":0,"middlemen":0,"goodmen":0,"left":90}}]
             * totalPage : 1
             */

            private int totalPage;
            private List<DataBean> data;

            public int getTotalPage() {
                return totalPage;
            }

            public void setTotalPage(int totalPage) {
                this.totalPage = totalPage;
            }

            public List<DataBean> getData() {
                return data;
            }

            public void setData(List<DataBean> data) {
                this.data = data;
            }

            public static class DataBean {
                /**
                 * goods_number : 1000
                 * rec_id : 117
                 * is_attention : 0
                 * goods_id : 720
                 * goods_name : OPPO R9 4GB+64GB内存版 玫瑰金 全网通4G手机 双卡双待
                 * market_price : 3297.00
                 * shop_price : 2748.00
                 * promote_price :
                 * url : /app/index.php?r=goods&id=720
                 * goods_thumb : http://www.lidegou.com/images/201610/thumb_img/720_thumb_G_1477018981670.jpg
                 * add_time : 2016-11-03 10:58:59
                 * del : /app/index.php?r=user/index/delcollection&rec_id=117
                 * zconments : {"score":5,"stars":5,"badReview":0,"middlReview":0,"goodReview":100,"allReview":100,"allmen":0,"badmen":0,"middlemen":0,"goodmen":0,"left":90}
                 */

                private String goods_number;
                private String rec_id;
                private String is_attention;
                private String goods_id;
                private String goods_name;
                private String market_price;
                private String shop_price;
                private String promote_price;
                private String url;
                private String goods_thumb;
                private String add_time;
                private String del;
                private ZconmentsBean zconments;

                public String getGoods_number() {
                    return goods_number;
                }

                public void setGoods_number(String goods_number) {
                    this.goods_number = goods_number;
                }

                public String getRec_id() {
                    return rec_id;
                }

                public void setRec_id(String rec_id) {
                    this.rec_id = rec_id;
                }

                public String getIs_attention() {
                    return is_attention;
                }

                public void setIs_attention(String is_attention) {
                    this.is_attention = is_attention;
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

                public String getPromote_price() {
                    return promote_price;
                }

                public void setPromote_price(String promote_price) {
                    this.promote_price = promote_price;
                }

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }

                public String getGoods_thumb() {
                    return goods_thumb;
                }

                public void setGoods_thumb(String goods_thumb) {
                    this.goods_thumb = goods_thumb;
                }

                public String getAdd_time() {
                    return add_time;
                }

                public void setAdd_time(String add_time) {
                    this.add_time = add_time;
                }

                public String getDel() {
                    return del;
                }

                public void setDel(String del) {
                    this.del = del;
                }

                public ZconmentsBean getZconments() {
                    return zconments;
                }

                public void setZconments(ZconmentsBean zconments) {
                    this.zconments = zconments;
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

    }
}