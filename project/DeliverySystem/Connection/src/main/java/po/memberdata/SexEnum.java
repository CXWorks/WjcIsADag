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
	
	public int getNum(){
		switch (this) {
		case MAN:
			return 0;
		case WOMAN:
			return 1;
		case OTHERS:
			return 2;

		default:
			return -1;
		}
	}

	@Override
	public String getChinese() {
		return chinese;
	}
}


