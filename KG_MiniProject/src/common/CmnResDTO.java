package common;

public class CmnResDTO {
	private String RES_Code;//PK
	//FK
	private String MEM_Code;
	private String PRMSCHE_Code;
	private String MEMSHIPSCHE_Code;
	
	public CmnResDTO() {
		// TODO Auto-generated constructor stub
	}
	public CmnResDTO(CmnResDTO DTO) {
		RES_Code = DTO.getRES_Code();
		MEM_Code = DTO.getMEM_Code();
		PRMSCHE_Code = DTO.getPRMSCHE_Code();
		MEMSHIPSCHE_Code = DTO.getMEMSHIPSCHE_Code();
	}
	

	public CmnResDTO(String rES_Code, String mEM_Code, 
			String pRMSCHE_Code, String mEMSHIPSCHE_Code) {
		super();
		RES_Code = rES_Code;
		MEM_Code = mEM_Code;
		PRMSCHE_Code = pRMSCHE_Code;
		MEMSHIPSCHE_Code = mEMSHIPSCHE_Code;
	}
	public String getRES_Code() {
		return RES_Code;
	}

	public void setRES_Code(String rES_Code) {
		RES_Code = rES_Code;
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
	public String getMEMSHIPSCHE_Code() {
		return MEMSHIPSCHE_Code;
	}
	public void setMEMSHIPSCHE_Code(String mEMSHIPSCHE_Code) {
		MEMSHIPSCHE_Code = mEMSHIPSCHE_Code;
	}
}
