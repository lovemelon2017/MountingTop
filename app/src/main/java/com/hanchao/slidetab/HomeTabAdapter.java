package com.hanchao.slidetab;

import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

public class HomeTabAdapter extends BaseQuickAdapter<CustomTabBean, BaseViewHolder> {
    public HomeTabAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, CustomTabBean item) {

        View view = helper.getView(R.id.item_iv);
        TextView tvName = helper.getView(R.id.item_title);
        tvName.setText(item.name);
        tvName.setSelected(item.isSelected);
        if (item.isSelected){
            view.setVisibility(View.VISIBLE);
        }else {
            view.setVisibility(View.GONE);
        }
    }
}
