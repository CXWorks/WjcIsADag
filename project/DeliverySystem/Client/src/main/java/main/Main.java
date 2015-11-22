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
import javafx.stage.Stage;

public class Main extends Application {

    Stage primaryStage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        // TODO demo
        primaryStage.setTitle("Receive Demo");
        primaryStage.setX(150);
        primaryStage.setY(150);
        primaryStage.show();
    }
}
