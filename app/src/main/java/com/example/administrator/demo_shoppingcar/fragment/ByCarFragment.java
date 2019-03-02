package com.example.administrator.demo_shoppingcar.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bwie.myutilsclass.MyUtils;
import com.example.administrator.demo_shoppingcar.R;
import com.example.administrator.demo_shoppingcar.fragment.adapter.ByCarAdapter;
import com.example.administrator.demo_shoppingcar.fragment.bean.ByCarBean;
import com.example.administrator.demo_shoppingcar.presenter.Presenter;
import com.example.administrator.demo_shoppingcar.utils.API;
import com.example.administrator.demo_shoppingcar.utils.RxRetrofitUtils;
import com.example.administrator.demo_shoppingcar.view.IView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Author : 张自力
 * Created on time.
 * <p>
 * 购物车
 */

public class ByCarFragment extends Fragment implements IView, View.OnClickListener {

    private Presenter presenter;
    private View view;
    /**
     * 编辑
     */
    private TextView txtBianji;
    private RelativeLayout relayoutTop;
    /**
     * 全选
     */
    private CheckBox cbTotalSelect;
    /**
     * 合计:
     */
    private TextView txtTitleHeji;
    /**
     * 00
     */
    private TextView txtTotalPrice;
    /**
     * 去结算
     */
    private Button btnJiesuan;
    private RelativeLayout rlButtom;
    private XRecyclerView xlvGwfShopper;
    private List<ByCarBean.ResultBean> list;
    private ByCarAdapter byCarAdapter;
    private int urserId;
    private String sessionId;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.bycar_fragment, null);
        //初始化数据
        initView(view);
        getUserIdAndSessionId(view);
        //初始化ListAndAdapter
        initListAndAdapter();
        //初始化Presenter
        initPresenter(view);


        return view;
    }

    //得到userid  和 sessionid
    private void getUserIdAndSessionId(View view) {
        urserId = (int) MyUtils.getData(getActivity(), "urserId", 0);
        sessionId = (String) MyUtils.getData(getActivity(), "sessionId", "");
        RxRetrofitUtils.getInstance().getUserIDAndSessionID(urserId, sessionId);
    }

    //初始化Presenter
    private void initPresenter(View view) {
        presenter = new Presenter();
        presenter.attach(this);
        presenter.doGetDataP(API.APIbyCarURL,urserId,sessionId);
    }

    private void initListAndAdapter() {
        list = new ArrayList<>();
        byCarAdapter = new ByCarAdapter(getActivity(), list);
        xlvGwfShopper.setAdapter(byCarAdapter);
    }


    private void initView(View view) {
        txtBianji = (TextView) view.findViewById(R.id.txt_bianji);
        txtBianji.setOnClickListener(this);
        relayoutTop = (RelativeLayout) view.findViewById(R.id.relayout_top);
        cbTotalSelect = (CheckBox) view.findViewById(R.id.cb_total_select);
        cbTotalSelect.setOnClickListener(this);
        txtTitleHeji = (TextView) view.findViewById(R.id.txt_title_heji);
        txtTotalPrice = (TextView) view.findViewById(R.id.txt_total_price);
        btnJiesuan = (Button) view.findViewById(R.id.btn_jiesuan);
        btnJiesuan.setOnClickListener(this);
        rlButtom = (RelativeLayout) view.findViewById(R.id.rl_buttom);
        xlvGwfShopper = (XRecyclerView) view.findViewById(R.id.xlv_gwf_shopper);

        //添加布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        xlvGwfShopper.setLayoutManager(layoutManager);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.txt_bianji:
                break;
            case R.id.cb_total_select:
                break;
            case R.id.btn_jiesuan:
                break;
        }
    }


    //实现后成功的方法
    @Override
    public void onSuccessIV(Object o) {
        if (o instanceof ByCarBean) {
            ByCarBean byCarBean = (ByCarBean) o;
            List<ByCarBean.ResultBean> result = byCarBean.getResult();
            list.clear();
            list.addAll(result);
            byCarAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onFailed(String message) {
        MyUtils.toastShow(getActivity(), message);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.dattach();
        }
    }



}
