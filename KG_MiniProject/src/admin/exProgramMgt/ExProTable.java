package admin.exProgramMgt;

import java.util.Date;

public class ExProTable {
	
	private String programName;
	private String code;
	private String trainerName;
	private int limtPerson;
	private int currentPerson;
	private Date strDate;
	private Date endDate;
	private int price;
	private String timeC;
	
	
	
	public ExProTable(String programName, String code, String trainerName, int limtPerson, int currentPerson,
			Date strDate, Date endDate, int price, String timeC) {
		super();
		this.programName = programName;
		this.code = code;
		this.trainerName = trainerName;
		this.limtPerson = limtPerson;
		this.currentPerson = currentPerson;
		this.strDate = strDate;
		this.endDate = endDate;
		this.price = price;
		this.timeC = timeC;
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getProgramName() {
		return programName;
	}
	public void setProgramName(String programName) {
		this.programName = programName;
	}
	public String getTrainerName() {
		return trainerName;
	}
	public void setTrainerName(String trainerName) {
		this.trainerName = trainerName;
	}
	public int getLimtPerson() {
		return limtPerson;
	}
	public void setLimtPerson(int limtPerson) {
		this.limtPerson = limtPerson;
	}
	public int getCurrentPerson() {
		return currentPerson;
	}
	public void setCurrentPerson(int currentPerson) {
		this.currentPerson = currentPerson;
	}
	public Date getStrDate() {
		return strDate;
	}
	public void setStrDate(Date strDate) {
		this.strDate = strDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getTime() {
		return timeC;
	}
	public void setTime(String time) {
		this.timeC = timeC;
	}



	
}