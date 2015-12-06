package ui.navigationui;

import javafx.scene.Parent;
import javafx.util.Pair;
import ui.accountui.ManageAccountController;
import ui.accountui.NewAccountController;
import ui.common.TabMaker;

import java.io.IOException;
import java.util.Arrays;

/**
 * Created by Sissel on 2015/12/6.
 */
public class AdminNavigation {
    public static Parent launch() throws IOException {
        Parent node = TabMaker.makeTabs(Arrays.asList(
                new Pair<String, Parent>("管理账户", ManageAccountController.launch()),
                new Pair<String, Parent>("新建账户", NewAccountController.launch())
        ));

        return node;
    }
}
