/*
 * Created by Sissel on 2015/11/21.
 */
package main;

import bl.clientNetCache.CacheHelper;
import bl.clientRMI.NetInitException;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import po.memberdata.StaffTypeEnum;
import ui.accountui.ManageAccountController;
import ui.configurationui.ConfigurationController;
import ui.financeui.ManageBankAccountController;
import ui.hallui.ManageCarDriverController;
import ui.manangeui.organization.ManageOrganizationController;
import ui.manangeui.salary.ManageSalaryController;
import ui.manangeui.staff.ManageStaffController;
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
import userinfo.UserInfo;

import java.io.IOException;
import java.util.Map;

/**
 * Client//main//Main.java
 *
 * @author CXWorks
 * @date 2015年10月25日 下午11:58:09
 * @version 1.0
 */

public class Main extends Application {

    private static Map<StaffTypeEnum, Parent> panes;
    private static Pane loginPane;

    public static Stage primaryStage;

    public static void main(String[] args) throws NetInitException {
        CacheHelper.initializeCache();
        launch(args);
    }

    /**
     * 返回登录界面
     */
    public static void logOut(){
        primaryStage.setScene(new Scene(loginPane));
    }

    private static Parent launchByStaff(StaffTypeEnum staffTypeEnum){
        try{
            switch (staffTypeEnum){
                case BURSAR:
                    return FinanceNavigation.launch();

            }
        }catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据UserInfo加载对应的界面
     */
    private static void logIn(){
        StaffTypeEnum staffTypeEnum = UserInfo.getStaffType();
        Parent pane = panes.get(staffTypeEnum);

        if(pane == null){
            pane = launchByStaff(staffTypeEnum);
            panes.put(staffTypeEnum, pane);
        }
        primaryStage.setScene(new Scene(pane));
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
 //       		ConfigurationController.launch()
                ReceiveFormController.launch()
                //LoadCarController.launch()
                //CheckRevenueFormController.launch()
            //    ManageBankAccountController.launch()
                //LoginController.launch()
                //NewAccountController.launch()
                //ManageAccountController.launch()
//                NewOrderController.launch()
                //PersonalAccountViewController.launch()
                //CheckFinanceChartController.launch()
                //FinanceNavigation.launch()
//        		ManageOrganizationController.launch()
//        		ManageSalaryController.launch()
        		//ConfigurationController.launch()
                //ManageAccountController.launch()
//        		ManageCarDriverController.launch()
        		//PoepleReceiveFormController.launch()
        		//StoreSummaryController.launch()
//        		ManageStaffController.launch()
        ));

        primaryStage.show();
    }
}
