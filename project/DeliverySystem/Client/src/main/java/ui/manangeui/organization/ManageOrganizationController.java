package ui.manangeui.organization;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

/** 
 * Client//ui.manangeui.organization//ManageOrganizationController.java
 * @author CXWorks
 * @date 2015年12月8日 上午8:51:57
 * @version 1.0 
 */
public class ManageOrganizationController {
	
	
	
	public static Parent launch() throws IOException{
		FXMLLoader fxmlLoader=new FXMLLoader();
		fxmlLoader.setLocation(ManageOrganizationController.class.getResource("manageOrganization.fxml"));
		return fxmlLoader.load();
	}
}
