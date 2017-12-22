package test.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import test.board.dto.BoardDto;
import test.util.DbcpBean;

public class BoardDao {
	private static BoardDao dao;
	
	private BoardDao() {}
	
	public static BoardDao getInstance() {
		if(dao==null) {
			dao=new BoardDao();
		}
		return dao;
	}
	
	// 글 정보를 수정하는 메소드 
	public boolean update(BoardDto dto) {
		Connection conn= null;
		PreparedStatement pstmt= null;
		boolean isSuccess= false;
		try {
			// Connection 객체의 참조값 얻어오기
			conn= new DbcpBean().getConn();
			String sql= "UPDATE board_guest "
					+ " SET writer=?,title=?,content=? "
					+ " WHERE num=?";
			pstmt= conn.prepareStatement(sql);
			pstmt.setString(1, dto.getWriter());
			pstmt.setString(2, dto.getTitle());
			pstmt.setString(3, dto.getContent());
			pstmt.setInt(4, dto.getNum());
			// sql 문 수행하기
			int flag= pstmt.executeUpdate();
			if (flag > 0) {
				isSuccess = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt!= null)
					pstmt.close();
				if (conn!= null)
					conn.close();
			} catch (Exception e) {}
		}
		return isSuccess;
	}
	
	
	// 인자로 전달되는 글 번호에 해당하는 정보를 리턴해주는 메소드 
	public BoardDto getData(int num) {
		Connection conn= null;
		PreparedStatement pstmt= null;
		ResultSet rs= null;
		BoardDto dto= null;
		try {
			conn= new DbcpBean().getConn();
			String sql= "SELECT writer,title,content,regdate"
					+ " FROM board_guest "
					+ " WHERE num=?";
			pstmt= conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			// sql 문 수행하고 결과셋 받아오기 
			rs= pstmt.executeQuery();
			if (rs.next()) {
				String writer= rs.getString("writer");
				String title= rs.getString("title");
				String content= rs.getString("content");
				String regdate= rs.getString("regdate");
				// 글 정보를 BoardDto 에 담는다.
				dto= new BoardDto();
				dto.setNum(num);
				dto.setWriter(writer);
				dto.setTitle(title);
				dto.setContent(content);
				dto.setRegdate(regdate);
			}
		} catch (Exception e) {
			e.printStackTrace();
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
		return dto;
	}
	
	// 글 정보를 삭제하는 메소드
	public boolean delete(int num) {
		Connection conn= null;
		PreparedStatement pstmt= null;
		boolean isSuccess= false;
		try {
			// Connection 객체의 참조값 얻어오기
			conn= new DbcpBean().getConn();
			String sql= "DELETE FROM board_guest WHERE num=?";
			pstmt= conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			// sql 문 수행하기
			int flag= pstmt.executeUpdate();
			if (flag > 0) {
				isSuccess= true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt!= null)
					pstmt.close();
				if (conn!= null)
					conn.close();
			} catch (Exception e) {
			}
		}
		return isSuccess;
	}
	
	
	// 글 목록을 리턴해주는 메소드
	public List<BoardDto> getList(){
		Connection conn= null;
		PreparedStatement pstmt= null;
		ResultSet rs= null;
		// 글 목록을 저장할 객체 생성 
		List<BoardDto> list= new ArrayList<>();
		try{
			conn= new DbcpBean().getConn();
			String sql= "SELECT num,writer,title,regdate"
					+ " FROM board_guest"
					+ " ORDER BY num DESC";
			pstmt= conn.prepareStatement(sql);
			// sql 문 수행하고 결과셋 받아오기 
			rs= pstmt.executeQuery();
			while(rs.next()){
				int num= rs.getInt("num");
				String writer= rs.getString("writer");
				String title= rs.getString("title");
				String regdate= rs.getString("regdate");
				// 글 정보를 BoardDto 에 담아서
				BoardDto dto= new BoardDto();
				dto.setNum(num);
				dto.setWriter(writer);
				dto.setTitle(title);
				dto.setRegdate(regdate);
				// List<BoardDto> 객체에 저장한다.
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
	// 글 하나의 정보를 저장하는 메소드
	public boolean insert(BoardDto dto) {
		Connection conn= null;
		PreparedStatement pstmt= null;
		boolean isSuccess= false;
		try{
			// Connection 객체의 참조값 얻어오기
			conn= new DbcpBean().getConn();
			String sql= "INSERT INTO board_guest "
					+ "(num,writer,title,content,regdate) "
				+ "VALUES(board_guest_seq.NEXTVAL,?,?,?,SYSDATE)";
			pstmt= conn.prepareStatement(sql);
			// ? 에 값 바인딩하기
			pstmt.setString(1, dto.getWriter());
			pstmt.setString(2, dto.getTitle());
			pstmt.setString(3, dto.getContent());
			// sql 문 수행하기
			int flag= pstmt.executeUpdate();
			if(flag>0){
				isSuccess=true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(pstmt!=null)pstmt.close();
				// Connection 객체의 .close() 메소드 호출하면 
				// Connection 객체가 알아서 Pool 에 반납된다. 
				if(conn!=null)conn.close();
			}catch(Exception e){}
		}
		return isSuccess;
	}
	
}


















