package kr.co.jboard2.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.jboard2.controller.CommonService;
import kr.co.jboard2.dao.ArticleDao;
import kr.co.jboard2.vo.ArticleVo;

public class CommentService implements CommonService{

	@Override
	public String requestProc(HttpServletRequest req, HttpServletResponse resp) {
		
		if(req.getMethod().equals("POST")) {
			
			String uid = req.getParameter("uid");
			String parent = req.getParameter("parent");
			String comment = req.getParameter("comment");
			String regip = req.getRemoteAddr();
			
			ArticleDao.getInstance().insertComment(parent, comment, uid, regip);
			
			return "redirect:/Jboard2/view.do?seq="+parent;
			
		}else {

			return null;
		
		}
	}
}
