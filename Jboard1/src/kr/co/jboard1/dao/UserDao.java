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
	
	public void insertUser(MemberBean mb) throws Exception {
		
		conn = DBConfig.getInstance().getConnection();
		
		// 3�ܰ� - SQL ���ఴü ����
		stmt = conn.createStatement();
		
		// 4�ܰ� - SQL ����
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
				
		// 5�ܰ� - ����� ó��(SELECT�� ���)
		
		
		// 6�ܰ� - �����ͺ��̽� ����
		conn.close();
		stmt.close();
	}
	
	public MemberBean selectUser(String uid, String pass) throws Exception {
		
		conn = DBConfig.getInstance().getConnection();
		
		// 3�ܰ�
		stmt = conn.createStatement();
		
		// 4�ܰ�
		String sql = "SELECT * FROM `JBOARD_MEMBER` ";
				sql += "WHERE `uid` = '"+uid+"' AND `pass`=PASSWORD('"+pass+"');";
		
		rs = stmt.executeQuery(sql); // select ��� query ����
		
		// 5�ܰ� - �����ó��(SECELT ���)
		MemberBean mb = null;
		
		if(rs.next()){
			
			// ȸ���� ���� ���
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
		
		// 6�ܰ� - �����ͺ��̽� ����.
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
		
		// 3�ܰ� - SQL ���ఴü ����
		stmt = conn.createStatement();
		
		// 4�ܰ� - SQL ����
		String sql = "SELECT * FROM `JBOARD_TERMS`;";
		rs = stmt.executeQuery(sql);
		
		// 5�ܰ� - ����� ó��(SELECT�� ��� - Bean�� ���)
		TermsBean tb = new TermsBean();
		
		if(rs.next()){
			tb.setTerms(rs.getString(1));
			tb.setPrivacy(rs.getString(2));
		}
		
		// 6�ܰ� - �����ͺ��̽� ����
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