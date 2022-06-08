package service;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import model.FileDAO;


@WebServlet("/UploadFile")
public class UploadFile extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		service(request, response);		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		service(request, response);
	}
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		FileDAO dao = new FileDAO();
		
		ServletContext text = getServletContext(); //어플리케이션에 대한 정보를 ServletContext 객체가 갖게 됨.
		String saveDir = text.getRealPath("Upload"); //절대 경로 가져오기
		System.out.println("절대경로 >> " + saveDir);
			
		int maxSize = 3*1024*1024; // 3MB
		String encoding = "UTF-8";
			
			// saveDir: 경로
			// maxSize: 크기제한 설정
			// encoding: 인코딩타입 설정
			// new DefaultFileRenamePolicy(): 동일한 이름일 경우 자동으로 (1),(2)..붙게 해줌		
			
		MultipartRequest multi = new MultipartRequest(request, saveDir, maxSize, encoding, new DefaultFileRenamePolicy());
		int result = 0;
		String name = multi.getParameter("name");
		String title = multi.getParameter("title");
		String file = multi.getFilesystemName("file");
		System.out.println(name+title+file);
		
		try {
			
			result = dao.insert(name, title, file);
		} catch (IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.setContentType("text/html; charset=UTF-8");
		
		String alert = result>0 ? "저장완료!" : "저장실패!";
		String mvURL = result>0 ? "ListFile" : "upload.jsp";
		System.out.println(alert);
		response.sendRedirect(mvURL);
	
			
	}
}
