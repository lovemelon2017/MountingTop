package com.hanchao.slidetab.mall;

import java.util.List;
import java.util.Map;

public class MallListBean {

    public int id;
    public String title;
    public String subtitle;
    public String pic;
    public List<SpecListBean> ios_spec; //规格
    public Map<String, StokeBean> spec_mate;

    public int comment_count;
    public int u_rank;


}
