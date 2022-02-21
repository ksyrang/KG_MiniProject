package Main.login;

public class MainPrmTable {
	private String mainPrmName;
	private String mainTrnName;
	private int mainCurrentP;
	private int mainLimitP;
	private int mainPrmPrice;
	
	public MainPrmTable(String mainPrmName, String mainTrnName, int mainCurrentP,int mainLimitP, int mainPrmPrice) {
		this.mainPrmName = mainPrmName;
		this.mainTrnName = mainTrnName;
		this.mainCurrentP = mainCurrentP;
		this.mainLimitP= mainLimitP;
		this.mainPrmPrice = mainPrmPrice;
	}

	public String getMainPrmName() {
		return mainPrmName;
	}

	public void setMainPrmName(String mainPrmName) {
		this.mainPrmName = mainPrmName;
	}

	public String getMainTrnName() {
		return mainTrnName;
	}

	public void setMainTrnName(String mainTrnName) {
		this.mainTrnName = mainTrnName;
	}

	public int getMainCurrentP() {
		return mainCurrentP;
	}

	public void setMainCurrentP(int mainCurrentP) {
		this.mainCurrentP = mainCurrentP;
	}

	public int getMainLimitP() {
		return mainLimitP;
	}

	public void setMainLimitP(int mainLimitP) {
		this.mainLimitP = mainLimitP;
	}

	public int getMainPrmPrice() {
		return mainPrmPrice;
	}

	public void setMainPrmPrice(int mainPrmPrice) {
		this.mainPrmPrice = mainPrmPrice;
	}
	
	
	
	

}
