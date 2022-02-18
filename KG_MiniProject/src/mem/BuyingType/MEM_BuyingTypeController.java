package mem.BuyingType;

import java.net.URL;
import java.util.ResourceBundle;

import common.CommonService;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;


public class MEM_BuyingTypeController implements Initializable{
	private Parent buyingTypeForm;
	private MEM_BuyingTypeService buyingTypeSvc;;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		buyingTypeSvc = new MEM_BuyingTypeService();
		buyingTypeSvc.setBuyingTypeController(this);
	}
	
	public void setBuyingTypeForm(Parent buyingTypeForm) {
		System.out.println("BuyingType 1 : "+buyingTypeForm);
		this.buyingTypeForm = buyingTypeForm;
		System.out.println("BuyingType 2 : "+this.buyingTypeForm);
	}

}
