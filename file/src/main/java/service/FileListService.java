package service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.File;
import model.FileDAO;
import model.Paging;


public class FileListService implements Service {
       
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		FileDAO dao = FileDAO.getInstance();
		
		String view = request.getParameter("view");
		
		/* 현재 페이지 */
		int viewPage = (view == null) ? 1 : Integer.parseInt(view);
		
		/* 한 블럭( range : 범위 ) 의 페이지 수 */
		int rangeSize = 5; 
		
		int end = 5; // 한 페이지의 게시물 수 
		int start = (viewPage-1)*rangeSize;
		
		
		/* 총 파일 갯수 */
		int total = dao.countAll();
		// 마지막 페이지 
		int endPage = (int)Math.ceil((double)total/5);		
		
		// ( range : 범위 ) 
		int Range = (viewPage % rangeSize == 0)?viewPage/rangeSize:(viewPage/rangeSize)+1;
		// 이전버튼 블럭( range : 범위 ) 
		int startRange = (Range-1)*rangeSize+1;
		// 다음버튼 블럭( range : 범위 ) 
		int endRange = startRange + rangeSize-1;
		// 마지막 블락에서 총페이지수를 넘어가면 끝 페이지를 마지막 페이지 숫자로 넣어줍니다. 
		endRange = (endRange >= endPage) ? endPage : endRange;
		
		ArrayList<File> list = dao.selectAll(start, end, total);
		
		
		Paging paging = new Paging();			
		paging.setView(viewPage);
		paging.setRange(rangeSize);
		paging.setStart(startRange);
		paging.setEnd(endRange);
		paging.setEndPage(endPage);
		
		request.setAttribute("list", list);
		request.setAttribute("paging", paging);	
	}
}