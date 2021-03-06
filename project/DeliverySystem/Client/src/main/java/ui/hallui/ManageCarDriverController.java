package ui.hallui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import message.OperationMessage;
import tool.time.TimeConvert;
import ui.informui.InformController;
import bl.blService.manageblService.ManageblCarService;
import bl.blService.manageblService.ManageblDriverService;
import factory.CarFactory;
import factory.StaffFactory;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import userinfo.UserInfo;
import vo.managevo.car.CarVO;
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
	public TableView<CarAbstractCheckItem> car_TableView;
	public TableColumn<CarAbstractCheckItem, CarAbstractCheckItem> carCheck_TableColumn;
	public TableColumn<CarAbstractCheckItem, String> carID_TableColumn;
	public TableColumn<CarAbstractCheckItem, String> carLicense_TableColumn;
	public TableColumn<CarAbstractCheckItem, String> serveTime_TableColumn;
	public TableView<DriverAbstractCheckItem> driver_TableView;
	public TableColumn<DriverAbstractCheckItem, DriverAbstractCheckItem> driverCheck_TableColumn;
	public TableColumn<DriverAbstractCheckItem, String> driverID_TableColumn;
	public TableColumn<DriverAbstractCheckItem, String> driverName_TableColumn;

	public CheckBox all_Car_CheckBox;
	public CheckBox all_Driver_CheckBox;
	public TextField search_Car_Field;
	public TextField search_Driver_Field;
	public Button back_Btn;

	private ManageblCarService manageblCarService;
	private ManageblDriverService manageblDriverService;

	private ArrayList<CarVO> carvo_list;
	private ArrayList<DriverVO> drivervo_list;

	private List<CarAbstractCheckItem> cars = new ArrayList<CarAbstractCheckItem>();
	private List<DriverAbstractCheckItem> drivers = new ArrayList<DriverAbstractCheckItem>();

	private CarAbstractCheckItem carVO = new CarAbstractCheckItem(null);
	private DriverAbstractCheckItem driverVO = new DriverAbstractCheckItem(null);
	private TimeConvert timeConvert = new TimeConvert();
	private InformController informController;

	public static Parent launch(Pane father, Pane before, ManageblCarService carService,
			ManageblDriverService driverService) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ManageCarDriverController.class.getResource("manageCarDriver.fxml"));
		Pane pane = loader.load();

		ManageCarDriverController controller = loader.getController();
		controller.informController = InformController.newInformController(pane);

		controller.manageblCarService = carService;
		controller.manageblDriverService = driverService;

		if (father == null) {
			pane.getChildren().remove(controller.back_Btn);
			controller.back_Btn.setVisible(false);
		} else {
			controller.back_Btn.setOnAction(o -> {
				father.getChildren().clear();
				father.getChildren().add(before);
			});
		}

		controller.refresh();

		return controller.informController.stackPane;
	}

	@FXML
	// TODO test jump : change the name
	public void initialize() {

		car_TableView.setItems(FXCollections.observableArrayList(cars));
		carID_TableColumn
				.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getVo().getCarID()));
		carLicense_TableColumn
				.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getVo().getNameID()));
		serveTime_TableColumn
				.setCellValueFactory(cellData -> new SimpleStringProperty(timeConvert.getDisplayDate(cellData.getValue().getVo().getUseTime())));
		carCheck_TableColumn.setCellFactory(o -> new CarTableCell());
		carCheck_TableColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue()));
		car_TableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			System.out.println("selected " + newValue);
			carVO = newValue;
		});

		driver_TableView.setItems(FXCollections.observableArrayList(drivers));
		driverID_TableColumn
				.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getVo().getID()));
		driverName_TableColumn
				.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getVo().getName()));

		driverCheck_TableColumn.setCellFactory(o -> new DriverTableCell());
		driverCheck_TableColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue()));

		driver_TableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			System.out.println("selected " + newValue);
			driverVO = newValue;
		});

	}

	public void refresh() {
		car_TableView.getItems().clear();
		driver_TableView.getItems().clear();
		carvo_list = manageblCarService.getCar(UserInfo.getInstitutionID());
		drivervo_list = manageblDriverService.getStaffByInstitution();
		cars.clear();
		drivers.clear();
		for (int i = 0; i < carvo_list.size(); i++) {
			cars.add(new CarAbstractCheckItem(carvo_list.get(i)));
		}
		for (int j = 0; j < drivervo_list.size(); j++) {
			drivers.add(new DriverAbstractCheckItem(drivervo_list.get(j)));
		}

		initialize();

	}

	public void selectAllCar(ActionEvent actionEvent) {
		if ((!all_Car_CheckBox.isSelected()) && isAllCarSelected()) {
			setAllCarSelectedValue(false);
		} else if (all_Car_CheckBox.isSelected()) {
			setAllCarSelectedValue(true);
		} else {
			// do nothing
		}
	}

	public void addCar(ActionEvent actionEvent) throws IOException {
		CarVO car = new CarVO();

		CarNewDialogController controller = CarNewDialogController.newDialog(car);

		CarAbstractCheckItem selected = new CarAbstractCheckItem(car);
		controller.stage.showAndWait();

		;
		OperationMessage msg = manageblCarService.addCar(car);
		refresh();
		informController.inform(msg, "新增车辆成功");
	}

	@FXML
	public void searchCar(ActionEvent actionEvent) {
		String filter = search_Car_Field.getText();
		if (filter.equals(null)) {
			refresh();
		}
		CarVO car = manageblCarService.searchCar(filter);
		CarAbstractCheckItem select = new CarAbstractCheckItem(car);
		car_TableView.getItems().clear();
		car_TableView.setItems(FXCollections.observableArrayList(select));

		carID_TableColumn.setCellValueFactory(cellData -> new SimpleStringProperty(car.getCarID()));
		carLicense_TableColumn.setCellValueFactory(cellData -> new SimpleStringProperty(car.getNameID()));
		serveTime_TableColumn.setCellValueFactory(cellData -> new SimpleStringProperty(car.getUseTime().toString()));
		carCheck_TableColumn.setCellFactory(o -> new CarTableCell());
		carCheck_TableColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue()));
		car_TableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			System.out.println("selected " + newValue);
			carVO = newValue;
		});

	}

	@FXML
	public void deleteCar(ActionEvent actionEvent) {
		for (int i = 0; i < cars.size(); i++) {
			if (cars.get(i).getSelected()) {
				cars.remove(i);
				// car_TableView.getItems().remove(i);

				OperationMessage msg = manageblCarService.deleteCar(cars.get(i).getVo());
                refresh();
                informController.inform(msg, "删除车辆成功");
			}
		}
	}

	@FXML
	public void editCar(ActionEvent actionEvent) throws IOException {
		CarVO selected = car_TableView.getSelectionModel().getSelectedItem().getVo();
		CarEditDialogController controller = CarEditDialogController.newDialog(selected);

		CarAbstractCheckItem car = new CarAbstractCheckItem(selected);
		controller.stage.showAndWait();

		// car_TableView.getItems().add(car);

		OperationMessage msg = manageblCarService.modifyCar(selected);
		refresh();
        informController.inform(msg, "修改车辆信息成功");
	}

	private boolean isAllCarSelected() {
		for (CarAbstractCheckItem car : cars) {
			if (!car.getSelected()) {
				return false;
			}
		}
		return true;
	}

	private void setAllCarSelectedValue(boolean value) {
		for (CarAbstractCheckItem car : cars) {
			car.setSelected(value);
		}
	}

	private class CarTableCell extends TableCell<CarAbstractCheckItem, CarAbstractCheckItem> {
		@Override
		protected void updateItem(CarAbstractCheckItem item, boolean empty) {
			super.updateItem(item, empty);

			if (item == null || empty) {
				return;
			}

			CheckBox checkBox = new CheckBox();
			checkBox.selectedProperty().bindBidirectional(item.selectedProperty());

			setGraphic(checkBox);
		}
	}

	private class DriverTableCell extends TableCell<DriverAbstractCheckItem, DriverAbstractCheckItem> {
		@Override
		protected void updateItem(DriverAbstractCheckItem item, boolean empty) {
			super.updateItem(item, empty);

			if (item == null || empty) {
				return;
			}

			CheckBox checkBox = new CheckBox();
			checkBox.selectedProperty().bindBidirectional(item.selectedProperty());

			setGraphic(checkBox);
		}
	}

	public void selectAllDriver(ActionEvent actionEvent) {
		if ((!all_Driver_CheckBox.isSelected()) && isAllDriverSelected()) {
			setAllDriverSelectedValue(false);
		} else if (all_Driver_CheckBox.isSelected()) {
			setAllDriverSelectedValue(true);
		} else {
			// do nothing
		}
	}

	public void addDriver(ActionEvent actionEvent) throws IOException {
		DriverVO driver = new DriverVO();

		DriverNewDialogController controller = DriverNewDialogController.newDialog(driver);

		controller.stage.showAndWait();
		DriverAbstractCheckItem selected = new DriverAbstractCheckItem(driver);

		// driver_TableView.getItems().add(selected);

		OperationMessage msg = manageblDriverService.addStaff(driver);
        refresh();
        informController.inform(msg, "新增司机成功");
	}

	@FXML
	public void searchDriver(ActionEvent actionEvent) {
		String filter = search_Driver_Field.getText();
		if (filter.equals(null)) {
			refresh();
		}
		DriverVO driver = manageblDriverService.searchDriver(filter);
		DriverAbstractCheckItem selected = new DriverAbstractCheckItem(driver);

		driver_TableView.getItems().clear();
		driver_TableView.setItems(FXCollections.observableArrayList(selected));
		driverID_TableColumn.setCellValueFactory(cellData -> new SimpleStringProperty(driver.getID()));
		driverName_TableColumn.setCellValueFactory(cellData -> new SimpleStringProperty(driver.getName()));

		driverCheck_TableColumn.setCellFactory(o -> new DriverTableCell());
		driverCheck_TableColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue()));

		driver_TableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			System.out.println("selected " + newValue);
			driverVO = newValue;
		});
	}

	@FXML
	public void deleteDriver(ActionEvent actionEvent) {
		for (int i = 0; i < drivers.size(); i++) {
			if (drivers.get(i).getSelected()) {
				System.out.println("I saw ya !!!"+drivers.get(i).getVo().getName());
				OperationMessage msg = manageblDriverService.dismissStaff(drivers.get(i).getVo());
                refresh();
                informController.inform(msg, "删除司机成功");
			}
		}
	}

	@FXML
	public void editDriver(ActionEvent actionEvent) throws IOException {
		DriverVO selected = driver_TableView.getSelectionModel().getSelectedItem().getVo();
		DriverEditDialogController controller = DriverEditDialogController.newDialog(selected);

		controller.stage.showAndWait();

		OperationMessage msg = manageblDriverService.modifyStaff(selected);
        refresh();
        informController.inform(msg, "修改司机信息成功");
	}

	private boolean isAllDriverSelected() {
		for (DriverAbstractCheckItem driver : drivers) {
			if (!driver.getSelected()) {
				return false;
			}
		}
		return true;
	}

	private void setAllDriverSelectedValue(boolean value) {
		for (DriverAbstractCheckItem driver : drivers) {
			driver.setSelected(value);
		}
	}
}
