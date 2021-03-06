package jx.com.shoppingtrolley_zihenguncle.order.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.view.View;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import jx.com.shoppingtrolley_zihenguncle.R;
import jx.com.shoppingtrolley_zihenguncle.base.BaseFragment;
import jx.com.shoppingtrolley_zihenguncle.bean.AddCarBean;
import jx.com.shoppingtrolley_zihenguncle.bean.CarBean;
import jx.com.shoppingtrolley_zihenguncle.iteminterval.SpacesItemDecoration;
import jx.com.shoppingtrolley_zihenguncle.order.bean.OrderBean;
import jx.com.shoppingtrolley_zihenguncle.order.beevalued_adapter.BeevaluedAdapter;
import jx.com.shoppingtrolley_zihenguncle.persenter.IPersenterImpl;
import jx.com.shoppingtrolley_zihenguncle.tool.VerifyUtils;
import jx.com.shoppingtrolley_zihenguncle.url.Apis;
import jx.com.shoppingtrolley_zihenguncle.view.IView;

public class BeevaluatedFragment extends BaseFragment implements IView {


    private int page;
    //要查询对应状态的订单数据
    private int STATUS = 3;
    //当前页显示条数
    private int COUNT = 5;
    IPersenterImpl iPersenter;
    @BindView(R.id.beevaluated_xrecycle)
    XRecyclerView xRecyclerView;
    private BeevaluedAdapter adapter;
    @Override
    protected void initData(View view) {

        page = 1;
        ButterKnife.bind(this,view);
        iPersenter = new IPersenterImpl(this);
        //设置布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        xRecyclerView.setLayoutManager(layoutManager);
        int spacingInPixels = 11;
        xRecyclerView.addItemDecoration(new SpacesItemDecoration(spacingInPixels));

        adapter = new BeevaluedAdapter(getActivity());
        xRecyclerView.setAdapter(adapter);
        xRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page = 1;
                getData();
            }

            @Override
            public void onLoadMore() {
                getData();
            }
        });
        getData();
        adapter.setGotoPay(new BeevaluedAdapter.gotoPay() {
            @Override
            public void setPay(String orderId) {
                Map<String,String> map = new HashMap<>();
                map.put("orderId",orderId);
                iPersenter.startRequestPut(Apis.DELIVERY_URL,map,AddCarBean.class);
            }
        });
    }

    private void getData(){
        iPersenter.startRequestget(String.format(Apis.SELECT_ORDER_URL,STATUS,page,COUNT),OrderBean.class);
    }
    @Override
    protected int getViewById() {
        return R.layout.fragment_beevaluated;
    }

    @Override
    public void success(Object data) {
        if(data instanceof OrderBean){
            if(page == 1){
                adapter.setData(((OrderBean) data).getOrderList());
            }else {
                adapter.addData(((OrderBean) data).getOrderList());
            }
        }
        page++;
        xRecyclerView.loadMoreComplete();
        xRecyclerView.refreshComplete();

    }

    @Override
    public void failed(String error) {
        VerifyUtils.getInstance().toast(error);
    }
}
