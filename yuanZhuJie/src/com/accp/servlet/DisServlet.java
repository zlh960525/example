package com.accp.servlet;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/servlet")
public class DisServlet extends HttpServlet{
	private static final String SERVLET_PATH="com.accp.servlet";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	
    	String clazz=req.getParameter("clazz");
    	String method=req.getParameter("method");
    	System.out.println(clazz);
    	System.out.println(method);

    try {
		Class cls=Class.forName(SERVLET_PATH+"."+clazz);
		Object obj=cls.newInstance();
		Method invokeMethod =cls.getDeclaredMethod(method,HttpServletRequest.class,HttpServletResponse.class);
		invokeMethod.invoke(obj,req,resp);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	req.setCharacterEncoding("UTF-8");
    	this.doGet(req, resp);
    }
}

