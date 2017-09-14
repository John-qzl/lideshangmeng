package com.lidegou.lideshangmeng.mobile.data.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/12/6.
 */

public class ShopsStore implements Serializable {


    /**
     * shop_id : 409
     * user_id : 320
     * shop_name : 利德依安商盟
     * shop_logo : /mobile/themes/default/img/no_image.png
     * gaze_number : 1
     * gaze_status : 0
     */
    private String shop_id;
    private String user_id;
    private String shop_name;
    private String km;
    private String shop_logo;
    private String gaze_number;
    private String gaze_status;
    private String longitude;
    private String latitude;
    private int totalPage;

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public String getShop_id() {
        return shop_id;
    }

    public void setShop_id(String shop_id) {
        this.shop_id = shop_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    public String getShop_logo() {
        return shop_logo;
    }

    public void setShop_logo(String shop_logo) {
        this.shop_logo = shop_logo;
    }

    public String getGaze_number() {
        return gaze_number;
    }

    public void setGaze_number(String gaze_number) {
        this.gaze_number = gaze_number;
    }

    public String getGaze_status() {
        return gaze_status;
    }

    public void setGaze_status(String gaze_status) {
        this.gaze_status = gaze_status;
    }

    public String getKm() {
        return km;
    }

    public void setKm(String km) {
        this.km = km;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public static class ShopsStoreClassify {

        /**
         * id : 1580
         * cat_alias_name : 餐饮美食
         */

        private String id;
        private String cat_alias_name;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCat_alias_name() {
            return cat_alias_name;
        }

        public void setCat_alias_name(String cat_alias_name) {
            this.cat_alias_name = cat_alias_name;
        }
    }

    public class Tabs {

        /**
         * type : 1
         * name : 综合
         */

        private String type;
        private String name;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class Attention {


        /**
         * store_list : [{"collect_number":"8","goods":"","rec_id":"1497","del":"/app/index.php?r=user/index/delstore&rec_id=1497","shop_id":"395","store_name":"法国英优优~甲油膜","shop_logo":"","count_store":"8","add_time":"2017-01-02","kf_type":"0","kf_ww":"","kf_qq":"","ru_id":"395","brand_thumb":"/app/themes/default/img/no_image.png","url":"/app/index.php?r=store/index/shop_info&id=395","merch_cmt":{"cmt":{"commentRank":{"zconments":{"score":5,"stars":5,"badReview":0,"middlReview":0,"goodReview":"高","allReview":100,"allmen":0,"badmen":0,"middlemen":0,"goodmen":0,"left":90}},"commentServer":{"zconments":{"score":5,"stars":5,"badReview":0,"middlReview":0,"goodReview":"高","allReview":100,"allmen":0,"badmen":0,"middlemen":0,"goodmen":0,"left":90}},"commentDelivery":{"zconments":{"score":5,"stars":5,"badReview":0,"middlReview":0,"goodReview":"高","allReview":100,"allmen":0,"badmen":0,"middlemen":0,"goodmen":0,"left":90}},"all_zconments":{"score":"5.00","allReview":100,"position":-3}}},"commentrank":5,"commentServer":5,"commentdelivery":5,"rankgoodReview":"高","ServergoodReview":"高","deliverygoodReview":"高","hot_goods":[],"new_goods":[]}]
         * show : 1
         * totalPage : 1
         */

        private int show;
        private int totalPage;
        private List<StoreListBean> store_list;

        public int getShow() {
            return show;
        }

        public void setShow(int show) {
            this.show = show;
        }

        public int getTotalPage() {
            return totalPage;
        }

        public void setTotalPage(int totalPage) {
            this.totalPage = totalPage;
        }

        public List<StoreListBean> getStore_list() {
            return store_list;
        }

        public void setStore_list(List<StoreListBean> store_list) {
            this.store_list = store_list;
        }

        public static class StoreListBean {
            /**
             * collect_number : 8
             * goods :
             * rec_id : 1497
             * del : /app/index.php?r=user/index/delstore&rec_id=1497
             * shop_id : 395
             * store_name : 法国英优优~甲油膜
             * shop_logo :
             * count_store : 8
             * add_time : 2017-01-02
             * kf_type : 0
             * kf_ww :
             * kf_qq :
             * ru_id : 395
             * brand_thumb : /app/themes/default/img/no_image.png
             * url : /app/index.php?r=store/index/shop_info&id=395
             * merch_cmt : {"cmt":{"commentRank":{"zconments":{"score":5,"stars":5,"badReview":0,"middlReview":0,"goodReview":"高","allReview":100,"allmen":0,"badmen":0,"middlemen":0,"goodmen":0,"left":90}},"commentServer":{"zconments":{"score":5,"stars":5,"badReview":0,"middlReview":0,"goodReview":"高","allReview":100,"allmen":0,"badmen":0,"middlemen":0,"goodmen":0,"left":90}},"commentDelivery":{"zconments":{"score":5,"stars":5,"badReview":0,"middlReview":0,"goodReview":"高","allReview":100,"allmen":0,"badmen":0,"middlemen":0,"goodmen":0,"left":90}},"all_zconments":{"score":"5.00","allReview":100,"position":-3}}}
             * commentrank : 5
             * commentServer : 5
             * commentdelivery : 5
             * rankgoodReview : 高
             * ServergoodReview : 高
             * deliverygoodReview : 高
             * hot_goods : []
             * new_goods : []
             */

            private String collect_number;
            private String goods;
            private String rec_id;
            private String del;
            private String shop_id;
            private String store_name;
            private String shop_logo;
            private String count_store;
            private String add_time;
            private String kf_type;
            private String kf_ww;
            private String kf_qq;
            private String ru_id;
            private String brand_thumb;
            private String url;
            private MerchCmtBean merch_cmt;
            private int commentrank;
            private int commentServer;
            private int commentdelivery;
            private String rankgoodReview;
            private String ServergoodReview;
            private String deliverygoodReview;
            private List<?> hot_goods;
            private List<?> new_goods;
            private String longitude;
            private String latitude;

            public String getCollect_number() {
                return collect_number;
            }

            public void setCollect_number(String collect_number) {
                this.collect_number = collect_number;
            }

            public String getGoods() {
                return goods;
            }

            public void setGoods(String goods) {
                this.goods = goods;
            }

            public String getRec_id() {
                return rec_id;
            }

            public void setRec_id(String rec_id) {
                this.rec_id = rec_id;
            }

            public String getDel() {
                return del;
            }

            public void setDel(String del) {
                this.del = del;
            }

            public String getShop_id() {
                return shop_id;
            }

            public void setShop_id(String shop_id) {
                this.shop_id = shop_id;
            }

            public String getStore_name() {
                return store_name;
            }

            public void setStore_name(String store_name) {
                this.store_name = store_name;
            }

            public String getShop_logo() {
                return shop_logo;
            }

            public void setShop_logo(String shop_logo) {
                this.shop_logo = shop_logo;
            }

            public String getCount_store() {
                return count_store;
            }

            public void setCount_store(String count_store) {
                this.count_store = count_store;
            }

            public String getAdd_time() {
                return add_time;
            }

            public void setAdd_time(String add_time) {
                this.add_time = add_time;
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

            public String getRu_id() {
                return ru_id;
            }

            public void setRu_id(String ru_id) {
                this.ru_id = ru_id;
            }

            public String getBrand_thumb() {
                return brand_thumb;
            }

            public void setBrand_thumb(String brand_thumb) {
                this.brand_thumb = brand_thumb;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public MerchCmtBean getMerch_cmt() {
                return merch_cmt;
            }

            public void setMerch_cmt(MerchCmtBean merch_cmt) {
                this.merch_cmt = merch_cmt;
            }

            public int getCommentrank() {
                return commentrank;
            }

            public void setCommentrank(int commentrank) {
                this.commentrank = commentrank;
            }

            public int getCommentServer() {
                return commentServer;
            }

            public void setCommentServer(int commentServer) {
                this.commentServer = commentServer;
            }

            public int getCommentdelivery() {
                return commentdelivery;
            }

            public void setCommentdelivery(int commentdelivery) {
                this.commentdelivery = commentdelivery;
            }

            public String getRankgoodReview() {
                return rankgoodReview;
            }

            public void setRankgoodReview(String rankgoodReview) {
                this.rankgoodReview = rankgoodReview;
            }

            public String getServergoodReview() {
                return ServergoodReview;
            }

            public void setServergoodReview(String ServergoodReview) {
                this.ServergoodReview = ServergoodReview;
            }

            public String getDeliverygoodReview() {
                return deliverygoodReview;
            }

            public void setDeliverygoodReview(String deliverygoodReview) {
                this.deliverygoodReview = deliverygoodReview;
            }

            public List<?> getHot_goods() {
                return hot_goods;
            }

            public void setHot_goods(List<?> hot_goods) {
                this.hot_goods = hot_goods;
            }

            public List<?> getNew_goods() {
                return new_goods;
            }

            public void setNew_goods(List<?> new_goods) {
                this.new_goods = new_goods;
            }

            public String getLongitude() {
                return longitude;
            }

            public void setLongitude(String longitude) {
                this.longitude = longitude;
            }

            public String getLatitude() {
                return latitude;
            }

            public void setLatitude(String latitude) {
                this.latitude = latitude;
            }

            public class MerchCmtBean {
                /**
                 * cmt : {"commentRank":{"zconments":{"score":5,"stars":5,"badReview":0,"middlReview":0,"goodReview":"高","allReview":100,"allmen":0,"badmen":0,"middlemen":0,"goodmen":0,"left":90}},"commentServer":{"zconments":{"score":5,"stars":5,"badReview":0,"middlReview":0,"goodReview":"高","allReview":100,"allmen":0,"badmen":0,"middlemen":0,"goodmen":0,"left":90}},"commentDelivery":{"zconments":{"score":5,"stars":5,"badReview":0,"middlReview":0,"goodReview":"高","allReview":100,"allmen":0,"badmen":0,"middlemen":0,"goodmen":0,"left":90}},"all_zconments":{"score":"5.00","allReview":100,"position":-3}}
                 */

                private CmtBean cmt;

                public CmtBean getCmt() {
                    return cmt;
                }

                public void setCmt(CmtBean cmt) {
                    this.cmt = cmt;
                }

                public class CmtBean {
                    /**
                     * commentRank : {"zconments":{"score":5,"stars":5,"badReview":0,"middlReview":0,"goodReview":"高","allReview":100,"allmen":0,"badmen":0,"middlemen":0,"goodmen":0,"left":90}}
                     * commentServer : {"zconments":{"score":5,"stars":5,"badReview":0,"middlReview":0,"goodReview":"高","allReview":100,"allmen":0,"badmen":0,"middlemen":0,"goodmen":0,"left":90}}
                     * commentDelivery : {"zconments":{"score":5,"stars":5,"badReview":0,"middlReview":0,"goodReview":"高","allReview":100,"allmen":0,"badmen":0,"middlemen":0,"goodmen":0,"left":90}}
                     * all_zconments : {"score":"5.00","allReview":100,"position":-3}
                     */

                    private CommentRankBean commentRank;
                    private CommentServerBean commentServer;
                    private CommentDeliveryBean commentDelivery;
                    private AllZconmentsBean all_zconments;

                    public CommentRankBean getCommentRank() {
                        return commentRank;
                    }

                    public void setCommentRank(CommentRankBean commentRank) {
                        this.commentRank = commentRank;
                    }

                    public CommentServerBean getCommentServer() {
                        return commentServer;
                    }

                    public void setCommentServer(CommentServerBean commentServer) {
                        this.commentServer = commentServer;
                    }

                    public CommentDeliveryBean getCommentDelivery() {
                        return commentDelivery;
                    }

                    public void setCommentDelivery(CommentDeliveryBean commentDelivery) {
                        this.commentDelivery = commentDelivery;
                    }

                    public AllZconmentsBean getAll_zconments() {
                        return all_zconments;
                    }

                    public void setAll_zconments(AllZconmentsBean all_zconments) {
                        this.all_zconments = all_zconments;
                    }

                    public class CommentRankBean {
                        /**
                         * zconments : {"score":5,"stars":5,"badReview":0,"middlReview":0,"goodReview":"高","allReview":100,"allmen":0,"badmen":0,"middlemen":0,"goodmen":0,"left":90}
                         */

                        private ZconmentsBean zconments;

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
                             * goodReview : 高
                             * allReview : 100
                             * allmen : 0
                             * badmen : 0
                             * middlemen : 0
                             * goodmen : 0
                             * left : 90
                             */

                            private int score;
                            private int stars;
                            private int badReview;
                            private int middlReview;
                            private String goodReview;
                            private int allReview;
                            private int allmen;
                            private int badmen;
                            private int middlemen;
                            private int goodmen;
                            private int left;

                            public int getScore() {
                                return score;
                            }

                            public void setScore(int score) {
                                this.score = score;
                            }

                            public int getStars() {
                                return stars;
                            }

                            public void setStars(int stars) {
                                this.stars = stars;
                            }

                            public int getBadReview() {
                                return badReview;
                            }

                            public void setBadReview(int badReview) {
                                this.badReview = badReview;
                            }

                            public int getMiddlReview() {
                                return middlReview;
                            }

                            public void setMiddlReview(int middlReview) {
                                this.middlReview = middlReview;
                            }

                            public String getGoodReview() {
                                return goodReview;
                            }

                            public void setGoodReview(String goodReview) {
                                this.goodReview = goodReview;
                            }

                            public int getAllReview() {
                                return allReview;
                            }

                            public void setAllReview(int allReview) {
                                this.allReview = allReview;
                            }

                            public int getAllmen() {
                                return allmen;
                            }

                            public void setAllmen(int allmen) {
                                this.allmen = allmen;
                            }

                            public int getBadmen() {
                                return badmen;
                            }

                            public void setBadmen(int badmen) {
                                this.badmen = badmen;
                            }

                            public int getMiddlemen() {
                                return middlemen;
                            }

                            public void setMiddlemen(int middlemen) {
                                this.middlemen = middlemen;
                            }

                            public int getGoodmen() {
                                return goodmen;
                            }

                            public void setGoodmen(int goodmen) {
                                this.goodmen = goodmen;
                            }

                            public int getLeft() {
                                return left;
                            }

                            public void setLeft(int left) {
                                this.left = left;
                            }
                        }
                    }

                    public class CommentServerBean {
                        /**
                         * zconments : {"score":5,"stars":5,"badReview":0,"middlReview":0,"goodReview":"高","allReview":100,"allmen":0,"badmen":0,"middlemen":0,"goodmen":0,"left":90}
                         */

                        private ZconmentsBeanX zconments;

                        public ZconmentsBeanX getZconments() {
                            return zconments;
                        }

                        public void setZconments(ZconmentsBeanX zconments) {
                            this.zconments = zconments;
                        }

                        public class ZconmentsBeanX {
                            /**
                             * score : 5
                             * stars : 5
                             * badReview : 0
                             * middlReview : 0
                             * goodReview : 高
                             * allReview : 100
                             * allmen : 0
                             * badmen : 0
                             * middlemen : 0
                             * goodmen : 0
                             * left : 90
                             */

                            private int score;
                            private int stars;
                            private int badReview;
                            private int middlReview;
                            private String goodReview;
                            private int allReview;
                            private int allmen;
                            private int badmen;
                            private int middlemen;
                            private int goodmen;
                            private int left;

                            public int getScore() {
                                return score;
                            }

                            public void setScore(int score) {
                                this.score = score;
                            }

                            public int getStars() {
                                return stars;
                            }

                            public void setStars(int stars) {
                                this.stars = stars;
                            }

                            public int getBadReview() {
                                return badReview;
                            }

                            public void setBadReview(int badReview) {
                                this.badReview = badReview;
                            }

                            public int getMiddlReview() {
                                return middlReview;
                            }

                            public void setMiddlReview(int middlReview) {
                                this.middlReview = middlReview;
                            }

                            public String getGoodReview() {
                                return goodReview;
                            }

                            public void setGoodReview(String goodReview) {
                                this.goodReview = goodReview;
                            }

                            public int getAllReview() {
                                return allReview;
                            }

                            public void setAllReview(int allReview) {
                                this.allReview = allReview;
                            }

                            public int getAllmen() {
                                return allmen;
                            }

                            public void setAllmen(int allmen) {
                                this.allmen = allmen;
                            }

                            public int getBadmen() {
                                return badmen;
                            }

                            public void setBadmen(int badmen) {
                                this.badmen = badmen;
                            }

                            public int getMiddlemen() {
                                return middlemen;
                            }

                            public void setMiddlemen(int middlemen) {
                                this.middlemen = middlemen;
                            }

                            public int getGoodmen() {
                                return goodmen;
                            }

                            public void setGoodmen(int goodmen) {
                                this.goodmen = goodmen;
                            }

                            public int getLeft() {
                                return left;
                            }

                            public void setLeft(int left) {
                                this.left = left;
                            }
                        }
                    }

                    public class CommentDeliveryBean {
                        /**
                         * zconments : {"score":5,"stars":5,"badReview":0,"middlReview":0,"goodReview":"高","allReview":100,"allmen":0,"badmen":0,"middlemen":0,"goodmen":0,"left":90}
                         */

                        private ZconmentsBeanXX zconments;

                        public ZconmentsBeanXX getZconments() {
                            return zconments;
                        }

                        public void setZconments(ZconmentsBeanXX zconments) {
                            this.zconments = zconments;
                        }

                        public class ZconmentsBeanXX {
                            /**
                             * score : 5
                             * stars : 5
                             * badReview : 0
                             * middlReview : 0
                             * goodReview : 高
                             * allReview : 100
                             * allmen : 0
                             * badmen : 0
                             * middlemen : 0
                             * goodmen : 0
                             * left : 90
                             */

                            private int score;
                            private int stars;
                            private int badReview;
                            private int middlReview;
                            private String goodReview;
                            private int allReview;
                            private int allmen;
                            private int badmen;
                            private int middlemen;
                            private int goodmen;
                            private int left;

                            public int getScore() {
                                return score;
                            }

                            public void setScore(int score) {
                                this.score = score;
                            }

                            public int getStars() {
                                return stars;
                            }

                            public void setStars(int stars) {
                                this.stars = stars;
                            }

                            public int getBadReview() {
                                return badReview;
                            }

                            public void setBadReview(int badReview) {
                                this.badReview = badReview;
                            }

                            public int getMiddlReview() {
                                return middlReview;
                            }

                            public void setMiddlReview(int middlReview) {
                                this.middlReview = middlReview;
                            }

                            public String getGoodReview() {
                                return goodReview;
                            }

                            public void setGoodReview(String goodReview) {
                                this.goodReview = goodReview;
                            }

                            public int getAllReview() {
                                return allReview;
                            }

                            public void setAllReview(int allReview) {
                                this.allReview = allReview;
                            }

                            public int getAllmen() {
                                return allmen;
                            }

                            public void setAllmen(int allmen) {
                                this.allmen = allmen;
                            }

                            public int getBadmen() {
                                return badmen;
                            }

                            public void setBadmen(int badmen) {
                                this.badmen = badmen;
                            }

                            public int getMiddlemen() {
                                return middlemen;
                            }

                            public void setMiddlemen(int middlemen) {
                                this.middlemen = middlemen;
                            }

                            public int getGoodmen() {
                                return goodmen;
                            }

                            public void setGoodmen(int goodmen) {
                                this.goodmen = goodmen;
                            }

                            public int getLeft() {
                                return left;
                            }

                            public void setLeft(int left) {
                                this.left = left;
                            }
                        }
                    }

                    public class AllZconmentsBean {
                        /**
                         * score : 5.00
                         * allReview : 100
                         * position : -3
                         */

                        private String score;
                        private int allReview;
                        private int position;

                        public String getScore() {
                            return score;
                        }

                        public void setScore(String score) {
                            this.score = score;
                        }

                        public int getAllReview() {
                            return allReview;
                        }

                        public void setAllReview(int allReview) {
                            this.allReview = allReview;
                        }

                        public int getPosition() {
                            return position;
                        }

                        public void setPosition(int position) {
                            this.position = position;
                        }
                    }
                }
            }
        }
    }

    public static class ShopsStoreDetails {

        /**
         * count_goods : 0
         * count_goods_new : 0
         * count_goods_promote : 0
         * count_bonus : 0
         * bonus_all : []
         * shop_id : 154
         * ru_id : 350
         * shop_logo : http://www.lidegou.com/data/store_street/street_thumb/1478140991253903367.jpg
         * count_gaze : 3
         * gaze_status :
         * goods_list : []
         * shop_name : 路易保罗皮具商行
         * shop_desc : 路易保罗皮具商行
         * shop_start :
         * shop_address : 无锡皮革城16-1N16,17
         * shop_flash : http://www.lidegou.com/data/store_street/street_thumb/1478140991253903367.jpg
         * shop_wangwang :
         * shop_qq :
         * shop_tel : 15152203027
         * shop_category : []
         * img_arr : ["http://www.lidegou.com/seller_imgs/seller_logo/zhanshi_thumb1350.jpg","http://www.lidegou.com/seller_imgs/seller_logo/zhanshi_thumb2350.jpg"]
         * code : http://qr.liantu.com/api.php?bg=f3f3f3&fg=000&el=l&w=800&m=30&text=http://www.lidegou.com/app/index.php?r=store/index/shop_info%26 id=
         * km : 0m
         * busines_scope : 皮具，皮鞋
         * street_desc :
         */

        private String count_goods;
        private String count_goods_new;
        private String count_goods_promote;
        private String count_bonus;
        private String shop_id;
        private String ru_id;
        private String shop_logo;
        private String count_gaze;
        private String gaze_status;
        private String shop_name;
        private String shop_desc;
        private String shop_start;
        private String shop_address;
        private String shop_flash;
        private String shop_wangwang;
        private String shop_qq;
        private String shop_tel;
        private String code;
        private String km;
        private String busines_scope;
        private String street_desc;
        private List<?> bonus_all;
        private List<?> goods_list;
        private List<?> shop_category;
        private List<String> img_arr;

        public String getCount_goods() {
            return count_goods;
        }

        public void setCount_goods(String count_goods) {
            this.count_goods = count_goods;
        }

        public String getCount_goods_new() {
            return count_goods_new;
        }

        public void setCount_goods_new(String count_goods_new) {
            this.count_goods_new = count_goods_new;
        }

        public String getCount_goods_promote() {
            return count_goods_promote;
        }

        public void setCount_goods_promote(String count_goods_promote) {
            this.count_goods_promote = count_goods_promote;
        }

        public String getCount_bonus() {
            return count_bonus;
        }

        public void setCount_bonus(String count_bonus) {
            this.count_bonus = count_bonus;
        }

        public String getShop_id() {
            return shop_id;
        }

        public void setShop_id(String shop_id) {
            this.shop_id = shop_id;
        }

        public String getRu_id() {
            return ru_id;
        }

        public void setRu_id(String ru_id) {
            this.ru_id = ru_id;
        }

        public String getShop_logo() {
            return shop_logo;
        }

        public void setShop_logo(String shop_logo) {
            this.shop_logo = shop_logo;
        }

        public String getCount_gaze() {
            return count_gaze;
        }

        public void setCount_gaze(String count_gaze) {
            this.count_gaze = count_gaze;
        }

        public String getGaze_status() {
            return gaze_status;
        }

        public void setGaze_status(String gaze_status) {
            this.gaze_status = gaze_status;
        }

        public String getShop_name() {
            return shop_name;
        }

        public void setShop_name(String shop_name) {
            this.shop_name = shop_name;
        }

        public String getShop_desc() {
            return shop_desc;
        }

        public void setShop_desc(String shop_desc) {
            this.shop_desc = shop_desc;
        }

        public String getShop_start() {
            return shop_start;
        }

        public void setShop_start(String shop_start) {
            this.shop_start = shop_start;
        }

        public String getShop_address() {
            return shop_address;
        }

        public void setShop_address(String shop_address) {
            this.shop_address = shop_address;
        }

        public String getShop_flash() {
            return shop_flash;
        }

        public void setShop_flash(String shop_flash) {
            this.shop_flash = shop_flash;
        }

        public String getShop_wangwang() {
            return shop_wangwang;
        }

        public void setShop_wangwang(String shop_wangwang) {
            this.shop_wangwang = shop_wangwang;
        }

        public String getShop_qq() {
            return shop_qq;
        }

        public void setShop_qq(String shop_qq) {
            this.shop_qq = shop_qq;
        }

        public String getShop_tel() {
            return shop_tel;
        }

        public void setShop_tel(String shop_tel) {
            this.shop_tel = shop_tel;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getKm() {
            return km;
        }

        public void setKm(String km) {
            this.km = km;
        }

        public String getBusines_scope() {
            return busines_scope;
        }

        public void setBusines_scope(String busines_scope) {
            this.busines_scope = busines_scope;
        }

        public String getStreet_desc() {
            return street_desc;
        }

        public void setStreet_desc(String street_desc) {
            this.street_desc = street_desc;
        }

        public List<?> getBonus_all() {
            return bonus_all;
        }

        public void setBonus_all(List<?> bonus_all) {
            this.bonus_all = bonus_all;
        }

        public List<?> getGoods_list() {
            return goods_list;
        }

        public void setGoods_list(List<?> goods_list) {
            this.goods_list = goods_list;
        }

        public List<?> getShop_category() {
            return shop_category;
        }

        public void setShop_category(List<?> shop_category) {
            this.shop_category = shop_category;
        }

        public List<String> getImg_arr() {
            return img_arr;
        }

        public void setImg_arr(List<String> img_arr) {
            this.img_arr = img_arr;
        }
    }
}
