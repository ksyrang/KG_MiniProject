package mem.Welcome;

import java.util.Date;

public class MEM_WelcomeMgtTable {
	private String colMemId;
	private String colPrmCode;
	private String colPrmName;
	private String colPrmscheCode;
	private String colMemshipscheCode;
	private String colTrainerCode;
	private String colPrmscheName;
	private String colPrmscheTime;
	private Date colPrmscheStrdate;
	private Date colPrmscheEnddate;
	private int colPrmschePrice;
	private int colPrmscheCurrentp;
	private int colPrmscheLimitp;
	
	public MEM_WelcomeMgtTable(String colPrmName, String colPrmscheTime, int colPrmschePrice,Date colPrmscheStrdate,Date colPrmscheEnddate) {
		this.colPrmName = colPrmName;
		this.colPrmscheTime = colPrmscheTime;
		this.colPrmschePrice = colPrmschePrice;
		this.colPrmscheStrdate = colPrmscheStrdate;
		this.colPrmscheEnddate = colPrmscheEnddate;
	}

	public String getColMemId() {
		return colMemId;
	}

	public void setColMemId(String colMemId) {
		this.colMemId = colMemId;
	}

	public String getColPrmName() {
		return colPrmName;
	}

	public void setColPrmName(String colPrmName) {
		colPrmName = colPrmName;
	}

	public String getColPrmscheName() {
		return colPrmscheName;
	}

	public void setColPrmscheName(String colPrmscheName) {
		this.colPrmscheName = colPrmscheName;
	}

	public String getColPrmscheTime() {
		return colPrmscheTime;
	}

	public void setColPrmscheTime(String colPrmscheTime) {
		this.colPrmscheTime = colPrmscheTime;
	}

	public int getColPrmscheCurrentp() {
		return colPrmscheCurrentp;
	}

	public void setColPrmscheCurrentp(int colPrmscheCurrentp) {
		this.colPrmscheCurrentp = colPrmscheCurrentp;
	}

	public int getColPrmscheLimitp() {
		return colPrmscheLimitp;
	}

	public void setColPrmscheLimitp(int colPrmscheLimitp) {
		this.colPrmscheLimitp = colPrmscheLimitp;
	}

	public String getColPrmscheCode() {
		return colPrmscheCode;
	}

	public void setColPrmscheCode(String colPrmscheCode) {
		this.colPrmscheCode = colPrmscheCode;
	}
	
	public String getColMemshipscheCode() {
		return colMemshipscheCode;
	}

	public void setColMemshipscheCode(String colMemshipscheCode) {
		this.colMemshipscheCode = colMemshipscheCode;
	}

	public String getColPrmCode() {
		return colPrmCode;
	}

	public void setColPrmCode(String colPrmCode) {
		this.colPrmCode = colPrmCode;
	}

	public String getColTrainerCode() {
		return colTrainerCode;
	}

	public void setColTrainerCode(String colTrainerCode) {
		this.colTrainerCode = colTrainerCode;
	}

	public int getColPrmschePrice() {
		return colPrmschePrice;
	}

	public void setColPrmschePrice(int colPrmschePrice) {
		this.colPrmschePrice = colPrmschePrice;
	}

	public Date getColPrmscheStrdate() {
		return colPrmscheStrdate;
	}

	public void setColPrmscheStrdate(Date colPrmscheStrdate) {
		this.colPrmscheStrdate = colPrmscheStrdate;
	}

	public Date getColPrmscheEnddate() {
		return colPrmscheEnddate;
	}

	public void setColPrmscheEnddate(Date colPrmscheEnddate) {
		this.colPrmscheEnddate = colPrmscheEnddate;
	}

	
	
}