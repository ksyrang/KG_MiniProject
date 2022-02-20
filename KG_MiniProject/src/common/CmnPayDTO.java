package common;

import java.sql.Date;

public class CmnPayDTO {
	private String PAY_Code;//PK
	private int PAYCode_Num;
	private String PAY_Type;
	private Date PAY_Date;
	//FK
	private String MEMSHIPSCHE_Code;
	private String MEM_Code;
	private String PRMSCHE_Code;
	
	public CmnPayDTO() {
		// TODO Auto-generated constructor stub
	}
	public CmnPayDTO(CmnPayDTO DTO) {
		PAY_Code = DTO.getPAY_Code();
		PAYCode_Num = DTO.getPAYCode_Num();
		PAY_Type = DTO.getPAY_Type();
		PAY_Date = DTO.getPAY_Date();
		MEMSHIPSCHE_Code = DTO.getMEMSHIPSCHE_Code();
		MEM_Code = DTO.getMEM_Code();
		PRMSCHE_Code = DTO.getPRMSCHE_Code();
	}
	public CmnPayDTO(String pAY_Code, int pAYCode_Num, String pAY_Type, 
			Date pAY_Date, String mEMSHIPSCHE_Code, 
			String mEM_Code, String pRMSCHE_Code) {
		super();
		PAY_Code = pAY_Code;
		PAYCode_Num = pAYCode_Num;
		PAY_Type = pAY_Type;
		PAY_Date = pAY_Date;
		MEMSHIPSCHE_Code = mEMSHIPSCHE_Code;
		MEM_Code = mEM_Code;
		PRMSCHE_Code = pRMSCHE_Code;
	}

	public String getPAY_Code() {
		return PAY_Code;
	}

	public void setPAY_Code(String pAY_Code) {
		PAY_Code = pAY_Code;
	}
	
	public int getPAYCode_Num() {
		return PAYCode_Num;
	}
	
	public void setPAYCode_Num(int pAYCode_Num) {
		PAYCode_Num = pAYCode_Num;
	}

	public String getPAY_Type() {
		return PAY_Type;
	}

	public void setPAY_Type(String pAY_Type) {
		PAY_Type = pAY_Type;
	}

	public Date getPAY_Date() {
		return PAY_Date;
	}

	public void setPAY_Date(Date pAY_Date) {
		PAY_Date = pAY_Date;
	}

	public String getMEMSHIPSCHE_Code() {
		return MEMSHIPSCHE_Code;
	}

	public void setMEMSHIPSCHE_Code(String mEMSHIPSCHE_Code) {
		MEMSHIPSCHE_Code = mEMSHIPSCHE_Code;
	}

	public String getMEM_Code() {
		return MEM_Code;
	}

	public void setMEM_Code(String mEM_Code) {
		MEM_Code = mEM_Code;
	}
	public String getPRMSCHE_Code() {
		return PRMSCHE_Code;
	}
	public void setPRMSCHE_Code(String pRMSCHE_Code) {
		PRMSCHE_Code = pRMSCHE_Code;
	}
	
	
}
