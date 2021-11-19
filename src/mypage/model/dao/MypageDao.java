package mypage.model.dao;

import static common.JDBCTemplate.close;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import friend.model.vo.Board;
import friend.model.vo.Image;
import manager.model.vo.PageInfo;
import manager.model.vo.Report;
import member.model.dao.MemberDao;
import member.model.vo.Member;

public class MypageDao {
	
private Properties mypageQuery;
	
	public MypageDao() {
		mypageQuery = new Properties();
		String fileName = MemberDao.class.getResource("/sql/mypage/mypage-query.xml").getPath();
		try {
			mypageQuery.loadFromXML(new FileInputStream(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int modifyMember(Connection conn, Member member) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = mypageQuery.getProperty("modifyMember");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, member.getUserName());
			pstmt.setString(2, member.getNickName());
			pstmt.setString(3, member.getPhone());
			pstmt.setString(4, member.getEmail());
			pstmt.setString(5, member.getProfilePath());
			pstmt.setInt(6, member.getUserNo());
			
			result = pstmt.executeUpdate();	
		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
		
	}

	public int deleteAccount(Connection conn, int userNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = mypageQuery.getProperty("deleteMember");
		
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

	public int myScrapInsert(Connection conn, int bid, int userNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = mypageQuery.getProperty("myScrapInsert");
		
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, userNo);
				pstmt.setInt(2, bid);
				result = pstmt.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				close(pstmt);
			}
			
		return result;
	}

	public int myScrapDelete(Connection conn, int bid, int userNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = mypageQuery.getProperty("myScrapDelete");
		
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, userNo);
				pstmt.setInt(2, bid);
				result = pstmt.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				close(pstmt);
			}
			
		return result;
	}

	public int getListCount(Connection conn,int userNo) {
		 PreparedStatement pstmt = null;
	      ResultSet rset = null;      // select구문 이용해야 하기 때문에 꼭 만들어주기
	      int listCount = 0;
	      
	      String sql = mypageQuery.getProperty("getListCount");
	      try {
	         pstmt = conn.prepareStatement(sql);
	         pstmt.setInt(1, userNo);
	         
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

	public List<Board> selectLikeList(Connection conn, PageInfo pi, int userNo) {
		 PreparedStatement pstmt = null;
	      ResultSet rset = null;
	      String sql = mypageQuery.getProperty("selectLikeList");
	      
	      List<Board> likeList = new ArrayList<>();
	      
	      try {
	         pstmt = conn.prepareStatement(sql);
	         
	         // 시작 page
	         int startRow = (pi.getPage() - 1) * pi.getBoardLimit() + 1;
	         // 끝 page
	         int endRow = startRow + pi.getBoardLimit() - 1;
	         
	         pstmt.setInt(1, userNo);
	         pstmt.setInt(2, startRow);
	         pstmt.setInt(3, endRow);
	         
	         rset = pstmt.executeQuery();
	         
	         while(rset.next()) {
	        	 Board board = new Board();
					
					board.setBid(rset.getInt("bid"));
					board.setBtitle(rset.getString("btitle"));
					board.setBtype(rset.getInt("btype"));
					board.setWritetime(rset.getInt("writetime"));				  
					board.setWritemin(rset.getInt("writemin"));
					board.setWriteday(rset.getInt("writeday"));
					board.setProfilePath(rset.getString("profile_path"));
					board.setNickname(rset.getString("nickname"));
					
					List<Image> friendPhotoList = new ArrayList<>();
					Image photo = new Image();
					photo.setFilePath(rset.getString("file_path"));
					photo.setChangeName(rset.getString("change_name"));	
					
					friendPhotoList.add(photo);
					board.setFriendPhotoList(friendPhotoList);
					
					likeList.add(board);
	         }
	         
	      } catch (SQLException e) {
	         e.printStackTrace();
	      } finally {
	         close(pstmt);
	         close(rset);
	      }
	      
	      return likeList;
	}

	public int myProductInsert(Connection conn, int mid, int userNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = mypageQuery.getProperty("myProductInsert");
		
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, userNo);
				pstmt.setInt(2, mid);
				result = pstmt.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				close(pstmt);
			}
			
		return result;
	}

	public int myProductDelete(Connection conn, int mid, int userNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = mypageQuery.getProperty("myProductDelete");
		
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, userNo);
				pstmt.setInt(2, mid);
				result = pstmt.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				close(pstmt);
			}
			
		return result;
	}

	public int getProductListCount(Connection conn, int userNo) {
		PreparedStatement pstmt = null;
	      ResultSet rset = null;      // select구문 이용해야 하기 때문에 꼭 만들어주기
	      int listCount = 0;
	      
	      String sql = mypageQuery.getProperty("getProductListCount");
	      try {
	         pstmt = conn.prepareStatement(sql);
	         pstmt.setInt(1, userNo);
	         
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

	public List<Board> selectProductList(Connection conn, PageInfo pi, int userNo) {
		 PreparedStatement pstmt = null;
	      ResultSet rset = null;
	      String sql = mypageQuery.getProperty("selectProductList");
	      
	      List<Board> likeList = new ArrayList<>();
	      
	      try {
	         pstmt = conn.prepareStatement(sql);
	         
	         // 시작 page
	         int startRow = (pi.getPage() - 1) * pi.getBoardLimit() + 1;
	         // 끝 page
	         int endRow = startRow + pi.getBoardLimit() - 1;
	         
	         pstmt.setInt(1, userNo);
	         pstmt.setInt(2, startRow);
	         pstmt.setInt(3, endRow);
	         
	         rset = pstmt.executeQuery();
	         
	         while(rset.next()) {
	        	 Board board = new Board();
					
					board.setBid(rset.getInt("bid"));
					board.setBtitle(rset.getString("btitle"));
					board.setPrice(rset.getLong("price"));
					board.setWritetime(rset.getInt("writetime"));				  
					board.setWritemin(rset.getInt("writemin"));
					board.setWriteday(rset.getInt("writeday"));
					board.setProfilePath(rset.getString("profile_path"));
					board.setNickname(rset.getString("nickname"));
					
					List<Image> friendPhotoList = new ArrayList<>();
					Image photo = new Image();
					photo.setFilePath(rset.getString("file_path"));
					photo.setChangeName(rset.getString("change_name"));	
					
					friendPhotoList.add(photo);
					board.setFriendPhotoList(friendPhotoList);
					
					likeList.add(board);
	         }
	         
	      } catch (SQLException e) {
	         e.printStackTrace();
	      } finally {
	         close(pstmt);
	         close(rset);
	      }
	      
	      return likeList;
	}

	public int getlikeCount(Connection conn, int userNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int likeCount = 0;
		String sql = mypageQuery.getProperty("likeCount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userNo);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				likeCount = rset.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(rset);
		}
		  
		return likeCount;
	}

	public int getproductCount(Connection conn, int userNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int productCount = 0;
		String sql = mypageQuery.getProperty("productCount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userNo);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				productCount = rset.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(rset);
		}
		  
		return productCount;
	}


}
