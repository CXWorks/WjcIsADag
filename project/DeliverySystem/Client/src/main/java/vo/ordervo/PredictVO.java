package vo.ordervo;

import po.InfoEnum;
import vo.InfoVO;

public class PredictVO extends InfoVO{
	private String expense;
	private String predictDate;
	//
	public PredictVO(){
		super(InfoEnum.PREDICT);
	}
}
