package jx.com.shoppingtrolley_zihenguncle.url;

import java.security.PublicKey;

public class Apis {

    public static final String LOGIN_URL = "user/v1/login";
    public static final String REGISTER_URL="user/v1/register";
    public static final String BANNER_URL = "commodity/v1/bannerShow";
    public static final String GOODS_URL = "commodity/v1/commodityList";
    public static final String ERJI_URL = "commodity/v1/findFirstCategory";
    public static final String ERJI_ITEM_URL = "commodity/v1/findSecondCategory?firstCategoryId=%s";
    public static  final String HOT_LABLE_URL="commodity/v1/findCommodityListByLabel?labelId=%s&page=%d&count=%d";
    public static final String MORE_URL = "commodity/v1/findCommodityByKeyword?keyword=%s&page=%d&count=%d";
    public static final String ERJI_SHOW_URL = "commodity/v1/findCommodityByCategory?categoryId=%s&page=%d&count=%d";
    //详情接口
    public static final String DETAILS_URL = "commodity/v1/findCommodityDetailsById?commodityId=%d";
    //圈子列表
    public static final String CIRCLE_LIST_URL = "circle/v1/findCircleList?page=%d&count=%d";

    //浏览记录
    public static final String FOOT_JILU_URL = "commodity/verify/v1/browseList?page=%d&count=%d";

    //点赞
    public static final String ADD_CIRCLR_URL = "circle/verify/v1/addCircleGreat";
    //取消点赞
    public static final String CANCLE_CIRCLE_URL = "circle/verify/v1/cancelCircleGreat?circleId=%s";

    //评论列表
    public static final String DISCUSS_Url ="commodity/v1/CommodityCommentList?commodityId=%d&page=%d&count=%d";

    //查询购物车
    public static final String SELECTCAR_URL = "order/verify/v1/findShoppingCart";

    //加入购物车
    public static final String ADDCAR_URL = "order/verify/v1/syncShoppingCart";
}
