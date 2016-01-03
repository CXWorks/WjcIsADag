package ui;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;

import database.ConnecterHelper;
import database.RMIHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;

public class ServerView {
	public Label state;
	public Label start_time;
	private boolean isON = false;

	public static Parent launch() throws IOException {
		FXMLLoader contentLoader = new FXMLLoader();
		contentLoader.setLocation(ServerView.class.getResource("server.fxml"));
		return contentLoader.load();
	}

	@FXML
	public void initialize() {

	}

	public void start(ActionEvent actionEvent) {
		if (!isON) {
			new Server().start();
			isON = true;
			state.setText(" 启动");
			SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String TimeString = time.format(new java.util.Date());
			start_time.setText(TimeString);
		}

	}

	public void stop(ActionEvent actionEvent) {
		try {
			System.out.println("hhh");
			if (isON) {
				isON = false;
				RMIHelper.closeServer();
				state.setText(" 停止");
				start_time.setText("");
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
	}



	public void out(ActionEvent actionEvent) {
		ConnecterHelper.deconnSQL();
		System.exit(0);
	}
	  public void minimizeStage(ActionEvent actionEvent) {
		  
	    }

}

/**
 * 启动服务的线程，防止启动时候卡顿
 *
 * @author wjc
 * @version 2015年12月24日
 */
class Server extends Thread {

	@Override
	public void run() {
		try {
			RMIHelper.initializeRMI();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
