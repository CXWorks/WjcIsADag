package ui.manangeui.salary;

import java.io.IOException;

import po.memberdata.StaffTypeEnum;
import factory.ConfigurationFactory;
import bl.blService.configurationblService.ConfigurationBLService;
import vo.configurationvo.SalaryStrategyVO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/** 
 * Client//ui.manangeui.salary//StaffTypeSalaryController.java
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
	public static Parent launch() throws IOException{
		FXMLLoader fxmlLoader=new FXMLLoader();
		fxmlLoader.setLocation(StaffTypeSalaryController.class.getResource("salary.fxml"));
		return fxmlLoader.load();
	}
	//
	public void change(SalaryStrategyVO salaryStrategyVO){
		this.salaryStrategyVO=salaryStrategyVO;
		type.setText(salaryStrategyVO.getStaff().getChinese());
		base.setText(Integer.toString(salaryStrategyVO.getBase()));
		commission.setText(Integer.toString(salaryStrategyVO.getCommission()));
		bonus.setText(Integer.toString(salaryStrategyVO.getBonus()));
		
	}
	//
	@FXML
	private void cancel(){
		//TODO jump back
		this.base.clear();
		this.bonus.clear();
		this.commission.clear();
	}
	@FXML
	private void modify(){
		
		boolean changed=false;
			if (salaryStrategyVO==null) {
				
				salaryStrategyVO=new SalaryStrategyVO();
			}
			int newBase=Integer.parseInt(base.getText());
			if (newBase!=salaryStrategyVO.getBase()) {
				changed=true;
			}
			salaryStrategyVO.setBase(newBase);
			//
			int newCommission=Integer.parseInt(commission.getText());
			if (newCommission!=salaryStrategyVO.getCommission()) {
				changed=true;
			}
			salaryStrategyVO.setCommission(newCommission);
			//
			int newBonus=Integer.parseInt(bonus.getText());
			if (newBonus!=salaryStrategyVO.getBonus()) {
				changed=true;
			}
			salaryStrategyVO.setBonus(newBonus);
			if (changed) {
				ConfigurationBLService configurationBLService=ConfigurationFactory.getConfigurationBLService();
				configurationBLService.modify(salaryStrategyVO);
			}
			else {
				ConfigurationBLService configurationBLService=ConfigurationFactory.getConfigurationBLService();
				System.out.println(configurationBLService.modify(salaryStrategyVO).operationResult);
				
				return;
			}
		
	}
	
	public void setStaffEnum(StaffTypeEnum staffTypeEnum){
		this.nowSelected=staffTypeEnum;
	}
}
