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
import ui.common.TabMaker;
import ui.configurationui.ConfigurationController;
import ui.examineui.CheckFormController;
import ui.financeui.CheckFinanceChartController;
import ui.financeui.CheckFinanceSummaryController;
import ui.manangeui.organization.ManageOrganizationController;
import ui.manangeui.salary.ManageSalaryController;
import ui.manangeui.staff.ManageStaffController;

import java.io.IOException;
import java.util.Arrays;

/**
 * Created by Sissel on 2015/12/6.
 */
public class ManagerNavigation {
    public static Parent launch() throws IOException {

        TabPane tabPane = TabMaker.newTabPane();

        TabMaker.addTabs(tabPane, Arrays.asList(
                new Pair<String, Parent>("管理系统常量", ConfigurationController.launch()),
                new Pair<String, Parent>("审批表单", CheckFormController.launch()),
                new Pair<String, Parent>("管理薪水策略", ManageSalaryController.launch()),
                new Pair<String, Parent>("成本收益表", CheckFinanceSummaryController.launch()),
                new Pair<String, Parent>("财务报表", CheckFinanceChartController.launch()),
                new Pair<String, Parent>("个人信息", PersonalAccountViewController.launch())
        ));

        Tab staffTab = new Tab("管理员工");
        ManageStaffController staffController = ManageStaffController.launch(null, null, StaffFactory.getManageService());
        staffTab.setContent(staffController.getSelfPane());

        Tab organizationTab = new Tab("管理机构");
        organizationTab.setContent(ManageOrganizationController.launch
                (null, null, tabPane, staffTab, staffController,
                        InstitutionFactory.getManageblHallService(), InstitutionFactory.getManageblCenterService(),ConfigurationFactory.getConfigurationBLService()));

        tabPane.getTabs().addAll(staffTab, organizationTab);

        return tabPane;
    }
}
