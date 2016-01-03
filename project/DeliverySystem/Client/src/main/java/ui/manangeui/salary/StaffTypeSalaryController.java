package ui.manangeui.salary;

import java.io.IOException;

import po.memberdata.StaffTypeEnum;
import ui.common.checkFormat.FormatCheckQueue;
import ui.common.checkFormat.field.CheckIsNullTasker;
import ui.hallui.RevenueFormController;
import ui.informui.InformController;
import factory.ConfigurationFactory;
import bl.blService.configurationblService.ConfigurationBLService;
import vo.configurationvo.SalaryStrategyVO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

/**
 * Client//ui.manangeui.salary//StaffTypeSalaryController.java
 *
 * @author CXWorks
 * @date 2015年12月1日 下午10:37:28
 * @version 1.0
 */
public class StaffTypeSalaryController {
	public Label type;
	public TextField base;
	public TextField commission;
	public TextField bonus;
	//
	private SalaryStrategyVO salaryStrategyVO;
	private StaffTypeEnum nowSelected;
	//
	private InformController informController;
	public Pane selfPane;
	private FormatCheckQueue formatCheckQueue;

	public static StaffTypeSalaryController launch() throws IOException {
		FXMLLoader loader = new FXMLLoader(StaffTypeSalaryController.class.getResource("salary.fxml"));
		Pane pane = loader.load();
		StaffTypeSalaryController controller = loader.getController();
		controller.informController = InformController.newInformController(pane);
		//init check
		controller.formatCheckQueue=new FormatCheckQueue();
		controller.formatCheckQueue.addTasker(new CheckIsNullTasker(controller.base),
				new CheckIsNullTasker(controller.bonus),
				new CheckIsNullTasker(controller.commission)
		);
        controller.selfPane = controller.informController.stackPane;

		return controller;
	}



	//
	public void change(SalaryStrategyVO salaryStrategyVO) {
		// check
		if (formatCheckQueue!=null&&!formatCheckQueue.startCheck()) {
			return;
		}
		this.salaryStrategyVO = salaryStrategyVO;
		type.setText(salaryStrategyVO.getStaff().getChinese());
		base.setText(Integer.toString(salaryStrategyVO.getBase()));
		commission.setText(Integer.toString(salaryStrategyVO.getCommission()));
		bonus.setText(Integer.toString(salaryStrategyVO.getBonus()));
	}

	//
	@FXML
	private void cancel() {
		// TODO jump back
		this.base.clear();
		this.bonus.clear();
		this.commission.clear();
	}

	@FXML
	private void modify() {
		//check
		if (!formatCheckQueue.startCheck()) {
			return;
		}

		boolean changed = false;
		if (salaryStrategyVO == null) {

			salaryStrategyVO = new SalaryStrategyVO();
			salaryStrategyVO.setStaff(nowSelected);
		}
		int newBase = Integer.parseInt(base.getText());
		if (newBase != salaryStrategyVO.getBase()) {
			changed = true;
		}
		salaryStrategyVO.setBase(newBase);
		//
		int newCommission = Integer.parseInt(commission.getText());
		if (newCommission != salaryStrategyVO.getCommission()) {
			changed = true;
		}
		salaryStrategyVO.setCommission(newCommission);
		//
		int newBonus = Integer.parseInt(bonus.getText());
		if (newBonus != salaryStrategyVO.getBonus()) {
			changed = true;
		}
		salaryStrategyVO.setBonus(newBonus);
		if (changed) {
			ConfigurationBLService configurationBLService = ConfigurationFactory.getConfigurationBLService();
			configurationBLService.modify(salaryStrategyVO);
		} else {
			ConfigurationBLService configurationBLService = ConfigurationFactory.getConfigurationBLService();
			System.out.println(configurationBLService.modify(salaryStrategyVO).operationResult);

			return;
		}

	}

	public void setStaffEnum(StaffTypeEnum staffTypeEnum) {
		this.nowSelected = staffTypeEnum;
	}
}
