package vo.storevo;

import model.store.StoreLocation;

import java.util.ArrayList;
import java.util.List;

import model.store.StoreModel;
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
	public String date;

    // 该日期的序号
	public String id;

    // 筛选出的符合的货架位置
	public ArrayList<StoreLocation> locations;

    // 已经添加的筛选条件
	public ArrayList<String> filters;

	// 直接给我storeModel好了
	public StoreModel storeModel;

}
