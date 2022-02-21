package common;

public class CmnMemScheDTO {
	//PK
	private String MEMSCHE_Code; 

	//FK
	private String PRMSCHE_Code; 
	private String MEMSHIPSCHE_Code;
	private String MEM_Code;
	
	

	
	public CmnMemScheDTO(String mEMSCHE_Code, String pRMSCHE_Code, String mEMSHIPSCHE_Code, String mEM_Code) {
		super();
		MEMSCHE_Code = mEMSCHE_Code;
		PRMSCHE_Code = pRMSCHE_Code;
		MEMSHIPSCHE_Code = mEMSHIPSCHE_Code;
		MEM_Code = mEM_Code;
	}




	public String getMEMSCHE_Code() {
		return MEMSCHE_Code;
	}

	public void setMEMSCHE_Code(String mEMSCHE_Code) {
		MEMSCHE_Code = mEMSCHE_Code;
	}

	public String getPRMSCHE_Code() {
		return PRMSCHE_Code;
	}

	public void setPRMSCHE_Code(String pRMSCHE_Code) {
		PRMSCHE_Code = pRMSCHE_Code;
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

	
	
}
