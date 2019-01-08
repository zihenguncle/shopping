package jx.com.shoppingtrolley_zihenguncle.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jx.com.shoppingtrolley_zihenguncle.R;
import jx.com.shoppingtrolley_zihenguncle.activity.MyFootPrint;
import jx.com.shoppingtrolley_zihenguncle.base.BaseFragment;

public class MineFragment extends BaseFragment {

    @BindView(R.id.mine_personage_text)
    TextView personage;
    @BindView(R.id.mine_mycircle_text)
    TextView mycircle;
    @BindView(R.id.mine_myfootprint_text)
    TextView myfootprint;
    @BindView(R.id.mine_mywallet_text)
    TextView mywallet;
    @BindView(R.id.mine_myaddress_text)
    TextView myaddress;
    @Override
    protected void initData(View view) {
        //绑定
        ButterKnife.bind(this,view);
    }

    @OnClick({R.id.mine_myfootprint_text})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.mine_myfootprint_text:
                startActivity(new Intent(getActivity(),MyFootPrint.class));
                break;
                default:
                    break;
        }
    }

    @Override
    protected int getViewById() {
        return R.layout.fragment_mine;
    }

}
