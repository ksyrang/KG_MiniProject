package trn.TrnMgt;

import java.net.URL;
import java.util.ResourceBundle;

import common.CommonService;
import javafx.fxml.Initializable;
import javafx.scene.Parent;

public class TrnMgtController implements Initializable {
	private TrnMgtService TrnMgtSvc;
	private String trnCode = null;
	private Parent trnMgtForm;
	
	public void setTrnMgtForm(Parent trnMgtForm) {
		this.trnMgtForm = trnMgtForm;
	}
	
	public void setTrnCode(String trnCode) {
		this.trnCode = trnCode;
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		TrnMgtSvc = new TrnMgtService();
		
	}
	public void TnrMgtProc() {
		MgtDTO tmpTrnDto = TrnMgtSvc.getTrnInfo(trnCode);
		int result = TrnMgtSvc.TrnMgtUpdate(tmpTrnDto);
		if(result == 1) {
			CommonService.Msg("수정완료");
			CommonService.WindowClose(trnMgtForm);
		}
		else CommonService.Msg("이상 발생");
	}
	
	public void BackProc() {
		CommonService.WindowClose(trnMgtForm);
	}
	
	
}
