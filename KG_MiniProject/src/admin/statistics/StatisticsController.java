package admin.statistics;

import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.ResourceBundle;

import admin.sales.SalesDAO;
import admin.sales.SalesDTO;
import common.CmnMemDAO;
import common.CmnMemDTO;
import common.CmnMemShipScheDAO;
import common.CmnPayDAO;
import common.CmnPayDTO;
import common.CmnPrmDAO;
import common.CmnPrmDTO;
import common.CmnPrmScheDAO;
import common.CmnPrmScheDTO;
import common.CmnTrainerDAO;
import common.CmnTrainerDTO;
import common.CommonService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.Tooltip;
import javafx.scene.chart.XYChart;

public class StatisticsController implements Initializable {
	private Parent statisticsForm;
	private ObservableList<SalesDTO> allSalesList;

	@FXML
	private PieChart genderPie;
	@FXML
	private PieChart proPie;
	@FXML
	private AreaChart trainerArea;
	@FXML
	private BarChart monthlyBar;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		SalesDAO salesDao = new SalesDAO();
		this.allSalesList = salesDao.getAllInfo();

		// 1. 남녀성비 PieChart

		CmnMemDAO memDao = new CmnMemDAO();
		ArrayList<CmnMemDTO> member = memDao.SltMemGender();
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

		ObservableList<Data> list1 = FXCollections.observableArrayList();
		if (menCnt > 0) {
			list1.add(new Data("남 : " + menCnt + " 명", menCnt));
		}
		if (womenCnt > 0) {
			list1.add(new Data("여 : " + womenCnt + " 명", womenCnt));
		}
		if (noGender > 0) {
			list1.add(new Data("선택안함 : " + noGender +" 명", noGender));
		}
		genderPie.setData(list1);
	
		
		// 2.회원권, 각 프로그램 종류 별 PieChart
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

		ObservableList<Data> list2 = FXCollections.observableArrayList();
		if (memshipSche != 0) {
			list2.add(new PieChart.Data("회원권 : " + memshipSche + "건", memshipSche));
		}

		for (CmnPrmDTO m : prmDto) {
			int payedPrm = 0;
			// 프로그램 코드 별 스케줄
			prmScheDto = prmScheDao.GetPrmScheCode(m.getPRM_Code());
			for (CmnPrmScheDTO sche : prmScheDto) {
				// 프로그램스케줄 코드 별 결제내역
				payDto = payDao.getPayCode(sche.getPRMSCHE_Code());
				if (payDto != null) {
					for (CmnPayDTO pay : payDto) {
						payedPrm++;
					}
				}
			}
			if (payedPrm > 0) {
				list2.add(new PieChart.Data(m.getPRM_Name() + " : " + payedPrm + "건", payedPrm));
			}
		}
		proPie.setData(list2);

		// 3.강사별 차트
		XYChart.Series series1 = new XYChart.Series();
		series1.setName("강사별 매출");
		
		String trainerName = null;
		CmnTrainerDAO cmnTrainerDao = new CmnTrainerDAO();
		ObservableList<CmnTrainerDTO> cmnTrainerDto = cmnTrainerDao.trainerName();

		for (CmnTrainerDTO i : cmnTrainerDto) {
			trainerName = i.getTRAINER_Name();
			int sales = 0;
			for (SalesDTO j : this.allSalesList) {
				// null처리
				if (j.getPRMSCHE_Code() != null && trainerName != null) {
					// ex프로그램
					if (trainerName.equals(j.getTRAINER_NAME())) {
						sales += j.getPRMSCHE_Price();
					}
				}
			}
			//series1.getData().add(new XYChart.Data(trainerName, sales));
			series1.getData().add(new XYChart.Data(trainerName + "\n(" + sales / 10000 + " 만원)", sales));
		}
		trainerArea.getData().add(series1);
		

		// 4. 월별 차트
		XYChart.Series series2 = new XYChart.Series();
		series2.setName("월별 매출");
		
		for (int monthDate12 = 1; monthDate12 < 13; monthDate12++) {
			int sales2 = 0;
			for (SalesDTO i : this.allSalesList) {
				Date date = i.getPAY_Date();
				int monthDate = CommonService.DateCnvt(date).getMonthValue();
				if (monthDate == monthDate12) {
					if (i.getPRMSCHE_Code() != null) {
						// ex프로그램
						sales2 += i.getPRMSCHE_Price();
					} else {
						// 헬스회원권
						sales2 += i.getMEMSHIP_Price();
					}
				}
			}
			String strMonthDate = Integer.toString(monthDate12);
			series2.getData().add(new XYChart.Data(strMonthDate,sales2));
		}
		monthlyBar.getData().add(series2);
		//monthlyBar.
	}

	public void setStatisticsForm(Parent statisticsForm) {
		this.statisticsForm = statisticsForm;
	}

	public void staCloseProc() {
		CommonService.WindowClose(statisticsForm);
	}

}
