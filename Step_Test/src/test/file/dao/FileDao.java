package test.file.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import test.file.dto.FileDto;
import test.util.DbcpBean;

public class FileDao {
	private static FileDao dao;
	private FileDao() {}
	public static FileDao getInstance() {
		if(dao==null) {
			dao= new FileDao();
		}
		return dao;
	}
	// 파일 정보를 저장하는 메소드 insert()
	public boolean insert(FileDto dto) {
		Connection conn= null;
		PreparedStatement pstmt= null;
		boolean isSuccess= false;
		try {
			// Connection 객체의 참조값 얻어오기
			conn= new DbcpBean().getConn();
			String sql= "INSERT INTO board_data "
					+ " (num, writer, title, orgFileName, saveFileName, fileSize, regdate) "
					+ " VALUES(board_data_seq.NEXTVAL, ?, ?, ?, ?, ?, SYSDATE)";
			pstmt= conn.prepareStatement(sql);
			// ? 에 값 바인딩하기
			pstmt.setString(1, dto.getWriter());
			pstmt.setString(2, dto.getTitle());
			pstmt.setString(3, dto.getOrgFileName());
			pstmt.setString(4, dto.getSaveFileName());
			// fileSize 는 long type 이므로 .setLong()
			pstmt.setLong(5, dto.getFileSize());
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
	
	// 파일 목록을 리턴해주는 메소드 getList()
	public List<FileDto> getList(){
		Connection conn= null;
		PreparedStatement pstmt= null;
		ResultSet rs= null;
		// 글 목록을 저장할 객체 생성 
		List<FileDto> list= new ArrayList<>();
		try{
			conn= new DbcpBean().getConn();
			String sql= "SELECT num, writer, title, orgFileName, saveFileName, fileSize, regdate"
					+ " FROM board_data"
					+ " ORDER BY num DESC";
			pstmt= conn.prepareStatement(sql);
			// sql 문 수행하고 결과셋 받아오기 
			rs= pstmt.executeQuery();
			while(rs.next()){
				int num= rs.getInt("num");
				String writer= rs.getString("writer");
				String title= rs.getString("title");
				String orgFileName= rs.getString("orgFileName");
				String saveFileName= rs.getString("saveFileName");
				// fileSize 는 long type 이므로 .getLong()
				Long fileSize= rs.getLong("fileSize");
				String regdate= rs.getString("regdate");
				// 글정보를 FileDto 에 담아서
				FileDto dto=new FileDto();
				dto.setNum(num);
				dto.setWriter(writer);
				dto.setTitle(title);
				dto.setOrgFileName(orgFileName);
				dto.setSaveFileName(saveFileName);
				dto.setFileSize(fileSize);
				dto.setRegdate(regdate);
				// List<FileDto> 객체에 저장한다.
				list.add(dto);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				// Connection 객체 반납하기
				if(conn!=null)conn.close();
			}catch(Exception e){}
		}
		// 글 목록을 리턴해준다. 
		return list;
	}
	
	// 다운로드 할 파일의 정보를 리턴해주는 메소드
	public FileDto getData(int num){
		Connection conn= null;
		PreparedStatement pstmt= null;
		ResultSet rs= null;
		// 파일 정보를 저장할 FileDto 
		FileDto dto=null;
		try {
			conn= new DbcpBean().getConn();
			String sql= "SELECT writer, title, orgFileName, saveFileName, fileSize, regdate "
					+" FROM board_data WHERE num=?";
			pstmt= conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			// sql 문 수행하고 resultSet 받아오기
			rs= pstmt.executeQuery();
			while(rs.next()) {	
				dto= new FileDto();
				dto.setNum(num);
				dto.setWriter(rs.getString("writer"));
				dto.setTitle(rs.getString("title"));
				dto.setOrgFileName(rs.getString("orgFileName"));
				dto.setSaveFileName(rs.getString("saveFileName"));
				dto.setFileSize(rs.getLong("fileSize"));
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
	
	// 파일을 삭제하는 메소드
	public boolean delete(int num) {
		Connection conn= null;
		PreparedStatement pstmt= null;
		boolean isSuccess= false;
		try {
			// Connection 객체의 참조값 얻어오기
			conn= new DbcpBean().getConn();
			String sql= "DELETE FROM board_data WHERE num=?";
			pstmt = conn.prepareStatement(sql);
			// ? 에 값 바인딩하기
			pstmt.setInt(1, num);
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
