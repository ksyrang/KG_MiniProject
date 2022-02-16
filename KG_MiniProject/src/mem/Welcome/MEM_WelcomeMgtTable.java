package mem.Welcome;

import java.util.Date;

public class MEM_WelcomeMgtTable {
	
	private String colPrmName;
	private String colPrmscheTime;
	private int colPrmschePrice;
	private Date colPrmscheStrdate;
	private Date colPrmscheEnddate;
	private String colMemshipscheCode;
	private String colTrainerCode;
	
	public MEM_WelcomeMgtTable(String colPrmName, String colPrmscheTime, int colPrmschePrice,Date colPrmscheStrdate,Date colPrmscheEnddate) {
		this.colPrmName = colPrmName;
		this.colPrmscheTime = colPrmscheTime;
		this.colPrmschePrice = colPrmschePrice;
		this.colPrmscheStrdate = colPrmscheStrdate;
		this.colPrmscheEnddate = colPrmscheEnddate;
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

	
	
}