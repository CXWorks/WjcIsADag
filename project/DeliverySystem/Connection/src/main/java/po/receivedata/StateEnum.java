package po.receivedata;

import util.EnumObservable;

/**
 * 
 * @author wjc 2015/10/24
 */

public enum StateEnum implements EnumObservable<StateEnum> {
	Damage("损坏"),
	Complete("完好"),
	Lose("丢失");

	private String chinese;

	StateEnum(String c){
		this.chinese = c;
	}

	@Override
	public String getChinese() {
		return null;
	}
}
