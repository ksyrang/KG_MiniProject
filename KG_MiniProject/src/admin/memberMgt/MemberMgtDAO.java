package admin.memberMgt;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mem.Enroll.EnrollDTO;

public class MemberMgtDAO {
	private Connection con;
	
	public MemberMgtDAO() {
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
	
	// 코드 체크
	public MemberMgtDTO selectCode(String mem_code) {
		String sql = "SELECT * FROM mem_tb WHERE mem_code= ?";
		PreparedStatement ps;
		ResultSet rs;
		
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, mem_code);
			rs = ps.executeQuery();
			MemberMgtDTO memberMgtDto = new MemberMgtDTO();
			if(rs.next()) {
				memberMgtDto.setMem_code(rs.getString("mem_code"));
				memberMgtDto.setMem_id(rs.getString("mem_id"));
				memberMgtDto.setMem_name(rs.getString("mem_name"));
				memberMgtDto.setMem_pw(rs.getString("mem_pw"));
				memberMgtDto.setMem_gender(rs.getString("mem_gender"));
				memberMgtDto.setMem_birth(rs.getInt("mem_birth"));
				memberMgtDto.setMem_mobile(rs.getInt("mem_mobile"));
				memberMgtDto.setMem_addr(rs.getString("mem_addr"));
				memberMgtDto.setMem_approve(rs.getString("mem_approve"));
				
				return memberMgtDto;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	// id 체크
	public MemberMgtDTO selectId(String mem_id) {
		String sql = "SELECT * FROM mem_tb WHERE mem_id= ?";
		PreparedStatement ps;
		ResultSet rs;
		
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, mem_id);
			rs = ps.executeQuery();
			MemberMgtDTO memberMgtDto = new MemberMgtDTO();
			if(rs.next()) {
				memberMgtDto.setMem_code(rs.getString("mem_code"));
				memberMgtDto.setMem_id(rs.getString("mem_id"));
				memberMgtDto.setMem_name(rs.getString("mem_name"));
				memberMgtDto.setMem_pw(rs.getString("mem_pw"));
				memberMgtDto.setMem_gender(rs.getString("mem_gender"));
				memberMgtDto.setMem_birth(rs.getInt("mem_birth"));
				memberMgtDto.setMem_mobile(rs.getInt("mem_mobile"));
				memberMgtDto.setMem_addr(rs.getString("mem_addr"));
				memberMgtDto.setMem_approve(rs.getString("mem_approve"));
				
				return memberMgtDto;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	// 회원 전체 보기
	public ObservableList<MemberMgtDTO> getAllMemberList() {
		String sql = "SELECT mem_code, mem_name, mem_approve FROM mem_tb";
		PreparedStatement ps;
		ResultSet rs;
		ObservableList<MemberMgtDTO> member = FXCollections.observableArrayList();
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				MemberMgtDTO memberMgtDto = new MemberMgtDTO();
				memberMgtDto.setMem_code(rs.getString("mem_code"));
				memberMgtDto.setMem_name(rs.getString("mem_name"));
				memberMgtDto.setMem_approve(rs.getString("mem_approve"));
				member.add(memberMgtDto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return member;
	}
	
	// 회원 승인 여부 보기
	public ObservableList<MemberMgtDTO> getNotApproveList() {
		String sql = "SELECT mem_code, mem_name, mem_approve FROM mem_tb WHERE mem_approve ='false'";
		PreparedStatement ps;
		ResultSet rs;
		ObservableList<MemberMgtDTO> member = FXCollections.observableArrayList();
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				MemberMgtDTO memberMgtDto = new MemberMgtDTO();
				memberMgtDto.setMem_code(rs.getString("mem_code"));
				memberMgtDto.setMem_name(rs.getString("mem_name"));
				memberMgtDto.setMem_approve(rs.getString("mem_approve"));
				member.add(memberMgtDto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return member;
	}
	
	// 가입 승인
	public void approveUpdate(String mem_id) {
		String sql = "UPDATE mem_tb SET mem_approve = 'true' WHERE mem_id = ?";
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, mem_id);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// 회원 수정
	public void memberUpdate(String mem_id, String mem_name, String mem_pw, String mem_gender, int mem_birth, String mem_addr) {
		String sql = "UPDATE mem_tb SET mem_name = ?, mem_pw = ?, mem_gender = ?, mem_birth = ?, mem_addr = ?  WHERE mem_id = ?";
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, mem_name);
			ps.setString(2, mem_pw);
			ps.setString(3, mem_gender);
			ps.setInt(4, mem_birth);
			ps.setString(5, mem_addr);
			ps.setString(6, mem_id);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// 회원 삭제
	public void memberDelete(String mem_id) {
		String sql = "DELETE FROM mem_tb WHERE mem_id = ?";
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, mem_id);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
