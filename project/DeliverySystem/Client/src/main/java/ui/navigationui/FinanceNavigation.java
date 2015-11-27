package ui.navigationui;

import javafx.scene.Parent;
import javafx.util.Pair;
import ui.common.TabMaker;
import ui.financeui.CheckFinanceChartController;
import ui.financeui.CheckFinanceSummaryController;
import ui.financeui.ManageBankAccountController;

import java.io.IOException;
import java.util.Arrays;

/**
 * Created by Sissel on 2015/11/27.
 */
public class FinanceNavigation {

    public static Parent launch() throws IOException {
        Parent node = TabMaker.makeTabs(Arrays.asList(
                new Pair<String, Parent>("财务报表", CheckFinanceChartController.launch()),
                new Pair<String, Parent>("管理账户", ManageBankAccountController.launch())
        ));

        return node;
    }
}
