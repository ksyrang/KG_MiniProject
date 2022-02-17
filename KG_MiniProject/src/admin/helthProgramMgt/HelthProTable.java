package admin.helthProgramMgt;

public class HelthProTable {
	
	private String colCode;
	private String colType;
	private int colPrice;
	
	public HelthProTable(String colCode, String colType, int colPrice) {
		this.colCode = colCode;
		this.colType = colType;
		this.colPrice = colPrice;
	}

	public String getColCode() {
		return colCode;
	}

	public void setColCode(String colCode) {
		this.colCode = colCode;
	}

	public String getColType() {
		return colType;
	}

	public void setColType(String colType) {
		this.colType = colType;
	}

	public int getColPrice() {
		return colPrice;
	}

	public void setColPrice(int colPrice) {
		this.colPrice = colPrice;
	}
	
	

}
