package database;

import java.util.Calendar;
import java.util.Iterator;
import java.util.Map;

import database.enums.TableEnum;

public class MySql {

	public static String time(TableEnum table,String target,Calendar start, Calendar end) {
		String select = "select * from `" + table.getName() + "` where '" + start.getTime().getTime() / 1000
				+ "' <= UNIX_TIMESTAMP(`" + target + "`) " + "and '" + end.getTime().getTime() / 1000
				+ "' >= UNIX_TIMESTAMP(`" + target + "`)";

		return select;
	}

	public static String insert(TableEnum table, Map<String, String> map) {
		String insert = "insert into `" + table.getName() + "`(";
		boolean isFirst = true;
		for (String key : map.keySet()) {
			if (isFirst) {
				insert += key;
				isFirst = false;
			} else {
				insert += "," + key;
			}
		}
		insert += ") values(";
		isFirst = true;
		for (String value : map.values()) {
			if (isFirst) {
				insert += "'" + value + "'";
				isFirst = false;
			} else {
				insert += "," + "'" + value + "'";
			}
		}
		insert += ")";
		return insert;
	}

	public static String delete(TableEnum table, Map<String, String> map) {
		String delete = "delete from `" + table.getName() + "`";
		if (map == null)
			return delete;
		boolean isFirst = true;
		Iterator<Map.Entry<String, String>> entries = map.entrySet().iterator();

		while (entries.hasNext()) {
			Map.Entry<String, String> entry = entries.next();
			if (entry.getValue() == null)
				continue;
			if (isFirst) {
				delete += " where `" + entry.getKey() + "` = '" + entry.getValue() + "'";
				isFirst = false;
			} else {
				delete += " and `" + entry.getKey() + "` = '" + entry.getValue() + "'";
			}

		}
		return delete;
	}

	public static String update(TableEnum table, String target, String value, Map<String, String> map) {
		String update = "update `" + table.getName() + "`";
		update += " set `" + target + "` = '" + value + "'";
		if (map == null)
			return update;
		boolean isFirst = true;
		Iterator<Map.Entry<String, String>> entries = map.entrySet().iterator();

		while (entries.hasNext()) {
			Map.Entry<String, String> entry = entries.next();
			if (entry.getValue() == null)
				continue;
			if (isFirst) {
				update += " where `" + entry.getKey() + "` = '" + entry.getValue() + "'";
				isFirst = false;
			} else {
				update += " and `" + entry.getKey() + "` = '" + entry.getValue() + "'";
			}

		}
		System.out.println(update);

		return update;
	}

	public static String select(TableEnum table, Map<String, String> map) {
		String select = "select * from `" + table.getName() + "`";
		if (map == null)
			return select;
		boolean isFirst = true;
		Iterator<Map.Entry<String, String>> entries = map.entrySet().iterator();

		while (entries.hasNext()) {
			Map.Entry<String, String> entry = entries.next();
			if (entry.getValue() == null)
				continue;
			if (isFirst) {
				select += " where `" + entry.getKey() + "` = '" + entry.getValue() + "'";
				isFirst = false;
			} else {
				select += " and `" + entry.getKey() + "` = '" + entry.getValue() + "'";
			}

		}
		return select;
	}
}