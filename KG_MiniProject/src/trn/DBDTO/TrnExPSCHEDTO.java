package trn.DBDTO;

import java.sql.Date;

public class TrnExPSCHEDTO {
	private String PRMSCHE_CODE;
	private Date PRMSCHE_STRDATE;
	private Date PRMSCHE_ENDDATE;
	private String PRMSCHE_TIME;
	private String PRM_CODE;
	private String TRAINER_CODE;
	private int PRMSCHE_LIMITP;
	private int PRMSCHE_CURRENTP;
	private int PRMSCH_PRICE;
	
	public TrnExPSCHEDTO(String pRMSCHE_CODE, Date pRMSCHE_STRDATE, Date pRMSCHE_ENDDATE, String pRMSCHE_TIME,
			String pRM_CODE, String tRAINER_CODE, int pRMSCHE_LIMITP, int pRMSCHE_CURRENTP, int pRMSCH_PRICE) {
		super();
		PRMSCHE_CODE = pRMSCHE_CODE;
		PRMSCHE_STRDATE = pRMSCHE_STRDATE;
		PRMSCHE_ENDDATE = pRMSCHE_ENDDATE;
		PRMSCHE_TIME = pRMSCHE_TIME;
		PRM_CODE = pRM_CODE;
		TRAINER_CODE = tRAINER_CODE;
		PRMSCHE_LIMITP = pRMSCHE_LIMITP;
		PRMSCHE_CURRENTP = pRMSCHE_CURRENTP;
		PRMSCH_PRICE = pRMSCH_PRICE;
	}
	public String getPRMSCHE_CODE() {
		return PRMSCHE_CODE;
	}
	public Date getPRMSCHE_STRDATE() {
		return PRMSCHE_STRDATE;
	}
	public Date getPRMSCHE_ENDDATE() {
		return PRMSCHE_ENDDATE;
	}
	public String getPRMSCHE_TIME() {
		return PRMSCHE_TIME;
	}
	public String getPRM_CODE() {
		return PRM_CODE;
	}
	public String getTRAINER_CODE() {
		return TRAINER_CODE;
	}
	public int getPRMSCHE_LIMITP() {
		return PRMSCHE_LIMITP;
	}
	public int getPRMSCHE_CURRENTP() {
		return PRMSCHE_CURRENTP;
	}
	public int getPRMSCH_PRICE() {
		return PRMSCH_PRICE;
	}
	
	
}
