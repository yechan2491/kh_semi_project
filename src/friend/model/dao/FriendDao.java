package friend.model.dao;

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


import friend.model.vo.Answer;
import friend.model.vo.Board;
import friend.model.vo.Image;
import friend.model.vo.PageInfo;

public class FriendDao {
	
	private Properties friendQuery = new Properties();
	
	public FriendDao() {
		String fileName = FriendDao.class.getResource("/sql/friend/friend-query.xml").getPath();

		try {
			friendQuery.loadFromXML(new FileInputStream(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 게시글 갯수를 조회, 셀렉포함
	public int getListCount(Connection conn, String searchCondition) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int listCount = 0;
		String sql = "";
		// System.out.println(searchCondition);
		// String sql = friendQuery.getProperty("getListCount");
		String cidNum2 = searchCondition;
		// 셀렉객체가 들어왔을 때 sql문 수행
		int cidNum = 0;
		if(searchCondition != null && !searchCondition.equals("all")) {	// 셀렉 되었을 떄
			sql = friendQuery.getProperty("getSearchCount");
			cidNum = Integer.parseInt(cidNum2);
			// System.out.println("null all");
		} else if (searchCondition != null && searchCondition.equals("all")){	// 전체보기 입력 받았을때
			sql = friendQuery.getProperty("getListCount");
			// System.out.println("all들어옴");
		}  else { // 안들어 오면 전체 게시글 조회
			sql = friendQuery.getProperty("getListCount");
			// System.out.println("안들어옴");
		}
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			if(searchCondition != null && !searchCondition.equals("all")) {
				pstmt.setInt(1, cidNum);
			}
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
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
	


	
	// 메인 리스트 보이기, 셀렉 된 게시물
	public List<Board> selectGalleryList(Connection conn, PageInfo pi, String searchCondition) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Board> boardList = new ArrayList<>();
		String sql = "";
		//String sql = friendQuery.getProperty("selectList");
		String cidNum2 = searchCondition;
		// 셀렉객체가 들어왔을 때 sql문 수행
		int cidNum = 0;
		
		if(searchCondition != null && !searchCondition.equals("all")) {	// 셀렉 되었을 떄
			sql = friendQuery.getProperty("selectSearchList");
			cidNum = Integer.parseInt(cidNum2);
		}  else if (searchCondition != null && searchCondition.equals("all")){	// 전체보기 입력 받았을때
			sql = friendQuery.getProperty("selectList");
		}else { // 안들어 오면 전체 게시글 조회
			sql = friendQuery.getProperty("selectList");
		}
		
		
		/*
		if(searchCondition != null) {	// 셀렉 되었을 떄
			sql = friendQuery.getProperty("selectSearchList");
			cidNum = Integer.parseInt(cidNum2);
		} else { // 안들어 오면 전체 게시글 조회
			sql = friendQuery.getProperty("selectList");
		}
		*/
		
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			int startRow = (pi.getPage() - 1) * pi.getBoardLimit() + 1;
			int endRow = startRow + pi.getBoardLimit() - 1;
			
			if(searchCondition != null && !searchCondition.equals("all")) {
				pstmt.setInt(1, cidNum);
				pstmt.setInt(2, startRow);
				pstmt.setInt(3, endRow);
			} else if (searchCondition != null && searchCondition.equals("all")){	// 전체보기 입력 받았을때
				pstmt.setInt(1, startRow);
				pstmt.setInt(2, endRow);
			} else {
				pstmt.setInt(1, startRow);
				pstmt.setInt(2, endRow);
			}

			/*
			if(searchCondition != null) {
				pstmt.setInt(1, cidNum);
				pstmt.setInt(2, startRow);
				pstmt.setInt(3, endRow);
			} else {
				pstmt.setInt(1, startRow);
				pstmt.setInt(2, endRow);
			}
			*/

			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Board board = new Board();
				
				board.setBid(rset.getInt("bid"));
				board.setCname(rset.getString("cname"));
				board.setBtitle(rset.getString("btitle"));
				board.setUserName(rset.getString("user_name"));
				board.setBcount(rset.getInt("bcount"));
				board.setGender(rset.getString("gender"));
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
	


	// 게시물 작성
	public int insertBoard(Connection conn, Board board) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = friendQuery.getProperty("insertBoard");

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, board.getBtitle());
			pstmt.setString(2, board.getBcontent());
			pstmt.setInt(3, board.getBwriter());
			pstmt.setInt(4, board.getBtype());
			pstmt.setInt(5, board.getCid());
			pstmt.setString(6, board.getGender());
			
			

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
		String sql = friendQuery.getProperty("insertImage");

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

	public int increaseCount(Connection conn, int bid) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = friendQuery.getProperty("increaseCount");

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

	// 상세페이지에서 사용
	public Board selectBoard(Connection conn, int bid) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Board board = null;
		String sql = friendQuery.getProperty("selectBoard");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bid);
			rset = pstmt.executeQuery();
		
			if(rset.next()) {
				board = new Board();
				board.setBid(rset.getInt("bid"));
				board.setCid(rset.getInt("cid"));
				board.setCname(rset.getString("cname"));
				board.setBtitle(rset.getString("btitle"));
				board.setBcontent(rset.getString("bcontent"));
				board.setBwriter(rset.getInt("bwriter"));
				board.setUserName(rset.getString("user_name"));
				board.setBcount(rset.getInt("bcount"));
				board.setBrith(rset.getString("birth"));
				board.setAge(rset.getString("age"));
				board.setGender(rset.getString("gender"));
				board.setPhone(rset.getString("phone"));
				board.setProfilePath(rset.getString("profile_path"));
				board.setWritetime(rset.getInt("writetime"));
				board.setWritemin(rset.getInt("writemin"));
				board.setCreateDate(rset.getTimestamp("create_date"));
				board.setModifyDate(rset.getTimestamp("modify_date"));
				board.setWriteday(rset.getInt("writeday"));
				board.setNickname(rset.getString("nickname"));
				}
		
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(pstmt);
				close(rset);
			}
		
			return board;
			}

	public List<Image> selectPhotoList(Connection conn, int bid) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Image> friendphotoList = new ArrayList<>();
		String sql = friendQuery.getProperty("selectPhotoList");

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
				friendphotoList.add(image);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return friendphotoList;
	}

	public int deleteBoard(Connection conn, int bid) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = friendQuery.getProperty("deleteBoard");

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
		String sql = friendQuery.getProperty("deletePhoto");

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

	public int updateBoard(Connection conn, Board board) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = friendQuery.getProperty("updateBoard");

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, board.getCid());
			pstmt.setString(2, board.getBtitle());
			pstmt.setString(3, board.getBcontent());
			pstmt.setString(4, board.getGender());
			pstmt.setInt(5, board.getBid());
			

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}

	public int updatePhoto(Connection conn, Image image) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = friendQuery.getProperty("updatePhoto");

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, image.getOriginName());
			pstmt.setString(2, image.getChangeName());
			pstmt.setString(3, image.getDeletedName());

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}

	public int insertAddedPhoto(Connection conn, int bid, Image image) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = friendQuery.getProperty("insertAddedPhoto");

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, bid);
			pstmt.setString(2, image.getOriginName());
			pstmt.setString(3, image.getChangeName());
			pstmt.setString(4, image.getFilePath());
			pstmt.setInt(5, image.getFileLevel());

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}

	public int insertReply(Connection conn, Answer answer) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = friendQuery.getProperty("insertAnswer");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, answer.getAcontent());
			pstmt.setInt(2, answer.getBid());
			pstmt.setInt(3, answer.getWriter());

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;

	}

	// 댓글 조회
	public List<Answer> selectReplyList(Connection conn, int bid) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Answer> friendAnswerList = new ArrayList<>();
		String sql = friendQuery.getProperty("selectReplyList");

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bid);

			rset = pstmt.executeQuery();

			while (rset.next()) {
				Answer answer = new Answer();
				answer.setAid(rset.getInt("aid"));
				answer.setAcontent(rset.getString("acontent"));
				answer.setBid(rset.getInt("bid"));
				answer.setWriter(rset.getInt("writer"));
				answer.setUserName(rset.getString("user_name"));
				answer.setCreateDate(rset.getTimestamp("create_date"));
				answer.setModifyDate(rset.getTimestamp("modify_date"));
				answer.setProfilePath(rset.getString("profile_path"));
				answer.setNickName(rset.getString("nickname"));
				
				friendAnswerList.add(answer);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return friendAnswerList;
	}

	/* 댓글 삭제 */
	public int deleteReply(Connection conn, Answer answer) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = friendQuery.getProperty("deleteReply");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, answer.getBid());
			pstmt.setInt(2, answer.getAid());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int selectLikeUser(Connection conn, int userNo, int bid) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int likeBid = 0;
		String sql = friendQuery.getProperty("selectLikeBid");

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




















