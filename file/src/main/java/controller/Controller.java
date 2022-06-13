package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.FileListService;
import service.FileSelectOneService;
import service.Service;
import service.UploadService;


@WebServlet("*.do")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		execute(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		execute(request, response);
	}
	
	private void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String uri = request.getRequestURI();
		String conPath = request.getContextPath();
		String command = uri.substring(conPath.length());
		
		String site = null;
		Service service = null;
		switch(command) {
		case "/Main.do"          :
			site = "fileupload/main.jsp";
			break;
			
		case "/Upload.do"        :
			site = "fileupload/upload.jsp";
			break;	
			
		case "/FileList.do"      :
			service = new FileListService();
			service.execute(request, response);
			site = "fileupload/list.jsp";
			break;
			
		case "/FileSelect.do"     :
			service = new FileSelectOneService();
			service.execute(request, response);
			site = "fileupload/detail.jsp";
			break;
			
		case "/UploadService.do" :
			service = new UploadService();
			service.execute(request, response);
			site = "fileupload/main.jsp";
			break;
		}
		
		
		RequestDispatcher dis = request.getRequestDispatcher(site);
		dis.forward(request, response);

	}
}
