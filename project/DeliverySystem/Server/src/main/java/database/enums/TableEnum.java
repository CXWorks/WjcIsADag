package database.enums;

public enum TableEnum {
	ACCOUNT("account"),
	BANK_ACCOUNT("bank_account"),
	CAR("car"),
	CENTER("center"),
	CENTEROUT("centerout"),
	CITY2D("city2d"),
	DELIVER("deliver"),
	HALL("hall"),
	LOAD("load"),
	LOG("log"),
	ORDER("order"),
	PACK("pack"),
	PAYMENT("payment"),
	PRICE("price"),
	PROPORTION("proportion"),
	RECEIVE("receive"),
	REVENUE("revenue"),
	SALARY_STRATEGY("salary_strategy"),
	STAFF("staff"),
	STORE_IN("store_in"),
	STORE_MODEL("store_model"),
	STORE_OUT("store_out"),
	TACK("tack"),
	WARNINGLINE("warningline");

	private String name;

	TableEnum(String c) {
		this.name = c;
	}

	public String getName() {
		return name;
	}

}
