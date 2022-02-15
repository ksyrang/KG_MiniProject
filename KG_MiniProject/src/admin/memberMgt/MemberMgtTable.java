package admin.memberMgt;

public class MemberMgtTable {
	
	private String colCode;
	private String colName;
	private String colApprove;
	
	public MemberMgtTable(String colCode, String colName, String colApprove) {
		this.colCode = colCode;
		this.colName = colName;
		this.colApprove = colApprove;
	}

	public String getColCode() {
		return colCode;
	}

	public void setColCode(String colCode) {
		this.colCode = colCode;
	}

	public String getColName() {
		return colName;
	}

	public void setColName(String colName) {
		this.colName = colName;
	}

	public String getColApprove() {
		return colApprove;
	}

	public void setColApprove(String colApprove) {
		this.colApprove = colApprove;
	}

	
}
