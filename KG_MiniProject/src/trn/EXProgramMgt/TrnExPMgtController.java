package trn.EXProgramMgt;

import java.awt.TextField;
import java.net.URL;
import java.util.ResourceBundle;

import common.CmnTrainerDAO;
import common.CmnTrainerDTO;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import trn.DBDAO.TrnTrainerDAO;
import trn.DBDTO.TrnTrainerDTO;

public class TrnExPMgtController implements Initializable {
	
	@FXML
    private Label TitleUserNameLabel;
    @FXML
    private Button ExPMdyBtn;
    @FXML
    private Button ExPDltBtn;
    @FXML
    private Button Backbtn;
    @FXML
    private Label TrnName;
    @FXML
    private TextField ExPName;
    @FXML
    private DatePicker SrtDate;
    @FXML
    private DatePicker EndDate;
    @FXML
    private RadioButton AMRBtn;
    @FXML
    private RadioButton PMRBtn;
    @FXML
    private TextField LimitMemsField;
	
	private TrnExPMgtService trnExPMgtService;
	private Parent trnExProgramMgtForm;
	private String trnCode;
	
	public TrnExPMgtController() {
		trnExPMgtService = new TrnExPMgtService();
	}
	public void setTrnCode(String trnCode) {
		this.trnCode = trnCode;
	}

	public void setTrnExProgramMgtForm(Parent trnExProgramMgtForm) {
		this.trnExProgramMgtForm = trnExProgramMgtForm;
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		
	}
	
	public void ExPDltProc() {//삭제
		
	}
	
	public void ExPMdyProc() {//수정
		
	}
	
	public void BackProc() {
		trnExPMgtService.backClose(trnExProgramMgtForm);
	}
}
