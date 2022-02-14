package mem.HelthProgramBuying;

import javafx.scene.Parent;

public class MEM_HealthProgramBuyingController {
//	private MEM_HealthProgramBuyingController HPBuyingController;
	private MEM_HealthProgramBuyingService HPBuyingService;
	private Parent HPBuyingForm;
	
	public MEM_HealthProgramBuyingController() {
		System.out.println("Check 1");
		HPBuyingService = new MEM_HealthProgramBuyingService();
		System.out.println("Check 2");
		HPBuyingService.setHPBuyingController(this);
		System.out.println("Check 3");
	}
	
//	public void setHPBuyingController(MEM_HealthProgramBuyingController HPBuyingController) {
//		this.HPBuyingController = HPBuyingController;
//	}
//	public MEM_HealthProgramBuyingController getHPBuyingController() {
//		return HPBuyingController;
//	}
	public void setHPBuyingForm(Parent HPBuyingForm) {
		this.HPBuyingForm = HPBuyingForm;
	}
}
