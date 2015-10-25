package blService.FormatCheckService;

import message.CheckFormMessage;


/**
 * 
 * @author wjc
 *2015/10/24
 */

public interface FormatCheckService{
	
	/**
	 * 检查订单号
	 * @param ID 订单号
	 * @return 返回检查结果
	 */
	public CheckFormMessage checkOrderID(String ID);
	
	/**
	 * 检查日期（系统时间之前）
	 * @param data 日期
	 * @return 返回检查结果
	 */
	public  CheckFormMessage checkDate(String data);
	
	/**
	 * 检查日期（系统时间之hou）
	 * @param data 日期
	 * @return 返回检查结果
	 */
	public  CheckFormMessage checkPreDate(String data);
	
	/**
	 * 检查位置
	 * @param from 出发地,to 到达地
	 * @return 返回检查结果
	 */
	public  CheckFormMessage checkLoction(String from,String to);
	
	/**
	 * 检查中转单号
	 * @param ID 中转单号
	 * @return 返回检查结果
	 */
	public CheckFormMessage checkTransitID(String ID);
	
	/**
	 * 检查中转中心编号
	 * @param ID 中转中心编号
	 * @return 返回检查结果
	 */
	public CheckFormMessage checkTransportHallID(String ID);
	
	/**
	 * 检查营业厅编号
	 * @param ID 营业厅编号
	 * @return 返回检查结果
	 */
	public CheckFormMessage checkTransportCenterID(String ID);
	
	/**
	 * 检查车辆代号
	 * @param ID 车辆代号
	 * @return 返回检查结果
	 */
	public CheckFormMessage checkTruckLoadID(String ID);
	
	/**
	 * 检查人名
	 * @param name 人名
	 * @return 返回检查结果
	 */
	public CheckFormMessage checkName(String name);
	
	/**
	 * 检查金额
	 * @param money 金额
	 * @return 返回检查结果
	 */
	public CheckFormMessage checkMoney(String money);
	
	/**
	 * 检查手机号码
	 * @param num 手机号码
	 * @return 返回检查结果
	 */
	public CheckFormMessage checkPhone(String num);
	
	/**
	 * 检查是否为整数（货物内件数）
	 * @param num 货物内件数
	 * @return 返回检查结果
	 */
	public CheckFormMessage checkIsInt(String num);
	
	/**
	 * 检查重量
	 * @param weight 重量
	 * @return 返回检查结果
	 */
	public CheckFormMessage checkWeight(String weight);
	
	/**
	 * 检查体积
	 * @param volume 体积
	 * @return 返回检查结果
	 */
	public CheckFormMessage checkVolume(String volume);
	
	/**
	 * 检查输入是否为空
	 * @param in 输入的字符串
	 * @return 返回检查结果
	 */
	public CheckFormMessage checkIsNull(String in);
}
