package mem.Welcome;

import java.sql.Date;

public class MEM_WelcomeMgtTable {
//	private String colMemId;
//	private String colPrmCode;
	private String colPrmName;
//	private String colPrmscheCode;
//	private String colMemshipscheCode;
	private String colTrainerName;
//	private String colPrmscheName;
	private String colPrmscheTime;
	private Date colPrmscheStrdate;
	private Date colPrmscheEnddate;
	private int colPrmschePrice;
//	private int colPrmscheCurrentp;
//	private int colPrmscheLimitp;
	
	public MEM_WelcomeMgtTable(String colTrainerName,String colPrmName, String colPrmscheTime, int colPrmschePrice,Date colPrmscheStrdate,Date colPrmscheEnddate) {
		this.colPrmName = colPrmName;
		this.colPrmscheTime = colPrmscheTime;
		this.colPrmschePrice = colPrmschePrice;
		this.colPrmscheStrdate = colPrmscheStrdate;
		this.colPrmscheEnddate = colPrmscheEnddate;
		this.colTrainerName = colTrainerName;
	}



	public String getColPrmName() {
		return colPrmName;
	}

	public void setColPrmName(String colPrmName) {
		this.colPrmName = colPrmName;
	}

	public String getColPrmscheTime() {
		return colPrmscheTime;
	}

	public void setColPrmscheTime(String colPrmscheTime) {
		this.colPrmscheTime = colPrmscheTime;
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



	public String getColTrainerName() {
		return colTrainerName;
	}



	public void setColTrainerName(String colTrainerName) {
		this.colTrainerName = colTrainerName;
	}
	
	
}