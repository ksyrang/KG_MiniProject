package trn.DTO;

public class ExPDTO {
	private String PRM_Code;
	private String PRM_Name;
	private String PRM_Price;
	
	public ExPDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public ExPDTO(String pRM_Code, String pRM_Name, String pRM_Price) {
		super();
		PRM_Code = pRM_Code;
		PRM_Name = pRM_Name;
		PRM_Price = pRM_Price;
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
	public String getPRM_Price() {
		return PRM_Price;
	}
	public void setPRM_Price(String pRM_Price) {
		PRM_Price = pRM_Price;
	}
	
	
}
