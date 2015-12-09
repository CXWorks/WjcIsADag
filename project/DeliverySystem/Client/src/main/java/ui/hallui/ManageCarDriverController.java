package ui.hallui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;






import bl.blService.manageblService.ManageblCarService;
import bl.blService.manageblService.ManageblDriverService;
import factory.CarFactory;
import factory.StaffFactory;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import userinfo.UserInfo;
import vo.managevo.car.CarVO;
import vo.managevo.institution.HallVO;
import vo.managevo.staff.DriverVO;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

/**
 * Created by Sissel on 2015/11/27.
 */
public class ManageCarDriverController {
	public TableView<CarVOCheckItem> car_TableView;
	public TableColumn<CarVOCheckItem,CarVOCheckItem> carCheck_TableColumn;
	public TableColumn<CarVOCheckItem,String> carID_TableColumn;
	public TableColumn<CarVOCheckItem,String> carLicense_TableColumn;
	public TableColumn<CarVOCheckItem,String> serveTime_TableColumn;
	public TableView<DriverVOCheckItem> driver_TableView;
	public TableColumn<DriverVOCheckItem,DriverVOCheckItem> driverCheck_TableColumn;
	public TableColumn<DriverVOCheckItem,String> driverID_TableColumn;
	public TableColumn<DriverVOCheckItem,String> driverName_TableColumn;

	public CheckBox all_Car_CheckBox;
	public CheckBox all_Driver_CheckBox;
	public TextField search_Car_Field;
	public TextField search_Driver_Field;
	public Button back_Btn;

	private ManageblCarService manageblCarService;
	private ManageblDriverService manageblDriverService = StaffFactory.getManageblDriverService();

	private ArrayList<CarVO> carvo_list = manageblCarService.getCar(UserInfo.getInstitutionID());
	private ArrayList<DriverVO> drivervo_list = manageblDriverService.getStaffByInstitution();

	private List<CarVOCheckItem> cars = new ArrayList<CarVOCheckItem>();
	private List<DriverVOCheckItem> drivers = new ArrayList<DriverVOCheckItem>();

	private CarVOCheckItem carVO = new CarVOCheckItem(null);
	private DriverVOCheckItem driverVO = new DriverVOCheckItem(null);

	public static Parent launch(Pane father, Pane before, ManageblCarService service) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ManageCarDriverController.class.getResource("manageCarDriver.fxml"));
		Pane pane = loader.load();
		ManageCarDriverController controller = loader.getController();
		controller.manageblCarService = service;

		if(father == null){
			pane.getChildren().remove(controller.back_Btn);
		}else{
			controller.back_Btn.setOnAction(
					o -> {
						father.getChildren().clear();
						father.getChildren().add(before);}
			);
		}

		return pane;
	}

	@FXML
	public void initialize(){
		for(int i=0;i<carvo_list.size();i++){
			cars.add(new CarVOCheckItem(carvo_list.get(i)));
		}
		for(int j=0;j<drivervo_list.size();j++){
			drivers.add(new DriverVOCheckItem(drivervo_list.get(j)));
		}

		car_TableView.setItems(FXCollections.observableArrayList(cars));

		carID_TableColumn.setCellValueFactory(
				cellData -> new SimpleStringProperty(cellData.getValue().getVo().getCarID())
				);
		carLicense_TableColumn.setCellValueFactory(
				cellData -> new SimpleStringProperty(cellData.getValue().getVo().getNameID())
				);
		serveTime_TableColumn.setCellValueFactory(
				cellData -> new SimpleStringProperty(cellData.getValue().getVo().getUseTime().toString())
				);
		carCheck_TableColumn.setCellFactory(
				o -> new CarTableCell()
				);
		carCheck_TableColumn.setCellValueFactory(
				cellData -> new SimpleObjectProperty<>(cellData.getValue())
				);
		car_TableView.getSelectionModel().selectedItemProperty().addListener(
				(observable, oldValue, newValue) -> {
					System.out.println("selected " + newValue);
					carVO = newValue;
				}
				);

		driver_TableView.setItems(FXCollections.observableArrayList(drivers));

		driverID_TableColumn.setCellValueFactory(
				cellData -> new SimpleStringProperty(cellData.getValue().getVo().getID())
				);
		driverName_TableColumn.setCellValueFactory(
				cellData -> new SimpleStringProperty(cellData.getValue().getVo().getName())
				);

		driverCheck_TableColumn.setCellFactory(
				o -> new DriverTableCell()
				);
		driverCheck_TableColumn.setCellValueFactory(
				cellData -> new SimpleObjectProperty<>(cellData.getValue())
				);

		driver_TableView.getSelectionModel().selectedItemProperty().addListener(
				(observable, oldValue, newValue) -> {
					System.out.println("selected " + newValue);
					driverVO = newValue;
				}
				);
	}

	public void selectAllCar(ActionEvent actionEvent) {
		if((!all_Car_CheckBox.isSelected()) && isAllCarSelected()){
			setAllCarSelectedValue(false);
		}else if(all_Car_CheckBox.isSelected()){
			setAllCarSelectedValue(true);
		}else{
			// do nothing
		}
	}

	public void addCar(ActionEvent actionEvent) throws IOException {
		CarVO car = new CarVO();

		CarNewDialogController controller = CarNewDialogController.newDialog
				(car);

		CarVOCheckItem selected= new CarVOCheckItem(car);
		controller.stage.showAndWait();

		car_TableView.getItems().add(selected);
		manageblCarService.addCar(car);
	}

	@FXML
	public void searchCar(ActionEvent actionEvent) {
		String filter = search_Car_Field.getText();
		CarVO car=manageblCarService.searchCar(filter);
		CarVOCheckItem select = new CarVOCheckItem(car);
		car_TableView.setItems(FXCollections.observableArrayList(select));

		carID_TableColumn.setCellValueFactory(
				cellData -> new SimpleStringProperty(car.getCarID())
				);
		carLicense_TableColumn.setCellValueFactory(
				cellData -> new SimpleStringProperty(car.getNameID())
				);
		serveTime_TableColumn.setCellValueFactory(
				cellData -> new SimpleStringProperty(car.getUseTime().toString())
				);
		carCheck_TableColumn.setCellFactory(
				o -> new CarTableCell()
				);
		carCheck_TableColumn.setCellValueFactory(
				cellData -> new SimpleObjectProperty<>(cellData.getValue())
				);
		car_TableView.getSelectionModel().selectedItemProperty().addListener(
				(observable, oldValue, newValue) -> {
					System.out.println("selected " + newValue);
					carVO = newValue;
				}
				);

	}

	@FXML
	public void deleteCar(ActionEvent actionEvent) {
		for (int i = 0; i < cars.size(); i++) {
			if(cars.get(i).getSelected()){
				cars.remove(i);
				car_TableView.getItems().remove(i);
				manageblCarService.deleteCar(cars.get(i).getVo());
			}
		}
	}

	@FXML
	public void editCar(ActionEvent actionEvent) throws IOException {
		CarVO selected = car_TableView.getSelectionModel().getSelectedItem().getVo();
		CarEditDialogController controller = CarEditDialogController.newDialog
				(selected);

		CarVOCheckItem car= new CarVOCheckItem(selected);
		controller.stage.showAndWait();

		car_TableView.getItems().add(car);

		manageblCarService.modifyCar(selected);
	}

	private boolean isAllCarSelected(){
		for (CarVOCheckItem car : cars) {
			if(!car.getSelected()){
				return false;
			}
		}
		return true;
	}

	private void setAllCarSelectedValue(boolean value){
		for (CarVOCheckItem car : cars) {
			car.setSelected(value);
		}
	}

	private class CarTableCell extends TableCell<CarVOCheckItem, CarVOCheckItem> {
		@Override
		protected void updateItem(CarVOCheckItem item, boolean empty) {
			super.updateItem(item, empty);

			if(item == null || empty){
				return;
			}

			CheckBox checkBox = new CheckBox();
			checkBox.selectedProperty().bindBidirectional(item.selectedProperty());

			setGraphic(checkBox);
		}
	}


	private class DriverTableCell extends TableCell<DriverVOCheckItem, DriverVOCheckItem> {
		@Override
		protected void updateItem(DriverVOCheckItem item, boolean empty) {
			super.updateItem(item, empty);

			if(item == null || empty){
				return;
			}

			CheckBox checkBox = new CheckBox();
			checkBox.selectedProperty().bindBidirectional(item.selectedProperty());

			setGraphic(checkBox);
		}
	}



	public void selectAllDriver(ActionEvent actionEvent) {
		if((!all_Driver_CheckBox.isSelected()) && isAllDriverSelected()){
			setAllDriverSelectedValue(false);
		}else if(all_Driver_CheckBox.isSelected()){
			setAllDriverSelectedValue(true);
		}else{
			// do nothing
		}
	}

	public void addDriver(ActionEvent actionEvent) throws IOException {
		//TODO Problem!!!!!!!!!!!!!!!!
		DriverVO driver = new DriverVO(null);

		DriverNewDialogController controller = DriverNewDialogController.newDialog
				(driver);

		controller.stage.showAndWait();
		DriverVOCheckItem selected= new DriverVOCheckItem(driver);
		driver_TableView.getItems().add(selected);
		manageblDriverService.addStaff(driver);
	}


	@FXML
	public void searchDriver(ActionEvent actionEvent) {
		String filter = search_Driver_Field.getText();
		DriverVO driver =manageblDriverService.searchDriver(filter);
		DriverVOCheckItem selected= new DriverVOCheckItem(driver);

		driver_TableView.setItems(FXCollections.observableArrayList(selected));
		driverID_TableColumn.setCellValueFactory(
				cellData -> new SimpleStringProperty(driver.getID())
				);
		driverName_TableColumn.setCellValueFactory(
				cellData -> new SimpleStringProperty(driver.getName())
				);

		driverCheck_TableColumn.setCellFactory(
				o -> new DriverTableCell()
				);
		driverCheck_TableColumn.setCellValueFactory(
				cellData -> new SimpleObjectProperty<>(cellData.getValue())
				);

		driver_TableView.getSelectionModel().selectedItemProperty().addListener(
				(observable, oldValue, newValue) -> {
					System.out.println("selected " + newValue);
					driverVO = newValue;
				}
				);
	}
	@FXML
	public void deleteDriver(ActionEvent actionEvent) {
		for (int i = 0; i < drivers.size(); i++) {
			if(drivers.get(i).getSelected()){
				drivers.remove(i);
				driver_TableView.getItems().remove(i);
				manageblDriverService.dismissStaff(drivers.get(i).getVo());
			}
		}
	}

	@FXML
	public void editDriver(ActionEvent actionEvent) throws IOException {
		DriverVO selected = driver_TableView.getSelectionModel().getSelectedItem().getVo();
		DriverEditDialogController controller = DriverEditDialogController.newDialog
				(selected);

		controller.stage.showAndWait();
		DriverVOCheckItem driver= new DriverVOCheckItem(selected);


		//以下好像不是这么干的
		driver_TableView.getItems().add(driver);
		manageblDriverService.modifyStaff(selected);
	}

	private boolean isAllDriverSelected(){
		for (DriverVOCheckItem driver : drivers) {
			if(!driver.getSelected()){
				return false;
			}
		}
		return true;
	}

	private void setAllDriverSelectedValue(boolean value){
		for (DriverVOCheckItem driver : drivers) {
			driver.setSelected(value);
		}
	}
}
