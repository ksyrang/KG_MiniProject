package admin.sales;

import java.sql.Date;

public class SalesTable {

	private String colMemNumber;
	private String colProgramName;
	private String colProgramType;
	private int colPrice;
	private String colSalesType;
	private Date colDate;
	private String colTrainerName;
	
	public SalesTable(String colMemNumber, String colProgramName, String colProgramType, int colPrice,
			String colSalesType, Date colDate, String colTrainerName) {
		super();
		this.colMemNumber = colMemNumber;
		this.colProgramName = colProgramName;
		this.colProgramType = colProgramType;
		this.colPrice = colPrice;
		this.colSalesType = colSalesType;
		this.colDate = colDate;
		this.colTrainerName = colTrainerName;
	}
	
	
	
	
	public String getColMemNumber() {
		return colMemNumber;
	}
	public void setColMemNumber(String colMemNumber) {
		this.colMemNumber = colMemNumber;
	}
	public String getColProgramName() {
		return colProgramName;
	}
	public void setColProgramName(String colProgramName) {
		this.colProgramName = colProgramName;
	}
	public String getColProgramType() {
		return colProgramType;
	}
	public void setColProgramType(String colProgramType) {
		this.colProgramType = colProgramType;
	}
	public int getColPrice() {
		return colPrice;
	}
	public void setColPrice(int colPrice) {
		this.colPrice = colPrice;
	}
	public String getColSalesType() {
		return colSalesType;
	}
	public void setColSalesType(String colSalesType) {
		this.colSalesType = colSalesType;
	}
	public Date getColDate() {
		return colDate;
	}
	public void setColDate(Date colDate) {
		this.colDate = colDate;
	}
	public String getColTrainerName() {
		return colTrainerName;
	}
	public void setColTrainerName(String colTrainerName) {
		this.colTrainerName = colTrainerName;
	}
	
	

	
	
	
	
	
	
}
