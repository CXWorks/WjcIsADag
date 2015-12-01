package ui.configurationui;

import java.io.IOException;

import bl.blService.configurationblService.ConfigurationBLService;
import factory.ConfigurationFactory;
import factory.LoginFactory;
import ui.loginui.LoginController;
import vo.configurationvo.CityDistanceVO;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tab;



//快递费、收费比例、城市距离、包装费、薪资 

public class ConfigurationController {

	//标签
	public Tab distance_Tab;
	public Tab expense_Tab;
	public Tab pack_Tab;
	public Tab proportion_Tab;
	
	//城市距离
	public TextField one_Two_Field;  
	public TextField one_Three_Field;
	public TextField one_Four_Field;
	public TextField two_Three_Field;
	public TextField two_Four_Field;
	public TextField three_Four_Field;
	
	public Label two_One_Label;
	public Label three_One_Label;
	public Label three_Two_Label;
	public Label four_One_Label;
	public Label four_Two_Label;
	public Label four_Three_Label;
	
	//快递费
	public TextField divisor_Field;//下面那个除数（原来是1000）
	public TextField factor_Field; //后面的那个比例（原来是23）
	
	//收费比例
	public TextField slow_Field;
	public TextField normal_Field;
	public TextField fast_Field;
	
	//包装费
	public TextField paper_Field;
	public TextField wood_Field;
	public TextField bag_Field;
	
	ConfigurationBLService cdbl= ConfigurationFactory.getConfigurationBLService();
	public static Parent launch() throws IOException {
        return FXMLLoader.load(LoginController.class.getResource("configuration.fxml"));
    }
	
    public void initialize(){
    	//one_Two_Field.setText();
    }
	
	
	
	//调整城市距离
	public void submitDistance(){
		
	}
	
    //调整快递费
	public void submitExpense(){
		
	}
    //调整包装费
	public void submitPack(){
		
	}
	//调整收费比例
	public void submitProportion(){
		
	}
	//
	public void selectedChanged(){
		
	}
	
}
