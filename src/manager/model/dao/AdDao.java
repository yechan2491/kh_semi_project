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
import manager.model.vo.PageInfo;


public class AdDao {

	private Properties adQuery = new Properties();
	
	public AdDao() {
	      String fileName = AdDao.class.getResource("/sql/ad/ad-query.xml").getPath();
	      
	      try {
	         adQuery.loadFromXML(new FileInputStream(fileName));
	      } catch (IOException e) {
	         e.printStackTrace();
	      }
	   }

	public int insertAd(Connection conn, Advertise ad) {
		  PreparedStatement pstmt = null;
	      int result = 0;
	      String sql = adQuery.getProperty("insertAd");
	      
	      try {
	         pstmt = conn.prepareStatement(sql);
	         
	         pstmt.setString(1, ad.getAdTitle());
	         pstmt.setString(2, ad.getAdWriter());
	         pstmt.setString(3, ad.getAdContent());
	         pstmt.setString(4, ad.getAdCompany());
	         pstmt.setString(5, ad.getAdPhone());
	         
	         result = pstmt.executeUpdate();
	         
	      } catch (SQLException e) {
	         e.printStackTrace();
	      } finally {
	         close(pstmt);
	      }
	      
	      return result;
	}

	public int getListCount(Connection conn) {
		 PreparedStatement pstmt = null;
	      ResultSet rset = null;      // select구문 이용해야 하기 때문에 꼭 만들어주기
	      int listCount = 0;
	      
	      String sql = adQuery.getProperty("getListCount");
	      
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

	public List<Advertise> selectList(Connection conn, PageInfo pi) {
		 PreparedStatement pstmt = null;
	      ResultSet rset = null;
	      String sql = adQuery.getProperty("selectList");
	      
	      List<Advertise> adList = new ArrayList<>();
	      
	      try {
	         pstmt = conn.prepareStatement(sql);
	         
	         // 시작 page
	         int startRow = (pi.getPage() - 1) * pi.getBoardLimit() + 1;
	         // 끝 page
	         int endRow = startRow + pi.getBoardLimit() - 1;
	         
	         
	         pstmt.setInt(1, startRow);
	         pstmt.setInt(2, endRow);
	         /**/
	         
//	         pstmt.setInt(1, startRow);
//	         pstmt.setInt(2, endRow);
	         
	         rset = pstmt.executeQuery();
	         
	         while(rset.next()) {
	        	 Advertise ad = new Advertise();
	        	 
	        	 ad.setAdNo(rset.getInt("ad_no"));
	        	 ad.setAdTitle(rset.getString("ad_title"));
	        	 ad.setAdWriter(rset.getString("ad_writer"));
	        	 ad.setRead(rset.getString("read"));
	        	 ad.setAdDate(rset.getDate("ad_date"));
	            
	            adList.add(ad);
	         }
	         
	      } catch (SQLException e) {
	         e.printStackTrace();
	      } finally {
	         close(pstmt);
	         close(rset);
	      }
	      
	      return adList;
	}

	public int increaseRead(Connection conn, int adNo) {
		  PreparedStatement pstmt = null;
	      int result = 0;
	      String sql = adQuery.getProperty("increaseRead");
	      
	      try {
	         pstmt = conn.prepareStatement(sql);
	         pstmt.setInt(1, adNo);
	         
	         result = pstmt.executeUpdate();
	      } catch (SQLException e) {
	         e.printStackTrace();
	      } finally {
	         close(pstmt);
	      }
	      
	      return result;
	}

	public Advertise selectAd(Connection conn, int adNo) {
		PreparedStatement pstmt = null;
	      ResultSet rset = null;
	      Advertise ad = null;
	      String sql = adQuery.getProperty("selectAd");
	      
	      try {
	         pstmt = conn.prepareStatement(sql);
	         pstmt.setInt(1, adNo);
	         
	         rset = pstmt.executeQuery();
	         
	         if(rset.next()) {
	        	 ad = new Advertise();
	        	 
	        	 ad.setAdNo(rset.getInt("ad_no"));
	        	 ad.setAdTitle(rset.getString("ad_title"));
	        	 ad.setAdWriter(rset.getString("ad_writer"));
	        	 ad.setAdDate(rset.getTimestamp("ad_date"));
	        	 ad.setAdContent(rset.getString("ad_content"));
	        	 ad.setAdCompany(rset.getString("ad_company"));
	        	 ad.setAdPhone(rset.getString("ad_phone"));
	        	 
	         }
	         
	      } catch (SQLException e) {
	         e.printStackTrace();
	      } finally {
	         close(pstmt);
	         close(rset);
	      }
	      return ad;
	}
}
