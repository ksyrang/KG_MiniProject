package admin.trainerMgt;

public class TrainerMgtTable {
	private String colTrnCode;
	private String colTrnName;
	private int colTrnMobile;
	
	public TrainerMgtTable(String colTrnCode, String colTrnName, int colTrnMobile) {
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
	public int getColTrnMobile() {
		return colTrnMobile;
	}
	public void setColTrnMobile(int colTrnMobile) {
		this.colTrnMobile = colTrnMobile;
	}
	
	

}
