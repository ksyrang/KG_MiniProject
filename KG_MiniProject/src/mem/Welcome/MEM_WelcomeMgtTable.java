package mem.Welcome;

public class MEM_WelcomeMgtTable {
	
	private String colCode;
	private String colPrmsche_code;
	private String colMemshipsche_code;
	private String colID;
	private String colName;
	
	public String getColID() {
		return colID;
	}

	public void setColID(String colID) {
		this.colID = colID;
	}

	public String getColName() {
		return colName;
	}

	public void setColName(String colName) {
		this.colName = colName;
	}

	public MEM_WelcomeMgtTable(String colCode, String colPrmsche_code, String colMemshipsche_code,String colID,String colName) {
		this.colCode = colCode;
		this.colPrmsche_code = colPrmsche_code;
		this.colMemshipsche_code = colMemshipsche_code;
		this.colID = colID;
		this.colName = colName;
	}

	public String getColCode() {
		return colCode;
	}

	public void setColCode(String colCode) {
		this.colCode = colCode;
	}

	public String getcolPrmsche_code() {
		return colPrmsche_code;
	}

	public void setPrmsche_code(String colPrmsche_code) {
		this.colPrmsche_code = colPrmsche_code;
	}

	public String getcolMemshipsche_code() {
		return colMemshipsche_code;
	}

	public void setColMemshipsche_code(String colMemshipsche_code) {
		this.colMemshipsche_code = colMemshipsche_code;
	}

	
}