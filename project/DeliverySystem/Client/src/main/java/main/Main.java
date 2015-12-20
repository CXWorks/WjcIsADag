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
import javafx.stage.StageStyle;
import po.memberdata.StaffTypeEnum;
import ui.accountui.ManageAccountController;
import ui.configurationui.ConfigurationController;
import ui.deliverui.deliverController;
import ui.examineui.CheckFormController;
import ui.financeui.CheckFinanceChartController;
import ui.financeui.CheckLogController;
import ui.financeui.ManageBankAccountController;
import ui.financeui.PaymentFormController;
import ui.hallui.ManageCarDriverController;
import ui.initui.CheckInitInfoController;
import ui.loginui.LoginController;
import ui.manangeui.organization.ManageOrganizationController;
import ui.manangeui.salary.ManageSalaryController;
import ui.manangeui.staff.ManageStaffController;
import ui.navigationui.*;
import ui.orderui.NewOrderController;
import ui.receiveui.ReceiveFormController;
import ui.storeui.StockTackController;
import ui.storeui.StoreSummaryController;
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

    public static Scene loginScene;
    public static Scene personScene;
    public static Stage primaryStage;

    public static void main(String[] args) throws NetInitException {
        CacheHelper.initializeCache();
        launch(args);
    }

    /**
     * 返回登录界面
     */
    public static void logOut(){
        primaryStage.setScene(loginScene);
        setDraggable();
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

        if(personScene == null){
            personScene = new Scene(pane);
        }else{
            personScene.setRoot(pane);
        }

        primaryStage.setScene(personScene);
        setDraggable();
    }

    private static double initX;
    private static double initY;

    @Override
    public void start(Stage primaryStage) throws IOException {
        Main.primaryStage = primaryStage;

        primaryStage.setTitle("2333快递物流管理系统");
        primaryStage.setX(150);
        primaryStage.setY(150);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        loginScene = new Scene((Pane)LoginController.launch());
        primaryStage.setScene(loginScene);

        setDraggable();

        primaryStage.show();
    }

    private static void setDraggable(){
        primaryStage.getScene().setOnMousePressed(
                me -> {
                    initX = me.getScreenX() - primaryStage.getX();
                    initY = me.getScreenY() - primaryStage.getY();
                }
        );
        primaryStage.getScene().setOnMouseDragged(
                me -> {
                    primaryStage.setX(me.getScreenX() - initX);
                    primaryStage.setY(me.getScreenY() - initY);
                }
        );
    }
}
