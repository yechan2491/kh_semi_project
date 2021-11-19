package friend.model.service;

import static common.JDBCTemplate.*;


import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import friend.model.dao.FriendDao;
import friend.model.vo.Answer;
import friend.model.vo.Board;
import friend.model.vo.Image;
import friend.model.vo.PageInfo;
import marketplace.model.vo.Reply;


public class FriendService {
	
	private FriendDao friendDao = new FriendDao();
	
	// 페이징
	public Map<String, Object> selectList(int page, String searchCondition) {
		Connection conn = getConnection();
		// int listCount = 0;
		
		
		// 1. 게시글 개수 구하기
		int listCount = friendDao.getListCount(conn, searchCondition);
		//System.out.println(listCount);
		// 2. 인포 객체만들기
		PageInfo pi = new PageInfo(page, listCount, 5, 12);
		
		// 3. 페이징 처리 된 게시글 목록 조회
		List<Board> boardList = friendDao.selectGalleryList(conn, pi, searchCondition);
		// System.out.println("service : "+boardList);
		
	
		Map<String, Object> returnMap = new HashMap<>();
		
		returnMap.put("pi", pi);
		returnMap.put("boardList", boardList);
				
		return returnMap;
	}

	// 이미지 넣기
	public int insertGallery(Board board) {
		Connection conn = getConnection();
		
		int boardResult = friendDao.insertBoard(conn, board);
		
		int iamgeResult = 0;
		for(Image photo : board.getFriendPhotoList()) {			// 사진이 여러개
			iamgeResult += friendDao.insertAttachment(conn, photo);
		}
		
		int result = 0;  // 2가지 로직이 모두 잘 수행 되었음을 나타내는 변수 (게시글, 사진 insert)
		if(boardResult > 0 && iamgeResult == board.getFriendPhotoList().size()) {
			commit(conn);							// 사진을 몇개 가져오냐에 따라 위에 반복문이 실행되니까 그 두개의 길이가 같니?를 비교
			result = 1;
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}

	// 조회수 증가
	public int increaseCount(int bid) {
		Connection conn = getConnection();
		
		int result = friendDao.increaseCount(conn, bid);
		
		if(result > 0 ) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);

		return result;
	}

	// 메인 화면 리스트 조회
	public Board selectGalleryList(int bid) {
		Connection conn = getConnection();
		
		/* Board 테이블 정보 조회 */
		Board board = friendDao.selectBoard(conn, bid);
		
		/* Attachment 테이블 정보 조회 */
		List<Image> photoList = friendDao.selectPhotoList(conn, bid);
		board.setFriendPhotoList(photoList);
		
		/* 댓글 조회 */
		board.setFriendAnswerList(friendDao.selectReplyList(conn,bid));
		
		close(conn);
		
		return board;
	}

	// 삭제
	public List<Image> deleteGallery(int bid) {
		Connection conn = getConnection();
		
		List<Image> deletedPhotoList = friendDao.selectPhotoList(conn, bid);
		
		int boardResult = friendDao.deleteBoard(conn, bid);
		
		int photoResult = friendDao.deletePhoto(conn, bid);
		
		if(boardResult > 0 && photoResult == deletedPhotoList.size()) {
			commit(conn);
		} else {
			rollback(conn);
			deletedPhotoList = null;
		}
		close(conn);
		
		return deletedPhotoList;
		
	}

	// 게시판 수정
	public Board selectGallery(int bid) {
		Connection conn = getConnection();
		
		// board 테이블 정보 조회
		Board board = friendDao.selectBoard(conn, bid);
		
		// 댓글 조회
		board.setFriendAnswerList(friendDao.selectReplyList(conn,bid));
		
		/* Image 테이블 정보 조회 */
		List<Image> photoList = friendDao.selectPhotoList(conn, bid);
		board.setFriendPhotoList(photoList);
		
		close(conn);
		
		return board;
	}

	// 게시글 업데이트 
	public int updateGallery(Board board) {
		Connection conn = getConnection();
		
		int boardResult = friendDao.updateBoard(conn, board);
		
		int updatePhotoResult = 0;	
		int insertPhotoResult = 0;
		int updateListCount = 0;
		int insertListCount = 0;
		
		for(Image image : board.getFriendPhotoList()) {
			if(image.getDeletedName() != null) {
				updatePhotoResult += friendDao.updatePhoto(conn, image); 
				updateListCount++;
			} else {
				insertPhotoResult += friendDao.insertAddedPhoto(conn, board.getBid(), image);
				insertListCount++;
			}
		}

		int result = 0;
		if(boardResult > 0 
				&& updatePhotoResult == updateListCount 
				&& insertPhotoResult == insertListCount) {
			commit(conn);
			result = 1;		
		} else {
			rollback(conn);
		}
		
		return result;
	}

	public List<Answer> insertReply(Answer answer) {
		Connection conn = getConnection();
		List<Answer> friendAnswerList = null;
		
		/* 댓글 입력 */
		int result = friendDao.insertReply(conn, answer);
		
		if(result > 0) {
			commit(conn);
			/* 갱신 된 댓글 목록 조회 */
			friendAnswerList = friendDao.selectReplyList(conn, answer.getBid());
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return friendAnswerList;
	}

	public List<Answer> deleteReply(Answer answer) {
		Connection conn = getConnection();
		List<Answer> friendAnswerList = null;
		
		/* 댓글 삭제 */
		int result= friendDao.deleteReply(conn, answer);
		
		
		if (result > 0) {
			commit(conn);
			/* 갱신 된 댓글 목록 조회 */
			friendAnswerList = friendDao.selectReplyList(conn, answer.getBid());
		} else {
			rollback(conn);
		}
		
		
		close(conn);
				
		return friendAnswerList;
	}

	// 좋아요여부
	public int selectLikeUser(int userNo, int bid) {
		Connection conn = getConnection();
		
		int likeUser = friendDao.selectLikeUser(conn,userNo,bid);
		
		close(conn);
		return likeUser;
	}





	

}
