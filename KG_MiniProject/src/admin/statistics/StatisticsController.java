package admin.statistics;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import common.CmnMemDAO;
import common.CmnMemDTO;
import common.CmnMemShipScheDAO;
import common.CmnMemShipScheDTO;
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
	@FXML private PieChart proPie;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		statisticsSvc = new StatisticsService();
		
		//남녀 성비 Pie 차트
		CmnMemDAO memDao = new CmnMemDAO();
		ArrayList<CmnMemDTO> member = memDao.SltMemAll();
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
		
		// 회원권, 각 프로그램 별 Pie 차트
		CmnMemShipScheDAO memShipScheDao = new CmnMemShipScheDAO();
		ArrayList<CmnMemShipScheDTO> memShipSche = memShipScheDao.SltMemShipScheAll();
		int memShip = 0;
		for(CmnMemShipScheDTO m : memShipSche) {
			System.out.println(m.getMEMSHIPSCHE_Code());
			memShip++;
		}
		System.out.println(memShip);
//		proPie.setData(FXCollections.observableArrayList(
//				new PieChart.Data("회원권", memShip),
//				new PieChart.Data("필라테스", 10)
//				));
		
	}
	
	public void setStatisticsForm(Parent statisticsForm) {
		this.statisticsForm = statisticsForm;
	}

}
