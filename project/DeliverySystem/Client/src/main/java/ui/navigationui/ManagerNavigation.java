package ui.navigationui;

import factory.ConfigurationFactory;
import factory.InstitutionFactory;
import factory.StaffFactory;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.util.Pair;
import ui.accountui.PersonalAccountViewController;
import ui.common.MainPaneController;
import ui.common.TabMaker;
import ui.configurationui.ConfigurationController;
import ui.examineui.CheckFormController;
import ui.financeui.CheckFinanceChartController;
import ui.financeui.CheckFinanceSummaryController;
import ui.manangeui.organization.ManageOrganizationController;
import ui.manangeui.salary.ManageSalaryController;
import ui.manangeui.staff.ManageStaffController;
import ui.messageui.CheckMessageController;

import java.io.IOException;
import java.util.Arrays;

/**
 * Created by Sissel on 2015/12/6.
 */
public class ManagerNavigation {
    public static Parent launch() throws IOException {

        MainPaneController mpc = TabMaker.newMainPaneController();

        TabMaker.addLeftTabs(mpc, Arrays.asList(
                new Pair<String, Parent>("管理系统常量", ConfigurationController.launch()),
                new Pair<String, Parent>("审批表单", CheckFormController.launch()),
                new Pair<String, Parent>("管理薪水策略", ManageSalaryController.launch()),
                new Pair<String, Parent>("成本收益表", CheckFinanceSummaryController.launch()),
                new Pair<String, Parent>("财务报表", CheckFinanceChartController.launch()),
                new Pair<String, Parent>("消息通知", CheckMessageController.launch())
        ));

        ManageStaffController staffController = ManageStaffController.launchInManage();
        TabMaker.addLeftTab(mpc, new Pair<String, Parent>("管理员工", staffController.getSelfPane()));

        Tab organizationTab = new Tab("管理机构");
        organizationTab.setContent(ManageOrganizationController.launch
                (null, null, staffController, mpc,
                        InstitutionFactory.getManageblHallService(), InstitutionFactory.getManageblCenterService(), ConfigurationFactory.getConfigurationBLService()));

        return mpc.getOuterPane();
    }
}
