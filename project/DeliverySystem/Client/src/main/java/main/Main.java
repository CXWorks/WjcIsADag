/*
 * Created by Sissel on 2015/11/21.
 */
package main;

import bl.clientNetCache.CacheHelper;
import bl.clientRMI.NetInitException;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import po.memberdata.StaffTypeEnum;
import ui.loginui.LoginController;
import ui.navigationui.*;
import userinfo.UserInfo;

import java.awt.event.MouseEvent;
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
//        CacheHelper.initializeCache();
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

    private static double calX;
    private static double calY;
    private static double oldPosX;
    private static double oldPosY;
    private static double oldWidth;
    private static double oldHeight;
    private static boolean dragging = false;
    private static boolean resizing = false;
    private static CURSOR_AREA pressedArea = CURSOR_AREA.CENTER;
    private static final int padding = 14;
    private static final int titleHeight = 50;

    @Override
    public void start(Stage primaryStage) throws IOException {
        Main.primaryStage = primaryStage;

        primaryStage.setTitle("2333快递物流管理系统");
        primaryStage.setX(150);
        primaryStage.setY(100);

        primaryStage.initStyle(StageStyle.UNDECORATED);
//        loginScene = new Scene((Pane)LoginController.launch());
        // Test
        UserInfo.setInfo("06000001", StaffTypeEnum.DELIVER, "0251001", "wjr");
        loginScene = new Scene((Pane)DeliverNavigation.launch());

        primaryStage.setScene(loginScene);
        setDraggable();
        primaryStage.show();
    }

    /**
     * the following is the implementation of resizing without frame, so ugly that I don't recommend you read the code (T_T)
     */
    private static enum CURSOR_AREA{
        NORTH_WEST,
        NORTH_EAST,
        SOUTH_WEST,
        SOUTH_EAST,
        NORTH,
        SOUTH,
        WEST,
        EAST,
        TITLE,
        CENTER
    }

    private static CURSOR_AREA getCursorArea(double x, double y, double width, double height){
        if(y < padding && x < padding){
            return CURSOR_AREA.NORTH_WEST;
        }else if(y < padding && x > width - padding){
            return CURSOR_AREA.NORTH_EAST;
        }else if(y > height - padding && x < padding){
            return CURSOR_AREA.SOUTH_WEST;
        }else if(y > height - padding && x > width - padding){
            return CURSOR_AREA.SOUTH_EAST;
        }else if(y> height - padding){
            return CURSOR_AREA.SOUTH;
        }else if(x> width - padding){
            return CURSOR_AREA.EAST;
        }else if(y < padding){
            return CURSOR_AREA.NORTH;
        }else if(x < padding){
            return CURSOR_AREA.WEST;
        }else if(y < titleHeight){
            return CURSOR_AREA.TITLE;
        }else{
            return CURSOR_AREA.CENTER;
        }
    }

    private static final double minWidth = 300;
    private static final double minHeight = 500;
    private static void setBounds(Stage stage, double x, double y, double w, double h){
        if(w > minWidth){
            stage.setWidth(w);
            stage.setX(x);
            stage.setY(y);
        }else{
            stage.setWidth(minWidth);
        }
        if(h > minHeight){
            stage.setHeight(h);
            stage.setX(x);
            stage.setY(y);
        }else{
            stage.setHeight(minHeight);
        }
    }

    /**
     * ！！！！！！！！！ 我必须要写注释，千万不要搞混 sceneX 和 screenX ！！！！！！！！！
     */
    private static void setDraggable(){
        primaryStage.getScene().setOnMousePressed(
                me -> {
                    pressedArea = getCursorArea(me.getX(), me.getY(), primaryStage.getWidth(), primaryStage.getHeight());
                    if (pressedArea == CURSOR_AREA.TITLE) {
                        resizing = false;
                        dragging = true;
                        calX = me.getScreenX() - primaryStage.getX();
                        calY = me.getScreenY() - primaryStage.getY();
                    } else if (pressedArea == CURSOR_AREA.CENTER) {
                        resizing = false;
                        dragging = false;
                    } else {
                        resizing = true;
                        dragging = false;
                        oldPosX = primaryStage.getX();
                        oldPosY = primaryStage.getY();
                        oldWidth = primaryStage.getWidth();
                        oldHeight = primaryStage.getHeight();
                        calX = me.getScreenX();
                        calY = me.getScreenY();
                    }
                }
        );
        primaryStage.getScene().setOnMouseDragged(
                me -> {
                    if (dragging) {
                        primaryStage.setX(me.getScreenX() - calX);
                        primaryStage.setY(me.getScreenY() - calY);
                    } else if (resizing) {
                        double dx = me.getScreenX() - calX;
                        double dy = me.getScreenY() - calY;
                        switch (pressedArea) {
                            case NORTH_WEST:
                                setBounds(primaryStage, oldPosX + dx, oldPosY + dy, oldWidth - dx, oldHeight - dy);
                                break;
                            case NORTH_EAST:
                                setBounds(primaryStage, oldPosX, oldPosY + dy, oldWidth + dx, oldHeight - dy);
                                break;
                            case SOUTH_WEST:
                                setBounds(primaryStage, oldPosX + dx, oldPosY, oldWidth - dx, oldHeight + dy);
                                break;
                            case SOUTH_EAST:
                                setBounds(primaryStage, oldPosX, oldPosY, oldWidth + dx, oldHeight + dy);
                                break;
                            case NORTH:
                                setBounds(primaryStage, oldPosX, oldPosY + dy, oldWidth, oldHeight - dy);
                                break;
                            case SOUTH:
                                setBounds(primaryStage, oldPosX, oldPosY, oldWidth, oldHeight + dy);
                                break;
                            case WEST:
                                setBounds(primaryStage, oldPosX + dx, oldPosY, oldWidth - dx, oldHeight);
                                break;
                            case EAST:
                                setBounds(primaryStage, oldPosX, oldPosY, oldWidth + dx, oldHeight);
                                break;
                            case TITLE:
                                break;
                            case CENTER:
                                break;
                        }
                    }
                }
        );
        // change the look of the mouse when it is moved to the sides
        primaryStage.getScene().setOnMouseMoved(
                me -> {
                    CURSOR_AREA area = getCursorArea(me.getX(), me.getY(), primaryStage.getWidth(), primaryStage.getHeight());
                    Cursor cursor = Cursor.DEFAULT;
                    switch (area) {
                        case NORTH_WEST:
                            cursor = Cursor.NW_RESIZE;
                            break;
                        case NORTH_EAST:
                            cursor = Cursor.NE_RESIZE;
                            break;
                        case SOUTH_WEST:
                            cursor = Cursor.SW_RESIZE;
                            break;
                        case SOUTH_EAST:
                            cursor = Cursor.SE_RESIZE;
                            break;
                        case NORTH:
                        case SOUTH:
                            cursor = Cursor.V_RESIZE;
                            break;
                        case WEST:
                        case EAST:
                            cursor = Cursor.H_RESIZE;
                            break;
                        case TITLE:
                        case CENTER:
                            cursor = Cursor.DEFAULT;
                            break;
                    }
                    primaryStage.getScene().setCursor(cursor);
                }
        );
    }
}
