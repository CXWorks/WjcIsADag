package po.transportdata;

import po.receivedata.StateEnum;
import util.EnumObservable;

/**
 * Created by Sissel on 2015/10/26.
 */
public enum TransportationEnum  implements EnumObservable<TransportationEnum> {
    PLANE("飞机"),
    TRAIN("火车"),
    CAR("汽车");

    private String chinese;

    TransportationEnum(String c){
		this.chinese = c;
	}

	public String getChinese() {
		return chinese;
	}
}
