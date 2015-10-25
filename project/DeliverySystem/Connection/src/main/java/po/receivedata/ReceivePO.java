package po.receivedata;

import java.io.Serializable;

import po.receivedata.StateEnum;

/**
 * 
 * @author wjc
 *2015/10/24
 */

public class ReceivePO implements Serializable{
	private String orderID;
	private String transitID;
	private String data;
	private String depature;
	private StateEnum state;
}
