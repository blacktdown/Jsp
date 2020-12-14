package kr.co.jboard1.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import kr.co.jboard1.bean.ArticleBean;
import kr.co.jboard1.bean.FileBean;
import kr.co.jboard1.db.DBConfig;

public class ArticleDao {
	
	// �̱��� ��ü
	private static ArticleDao instance = new ArticleDao();
	private ArticleDao() {}
	
	public static ArticleDao getInstance() {
		return instance;
	}
	
	private Connection conn = null;
	private PreparedStatement psmt = null;
	private Statement stmt = null;
	private ResultSet rs = null;
	
	public int getLastPgNum(int total) {
		
		int lastPgNum = 0;
		
		if(total % 10 == 0){
			lastPgNum = total / 10;
		}else{
			lastPgNum = total / 10 + 1;
		}
		
		return lastPgNum;
	}
	
	public int getCurrentPg(String pg) {
		int currentPg = 1;
		if(pg != null){
			currentPg = Integer.parseInt(pg);
		}
		
		return currentPg;
	}
	
	public int getLimitStart(int currentPg) {
		
		int limitStart = (currentPg - 1) * 10;
		
		return limitStart;
	}

	public int[] getPageGroup(int currentPg, int lastPgNum) {
		
		int groupCurrent = (int)Math.ceil(currentPg / 5.0);
		int groupStart = (groupCurrent - 1) * 5 + 1;
		int groupEnd = groupCurrent * 5;
		
		if(groupEnd > lastPgNum){
			groupEnd = lastPgNum;
		}
		
		int[] groups = {groupStart, groupEnd};
		
		return groups;
	}
	
	public int getCurrentStartNum(int total, int limitStart) { 	

		return total - limitStart;
	}
	
	public int selectCountArticle() throws Exception {
		
		conn = DBConfig.getInstance().getConnection();
		
		// 3�ܰ�
		stmt = conn.createStatement();
		
		// 4�ܰ�
		String sql = "SELECT COUNT(*) FROM `JBOARD_ARTICLE` ";
		rs = stmt.executeQuery(sql);
		
		// 5�ܰ�
		int total = 0;
		if(rs.next()){
			total = rs.getInt(1);
		}
		
		// 6�ܰ�
		close();
		
		return total;
	}
	
	public void insertFile(FileBean fb) throws Exception {
		
		conn = DBConfig.getInstance().getConnection();
		stmt = conn.createStatement();
		
		String sql = "INSERT INTO `JBOARD_FILE` SET";
			   sql += "`parent`="+fb.getParent()+",";
			   sql += "`oldName`='"+fb.getOldName()+"',";
			   sql += "`newName`='"+fb.getNewName()+"',";
			   sql += "`rdate`=NOW();";
			   
		stmt.executeUpdate(sql);
			   
		close();
		
	}
	
	public int insertArticle(ArticleBean ab) throws Exception {

		
		conn = DBConfig.getInstance().getConnection();
		
		// 3�ܰ�
		stmt = conn.createStatement();
			
		// 4�ܰ�
			String sql  = "INSERT INTO `JBOARD_ARTICLE` SET ";
				   sql += "`title`='"+ab.getTitle()+"',";
				   sql += "`content`='"+ab.getContent()+"',"; 
				   sql += "`file`="+ab.getFile()+","; 
				   sql += "`uid`='"+ab.getUid()+"',";
				   sql += "`regip`='"+ab.getRegip()+"',";
				   sql += "`rdate`=NOW();"; 
				    
			stmt.executeUpdate(sql);
			
		// 5�ܰ�
		// 6�ܰ�
		close();
		
		return selectMaxSeq();

		
	}
	
	public int selectMaxSeq() throws Exception {
		
		conn = DBConfig.getInstance().getConnection();
		stmt = conn.createStatement();
		
		String sql = "SELECT MAX(`seq`) FROM `JBOARD_ARTICLE`;";
		
		rs = stmt.executeQuery(sql);
		
		int parent = 0;
		
		if(rs.next()) {
			parent = rs.getInt(1);
		}
		
		close();
		
		return parent;
		
	}
	
	public ArticleBean selectArticle(String seq) throws Exception {
		
		conn = DBConfig.getInstance().getConnection();
		// 3�ܰ�
		stmt = conn.createStatement();
		
		// 4�ܰ�
		String sql = "SELECT a.*, b.oldName, b.download FROM  `JBOARD_ARTICLE` AS a ";
				sql += "LEFT JOIN `JBOARD_FILE` AS b ";
				sql += "ON a.seq = b.parent ";
				sql += "WHERE a.seq="+seq;
		
		// execute ������ ���� ��Ȳ�϶�
		rs = stmt.executeQuery(sql);
		
		// 5�ܰ�
		ArticleBean ab = new ArticleBean();
		
		if(rs.next()){

			ab.setSeq(rs.getInt(1));
			ab.setParent(rs.getInt(2));
			ab.setComment(rs.getInt(3));
			ab.setCate(rs.getString(4));
			ab.setTitle(rs.getString(5));
			ab.setContent(rs.getString(6));
			ab.setFile(rs.getInt(7));
			ab.setHit(rs.getInt(8));
			ab.setUid(rs.getString(9));
			ab.setRegip(rs.getString(10));
			ab.setRdate(rs.getString(11));
			ab.setOldName(rs.getString(12));
			ab.setDownload(rs.getInt(13));
			
		}
		
		// 6�ܰ�
		conn.close();
		stmt.close();
		rs.close();
		
		return ab;
		
		}
	
	public List<ArticleBean> selectArticles(int limitStart) throws Exception {
		
		conn = DBConfig.getInstance().getConnection();
		
		// 3�ܰ�
		stmt = conn.createStatement();
		
		// 4�ܰ�
		String sql = "SELECT a.*, b.nick FROM `JBOARD_ARTICLE` AS a ";
				sql += "JOIN `JBOARD_MEMBER` AS b ";
				sql += "ON a.uid = b.uid ";
				sql += "ORDER BY `seq` DESC ";
				sql += "LIMIT "+limitStart+", 10;";
		rs = stmt.executeQuery(sql);
		
		// 5�ܰ�
		List<ArticleBean> articles = new ArrayList<>();
		
		while(rs.next()){
				
			ArticleBean ab = new ArticleBean();
			
			ab.setSeq(rs.getInt(1));
			ab.setParent(rs.getInt(2));
			ab.setComment(rs.getInt(3));
			ab.setCate(rs.getString(4));
			ab.setTitle(rs.getString(5));
			ab.setContent(rs.getString(6));
			ab.setFile(rs.getInt(7));
			ab.setHit(rs.getInt(8));
			ab.setUid(rs.getString(9));
			ab.setRegip(rs.getString(10));
			ab.setRdate(rs.getString(11));
			ab.setNick(rs.getString(12));
			
			articles.add(ab);
		}
		
		// 6�ܰ�
		conn.close();
		stmt.close();
		rs.close();
		
		return articles;
		
	}
	
	public void updateArticle(String title, String content, String seq) throws Exception {
		
		conn = DBConfig.getInstance().getConnection();
		
		// 3�ܰ�
		String sql = "UPDATE `JBOARD_ARTICLE` SET `title`=?, `content`=? WHERE `seq`=?";
		psmt = conn.prepareStatement(sql);
		psmt.setString(1, title);
		psmt.setString(2, content);
		psmt.setString(3, seq);
		
		// 4�ܰ�
		psmt.executeUpdate();
		
		// 5�ܰ�
		
		// 6�ܰ�
		close();
	}
	
	public void updateHit(String seq) throws Exception {
		
		conn = DBConfig.getInstance().getConnection();
		
		stmt = conn.createStatement();
		
		String sqlUpdate = "UPDATE `JBOARD_ARTICLE` SET `hit`=`hit`+1 WHERE `seq` =" +seq;	//��ȸ��
		
		stmt.executeUpdate(sqlUpdate);
		
		close();
		
	}
	
	public void deleteArticle(String seq) throws Exception {
		
		// 3�ܰ�
		String sql = "DELETE FROM `JBOARD_ARTICLE` WHERE `seq`=?";
		psmt = conn.prepareStatement(sql);
		psmt.setString(1, seq);
		
		// 4�ܰ�
		psmt.executeUpdate();
		
		// 5�ܰ�
		
		// 6�ܰ�
		conn.close();
		psmt.close();
	}
	
	
	
	public void close() throws Exception{
		if(rs != null) rs.close();
		if(stmt != null) stmt.close();
		if(psmt != null) psmt.close();
		if(conn != null) conn.close();
		
	}
}
