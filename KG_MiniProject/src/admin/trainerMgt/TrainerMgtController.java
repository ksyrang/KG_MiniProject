package admin.trainerMgt;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.scene.Parent;

public class TrainerMgtController implements Initializable{
	private Parent trainerMgtForm;
	private TrainerMgtService trainerMgtSvc;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		trainerMgtSvc = new TrainerMgtService();
	}
	
	public void setTrainerMgtForm(Parent trainerMgtForm) {
		this.trainerMgtForm = trainerMgtForm;
	}

}
