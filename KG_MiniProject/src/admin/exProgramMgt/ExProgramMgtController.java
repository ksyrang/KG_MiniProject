package admin.exProgramMgt;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.scene.Parent;

public class ExProgramMgtController implements Initializable{
	private Parent exProgramMgtForm;
	private ExProgramMgtService exProgramSvc;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		exProgramSvc = new ExProgramMgtService();
	}
	
	public void setExProgramMgtForm(Parent exProgramMgtForm) {
		this.exProgramMgtForm = exProgramMgtForm;
	}

}
