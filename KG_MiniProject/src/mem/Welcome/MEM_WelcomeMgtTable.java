package mem.Welcome;

import java.util.Date;

public class MEM_WelcomeMgtTable {
	
	private String colPrmCode;
	private String colTrainerCode;
	private int colPrmschePrice;
	private Date colPrmscheStrdate;
	private Date colPrmscheEnddate;
	private String colPrmscheCode;
	private String colMemshipscheCode;
	
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

	public MEM_WelcomeMgtTable(String colPrmCode, String colTrainerCode, String colPrmscheCode,Date colPrmscheStrdate,Date colPrmscheEnddate) {
		this.colPrmCode = colPrmCode;
		this.colTrainerCode = colTrainerCode;
		this.colPrmscheCode = colPrmscheCode;
		this.colPrmscheStrdate = colPrmscheStrdate;
		this.colPrmscheEnddate = colPrmscheEnddate;
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