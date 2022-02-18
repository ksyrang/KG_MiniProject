package mem.HelthProgramBuying;

import common.CmnMemShipDAO;
import common.CmnMemShipDTO;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;


public class HealthPrmBuyingService {
	private ObservableList<String> allProgram;
	private CmnMemShipDAO memshipDao;

	// 콤보박스 헬스 회원권 항목
	public ObservableList<String> getAllProgram() {
		memshipDao = new CmnMemShipDAO();
		this.allProgram = memshipDao.GETMemshipType();
		return this.allProgram;
	}

	// 회원권 선택시 가격 변동
	public void selectTypeCombo(Parent healthPrmBuyingForm) {
		System.out.println("서비스로 넘어옴");
		System.out.println(healthPrmBuyingForm);
		ComboBox<String> memshipComboBox = (ComboBox<String>) healthPrmBuyingForm.lookup("#memshipComboBox");
		Label memshipPriceTxt = (Label) healthPrmBuyingForm.lookup("#memshipPriceTxt");
		String type = memshipComboBox.getValue();
		memshipDao = new CmnMemShipDAO();
		CmnMemShipDTO memshipDto = memshipDao.SltMemShipAll(type);
		String price = Integer.toString(memshipDto.getMEMSHIP_Price());
		memshipPriceTxt.setText(price);
	}


}
