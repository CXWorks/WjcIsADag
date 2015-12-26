package ui.navigationui;

import javafx.scene.Parent;
import javafx.util.Pair;
import ui.accountui.PersonalAccountViewController;
import ui.common.TabMaker;
import ui.deliverui.deliverController;
import ui.messageui.CheckMessageController;
import ui.orderui.NewOrderController;
import ui.orderui.PeopleReceiveFormController;
import ui.searchui.CheckLogisticsController;

import java.io.IOException;
import java.util.Arrays;

/**
 * Created by Sissel on 2015/12/6.
 */
public class DeliverNavigation {
    public static Parent launch() throws IOException {
        Parent node = TabMaker.newLeftTabPane(Arrays.asList(
                new Pair<String, Parent>("填写收件单", PeopleReceiveFormController.launch()),
                new Pair<String, Parent>("新建订单", NewOrderController.launch()),
                new Pair<String, Parent>("物流信息查询", CheckLogisticsController.launch()),
                new Pair<String, Parent>("消息通知", CheckMessageController.launch())
        ));

        return node;
    }
}
