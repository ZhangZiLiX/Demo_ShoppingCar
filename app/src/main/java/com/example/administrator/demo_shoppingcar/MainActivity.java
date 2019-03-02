package com.example.administrator.demo_shoppingcar;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.bwie.myutilsclass.MyUtils;
import com.example.administrator.demo_shoppingcar.fragment.ByCarFragment;
import com.example.administrator.demo_shoppingcar.fragment.CircleFragment;
import com.example.administrator.demo_shoppingcar.fragment.DillFragment;
import com.example.administrator.demo_shoppingcar.fragment.HomeFragment;
import com.example.administrator.demo_shoppingcar.fragment.MeFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.viewpager_fragment)
    ViewPager viewpagerFragment;
    @BindView(R.id.btn_home)
    Button btnHome;
    @BindView(R.id.btn_circel)
    Button btnCircel;
    @BindView(R.id.btn_bycar)
    Button btnBycar;
    @BindView(R.id.btn_detail)
    Button btnDetail;
    @BindView(R.id.btn_me)
    Button btnMe;
    @BindView(R.id.ll_buttom)
    LinearLayout llButtom;
    private Unbinder unbinder;
    private List<Fragment> fragmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化  绑定
        unbinder = ButterKnife.bind(this);
        //得到userid  和 sessionid
        getUserIdAndSessionId();
        //创建List  和 Fragment
        initListAndFragment();
        //设置ViewPage
        setVP();
    }



    //设置ViewPage
    private void setVP() {
        viewpagerFragment.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragmentList.get(position);
            }

            @Override
            public int getCount() {
                return fragmentList.size();
            }
        });

    }

    //得到userid  和 sessionid
    private void getUserIdAndSessionId() {
        int urserId = (int) MyUtils.getData(this, "urserId", 0);
        String sessionId = (String) MyUtils.getData(this, "sessionId", "");
    }

    //创建List  和 Fragment
    private void initListAndFragment() {
        fragmentList = new ArrayList<>();
        HomeFragment homeFragment = new HomeFragment();
        CircleFragment circleFragment = new CircleFragment();
        ByCarFragment byCarFragment = new ByCarFragment();
        DillFragment dillFragment = new DillFragment();
        MeFragment meFragment = new MeFragment();
        //将Fragment加入List
        fragmentList.add(homeFragment);
        fragmentList.add(circleFragment);
        fragmentList.add(byCarFragment);
        fragmentList.add(dillFragment);
        fragmentList.add(meFragment);
    }

    @OnClick({R.id.btn_home, R.id.btn_circel, R.id.btn_bycar, R.id.btn_detail, R.id.btn_me, R.id.ll_buttom})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_home:
                viewpagerFragment.setCurrentItem(0);
                break;
            case R.id.btn_circel:
                viewpagerFragment.setCurrentItem(1);
                break;
            case R.id.btn_bycar:
                viewpagerFragment.setCurrentItem(2);
                break;
            case R.id.btn_detail:
                viewpagerFragment.setCurrentItem(3);
                break;
            case R.id.btn_me:
                viewpagerFragment.setCurrentItem(4);
                break;
            case R.id.ll_buttom:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //解绑
        unbinder.unbind();
    }
}
