package ui.configurationui;

import java.io.IOException;
import java.util.ArrayList;

import po.InfoEnum;
import po.configurationdata.enums.PackEnum;
import po.orderdata.DeliverTypeEnum;
import bl.blService.configurationblService.ConfigurationBLService;
import factory.ConfigurationFactory;
import factory.LoginFactory;
import ui.loginui.LoginController;
import util.R.string;
import vo.configurationvo.City2DVO;
import vo.configurationvo.CityDistanceVO;
import vo.configurationvo.ConfigurationVO;
import vo.configurationvo.PackVO;
import vo.configurationvo.PriceVO;
import vo.configurationvo.ProportionVO;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Tab;



//快递费、收费比例、城市距离、包装费、薪资 

public class ConfigurationController {
	public TabPane tabPane;

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
	public TextField factor_Field; //后面的那个比例（原来是23）
	
	//收费比例
	public TextField slow_Field;
	public TextField normal_Field;
	public TextField fast_Field;
	
	//包装费
	public TextField paper_Field;
	public TextField wood_Field;
	public TextField bag_Field;
	
	ConfigurationBLService configurationBLService= ConfigurationFactory.getConfigurationBLService();
	public static Parent launch() throws IOException {
        return FXMLLoader.load(LoginController.class.getResource("configuration.fxml"));
    }
	
    public void initialize(){
    	//one_Two_Field.setText();
    	this.selectedChanged();
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
		ObservableList<Tab> tabs=tabPane.getTabs();
		for (Tab tab : tabs) {
			if (tab.isSelected()) {
				String text=tab.getText();
				switch (text) {
				case "城市距离":
					this.initializeDistance();
					break;
				case "快递费":
					this.initializeDeliverFee();
					break;
				case "收费比例":
					this.initializeProportion();
					break;
				case "包装费":
					this.initializePack();
					break;

				default:
					break;
				}
			}
		}
	}
	//
	private void initializeDistance(){
		ArrayList<ConfigurationVO> configurationVOs=configurationBLService.get(InfoEnum.CITY_2D);
		ArrayList<City2DVO> vo=new ArrayList<City2DVO>(configurationVOs.size());
		for (ConfigurationVO configurationVO : configurationVOs) {
			City2DVO city2dvo=(City2DVO)configurationVO;
			vo.add(city2dvo);
		}
		City2DVO city1=vo.get(0);
		City2DVO city2=vo.get(1);
		//
		two_One_Label.setText(Double.toString(city1.distance(city2)));
		city2=vo.get(2);
		three_One_Label.setText(Double.toString(city1.distance(city2)));
		city2=vo.get(3);
		four_One_Label.setText(Double.toString(city1.distance(city2)));
		//
		city1=vo.get(1);
		city2=vo.get(2);
		three_Two_Label.setText(Double.toString(city1.distance(city2)));
		city2=vo.get(3);
		four_Two_Label.setText(Double.toString(city1.distance(city2)));
		city1=vo.get(2);
		four_Three_Label.setText(Double.toString(city1.distance(city2)));
	}
	//
	private void initializeDeliverFee(){
		ArrayList<ConfigurationVO> configurationVOs=configurationBLService.get(InfoEnum.PRICE);
		PriceVO priceVO=(PriceVO)configurationVOs.get(0);
		factor_Field.setText(Integer.toString(priceVO.getByType(DeliverTypeEnum.NORMAL)));
	}
	//
	private void initializeProportion(){
		ArrayList<ConfigurationVO> configurationVOs=configurationBLService.get(InfoEnum.PROPORTION);
		ProportionVO proportionVO=(ProportionVO)configurationVOs.get(0);
		slow_Field.setText(Integer.toString(proportionVO.getByType(DeliverTypeEnum.SLOW)));
		normal_Field.setText(Integer.toString(proportionVO.getByType(DeliverTypeEnum.NORMAL)));
		fast_Field.setText(Integer.toString(proportionVO.getByType(DeliverTypeEnum.FAST)));
	}
	//
	private void initializePack(){
		ArrayList<ConfigurationVO> configurationVOs=configurationBLService.get(InfoEnum.PACK);
		PackVO packVO=(PackVO)configurationVOs.get(0);
		paper_Field.setText(Double.toString(packVO.getByType(PackEnum.PAPER)));
		wood_Field.setText(Double.toString(packVO.getByType(PackEnum.WOOD)));
		bag_Field.setText(Double.toString(packVO.getByType(PackEnum.PACKAGE)));
	}
}
