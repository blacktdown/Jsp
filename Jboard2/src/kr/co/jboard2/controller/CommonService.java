package kr.co.jboard2.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface CommonService {

	public String requestProc(HttpServletRequest req, HttpServletResponse resp);
}