package trn.TrnMgt;

import common.CommonService;
import javafx.scene.Parent;
import trn.DTO.TrnDTO;

public class TrnMgtService {
	private MgtDAO TrnDAO; 

	public TrnDTO getTrnInfo(String Code) {
		TrnDTO tmpDto = TrnDAO.SelectTrnInfo(Code);
		return tmpDto;
	}

	public int TrnMgtUpdate(TrnDTO Dto) {
		return TrnDAO.UpdateTrnInfo(Dto);
	}
	
	
	public void BackProc(Parent root) {
		CommonService.WindowClose(root);
	}
	
	
}
