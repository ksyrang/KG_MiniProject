package mem.Delete;

import java.net.URL;
import java.util.ResourceBundle;

import common.CommonService;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import mem.Welcome.MEM_WelcomeController;


public class DeleteController implements Initializable {

	private Parent deleteForm;
	private Parent memWelcomeForm;
	private DeleteService deleteService;
	private DeleteController deleteController;
	private MEM_WelcomeController memWelcomeController;
	private String membCode;
	private Parent memMgtForm;
	
	
	public void setMembCode(String membCode) {
		this.membCode = membCode;
	}
	
	

	public MEM_WelcomeController getMemWelcomeController() {
		return memWelcomeController;
	}



	public void setMemWelcomeController(MEM_WelcomeController memWelcomeController) {
		this.memWelcomeController = memWelcomeController;
	}



	public DeleteController getDeleteController() {
		return deleteController;
	}
	
	public void setDeleteController(DeleteController deleteController) {
		this.deleteController = deleteController;
	}
	

	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		deleteService = new DeleteService();
		deleteService.setDeleteController(this);
	}
	
	//취소버튼 클릭 시	
		public void BackDeleteProc() {
			deleteService.BackDeleteProc(deleteForm);
		}
	

		public void DeleteProc() {
			deleteService.deleteProc(deleteForm, membCode);
//			CommonService.WindowClose(deleteForm);
//			CommonService.WindowClose(memWelcomeForm);
//			CommonService.WindowClose(mgtWelcomeForm);
		}

		
		
		
		public Parent getMemMgtForm() {
			return memMgtForm;
		}
		public void setMemMgtForm(Parent memMgtForm) {
			this.memMgtForm = memMgtForm;
		}
		public void setDeleteForm(Parent deleteForm) {
			this.deleteForm = deleteForm;
		}		
		public void setMemWelcomeForm(Parent memWelcomeForm) {
			this.memWelcomeForm = memWelcomeForm;
		}
		public Parent getMemWelcomeForm() {
			return memWelcomeForm;
		}
	
	
}
