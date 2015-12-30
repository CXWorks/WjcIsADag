package ui.navigationui;

import factory.CarFactory;
import factory.FormFactory;
import factory.StaffFactory;
import javafx.scene.Parent;
import javafx.util.Pair;
import ui.accountui.PersonalAccountViewController;
import ui.common.TabMaker;
import ui.deliverui.deliverController;
import ui.formHistoryui.FormHistoryController;
import ui.hallui.ManageCarDriverController;
import ui.hallui.RevenueFormController;
import ui.messageui.CheckMessageController;
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
                new Pair<String, Parent>("填写到达单", ReceiveFormController.launchInNew()),
                new Pair<String, Parent>("填写收款单", RevenueFormController.launch()),
                new Pair<String ,Parent>("填写派件单",deliverController.launch()),
                new Pair<String, Parent>("填写装车单", LoadCarController.launchInNew()),
                new Pair<String, Parent>("查看历史单据", FormHistoryController.launch
                        (FormFactory.getTransportHallBLService(), FormFactory.getReceiveBLService())),
                new Pair<String, Parent>("消息通知", CheckMessageController.launch())
        ));

        return node;
    }
}
