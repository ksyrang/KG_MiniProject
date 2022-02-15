package common;

import java.sql.Date;

public class CmnPayDTO {
	private String PAY_Code;//PK
	private String PAY_Type;
	private Date PAY_Date;
	//FK
	private String MEMSHIPSCHE_Code;
	private String RES_Code;
	
	public CmnPayDTO() {
		// TODO Auto-generated constructor stub
	}
	public CmnPayDTO(CmnPayDTO cmnPayDTO) {
		PAY_Code = cmnPayDTO.getPAY_Code();
		PAY_Type = cmnPayDTO.getPAY_Type();
		PAY_Date = cmnPayDTO.getPAY_Date();
		MEMSHIPSCHE_Code = cmnPayDTO.getMEMSHIPSCHE_Code();
		RES_Code = cmnPayDTO.getRES_Code();	
	}
	public CmnPayDTO(String pAY_Code, String pAY_Type, Date pAY_Date, String mEMSHIPSCHE_Code, String rES_Code) {
		super();
		PAY_Code = pAY_Code;
		PAY_Type = pAY_Type;
		PAY_Date = pAY_Date;
		MEMSHIPSCHE_Code = mEMSHIPSCHE_Code;
		RES_Code = rES_Code;
	}

	public String getPAY_Code() {
		return PAY_Code;
	}

	public void setPAY_Code(String pAY_Code) {
		PAY_Code = pAY_Code;
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

	public String getRES_Code() {
		return RES_Code;
	}

	public void setRES_Code(String rES_Code) {
		RES_Code = rES_Code;
	}
	
	
}
