package ui;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;

import database.RMIHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;

public class ServerView {
	public Label state;
	public Label start_time;

	public static Parent launch() throws IOException {
		FXMLLoader contentLoader = new FXMLLoader();
		contentLoader.setLocation(ServerView.class.getResource("server.fxml"));
		return contentLoader.load();
	}

	@FXML
	public void initialize() {

	}

	public void start(ActionEvent actionEvent) {
		try {
			RMIHelper.initializeRMI();
			state.setText("启动");
			SimpleDateFormat time=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    	String TimeString = time.format(new java.util.Date());
			start_time.setText("开始时间：" + TimeString);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void stop(ActionEvent actionEvent) {
		try {
			RMIHelper.closeServer();
			state.setText("关闭");
			start_time.setText("开始时间：");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
