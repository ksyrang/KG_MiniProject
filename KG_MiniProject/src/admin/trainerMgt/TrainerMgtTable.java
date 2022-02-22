package admin.trainerMgt;

public class TrainerMgtTable {
	private String colTrnCode;
	private String colTrnName;
	private String colTrnMobile;
	
	public TrainerMgtTable(String colTrnCode, String colTrnName, String colTrnMobile) {
		this.colTrnCode = colTrnCode;
		this.colTrnName = colTrnName;
		this.colTrnMobile = colTrnMobile;
	}
	public String getColTrnCode() {
		return colTrnCode;
	}
	public void setColTrnCode(String colTrnCode) {
		this.colTrnCode = colTrnCode;
	}
	public String getColTrnName() {
		return colTrnName;
	}
	public void setColTrnName(String colTrnName) {
		this.colTrnName = colTrnName;
	}
	public String getColTrnMobile() {
		return colTrnMobile;
	}
	public void setColTrnMobile(String colTrnMobile) {
		this.colTrnMobile = colTrnMobile;
	}
	
	

}
