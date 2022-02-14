package mem.Welcome;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.scene.Parent;

public class MEM_Controller implements Initializable {
	
	Parent MEMWelcomeForm;
	
	public void setMEMWelcomeForm(Parent MEMwelcomeForm) {
		this.MEMWelcomeForm = MEMwelcomeForm;
	}
	public Parent getMEMWelcomeForm() {
		return this.MEMWelcomeForm;
	}

	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}
 
