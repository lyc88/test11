package com.servlert;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sun.misc.UCDecoder;

/**
 * Servlet implementation class MyServlert
 */
public class MyServlert extends HttpServlet {
	private static final long serialVersionUID = 1L;

  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("hello", URLEncoder.encode("hello","gbk"));
		request.setAttribute("hello", URLEncoder.encode("hello11","gbk"));
		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}

}
