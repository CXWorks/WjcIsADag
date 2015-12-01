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
	public Button closeButton;
	public Button modifyButton;
	public Button cancelButton;
	public TextField staff_type;
	public TextField base;
	public TextField commission;
	public TextField bonus;
	public TabPane tabPane;
	private ArrayList<SalaryStrategyVO> vo;
	private ObservableList<Tab> tabs;
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
		//
		this.seletedChange();
		//
//		ArrayList<ConfigurationVO> vo=configurationBLService.get(InfoEnum.SALARY);
//		this.vo=new ArrayList<SalaryStrategyVO>(vo.size());
//		for (ConfigurationVO configurationVO : vo) {
//			this.vo.add((SalaryStrategyVO)configurationVO);
//		}
		//
		
	}
	//
	@FXML
	private void cancel(){
		System.out.println("succeed");
	}
	@FXML
	private void sure(){
		
	}
	//
	@FXML
	private void seletedChange(){
		tabs=tabPane.getTabs();
		for (Tab tab : tabs) {
			if (tab.isSelected()) {
				staff_type.setText(tab.getText());
				base.setText("1000");
				commission.setText("20");
				bonus.setText("1500");
			}
		}
		
	}
}
