package vo.storevo;

import po.InfoEnum;
import vo.InfoVO;
import vo.ordervo.OrderVO;

/**
 * Created by Sissel on 2015/10/25.
 */
public class GoodsVO extends InfoVO{
	public GoodsVO(){
		super(InfoEnum.GOODS);
	}
	
    public GoodsVO(OrderVO orderVO, StoreInVO storeInVO, StoreOutVO storeOutVO) {
		this();
		this.orderVO = orderVO;
		this.storeInVO = storeInVO;
		this.storeOutVO = storeOutVO;
	}
    
	OrderVO orderVO;
    StoreInVO storeInVO;
    StoreOutVO storeOutVO;
}
