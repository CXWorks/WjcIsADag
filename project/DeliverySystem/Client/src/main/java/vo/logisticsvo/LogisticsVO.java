package vo.logisticsvo;

import java.util.ArrayList;

import po.InfoEnum;
import po.orderdata.DeliverTypeEnum;
import vo.InfoVO;

/**
 * 
 * @author CCharles_Meng
 *2015/10/24
 */

public class LogisticsVO extends InfoVO{
	  private String nameFrom;
	  private String nameTo;
	  private ArrayList<String> location;
	  private ArrayList<String> time;
	  private String local;
	  private String phoneNumTo;
	  private String goodsNum;
	  private String goodsName;
	  private DeliverTypeEnum type;
	  public LogisticsVO(){
		  super(InfoEnum.LOGISTICS);
	  }
	  //
	  
}
