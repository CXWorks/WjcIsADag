package bl.blImpl.formatCheck;

import java.util.Calendar;

import message.CheckFormMessage;
import bl.blService.FormatCheckService.FormatCheckService;

/** 
 * Client//bl.blImpl.formatCheck//FormatCheckImpl.java
 * @author CXWorks
 * @date 2015年11月21日 下午11:37:51
 * @version 1.0 
 */
public class FormatCheckImpl implements FormatCheckService {

	/* (non-Javadoc)
	 * @see bl.blService.FormatCheckService.FormatCheckService#checkOrderID(java.lang.String)
	 */
	@Override
	public CheckFormMessage checkOrderID(String ID) {
		
		return new CheckFormMessage();
	}

	/* (non-Javadoc)
	 * @see bl.blService.FormatCheckService.FormatCheckService#checkPostDate(java.util.Calendar)
	 */
	@Override
	public CheckFormMessage checkPostDate(Calendar date) {
		Calendar now=Calendar.getInstance();
		if (now.before(date)) {
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
		if (now.after(date)) {
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
		// TODO Auto-generated method stub
		return new CheckFormMessage();
	}

	/* (non-Javadoc)
	 * @see bl.blService.FormatCheckService.FormatCheckService#checkTransportHallID(java.lang.String)
	 */
	@Override
	public CheckFormMessage checkTransportHallID(String ID) {
		// TODO Auto-generated method stub
		return new CheckFormMessage();
	}

	/* (non-Javadoc)
	 * @see bl.blService.FormatCheckService.FormatCheckService#checkTransportCenterID(java.lang.String)
	 */
	@Override
	public CheckFormMessage checkTransportCenterID(String ID) {
		// TODO Auto-generated method stub
		return new CheckFormMessage();
	}

	/* (non-Javadoc)
	 * @see bl.blService.FormatCheckService.FormatCheckService#checkTruckLoadID(java.lang.String)
	 */
	@Override
	public CheckFormMessage checkTruckLoadID(String ID) {
		// TODO Auto-generated method stub
		return new CheckFormMessage();
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
		// TODO Auto-generated method stub
		return new CheckFormMessage();
	}

	/* (non-Javadoc)
	 * @see bl.blService.FormatCheckService.FormatCheckService#checkPhone(java.lang.String)
	 */
	@Override
	public CheckFormMessage checkPhone(String num) {
		// TODO Auto-generated method stub
		return new CheckFormMessage();
	}

	/* (non-Javadoc)
	 * @see bl.blService.FormatCheckService.FormatCheckService#checkIsInt(java.lang.String)
	 */
	@Override
	public CheckFormMessage checkIsInt(String num) {
		// TODO Auto-generated method stub
		return new CheckFormMessage();
	}

	/* (non-Javadoc)
	 * @see bl.blService.FormatCheckService.FormatCheckService#checkWeight(java.lang.String)
	 */
	@Override
	public CheckFormMessage checkWeight(String weight) {
		// TODO Auto-generated method stub
		return new CheckFormMessage();
	}

	/* (non-Javadoc)
	 * @see bl.blService.FormatCheckService.FormatCheckService#checkVolume(java.lang.String)
	 */
	@Override
	public CheckFormMessage checkVolume(String volume) {
		// TODO Auto-generated method stub
		return new CheckFormMessage();
	}

	/* (non-Javadoc)
	 * @see bl.blService.FormatCheckService.FormatCheckService#checkIsNull(java.lang.String)
	 */
	@Override
	public CheckFormMessage checkIsNull(String in) {
		// TODO Auto-generated method stub
		return new CheckFormMessage();
	}

}
