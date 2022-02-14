package trn.Welcome;

import java.io.IOException;

import common.CommonService;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import trn.TrnMgt.MgtDTO;

public class WelcomeService {

	private TrnController trncontroller;
	private Parent WelcomeForm;
	
	public void setController(TrnController trncontroller) {
		this.trncontroller = trncontroller;
	}
	
	public MgtDTO programclickProc(Parent programclick) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void ExPEnrollProc() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/trn/EXProgramMgt/KG_TRN_FX_EXProgramMgt.fxml"));
		Parent trnExPEnrollFrom;
		try {
			trnExPEnrollFrom= loader.load();
			trncontroller.settrnEnrollForm(trnExPEnrollFrom);
			Stage stage = new Stage();
			stage.setScene(new Scene(trnExPEnrollFrom));
			stage.setTitle("trnExPEnroll");
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}			
	}

	public void ExPMgtProc() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/trn/EXProgramMgt/KG_TRN_FX_EXProgramMgt.fxml"));
		Parent trnExPMgtFrom;
		try {
			trnExPMgtFrom = loader.load();
			trncontroller.settrnEnrollForm(trnExPMgtFrom);
			Stage stage = new Stage();
			stage.setScene(new Scene(trnExPMgtFrom));
			stage.setTitle("trnExPEnroll");
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	
	}
	public void backProc(Parent back) {
		CommonService.WindowClose(WelcomeForm);
	}
	

	


}
