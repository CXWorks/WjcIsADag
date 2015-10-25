package po.deliverdata;

import java.io.Serializable;

import po.FormPO;

/**
 * 
 * @author wjc
 *2015/10/24
 */

public class DeliverPO extends FormPO implements Serializable{
	private String orderID;
	private String data;
	private String postman;
}
