package ui.navigationui;

import factory.FormFactory;
import javafx.scene.Parent;
import javafx.util.Pair;
import ui.accountui.PersonalAccountViewController;
import ui.common.TabMaker;
import ui.formHistoryui.FormHistoryController;
import ui.messageui.CheckMessageController;
import ui.storeui.*;

import java.io.IOException;
import java.util.Arrays;

/**
 * Created by Sissel on 2015/12/6.
 */
public class StoreNavigation {
    public static Parent launch() throws IOException {
        Parent node = TabMaker.newLeftTabPane("填写入库单", Arrays.asList(
                new Pair<String, Parent>("填写入库单", StoreInFormController.launchInNew()),
                new Pair<String, Parent>("填写出库单", StoreOutFormController.launch()),
                new Pair<String, Parent>("出入库汇总", StoreSummaryController.launch()),
                new Pair<String, Parent>("分区管理", StorePartitionController.launch()),
                new Pair<String, Parent>("库存盘点", StockTackController.launch()),
                new Pair<String, Parent>("消息通知", CheckMessageController.launch()),
                new Pair<String, Parent>("查看历史单据", FormHistoryController.launch
                        (FormFactory.getStoreInBLService(), FormFactory.getStoreOutBLService()))
        ));

        return node;
    }
}
