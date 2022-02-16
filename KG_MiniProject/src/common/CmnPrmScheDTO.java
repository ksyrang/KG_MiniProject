package common;

import java.sql.Date;

public class CmnPrmScheDTO {

	private String PRMSCHE_Code;//PK
	private Date PRMSCHE_Strdate;
	private Date PRMSCHE_Enddate;
	private String PRMSCHE_Time;
	private int PRMSCHE_LimitP;
	private int PRMSCHE_CurrentP;
	private int PRMSCHE_Price;
	private String PRMSCHE_Name;
	//FK
	private String PRM_Code;
	private String TRAINER_Code;
	
	public CmnPrmScheDTO() {
		// TODO Auto-generated constructor stub
	}
	public CmnPrmScheDTO(CmnPrmScheDTO dTO) {
		super();
		PRMSCHE_Code = dTO.getPRMSCHE_Code();
		PRMSCHE_Strdate = dTO.getPRMSCHE_Strdate();
		PRMSCHE_Enddate = dTO.getPRMSCHE_Enddate();
		PRMSCHE_Time = dTO.getPRMSCHE_Time();
		PRMSCHE_LimitP = dTO.getPRMSCHE_LimitP();
		PRMSCHE_CurrentP = dTO.getPRMSCHE_CurrentP();
		PRMSCHE_Price = dTO.getPRMSCHE_Price();
		PRMSCHE_Name = dTO.getPRMSCHE_Name();
		PRM_Code = dTO.getPRM_Code();
		TRAINER_Code = dTO.getTRAINER_Code();
	}
	public CmnPrmScheDTO(String pRMSCHE_Code, Date pRMSCHE_Strdate, Date pRMSCHE_Enddate, String pRMSCHE_Time,
			int pRMSCHE_LimitP, int pRMSCHE_CurrentP, int pRMSCHE_Price, String pRMSCHE_Name, String pRM_Code,
			String tRAINER_Code) {
		super();
		PRMSCHE_Code = pRMSCHE_Code;
		PRMSCHE_Strdate = pRMSCHE_Strdate;
		PRMSCHE_Enddate = pRMSCHE_Enddate;
		PRMSCHE_Time = pRMSCHE_Time;
		PRMSCHE_LimitP = pRMSCHE_LimitP;
		PRMSCHE_CurrentP = pRMSCHE_CurrentP;
		PRMSCHE_Price = pRMSCHE_Price;
		PRMSCHE_Name = pRMSCHE_Name;
		PRM_Code = pRM_Code;
		TRAINER_Code = tRAINER_Code;
	}

	public String getPRMSCHE_Code() {
		return PRMSCHE_Code;
	}

	public void setPRMSCHE_Code(String pRMSCHE_Code) {
		PRMSCHE_Code = pRMSCHE_Code;
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

	public int getPRMSCHE_Price() {
		return PRMSCHE_Price;
	}

	public void setPRM_Price(int pRMSCHE_Price) {
		PRMSCHE_Price = pRMSCHE_Price;
	}

	public String getPRMSCHE_Name() {
		return PRMSCHE_Name;
	}

	public void setPRMSCHE_Name(String pRMSCHE_Name) {
		PRMSCHE_Name = pRMSCHE_Name;
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
	
	
}
