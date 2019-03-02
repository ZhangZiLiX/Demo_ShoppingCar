package com.example.administrator.demo_shoppingcar.loginpage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bwie.myutilsclass.MyUtils;
import com.example.administrator.demo_shoppingcar.MainActivity;
import com.example.administrator.demo_shoppingcar.R;
import com.example.administrator.demo_shoppingcar.loginpage.bean.LoginBean;
import com.example.administrator.demo_shoppingcar.presenter.Presenter;
import com.example.administrator.demo_shoppingcar.utils.API;
import com.example.administrator.demo_shoppingcar.view.IView;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class LoginActivity extends AppCompatActivity implements IView {

    @BindView(R.id.txt_username)
    EditText txtUsername;
    @BindView(R.id.txt_userpassword)
    EditText txtUserpassword;
    @BindView(R.id.ll_login)
    LinearLayout llLogin;
    @BindView(R.id.btn_login)
    Button btnLogin;
    private Unbinder unbinder;
    private Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //绑定
        unbinder = ButterKnife.bind(this);
        //初始化Presenter
        initPresenter();


    }

    //初始化Presenter
    private void initPresenter() {
        presenter = new Presenter();
        presenter.attach(this);

    }

    @OnClick(R.id.btn_login)
    public void onViewClicked() {
        //点击登录  首先要获取用户输入内容
        String username = txtUsername.getText().toString().trim();
        String password = txtUserpassword.getText().toString();
        Map<String,String> map = new HashMap<>();

        //判空
        if(username.equals("")||password.equals("")){
            MyUtils.toastShow(this,"用户账号或密码不能为空,请填写完成后登录!");
        }else{
            //请求接口
            presenter.doPostDataP(API.APILoginURL,username,password);
        }


    }

    @Override
    public void onSuccessIV(Object o) {
       if(o instanceof LoginBean){
           LoginBean loginBean = (LoginBean) o;
           String message = loginBean.getMessage();
           MyUtils.toastShow(this,message);
           //存储userid  和 sessionid
           int userId = loginBean.getResult().getUserId();
           String sessionId = loginBean.getResult().getSessionId();
           MyUtils.putData(this,"urserId",userId);
           MyUtils.putData(this,"sessionId",sessionId);
           Intent intent = new Intent(LoginActivity.this, MainActivity.class);
           startActivity(intent);

       }
    }

    @Override
    public void onFailed(String message) {
       MyUtils.toastShow(this,message);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();//解绑
        if(presenter!=null){
            presenter.dattach();
        }
    }


}
