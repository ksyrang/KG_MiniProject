package admin.sales;

import java.sql.Date;

public class SalesDTO {
	private String PAY_Code;
	private String PAY_Type; //6. 결제종류
	private Date PAY_Date; //5. 결제일자
	
	
	//FK
	private String RES_Code;
	private String MEMSHIPSCHE_Code;
	private String PRMSCHE_Code;
	
	//가져올데이터(결제금액/프로그램명/)
	private int MEMSHIP_Price; // 4.결제금액 membershipescheduled -> membershipe에 price있음
	private int PRMSCHE_Price; // 4.결제금액 
	private String MEM_Code; //1. 회원고유번호 membershipescheduled에 있음
	private String PRMSCHE_Name; //2. 프로그램명
	private String PRM_Name; //3. ex프로그램 종류 이름
	private String MEMSHIP_Type; //3. 회원권 개월 수 숫자만 있음.
	
	
	
	public String getPAY_Code() {
		return PAY_Code;
	}
	public void setPAY_Code(String pAY_Code) {
		PAY_Code = pAY_Code;
	}
	public String getPAY_Type() {
		return PAY_Type;
	}
	public void setPAY_Type(String pAY_Type) {
		PAY_Type = pAY_Type;
	}
	public Date getPAY_Date() {
		return PAY_Date;
	}
	public void setPAY_Date(Date pAY_Date) {
		PAY_Date = pAY_Date;
	}
	public String getRES_Code() {
		return RES_Code;
	}
	public void setRES_Code(String rES_Code) {
		RES_Code = rES_Code;
	}
	public String getMEMSHIPSCHE_Code() {
		return MEMSHIPSCHE_Code;
	}
	public void setMEMSHIPSCHE_Code(String mEMSHIPSCHE_Code) {
		MEMSHIPSCHE_Code = mEMSHIPSCHE_Code;
	}
	public String getPRMSCHE_Code() {
		return PRMSCHE_Code;
	}
	public void setPRMSCHE_Code(String pRMSCHE_Code) {
		PRMSCHE_Code = pRMSCHE_Code;
	}
	public int getMEMSHIP_Price() {
		return MEMSHIP_Price;
	}
	public void setMEMSHIP_Price(int mEMSHIP_Price) {
		MEMSHIP_Price = mEMSHIP_Price;
	}
	public int getPRMSCHE_PRICE() {
		return PRMSCHE_Price;
	}
	public void setPRMSCHE_PRICE(int pRMSCHE_Price) {
		PRMSCHE_Price = pRMSCHE_Price;
	}
	public String getMEM_Code() {
		return MEM_Code;
	}
	public void setMEM_Code(String mEM_Code) {
		MEM_Code = mEM_Code;
	}
	public String getPRMSCHE_Name() {
		return PRMSCHE_Name;
	}
	public void setPRMSCHE_Name(String pRMSCHE_Name) {
		PRMSCHE_Name = pRMSCHE_Name;
	}
	public String getPRM_Name() {
		return PRM_Name;
	}
	public void setPRM_Name(String pRM_Name) {
		PRM_Name = pRM_Name;
	}
	public String getMEMSHIP_Type() {
		return MEMSHIP_Type;
	}
	public void setMEMSHIP_Type(String mEMSHIP_Type) {
		MEMSHIP_Type = mEMSHIP_Type;
	}

	

	
	
	
	
}
