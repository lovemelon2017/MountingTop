package com.hanchao.slidetab.mall;

import java.util.List;

/**
 * 规格
 */
public class SpecListBean {

    /**
     * spec_id : 574
     * name : 颜色
     * data : [{"item_id":3016,"item":"红","spec_id":574,"spec_img":"/upload/pic/3bb7c8accf0c17a414e31055a52ab1b7.jpg","num":""},{"item_id":3017,"item":"黄","spec_id":574,"spec_img":"/upload/pic/3bb7c8accf0c17a414e31055a52ab1b7.jpg","num":""},{"item_id":3018,"item":"蓝","spec_id":574,"spec_img":"/upload/pic/3bb7c8accf0c17a414e31055a52ab1b7.jpg","num":""}]
     */

    public int spec_id;
    public String name;
    public List<DataBean> data;

    public static class DataBean {
        /**
         * item_id : 3016
         * item : 红
         * spec_id : 574
         * spec_img : /upload/pic/3bb7c8accf0c17a414e31055a52ab1b7.jpg
         * num :
         */

        public int item_id;
        public String item;
        public int spec_id;
        public String spec_img;

        public String num;

    }
}
