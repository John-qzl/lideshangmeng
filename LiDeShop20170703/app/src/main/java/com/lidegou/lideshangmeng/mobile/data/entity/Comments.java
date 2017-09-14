package com.lidegou.lideshangmeng.mobile.data.entity;

import java.util.List;

/**
 * 商品评论
 */

public class Comments {


    /**
     * data : [{"id":"14","email":" 123@126.com","username":"15560751512","content":"您老人家里","rank":"3","add_time":"2017-01-17 10:01:25","goods":[{"goods_id":"835","goods_name":"测试产品","goods_attr":"","goods_img":"http://www.lidegou.com/app/themes/default/img/no_image.png"}],"thumb":["http://www.lidegou.com/data/cmt_img/201701/1484618485006571389.jpg"]},{"id":"11","email":" 123@126.com","username":"15560751512","content":"啊实打实大","rank":"3","add_time":"2017-01-13 15:13:03","goods":[{"goods_id":"835","goods_name":"测试产品","goods_attr":"","goods_img":"http://www.lidegou.com/app/themes/default/img/no_image.png"}],"thumb":["http://www.lidegou.com/data/cmt_img/201701/1484291583442136501.jpg"]},{"id":"10","email":" 123@126.com","username":"15560751512","content":"舒服舒服","rank":"2","add_time":"2017-01-13 15:12:30","goods":[{"goods_id":"835","goods_name":"测试产品","goods_attr":"","goods_img":"http://www.lidegou.com/app/themes/default/img/no_image.png"}],"thumb":""},{"id":"9","email":" 123@126.com","username":"15560751512","content":"爱国士大夫撒旦","rank":"3","add_time":"2017-01-13 15:00:29","goods":[{"goods_id":"835","goods_name":"测试产品","goods_attr":"","goods_img":"http://www.lidegou.com/app/themes/default/img/no_image.png"}],"thumb":["http://www.lidegou.com/data/cmt_img/201701/1484290829583028820.jpg"]},{"id":"8","email":" 123@126.com","username":"15560751512","content":"阿达撒大师大师","rank":"3","add_time":"2017-01-13 15:00:16","goods":[{"goods_id":"835","goods_name":"测试产品","goods_attr":"","goods_img":"http://www.lidegou.com/app/themes/default/img/no_image.png"}],"thumb":["http://www.lidegou.com/data/cmt_img/201701/1484290816545513833.jpg"]}]
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
         * id : 14
         * email :  123@126.com
         * username : 15560751512
         * content : 您老人家里
         * rank : 3
         * add_time : 2017-01-17 10:01:25
         * goods : [{"goods_id":"835","goods_name":"测试产品","goods_attr":"","goods_img":"http://www.lidegou.com/app/themes/default/img/no_image.png"}]
         * thumb : ["http://www.lidegou.com/data/cmt_img/201701/1484618485006571389.jpg"]
         */

        private String id;
        private String email;
        private String username;
        private String content;
        private String rank;
        private String add_time;
        private List<GoodsBean> goods;
        private List<String> thumb;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getRank() {
            return rank;
        }

        public void setRank(String rank) {
            this.rank = rank;
        }

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }

        public List<GoodsBean> getGoods() {
            return goods;
        }

        public void setGoods(List<GoodsBean> goods) {
            this.goods = goods;
        }

        public List<String> getThumb() {
            return thumb;
        }

        public void setThumb(List<String> thumb) {
            this.thumb = thumb;
        }

        public static class GoodsBean {
            /**
             * goods_id : 835
             * goods_name : 测试产品
             * goods_attr :
             * goods_img : http://www.lidegou.com/app/themes/default/img/no_image.png
             */

            private String goods_id;
            private String goods_name;
            private String goods_attr;
            private String goods_img;

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

            public String getGoods_attr() {
                return goods_attr;
            }

            public void setGoods_attr(String goods_attr) {
                this.goods_attr = goods_attr;
            }

            public String getGoods_img() {
                return goods_img;
            }

            public void setGoods_img(String goods_img) {
                this.goods_img = goods_img;
            }
        }
    }
}
