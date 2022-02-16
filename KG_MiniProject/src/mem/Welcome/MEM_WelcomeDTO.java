package mem.Welcome;

import java.util.Date;

public class MEM_WelcomeDTO {
	private String prm_name;
	private String prmsche_time;
	private int prmsche_price;
	private Date prmsche_strdate;
	private Date prmsche_enddate;
	private String prmsche_code;
	private String memshipsche_code;
	private String trainer_code;
	
	public String getPrmsche_code() {
		return prmsche_code;
	}
	public void setPrmsche_code(String prmsche_code) {
		this.prmsche_code = prmsche_code;
	}
	public String getMemshipsche_code() {
		return memshipsche_code;
	}
	public void setMemshipsche_code(String memshipsche_code) {
		this.memshipsche_code = memshipsche_code;
	}
	public String getTrainer_code() {
		return trainer_code;
	}
	public void setTrainer_code(String trainer_code) {
		this.trainer_code = trainer_code;
	}
	public String getPrm_name() {
		return prm_name;
	}
	public void setPrm_name(String prm_name) {
		this.prm_name = prm_name;
	}
	public String getPrmsche_time() {
		return prmsche_time;
	}
	public void setPrmsche_time(String prmsche_time) {
		this.prmsche_time = prmsche_time;
	}
	public int getPrmsche_price() {
		return prmsche_price;
	}
	public void setPrmsche_price(int prmsche_price) {
		this.prmsche_price = prmsche_price;
	}
	public Date getPrmsche_strdate() {
		return prmsche_strdate;
	}
	public void setPrmsche_strdate(Date prmsche_strdate) {
		this.prmsche_strdate = prmsche_strdate;
	}
	public Date getPrmsche_enddate() {
		return prmsche_enddate;
	}
	public void setPrmsche_enddate(Date prmsche_enddate) {
		this.prmsche_enddate = prmsche_enddate;
	}
	
}
	