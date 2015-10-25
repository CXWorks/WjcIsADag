package po.receivedata;

import java.io.Serializable;

import po.FormPO;
import po.receivedata.StateEnum;

/**
 * 
 * @author wjc
 *2015/10/24
 */

public class ReceivePO extends FormPO implements Serializable{
	private String orderID;
	private String transitID;
	private String data;
	private String depature;
	private StateEnum state;
}
