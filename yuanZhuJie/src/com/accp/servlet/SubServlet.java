package com.accp.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.accp.service.ISubService;
import com.accp.service.impl.SubServiceImpl;
import com.accp.student.Subject;

@WebServlet("/sub")
public class SubServlet {
	ISubService service=new SubServiceImpl();
	public void query(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	List<Subject> list=service.query();
	req.setAttribute("list",list);
	req.getRequestDispatcher("WEB-INF/jsp/subject.jsp").forward(req, resp);
	}
	public void queryById(HttpServletRequest req, HttpServletResponse resp) {

	}
	public void toInsert(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("WEB-INF/jsp/subjectInsert.jsp").forward(req, resp);
	}
	public void insert(HttpServletRequest req, HttpServletResponse resp) throws IOException {
         String name=req.getParameter("name");
         int nj=Integer.parseInt(req.getParameter("nj"));
         Subject sub=new Subject();
        
         sub.setSubjectName(name);
         sub.setGradeId(Integer.valueOf(nj));
         boolean bol= service.insert(sub);
         resp.sendRedirect("servlet?clazz=SubServlet&method=query");
	}
	public void toUpdate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
     String id=req.getParameter("id");
     Subject sub= service.queryById(Integer.valueOf(id));
     req.setAttribute("subject",sub);
     req.getRequestDispatcher("WEB-INF/jsp/subjectUpdate.jsp").forward(req, resp);
     
	}
	public void update(HttpServletRequest req, HttpServletResponse resp) throws IOException {
         int id=Integer.parseInt(req.getParameter("id"));
          String name=req.getParameter("name");
          int nj=Integer.parseInt(req.getParameter("nj"));
          Subject sub=new Subject();
          sub.setId(Integer.valueOf(id));
          sub.setSubjectName(name);
          sub.setGradeId(Integer.valueOf(nj));
          boolean bol= service.update(sub);
          resp.sendRedirect("servlet?clazz=SubServlet&method=query");
	}

	public void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    String id=req.getParameter("id");
    Subject sub=new Subject();
    sub.setId(Integer.valueOf(id));
    boolean bol=service.delete(sub);
    resp.sendRedirect("servlet?clazz=SubServlet&method=query");
	}

}
