package com.hanchao.slidetab.mall;

import android.text.TextUtils;
import android.view.View;

import androidx.annotation.NonNull;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.android.flexbox.FlexboxLayout;
import com.hanchao.slidetab.R;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SpecListAdapter extends BaseQuickAdapter<SpecListBean, BaseViewHolder> {

    Map<Integer,List<CustomTabView>> map=new HashMap<>();

    public SpecListAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, SpecListBean item) {

        int adapterPosition = helper.getAdapterPosition();
        List<CustomTabView> list=new ArrayList<>();
        map.put(adapterPosition,list);

        if (!TextUtils.isEmpty(item.name)){
            helper.setText(R.id.spec_type,item.name);
        }

        List<SpecListBean.DataBean> data = item.data;//规格子列表


        if (data!=null){

            View.OnClickListener onClickListener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int position = helper.getAdapterPosition();
                    List<CustomTabView> tabs = map.get(position);


                    int tag = (int) v.getTag();
                    CustomTabView view = tabs.get(tag);
                    SpecListBean.DataBean itemBean = view.getItemBean();
                    boolean selected = view.isSelected();
                    if (selected) {
                        view.setSelected(false);
                        EventBus.getDefault().post(new SpecEvent(helper.getAdapterPosition(),item.name,itemBean.item,""));
                    } else {
                        //将其他的都变成未选择
                        for (int i = 0; i < tabs.size(); i++) {
                            tabs.get(i).setSelected(false);
                        }

                        view.setSelected(true);
                        EventBus.getDefault().post(new SpecEvent(helper.getAdapterPosition(),item.name,itemBean.item,item.spec_id+":"+itemBean.item_id));
                    }
                }
            };


            FlexboxLayout  flexboxLayout = helper.getView(R.id.flex_lay);
            List<CustomTabView> tabViewList = map.get(adapterPosition);

            for (int i = 0; i < data.size(); i++) {
                SpecListBean.DataBean dataBean = data.get(i);

                CustomTabView tabView=new CustomTabView(mContext);
                FlexboxLayout.LayoutParams params = new FlexboxLayout.LayoutParams(FlexboxLayout.LayoutParams.WRAP_CONTENT,
                        FlexboxLayout.LayoutParams.WRAP_CONTENT);
                flexboxLayout.addView(tabView, params);
                tabView.setTag(i);
                tabViewList.add(tabView);
                tabView.setBean(dataBean);
                tabView.setOnClickListener(onClickListener);
            }
        }
    }
}
