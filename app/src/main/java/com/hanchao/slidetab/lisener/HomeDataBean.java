package com.hanchao.slidetab.lisener;

import java.util.List;

public class HomeDataBean {
    String name;
    List<ItemBean> list;
    public static class ItemBean{
        String name; //名字
        String type;//优惠

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ItemBean> getList() {
        return list;
    }

    public void setList(List<ItemBean> list) {
        this.list = list;
    }
}
