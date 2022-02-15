package admin.sales;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.scene.Parent;

public class SalesController implements Initializable{
	private Parent salesForm;
	private SalesService salesSvc;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		salesSvc = new SalesService();
	}

	public void setSalesForm(Parent salesForm) {
		this.salesForm = salesForm;
	}
}
