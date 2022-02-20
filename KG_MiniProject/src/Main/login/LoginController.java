package Main.login;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Main.main.Controller;
import common.CmnMemShipDAO;
import common.CmnMemShipDTO;
import common.CommonService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class LoginController implements Initializable{

	@FXML
    private VBox 회원권표시00;
    @FXML
    private Label MemshipName1;
    @FXML
    private Label MemshipPrice1;
    @FXML
    private VBox 회원권표시10;
    @FXML
    private Label MemshipName2;
    @FXML
    private Label MemshipPrice2;
    @FXML
    private VBox 회원권표시20;
    @FXML
    private Label MemshipName3;
    @FXML
    private Label MemshipPrice3;
    @FXML
    private VBox 회원권표시30;
    @FXML
    private Label MemshipName4;
    @FXML
    private Label MemshipPrice4;
    @FXML
    private VBox 회원권표시01;
    @FXML
    private Label MemshipName5;
    @FXML
    private Label MemshipPrice5;
    @FXML
    private VBox 회원권표시11;
    @FXML
    private Label MemshipName6;
    @FXML
    private Label MemshipPrice6;
    @FXML
    private VBox 회원권표시21;
    @FXML
    private Label MemshipName7;
    @FXML
    private Label MemshipPrice7;
    @FXML
    private VBox 회원권표시31;
    @FXML
    private Label MemshipName8;
    @FXML
    private Label MemshipPrice8;
    @FXML
    private VBox 회원권표시02;
    @FXML
    private Label MemshipName9;
    @FXML
    private Label MemshipPrice9;
    @FXML
    private VBox 회원권표시12;
    @FXML
    private Label MemshipName10;
    @FXML
    private Label MemshipPrice10;
    @FXML
    private VBox 회원권표시22;
    @FXML
    private Label MemshipName11;
    @FXML
    private Label MemshipPrice11;
    @FXML
    private VBox 회원권표시32;
    @FXML
    private Label MemshipName12;
    @FXML
    private Label MemshipPrice12;

	
	
	private Parent mainForm;
	private LoginService loginSvc;
	private Controller controller;
	private String UserCode;
	
	public String getUserCode() {
		return UserCode;
	}
	public void setUserCode(String userCode) {
		UserCode = userCode;
	}


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.loginSvc = new LoginService();
//		UptMemshipList();
	}
	
	public void setController(Controller controller) {
		this.controller = controller;
	}
	
	public void setmainForm(Parent mainForm) {
		this.mainForm = mainForm;
	}
	public Parent getMainForm() {
		return mainForm;
	}
	
	//로그인 버튼 클릭 시 호출
	public void loginProc() {
		LoginDTO loginDto = loginSvc.loginProc(mainForm);
		String id =loginSvc.getId();
		controller.setId(id);
		String job = loginSvc.getjob();
		if(loginDto != null) {
			CommonService.WindowClose(mainForm);
			if(job.equals("회원")) {
				controller.setUserCode(loginSvc.getUserCode());
				controller.open(loginSvc.getpage(UserCode));
				CommonService.Msg("회원 계정 로그인 성공");
			}
			else if(job.equals("강사")) {
				controller.setUserCode(loginSvc.getUserCode());
				controller.open(loginSvc.getpage(UserCode));
				CommonService.Msg("강사 계정 로그인 성공");
			}
			else if(job.equals("관리자")) {
				controller.open(loginSvc.getpage(UserCode));
				CommonService.Msg("관리자 계정 로그인 성공");
			}
		}
	}

	//회원가입 버튼 클릭 시 호출
	public void joinProc() {		
	//		CommonService.WindowClose(mainForm);
			controller.open("memberJoin");
		}
	//아이디찾기 클릭 시 호출
	public void findIDProc() {
	//		CommonService.WindowClose(mainForm);
			controller.open("memberFindID");
	}
	//비밀번호찾기 클릭 시 호출
	public void findPWProc() {
	//		CommonService.WindowClose(mainForm);
			controller.open("memberFindPW");
	}
	
	private void UptMemshipList() {
		ArrayList<CmnMemShipDTO> memshiplist = new CmnMemShipDAO().SltMemShipAllType();
		String[] tmpTypelist = new String[memshiplist.size()];
		int[] tmpPricelist = new int[memshiplist.size()];
		int tmpnum = 0;
		for(CmnMemShipDTO E : memshiplist) {
			tmpTypelist[tmpnum] = E.getMEMSHIP_Type();
			tmpPricelist[tmpnum] = E.getMEMSHIP_Price();
			tmpnum++;
		}
		//Type과 가격 입력
		MemshipName1.setText(tmpTypelist[0]+" 개월");
		MemshipName2.setText(tmpTypelist[1]+" 개월");
		MemshipName3.setText(tmpTypelist[2]+" 개월");
		MemshipName4.setText(tmpTypelist[3]+" 개월");
		MemshipName5.setText(tmpTypelist[4]+" 개월");
		MemshipName6.setText(tmpTypelist[5]+" 개월");
		MemshipName7.setText(tmpTypelist[6]+" 개월");
		MemshipName8.setText(tmpTypelist[7]+" 개월");
		MemshipName9.setText(tmpTypelist[8]+" 개월");
		MemshipName10.setText(tmpTypelist[9]+" 개월");
		MemshipName11.setText(tmpTypelist[10]+" 개월");
		MemshipName12.setText(tmpTypelist[11]+" 개월");
		
		MemshipPrice1.setText(Integer.toString(tmpPricelist[0])+" 원");
		MemshipPrice2.setText(Integer.toString(tmpPricelist[1])+" 원");
		MemshipPrice3.setText(Integer.toString(tmpPricelist[2])+" 원");
		MemshipPrice4.setText(Integer.toString(tmpPricelist[3])+" 원");
		MemshipPrice5.setText(Integer.toString(tmpPricelist[4])+" 원");
		MemshipPrice6.setText(Integer.toString(tmpPricelist[5])+" 원");
		MemshipPrice7.setText(Integer.toString(tmpPricelist[6])+" 원");
		MemshipPrice8.setText(Integer.toString(tmpPricelist[7])+" 원");
		MemshipPrice9.setText(Integer.toString(tmpPricelist[8])+" 원");
		MemshipPrice10.setText(Integer.toString(tmpPricelist[9])+" 원");
		MemshipPrice11.setText(Integer.toString(tmpPricelist[10])+" 원");
		MemshipPrice12.setText(Integer.toString(tmpPricelist[11])+" 원");

		//입력 값이 없을 경우 숨기기
		if(MemshipName1.getText().equals("-")) 회원권표시00.setVisible(false);
		if(MemshipName2.getText().equals("-")) 회원권표시01.setVisible(false);
		if(MemshipName3.getText().equals("-")) 회원권표시02.setVisible(false);
		if(MemshipName4.getText().equals("-")) 회원권표시10.setVisible(false);
		if(MemshipName5.getText().equals("-")) 회원권표시11.setVisible(false);
		if(MemshipName6.getText().equals("-")) 회원권표시12.setVisible(false);
		if(MemshipName7.getText().equals("-")) 회원권표시20.setVisible(false);
		if(MemshipName8.getText().equals("-")) 회원권표시21.setVisible(false);
		if(MemshipName9.getText().equals("-")) 회원권표시22.setVisible(false);
		if(MemshipName10.getText().equals("-")) 회원권표시30.setVisible(false);
		if(MemshipName11.getText().equals("-")) 회원권표시31.setVisible(false);
		if(MemshipName12.getText().equals("-")) 회원권표시32.setVisible(false);
		
	}

}