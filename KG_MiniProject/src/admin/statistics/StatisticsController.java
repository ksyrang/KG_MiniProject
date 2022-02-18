package admin.statistics;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import common.CmnMemDAO;
import common.CmnMemDTO;
import common.CmnMemShipScheDAO;
import common.CmnPayDAO;
import common.CmnPayDTO;
import common.CmnPrmDAO;
import common.CmnPrmDTO;
import common.CmnPrmScheDAO;
import common.CmnPrmScheDTO;
import common.CommonService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;

public class StatisticsController implements Initializable {
	private Parent statisticsForm;
	private StatisticsService statisticsSvc;

	@FXML
	private PieChart genderPie;
	@FXML
	private PieChart proPie;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		statisticsSvc = new StatisticsService();

		genderPieChart();
		programCPiehart();

	}

	public void setStatisticsForm(Parent statisticsForm) {
		this.statisticsForm = statisticsForm;
	}

	// 남녀성비 PieChart
	public void genderPieChart() {
		CmnMemDAO memDao = new CmnMemDAO();
		ArrayList<CmnMemDTO> member = memDao.SltMemAll();
		int menCnt = 0;
		int womenCnt = 0;
		int noGender = 0;

		for (CmnMemDTO m : member) {
			try {
				if (m.getMEM_Gender().equals("남")) {
					menCnt++;
				} else if (m.getMEM_Gender().equals("여")) {
					womenCnt++;
				}
			} catch (NullPointerException e) {
				noGender++;
				continue;
			}
		}

		ObservableList<Data> list = FXCollections.observableArrayList();
		if (menCnt > 0) {
			list.add(new PieChart.Data("남", menCnt));
		}
		if (womenCnt > 0) {
			list.add(new PieChart.Data("여", womenCnt));
		}
		if (noGender > 0) {
			list.add(new PieChart.Data("선택안함", noGender));
		}
		genderPie.setData(list);

	}

	// 회원권, 각 프로그램 종류 별 PieChart
	public void programCPiehart() {
		// 회원권 갯수
		CmnMemShipScheDAO memShipScheDao = new CmnMemShipScheDAO();
		int memshipSche = memShipScheDao.CntMemShipSche();

		// 각 프로그램 갯수
		CmnPrmDAO prmDao = new CmnPrmDAO();
		ArrayList<CmnPrmDTO> prmDto = prmDao.SltPrmAll();

		// prmSche TB 에서 prm_code를 얻어와서
		CmnPrmScheDAO prmScheDao = new CmnPrmScheDAO();
		ArrayList<CmnPrmScheDTO> prmScheDto;

		CmnPayDAO payDao = new CmnPayDAO();
		ArrayList<CmnPayDTO> payDto;
		int payedPrm = 0;

		ObservableList<Data> list = FXCollections.observableArrayList();
		if (memshipSche != 0) {
			list.add(new PieChart.Data("회원권", memshipSche));
		}
		for (CmnPrmDTO m : prmDto) {
			// 프로그램 코드 별 스케줄
			prmScheDto = prmScheDao.GetPrmScheCode(m.getPRM_Code());
			for (CmnPrmScheDTO sche : prmScheDto) {
				// 프로그램스케줄 코드 별 결제내역
				payDto = payDao.getPayCode(sche.getPRMSCHE_Code()); 
					if (payDto != null) {
						for (CmnPayDTO pay : payDto) {
							payedPrm++;
						}
					} else {
						System.out.println("payDto null나온다.");
					}
				
			}
			if (payedPrm > 0) {
				list.add(new PieChart.Data(m.getPRM_Name(), payedPrm));
			}
		}
		proPie.setData(list);
	}

	public void staCloseProc() {
		CommonService.WindowClose(statisticsForm);
	}

}
