package manager.model.dao;

import static common.JDBCTemplate.close;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import manager.model.vo.Advertise;
import manager.model.vo.Member;
import manager.model.vo.PageInfo;
import manager.model.vo.Report;

public class ManagerDao {
	
	private Properties managerQuery = new Properties();
	
	public ManagerDao() {
		
	      String fileName = ManagerDao.class.getResource("/sql/manager/manager-query.xml").getPath();
	      
	      try {
	    	  managerQuery.loadFromXML(new FileInputStream(fileName));
	      } catch (IOException e) {
	         e.printStackTrace();
	      }
	   }

	
	public Member selectMember(Connection conn, String searchCondition, String searchValue) {
		  PreparedStatement pstmt = null;
	      ResultSet rset = null;
	      Member member = null;
	      String sql = "";
	      
	      if(searchCondition.equals("userNo")) {
			   sql = managerQuery.getProperty("searchUserNo");
		   }else if(searchCondition.equals("nickName")) {
			   sql = managerQuery.getProperty("searchNickName");
		   }
	      
	      try {
	    	  pstmt = conn.prepareStatement(sql);
			
			  if(sql == managerQuery.getProperty("searchUserNo")) {
				  pstmt.setInt(1, Integer.parseInt(searchValue));
			  }else if(sql == managerQuery.getProperty("searchNickName")) {
				  pstmt.setString(1, searchValue);
			  }
			  
			  rset = pstmt.executeQuery();
			  
			  if(rset.next()) {
				member = new Member();
				member.setUserNo(rset.getInt("user_no"));
				member.setUserId(rset.getString("user_id"));
				member.setUserName(rset.getString("user_name"));
				member.setNickName(rset.getString("nickname"));
				member.setEmail(rset.getString("email"));		  
				member.setEnrollDate(rset.getDate("enroll_date"));
						  		
			  }
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
	         close(pstmt);
	         close(rset);
	      }
		return member;
	}


	public int kickOutMember(Connection conn, int userNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = managerQuery.getProperty("kickOutMember");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		
		return result;
	}


}
