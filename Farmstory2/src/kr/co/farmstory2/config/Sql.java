package kr.co.farmstory2.config;

public class Sql {
	// Member 관련
	public static final String SELECT_TERMS = "SELECT * FROM `JBOARD_TERMS`";
	
	public static final String INSERT_USER = "INSERT INTO `JBOARD_MEMBER` SET"
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
											+ "`rdate`=NOW()";
	
	public static final String SELECT_COUNT_USER = "SELECT COUNT(*) FROM `JBOARD_MEMBER` WHERE `uid`=?";
	
	public static final String SELECT_USER = "SELECT * FROM `JBOARD_MEMBER` "
											+ "WHERE `uid`=? AND `pass`=PASSWORD(?)";
	
	// Write 관련
	public static final String INSERT_COMMENT = "INSERT INTO `JBOARD_ARTICLE` SET "
												+ "`parent`=?, "
												+ "`content`=?, "
												+ "`uid`=?, "
												+ "`regip`=?, "
												+ "`rdate`=NOW()";
	
	public static final String SELECT_COMMENTS = "SELECT * FROM `JBOARD_ARTICLE` "
												+ "WHERE `parent`=? "
												+ "ORDER BY `seq` ASC";
	
	public static final String INSERT_ARTICLE = "INSERT INTO `JBOARD_ARTICLE` SET "
												+ "`cate`=?,"
												+ "`title`=?,"
												+ "`content`=?,"
												+ "`uid`=?,"
												+ "`regip`=?,"
												+ "`rdate`=NOW()";
	
	public static final String SELECT_COUNT_TOTAL = "SELECT COUNT(*) FROM `JBOARD_ARTICLE` "
            									+ "WHERE `parent`=0 AND `cate`='free'";
	
	public static final String SELECT_ARTICLE = "SELECT * FROM `JBOARD_ARTICLE` WHERE `seq`=?";
	
	public static final String SELECT_ARTICLES = "SELECT a.*, b.nick FROM `JBOARD_ARTICLE` AS a "
												+ "JOIN `JBOARD_MEMBER` AS b "
												+ "ON a.uid = b.uid "
            									+ "WHERE `parent`=0 AND `cate`=? "
            									+ "ORDER BY `seq` DESC "
            									+ "LIMIT ?, 10";
	
	public static final String SELECT_LATEST = "(SELECT `seq`, `title`, `rdate` FROM `JBOARD_ARTICLE` WHERE `cate`='grow' ORDER BY `seq` DESC LIMIT 5) "
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
	

}
