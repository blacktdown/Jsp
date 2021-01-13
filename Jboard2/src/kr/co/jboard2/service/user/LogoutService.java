package kr.co.jboard2.service.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.jboard2.controller.CommonService;
import kr.co.jboard2.dao.UserDao;
import kr.co.jboard2.vo.MemberVo;

public class LogoutService implements CommonService {

	@Override
	public String requestProc(HttpServletRequest req, HttpServletResponse resp) {

		HttpSession sesstion = req.getSession();
		sesstion.invalidate();
		
			return "redirect:/Jboard2/user/login.do";
		
	}

}