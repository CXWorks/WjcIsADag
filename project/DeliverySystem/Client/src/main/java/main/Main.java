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
import ui.accountui.PersonalAccountViewController;
import ui.configurationui.ConfigurationController;
import ui.deliverui.deliverController;
import ui.examineui.CheckFormController;
import ui.financeui.CheckFinanceChartController;
import ui.financeui.CheckLogController;
import ui.financeui.ManageBankAccountController;
import ui.hallui.ManageCarDriverController;
import ui.initui.CheckInitInfoController;
import ui.loginui.LoginController;
import ui.manangeui.organization.ManageOrganizationController;
import ui.manangeui.salary.ManageSalaryController;
import ui.manangeui.staff.ManageStaffController;
import ui.navigationui.*;
import ui.orderui.NewOrderController;
import ui.storeui.StockTackController;
import ui.transportui.LoadCarController;
import ui.transportui.TransitFormController;
import userinfo.UserInfo;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Client//main//Main.java
 *
 * @author CXWorks
 * @date 2015年10月25日 下午11:58:09
 * @version 1.0
 */

public class Main extends Application {

    private static Map<StaffTypeEnum, Parent> panes = new HashMap<>();
    private static Pane loginPane;

    public static Stage primaryStage;

    public static void main(String[] args) throws NetInitException {
        //CacheHelper.initializeCache();
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
                case MANAGER:
                    return ManagerNavigation.launch();
                case ADMINISTRATOR:
                    return AdminNavigation.launch();
                case DELIVER:
                    return DeliverNavigation.launch();
                case HALL_COUNTERMAN:
                    return HallNavigation.launch();
                case CENTER_COUNTERMAN:
                    return CenterNavigation.launch();
                case STOREMAN:
                    return StoreNavigation.launch();
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据UserInfo加载对应的界面
     */
    public static void logIn(){

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

        loginPane = (Pane)LoginController.launch();

//        primaryStage.setScene(new Scene( loginPane));
        primaryStage.setScene(new Scene(
//        		NewOrderController.launch()
//        		CheckFormController.launch()
//                ManageBankAccountController.launch()
//                CheckFinanceChartController.launch()
        		//ManageOrganizationController.launch()
//                CheckLogController.launch()
//                deliverController.launch()
        		//ManageStaffController.launch()
//               StockTackController.launch()
        		//ConfigurationController.launch()
//        		ManageSalaryController.launch()
//       		LoadCarController.launch()
//        		TransitFormController.launch()
        		//LoginController.launch()
        		//StorePartitionController.launch()
                //FinanceNavigation.launch()
                //PersonalAccountViewController.launch()
                ManagerNavigation.launch()
        ));
        primaryStage.show();
    }
}
