package mem.Welcome;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import Main.main.Controller;
import common.CmnMemShipDAO;
import common.CmnMemShipDTO;
import common.CmnTrainerDAO;
import common.CmnTrainerDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class MEM_WelcomeDAO {
	private Connection con;
	
	public MEM_WelcomeDAO() {
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "KGGYM";
			String password = "oracle1";
			
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				con = DriverManager.getConnection(url, user, password);
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	
	public ObservableList<MEM_WelcomeDTO> selectMemAllProgram(String id) {
		String sql1 = "SELECT * FROM mem_tb WHERE mem_id=?";
		String sql2 = "SELECT * FROM prmsche_tb WHERE prmsche_code=?";
		String sql3 = "SELECT * FROM memshipsche_tb WHERE memshipsche_code=?";
		PreparedStatement ps1, ps2, ps3;
		ResultSet rs1, rs2, rs3;
		ObservableList<MEM_WelcomeDTO> member = FXCollections.observableArrayList();
		MEM_WelcomeDTO memWelcomeDto;				
		try {
			ps1 = con.prepareStatement(sql1); 
			ps1.setString(1, id);
			rs1 = ps1.executeQuery();
			if(rs1.next()) {
				if(rs1.getString("prmsche_code") != null) {
					ps2 = con.prepareStatement(sql2);
					ps2.setString(1, rs1.getString("prmsche_code"));
					rs2 = ps2.executeQuery();
					if(rs2.next()) {
						memWelcomeDto = new MEM_WelcomeDTO();
						memWelcomeDto.setMem_id(rs1.getString("mem_id"));
						memWelcomeDto.setPrm_code(rs2.getString("prm_code"));
						memWelcomeDto.setPrmsche_code(rs1.getString("prmsche_code"));
//						memWelcomeDto.setPrm_name(rs2.getString("prm_name"));
						memWelcomeDto.setMemshipsche_code(rs1.getString("memshipsche_code"));
						memWelcomeDto.setTrainer_code(rs2.getString("trainer_code"));
						memWelcomeDto.setPrmsche_name(rs2.getString("prmsche_name"));
						memWelcomeDto.setPrmsche_time(rs2.getString("prmsche_time"));
						memWelcomeDto.setPrmsche_strdate(rs2.getDate("prmsche_strdate"));
						memWelcomeDto.setPrmsche_enddate(rs2.getDate("prmsche_enddate"));
						memWelcomeDto.setPrmsche_price(rs2.getInt("prmsche_price"));
						memWelcomeDto.setPrmsche_currentp(rs2.getInt("prmsche_currentp"));
						memWelcomeDto.setPrmsche_limitp(rs2.getInt("prmsche_limitp"));
						member.add(memWelcomeDto);
						
						
						System.out.println("prmsche_code : " + rs1.getString("prmsche_code"));
						System.out.println("prmsche_code : " + member.get(0));
					}
				}
				if(rs1.getString("memshipsche_code") != null) {
					ps3 = con.prepareStatement(sql3);
					ps3.setString(1, rs1.getString("memshipsche_code"));
					rs3 = ps3.executeQuery();
					if(rs3.next()) {
						memWelcomeDto = new MEM_WelcomeDTO();
						memWelcomeDto.setMem_id(rs1.getString("mem_id"));
						memWelcomeDto.setPrm_code(rs3.getString("memship_code")); // *****
 
						CmnMemShipDAO cmnMemShipDao = new CmnMemShipDAO();
						CmnMemShipDTO cmnMemShipDto = new CmnMemShipDTO();
						cmnMemShipDto = cmnMemShipDao.SltMemShipOne(rs3.getString("memship_code")); //****
						memWelcomeDto.setPrm_name(cmnMemShipDto.getMEMSHIP_Type());    //*****
						
						memWelcomeDto.setPrmsche_code(rs1.getString("prmsche_code"));
						memWelcomeDto.setMemshipsche_code(rs1.getString("memshipsche_code"));
//						memWelcomeDto.setTrainer_code(rs2.getString("trainer_code"));
//						memWelcomeDto.setPrmsche_name(rs2.getString("prmsche_name"));
//						memWelcomeDto.setPrmsche_time(rs2.getString("prmsche_time"));
						memWelcomeDto.setTrainer_code(" ");
						memWelcomeDto.setPrmsche_name(" ");
						memWelcomeDto.setPrmsche_time(" ");
						memWelcomeDto.setPrmsche_strdate(rs3.getDate("memshipsche_strdate"));
						memWelcomeDto.setPrmsche_enddate(rs3.getDate("memshipsche_enddate"));
//						memWelcomeDto.setPrmsche_price(rs2.getInt("prmsche_price"));
//						memWelcomeDto.setPrmsche_currentp(rs2.getInt("prmsche_currentp"));
//						memWelcomeDto.setPrmsche_limitp(rs2.getInt("prmsche_limitp"));
						memWelcomeDto.setPrmsche_price(cmnMemShipDto.getMEMSHIP_Price());
						memWelcomeDto.setPrmsche_currentp(0);
						memWelcomeDto.setPrmsche_limitp(0);
						member.add(memWelcomeDto);
						System.out.println("memshipsche_code : " + rs1.getString("memshipsche_code"));
						System.out.println("memshipsche_code : " + member.get(1));
					}
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return member;
	}
	public MEM_WelcomeDTO selectProgram(String id) {
		MEM_WelcomeDTO memWelcomeDto = new MEM_WelcomeDTO();				
		String sql1 = "SELECT * FROM mem_tb WHERE mem_id=?";
		String sql2 = "SELECT * FROM prmsche_tb WHERE prmsche_code=?";
		PreparedStatement ps1, ps2;
		ResultSet rs1, rs2;
		try {
			ps1 = con.prepareStatement(sql1);
			ps1.setString(1, id);
			rs1 = ps1.executeQuery();
			if(rs1.next()) {
				ps2 = con.prepareStatement(sql2);
				ps2.setString(1, rs1.getString("prmsche_code"));
				rs2 = ps2.executeQuery();
				if(rs2.next()) {
					memWelcomeDto.setMem_id(rs1.getString("mem_id"));
					memWelcomeDto.setPrm_code(rs2.getString("prm_code"));
//					memWelcomeDto.setPrm_name(rs2.getString("prm_name"));
					memWelcomeDto.setPrmsche_code(rs1.getString("prmsche_code"));
					memWelcomeDto.setMemshipsche_code(rs1.getString("memshipsche_code"));
					memWelcomeDto.setTrainer_code(rs2.getString("trainer_code"));
					memWelcomeDto.setPrmsche_name(rs2.getString("prmsche_name"));
					memWelcomeDto.setPrmsche_time(rs2.getString("prmsche_time"));
					memWelcomeDto.setPrmsche_strdate(rs2.getDate("prmsche_strdate"));
					memWelcomeDto.setPrmsche_enddate(rs2.getDate("prmsche_enddate"));
					memWelcomeDto.setPrmsche_price(rs2.getInt("prmsche_price"));
					memWelcomeDto.setPrmsche_currentp(rs2.getInt("prmsche_currentp"));
					memWelcomeDto.setPrmsche_limitp(rs2.getInt("prmsche_limitp"));
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return memWelcomeDto;
	}
	
	public String getPrmName(String PrmCode) {
		String prm_name = null;
		String sql = "SELECT prm_name FROM prm_tb WHERE prm_code=?";
		PreparedStatement ps;
		ResultSet rs;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, PrmCode);
			rs = ps.executeQuery();
			if(rs.next()) {
				return rs.getString("prm_name");
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return prm_name;
	}
	
	public String getTrainner(String PrmCode) {
		String prm_name = null;
		String sql = "SELECT prm_name FROM prm_tb WHERE prm_code=?";
		PreparedStatement ps;
		ResultSet rs;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, PrmCode);
			rs = ps.executeQuery();
			if(rs.next()) {
				return rs.getString("prm_name");
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return prm_name;
	}
	
}
