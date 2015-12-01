package model.store;

import java.io.Serializable;

import po.receivedata.StateEnum;
import util.EnumObservable;

/**
 * Created by Sissel on 2015/10/24.
 */
public enum StoreAreaCode implements EnumObservable<StoreAreaCode> {
    AIR("航空"),
    RAIL("铁路"),
    ROAD("公路"),
    FLEX("机动");

	private String chinese;

	StoreAreaCode(String c){
		this.chinese = c;
	}

	@Override
	public String getChinese() {
		// TODO Auto-generated method stub
		return chinese;
	}
}
