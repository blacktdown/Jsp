package kr.co.jboard2.config;

public class Sql {
	// Member °ú³ã¤¤
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
	
	// Write °ü·Ã
	public static final String INSERT_ARTICLE = "INSERT INTO `JBOARD_ARTICLE` SET "
												+ "`title`=?,"
												+ "`content`=?,"
												+ "`uid`=?,"
												+ "`regip`=?,"
												+ "`rdate`=NOW()";
												
	
}
