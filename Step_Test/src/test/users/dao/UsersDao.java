package test.users.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import test.users.dto.UsersDto;
import test.util.DbcpBean;

public class UsersDao {
	private static UsersDao dao;
	
	private UsersDao() {}
	
	public static UsersDao getInstance() {
		if(dao==null) {
			dao= new UsersDao();
		}
		return dao;
	}
	
	// 회원정보를 저장하는 메소드 
	public boolean insert(UsersDto dto) {
		Connection conn= null;
		PreparedStatement pstmt= null;
		boolean isSuccess= false;
		try {
			// Connection 객체의 참조값 얻어오기
			conn= new DbcpBean().getConn();
			String sql= "INSERT INTO users (id,pwd,email,regdate) VALUES(?,?,?,SYSDATE)";
			pstmt = conn.prepareStatement(sql);
			// ? 에 값 바인딩하기
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getPwd());
			pstmt.setString(3, dto.getEmail());
			// sql 문 수행하기
			int flag= pstmt.executeUpdate();
			if(flag>0) {
				isSuccess= true;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				// Connection 객체의 .close() 메소드 호출하면
				// Connection 객체가 알아서 Pool 에 반납된다.
				if(conn!=null) conn.close();
				if(pstmt!=null) pstmt.close();
			}catch(Exception e) {}
		}
		return isSuccess;
	}
	
	// 아이디 비밀번호가 유효한지 여부를 리턴하는 메소드
		//rs.next 가 true 인지 아닌지만 확인 하면 돼! (즉, SELECT 된 row 가 존재하는가)
		//id, pwd 둘 중에 하나라도 틀리면 select 된 row 없음 (sql 문 에서 AND 사용)
	public boolean isValid(UsersDto dto){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		// 아이디와 비밀번호가 맞는 정보인지 여부 
		boolean isValid=false;
		try {
			conn = new DbcpBean().getConn();
			String sql = "SELECT * FROM users WHERE id=? AND pwd=?";
			pstmt = conn.prepareStatement(sql);
			// ? 에 아이디와 비밀번호를 바인딩하고 
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getPwd());
			// SELECT 해서 
			rs = pstmt.executeQuery();
			while (rs.next()) {	// row 가 하나라도 있으면 
				isValid=true; 	// 유효한 정보이다. 
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			try {
				if (rs!= null)
					rs.close();
				if (pstmt!= null)
					pstmt.close();
				if (conn!= null)
					conn.close();
			} catch (Exception e) {
			}
		}
		// 아이디 비밀번호가 유효한 정보인지 여부를 리턴해준다.
		return isValid;
	}	// isValid()	
	
	// 회원 정보를 불러오는 메소드
	public UsersDto getData(String id) {
		Connection conn= null;
		PreparedStatement pstmt= null;
		ResultSet rs= null;
		UsersDto dto= new UsersDto();
		try {
			conn= new DbcpBean().getConn();
			String sql= "SELECT pwd,email,regdate FROM users WHERE id=?";
			pstmt= conn.prepareStatement(sql);
			pstmt.setString(1, id);
			// sql 문 수행하고 resultSet 받아오기
			rs=pstmt.executeQuery();
			while(rs.next()) {				
				dto.setId(id);
				dto.setPwd(rs.getString("pwd"));
				dto.setEmail(rs.getString("email"));
				dto.setRegdate(rs.getString("regdate"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				// Connection 객체 반납하기 (overriding 되어 있어서 실제로 close 되지는 않고 반납만 됨)
				if(conn!=null) conn.close();
				if(pstmt!=null) pstmt.close();
				if(rs!=null) rs.close();
			}catch(Exception e) {}
		}
		return dto;
	}
	
	// 회원정보를 수정하는 메소드
	public boolean update(UsersDto dto) {
		Connection conn= null;
		PreparedStatement pstmt= null;
		boolean isSuccess= false;
		try {
			// Connection 객체의 참조값 얻어오기
			conn= new DbcpBean().getConn();
			String sql= "UPDATE users SET pwd=?, email=? WHERE id=?";
			pstmt = conn.prepareStatement(sql);
			// ? 에 값 바인딩하기
			pstmt.setString(1, dto.getPwd());
			pstmt.setString(2, dto.getEmail());
			pstmt.setString(3, dto.getId());
			// sql 문 수행하기
			int flag= pstmt.executeUpdate();
			if(flag>0) {
				isSuccess= true;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				// Connection 객체의 .close() 메소드 호출하면
				// Connection 객체가 알아서 Pool 에 반납된다.
				if(conn!=null) conn.close();
				if(pstmt!=null) pstmt.close();
			}catch(Exception e) {}
		}
		return isSuccess;
	}
	
	// 회원 정보를 삭제하는 메소드
	public boolean delete(String id) {
		Connection conn= null;
		PreparedStatement pstmt= null;
		boolean isSuccess= false;
		try {
			// Connection 객체의 참조값 얻어오기
			conn= new DbcpBean().getConn();
			String sql= "DELETE FROM users WHERE id=?";
			pstmt = conn.prepareStatement(sql);
			// ? 에 값 바인딩하기
			pstmt.setString(1, id);
			// sql 문 수행하기
			int flag= pstmt.executeUpdate();
			if(flag>0) {
				isSuccess= true;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(conn!=null) conn.close();
				if(pstmt!=null) pstmt.close();
			}catch(Exception e) {}
		}
		return isSuccess;
	}
}
