package ui.navigationui;

import factory.FinanceBLFactory;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.util.Pair;
import ui.accountui.PersonalAccountViewController;
import ui.common.TabMaker;
import ui.financeui.*;
import ui.initui.CheckInitInfoController;
import ui.messageui.CheckMessageController;

import java.io.IOException;
import java.util.Arrays;

/**
 * Created by Sissel on 2015/11/27.
 */
public class FinanceNavigation {

    public static Parent launch() throws IOException {
        Pane initPane = new Pane();
        initPane.getChildren().add(
                CheckInitInfoController.launch(initPane));

        Parent node = TabMaker.newLeftTabPane(Arrays.asList(
                new Pair<String, Parent>("财务报表", CheckFinanceChartController.launch()),
                new Pair<String, Parent>("管理账户",
                        ManageBankAccountController.launch(null, null, FinanceBLFactory.getBankAccountBLService())),
                new Pair<String, Parent>("成本收益表", CheckFinanceSummaryController.launch()),
                new Pair<String, Parent>("新建付款单", PaymentFormController.launch()),
                new Pair<String, Parent>("查看收款单", CheckRevenueFormController.launch()),
                new Pair<String, Parent>("查看系统日志", CheckLogController.launch()),
                new Pair<String, Parent>("查看期初建账", initPane),
                new Pair<String, Parent>("消息通知", CheckMessageController.launch())
        ));

        return node;
    }
}
