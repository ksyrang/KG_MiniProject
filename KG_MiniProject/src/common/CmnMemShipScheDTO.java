package common;

import java.sql.Date;

public class CmnMemShipScheDTO {
	private String MEMSHIPSCHE_Code;//PK
	private int MEMSHIPSCHECode_Num;
	private Date MEMSHIPSCHE_Strdate;
	private Date MEMSHIPSCHE_Enddate;
	//FK
	private String MEMSHIP_Code;
	private String MEM_Code;
	
	public CmnMemShipScheDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public CmnMemShipScheDTO(String mEMSHIPSCHE_Code,int mEMSHIPSCHECode_Num, Date mEMSHIPSCHE_Strdate, Date mEMSHIPSCHE_Enddate,
			String mEMSHIP_Code, String mEM_Code) {
		super();
		MEMSHIPSCHE_Code = mEMSHIPSCHE_Code;
		MEMSHIPSCHECode_Num = mEMSHIPSCHECode_Num;
		MEMSHIPSCHE_Strdate = mEMSHIPSCHE_Strdate;
		MEMSHIPSCHE_Enddate = mEMSHIPSCHE_Enddate;
		MEMSHIP_Code = mEMSHIP_Code;
		MEM_Code = mEM_Code;
	}

	public String getMEMSHIPSCHE_Code() {
		return MEMSHIPSCHE_Code;
	}

	public void setMEMSHIPSCHE_Code(String mEMSHIPSCHE_Code) {
		MEMSHIPSCHE_Code = mEMSHIPSCHE_Code;
	}
	
	public int getMEMSHIPSCHECode_Num() {
		return MEMSHIPSCHECode_Num;
	}
	
	public void setMEMSHIPSCHECode_Num(int mEMSHIPSCHECode_Num) {
		MEMSHIPSCHECode_Num = mEMSHIPSCHECode_Num;
	}

	public Date getMEMSHIPSCHE_Strdate() {
		return MEMSHIPSCHE_Strdate;
	}

	public void setMEMSHIPSCHE_Strdate(Date mEMSHIPSCHE_Strdate) {
		MEMSHIPSCHE_Strdate = mEMSHIPSCHE_Strdate;
	}

	public Date getMEMSHIPSCHE_Enddate() {
		return MEMSHIPSCHE_Enddate;
	}

	public void setMEMSHIPSCHE_Enddate(Date mEMSHIPSCHE_Enddate) {
		MEMSHIPSCHE_Enddate = mEMSHIPSCHE_Enddate;
	}

	public String getMEMSHIP_Code() {
		return MEMSHIP_Code;
	}

	public void setMEMSHIP_Code(String mEMSHIP_Code) {
		MEMSHIP_Code = mEMSHIP_Code;
	}

	public String getMEM_Code() {
		return MEM_Code;
	}

	public void setMEM_Code(String mEM_Code) {
		MEM_Code = mEM_Code;
	}
	
	
}
