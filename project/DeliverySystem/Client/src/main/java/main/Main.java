/*
 * Created by Sissel on 2015/11/21.
 */
package main;

import bl.clientNetCache.CacheHelper;
import bl.clientRMI.NetInitException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ui.accountui.ManageAccountController;
import ui.accountui.NewAccountController;
import ui.loginui.LoginController;
import ui.receiveui.HallReceiveFormController;
import ui.transportui.LoadCarController;

import java.io.IOException;

/**
 * Client//main//Main.java
 *
 * @author CXWorks
 * @date 2015年10月25日 下午11:58:09
 * @version 1.0
 */

public class Main extends Application {

    Stage primaryStage;

    public static void main(String[] args) throws NetInitException {
    	//CacheHelper.initializeCache();
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        // TODO demo
        primaryStage.setTitle("Receive Demo");
        primaryStage.setX(150);
        primaryStage.setY(150);

        primaryStage.setScene(new Scene(
                //HallReceiveFormController.launch()
                //LoadCarController.launch()
                //CheckRevenueFormController.launch()
                //ManageBankAccountController.launch()
                //LoginController.launch()
                //NewAccountController.launch()
                ManageAccountController.launch()
                //NewOrderController.launch()
        ));

        primaryStage.show();
    }
}
