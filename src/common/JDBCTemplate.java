package common;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCTemplate {
	
	public static Connection getConnection() {
		Connection conn=null;
		
		// Db 연결 정보 파일을 읽어오기 위해 선언
		Properties driver = new Properties();
		
		// JDBCTemplate.class와 같은 경로상에서 찾는다는 의미
		// getPath()절대경로를 문자열로 얻어옴
		String fileName =JDBCTemplate.class.getResource("/sql/driver.properties").getPath(); 
		
		
//		System.out.println(fileName);
		
		// 데이터 읽어와서 map 형식으로 저장
		try {
			driver.load(new FileReader(fileName));
			
			// 1. OracleDriver 등록
			Class.forName(driver.getProperty("driver"));
			
			// 2. DBMS와 연결
			conn=DriverManager.getConnection(driver.getProperty("url")
											, driver.getProperty("user")
											, driver.getProperty("password"));
			
			// 3. 트랜잭션 관리는 application에서 수행하기 위해 자동 commit 방지
			conn.setAutoCommit(false); // DML 구문  COMMIT 방지 (insert update delete)
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return conn;
	}
	
	// 사용 객체 반환 메소드(Connection, Statement, ResultSet)
	public static void close(Connection conn) {
		try {
			if(conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// PreparedStatement는 Statement의 후손이므로 부모 타입인 Statement만
	// 작성하면 같이 사용 가능 (다형성)
	public static void close(Statement stmt) {
		try {
			if(stmt != null && !stmt.isClosed()) {
				stmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(ResultSet rset) {
		try {
			if(rset != null && !rset.isClosed()) {
				rset.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void commit(Connection conn) {
		try {
			if(conn!=null && !conn.isClosed()) {
				conn.commit();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void rollback(Connection conn) {
		try {
			if(conn!=null && !conn.isClosed()) {
				conn.rollback();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
