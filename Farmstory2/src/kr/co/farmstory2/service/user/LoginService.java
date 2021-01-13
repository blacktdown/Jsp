package kr.co.farmstory2.service.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.farmstory2.controller.CommonService;
import kr.co.farmstory2.dao.UserDao;
import kr.co.farmstory2.vo.MemberVo;

public class LoginService implements CommonService {

	@Override
	public String requestProc(HttpServletRequest req, HttpServletResponse resp) {
		
		if(req.getMethod().equals("POST")) {
			
			String uid = req.getParameter("uid");
			String pass = req.getParameter("pass");
			
			MemberVo vo = UserDao.getInstance().selectUser(uid, pass);
			
			if(vo == null) {
				// 회원이 아닐경우
				return "redirect:/Farmstory2/user/login.do";
			}else {
				// 회원이 맞을경우
				HttpSession session = req.getSession();
				session.setAttribute("smember", vo);
				
				return "redirect:/Farmstory2/index.do";
			}
			
		}else {
			return "/user/login.jsp";	
		}
	}

}