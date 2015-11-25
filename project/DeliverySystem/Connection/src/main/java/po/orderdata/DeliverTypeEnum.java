package po.orderdata;

import java.io.Serializable;

import javafx.collections.ObservableList;
import po.FormPO;
import util.EnumObservable;

public enum DeliverTypeEnum implements Serializable, EnumObservable<DeliverTypeEnum> {
	SLOW("经济快递"),
	NORMAL("标准快递"),
	FAST("特快快递");

	private String chinese;

	DeliverTypeEnum(String c){
		this.chinese = c;
	}

	@Override
	public String getChinese() {
		return chinese;
	}
}