package ui.navigationui;

import javafx.scene.Parent;
import javafx.util.Pair;
import ui.common.TabMaker;
import ui.deliverui.deliverController;
import ui.orderui.NewOrderController;
import ui.orderui.PoepleReceiveFormController;

import java.io.IOException;
import java.util.Arrays;

/**
 * Created by Sissel on 2015/12/6.
 */
public class DeliverNavigation {
    public static Parent launch() throws IOException {
        Parent node = TabMaker.makeTabs(Arrays.asList(
                new Pair<String, Parent>("填写收件单", PoepleReceiveFormController.launch()),
                new Pair<String, Parent>("查看派送单", deliverController.launch()),
                new Pair<String, Parent>("新建订单", NewOrderController.launch())
        ));

        return node;
    }
}
