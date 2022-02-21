package mem.Welcome;

import java.sql.Date;

public class MEM_WelcomeDTO {
	private String mem_id;
	private String prm_code;
	private String prm_name;
	private String trainer_code;
	private String prmsche_name;
	private String prmsche_time;

	//예약 스케줄 관리
	private String mem_code;
	private String memsche_code;
	private String prmsche_code;
	private String memshipsche_code;
	
	private Date prmsche_strdate;
	private Date prmsche_enddate;
	private int prmsche_price;
	private int prmsche_currentp;
	private int prmsche_limitp;
	
	
	public String getPrmsche_time() {
		return prmsche_time;
	}
	public void setPrmsche_time(String prmsche_time) {
		this.prmsche_time = prmsche_time;
	}
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
	public String getPrm_code() {
		return prm_code;
	}
	public void setPrm_code(String prm_code) {
		this.prm_code = prm_code;
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
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public String getPrm_name() {
		return prm_name;
	}
	public void setPrm_name(String prm_name) {
		this.prm_name = prm_name;
	}
	public String getPrmsche_name() {
		return prmsche_name;
	}
	public void setPrmsche_name(String prmsche_name) {
		this.prmsche_name = prmsche_name;
	}
	public int getPrmsche_currentp() {
		return prmsche_currentp;
	}
	public void setPrmsche_currentp(int prmsche_currentp) {
		this.prmsche_currentp = prmsche_currentp;
	}
	public int getPrmsche_limitp() {
		return prmsche_limitp;
	}
	public void setPrmsche_limitp(int prmsche_limitp) {
		this.prmsche_limitp = prmsche_limitp;
	}
	public String getMemsche_code() {
		return memsche_code;
	}
	public void setMemsche_code(String memsche_code) {
		this.memsche_code = memsche_code;
	}
	public String getMem_code() {
		return mem_code;
	}
	public void setMem_code(String mem_code) {
		this.mem_code = mem_code;
	}
	
}
	