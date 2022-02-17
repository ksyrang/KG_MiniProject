package A_Testonly;

import java.sql.Date;

import common.*; 

public class testmain {
	
	
	public static void main(String[] args) {
		String Pay_Code = "pay01";
//		String Pay_Code = "All";
//		testPayTB(Pay_Code);
		
		String Res_Code = "res01";
//		String Res_Code = "All";
//		testResTB(Res_Code);
		String Mem_Code = "mem01";
//		String Mem_Code = "All";
//		testMemTB(Mem_Code);
		
		String Trn_Code = "0";
//		String Trn_Code = "All";
//		testTrnTB(Trn_Code);
		String PrmSche_Code = "0";
//		testPrmScheTB(PrmSche_Code);
		
//		CmnMemDAO CmnMemDAO = new CmnMemDAO();
//		CmnMemDTO CmnMemDTO = new CmnMemDTO(CmnMemDAO.SltResOne(Mem_Code));
//		CmnMemDTO.setMEM_Code("mem02");
//		System.out.println(CmnMemDAO.IstMem(CmnMemDTO));
		
		
//		CmnPayDAO CmnPayDAO = new CmnPayDAO();
//		CmnPayDTO CmnPayDTO = new CmnPayDTO();
//		Date Date = new Date(22, 02, 22);
//		CmnPayDTO.setPAY_Code("pay02");
//		CmnPayDTO.setPAY_Type("cash");
//		CmnPayDTO.setPAY_Date(Date);
//		CmnPayDTO.setMEMSHIPSCHE_Code("memshipsche01");
//		CmnPayDTO.setRES_Code("res01");
//		System.out.println("Istpay result: "+CmnPayDAO.Istpay(CmnPayDTO));
		
		
	}
	private static void testPrmScheTB(String Code) {
		CmnPrmScheDAO DAO = new CmnPrmScheDAO();
		if(Code.equals("All")) {
			System.out.println("==allpaylis==");
			for(CmnPrmScheDTO dto : DAO.SltPrmScheAll()) {
				System.out.println(dto.getPRMSCHE_Code());
				System.out.println(dto.getPRMSCHE_Strdate());
				System.out.println(dto.getPRMSCHE_Enddate());
				System.out.println(dto.getPRMSCHE_Time());
				System.out.println(dto.getPRMSCHE_LimitP());	
				System.out.println(dto.getPRMSCHE_CurrentP());
				System.out.println(dto.getPRMSCHE_Price());
				//System.out.println(dto.getPRMSCHE_Name());
				System.out.println(dto.getPRM_Code());
				System.out.println(dto.getTRAINER_Code());
				System.out.println("=split line=");
			}
		}
		else {
			CmnPrmScheDTO dto = new CmnPrmScheDTO(DAO.SltPrmScheOne(Code));
			System.out.println(dto.getPRMSCHE_Code());
			System.out.println(dto.getPRMSCHE_Strdate());
			System.out.println(dto.getPRMSCHE_Enddate());
			System.out.println(dto.getPRMSCHE_Time());
			System.out.println(dto.getPRMSCHE_LimitP());	
			System.out.println(dto.getPRMSCHE_CurrentP());
			System.out.println(dto.getPRMSCHE_Price());
			//System.out.println(dto.getPRMSCHE_Name());
			System.out.println(dto.getPRM_Code());
			System.out.println(dto.getTRAINER_Code());
		}
	}
	private static void testTrnTB(String Code) {
		CmnTrainerDAO DAO = new CmnTrainerDAO();
		if(Code.equals("All")) {
			System.out.println("==allpaylis==");
			for(CmnTrainerDTO dto : DAO.SltTrnAll()) {
				System.out.println(dto.getTRAINER_Code());
				System.out.println(dto.getTRAINER_Name());
				System.out.println(dto.getTRAINER_ID());
				System.out.println(dto.getTRAINER_PW());
				System.out.println(dto.getTRAINER_Gender());	
				System.out.println(dto.getTRAINER_Birth());
				System.out.println(dto.getTRAINER_Mobile());
				System.out.println(dto.getTRAINER_Career());
				System.out.println(dto.getTRAINER_Addr());
				System.out.println("=split line=");
			}
		}
		else {
			CmnTrainerDTO dto = new CmnTrainerDTO(DAO.SltTrnOne(Code));
			System.out.println(dto.getTRAINER_Code());
			System.out.println(dto.getTRAINER_Name());
			System.out.println(dto.getTRAINER_ID());
			System.out.println(dto.getTRAINER_PW());
			System.out.println(dto.getTRAINER_Gender());	
			System.out.println(dto.getTRAINER_Birth());
			System.out.println(dto.getTRAINER_Mobile());
			System.out.println(dto.getTRAINER_Career());
			System.out.println(dto.getTRAINER_Addr());
		}
	}
	private static void testMemTB(String Code) {
		CmnMemDAO CmnMemDAO = new CmnMemDAO();
		if(Code.equals("All")) {
			System.out.println("==allpaylis==");
			for(CmnMemDTO dto : CmnMemDAO.SltMemAll()) {
				System.out.println(dto.getMEM_Code());
				System.out.println(dto.getMEM_ID());
				System.out.println(dto.getMEM_PW());
				System.out.println(dto.getMEM_Name());
				System.out.println(dto.getMEM_Gender());	
				System.out.println(dto.getMEM_Birth());
				System.out.println(dto.getMEM_Mobile());
				System.out.println(dto.getMEM_Addr());
				System.out.println(dto.getPRMSCHE_Code());
				System.out.println(dto.getMEMSHIPSCHE_Code());
				System.out.println(dto.getMEM_Approve());
				System.out.println("=split line=");
			}
		}
		else {
			CmnMemDTO dto = new CmnMemDTO(CmnMemDAO.SltMemOne(Code));
			System.out.println(dto.getMEM_Code());
			System.out.println(dto.getMEM_ID());
			System.out.println(dto.getMEM_PW());
			System.out.println(dto.getMEM_Name());
			System.out.println(dto.getMEM_Gender());	
			System.out.println(dto.getMEM_Birth());
			System.out.println(dto.getMEM_Mobile());
			System.out.println(dto.getMEM_Addr());
			System.out.println(dto.getPRMSCHE_Code());
			System.out.println(dto.getMEMSHIPSCHE_Code());
			System.out.println(dto.getMEM_Approve());
		}
	}
	
	private static void testResTB(String Code) {
		CmnResDAO CmnResDAO = new CmnResDAO();
		if(Code.equals("All")) {
			System.out.println("==allpaylis==");
			for(CmnResDTO dto : CmnResDAO.SltResAll()) {
				System.out.println(dto.getMEM_Code());
				System.out.println(dto.getRES_Code());
				System.out.println(dto.getMEM_Code());
				System.out.println(dto.getPRMSCHE_Code());
				System.out.println("=split line=");
			}
		}else {
			CmnResDTO CmnResDTO = new CmnResDTO(CmnResDAO.SltResOne(Code));
			System.out.println(CmnResDTO.getMEM_Code());
			System.out.println(CmnResDTO.getRES_Code());
			System.out.println(CmnResDTO.getMEM_Code());
			System.out.println(CmnResDTO.getPRMSCHE_Code());
		}
	}
	
	private static void testPayTB(String Code) {		
		CmnPayDAO CmnPayDAO = new CmnPayDAO();
		if(Code.equals("All")) {
			System.out.println("==allpaylis==");
			for(CmnPayDTO dto : CmnPayDAO.SltPayAll()) {
				System.out.println("PAY_Code: "+dto.getPAY_Code());
				System.out.println("PAY_Type: "+dto.getPAY_Type());
				System.out.println("PAY_Date: "+dto.getPAY_Date());
				System.out.println("MEMSHIPSCHE_Code: "+dto.getMEMSHIPSCHE_Code());
				System.out.println("RES_Code: "+dto.getRES_Code());
				System.out.println("=split line=");
			}
		}else {
			CmnPayDTO CmnPayDTO = new CmnPayDTO(CmnPayDAO.SltPayOne(Code));
			System.out.println("PAY_Code: "+CmnPayDTO.getPAY_Code());
			System.out.println("PAY_Type: "+CmnPayDTO.getPAY_Type());
			System.out.println("PAY_Date: "+CmnPayDTO.getPAY_Date());
			System.out.println("MEMSHIPSCHE_Code: "+CmnPayDTO.getMEMSHIPSCHE_Code());
			System.out.println("RES_Code: "+CmnPayDTO.getRES_Code());
		}
	}
	
	

}
