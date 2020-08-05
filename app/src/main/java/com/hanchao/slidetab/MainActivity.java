package com.hanchao.slidetab;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.android.material.appbar.AppBarLayout;
import com.hanchao.slidetab.lisener.AppBarStateChangeListener;
import com.hanchao.slidetab.lisener.HomeDataBean;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    String titles[] = {"生活缴费", "视频会员", "试听会员", "生活阅读", "英雄联盟"};

    RecyclerView mRv;
    HomeItemAdapter mAdapter;

    RecyclerView mTabRv;
    HomeTabAdapter mTabAdapter;
    AppBarLayout barLayout;
    FrameLayout flContainer;//tab容器

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        barLayout = findViewById(R.id.appbar);
        flContainer = findViewById(R.id.fl_container);
        initRv();

        barLayout.addOnOffsetChangedListener(new AppBarStateChangeListener() {
            @Override
            public void onStateChanged(AppBarLayout appBarLayout, State state) {
                if (state == State.EXPANDED) {
                    //展开状态
                    Log.e("han", "展开了");
                    changeTopTabWidth(false);
                } else if (state == State.COLLAPSED) {
                    //折叠状态
                    Log.e("han", "折叠了");
                    changeTopTabWidth(true);
                } else {
                    //中间状态
                    Log.e("han", "中间状态");
                }
            }
        });

        mRv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                Log.e("han", "rv滑动==" + newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                Log.e("han", "rv滑动==" + dy);
            }
        });

    }

    private void initRv() {
        mRv = findViewById(R.id.rv);
        mRv.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new HomeItemAdapter(R.layout.item_home_rv);
        mRv.setAdapter(mAdapter);

        mTabRv = findViewById(R.id.tab_rv);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(RecyclerView.HORIZONTAL);
        mTabRv.setLayoutManager(manager);
        mTabAdapter = new HomeTabAdapter(R.layout.item_tab_lay);
        mTabRv.setAdapter(mTabAdapter);

        //模拟tab数据
        List<CustomTabBean> tabData = getTabData();
        mTabAdapter.setNewData(tabData);

        mAdapter.setNewData(getHomeList());

        /**
         * tab点击事件
         */
        mTabAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, final int position) {
                List<CustomTabBean> data = mTabAdapter.getData();
                for (int i = 0; i < data.size(); i++) {
                    data.get(i).isSelected = false;
                }
                data.get(position).isSelected = true;
                mTabAdapter.notifyDataSetChanged();

                removeView(position);

                mTabRv.post(new Runnable() {
                    @Override
                    public void run() {
                        if (position == 1) {
                            mTabRv.scrollToPosition(0);
                        } else if (position == titles.length - 2) {
                            mTabRv.scrollToPosition(titles.length - 1);
                        } else {
                            mTabRv.scrollToPosition(position);
                        }

                    }
                });

            }
        });

    }

    /**
     * 移动位置
     *
     * @param position
     */
    private void removeView(final int position) {
        if (position == 0) {
            barLayout.setExpanded(true);

        } else {
            barLayout.setExpanded(false);

        }
        mRv.post(new Runnable() {
            @Override
            public void run() {
                mRv.scrollToPosition(position);
            }
        });
    }

    private List<CustomTabBean> getTabData() {
        List<CustomTabBean> list = new ArrayList<>();
        for (int i = 0; i < titles.length; i++) {
            CustomTabBean bean = new CustomTabBean();
            if (i == 0) {
                bean.isSelected = true;
            }
            bean.name = titles[i];
            list.add(bean);
        }

        return list;
    }

    private void changeTopTabWidth(boolean isFull) {

        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) flContainer.getLayoutParams();
        if (!isFull) {
            layoutParams.setMargins(dip2px(15),0, dip2px(15), 0);
            flContainer.setLayoutParams(layoutParams);
            flContainer.setBackgroundResource(R.drawable.shape_white_10);
            resetRvTab();
        } else {
            layoutParams.setMargins(0, 0, 0, 0);
            flContainer.setLayoutParams(layoutParams);
            flContainer.setBackgroundResource(R.drawable.shape_white);
        }

    }

    private void resetRvTab() {
        mTabRv.post(new Runnable() {
            @Override
            public void run() {
                mTabRv.scrollToPosition(0);
            }
        });
        List<CustomTabBean> data = mTabAdapter.getData();
        for (int i = 0; i < data.size(); i++) {
            data.get(i).isSelected = false;
        }
        data.get(0).isSelected = true;
        mTabAdapter.notifyDataSetChanged();

    }

    public int dip2px(float dipValue) {
        float density = getResources().getDisplayMetrics().density;
        return (int) (dipValue * density + 0.5f);
    }

    public List<HomeDataBean> getHomeList() {
        List<HomeDataBean> dataBeans = new ArrayList<>();

        for (int i = 0; i < titles.length; i++) {
            HomeDataBean homeDataBean = new HomeDataBean();
            homeDataBean.setName(titles[i]);
            dataBeans.add(homeDataBean);

        }
        return dataBeans;

    }

}
