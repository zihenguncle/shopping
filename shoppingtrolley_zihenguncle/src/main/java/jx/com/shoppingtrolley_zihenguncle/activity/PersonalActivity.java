package jx.com.shoppingtrolley_zihenguncle.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.nfc.Tag;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jx.com.shoppingtrolley_zihenguncle.R;
import jx.com.shoppingtrolley_zihenguncle.base.BaseActivity;
import jx.com.shoppingtrolley_zihenguncle.bean.AddCarBean;
import jx.com.shoppingtrolley_zihenguncle.bean.EventBusBean;
import jx.com.shoppingtrolley_zihenguncle.bean.UserBean;
import jx.com.shoppingtrolley_zihenguncle.persenter.IPersenterImpl;
import jx.com.shoppingtrolley_zihenguncle.tag.EventTag;
import jx.com.shoppingtrolley_zihenguncle.tool.VerifyUtils;
import jx.com.shoppingtrolley_zihenguncle.url.Apis;
import jx.com.shoppingtrolley_zihenguncle.view.IView;

/**
 * @author 郭淄恒
 * 修改姓名
 */
public class PersonalActivity extends BaseActivity implements IView {

    @BindView(R.id.text_personal_name)
    TextView name;
    IPersenterImpl iPersenter;
    private String nickName;

    @Override
    protected void initData() {
        ButterKnife.bind(this);
        iPersenter = new IPersenterImpl(this);
        Intent intent = getIntent();
        nickName = intent.getStringExtra("nickName");

        iPersenter.startRequestget(Apis.SELECT_MY,UserBean.class);


    }


    @OnClick({R.id.text_personal_name})
    public void seton(View view){
        switch (view.getId()){
            case R.id.text_personal_name:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                View alertView = View.inflate(PersonalActivity.this,R.layout.builder_item,null);
                //输入框内的值
                final EditText edit_name = alertView.findViewById(R.id.builder_edit_name);

                edit_name.setText(nickName);
                TextView textView=new TextView(this);
                textView.setText("修改昵称");
                textView.setPadding(10, 10, 10, 10);
                textView.setTextSize(18);
                textView.setGravity(Gravity.CENTER);
                textView.setTextColor(Color.RED);
                builder.setCustomTitle(textView);
                builder.setView(alertView);
                builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        name.setText(edit_name.getText().toString());
                        Map<String,String> map = new HashMap<>();
                        map.put("nickName",edit_name.getText().toString());
                        iPersenter.startRequestPut(Apis.UPDATE_NAME,map,AddCarBean.class);
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.show();
                break;
                default:
                    break;
        }
    }
    @Override
    protected int getViewById() {
        return R.layout.activity_personal;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        iPersenter.detach();
    }

    @Override
    public void success(Object data) {
        if(data instanceof AddCarBean){
            if(((AddCarBean) data).getStatus().equals("0000")){
                VerifyUtils.getInstance().toast(((AddCarBean) data).getMessage());
            }else {
                VerifyUtils.getInstance().toast("ccccc");
            }
        }
        if(data instanceof UserBean){
            String nickName = ((UserBean) data).getResult().getNickName();

        }
    }

    @Override
    public void failed(String error) {
        VerifyUtils.getInstance().toast(error);
    }
}
