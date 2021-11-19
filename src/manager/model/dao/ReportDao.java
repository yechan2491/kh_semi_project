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

import manager.model.vo.PageInfo;
import manager.model.vo.Report;

public class ReportDao {
	
	private Properties reportQuery = new Properties();
	
	public ReportDao(){
		
	      String fileName = ManagerDao.class.getResource("/sql/report/report-query.xml").getPath();
	      
	      try {
	    	  reportQuery.loadFromXML(new FileInputStream(fileName));
	      } catch (IOException e) {
	         e.printStackTrace();
	      }
	   }

	public int boardReport(Connection conn, Report report) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = reportQuery.getProperty("boardReport");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, report.getrTitle());
			pstmt.setString(2, report.getrContent());
			pstmt.setInt(3, report.getWriter());
			pstmt.setInt(4, report.getRwriter());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	public int ridSortBid(Connection conn, int bid) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = reportQuery.getProperty("ridSortBid");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, bid);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	public int getListCount(Connection conn) {
		 PreparedStatement pstmt = null;
	      ResultSet rset = null;      // select구문 이용해야 하기 때문에 꼭 만들어주기
	      int listCount = 0;
	      
	      String sql = reportQuery.getProperty("getListCount");
	      try {
	         pstmt = conn.prepareStatement(sql);
	         
	         rset = pstmt.executeQuery();
	         
	         if(rset.next()) {
	            listCount = rset.getInt(1);
	         }
	      } catch (SQLException e) {
	         e.printStackTrace();
	      } finally {
	         close(pstmt);
	         close(rset);
	      }
	      
	      return listCount;
	}

	public List<Report> selectList(Connection conn, PageInfo pi) {
		 PreparedStatement pstmt = null;
	      ResultSet rset = null;
	      String sql = reportQuery.getProperty("selectList");
	      
	      List<Report> reportList = new ArrayList<>();
	      
	      try {
	         pstmt = conn.prepareStatement(sql);
	         
	         // 시작 page
	         int startRow = (pi.getPage() - 1) * pi.getBoardLimit() + 1;
	         // 끝 page
	         int endRow = startRow + pi.getBoardLimit() - 1;
	         
	         pstmt.setInt(1, startRow);
	         pstmt.setInt(2, endRow);
	         
	         rset = pstmt.executeQuery();
	         
	         while(rset.next()) {
	        	 Report report = new Report();
	        	 
	        	 report.setRid(rset.getInt("rid"));
	        	 report.setrTitle(rset.getString("rtitle"));
	        	 report.setReportDate(rset.getDate("report_date"));
	        	 report.setRnickName(rset.getString("nickname"));
	        	 report.setStatus(rset.getString("status"));
	            
	            reportList.add(report);
	         }
	         
	      } catch (SQLException e) {
	         e.printStackTrace();
	      } finally {
	         close(pstmt);
	         close(rset);
	      }
	      
	      return reportList;
	}

	public Report reportDetail(Connection conn, int rid) {
		  PreparedStatement pstmt = null;
	      ResultSet rset = null;
	      Report report = null;
	      String sql = reportQuery.getProperty("reportDetail");
	      
	      try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, rid);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				report = new Report();
	        	 
	        	 report.setRid(rset.getInt("rid"));
	        	 report.setrTitle(rset.getString("rtitle"));
	        	 report.setrContent(rset.getString("rcontent"));
	        	 report.setReportDate(rset.getTimestamp("report_date"));
	        	 report.setNickName(rset.getString("bwriter"));
	        	 report.setRnickName(rset.getString("reporter"));
	             report.setWriter(rset.getInt("writer"));
	             report.setRwriter(rset.getInt("rwriter")); 
	             if(rset.getInt("replybid")!= 0) {
	            	 report.setBid(rset.getInt("replybid"));
	             }else {
	            	 report.setBid(rset.getInt("boardbid"));
	             }
	             report.setAid(rset.getInt("aid"));
	             if(rset.getInt("replybtype")!= 0) {
	            	 report.setBtype(rset.getInt("replybtype"));
	             }else {
	            	 report.setBtype(rset.getInt("boardbtype"));
	             }
	             if(rset.getString("replyreason")!= null) {
	            	 report.setReason(rset.getString("replyreason"));
	             }else {
	            	 report.setReason(rset.getString("boardreason"));
	             }
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}

		return report;
	}

	public int increaseRead(Connection conn, int rid) {
		  PreparedStatement pstmt = null;
	      int result = 0;
	      String sql = reportQuery.getProperty("increaseRead");
	      
	      try {
	         pstmt = conn.prepareStatement(sql);
	         pstmt.setInt(1, rid);
	         
	         result = pstmt.executeUpdate();
	      } catch (SQLException e) {
	         e.printStackTrace();
	      } finally {
	         close(pstmt);
	      }
	      
	      return result;
	}

	public int replyReport(Connection conn, Report report) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = reportQuery.getProperty("replyReport");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, report.getrTitle());
			pstmt.setString(2, report.getrContent());
			pstmt.setInt(3, report.getWriter());
			pstmt.setInt(4, report.getRwriter());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	public int ridSortAid(Connection conn, int aid) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = reportQuery.getProperty("ridSortAid");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, aid);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	public int noteReport(Connection conn, Report report) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = reportQuery.getProperty("noteReport");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, report.getrTitle());
			pstmt.setString(2, report.getrContent());
			pstmt.setInt(3, report.getWriter());
			pstmt.setInt(4, report.getRwriter());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	public int ridSortNno(Connection conn, int nno) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = reportQuery.getProperty("ridSortNno");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, nno);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	public Report reportNoteDetail(Connection conn, int rid) {
		PreparedStatement pstmt = null;
	      ResultSet rset = null;
	      Report report = null;
	      String sql = reportQuery.getProperty("reportNoteDetail");
	      
	      try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, rid);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				report = new Report();
				report.setrTitle(rset.getString("rtitle"));
				report.setRnickName(rset.getString("rwriter_nick"));
				report.setReportDate(rset.getTimestamp("report_date"));
				report.setReason(rset.getString("ncontent"));
				report.setrContent(rset.getString("rcontent"));
				report.setNickName(rset.getString("writer_nick"));
				report.setWriter(rset.getInt("user_no"));
				report.setNno(rset.getInt("nno"));
				report.setRwriter(rset.getInt("rwriter"));
				report.setBtype(4);
				
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}

		return report;
	}

}
