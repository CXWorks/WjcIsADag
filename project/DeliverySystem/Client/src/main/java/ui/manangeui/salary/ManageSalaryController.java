/**
 * Client//ui.manangeui.salary//ManageSalaryController.java
 * 下午7:52:21
 * @Author CXWorks
 */
package ui.manangeui.salary;

import java.io.IOException;
import java.util.ArrayList;

import po.InfoEnum;
import vo.configurationvo.ConfigurationVO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import bl.blService.configurationblService.ConfigurationBLService;
import factory.ConfigurationFactory;

/**
* Client//ui.manangeui.salary//ManageSalaryController.java
* @author CXWorks
* @date 2015年11月30日 下午7:52:21
* @version 1.0
*/
public class ManageSalaryController {
	public Button closeButton;
	public Button modifyButton;
	public Button cancelButton;
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
		ArrayList<ConfigurationVO> vo=configurationBLService.get(InfoEnum.SALARY);
		
	}
	//
	@FXML
	private void cancel(){
		System.out.println("succeed");
	}
	@FXML
	private void sure(){
		
	}
}
