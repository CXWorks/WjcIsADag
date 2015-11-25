package po.orderdata;

import java.io.Serializable;

import po.FormPO;
import util.EnumObservable;

public enum PackingEnum implements Serializable, EnumObservable<PackingEnum>{
	PAPER("纸箱"),
	WOOD("木箱"),
	BAG("快递袋"),
	OTHER("其他");

	private String chinese;

	PackingEnum(String c){
		this.chinese = c;
	}

	@Override
	public String getChinese() {
		return chinese;
	}

}