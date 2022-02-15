package trn.Welcome;

public class TrnTbVDTO {

	private String PCodeColumn;
	private String PNameColumn;
	private String MembersColumn;
	
	public TrnTbVDTO(String pCodeColumn, String pNameColumn, String membersColumn) {
		super();
		PCodeColumn = pCodeColumn;
		PNameColumn = pNameColumn;
		MembersColumn = membersColumn;
	}
	public String getPCodeColumn() {
		return PCodeColumn;
	}
	public void setPCodeColumn(String pCodeColumn) {
		PCodeColumn = pCodeColumn;
	}
	public String getPNameColumn() {
		return PNameColumn;
	}
	public void setPNameColumn(String pNameColumn) {
		PNameColumn = pNameColumn;
	}
	public String getMembersColumn() {
		return MembersColumn;
	}
	public void setMembersColumn(String membersColumn) {
		MembersColumn = membersColumn;
	}
	
	
}
