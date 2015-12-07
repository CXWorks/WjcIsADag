package rmiImpl.initaldata;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import database.ConnecterHelper;
import message.OperationMessage;
import po.initialdata.InitialDataPO;

public class InitialHelper {

	private Connection conn = ConnecterHelper.getConn();
	private PreparedStatement statement = null;

	public InitialHelper() {
		conn = ConnecterHelper.getConn();
	}

	public int getVersion() throws ClassNotFoundException {
		String file_path = "E:/initial/";
		return new File(file_path).listFiles().length + 1;
	}

	public OperationMessage saveFile(InitialDataPO po) throws ClassNotFoundException {
		int num = this.getVersion();
		po.setVersion(num + "");
		try {
			ObjectOutputStream oo = new ObjectOutputStream(
					new FileOutputStream(new File("E:/initial/" + num + ".txt")));
			oo.writeObject(po);
			oo.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new OperationMessage(false, "打包时出错");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new OperationMessage(false, "打包时出错：");
		}

		return new OperationMessage(true, "版本号：" + num);
	}

	public InitialDataPO loadFile(String version) {

		InitialDataPO po = null;
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("E:/Person.txt")));
			po = (InitialDataPO) ois.readObject();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return po;
	}

	public OperationMessage clearMysql() {
		ArrayList<String> list = new ArrayList<String>() {
			{
				add("account");
				add("bank_account");
				add("car");
				add("center");
				add("centerout");
				add("city2d");
				add("deliver");
				add("driver");
				add("hall");
				add("load");
				add("log");
				add("order");
				add("pack");
				add("payment");
				add("price");
				add("proportion");
				add("receive");
				add("revenue");
				add("salary_strategy");
				add("staff");
				add("store_in");
				add("store_model");
				add("store_out");
			}

		};
		OperationMessage result = new OperationMessage();
		for (String tmp : list) {
			String delete = "delete from `" + tmp + "`";
			try {
				statement = conn.prepareStatement(delete);
				statement.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				result = new OperationMessage(false, "清空时出错：");
				System.err.println("清空时出错：");
				e.printStackTrace();
			}
		}
		return result;
	}

}
