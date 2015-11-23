/*
 * Created by Sissel on 2015/11/21.
 */
package main;

import factory.FormFactory;

/** 
 * Client//main//Main.java
 * @author CXWorks
 * @date 2015年10月25日 下午11:58:09
 * @version 1.0
 */

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ui.financeui.CheckRevenueFormController;
import ui.receiveui.HallReceiveFormController;

import java.io.IOException;

import bl.clientNetCache.CacheHelper;
import bl.clientRMI.NetInitException;
import bl.clientRMI.RMIHelper;
import ui.transportui.LoadCarController;

public class Main extends Application {

    Stage primaryStage;

    public static void main(String[] args) throws NetInitException {
    	//CacheHelper.initializeCache();
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        // TODO demo
        primaryStage.setTitle("Receive Demo");
        primaryStage.setX(150);
        primaryStage.setY(150);

        try {
            primaryStage.setScene(new Scene(
            //        HallReceiveFormController.launch()
                   // LoadCarController.launch()
                    CheckRevenueFormController.launch()
            ));
        } catch (IOException e) {
            System.out.println("can't find the fxml file");
        }
        primaryStage.show();
    }
}
