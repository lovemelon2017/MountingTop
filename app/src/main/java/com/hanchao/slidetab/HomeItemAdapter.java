package com.hanchao.slidetab;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hanchao.slidetab.lisener.HomeDataBean;

import java.util.ArrayList;
import java.util.List;

public class HomeItemAdapter extends BaseQuickAdapter<HomeDataBean, BaseViewHolder> {

    HomeItemSonAdapter mAdapter;
    RecyclerView mRv;

    public HomeItemAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeDataBean item) {
        mRv=helper.getView(R.id.item_rv);
        helper.getView(R.id.item_type_tv);
        GridLayoutManager manager=new GridLayoutManager(mContext,3);
        mRv.setLayoutManager(manager);
        mAdapter=new HomeItemSonAdapter(R.layout.item_son_rv_lay);
        mRv.setNestedScrollingEnabled(false);
        mRv.setAdapter(mAdapter);

        String name = item.getName();
        helper.setText(R.id.item_type_tv,name);
        int position = helper.getAdapterPosition();

        List<String>list=new ArrayList<>();

        if (position==0){
            list.add("1");
            list.add("1");
            list.add("1");
        }else if (position==1){
            list.add("1");
            list.add("1");
            list.add("1");
            list.add("1");
            list.add("1");

        }else if (position==2){
            list.add("1");
            list.add("1");
            list.add("1");
            list.add("1");
            list.add("1");
            list.add("1");
        }else {
            list.add("1");
            list.add("1");
            list.add("1");
            list.add("1");

        }

        mAdapter.setNewData(list);
    }
}
