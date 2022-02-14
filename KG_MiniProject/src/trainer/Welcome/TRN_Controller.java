package trainer.Welcome;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.scene.Parent;

public class TRN_Controller implements Initializable {
	
	Parent WelcomeForm;
	
	public void setWelcomeForm(Parent welcomeForm) {
		this.WelcomeForm = welcomeForm;
	}
	public Parent getWelcomeForm() {
		return this.WelcomeForm;
	}

	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}
 