package service;


import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import model.FileDAO;


public class UploadService implements Service {
       
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
   	 
	   	String saveDir = request.getSession().getServletContext().getRealPath("images");
	   	System.out.println("절대경로 : " + saveDir);
	   	
	   	int maxSize = 1024*1024*15; 
		String encoding = "UTF-8";
			
			// saveDir: 경로
			// maxSize: 크기제한 설정
			// encoding: 인코딩타입 설정
			// new DefaultFileRenamePolicy(): 동일한 이름일 경우 자동으로 (1),(2)..붙게 해줌		
			
		MultipartRequest multi = new MultipartRequest(request, saveDir, maxSize, encoding, new DefaultFileRenamePolicy());
		/* URL check */
		String uri = request.getRequestURI();
		String con = request.getContextPath();
		String command = uri.substring(con.length());
		System.out.println(command);
		
		String name = multi.getParameter("name");
		String title = multi.getParameter("title");
		String file = multi.getFilesystemName("file");
		
		
		FileDAO dao = FileDAO.getInstance(); // static 영역에 올려진 객체를 메소드로 얻어와서 메모리를 절약하는 싱글톤 패턴
		
		int result = dao.insert(name, title, file);
	
		if(result > 0) {
			System.out.println("저장완료!");			
		}else {
			System.out.println("저장실패!");
		}
	}	
}
