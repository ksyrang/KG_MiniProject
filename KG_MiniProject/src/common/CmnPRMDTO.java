package common;

public class CmnPRMDTO {
	private String PRM_Code;//PK
	private String PRM_Name;
	public CmnPRMDTO() {
		// TODO Auto-generated constructor stub
	}
	public CmnPRMDTO(String pRM_Code, String pRM_Name) {
		super();
		PRM_Code = pRM_Code;
		PRM_Name = pRM_Name;
	}
	public String getPRM_Code() {
		return PRM_Code;
	}
	public void setPRM_Code(String pRM_Code) {
		PRM_Code = pRM_Code;
	}
	public String getPRM_Name() {
		return PRM_Name;
	}
	public void setPRM_Name(String pRM_Name) {
		PRM_Name = pRM_Name;
	}
}
