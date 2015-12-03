/*
 * Created by Sissel on 2015/11/21.
 */
package main;

import bl.clientRMI.NetInitException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ui.accountui.ManageAccountController;
import ui.configurationui.ConfigurationController;
import ui.financeui.ManageBankAccountController;
import ui.hallui.ManageCarDriverController;
import ui.manangeui.organization.ManageOrganizationController;
import ui.manangeui.salary.ManageSalaryController;
import ui.navigationui.FinanceNavigation;
import ui.orderui.NewOrderController;
import ui.orderui.PoepleReceiveFormController;
import ui.receiveui.ReceiveFormController;
import ui.storeui.StoreInFormController;
import ui.storeui.StoreOutFormController;
import ui.storeui.StorePartitionController;
import ui.storeui.StoreSummaryController;
import ui.accountui.PersonalAccountViewController;
import ui.accountui.personAccountViewEditDialogController;

import java.io.IOException;

/**
 * Client//main//Main.java
 *
 * @author CXWorks
 * @date 2015年10月25日 下午11:58:09
 * @version 1.0
 */

public class Main extends Application {

    public static Stage primaryStage;

    public static void main(String[] args) throws NetInitException {
        //CacheHelper.initializeCache();
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Main.primaryStage = primaryStage;

        primaryStage.setTitle("Demo");
        primaryStage.setX(150);
        primaryStage.setY(150);

        primaryStage.setScene(new Scene(
//        		StoreInFormController.launch()
//        		StoreOutFormController.launch()
//        		StoreSummaryController.launch()
//        		StorePartitionController.launch()
//        		ConfigurationController.launch()
//                ReceiveFormController.launch()
                //LoadCarController.launch()
                //CheckRevenueFormController.launch()
                ManageBankAccountController.launch()
                //LoginController.launch()
                //NewAccountController.launch()
                //ManageAccountController.launch()
                //NewOrderController.launch()
                //PersonalAccountViewController.launch()
                //CheckFinanceChartController.launch()
//                FinanceNavigation.launch()
//        		ManageOrganizationController.launch()
//        		ManageSalaryController.launch()
//        		ConfigurationController.launch()
                //ManageAccountController.launch()
//        		ManageCarDriverController.launch()
        		//PoepleReceiveFormController.launch()
        		//StoreSummaryController.launch()


        ));

        primaryStage.show();
    }
}
