package admin.trainerEnroll;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.scene.Parent;

public class TrainerEnrollController implements Initializable{
	private Parent trainerEnrollForm;
	private TrainerEnrollService trainerEnrollSvc;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		trainerEnrollSvc = new TrainerEnrollService();
		
	}
	
	public void setTrainerEnrollForm(Parent trainerEnrollForm) {
		this.trainerEnrollForm = trainerEnrollForm;
	}
	
	

}
