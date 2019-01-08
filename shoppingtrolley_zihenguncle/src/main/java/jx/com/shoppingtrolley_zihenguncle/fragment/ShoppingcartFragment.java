package jx.com.shoppingtrolley_zihenguncle.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import jx.com.shoppingtrolley_zihenguncle.R;
import jx.com.shoppingtrolley_zihenguncle.base.BaseFragment;
import jx.com.shoppingtrolley_zihenguncle.bean.CarBean;
import jx.com.shoppingtrolley_zihenguncle.car_adapter.CarAdapter;
import jx.com.shoppingtrolley_zihenguncle.iteminterval.SpacesItemDecoration;
import jx.com.shoppingtrolley_zihenguncle.persenter.IPersenterImpl;
import jx.com.shoppingtrolley_zihenguncle.url.Apis;
import jx.com.shoppingtrolley_zihenguncle.view.IView;

public class ShoppingcartFragment extends BaseFragment implements IView {

    @BindView(R.id.car_recycle)
    RecyclerView recyclerView;
    @BindView(R.id.car_all_chekbox)
    CheckBox checkBox;
    @BindView(R.id.car_total_price)
    TextView price;
    @BindView(R.id.car_go_to_pay)
    TextView goto_pay;

    IPersenterImpl iPersenter;

    private CarAdapter adapter;

    @Override
    protected void initData(View view) {
        //绑定
        ButterKnife.bind(this,view);
        iPersenter = new IPersenterImpl(this);
        iPersenter.startRequestget(Apis.SELECTCAR_URL,CarBean.class);
        //设置布局管理器
        setLayout();

        adapter = new CarAdapter(getActivity());
        recyclerView.setAdapter(adapter);



    }

    private void setLayout(){
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        int spacingInPixels = 20;
        recyclerView.addItemDecoration(new SpacesItemDecoration(spacingInPixels));
    }
    @Override
    protected int getViewById() {
        return R.layout.fragment_shoppingcart;
    }

    @Override
    public void success(Object data) {
        if(data instanceof CarBean){
            adapter.setData(((CarBean) data).getResult());
        }
    }

    @Override
    public void failed(String error) {
        toast(error);
    }
    private void toast(String s){
        Toast.makeText(getActivity(),s+"",Toast.LENGTH_SHORT).show();
    }

}
