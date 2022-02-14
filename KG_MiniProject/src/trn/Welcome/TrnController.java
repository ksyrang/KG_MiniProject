package trn.Welcome;

import trn.EXProgramMgt.TrnExProgramMgtController;
import trn.ExprogramEnroll.TrnExpEnrollController;
import trn.TrnMgt.TrnMgtController;

public class TrnController {

	private TrnWelcomeController trnWelcomeController;
	private TrnMgtController trnMgtController;
	private TrnExpEnrollController trnExpEnrollController;
	private TrnExProgramMgtController trnExpMgtController;
	
	
	//웰컴 컨트롤러
	public void setTrnWelcomeController(TrnWelcomeController trnWelcomeController) {
		this.trnWelcomeController = trnWelcomeController;
	}
	public TrnWelcomeController getTrnWelcomeController() {
		return this.trnWelcomeController;
	}
	
	//강사 정보 컨트롤러
	public void setTrnMgtController(TrnMgtController trnMgtController) {
		this.trnMgtController = trnMgtController;
	}
	public TrnMgtController getTrnMgtController() {
		return this.trnMgtController;
	}
	
	//프로그램 등록 컨트롤러
	public void setTrnExpEnrollController(TrnExpEnrollController trnExpEnrollController) {
		this.trnExpEnrollController = trnExpEnrollController;
	}
	public TrnExpEnrollController getTrnExpEnrollController() {
		return this.trnExpEnrollController;
	}
	
	//프로그램 수정 컨트롤러
	public void setTrnExpMgtController(TrnExProgramMgtController trnExpMgtController) {
		this.trnExpMgtController = trnExpMgtController;
	}
	public TrnExProgramMgtController TrnExpMgtController() {
		return this.trnExpMgtController;
	}
	
}

