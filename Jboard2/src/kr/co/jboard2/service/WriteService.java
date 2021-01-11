package kr.co.jboard2.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.jboard2.dao.ArticleDao;

public class WriteService implements CommonService {

	@Override
	public String requestProc(HttpServletRequest req, HttpServletResponse resp) {
		
		if(req.getMethod().equals("POST")) {
			
			String uid     = req.getParameter("uid");
			String title   = req.getParameter("title");
			String content = req.getParameter("content");
			String file    = req.getParameter("file");
			String regip   = req.getRemoteAddr();
			
			ArticleDao.getInstance().insertArticle(title, content, uid, regip);
			
			return "redirect:/Jboard2/list.do";
			
		}else {
			return "/write.jsp";
		}
		
		
	}

}