package kr.co.jboard2.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.jboard2.controller.CommonService;
import kr.co.jboard2.dao.ArticleDao;
import kr.co.jboard2.vo.ArticleVo;

public class ViewService implements CommonService{

	@Override
	public String requestProc(HttpServletRequest req, HttpServletResponse resp) {
		
		String seq = req.getParameter("seq");
		
		ArticleDao dao = ArticleDao.getInstance();
		ArticleVo article = dao.selectArticle(seq);
		List<ArticleVo> comments = dao.selectComments(seq);
		
		req.setAttribute("article", article);
		req.setAttribute("comments", comments);
		
		return "/view.jsp";
	}

}
