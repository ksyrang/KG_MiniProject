package mem.Delete;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.scene.Parent;


public class DeleteController implements Initializable {

	private Parent memberDeleteForm;
	private DeleteService deleteService;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		deleteService = new DeleteService();		
	}
	
	public void setDeleteForm(Parent memberDeleteForm) {
		this.memberDeleteForm = memberDeleteForm;
	}
	
}
