package vo.storevo;

import model.store.StoreLocation;

import java.util.ArrayList;
import java.util.List;

import po.InfoEnum;
import vo.InfoVO;

/**
 * Created by Sissel on 2015/10/25.
 */
public class StockTackVO extends InfoVO{

	public StockTackVO(){
		super(InfoEnum.STOCK_TACK);
	}
	
    public StockTackVO(String date, ArrayList<StoreLocation> locations,
			ArrayList<String> filters) {
		this();
		this.date = date;
		this.locations = locations;
		this.filters = filters;
	}

	// 日期
    String date;

    // 该日期的序号
    static String id;

    // 筛选出的符合的货架位置
    ArrayList<StoreLocation> locations;

    // 已经添加的筛选条件
    ArrayList<String> filters;

}
