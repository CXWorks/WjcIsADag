package po.memberdata;

import java.io.Serializable;


import util.EnumObservable;

public enum SexEnum implements Serializable, EnumObservable<SexEnum> {
	MAN("男"),
	WOMAN("女"),
	OTHERS("其他");

	private String chinese;

	SexEnum(String c){
		this.chinese = c;
	}

	@Override
	public String getChinese() {
		return chinese;
	}
}


