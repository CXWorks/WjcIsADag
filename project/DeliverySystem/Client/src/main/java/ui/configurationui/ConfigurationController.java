package ui.configurationui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



import java.util.stream.Collectors;

import com.sun.org.apache.bcel.internal.generic.RETURN;





import po.InfoEnum;
import po.configurationdata.enums.PackEnum;
import po.orderdata.DeliverTypeEnum;
import ui.hallui.RevenueFormController;
import ui.informui.InformController;
import bl.blService.configurationblService.ConfigurationBLService;
import factory.ConfigurationFactory;
import vo.configurationvo.City2DVO;
import vo.configurationvo.ConfigurationVO;
import vo.configurationvo.PackVO;
import vo.configurationvo.PriceVO;
import vo.configurationvo.ProportionVO;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.control.Tab;
import javafx.scene.layout.GridPane;



//快递费、收费比例、城市距离、包装费、薪资

public class ConfigurationController {
	public TabPane tabPane;

	//标签
	public Tab distance_Tab;
	public Tab expense_Tab;
	public Tab pack_Tab;
	public Tab proportion_Tab;
	
	//城市距离
	public TableView<City2DVO> cityTableView;
	public TableColumn<City2DVO, String> cityNameColumn;
	public TableColumn<City2DVO, String> cityIDColumn;
	public TableColumn<City2DVO, String> cityLocationcColumn;
	public TextField cityNameTextField;
	public TextField cityIDTextField;
	public TextField cityXTextField;
	public TextField cityYTextField;
	public ChoiceBox<City2DVO> cityChoiceBox;
	public Label cityDistanceLabel;

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
	//
	private PriceVO priceVO;
	private ProportionVO proportionVO;
	private PackVO packVO;
	private List<City2DVO> city2dvos;
	private City2DVO city1;
	private City2DVO city2;

	private InformController informController;

	ConfigurationBLService configurationBLService= ConfigurationFactory.getConfigurationBLService();
	public static Parent launch() throws IOException {
        FXMLLoader loader = new FXMLLoader(ConfigurationController.class.getResource("configuration.fxml"));
        Pane pane = loader.load();
        ConfigurationController controller = loader.getController();
        controller.informController = InformController.newInformController(pane);

        return controller.informController.stackPane;
    }

    public void initialize(){
    	this.selectedChanged();
    }

    private City2DVO makeCity2DVO(String src,String name){
    	int cut=src.indexOf(',');
    	double x=Double.parseDouble(src.substring(0, cut));
    	double y=Double.parseDouble(src.substring(cut+1, src.length()));
    	//TODO 从界面获取城市编号
    	return new City2DVO(name, x, y,"ID");
    }

    //调整快递费
	public void submitExpense(){
		String expense=factor_Field.getText();
		try {
			int price=Integer.parseInt(expense);
			priceVO.setByType(DeliverTypeEnum.NORMAL, price);
			configurationBLService.modify(priceVO);
		} catch (Exception e) {
			// TODO: handle exception
		}
		finally{
			//TODO jump backs
		}

	}
    //调整包装费
	public void submitPack(){
		String papers=paper_Field.getText();
		String bags=bag_Field.getText();
		String woods=wood_Field.getText();
		try {
			double paper=Double.parseDouble(papers);
			double bag=Double.parseDouble(bags);
			double wood=Double.parseDouble(woods);
			packVO.setByType(PackEnum.WOOD, wood);
			packVO.setByType(PackEnum.PACKAGE, bag);
			packVO.setByType(PackEnum.PAPER, paper);
			configurationBLService.modify(packVO);

		} catch (Exception e) {
			// TODO: handle exception
		}
		//TODO jump back
	}
	//调整收费比例
	public void submitProportion(){
		String fasts=fast_Field.getText();
		String normals=normal_Field.getText();
		String slows=slow_Field.getText();
		try {
			int fast=Integer.parseInt(fasts);
			int normal=Integer.parseInt(normals);
			int slow=Integer.parseInt(slows);
			proportionVO.setByType(DeliverTypeEnum.FAST, fast);
			proportionVO.setByType(DeliverTypeEnum.NORMAL, normal);
			proportionVO.setByType(DeliverTypeEnum.SLOW, slow);
			configurationBLService.modify(proportionVO);
		} catch (Exception e) {
			// TODO: handle exception
		}
		//TODO jump back
	}
	//
	public void selectedChanged(){
		ObservableList<Tab> tabs=tabPane.getTabs();
//		if (true) {
//			return;
//		}
		for (Tab tab : tabs) {
			if (tab.isSelected()) {
				String text=tab.getText();
				switch (text) {
				case "城市距离":
					this.initializeDistance();
					break;
				case "快递费":
					this.initializePrice();
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
	private void initChoiceBox(){
		this.cityChoiceBox.getSelectionModel().clearSelection();
		this.cityChoiceBox.getItems().clear();
		
	}
	//
	private void initializeDistance(){
		this.city2dvos=configurationBLService.getCity();
		cityTableView.setItems(FXCollections.observableList(this.city2dvos));
		cityNameColumn.setCellValueFactory(cell->new SimpleStringProperty(cell.getValue().getName()));
		cityIDColumn.setCellValueFactory(cell->new SimpleStringProperty(cell.getValue().getID()));
		cityLocationcColumn.setCellValueFactory(cell->new SimpleStringProperty(cell.getValue().getXY()));
		cityTableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		cityTableView.getSelectionModel().selectFirst();
		cityTableView.getSelectionModel().selectedItemProperty().addListener(
				(obser,old,New)->{
					this.initChoiceBox();
						this.city1=New;
						if (city1!=null) {
							this.setCityInfo(city1);
						}
						
		});
		
	}
	//
	private void setCityInfo(City2DVO city2dvo){
		this.cityNameTextField.setText(city2dvo.getName());
		this.cityIDTextField.setText(city2dvo.getID());
		this.cityXTextField.setText(Double.toString(city2dvo.getX()));
		this.cityYTextField.setText(Double.toString(city2dvo.getY()));
		//
		List<City2DVO> chosable=city2dvos.stream().filter(city->{
			return !city.getID().equalsIgnoreCase(city2dvo.getID());
		}).collect(Collectors.toList());
		this.cityChoiceBox.setItems(FXCollections.observableList(chosable));
		this.cityChoiceBox.getSelectionModel().selectedItemProperty().addListener((obser,old,New)->{
			this.city2=New;
			if (city1!=null) {
				this.cityDistanceLabel.setText(Double.toString(city1.distance(city2)));
			}
			
		});
	}
	//
	private void initializePrice(){
		ArrayList<ConfigurationVO> configurationVOs=configurationBLService.get(InfoEnum.PRICE);
		priceVO=(PriceVO)configurationVOs.get(0);
		factor_Field.setText(Integer.toString(priceVO.getByType(DeliverTypeEnum.FAST)));
	}
	//
	private void initializeProportion(){
		ArrayList<ConfigurationVO> configurationVOs=configurationBLService.get(InfoEnum.PROPORTION);
		proportionVO=(ProportionVO)configurationVOs.get(0);
		slow_Field.setText(Integer.toString(proportionVO.getByType(DeliverTypeEnum.SLOW)));
		normal_Field.setText(Integer.toString(proportionVO.getByType(DeliverTypeEnum.NORMAL)));
		fast_Field.setText(Integer.toString(proportionVO.getByType(DeliverTypeEnum.FAST)));
	}
	//
	private void initializePack(){
		ArrayList<ConfigurationVO> configurationVOs=configurationBLService.get(InfoEnum.PACK);
		packVO=(PackVO)configurationVOs.get(0);
		paper_Field.setText(Double.toString(packVO.getByType(PackEnum.PAPER)));
		wood_Field.setText(Double.toString(packVO.getByType(PackEnum.WOOD)));
		bag_Field.setText(Double.toString(packVO.getByType(PackEnum.PACKAGE)));
	}
	public void clearCity(){
		this.cityNameTextField.clear();
		this.cityIDTextField.clear();
		this.cityXTextField.clear();
		this.cityYTextField.clear();
		this.city1=null;
		this.city2=null;
	}
	//
	public void deleteCity(){
		configurationBLService.deleteCity(city1);
		this.initializeDistance();
	}
	private City2DVO generateCity(){
		String cityName=cityNameTextField.getText();
		String cityID=cityIDTextField.getText();
		double x=Double.parseDouble(cityXTextField.getText());
		double y=Double.parseDouble(cityYTextField.getText());
		return new City2DVO(cityName, x, y, cityID);
	}
	public void submitCity(){
		City2DVO city2dvo=this.generateCity();
		boolean isNew=!city2dvos.stream().anyMatch(city->{
			return city.getID().equalsIgnoreCase(city2dvo.getID())||city.getName().equalsIgnoreCase(city2dvo.getName());
		});
		if (isNew) {
			configurationBLService.newCity(city2dvo);
		}else {
			configurationBLService.modifyCity(city2dvo);
		}
		this.initializeDistance();
	}
}
