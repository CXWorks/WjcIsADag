/**
 * Client//ui.manangeui.salary//ManageSalaryController.java
 * 下午7:52:21
 * @Author CXWorks
 */
package ui.manangeui.salary;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;

import javax.swing.table.TableCellEditor;

import po.InfoEnum;
import vo.configurationvo.ConfigurationVO;
import vo.configurationvo.SalaryStrategyVO;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import bl.blService.configurationblService.ConfigurationBLService;
import factory.ConfigurationFactory;


public class ManageSalaryController {
	public TabPane tabPane;
	
	private ArrayList<SalaryStrategyVO> vo;
	private ObservableList<Tab> tabs;
	//
	private StaffTypeSalaryController staffTypeSalaryController;
	//
	private ConfigurationBLService configurationBLService=ConfigurationFactory.getConfigurationBLService();
	//
	@FXML
	public static Parent launch() throws IOException{
		FXMLLoader fxmlLoader=new FXMLLoader();
		fxmlLoader.setLocation(ManageSalaryController.class.getResource("manageSalaryStrategy.fxml"));
		return fxmlLoader.load();
	}
	//
	@FXML
	public void initialize(){
		//
		tabs=tabPane.getTabs();
//		ArrayList<ConfigurationVO> vo=configurationBLService.get(InfoEnum.SALARY);
//		this.vo=new ArrayList<SalaryStrategyVO>(vo.size());
//		for (ConfigurationVO configurationVO : vo) {
//			this.vo.add((SalaryStrategyVO)configurationVO);
//		}
		//
		try {
			this.seletedChange();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//
	@FXML
	private void seletedChange() throws IOException{
		for (Tab tab : tabs) {
			if (tab.isSelected()) {
				//
//				for (SalaryStrategyVO salaryStrategyVO : vo) {
//					if (salaryStrategyVO.getStaff().getChinese().equalsIgnoreCase(tab.getText())) {
//						staffTypeSalaryController.change(salaryStrategyVO);
						FXMLLoader fxmlLoader=new FXMLLoader();
						fxmlLoader.setLocation(StaffTypeSalaryController.class.getResource("salary.fxml"));
						tab.setContent(fxmlLoader.load());
//					}
//				}
			}
		}
		
	}
}
