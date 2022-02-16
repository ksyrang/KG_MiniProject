package admin.statistics;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import common.CmnMemDAO;
import common.CmnMemDTO;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.Parent;
import javafx.scene.chart.PieChart;

public class StatisticsController implements Initializable{
	private Parent statisticsForm;
	private StatisticsService statisticsSvc;
	
	@FXML private PieChart genderPie;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		statisticsSvc = new StatisticsService();
		
		//남녀 성비 Pie 차트
		CmnMemDAO memDao = new CmnMemDAO();
		ArrayList<CmnMemDTO> member = memDao.SltResAll();
		int menCnt = 0;
		int womenCnt = 0;
		int noGender = 0;
		
		for(CmnMemDTO m : member) {
			try {
				if(m.getMEM_Gender().equals("남")) {
					menCnt++;
				}else if(m.getMEM_Gender().equals("여")){
					womenCnt++;
				} 
			} catch (NullPointerException e) {
				noGender++;
				continue;
			}
		}
		
		genderPie.setData(FXCollections.observableArrayList(
				new PieChart.Data("남", menCnt),
				new PieChart.Data("여", womenCnt),
				new PieChart.Data("선택안함", noGender)
				));
		genderPie.setLegendSide(Side.LEFT);
		
	}
	
	public void setStatisticsForm(Parent statisticsForm) {
		this.statisticsForm = statisticsForm;
	}

}
