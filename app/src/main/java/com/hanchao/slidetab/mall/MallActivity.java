package com.hanchao.slidetab.mall;

import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.GsonUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.hanchao.slidetab.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MallActivity extends AppCompatActivity {

    SpecListAdapter mAdapter;
    RecyclerView mRv;
    TextView tvGoodsName;
    TextView tvGoodsKuCun;
    TextView tvGoodsSelectSpec;
    TextView tvLast;


    String specSelect[]; //选择过程中展示
    String specLastSelect[];//最终选择类型展示
    String keyValueList[];//选择id 集合

    Map<String, StokeBean> spec_mate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mall);
        EventBus.getDefault().register(this);
        initView();

        getDataInfo();

    }

    private void initView() {
        tvGoodsName = findViewById(R.id.goods_title_tv);
        tvGoodsKuCun = findViewById(R.id.goods_kucun_tv);
        tvGoodsSelectSpec = findViewById(R.id.goods_select_tv);
        tvLast = findViewById(R.id.select_last);
        mRv = findViewById(R.id.rv_spec);
        mRv.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new SpecListAdapter(R.layout.item_parent_spec);
        mRv.setAdapter(mAdapter);
    }

    private void getDataInfo() {
        Map<String, String> map = new HashMap<>();
        map.put("user_id", "828b12aa0ba85ac9a905a79230dc5185");
        map.put("goods_id", "477");
        HttpUtil.getApiClass(GoodsInfoInterface.class).postData(map).enqueue(new Callback<MallBean>() {
            @Override
            public void onResponse(Call<MallBean> call, Response<MallBean> response) {
                MallBean beanResponse = response.body();
                if (beanResponse != null) {
                    MallListBean bean = beanResponse.getData();
                    setInfo(bean);
                }
            }

            @Override
            public void onFailure(Call<MallBean> call, Throwable t) {
                Log.e("han", t.toString());
            }
        });
    }

    private void setInfo(MallListBean bean) {

        if (bean != null) {

            if (!TextUtils.isEmpty(bean.title)) {
                tvGoodsName.setText(bean.title);
            }
            List<SpecListBean> ios_spec = bean.ios_spec;//规格数据
            spec_mate = bean.spec_mate;
            mAdapter.setNewData(ios_spec);
            specSelect = new String[ios_spec.size()];
            specLastSelect = new String[ios_spec.size()];
            keyValueList = new String[ios_spec.size()];
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < ios_spec.size(); i++) {
                SpecListBean specListBean = ios_spec.get(i);
                specSelect[i] = specListBean.name;
                sb.append(specListBean.name + " ");
            }
            //默认选择数据设置
            String specString = sb.toString();
            tvGoodsSelectSpec.setText(specString);

        }
    }


    boolean isAllHave = true;

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessage(SpecEvent event) {
        int position = event.getPosition();
        String keyValue = event.getSpecKeyValue();
        String specName = event.getSpecName();
        String specSonName = event.getSpecSonName();//选择内容

        if (TextUtils.isEmpty(keyValue)) {
            //取消选中
            keyValueList[position] = "";
            changeSpecHint(position, specName, specSonName, false);
        } else {
            //选中

            keyValueList[position] = keyValue;
            changeSpecHint(position, "", specSonName, true);
        }

        //计算库存
        isAllHave = true;
        for (int i = 0; i < keyValueList.length; i++) {
            if (TextUtils.isEmpty(keyValueList[i])) {
                isAllHave = false;
                break;
            }
        }

        if (isAllHave) {
            tvLast.setText("已选择");
            tvLast.setTextColor(Color.parseColor("#ff0000"));
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < specLastSelect.length; i++) {
                sb.append(specLastSelect[i] + "*");
            }
            String s2 = sb.toString();
            tvGoodsSelectSpec.setText(s2.substring(0, s2.length() - 1));

            caculateKuCun();


        } else {
            tvGoodsKuCun.setText("");
            tvLast.setText("请选择");
            tvLast.setTextColor(Color.parseColor("#333333"));
        }

    }

    /**
     * 计算库存
     */
    private void caculateKuCun() {
        StringBuffer sb=new StringBuffer();
        for (int i = 0; i < keyValueList.length; i++) {
            sb.append(keyValueList[i]+";");
        }
        String toString = sb.toString();
        String key = toString.substring(0, toString.length() - 1);
        Log.e("han","计算key="+key);
        StokeBean stokeBean = spec_mate.get(key);

        String stock = stokeBean.stock;//库存
        if (!TextUtils.isEmpty(stock)){
            tvGoodsKuCun.setText(stock);
            ToastUtils.showShort("当前库存:"+stock);
        }

    }

    private void changeSpecHint(int position, String name, String specSonName, boolean isChecked) {
        if (isChecked) {
            specSelect[position] = "";
            specLastSelect[position] = specSonName;
        } else {
            specSelect[position] = name;
            specLastSelect[position] = "";
        }

        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < specSelect.length; i++) {
            stringBuffer.append(specSelect[i] + " ");
        }
        String s = stringBuffer.toString();
        tvGoodsSelectSpec.setText(s);

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
