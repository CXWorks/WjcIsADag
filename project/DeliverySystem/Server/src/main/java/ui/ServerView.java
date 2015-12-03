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
		try {
			if (!isON) {
				state.setText("当前状态：启动");
				SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String TimeString = time.format(new java.util.Date());
				start_time.setText(TimeString);
				RMIHelper.initializeRMI();
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (

		MalformedURLException e) {
			e.printStackTrace();
		}

	}

	public void stop(ActionEvent actionEvent) {
		try {
			if (isON) {
				RMIHelper.closeServer();
				state.setText("当前状态：停止");
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
}
