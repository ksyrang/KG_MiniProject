package admin.exProgramMgt;

import java.util.Date;

public class ExProgramMgtDTO {
	

	private String PRM_Code;
	private String PRM_Name;
	private String PRMSCHE_Code;
	private String TRAINER_Code;
	private Date PRMSCHE_Strdate;
	private Date PRMSCHE_Enddate;
	private String PRMSCHE_Time;
	private int PRMSCHE_LimitP;
	private int PRMSCHE_CurrentP;
	private int PRMSCHE_Price;
	

	
	
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
	public int getPRMSCHE_Price() {
		return PRMSCHE_Price;
	}
	public void setPRMSCHE_Price(int pRMSCHE_Price) {
		PRMSCHE_Price = pRMSCHE_Price;
	}
	public Date getPRMSCHE_Strdate() {
		return PRMSCHE_Strdate;
	}
	public void setPRMSCHE_Strdate(Date pRMSCHE_Strdate) {
		PRMSCHE_Strdate = pRMSCHE_Strdate;
	}
	public Date getPRMSCHE_Enddate() {
		return PRMSCHE_Enddate;
	}
	public void setPRMSCHE_Enddate(Date pRMSCHE_Enddate) {
		PRMSCHE_Enddate = pRMSCHE_Enddate;
	}
	public String getPRMSCHE_Time() {
		return PRMSCHE_Time;
	}
	public void setPRMSCHE_Time(String pRMSCHE_Time) {
		PRMSCHE_Time = pRMSCHE_Time;
	}
	public int getPRMSCHE_LimitP() {
		return PRMSCHE_LimitP;
	}
	public void setPRMSCHE_LimitP(int pRMSCHE_LimitP) {
		PRMSCHE_LimitP = pRMSCHE_LimitP;
	}
	public int getPRMSCHE_CurrentP() {
		return PRMSCHE_CurrentP;
	}
	public void setPRMSCHE_CurrentP(int pRMSCHE_CurrentP) {
		PRMSCHE_CurrentP = pRMSCHE_CurrentP;
	}
	




}
