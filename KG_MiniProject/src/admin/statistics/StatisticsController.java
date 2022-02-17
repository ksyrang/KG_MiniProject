package admin.statistics;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import common.CmnMemDAO;
import common.CmnMemDTO;
import common.CmnMemShipScheDAO;
import common.CmnMemShipScheDTO;
import common.CmnPrmDAO;
import common.CmnPrmDTO;
import common.CmnPrmScheDAO;
import common.CmnPrmScheDTO;
import common.CommonService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.Parent;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;

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
		// 회원권 갯수
		CmnMemShipScheDAO memShipScheDao = new CmnMemShipScheDAO();
		int memshipSche = memShipScheDao.CntMemShipSche();
		
		// 각 프로그램
		CmnPrmDAO prmDao = new CmnPrmDAO();
		ArrayList<CmnPrmDTO> prmDto = prmDao.SltPrmAll();
		
		CmnPrmScheDAO prmScheDao = new CmnPrmScheDAO();
		ArrayList<CmnPrmScheDTO> prmScheDto;
		
		int prmSche;
		
		ObservableList<Data> list = FXCollections.observableArrayList();
		list.add(new PieChart.Data("회원권", memshipSche));
		for(CmnPrmDTO m : prmDto) {
			System.out.println(m.getPRM_Name());
			prmSche = prmScheDao.CntPrmSche(m.getPRM_Code());
			System.out.println(prmSche);
			list.add(new PieChart.Data(m.getPRM_Name(), prmSche));
		}
		proPie.setData(list);
		
//		proPie.setData(FXCollections.observableArrayList(
//				new PieChart.Data("회원권", memShip),
//				new PieChart.Data("필라테스", 10),
//				new PieChart.Data("요가", 7)
//				));
		
	}
	
	public void setStatisticsForm(Parent statisticsForm) {
		this.statisticsForm = statisticsForm;
	}
	
	public void staCloseProc() {
		CommonService.WindowClose(statisticsForm);
	}

}
