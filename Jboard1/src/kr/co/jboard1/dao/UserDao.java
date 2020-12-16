package kr.co.jboard1.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import kr.co.jboard1.bean.MemberBean;
import kr.co.jboard1.bean.TermsBean;
import kr.co.jboard1.db.DBConfig;

public class UserDao {
	
	private static UserDao instance = new UserDao();
	
	private UserDao() {}
	
	public static UserDao getInstance() {
		return instance;
	}
	
	private Connection conn;
	private PreparedStatement psmt;
	private Statement stmt;
	private ResultSet rs;
	
	public int selectCountUser(String uid) throws Exception {
		conn = DBConfig.getInstance().getConnection();
		stmt = conn.createStatement();
		
		String sql = "SELECT COUNT(*) FROM `JBOARD_MEMBER` WHERE `uid`='"+uid+"';";
		rs = stmt.executeQuery(sql);
		
		int count = 0;
		
		if(rs.next()) {
			count = rs.getInt(1);
		}
		
		close();
		
		return count;
	}
	
	public int selectCountNick(String nick) throws Exception {
		conn = DBConfig.getInstance().getConnection();
		stmt = conn.createStatement();
		
		String sql = "SELECT COUNT(*) FROM `JBOARD_MEMBER` WHERE `nick`='"+nick+"';";
		rs = stmt.executeQuery(sql);
		
		int count = 0;
		
		if(rs.next()) {
			count = rs.getInt(1);
		}
		
		close();
		
		return count;
	}
	
	public int selectCountHp(String hp) throws Exception {
		conn = DBConfig.getInstance().getConnection();
		stmt = conn.createStatement();
		
		String sql = "SELECT COUNT(*) FROM `JBOARD_MEMBER` WHERE `hp`='"+hp+"';";
		rs = stmt.executeQuery(sql);
		
		int count = 0;
		
		if(rs.next()) {
			count = rs.getInt(1);
		}
		
		close();
		
		return count;
	}
	
	public void insertUser(MemberBean mb) throws Exception {
		
		conn = DBConfig.getInstance().getConnection();
		
		// 3단계 - SQL 실행객체 생성
		stmt = conn.createStatement();
		
		// 4단계 - SQL 실행
		String sql = "INSERT INTO `JBOARD_MEMBER` SET ";
				sql += "`uid`='"+mb.getUid()+"',";
				sql += "`pass`=PASSWORD('"+mb.getPass()+"'),";
				sql += "`name`='"+mb.getName()+"',";
				sql += "`nick`='"+mb.getNick()+"',";
				sql += "`email`='"+mb.getEmail()+"',";
				sql += "`hp`='"+mb.getHp()+"',";
				sql += "`zip`='"+mb.getZip()+"',";
				sql += "`addr1`='"+mb.getAddr1()+"',";
				sql += "`addr2`='"+mb.getAddr2()+"',"; 
				sql += "`regip`='"+mb.getRegip()+"',"; 
				sql += "`rdate`=NOW();"; 
				
		stmt.executeUpdate(sql);
				
		// 5단계 - 결과셋 처리(SELECT일 경우)
		
		
		// 6단계 - 데이터베이스 종료
		conn.close();
		stmt.close();
	}
	
	public MemberBean selectUser(String uid, String pass) throws Exception {
		
		conn = DBConfig.getInstance().getConnection();
		
		// 3단계
		stmt = conn.createStatement();
		
		// 4단계
		String sql = "SELECT * FROM `JBOARD_MEMBER` ";
				sql += "WHERE `uid` = '"+uid+"' AND `pass`=PASSWORD('"+pass+"');";
		
		rs = stmt.executeQuery(sql); // select 경우 query 생성
		
		// 5단계 - 결과셋처리(SECELT 경우)
		MemberBean mb = null;
		
		if(rs.next()){
			
			// 회원이 맞을 경우
			mb = new MemberBean();
			
			mb.setUid(rs.getString(1));
			mb.setPass(rs.getString(2));
			mb.setName(rs.getString(3));
			mb.setNick(rs.getString(4));
			mb.setEmail(rs.getString(5));
			mb.setHp(rs.getString(6));
			mb.setGrade(rs.getInt(7));
			mb.setZip(rs.getString(8));
			mb.setAddr1(rs.getString(9));
			mb.setAddr2(rs.getString(10));
			mb.setRegip(rs.getString(11));
			mb.setRdate(rs.getString(12));
			
		}
		
		// 6단계 - 데이터베이스 종료.
		conn.close();
		stmt.close();
		rs.close();
		
		return mb;

	}
	public void selectUsers() throws Exception {}
	public void updateUser() throws Exception {}
	public void deleteUser() throws Exception {}
	
	public TermsBean selectTerms() throws Exception {
		
		conn = DBConfig.getInstance().getConnection();
		
		// 3단계 - SQL 실행객체 생성
		stmt = conn.createStatement();
		
		// 4단계 - SQL 실행
		String sql = "SELECT * FROM `JBOARD_TERMS`;";
		rs = stmt.executeQuery(sql);
		
		// 5단계 - 결과셋 처리(SELECT일 경우 - Bean을 사용)
		TermsBean tb = new TermsBean();
		
		if(rs.next()){
			tb.setTerms(rs.getString(1));
			tb.setPrivacy(rs.getString(2));
		}
		
		// 6단계 - 데이터베이스 종료
		conn.close();
		stmt.close();
		rs.close();
		
		return tb;
	}
	
	public void close() throws Exception{
		if(rs == null) rs.close();
		if(stmt != null) stmt.close();
		if(psmt != null) psmt.close();
		if(conn != null) conn.close();
		
	}

}
