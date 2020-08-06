package com.hanchao.slidetab.mall;

import android.content.Context;
import android.media.Image;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.hanchao.slidetab.R;

public class CustomTabView extends FrameLayout {

    TextView tvSpecName;
    ImageView ivGoods;
    Context mContext;

    SpecListBean.DataBean itemBean;

    public SpecListBean.DataBean getItemBean() {
        return itemBean;
    }

    public void setBean(SpecListBean.DataBean bean) {
        this.itemBean=bean;
        if (bean!=null){
            if (!TextUtils.isEmpty(bean.item)){
                tvSpecName.setText(bean.item);
            }
            if (!TextUtils.isEmpty(bean.spec_img)){
                Glide.with(mContext).load(bean.spec_img).into(ivGoods);
            }
        }

    }

    boolean isShowImage;





    public void setShowImage(boolean showImage) {
        isShowImage = showImage;
    }

    public CustomTabView(@NonNull Context context) {
        super(context);
        initView(context);
    }

    public CustomTabView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public CustomTabView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        this.mContext=context;
        View view = LayoutInflater.from(context).inflate(R.layout.custom_tab_lay, this);
        tvSpecName=view.findViewById(R.id.item_tab_name);
        ivGoods=view.findViewById(R.id.item_iv);
    }
}
