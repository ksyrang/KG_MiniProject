package admin.exProgramMgt;

import java.util.Date;

public class ExProTable {
	
	private String programName;
	private String code;
	private String trainerName;
	private String limtPerson;
	private String currentPerson;
	private String strDate;
	private String endDate;
	private String price;
	private String timeC;
	
	
	
	public ExProTable(String programName, String code, String trainerName, String limtPerson, String currentPerson,
			String strDate, String endDate, String price, String timeC) {
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
	public String getLimtPerson() {
		return limtPerson;
	}
	public void setLimtPerson(String limtPerson) {
		this.limtPerson = limtPerson;
	}
	public String getCurrentPerson() {
		return currentPerson;
	}
	public void setCurrentPerson(String currentPerson) {
		this.currentPerson = currentPerson;
	}
	public String getStrDate() {
		return strDate;
	}
	public void setStrDate(String strDate) {
		this.strDate = strDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getTimeC() {
		return timeC;
	}
	public void setTimeC(String timeC) {
		this.timeC = timeC;
	}



	
}