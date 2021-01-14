package kr.co.farmstory2.service.board;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.farmstory2.controller.CommonService;
import kr.co.farmstory2.dao.ArticleDao;
import kr.co.farmstory2.vo.ArticleVo;

public class ListService implements CommonService {

	@Override
	public String requestProc(HttpServletRequest req, HttpServletResponse resp) {
		
		String pg = req.getParameter("pg");
		String group = req.getParameter("group");
		String cate = req.getParameter("cate");
		
		int currentPg = getCurrentPg(pg);
		int start = getLimitStart(currentPg);
		
		List<ArticleVo> articles = ArticleDao.getInstance().selectArticles(cate, start);
		
		req.setAttribute("group", group);
		req.setAttribute("cate", cate);
		req.setAttribute("articles", articles);
		
		return "/board/list.jsp";
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

}
