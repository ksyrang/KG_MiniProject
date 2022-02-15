package common;

public class CmnMemShipDTO {
	private String MEMSHIP_Code;//PK
	private String MEMSHIP_Type;
	private int MEMSHIP_Price;
	
	public CmnMemShipDTO() {
		// TODO Auto-generated constructor stub
	}

	public CmnMemShipDTO(String mEMSHIP_Code, String mEMSHIP_Type, int mEMSHIP_Price) {
		super();
		MEMSHIP_Code = mEMSHIP_Code;
		MEMSHIP_Type = mEMSHIP_Type;
		MEMSHIP_Price = mEMSHIP_Price;
	}

	public String getMEMSHIP_Code() {
		return MEMSHIP_Code;
	}

	public void setMEMSHIP_Code(String mEMSHIP_Code) {
		MEMSHIP_Code = mEMSHIP_Code;
	}

	public String getMEMSHIP_Type() {
		return MEMSHIP_Type;
	}

	public void setMEMSHIP_Type(String mEMSHIP_Type) {
		MEMSHIP_Type = mEMSHIP_Type;
	}

	public int getMEMSHIP_Price() {
		return MEMSHIP_Price;
	}

	public void setMEMSHIP_Price(int mEMSHIP_Price) {
		MEMSHIP_Price = mEMSHIP_Price;
	}
	
}
