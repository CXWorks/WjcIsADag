package ui.navigationui;

import factory.InstitutionFactory;
import factory.StaffFactory;
import javafx.scene.Parent;
import javafx.util.Pair;
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
        Parent node = TabMaker.makeTabs(Arrays.asList(
                new Pair<String, Parent>("管理系统常量", ConfigurationController.launch()),
                new Pair<String, Parent>("审批表单", CheckFormController.launch()),
                new Pair<String, Parent>("管理机构", ManageOrganizationController.launch
                        (null, null,
                                InstitutionFactory.getManageblHallService(),
                                InstitutionFactory.getManageblCenterService())),
                new Pair<String, Parent>("管理薪水策略", ManageSalaryController.launch()),
                new Pair<String, Parent>("管理员工", ManageStaffController.launch
                        (null, null, StaffFactory.getManageService())),
                new Pair<String, Parent>("成本收益表", CheckFinanceSummaryController.launch()),
                new Pair<String, Parent>("财务报表", CheckFinanceChartController.launch())
        ));

        return node;
    }
}
