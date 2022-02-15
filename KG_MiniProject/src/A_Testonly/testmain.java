package A_Testonly;

import common.*; 

public class testmain {
	
	
	public static void main(String[] args) {
		String Pay_Code = "pay01";
//		String Pay_Code = "All";
		testPayTB(Pay_Code);
		
	}
	
	private static void testPayTB(String PAY_Code) {
		CmnPayDAO CmnPayDAO = new CmnPayDAO();
		CmnPayDTO CmnPayDTO = new CmnPayDTO(CmnPayDAO.SltPayOne(PAY_Code));
		System.out.println("PAY_Code: "+CmnPayDTO.getPAY_Code());
		System.out.println("PAY_Type: "+CmnPayDTO.getPAY_Type());
		System.out.println("PAY_Date: "+CmnPayDTO.getPAY_Date());
		System.out.println("MEMSHIPSCHE_Code: "+CmnPayDTO.getMEMSHIPSCHE_Code());
		System.out.println("RES_Code: "+CmnPayDTO.getRES_Code());
		
		if(PAY_Code.equals("All")) {
			System.out.println("==allpaylis==");
			for(CmnPayDTO dto : CmnPayDAO.SltPayAll()) {
				System.out.println("PAY_Code: "+dto.getPAY_Code());
				System.out.println("PAY_Type: "+dto.getPAY_Type());
				System.out.println("PAY_Date: "+dto.getPAY_Date());
				System.out.println("MEMSHIPSCHE_Code: "+dto.getMEMSHIPSCHE_Code());
				System.out.println("RES_Code: "+dto.getRES_Code());
				System.out.println("=split line=");
			}	
		}
	}
	
	

}
