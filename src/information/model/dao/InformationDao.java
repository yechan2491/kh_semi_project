package information.model.dao;

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


import information.model.vo.Board;
import information.model.vo.Image;
import information.model.vo.PageInfo;
import information.model.vo.Reply;
import information.model.vo.Search;



public class InformationDao {
   
   private Properties boardQuery = new Properties(); // map 타입 일종으로 키와 value가 하나의 엔트리이고 둘다 스트링 타입이다.

   // 파일 읽기
   public InformationDao() {
      String fileName = InformationDao.class.getResource("/sql/information/information-query.xml").getPath();

      try {
         boardQuery.loadFromXML(new FileInputStream(fileName));
      } catch (IOException e) {
         e.printStackTrace();
      }
   }
//   public List<Board> selectMarketplaceList(Connection conn) {
//      PreparedStatement pstmt = null;
//      ResultSet rset = null;
//      List<Board> boardList = new ArrayList<>();
//      String sql = boardQuery.getProperty("selectMarketplaceList");
//
//      try {
//         pstmt = conn.prepareStatement(sql);
//         rset = pstmt.executeQuery();
//
//         while (rset.next()) {
//            Board board = new Board();
//            board.setBid(rset.getInt("bid"));
//            board.setBtitle(rset.getString("btitle"));
//            board.setPrice(rset.getInt("price"));
//            board.setCname(rset.getString("cname"));
//            
//            board.setCreateDate(rset.getDate("create_date"));
//
//            List<Image> imageList = new ArrayList<>();
//            Image image = new Image();
//            image.setFilePath(rset.getString("file_path"));
//            image.setChangeName(rset.getString("change_name"));
//            imageList.add(image);
//            board.setImageList(imageList);
//
//            boardList.add(board);
//         }
//      } catch (SQLException e) {
//         e.printStackTrace();
//      } finally {
//         close(rset);
//         close(pstmt);
//      }
//
//      return boardList;
//   }

   // 조회수 증가
   public int increaseCount(Connection conn, int bid) {
      PreparedStatement pstmt = null;
      int result = 0;
      String sql = boardQuery.getProperty("increaseCount");
      
      try {
         pstmt = conn.prepareStatement(sql);
         pstmt.setInt(1, bid);
         result = pstmt.executeUpdate();
         
      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         close(pstmt);
      }
      
      
      return result;
      

   }

   
   public int insertBoard(Connection conn, Board board) {
      PreparedStatement pstmt = null;
      int result = 0;  //DML이니까 INT 값
      String sql = boardQuery.getProperty("insertBoard");  // 알맞은 키 값 전달
      
      try {
         pstmt = conn.prepareStatement(sql);
         
         pstmt.setString(1, board.getBtitle());
         pstmt.setString(2, board.getBcontent());
         pstmt.setInt(3, board.getBtype()); 
         pstmt.setInt(4, board.getBwriter());
         pstmt.setInt(5, board.getCid());
         
           // Board테이블에 새로운 행을 삽입한다?
         
         result = pstmt.executeUpdate();
         
         
      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         close(pstmt);
      }
      
      return result;
   }

   
   public int insertAttachment(Connection conn, Image photo) {
      PreparedStatement pstmt = null;
      int result = 0;
      String sql = boardQuery.getProperty("insertAttachment");
      
      try {
         pstmt = conn.prepareStatement(sql);
         
         pstmt.setString(1, photo.getOriginName());
         pstmt.setString(2, photo.getFilePath());
         pstmt.setString(3, photo.getChangeName());
         pstmt.setInt(4, photo.getFileLevel());
         
         result = pstmt.executeUpdate();
         
      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         close(pstmt);
      }
         
      return result;
   }

   public Board selectBoard(Connection conn, int bid) {
      PreparedStatement pstmt = null;
      ResultSet rset = null;
      Board board = null;  // rset 아마도 보드 타입으로 정의 할 것
      String sql = boardQuery.getProperty("selectBoard");
      
      try {
         pstmt = conn.prepareStatement(sql);
         pstmt.setInt(1, bid);
         rset = pstmt.executeQuery();
         
         if(rset.next()) {  // 한 개 씩 넘어오는데.. id 한 개니까 그냥 if문으로 ..  상세페이지 때 필요한 애들 
            board = new Board();
            board.setBid(bid);
            board.setBtitle(rset.getString("btitle"));
            board.setBcontent(rset.getString("bcontent"));
            board.setCreateDate(rset.getTimestamp("create_date"));
            board.setModifyDate(rset.getTimestamp("modify_date")); 
            board.setBcount(rset.getInt("bcount"));
            board.setBwriter(rset.getInt("bwriter"));
            board.setCid(rset.getInt("cid"));            
            board.setNickname(rset.getString("nickname"));
            board.setCname(rset.getString("cname")); 
            board.setUserName(rset.getString("user_name"));
         }
         
      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         close(rset);
         close(pstmt);
      }
      
      return board;
   }

   public List<Image> selectPhotoList(Connection conn, int bid) {
      List<Image> photoList=new ArrayList<>();
      ResultSet rset =null;
      PreparedStatement pstmt = null;
      String sql = boardQuery.getProperty("selectPhotoList");
      
      try {
         pstmt = conn.prepareStatement(sql);
         pstmt.setInt(1, bid);
         
         rset=pstmt.executeQuery();
         
         while(rset.next()) {
            Image image = new Image();
            image.setIid(rset.getInt("iid"));
            image.setOriginName(rset.getString("origin_name"));
            image.setChangeName(rset.getString("change_name"));
            image.setFilePath(rset.getString("file_path"));
            image.setFileLevel(rset.getInt("file_level"));
            photoList.add(image);
            
         }
      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         close(pstmt);
         close(rset);
      }
      
      return photoList;
   }

   public int updateBoard(Connection conn, Board board) {
      PreparedStatement pstmt = null;
      int result = 0;  //DML이니까 INT 값
      String sql = boardQuery.getProperty("updateBoard");
      
      try {
         pstmt = conn.prepareStatement(sql);
         
         pstmt.setString(1, board.getBtitle());
         pstmt.setString(2, board.getBcontent());
         pstmt.setInt(3, board.getCid());
         pstmt.setInt(4, board.getBid());
         
         result = pstmt.executeUpdate();
         
      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         close(pstmt);
      }
      
      return result;
   }

   public int updatePhoto(Connection conn, Image photo) {
      PreparedStatement pstmt = null;
      int result = 0;
      String sql = boardQuery.getProperty("updatePhoto");
      
      try {
         pstmt = conn.prepareStatement(sql);
         
         pstmt.setString(1, photo.getOriginName());
         pstmt.setString(2, photo.getChangeName());
         pstmt.setString(3, photo.getDeletedName());    //응?
         
         result = pstmt.executeUpdate();         
      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         close(pstmt);
      }
      
      return result;
   }

   public int insertAddedPhoto(Connection conn, int bid, Image photo) {
      PreparedStatement pstmt = null;
      int result = 0;
      String sql = boardQuery.getProperty("insertAddedPhoto");
      
      try {
         pstmt = conn.prepareStatement(sql);
         
         pstmt.setInt(1, bid);
         pstmt.setString(2, photo.getOriginName());
         pstmt.setString(3, photo.getFilePath());
         pstmt.setString(4, photo.getChangeName());
         pstmt.setInt(5, photo.getFileLevel());
         
         
         result = pstmt.executeUpdate();
         
      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         close(pstmt);
      }
      
      return result;
   }
   

   public int deleteBoard(Connection conn, int bid) {
      PreparedStatement pstmt = null;
      int result = 0;
      String sql = boardQuery.getProperty("deleteBoard");

      try {
         pstmt = conn.prepareStatement(sql);
         pstmt.setInt(1, bid);
         
         result = pstmt.executeUpdate();
         
      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         close(pstmt);
      }
      
      return result;
   }
   
   

   public int deletePhoto(Connection conn, int bid) {
      PreparedStatement pstmt = null;
      int result = 0;
      String sql = boardQuery.getProperty("deletePhoto");
      
      try {
         pstmt = conn.prepareStatement(sql);
         pstmt.setInt(1, bid);
         
         result = pstmt.executeUpdate();
      } catch (SQLException e) {
         
         e.printStackTrace();
      } finally {
         close(pstmt);
      }
      
      
      return result;
   }
   
   

   public int getLikeCount(Connection conn, int bid) {
      int likeCount = 0;
      PreparedStatement pstmt = null;
      ResultSet rset = null;
      String sql = boardQuery.getProperty("getLikeCount");

      try {
         pstmt = conn.prepareStatement(sql);
         pstmt.setInt(1, bid);
         rset = pstmt.executeQuery();

         if (rset.next()) {
            likeCount = rset.getInt(1);
         }
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      } finally {
         close(pstmt);
         close(rset);
      }

      return likeCount;
   }
   
   
   

   public int getListCount(Connection conn, Search search) {
      PreparedStatement pstmt = null;
      ResultSet rset = null;
      int listCount = 0;
      String sql = boardQuery.getProperty("getListCount");

      String[] category = { "clean", "decorate", "reform", "lifeInformation" };
//      String[] sort = { "recent", "old", "priceAsc", "priceDesc" };
      int cidNum = 0;
      // 검색 된 목록을 조회해야 하는 경우 다른 SQL문 수행
      if (search.getSearchCategory() != null ) {
         for (int i = 0; i < category.length; i++) {
            if (search.getSearchCategory().equals(category[i])) {
               sql = boardQuery.getProperty("getCategoryListCount");
               //cidNum = i + 21;
               cidNum = i + 1;
               break;
            }
         }

      }

      try {
         pstmt = conn.prepareStatement(sql);
         //if (cidNum > 20) {
         if (cidNum > 0) {
            pstmt.setInt(1, cidNum);
         }
         rset = pstmt.executeQuery();

         if (rset.next()) {
            listCount = rset.getInt(1);
         }
      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         close(rset);
         close(pstmt);
      }

      return listCount;
   }

   public List<Board> selectInformationList(Connection conn, PageInfo pi, Search search) {
      PreparedStatement pstmt = null;
      ResultSet rset = null;
      List<Board> boardList = new ArrayList<>();
      String sql = boardQuery.getProperty("selectInformationAllList");

      String[] category = { "clean", "decorate", "reform", "lifeInformation" };
      //String[] sort = { "recent", "old", "priceAsc", "priceDesc" };

      int cidNum = 0;
      // 검색 된 목록을 조회해야 하는 경우 다른 SQL문 수행
      if (search.getSearchCategory() != null ) {
         if (search.getSearchCategory().equals("all")) {
                  //sql = boardQuery.getProperty("selectInformationrecentList");   
                  sql = boardQuery.getProperty("selectInformationAllList");
         } else {
            for (int i = 0; i < category.length; i++) {
               if (search.getSearchCategory().equals(category[i])) {
                  //cidNum = i + 21;
                  cidNum = i + 1;
                  break;
               }
               
            }
            for (int i = 0; i < category.length; i++) {
               if (search.getSearchCategory().equals(category[i])) {
                  sql = boardQuery.getProperty("selectInformationList");
                  break;
               }
            }
         }
      }
      int index = 1;
      try {
         pstmt = conn.prepareStatement(sql);
         int startRow = (pi.getPage() - 1) * pi.getBoardLimit() + 1;
         int endRow = startRow + pi.getBoardLimit() - 1;

         //if (cidNum > 20) {
         if (cidNum > 0) {
            pstmt.setInt(index++, cidNum);
         }

         pstmt.setInt(index++, startRow);
         pstmt.setInt(index, endRow);

         rset = pstmt.executeQuery();

         while (rset.next()) {
            Board board = new Board();
            board.setBid(rset.getInt("bid"));
            board.setBtitle(rset.getString("btitle"));
            board.setCname(rset.getString("cname"));
            board.setModifyDate(rset.getTimestamp("modify_date"));

            List<Image> photoList = new ArrayList<>();
            Image image = new Image();
            image.setFilePath(rset.getString("file_path"));
            image.setChangeName(rset.getString("change_name"));
            photoList.add(image);
            board.setPhotoList(photoList);

            boardList.add(board);
         }
      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         close(rset);
         close(pstmt);
      }

      return boardList;
   }

   // 조회순 베스트 4 
   /*
    * public List<Board> selectBestGalleryList(Connection conn) { PreparedStatement
    * pstmt = null; ResultSet rset = null; List<Board> bestBoardList = new
    * ArrayList<>(); String sql = boardQuery.getProperty("selectBestGalleryList");
    * 
    * try { pstmt = conn.prepareStatement(sql); // 검색 조건 없으니 따로 위치홀더 없음..? rset =
    * pstmt.executeQuery();
    * 
    * while(rset.next()) { Board board = new Board();
    * board.setBid(rset.getInt("bid")); board.setCname(rset.getString("cname"));
    * board.setBtitle(rset.getString("btitle"));
    * board.setUserName(rset.getString("user_name"));
    * board.setBcount(rset.getInt("bcount")); // 보드의 그냥 필드값
    * 
    * List<Image> photoList = new ArrayList<>(); Image photo = new Image();
    * photo.setFilePath(rset.getString("file_path"));
    * photo.setChangeName(rset.getString("change_name")); photoList.add(photo);
    * board.setPhotoList(photoList);
    * 
    * bestBoardList.add(board); // 이거 빼먹지 말자
    * 
    * }
    * 
    * } catch (SQLException e) { e.printStackTrace(); } finally { close(rset);
    * close(pstmt); }
    * 
    * return bestBoardList; }
    */
   
	// 조회순 베스트4
	public List<Board> selectBestGalleryList(Connection conn) {

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Board> bestBoardList = new ArrayList<>();

		String sql = boardQuery.getProperty("selectBestGalleryList");

		try {
			pstmt = conn.prepareStatement(sql); // 검색 조건 없으니 따로 위치홀더 없음..? rset =
			rset = pstmt.executeQuery();
			

			while (rset.next()) {

				Board board = new Board();
	            board.setBid(rset.getInt("bid"));
	            board.setBtitle(rset.getString("btitle"));
	            board.setCname(rset.getString("cname"));

	            List<Image> photoList = new ArrayList<>();
	            Image image = new Image();
	            image.setFilePath(rset.getString("file_path"));
	            image.setChangeName(rset.getString("change_name"));
	            photoList.add(image);
	            board.setPhotoList(photoList);

				bestBoardList.add(board); // 이거 빼먹지 말자
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return bestBoardList;
	}
   
   
   
   
   
   
   
   

   public int insertReply(Connection conn, Reply reply) {
      PreparedStatement pstmt = null;
      int result = 0;
      String sql = boardQuery.getProperty("insertReply");
      
      try {
         pstmt = conn.prepareStatement(sql);
         
         pstmt.setString(1, reply.getAcontent());
         pstmt.setInt(2, reply.getBid());
         pstmt.setInt(3, reply.getWriter());
         
         result = pstmt.executeUpdate();
         
      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         close(pstmt);
      }
      
      return result;
   
   }

   public List<Reply> selectReplyList(Connection conn, int bid) {
      PreparedStatement pstmt = null;
      ResultSet rset = null;
      List<Reply> replyList = new ArrayList<>();
      String sql = boardQuery.getProperty("selectReplyList");
      
      try {
         pstmt = conn.prepareStatement(sql);
         
         pstmt.setInt(1, bid);
         
         rset = pstmt.executeQuery();
         
         while(rset.next()) {
            replyList.add(new Reply(rset.getInt("aid")
                              , rset.getString("acontent")
                              , rset.getTimestamp("create_date")
                              , rset.getTimestamp("modify_date")
                              , rset.getInt("bid")
                              , rset.getString("user_name")
                              , rset.getString("nickname")
                              , rset.getInt("writer")));
                              //, rset.getString("status")));
         }
         
      } catch (SQLException e) {
         e.printStackTrace();
      }  finally {
         close(rset);
         close(pstmt);
      }   
      return replyList;
   }

   public int deleteReply(Connection conn, Reply reply) {
      PreparedStatement pstmt = null;
      int result = 0;
      String sql = boardQuery.getProperty("deleteReply");
      try {
         pstmt = conn.prepareStatement(sql);
         pstmt.setInt(1, reply.getBid());
         pstmt.setInt(2, reply.getAid());
         result = pstmt.executeUpdate();
      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         close(pstmt);
      }
      return result;
   }
   

   
   
   
   
}

