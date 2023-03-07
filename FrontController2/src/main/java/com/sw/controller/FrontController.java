package com.sw.controller;

import java.io.IOException;
import java.security.Provider.Service;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sw.command.InsertCommand;
import com.sw.command.LoginCommand;

import MemberDto.MemberDto;

/**
 * Servlet implementation class FrontController1
 */
@WebServlet({ "/FrontController1", "*.do" })
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FrontController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		actionDo(request, response);
		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		actionDo(request, response);
	}
	protected void actionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String uri = request.getRequestURI();
		String conPath=request.getContextPath();
		String com=uri.substring(conPath.length());
		System.out.println("uri - "+uri);
		System.out.println("conPath - "+conPath);
		System.out.println("com - "+com);
		
		MemberDto mdto = new MemberDto();
		HttpSession session = request.getSession();
		InsertCommand command1 = null;
		LoginCommand command2 = null;
		String result_page=null;
		int result=0;
		String id = null;
		String pw = null;
		String name = null;
		
		if(com.equals("/join.do")) {
			id = request.getParameter("id");
			pw = request.getParameter("pw");
			name=request.getParameter("name");
			mdto.setId(id);
			mdto.setPw(pw);
			mdto.setName(name);
			command1=new InsertCommand();
			result=command1.execute(mdto);
			result_page="insertResult.jsp";
			if(result==1)
			{
			session.setAttribute("insertResult", "join success");
			}
			else {
			session.setAttribute("insertResult", "join fail");
			}
			response.sendRedirect(result_page);
			}
		else if(com.equals("/login.do")) {
			id = request.getParameter("id");
			pw = request.getParameter("pw");
			mdto.setId(id);
			mdto.setPw(pw);
			command2=new LoginCommand();
			result=command2.execute(mdto);
			result_page="loginResult.jsp";
			if(result==1)
			{
				session.setAttribute("loginResult", "login success");
			}
			else {
				session.setAttribute("loginResult", "login fail");
			}
			} 
		else {
				System.out.println("controller error");
			}
				response.sendRedirect(result_page);
			}
}
