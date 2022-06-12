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
	public int insert(String name, String title, String file){
		
		try {
			
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
	
	//파일목록 조회 (start, end)
	public ArrayList<File> selectAll(int start, int end) {

		ArrayList<File> list = new ArrayList<>();
		
		
		try {
			conn = getConnection();		
			
			String sql = " SELECT ";
			       sql+= " unq, ";
			       sql+= " name, ";
			       sql+= " title, ";
			       sql+= " fileName, ";
			       sql+= " hits, ";
			       sql+= " DATE_FORMAT(uploadDate, '%y/%m/%d')uploadDate ";
			       sql+= " FROM fileboard ";
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
			
			close();
		
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;

	}
	//선택된 파일 불러오기
	public File selectOne(String unq) {
		
		File file = new File();
		try {
			conn = getConnection();
			
			
			String sql = " SELECT ";
			       sql+= " unq, ";
			       sql+= " name, ";
			       sql+= " title, ";
			       sql+= " fileName, ";
			       sql+= " hits, ";
			       sql+= " DATE_FORMAT(uploadDate, '%y/%m/%d')uploadDate ";
			       sql+= " FROM fileboard ";
			       sql+= " WHERE unq ="+unq;
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			rs.next();
			
			
			file.setUnq(rs.getInt(1));
			file.setName(rs.getString(2));
			file.setTitle(rs.getString(3));
			file.setFileName(rs.getString(4));
			file.setHits(rs.getInt(5));
			file.setUploadDate(rs.getString(6));				
			
			
			close();	
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return file;
	}
}
