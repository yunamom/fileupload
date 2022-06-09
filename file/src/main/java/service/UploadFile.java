package service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import model.FileDAO;


@WebServlet("/")
public class UploadFile extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		service(request, response);		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		service(request, response);
	}
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* URL check */
		String uri = request.getRequestURI();
		String context = request.getContextPath();
		String command = uri.substring(context.length());
		String site = "upload.jsp";
		
		System.out.println("command : "+command);
		
		FileDAO dao = new FileDAO();
		int result = 0;
		switch(command) {
		case "/File" :
			site = "upload.jsp";
			break;
		case "/UploadFile" : 
			result = dao.insert(request, response);
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			if(result == 1) {
				out.println("<script>");
				out.println(" alert('파일등록이 성공'); location.href='"+context+"';  ");
				out.println("</script>");
				out.flush();
			}else {
				out.println("<script>");
				out.println("alert('등록실패!'); location.href='"+context+"'; ");
				out.println("</script>");
				out.flush();
			}
			break;
		case "/ListFile" : 		
			site = dao.selectAll(request, response);
			break;
		
		}
		/* 결과 */
		RequestDispatcher dispatcher = request.getRequestDispatcher(site);
		dispatcher.forward(request, response);
			
	}
}
