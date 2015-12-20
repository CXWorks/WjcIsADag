package ui.navigationui;

import javafx.scene.Parent;
import javafx.util.Pair;
import ui.accountui.PersonalAccountViewController;
import ui.common.TabMaker;
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
        Parent node = TabMaker.newLeftTabPane(Arrays.asList(
                new Pair<String, Parent>("新建到达单", ReceiveFormController.launch()),
                new Pair<String, Parent>("新建装车单", LoadCarController.launch()),
                new Pair<String, Parent>("新建中转单", TransitFormController.launch()),
                new Pair<String, Parent>("个人信息", PersonalAccountViewController.launch())
        ));

        return node;
    }
}
