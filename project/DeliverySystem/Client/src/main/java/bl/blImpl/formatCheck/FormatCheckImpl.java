package bl.blImpl.formatCheck;

import java.util.Calendar;

import javax.management.openmbean.OpenDataException;

import message.CheckFormMessage;
import bl.blService.FormatCheckService.FormatCheckService;

/** 
 * Client//bl.blImpl.formatCheck//FormatCheckImpl.java
 * @author CXWorks
 * @date 2015年11月21日 下午11:37:51
 * @version 1.0 
 */
public class FormatCheckImpl implements FormatCheckService {
	/*
	 * @warning true代表错误
	 */
	private boolean checkFormIDLength(String ID){
		if (ID.length()!=24) {
			return true;
		}
		else {
			return false;
		}
	}

	/* (non-Javadoc)
	 * @see bl.blService.FormatCheckService.FormatCheckService#checkOrderID(java.lang.String)
	 */
	@Override
	public CheckFormMessage checkOrderID(String ID) {
		if (ID.length()!=10) {
			return new CheckFormMessage(false, "位数错误");
		} else {
			return new CheckFormMessage();
		}
	}

	/* (non-Javadoc)
	 * @see bl.blService.FormatCheckService.FormatCheckService#checkPostDate(java.util.Calendar)
	 */
	@Override
	public CheckFormMessage checkPostDate(Calendar date) {
		Calendar now=Calendar.getInstance();
		if (!now.after(date)) {
			return new CheckFormMessage();
		}
		else {
			return new CheckFormMessage(false, "输入日期早于当前");
		}
	}

	/* (non-Javadoc)
	 * @see bl.blService.FormatCheckService.FormatCheckService#checkPreDate(java.util.Calendar)
	 */
	@Override
	public CheckFormMessage checkPreDate(Calendar date) {
		Calendar now=Calendar.getInstance();
		if (!now.before(date)) {
			return new CheckFormMessage();
		}
		else {
			return new CheckFormMessage(false, "输入日期晚于当前");
		}
	}

	/* (non-Javadoc)
	 * @see bl.blService.FormatCheckService.FormatCheckService#checkLoction(java.lang.String, java.lang.String)
	 */
	@Override
	public CheckFormMessage checkLoction(String from, String to) {
		if (from.equalsIgnoreCase(to)) {
			return new CheckFormMessage(false, "输入相同");
		} else {
			return new CheckFormMessage();
		}
	}

	/* (non-Javadoc)
	 * @see bl.blService.FormatCheckService.FormatCheckService#checkTransitID(java.lang.String)
	 */
	@Override
	public CheckFormMessage checkTransitID(String ID) {
		if (this.checkFormIDLength(ID)) {
			return new CheckFormMessage(false, "位数错误");
		} else {
			if (ID.charAt(1)=='7'||ID.charAt(1)=='8') {
				return new CheckFormMessage();
			} else {
				return new CheckFormMessage(false, "单据类型错误，应为07或08");
			}
		}
	}

	/* (non-Javadoc)
	 * @see bl.blService.FormatCheckService.FormatCheckService#checkTransportHallID(java.lang.String)
	 */
	@Override
	public CheckFormMessage checkTransportHallID(String ID) {
		if (ID.length()!=7) {
			return new CheckFormMessage(false, "位数错误，应为7位");
		} else {
			if (ID.charAt(3)=='1') {
				return new CheckFormMessage();
			} else {
				return new CheckFormMessage(false, "编号逻辑错误，不是营业厅");
			}
		}
	}

	/* (non-Javadoc)
	 * @see bl.blService.FormatCheckService.FormatCheckService#checkTransportCenterID(java.lang.String)
	 */
	@Override
	public CheckFormMessage checkTransportCenterID(String ID) {
		if (ID.length()!=7) {
			return new CheckFormMessage(false, "位数错误，应为7位");
		} else {
			if (ID.charAt(3)=='0') {
				return new CheckFormMessage();
			} else {
				return new CheckFormMessage(false, "编号逻辑错误，不是中转中心");
			}
		}
	}

	/* (non-Javadoc)
	 * @see bl.blService.FormatCheckService.FormatCheckService#checkTruckLoadID(java.lang.String)
	 */
	@Override
	public CheckFormMessage checkTruckLoadID(String ID) {
		if (ID.length()==13) {
			return new CheckFormMessage();
		} else {
			return new CheckFormMessage(false, "位数错误，应为13位");
		}
	}

	/* (non-Javadoc)
	 * @see bl.blService.FormatCheckService.FormatCheckService#checkName(java.lang.String)
	 */
	@Override
	public CheckFormMessage checkName(String name) {
		// TODO Auto-generated method stub
		return new CheckFormMessage();
	}

	/* (non-Javadoc)
	 * @see bl.blService.FormatCheckService.FormatCheckService#checkMoney(java.lang.String)
	 */
	@Override
	public CheckFormMessage checkMoney(String money) {
		double m=Double.parseDouble(money);
		if (m>=0) {
			return new CheckFormMessage();
		} else {
			return new CheckFormMessage(false, "输入金额小于0");
		}
		
	}

	/* (non-Javadoc)
	 * @see bl.blService.FormatCheckService.FormatCheckService#checkPhone(java.lang.String)
	 */
	@Override
	public CheckFormMessage checkPhone(String num) {
		if (num.length()==11) {
			return new CheckFormMessage();
		} else {
			return new CheckFormMessage(false, "号码位数不对，应为11位");
		}
	}

	/* (non-Javadoc)
	 * @see bl.blService.FormatCheckService.FormatCheckService#checkIsInt(java.lang.String)
	 */
	@Override
	public CheckFormMessage checkIsInt(String num) {
		try {
			int temp=Integer.parseInt(num);
			return new CheckFormMessage();
		} catch (NumberFormatException e) {
			return new CheckFormMessage(false, "不是整数");
		}
	}

	/* (non-Javadoc)
	 * @see bl.blService.FormatCheckService.FormatCheckService#checkWeight(java.lang.String)
	 */
	@Override
	public CheckFormMessage checkWeight(String weight) {
		try {
			double vol=Double.parseDouble(weight);
			return new CheckFormMessage();
		} catch (Exception e) {
			return new CheckFormMessage(false, "不是数字");
		}
	}

	/* (non-Javadoc)
	 * @see bl.blService.FormatCheckService.FormatCheckService#checkVolume(java.lang.String)
	 */
	@Override
	public CheckFormMessage checkVolume(String volume) {
		try {
			double vol=Double.parseDouble(volume);
			return new CheckFormMessage();
		} catch (Exception e) {
			return new CheckFormMessage(false, "不是数字");
		}
		
	}

	/* (non-Javadoc)
	 * @see bl.blService.FormatCheckService.FormatCheckService#checkIsNull(java.lang.String)
	 */
	@Override
	public CheckFormMessage checkIsNull(String in) {
		if (in==null) {
			return new CheckFormMessage();
		} else {
			return new CheckFormMessage(false, "不是空");
		}
	}

	/* (non-Javadoc)
	 * @see bl.blService.FormatCheckService.FormatCheckService#checkReceiveID(java.lang.String)
	 */
	@Override
	public CheckFormMessage checkReceiveID(String ID) {
		if (this.checkFormIDLength(ID)) {
			return new CheckFormMessage(false, "位数错误，应为24位");
		} else {
			if (ID.charAt(2)=='3') {
				return new CheckFormMessage();
			} else {
				return new CheckFormMessage(false, "单据类型错误，应为03");
			}
		}
	}

}
