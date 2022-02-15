package mem.Enroll;

public class EnrollDTO {
	private String MEM_Code;
	private String ID;
	private String PW;
	private String Name;
	private String Gender;
	private String Birth;
	private String Mobile;
	private String Addr;
	private String approve;
	
	public String getID() {
		return ID;
	}
	public void setID(String id) {
		this.ID = id;
	}
	public String getPW() {
		return PW;
	}
	public void setPW(String pw) {
		this.PW = pw;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		this.Name = name;
	}
	public String getGender() {
		return Gender;
	}
	public void setGender(String gender) {
		this.Gender = gender;
	}
	public String getBirth() {
		return Birth;
	}
	public void setBirth(String birth) {
		this.Birth = birth;
	}
	public String getMobile() {
		return Mobile;
	}
	public void setMobile(String mobile) {
		this.Mobile = mobile;
	}
	public String getAddr() {
		return Addr;
	}
	public void setAddr(String addr) {
		this.Addr = addr;
	}

	public String getApprove() {
		return approve;
	}
	
	public void setApprove(String approve) {
		this.approve = approve;
	}
	public String getMEM_Code() {
		return MEM_Code;
	}
	public void setMEM_Code(String mEM_Code) {
		MEM_Code = mEM_Code;
	}
	
	
	
}
