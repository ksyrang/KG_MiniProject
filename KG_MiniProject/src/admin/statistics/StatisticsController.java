package admin.statistics;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.scene.Parent;

public class StatisticsController implements Initializable{
	private Parent statisticsForm;
	private StatisticsService statisticsSvc;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		statisticsSvc = new StatisticsService();
	}
	
	public void setStatisticsForm(Parent statisticsForm) {
		this.statisticsForm = statisticsForm;
	}

}
