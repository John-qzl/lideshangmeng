package com.lidegou.lideshangmeng.mobile.data.entity;

import java.util.List;

/**
 * Created by Administrator on 2016/12/28.
 */

public class Search {

    public static class HotKeyWord{

        private List<String> keyword;

        public List<String> getKeyword() {
            return keyword;
        }

        public void setKeyword(List<String> keyword) {
            this.keyword = keyword;
        }
    }

}
