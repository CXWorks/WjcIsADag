package ui.navigationui;

import factory.CarFactory;
import factory.StaffFactory;
import javafx.scene.Parent;
import javafx.util.Pair;
import ui.accountui.PersonalAccountViewController;
import ui.common.TabMaker;
import ui.deliverui.deliverController;
import ui.hallui.ManageCarDriverController;
import ui.hallui.RevenueFormController;
import ui.receiveui.ReceiveFormController;
import ui.transportui.LoadCarController;

import java.io.IOException;
import java.util.Arrays;

/**
 * Created by Sissel on 2015/12/6.
 */
public class HallNavigation {
    public static Parent launch() throws IOException {
        Parent node = TabMaker.newLeftTabPane("填写到达单", Arrays.asList(
                new Pair<String, Parent>("管理司机车辆", ManageCarDriverController.launch
                        (null, null, CarFactory.getCarService(), StaffFactory.getManageblDriverService())),
                new Pair<String, Parent>("填写到达单", ReceiveFormController.launch()),
                new Pair<String, Parent>("填写收款单", RevenueFormController.launch()),
                new Pair<String ,Parent>("填写派件单",deliverController.launch()),
                new Pair<String, Parent>("填写装车单", LoadCarController.launch())
        ));

        return node;
    }
}
