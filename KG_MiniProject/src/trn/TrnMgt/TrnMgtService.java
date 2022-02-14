package trn.TrnMgt;

public class TrnMgtService {
	private MgtDAO TrnDAO; 

	public MgtDTO getTrnInfo(String Code) {
		MgtDTO tmpDto = TrnDAO.SelectTrnInfo(Code);
		return tmpDto;
	}

	public int TrnMgtUpdate(MgtDTO Dto) {
		return TrnDAO.UpdateTrnInfo(Dto);
	}
	
	
	
	
}
