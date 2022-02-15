package trn.EXProgramMgt;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.scene.Parent;
import trn.DBDAO.TrnTrainerDAO;
import trn.DBDTO.TrnTrainerDTO;

public class TrnExPMgtController implements Initializable {
	
	private TrnExPMgtService trnExPMgtService;
	private Parent trnExProgramMgtForm;
	private String trnCode;
	
	public TrnExPMgtController() {
		trnExPMgtService = new TrnExPMgtService();
	}
	public void setTrnCode(String trnCode) {
		this.trnCode = trnCode;
	}



	public void setTrnExProgramMgtForm(Parent trnExProgramMgtForm) {
		this.trnExProgramMgtForm = trnExProgramMgtForm;
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		TrnTrainerDTO tmptrndto = new TrnTrainerDAO().SelectTrnInfo(trnCode);
		
	}
	
	public void BackProc() {
		trnExPMgtService.backClose(trnExProgramMgtForm);
	}
}
