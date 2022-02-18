package mem.EXProgramBuying;

import common.CommonService;
import javafx.scene.Parent;

public class MemExPBuyingService {
	
	private MemExPBuyingContorller MemExPBuyingContorller;
	
	public void setMemExPBuyingContorller(MemExPBuyingContorller MemExPBuyingContorller) {
		this.MemExPBuyingContorller = MemExPBuyingContorller;
	}

	
	public void BackProc(Parent MyForm) {
		CommonService.WindowClose(MyForm);
	}
	

}
