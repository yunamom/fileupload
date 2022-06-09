package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;




public class FileDAO {
	
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private int result = 0;
	String mvURL = null;

	private static FileDAO instance = new FileDAO();
	
	public static FileDAO getInstance() {
		return instance;
	}
	
	//DB 연결하기
	public static Connection getConnection() throws Exception {
				
		String url = "jdbc:mysql://localhost:3306/apple?useUnicode=true&characterEncoding=utf8";
		String id = "root";
		String pw = "root";
		//접속드라이버 연결
		Class.forName("com.mysql.cj.jdbc.Driver");
		//접속정보 세팅
		Connection con = DriverManager.getConnection(url, id, pw);
		//접속한 MySQL의 SQL실행 결과를 위한 메모리 공간 확보
		return con;
		
	}
	
	//DB 연결 종료하기
	public void close() throws SQLException {
		if(rs != null) rs.close();
		if(pstmt != null) pstmt.close();
		if(conn != null) conn.close();
	}
	
	//파일업로드 하기 
	public int insert(HttpServletRequest request, HttpServletResponse response){
		
		try {
			
			FileDAO dao = new FileDAO();
			
			ServletContext text = request.getServletContext(); //어플리케이션에 대한 정보를 ServletContext 객체가 갖게 됨.
			String saveDir = text.getRealPath("Upload"); //절대 경로 가져오기
			System.out.println("절대경로 >> " + saveDir);
				
			int maxSize = 3*1024*1024; // 3MB
			String encoding = "UTF-8";
				
				// saveDir: 경로
				// maxSize: 크기제한 설정
				// encoding: 인코딩타입 설정
				// new DefaultFileRenamePolicy(): 동일한 이름일 경우 자동으로 (1),(2)..붙게 해줌		
				
			MultipartRequest multi = new MultipartRequest(request, saveDir, maxSize, encoding, new DefaultFileRenamePolicy());
			
			String name = multi.getParameter("name");
			String title = multi.getParameter("title");
			String file = multi.getFilesystemName("file");
			System.out.println(name+title+file);
		
		
		conn = getConnection();
		
		String sql = " INSERT INTO fileboard ";
		       sql+= " (name, title, fileName, hits, uploadDate) ";
               sql+= " VALUES( ?, ?, ?, 0, sysdate() )";
		
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, name);
		pstmt.setString(2, title);
		pstmt.setString(3, file);
		
		result = pstmt.executeUpdate();
		
		close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	//파일총갯수
	public int countAll() {
		int total = 0;
		try {
			conn = getConnection();
			
			String sql = "SELECT COUNT(*) FROM fileboard";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) total = rs.getInt(1);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return total;
	}
	
	//파일불러오기
	public String selectAll(HttpServletRequest request, HttpServletResponse response) {

		String view = request.getParameter("view");
		
		/* 현재 페이지 */
		int viewPage = (view == null) ? 1 : Integer.parseInt(view);
		
		/* 한 블럭( range : 범위 ) 의 페이지 수 */
		int rangeSize = 5; 
		
		int end = 5; // 한 페이지의 게시물 수 
		int start = (viewPage-1)*rangeSize;
		
		
		/* 총 파일 갯수 */
		int total = countAll();
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
		
		try {
			conn = getConnection();
			
			ArrayList<File> list = new ArrayList<>();
			
			String sql = " SELECT * FROM fileboard ";
			       sql+= " ORDER BY unq DESC LIMIT ?,?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				File file = new File();
				file.setUnq(rs.getInt(1));
				file.setName(rs.getString(2));
				file.setTitle(rs.getString(3));
				file.setFileName(rs.getString(4));
				file.setHits(rs.getInt(5));
				file.setUploadDate(rs.getString(6));
				list.add(file);
			}
			Paging paging = new Paging();			
			paging.setView(viewPage);
			paging.setRange(rangeSize);
			paging.setStart(startRange);
			paging.setEnd(endRange);
			paging.setEndPage(endPage);
			
			request.setAttribute("list", list);
			request.setAttribute("paging", paging);
			
			close();
		
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "list.jsp";
	}
}
