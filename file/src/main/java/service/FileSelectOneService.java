package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.File;
import model.FileDAO;


public class FileSelectOneService implements Service {

    
    public void execute(HttpServletRequest request, HttpServletResponse response) {
    	String unq = request.getParameter("unq");
    	FileDAO dao = FileDAO.getInstance();
    	
    	dao.updateHit(unq); // 조회수증가 
    	request.setAttribute("file", dao.selectOne(unq));
    }
}
