package mem.HelthProgramBuying;

import javafx.scene.Parent;

public class Controller {
//	private MEM_HealthProgramBuyingController HPBuyingController;
	private Service HPBuyingService;
	private Parent HPBuyingForm;
	
	public Controller() {
		System.out.println("Check 1");
		HPBuyingService = new Service();
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
