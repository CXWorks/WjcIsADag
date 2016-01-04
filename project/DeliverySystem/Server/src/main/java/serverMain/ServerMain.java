package serverMain;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
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
        primaryStage.initStyle(StageStyle.UNDECORATED);
		primaryStage.getIcons().add(new Image(this.getClass().getResourceAsStream("/ui/server/logo-server.png"))); 
		primaryStage.setX(150);
		primaryStage.setY(150);

        primaryStage.setOnCloseRequest(
        		
                event -> {
                    System.exit(0);
                }
        );

		primaryStage.setScene(new Scene(ServerView.launch()));

		primaryStage.show();
	}

}
