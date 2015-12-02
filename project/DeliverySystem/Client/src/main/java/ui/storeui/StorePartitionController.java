package ui.storeui;

import java.io.IOException;

import bl.blService.storeblService.StoreModelBLService;
import factory.FormFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.store.StoreAreaCode;

/**
 * Created by Sissel on 2015/11/26.
 */
public class StorePartitionController {
    public TableView rows_TableView;
    public TableColumn rowNum_TableColumn;
    public TableColumn shelfNum_TableColumn;
    public TableColumn rate_TableColumn;
    public TableView area_TableView;
    public TableColumn key_TableColumn;
    public TableColumn value_TableColumn;
    public TextField warnLine_Field;

    private StoreModelBLService storeModelBLService = FormFactory.getStoreModelBLService();

    StoreAreaCode area ;

	public static Parent launch() throws IOException {
		FXMLLoader contentLoader = new FXMLLoader();
		contentLoader.setLocation(StoreInFormController.class.getResource("storePartition.fxml"));
		return contentLoader.load();
	}

    public void selectAir(ActionEvent actionEvent) {
    	area = StoreAreaCode.AIR;
    	//
    }

    public void selectRail(ActionEvent actionEvent) {
    	area = StoreAreaCode.RAIL;
    	//
    }

    public void selectRoad(ActionEvent actionEvent) {
    	area = StoreAreaCode.ROAD;
    	//
    }

    public void selectFlex(ActionEvent actionEvent) {
    	area = StoreAreaCode.FLEX;
    	//
    }

    public void expandArea(ActionEvent actionEvent) {
    	storeModelBLService.expandPartition(area, 1);
    	//更新界面
    }

    public void reduceArea(ActionEvent actionEvent) {
    	storeModelBLService.reducePartition(area, 1);
    	//更新界面
    }

    public void reajust(ActionEvent actionEvent) {

    }
}
