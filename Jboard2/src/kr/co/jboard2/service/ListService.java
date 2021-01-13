package kr.co.jboard2.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.jboard2.controller.CommonService;
import kr.co.jboard2.dao.ArticleDao;
import kr.co.jboard2.vo.ArticleVo;

public class ListService implements CommonService {

	@Override
	public String requestProc(HttpServletRequest req, HttpServletResponse resp) {
		
		String pg = req.getParameter("pg");
		
		ArticleDao dao = ArticleDao.getInstance();
		int total = dao.selectCountTotal();
		
		int currentPg = getCurrentPg(pg);
		int start = getLimitStart(currentPg);
		int lastPgNum = getLastPgNum(total);
		int listCount = getListCountNum(total, start);
		
		List<ArticleVo> articles = dao.selectArticles(start);
		
		req.setAttribute("articles", articles);
		req.setAttribute("lastPgNum", lastPgNum);
		req.setAttribute("listCount", listCount+1);
		
		return "/list.jsp";
	}
	
	public int getListCountNum(int total, int start) {
		return total-start;
	}
	
	public int getLastPgNum(int total) {
		int lastPgNum = 0;
		
		if(total % 10 == 0) {
			lastPgNum = total / 10;
		}else {
			lastPgNum = total / 10 + 1;
		}
		return lastPgNum;
	}
	
	public int getCurrentPg(String pg) {
		int currentPg = 1;
		if(pg != null) {
			currentPg = Integer.parseInt(pg);
		}
		return currentPg;
	}
	
	public int getLimitStart(int currentPg) {

		return (currentPg - 1) * 10;
		
	}
	
	// 글 리스트번호 구하기
	public void getListCount() {
		
	}

}
