package trn.DBDTO;

public class TrnTrainerDTO {
	private String TRAINER_Code;
	private String TRAINER_Name;
	private String TRAINER_ID;
	private String TRAINER_PW;
	private String TRAINER_Gender;
	private int TRAINER_Birth;
	private int TRAINER_Mobile;
	private int TRAINER_Career;
	private String TRAINER_Addr;
	
	public TrnTrainerDTO() {
		// TODO Auto-generated constructor stub
	}
	public TrnTrainerDTO(String tRAINER_Code, String tRAINER_Name, String tRAINER_ID, String tRAINER_PW, String tRAINER_Gender,
			int tRAINER_Birth, int tRAINER_Mobile, int tRAINER_Career, String tRAINER_Addr) {
		super();
		TRAINER_Code = tRAINER_Code;
		TRAINER_Name = tRAINER_Name;
		TRAINER_ID = tRAINER_ID;
		TRAINER_PW = tRAINER_PW;
		TRAINER_Gender = tRAINER_Gender;
		TRAINER_Birth = tRAINER_Birth;
		TRAINER_Mobile = tRAINER_Mobile;
		TRAINER_Career = tRAINER_Career;
		TRAINER_Addr = tRAINER_Addr;
	}
	public String getTRAINER_Code() {
		return TRAINER_Code;
	}
	public void setTRAINER_Code(String tRAINER_Code) {
		TRAINER_Code = tRAINER_Code;
	}
	public String getTRAINER_Name() {
		return TRAINER_Name;
	}
	public void setTRAINER_Name(String tRAINER_Name) {
		TRAINER_Name = tRAINER_Name;
	}
	public String getTRAINER_ID() {
		return TRAINER_ID;
	}
	public void setTRAINER_ID(String tRAINER_ID) {
		TRAINER_ID = tRAINER_ID;
	}
	public String getTRAINER_PW() {
		return TRAINER_PW;
	}
	public void setTRAINER_PW(String tRAINER_PW) {
		TRAINER_PW = tRAINER_PW;
	}
	public String getTRAINER_Gender() {
		return TRAINER_Gender;
	}
	public void setTRAINER_Gender(String tRAINER_Gender) {
		TRAINER_Gender = tRAINER_Gender;
	}
	public int getTRAINER_Birth() {
		return TRAINER_Birth;
	}
	public void setTRAINER_Birth(int tRAINER_Birth) {
		TRAINER_Birth = tRAINER_Birth;
	}
	public int getTRAINER_Mobile() {
		return TRAINER_Mobile;
	}
	public void setTRAINER_Mobile(int tRAINER_Mobile) {
		TRAINER_Mobile = tRAINER_Mobile;
	}
	public int getTRAINER_Career() {
		return TRAINER_Career;
	}
	public void setTRAINER_Career(int tRAINER_Career) {
		TRAINER_Career = tRAINER_Career;
	}
	public String getTRAINER_Addr() {
		return TRAINER_Addr;
	}
	public void setTRAINER_Addr(String tRAINER_Addr) {
		TRAINER_Addr = tRAINER_Addr;
	}

	
}
