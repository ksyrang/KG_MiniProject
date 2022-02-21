package Main.main;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;

import common.CmnMemShipDAO;
import common.CmnMemShipDTO;
import common.CmnMemShipScheDAO;
import common.CmnMemShipScheDTO;
import common.CmnPrmDAO;
import common.CmnPrmDTO;
import common.CmnPrmScheDAO;
import common.CmnPrmScheDTO;
import common.CmnTrainerDAO;
import common.CmnTrainerDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import mem.Mgt.MgtDAO;
import mem.Mgt.MgtDTO;
import mem.Welcome.MEM_WelcomeController;
import mem.Welcome.MEM_WelcomeDAO;
import mem.Welcome.MEM_WelcomeDTO;
import mem.Welcome.MEM_WelcomeMgtTable;
import mem.Welcome.MEM_WelcomeService;
import trn.DBDAO.TrnTrainerDAO;
import trn.DBDTO.TrnTrainerDTO;
import trn.Welcome.TrnTbVDTO;

//새로운 창 오픈을 위한 메인 서비스 클래스
public class MainService {
	private Controller controller;

	public void setController(Controller controller) {
		this.controller = controller;
	}

	public void adminWelcomeOpen() {
	      FXMLLoader loader = new FXMLLoader(getClass().getResource("/admin/welcome/KG_ADM_FX_Welcome.fxml"));
	      Parent adminWelcomeForm;
	      try {
	         adminWelcomeForm = loader.load();
	         controller.setAdminWelcomeController(loader.getController());
	         controller.setAdminWelcomeForm(adminWelcomeForm);
	         controller.settingAdmin();
	         
	         Scene scene = new Scene(adminWelcomeForm);
	         Stage primaryStage = new Stage();
	         primaryStage.setTitle("adminWelcome");
	         primaryStage.setScene(scene);
	         primaryStage.show();
	      } catch (IOException e) {
	         e.printStackTrace();
	      }
	   }

	public void memberWelcomeOpen(String UserCode) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/mem/Welcome/KG_MEM_FX_Welcome.fxml"));
		Parent memberWelcomeForm;
		try {
			memberWelcomeForm = loader.load();
			controller.setmemberWelcomeForm(memberWelcomeForm);
			controller.setMEM_WelcomeController(loader.getController());
			controller.getMEM_WelcomeController().setMemWelcomeForm(memberWelcomeForm);
			controller.getMEM_WelcomeController().setMembCode(UserCode);
			Label titleUserName = (Label)memberWelcomeForm.lookup("#TitleMemNameLabel");
			Label idText = (Label)memberWelcomeForm.lookup("#idText");
	        MgtDTO tmpMemDto = new MgtDTO(new MgtDAO().selectCode(UserCode));
	        titleUserName.setText(tmpMemDto.getMEM_Name()+" 회원님");

	        
	        //초기화면 테이블 세팅
	        TableView<MEM_WelcomeMgtTable> memProgramTable = (TableView<MEM_WelcomeMgtTable>) memberWelcomeForm.lookup("#memProgramTable");
			MEM_WelcomeDAO memWelcomeDao = new MEM_WelcomeDAO();
			ObservableList<MEM_WelcomeMgtTable> obserList = FXCollections.observableArrayList();
	    	ObservableList<MEM_WelcomeDTO> memWelcomeDto = memWelcomeDao.selectMemAllProgram(tmpMemDto.getMEM_ID());

			for (MEM_WelcomeDTO m : memWelcomeDto) {
				// String prm_name 얻는 과정
				String prm_Code = m.getPrm_code();
				CmnPrmDAO cmnPrmDao = new CmnPrmDAO();
				CmnPrmDTO cmnPrmDto = cmnPrmDao.SltPrmOne(prm_Code);
				String type = null;
				String time = null;
				String trainerName = null;
				if(cmnPrmDto!=null) {
					
					type = cmnPrmDto.getPRM_Name();
					time = m.getPrmsche_time();
					
			        String prmSCheCode = m.getPrmsche_code();
			        CmnPrmScheDAO cmnPrmScheDao = new CmnPrmScheDAO();
			        CmnPrmScheDTO cmnPrmScheDto = cmnPrmScheDao.SltPrmScheOne(prmSCheCode);
			        String trainerCode = cmnPrmScheDto.getTRAINER_Code();
			        CmnTrainerDAO cmnTrainerDao = new CmnTrainerDAO();
			        CmnTrainerDTO cmnTrainerDto = cmnTrainerDao.SltTrnOne(trainerCode);
			        trainerName = cmnTrainerDto.getTRAINER_Name();
				}else {
					//회원권
					CmnMemShipScheDAO cmnMemShipScheDao = new CmnMemShipScheDAO();
					CmnMemShipScheDTO cmnMemShipScheDto = cmnMemShipScheDao.SltMemShipScheOne(m.getMemshipsche_code());
					String memShipCode = cmnMemShipScheDto.getMEMSHIP_Code();
					CmnMemShipDAO cmnMemShipDao = new CmnMemShipDAO();
					CmnMemShipDTO cmnMemShipDto = cmnMemShipDao.SltMemShipOne(memShipCode);
					String memShipType = cmnMemShipDto.getMEMSHIP_Type();
					type = "회원권_"+memShipType+"개월";
					time = "-";
					trainerName= "KGGYM";
					
				}

				int prmsche_price = m.getPrmsche_price();
				Date prmsche_strdate = m.getPrmsche_strdate();
				Date prmsche_enddate = m.getPrmsche_enddate();
				System.out.println("trainer"+trainerName);
				obserList.add(
						new MEM_WelcomeMgtTable(trainerName, type, time, prmsche_price, prmsche_strdate, prmsche_enddate));
			}
			memProgramTable.setItems(obserList);

			MEM_WelcomeService memWelcomeService = new MEM_WelcomeService();
			memWelcomeService.setId(tmpMemDto.getMEM_ID());	
	        
	        
	        
	        
			Scene scene = new Scene(memberWelcomeForm);
			Stage primaryStage = new Stage();
			primaryStage.setTitle("memberWelcome");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void trainerWelcomeOpen(String UserCode) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/trn/Welcome/KG_TRN_FX_Welcome.fxml"));
		Parent trainerWelcomeForm;
		try {
			trainerWelcomeForm = loader.load();
			controller.setTrinerWelcomeForm(trainerWelcomeForm);
			// trainer welcome form 참조값을 trainer package로 이동
			controller.setTrnWelcomeController(loader.getController());
			controller.getTrnWelcomeController().setTrnWelcomeForm(trainerWelcomeForm);
			controller.getTrnWelcomeController().setTrnCode(UserCode);
			controller.getTrnWelcomeController().setLogOut(controller.getLogOut());
			// 상단 강사명 표시
			Label titleUserName = (Label) trainerWelcomeForm.lookup("#TitleUserNameLabel");
			CmnTrainerDTO tmpTrnDto = new CmnTrainerDTO(new CmnTrainerDAO().SltTrnOne(UserCode));
			titleUserName.setText(tmpTrnDto.getTRAINER_Name() + " 강사님");
			// 테이블뷰 초기 표시
			TableView<TrnTbVDTO> CurrentProgramTableList = (TableView<TrnTbVDTO>) trainerWelcomeForm
					.lookup("#CurrentProgramTableList");
//			TableView<TrnTbVDTO> CurrentProgramTableList = controller.getTrnWelcomeController().getCurrentProgramTableList();			
			ArrayList<CmnPrmScheDTO> tmplist = new CmnPrmScheDAO().SltPrmScheAllbyTrn(UserCode);
			ObservableList<TrnTbVDTO> TBVwlist = FXCollections.observableArrayList();
			for (CmnPrmScheDTO DTO : tmplist) {// PCodeColumn, PNameColumn, MembersColumn
				TBVwlist.add(new TrnTbVDTO(DTO.getPRMSCHE_Code(), DTO.getPRMSCHE_Name(),
						Integer.toString(DTO.getPRMSCHE_CurrentP())));
			}
			CurrentProgramTableList.setItems(TBVwlist);

			Scene scene = new Scene(trainerWelcomeForm);
			Stage primaryStage = new Stage();
			primaryStage.setTitle("trainerWelcome");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void memberJoinOpen() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/mem/Enroll/KG_MEM_FX_Enroll.fxml"));
		Parent memberJoinForm;
		try {
			memberJoinForm = loader.load();
			controller.setMemberJoinForm(memberJoinForm);
			controller.setEnrollController(loader.getController());
			controller.settingEnroll();

			Scene scene = new Scene(memberJoinForm);
			Stage primaryStage = new Stage();
			primaryStage.setTitle("memberJoin");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void memberFindIDOpen() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/mem/FindID/KG_MEM_FX_FindID.fxml"));
		Parent memberFindIDForm;
		try {
			memberFindIDForm = loader.load();
			controller.setMemberFindIDForm(memberFindIDForm);
			controller.setFindIDController(loader.getController());
			controller.settingFindID();

			Scene scene = new Scene(memberFindIDForm);
			Stage primaryStage = new Stage();
			primaryStage.setTitle("memberFindID");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void memberFindPWOpen() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/mem/FindPW/KG_MEM_FX_FindPW.fxml"));
		Parent memberFindPWForm;
		try {
			memberFindPWForm = loader.load();
			controller.setMemberFindPWForm(memberFindPWForm);
			controller.setFindPWController(loader.getController());
			controller.settingFindPW();

			Scene scene = new Scene(memberFindPWForm);
			Stage primaryStage = new Stage();
			primaryStage.setTitle("memberFindPW");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
