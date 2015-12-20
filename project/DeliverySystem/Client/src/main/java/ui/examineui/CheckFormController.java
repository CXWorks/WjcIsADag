package ui.examineui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import po.FormEnum;
import factory.ExamineFactory;
import bl.blService.examineblService.ExamineblManageService;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Pane;
import ui.hallui.RevenueFormController;
import ui.informui.InformController;
import ui.loginui.LoginController;
import vo.FormVO;

public class CheckFormController implements EventHandler<Event> {

	public Tab all_Tab;
	public Tab order_Tab;
	public Tab hall_Arrive_Tab;
	public Tab center_Arrive_Tab;
	public Tab store_In_Tab;
	public Tab store_Out_Tab;
	public Tab deliver_Tab;
	public Tab load_Tab;
	public Tab transit_Tab;
	public Tab payment_Tab;
	public Tab Revenue_Tab;

	public CheckBox chooseAll_Box;

	public Button pass;
	public Button deny;
	public Button modify;

	public TabPane tabPane;
	public Tab selectedTab;
	//
	//
	@FXML
	public ObservableList<Tab> tabs;

	@FXML
	public ExamineblManageService examineblManageService = ExamineFactory.getExamineblManageService();
	@FXML
	public FormTableController formTableController;

	public Parent son;

	public FormEnum nowFormEnum = null;

	private InformController informController;

	public static Parent launch() throws IOException {
		FXMLLoader loader = new FXMLLoader(CheckFormController.class.getResource("checkForm.fxml"));
		Pane pane = loader.load();
		CheckFormController controller = loader.getController();
		controller.informController = InformController.newInformController(pane);

		return controller.informController.stackPane;
	}

	private void initSon() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader();
		fxmlLoader.setLocation(FormTableController.class.getResource("FormTableView.fxml"));
		son = (Parent) fxmlLoader.load();
		this.formTableController = (FormTableController) fxmlLoader.getController();
	}

	public void initialize() {
		tabs = tabPane.getTabs();
		for (Tab tab : tabs) {
			tab.setOnSelectionChanged(this);
			// tab.setContent(son);
		}
		tabPane.getSelectionModel().selectFirst();
		selectedTab = tabPane.getSelectionModel().getSelectedItem();
		this.handleSelection();

	}

	public void pass() {
		ArrayList<FormVO> temp = formTableController.getSelected();
		examineblManageService.passForm(temp);
		formTableController.change(nowFormEnum);
		this.changeCheckBox();
	}

	private void changeCheckBox() {
		chooseAll_Box.setSelected(false);
	}

	public void delete() {
		ArrayList<FormVO> temp = formTableController.getSelected();
		examineblManageService.deleteForm(temp);
		formTableController.change(nowFormEnum);
	}

	public void selectAll() {
		formTableController.selectAll();
	}

	//
	public void refresh() {
		examineblManageService.refresh();
		formTableController.change(nowFormEnum);
	}

	public void oneKey() {
		this.selectAll();
		this.pass();
	}

	private FormEnum getFormEnum(String text) {
		if (text == null) {
			return null;
		}
		//
		switch (text) {
		case "全部单据":
			return null;
		case "订单":
			return FormEnum.ORDER;
		case "派件单":
			return FormEnum.DELIVER;
		case "付款单":
			return FormEnum.PAYMENT;
		case "收款单":
			return FormEnum.REVENUE;
		case "到达单":
			return FormEnum.RECEIVE;
		case "营业厅中转单":
			return FormEnum.TRANSPORT_HALL;
		case "中转中心中转单":
			return FormEnum.TRANSPORT_CENTER;
		case "入库单":
			return FormEnum.STORE_IN;
		case "出库单":
			return FormEnum.STORE_OUT;

		default:
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see javafx.event.EventHandler#handle(javafx.event.Event)
	 */
	@Override
	public void handle(Event event) {
		Tab unknown = (Tab) event.getSource();
		// System.out.println(unknown.getText());
		if (unknown.getText().equalsIgnoreCase(selectedTab.getText())) {
			return;
		} else {
			selectedTab = unknown;
			this.handleSelection();
		}
	}

	//
	private void handleSelection() {
		try {
			this.initSon();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String text = selectedTab.getText();
		nowFormEnum = this.getFormEnum(text);
		formTableController.change(nowFormEnum);
		selectedTab.setContent(son);
		if (son == null) {
			System.out.println("null");
		}
	}
}
