package marketplace.model.dao;

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

import marketplace.model.dao.MarketplaceDao;
import marketplace.model.vo.Board;
import marketplace.model.vo.Image;
import marketplace.model.vo.Marketplace;
import marketplace.model.vo.PageInfo;
import marketplace.model.vo.Reply;
import marketplace.model.vo.Search;
import marketplace.model.vo.UpdateImage;
import note.model.vo.Note;

public class MarketplaceDao {
	private Properties boardQuery = new Properties(); // map 타입 일종으로 키와 value가 하나의 엔트리이고 둘다 스트링 타입이다.

	public MarketplaceDao() {
		String fileName = MarketplaceDao.class.getResource("/sql/marketplace/marketplace-query.xml").getPath();

		try {
			boardQuery.loadFromXML(new FileInputStream(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<Board> selectMarketplaceList(Connection conn, PageInfo pi, Search search) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Board> boardList = new ArrayList<>();
		String sql = boardQuery.getProperty("selectMarketplaceList");

		String[] category = { "clothes", "shoes", "bag", "beauty", "kitchen", "digital", "vehicle", "etc" };
		String[] sort = { "recent", "old", "priceAsc", "priceDesc" };

		int cidNum = 0;
		// 검색 된 목록을 조회해야 하는 경우 다른 SQL문 수행
		if (search.getSearchCategory() != null && search.getSearchSort() != null) {
			if (search.getSearchCategory().equals("all")) {
				for (int i = 0; i < sort.length; i++) {
					if (search.getSearchSort().equals(sort[i])) {
						sql = boardQuery.getProperty("selectMarketplace" + sort[i] + "AllList");
						break;
					}
				}
			} else {
				for (int i = 0; i < category.length; i++) {
					if (search.getSearchCategory().equals(category[i])) {
						cidNum = i + 21;
						break;
					}
				}

				for (int i = 0; i < sort.length; i++) {
					if (search.getSearchSort().equals(sort[i])) {
						sql = boardQuery.getProperty("selectMarketplace" + sort[i] + "List");
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

			if (cidNum > 20) {
				pstmt.setInt(index++, cidNum);
			}

			pstmt.setInt(index++, startRow);
			pstmt.setInt(index, endRow);

			rset = pstmt.executeQuery();

			while (rset.next()) {
				Board board = new Board();
				board.setBid(rset.getInt("bid"));
				board.setBtitle(rset.getString("btitle"));
				board.setPrice(rset.getLong("price"));
				board.setCname(rset.getString("cname"));
				board.setModifyDate(rset.getTimestamp("modify_date"));
				board.setProfilePath(rset.getString("profile_path"));
				board.setNickname(rset.getString("nickname"));

				List<Image> imageList = new ArrayList<>();
				Image image = new Image();
				image.setFilePath(rset.getString("file_path"));
				image.setChangeName(rset.getString("change_name"));
				imageList.add(image);
				board.setImageList(imageList);

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

	public int getListCount(Connection conn, Search search) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int listCount = 0;
		String sql = boardQuery.getProperty("getListCount");

		String[] category = { "clothes", "shoes", "bag", "beauty", "kitchen", "digital", "vehicle", "etc" };
//		String[] sort = { "recent", "old", "priceAsc", "priceDesc" };
		int cidNum = 0;
		// 검색 된 목록을 조회해야 하는 경우 다른 SQL문 수행
		if (search.getSearchCategory() != null && search.getSearchSort() != null) {
			for (int i = 0; i < category.length; i++) {
				if (search.getSearchCategory().equals(category[i])) {
					sql = boardQuery.getProperty("getCategoryListCount");
					cidNum = i + 21;
					break;
				}
			}

		}

		try {
			pstmt = conn.prepareStatement(sql);
			if (cidNum > 20) {
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

	public int insertBoard(Connection conn, Board board) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = boardQuery.getProperty("insertBoard");

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, board.getBtitle());
			pstmt.setString(2, board.getBcontent());
			pstmt.setInt(3, board.getBtype());
			pstmt.setInt(4, board.getBwriter());
			pstmt.setInt(5, board.getCid());

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}

	public int insertImage(Connection conn, Image photo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = boardQuery.getProperty("insertImage");

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

	public int insertMarketplace(Connection conn, Board board) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = boardQuery.getProperty("insertMarketplace");

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, board.getPrice());
			pstmt.setString(2, board.getCondition());
			pstmt.setString(3, board.getLocation());

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int increaseCount(Connection conn, int bid) {
		int result = 0;
		PreparedStatement pstmt = null;
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

	public Board selectMarketplace(Connection conn, int bid) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = boardQuery.getProperty("selectMarketplace");
		Board board = null;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bid);

			rset = pstmt.executeQuery();

			if (rset.next()) {
				board = new Board();
				board.setBid(bid);
				board.setCid(rset.getInt("cid"));
				board.setMid(rset.getInt("mid"));
				board.setPrice(rset.getLong("price"));
				board.setBcount(rset.getInt("bcount"));
				board.setCname(rset.getString("cname"));
				board.setModifyDate(rset.getTimestamp("modify_date")); // 물론 Date 타입도 util.Date 타입 이어야 한다.
				board.setUserName(rset.getString("user_name"));
				board.setCreateDate(rset.getTimestamp("create_date")); // getDate로 가져오면 00시 00분 00초가 되기 때문에 Timestamp로
				board.setCondition(rset.getString("condition"));
				board.setLocation(rset.getString("location"));
				board.setNickname(rset.getString("nickname"));
				board.setBtitle(rset.getString("btitle"));
				board.setBcontent(rset.getString("bcontent"));
				board.setBwriter(rset.getInt("bwriter"));
				board.setProfilePath(rset.getString("profile_path"));

				// 가져와야함
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}
		return board;
	}

	public List<Image> selectImageList(Connection conn, int bid) {
		List<Image> photoList = new ArrayList<>();
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		String sql = boardQuery.getProperty("selectPhotoList");

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bid);

			rset = pstmt.executeQuery();

			while (rset.next()) {
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

	public int getLikeCount(Connection conn, int mid) {
		int likeCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = boardQuery.getProperty("getLikeCount");

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mid);
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

	public int deleteBoard(Connection conn, int bid) {
		String sql = boardQuery.getProperty("deleteBoard");
		PreparedStatement pstmt = null;
		int result = 0;

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

//	public int deleteMarketplace(Connection conn, int bid) {
//		String sql = boardQuery.getProperty("deleteMarketplace");
//		PreparedStatement pstmt = null;
//		int result = 0;
//		
//		try {
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setInt(1, bid);
//			result = pstmt.executeUpdate();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			close(pstmt);
//		}
//		
//		return result;
//	}

	public int deleteImage(Connection conn, int bid) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = boardQuery.getProperty("deleteImage");

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bid);
			result = pstmt.executeUpdate(); // 처리된 행 개수 반환
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int updateMarketplace(Connection conn, Board board) {
		String sql = boardQuery.getProperty("updateMarketplace");
		PreparedStatement pstmt = null;
		int result = 0;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, board.getPrice());
			pstmt.setString(2, board.getCondition());
			pstmt.setString(3, board.getLocation());
			pstmt.setInt(4, board.getBid());

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}

	public int updateBoard(Connection conn, Board board) {
		String sql = boardQuery.getProperty("updateBoard");
		PreparedStatement pstmt = null;
		int result = 0;

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
			pstmt.setString(3, photo.getDeletedName());

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

	public int updateImage(Connection conn, int bid, String oldImageName) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = boardQuery.getProperty("updateImage");

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, oldImageName);
			pstmt.setInt(2, bid);
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
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

			while (rset.next()) {
				Reply reply = new Reply();
				reply.setAid(rset.getInt("aid"));
				reply.setAcontent(rset.getString("acontent"));
				reply.setBid(rset.getInt("bid"));
				reply.setWriter(rset.getInt("writer"));
				reply.setUserName(rset.getString("user_name"));
				reply.setNickName(rset.getString("nickname"));
				reply.setCreateDate(rset.getTimestamp("create_date"));
				reply.setModifyDate(rset.getTimestamp("modify_date"));
				reply.setProfilePath(rset.getString("profile_path"));

				String end_ymd = reply.getModifyDate() + "";
				
				String yyyy = end_ymd.substring(0, 4);
				int y = Integer.parseInt(yyyy);
				String MM = end_ymd.substring(5, 7);
				int M = Integer.parseInt(MM);
				String dd = end_ymd.substring(8, 10);
				int d = Integer.parseInt(dd);
				String tt = end_ymd.substring(11, 13);
				int t = Integer.parseInt(tt);
				String mm = end_ymd.substring(14, 16);
				int m = Integer.parseInt(mm);
				String ss = end_ymd.substring(17, 19);
				int s = Integer.parseInt(ss);
//				System.out.println(calculateTime(new Date(y-1900, M - 1, d, t, m, s)));

				reply.setTimeMessage(calculateTime(new Date(y-1900, M - 1, d, t, m, s)));
				replyList.add(reply);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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

	public String calculateTime(Date date) {
		final int SEC = 60;
		final int MIN = 60;
		final int HOUR = 24;
		final int DAY = 30;
		final int MONTH = 12;
		long curTime = System.currentTimeMillis();
		long regTime = date.getTime();
//		System.out.println(regTime);
		long diffTime = (curTime - regTime) / 1000;

		String msg = null;

		if (diffTime < SEC) {
			// sec
			msg = "방금전";
		} else if ((diffTime /= SEC) < MIN) {
			// min
//			System.out.println(diffTime);

			msg = diffTime + "분전";
		} else if ((diffTime /= MIN) < HOUR) {
			// hour
			msg = (diffTime) + "시간전";
		} else if ((diffTime /= HOUR) < DAY) {
			// day
			msg = (diffTime) + "일전";
		} else if ((diffTime /= DAY) < MONTH) {
			// day
			msg = (diffTime) + "달전";
		} else {
			msg = (diffTime) + "년전";
		}

		return msg;
	}

	public List<Board> selectRelationList(Connection conn, int bid, int cid) {
		PreparedStatement pstmt = null;
		ResultSet rset=null;
		List<Board> relationList = new ArrayList<>();
		String sql=boardQuery.getProperty("selectRelationList");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, cid);
			pstmt.setInt(2, bid);
			
			rset=pstmt.executeQuery();
			
			while(rset.next()) {
				Board board = new Board();
				board.setBid(rset.getInt("bid"));
				board.setBtitle(rset.getString("btitle"));
				board.setCid(rset.getInt("cid"));
				board.setCname(rset.getString("cname"));
				board.setChangeName(rset.getString("change_name"));
				board.setFilePath(rset.getString("file_path"));
				relationList.add(board);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}
		
		return relationList;
	}

	public List<Board> timeprocessingBoardList(List<Board> boardList) {
		for(Board board : boardList) {
			 
			String result=formatTimeString(board.getModifyDate());
			board.setTimeView(result);
//			System.out.println(result);
		}
		
		return boardList;
	}

	public Board timeprocessingBoard(Board board) {
		String result=formatTimeString(board.getModifyDate());
		board.setTimeView(result);
		return board;
	}
	
	
	private static class TIME_MAXIMUM {
		public static final int SEC = 60;
		public static final int MIN = 60;
		public static final int HOUR = 24;
		public static final int DAY = 30;
		public static final int MONTH = 12;
	}
	public static String formatTimeString(Date tempDate) {
		long curTime = System.currentTimeMillis();
		long regTime = tempDate.getTime();
		long diffTime = (curTime - regTime) / 1000;
		String msg = null;
		if (diffTime < TIME_MAXIMUM.SEC) {
			// sec
			msg = "방금전";
		} else if ((diffTime /= TIME_MAXIMUM.SEC) < TIME_MAXIMUM.MIN) {
			// min
			msg = diffTime + "분전";
		} else if ((diffTime /= TIME_MAXIMUM.MIN) < TIME_MAXIMUM.HOUR) {
			// hour
			msg = (diffTime) + "시간전";
		} else if ((diffTime /= TIME_MAXIMUM.HOUR) < TIME_MAXIMUM.DAY) {
			// day
			msg = (diffTime) + "일전";
		} else if ((diffTime /= TIME_MAXIMUM.DAY) < TIME_MAXIMUM.MONTH) {
			// day
			msg = (diffTime) + "달전";
		} else {
			msg = (diffTime) + "년전";
		}
		return msg;

	}

	public List<Reply> timeprocessingReplyList(List<Reply> replyList) {
		for(Reply reply : replyList) {
			 
			String result=formatTimeString(reply.getModifyDate());
			reply.setTimeView(result);
//			System.out.println(result);
		}
		
		return replyList;
	}

	public List<Reply> selectReplyListReduce(Connection conn, int bid) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Reply> replyList = new ArrayList<>();
		String sql = boardQuery.getProperty("selectReplyListReduce");

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bid);

			rset = pstmt.executeQuery();

			while (rset.next()) {
				Reply reply = new Reply();
				reply.setAid(rset.getInt("aid"));
				reply.setAcontent(rset.getString("acontent"));
				reply.setBid(rset.getInt("bid"));
				reply.setWriter(rset.getInt("writer"));
				reply.setUserName(rset.getString("user_name"));
				reply.setNickName(rset.getString("nickname"));
				reply.setCreateDate(rset.getTimestamp("create_date"));
				reply.setModifyDate(rset.getTimestamp("modify_date"));
				reply.setProfilePath(rset.getString("profile_path"));

				String end_ymd = reply.getModifyDate() + "";
				
				String yyyy = end_ymd.substring(0, 4);
				int y = Integer.parseInt(yyyy);
				String MM = end_ymd.substring(5, 7);
				int M = Integer.parseInt(MM);
				String dd = end_ymd.substring(8, 10);
				int d = Integer.parseInt(dd);
				String tt = end_ymd.substring(11, 13);
				int t = Integer.parseInt(tt);
				String mm = end_ymd.substring(14, 16);
				int m = Integer.parseInt(mm);
				String ss = end_ymd.substring(17, 19);
				int s = Integer.parseInt(ss);
//				System.out.println(calculateTime(new Date(y-1900, M - 1, d, t, m, s)));

				reply.setTimeMessage(calculateTime(new Date(y-1900, M - 1, d, t, m, s)));
				replyList.add(reply);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return replyList;
	}

	public int selectLikeUser(Connection conn, int userNo, int bid) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int likeBid = 0;
		String sql = boardQuery.getProperty("selectLikeBid");

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, userNo);	
			pstmt.setInt(2, bid);

			rset = pstmt.executeQuery();

			if (rset.next()) {
				
				likeBid = rset.getInt("bid");
			
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return likeBid;
	}

	
}





