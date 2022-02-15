package trn.TrnMgt;

import common.CommonService;
import javafx.scene.Parent;
import trn.DBDAO.TrnTrainerDAO;
import trn.DBDTO.TrnTrainerDTO;

public class TrnMgtService {
	private TrnTrainerDAO TrnDAO; 

	public TrnTrainerDTO getTrnInfo(String Code) {
		TrnTrainerDTO tmpDto = TrnDAO.SelectTrnInfo(Code);
		return tmpDto;
	}

	public int TrnMgtUpdate(TrnTrainerDTO Dto) {
		return TrnDAO.UpdateTrnInfo(Dto);
	}
	
	
	public void BackProc(Parent root) {
		CommonService.WindowClose(root);
	}
	
	
}
