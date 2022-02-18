package mem.Delete;

import java.net.URL;
import java.util.ResourceBundle;

import common.CommonService;
import javafx.fxml.Initializable;
import javafx.scene.Parent;


public class DeleteController implements Initializable {

	private Parent deleteForm;
	private DeleteService deleteService;
	private DeleteController deleteController;
	private String membCode;
	
	public void setMembCode(String membCode) {
		this.membCode = membCode;
	}
	
	
	public void setMemDeleteForm(Parent deleteForm) {
		this.deleteForm = deleteForm;
	}
	
	public DeleteController getDeleteController() {
		return deleteController;
	}
	
	public void setDeleteController(DeleteController deleteController) {
		this.deleteController = deleteController;
	}
	
	public void setDeleteForm(Parent deleteForm) {
		this.deleteForm = deleteForm;
	}
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		deleteService = new DeleteService();		
	}
	
	//취소버튼 클릭 시	
		public void BackDeleteProc() {
			deleteService.BackDeleteProc(deleteForm);
		}
	

		public void DeleteProc() {
			deleteService.deleteProc(deleteForm, membCode);
			CommonService.WindowClose(deleteForm);
		}

		
	
	
}
