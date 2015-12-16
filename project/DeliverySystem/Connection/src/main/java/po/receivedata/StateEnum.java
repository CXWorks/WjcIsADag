package po.receivedata;

import util.EnumObservable;

/**
 *
 * @author wjc 2015/10/24
 */

public enum StateEnum implements EnumObservable<StateEnum> {
	Complete("完好"),
	Damage("损坏"),
	Lose("丢失");

	private String chinese;

	StateEnum(String c){
		this.chinese = c;
	}

	public String getChinese() {
		return chinese;
	}
}
