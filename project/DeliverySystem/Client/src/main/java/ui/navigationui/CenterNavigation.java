package ui.navigationui;

import factory.FormFactory;
import javafx.scene.Parent;
import javafx.util.Pair;
import ui.accountui.PersonalAccountViewController;
import ui.common.TabMaker;
import ui.formHistoryui.FormHistoryController;
import ui.messageui.CheckMessageController;
import ui.receiveui.ReceiveFormController;
import ui.transportui.LoadCarController;
import ui.transportui.TransitFormController;

import java.io.IOException;
import java.util.Arrays;

/**
 * Created by Sissel on 2015/12/6.
 */
public class CenterNavigation {
    public static Parent launch() throws IOException {
        Parent node = TabMaker.newLeftTabPane("新建到达单", Arrays.asList(
                new Pair<String, Parent>("新建到达单", ReceiveFormController.launchInNew()),
                new Pair<String, Parent>("新建中转单", TransitFormController.launchInNew()),
                new Pair<String, Parent>("消息通知", CheckMessageController.launch()),
                new Pair<String, Parent>("查看历史单据", FormHistoryController.launch
                        (FormFactory.getStoreOutBLService(), FormFactory.getReceiveBLService()))
        ));

        return node;
    }
}
