package com.hanchao.slidetab.mall;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;


public interface GoodsInfoInterface {

    @POST("api/upgrade/good_detail")
    Call<MallBean> postData(@QueryMap Map<String,String>map);
}
