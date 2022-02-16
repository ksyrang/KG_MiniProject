package admin.exProgramMgt;


public class ExProgramMgtDTO {
	

	//PRMSCHE_TB
	private String PRMSCHE_Code;
	private String PRMSCHE_Strdate;
	private String PRMSCHE_Enddate;
	private String PRMSCHE_Time;
	private String PRMSCHE_LimitP;
	private String PRMSCHE_CurrentP;
	private String PRMSCHE_Price;
//	private String PRMSHE_Name;
	
	//FK
	private String TRAINER_Code;
	private String PRM_Code;
	
	//가져올 데이터
	private String TRAINER_Name;
	private String PRM_Name;
	
	
	public String getPRMSCHE_Code() {
		return PRMSCHE_Code;
	}
	public void setPRMSCHE_Code(String pRMSCHE_Code) {
		PRMSCHE_Code = pRMSCHE_Code;
	}
	public String getPRM_Code() {
		return PRM_Code;
	}
	public void setPRM_Code(String pRM_Code) {
		PRM_Code = pRM_Code;
	}
	public String getTRAINER_Code() {
		return TRAINER_Code;
	}
	public void setTRAINER_Code(String tRAINER_Code) {
		TRAINER_Code = tRAINER_Code;
	}
	public String getPRM_Name() {
		return PRM_Name;
	}
	public void setPRM_Name(String pRM_Name) {
		PRM_Name = pRM_Name;
	}
	public String getPRMSCHE_Price() {
		return PRMSCHE_Price;
	}
	public void setPRMSCHE_Price(String pRMSCHE_Price) {
		PRMSCHE_Price = pRMSCHE_Price;
	}
	public String getPRMSCHE_Strdate() {
		return PRMSCHE_Strdate;
	}
	public void setPRMSCHE_Strdate(String pRMSCHE_Strdate) {
		PRMSCHE_Strdate = pRMSCHE_Strdate;
	}
	public String getPRMSCHE_Enddate() {
		return PRMSCHE_Enddate;
	}
	public void setPRMSCHE_Enddate(String pRMSCHE_Enddate) {
		PRMSCHE_Enddate = pRMSCHE_Enddate;
	}
	public String getPRMSCHE_Time() {
		return PRMSCHE_Time;
	}
	public void setPRMSCHE_Time(String pRMSCHE_Time) {
		PRMSCHE_Time = pRMSCHE_Time;
	}
	public String getPRMSCHE_LimitP() {
		return PRMSCHE_LimitP;
	}
	public void setPRMSCHE_LimitP(String pRMSCHE_LimitP) {
		PRMSCHE_LimitP = pRMSCHE_LimitP;
	}
	public String getPRMSCHE_CurrentP() {
		return PRMSCHE_CurrentP;
	}
	public void setPRMSCHE_CurrentP(String pRMSCHE_CurrentP) {
		PRMSCHE_CurrentP = pRMSCHE_CurrentP;
	}
	public String getTRAINER_Name() {
		return TRAINER_Name;
	}
	public void setTRAINER_Name(String tRAINER_Name) {
		TRAINER_Name = tRAINER_Name;
	}
	




}
