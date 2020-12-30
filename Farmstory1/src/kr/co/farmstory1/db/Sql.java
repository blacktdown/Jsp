package kr.co.farmstory1.db;

public class Sql {

	// 회원관련 SQL
	public static final String SELECT_TERMS = "SELECT * FROM `JBOARD_TERMS`";
	public static final String INSERT_USER = "INSERT INTO `JBOARD_MEMBER` SET "
										   + "`uid`=?,"
										   + "`pass`=PASSWORD(?),"
										   + "`name`=?,"
										   + "`nick`=?,"
										   + "`email`=?,"
										   + "`hp`=?,"
										   + "`zip`=?,"
										   + "`addr1`=?,"
										   + "`addr2`=?,"
										   + "`regip`=?,"
										   + "`rdate`=NOW();";
	
	public static final String SELECT_USER = "SELECT * FROM `JBOARD_MEMBER` "
										   + "WHERE "
										   + "`uid`=? AND `pass`=PASSWORD(?)";
			                               
	
	// 게시판관련 SQL
	public static final String INSERT_ARTICLE = "INSERT INTO `JBOARD_ARTICLE` SET "
												+ "`cate`=?,"
												+ "`title`=?,"
												+ "`content`=?,"
												+ "`file`=?,"
												+ "`uid`=?,"
												+ "`regip`=?,"
												+ "`rdate`=NOW()";
	
	public static final String SELECT_ARTICLES = "SELECT * FROM `JBOARD_ARTICLE` WHERE "
												+ "`cate`=? AND `parent`=0";
	
	public static final String SELECT_LATEST1 = "(SELECT `seq`, `title`, `rdate` FROM `JBOARD_ARTICLE` WHERE `cate`='grow' ORDER BY `seq` DESC LIMIT 5) "
											  + "UNION "
											  + "(SELECT `seq`, `title`, `rdate` FROM `JBOARD_ARTICLE` WHERE `cate`='school' ORDER BY `seq` DESC LIMIT 5) "
											  + "UNION "
											  + "(SELECT `seq`, `title`, `rdate` FROM `JBOARD_ARTICLE` WHERE `cate`='story' ORDER BY `seq` DESC LIMIT 5) "
											  + "UNION "
											  + "(SELECT `seq`, `title`, `rdate` FROM `JBOARD_ARTICLE` WHERE `cate`='notice' ORDER BY `seq` DESC LIMIT 3) "
											  + "UNION "
											  + "(SELECT `seq`, `title`, `rdate` FROM `JBOARD_ARTICLE` WHERE `cate`='qna' ORDER BY `seq` DESC LIMIT 3) "
											  + "UNION "
											  + "(SELECT `seq`, `title`, `rdate` FROM `JBOARD_ARTICLE` WHERE `cate`='faq' ORDER BY `seq` DESC LIMIT 3) "; 
									
	
	
	// 기타 SQL
	
}