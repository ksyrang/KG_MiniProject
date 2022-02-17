package mem.Mgt;

public class MgtDTO {
	private String MEM_Code; //PK
	private String MEM_ID;
	private String MEM_PW;
	private String MEM_Name;
	private String MEM_Gender;
	private int MEM_Birth;
	private int MEM_Mobile;
	private String MEM_Addr;
	
	//FK
	private String PRMSCHE_Code; 
	private String MEMSHIPSCHE_Code;
	private String MEM_Approve;
	
	public MgtDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public MgtDTO(MgtDTO cmnMemDTO) {
		MEM_Code = cmnMemDTO.getMEM_Code();
		MEM_ID = cmnMemDTO.getMEM_ID();
		MEM_PW = cmnMemDTO.getMEM_PW();
		MEM_Name = cmnMemDTO.getMEM_Name();
		MEM_Gender = cmnMemDTO.getMEM_Gender();
		MEM_Birth = cmnMemDTO.getMEM_Birth();
		MEM_Mobile = cmnMemDTO.getMEM_Mobile();
		MEM_Addr = cmnMemDTO.getMEM_Addr();
		PRMSCHE_Code = cmnMemDTO.getPRMSCHE_Code();
		MEMSHIPSCHE_Code = cmnMemDTO.getMEMSHIPSCHE_Code();
		MEM_Approve = cmnMemDTO.getMEM_Approve();
	}
	
	public MgtDTO(String mEM_Code, String mEM_ID, String mEM_PW, String mEM_Name, String mEM_Gender, int mEM_Birth,
			int mEM_Mobile, String mEM_Addr, String pRMSCHE_Code, String mEMSHIPSCHE_Code, String mEM_Approve) {
		super();
		MEM_Code = mEM_Code;
		MEM_ID = mEM_ID;
		MEM_PW = mEM_PW;
		MEM_Name = mEM_Name;
		MEM_Gender = mEM_Gender;
		MEM_Birth = mEM_Birth;
		MEM_Mobile = mEM_Mobile;
		MEM_Addr = mEM_Addr;
		PRMSCHE_Code = pRMSCHE_Code;
		MEMSHIPSCHE_Code = mEMSHIPSCHE_Code;
		MEM_Approve = mEM_Approve;
	}

	public String getMEM_Code() {
		return MEM_Code;
	}

	public void setMEM_Code(String mEM_Code) {
		MEM_Code = mEM_Code;
	}

	public String getMEM_ID() {
		return MEM_ID;
	}

	public void setMEM_ID(String mEM_ID) {
		MEM_ID = mEM_ID;
	}

	public String getMEM_PW() {
		return MEM_PW;
	}

	public void setMEM_PW(String mEM_PW) {
		MEM_PW = mEM_PW;
	}

	public String getMEM_Name() {
		return MEM_Name;
	}

	public void setMEM_Name(String mEM_Name) {
		MEM_Name = mEM_Name;
	}

	public String getMEM_Gender() {
		return MEM_Gender;
	}

	public void setMEM_Gender(String mEM_Gender) {
		MEM_Gender = mEM_Gender;
	}

	public int getMEM_Birth() {
		return MEM_Birth;
	}

	public void setMEM_Birth(int mEM_Birth) {
		MEM_Birth = mEM_Birth;
	}

	public int getMEM_Mobile() {
		return MEM_Mobile;
	}

	public void setMEM_Mobile(int mEM_Mobile) {
		MEM_Mobile = mEM_Mobile;
	}

	public String getMEM_Addr() {
		return MEM_Addr;
	}

	public void setMEM_Addr(String mEM_Addr) {
		MEM_Addr = mEM_Addr;
	}

	public String getPRMSCHE_Code() {
		return PRMSCHE_Code;
	}

	public void setPRMSCHE_Code(String pRMSCHE_Code) {
		PRMSCHE_Code = pRMSCHE_Code;
	}

	public String getMEMSHIPSCHE_Code() {
		return MEMSHIPSCHE_Code;
	}

	public void setMEMSHIPSCHE_Code(String mEMSHIPSCHE_Code) {
		MEMSHIPSCHE_Code = mEMSHIPSCHE_Code;
	}

	public String getMEM_Approve() {
		return MEM_Approve;
	}

	public void setMEM_Approve(String mEM_Aprove) {
		MEM_Approve = mEM_Aprove;
	}
	
	
}
