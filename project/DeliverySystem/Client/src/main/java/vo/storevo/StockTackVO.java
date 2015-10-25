package vo.storevo;

import model.store.StoreLocation;

import java.util.List;

/**
 * Created by Sissel on 2015/10/25.
 */
public class StockTackVO {

    // 日期
    String date;

    // 该日期的序号
    static String id;

    // 筛选出的符合的货架位置
    List<StoreLocation> locations;

    // 已经添加的筛选条件
    List<String> filters;

}