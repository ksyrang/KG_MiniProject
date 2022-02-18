package mem.EXProgramBuying;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;

public class MemExPBuyingContorller implements Initializable {
    
	@FXML private Button ExPScheBuyBtn;
    @FXML private Button BackBtn;

	private Parent MemExpBuyingForm;
	private MemExPBuyingService MemExPBuyingSvc;
	
	public MemExPBuyingContorller() {
		MemExPBuyingSvc= new MemExPBuyingService();
		MemExPBuyingSvc.setMemExPBuyingContorller(this);		

	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	//Exp스케쥴 구매하기
	public void ExPScheProc() {
		
	}
	//뒤로가기
	public void BackProc() {
		System.out.println("Exp 뒤로가기 동작");
		MemExPBuyingSvc.BackProc(MemExpBuyingForm);
	}
	public Parent getMemExpBuyingForm() {
		return MemExpBuyingForm;
	}

	public void setMemExpBuyingForm(Parent memExpBuyingForm) {
		MemExpBuyingForm = memExpBuyingForm;
	}
	
	
	
	
}
