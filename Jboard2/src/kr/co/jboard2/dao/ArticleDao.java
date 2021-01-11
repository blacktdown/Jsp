package kr.co.jboard2.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import kr.co.jboard2.config.DBConfig;
import kr.co.jboard2.config.Sql;

public class ArticleDao {

	private static ArticleDao instance = new ArticleDao();
	
	public static ArticleDao getInstance() {
		return instance;
	}
	
	private ArticleDao() {}
	
	public void insertArticle(String title, String content, String uid, String regip) {
		try {
			Connection conn = DBConfig.getInstance().getConnection();
			PreparedStatement psmt = conn.prepareStatement(Sql.INSERT_ARTICLE);
			psmt.setString(1, title);
			psmt.setString(2, content);
			psmt.setString(3, uid);
			psmt.setString(4, regip);
			
			psmt.executeUpdate();
			psmt.close();
			conn.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void selectArticle() {}
	public void selectArticles() {}
	public void updateArticle() {}
	public void deleteArticle() {}
}