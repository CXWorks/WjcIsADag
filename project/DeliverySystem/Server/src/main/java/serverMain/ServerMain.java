package serverMain;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import message.OperationMessage;
import ui.ServerView;

/**
 *
 * @author WJC
 * @version 1.0
 */
public class ServerMain extends Application {

	public static Stage primaryStage;

	OperationMessage setUpSystem(String username, char[] password, String dbName) {
		return null;
	}

	OperationMessage recoverSystem(String username, char[] password, String dbName) {
		return null;
	}

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		ServerMain.primaryStage = primaryStage;

		primaryStage.setTitle("Server");
		primaryStage.setX(150);
		primaryStage.setY(150);

		primaryStage.setScene(new Scene(ServerView.launch()));

		primaryStage.show();
	}

}
