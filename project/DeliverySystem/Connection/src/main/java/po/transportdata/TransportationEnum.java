package po.transportdata;

import po.receivedata.StateEnum;
import util.EnumObservable;

/**
 * Created by Sissel on 2015/10/26.
 */
public enum TransportationEnum  implements EnumObservable<TransportationEnum> {
    PLANE("特快专递"),
    TRAIN("标准快递"),
    CAR("经济快递");

    private String chinese;

    TransportationEnum(String c){
		this.chinese = c;
	}

	public String getChinese() {
		return chinese;
	}
}
