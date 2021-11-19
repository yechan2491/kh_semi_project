package information.model.service;


import static common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import information.model.dao.InformationDao;
import information.model.vo.Board;
import information.model.vo.Image;
import information.model.vo.PageInfo;
import information.model.vo.Reply;
import information.model.vo.Search;





public class InformationService {
	
	private InformationDao boardDao = new InformationDao();
	
//	public List<Board> selectMarketplaceList() {
//		Connection conn = getConnection();
//		List<Board> boardList=boardDao.informationList(conn);
//		close(conn);
//		return boardList;
//	}
	
	
	public int increaseCount(int bid) {
		Connection conn = getConnection();
		
		int result = boardDao.increaseCount(conn, bid);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}

	public int insertGallery(Board board) {
			Connection conn = getConnection();  // DB와의 연결 위해
		
		/* Board 테이블에 삽입 - 기존 메소드 활용 (일반 게시판 때 만들어 놨던 것)*/
		int boardResult = boardDao.insertBoard(conn, board);      // 성공하면 if(boardResult > 0 142번줄 조건 성립  //SEQ_FID.NEXTVAL 생김?
		
		/* image 테이블에 삽입 */
		int attachmentResult = 0;
		for(Image photo : board.getPhotoList()) { //getPhotoList 몇개의 첨부파일이 필요한가에 따라서
			attachmentResult += boardDao.insertAttachment(conn, photo);  // insertAttachment 호출해서  attachmentResult쌓기
		} //
		
		System.out.println("지금 확인 : " + board.getPhotoList());
		
		int result = 0; // 2가지 로직이 모두 잘 수행 되었음을 나타내는 변수
		if(boardResult > 0 && attachmentResult == board.getPhotoList().size()) {    // 파일 몇 개 첨부 했는지? board.getPhotoList().size()
			commit(conn);
			result = 1;   // 성공하면 result 값 0 -> 1로 바꿈
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}

	public Board selectGallery(int bid) {
		Connection conn = getConnection();  // DB와의 연결 위해.
		
		/* Board 테이블 정보 조회  (기존 로직 활용)*/
		Board board = boardDao.selectBoard(conn, bid);
		
		/* 댓글 조회 추가 */
		board.setReplyList(boardDao.selectReplyList(conn,bid));
		
		/* 찜한 유저 수 조회 */
		int likeCount = boardDao.getLikeCount(conn,board.getBid());
		board.setLikeCount(likeCount);
		
		
		
		/* Attachment 테이블 정보 조회 */
		List<Image> photoList = boardDao.selectPhotoList(conn, bid);
		
		board.setPhotoList(photoList);
		
		close(conn);
		
		
		return board;
	}

	public int updateGallery(Board board) {
		Connection conn = getConnection();  // DB와의 연결 위해.
		
		/* Board 테이블 수정 */
		int boardResult = boardDao.updateBoard(conn, board);
		
		
		int updatePhotoResult = 0;  //수행 결과를 받은 변수
		int insertPhotoResult = 0;  //수행 결과를 받은 변수
		int updateListCount = 0;  // 수행 해야할 갯수 변수
		int insertListCount = 0;  // 수행 해야할 갯수 변수
	
		
		/* Attachment 테이블 수정 및 삽입 */
		for(Image photo : board.getPhotoList()) {  // board.getPhotoList() => List<Attachment> ?
			if(photo.getDeletedName() != null) {
				/* 기존에 있던 파일을 덮어쓰기 - update */
				updatePhotoResult += boardDao.updatePhoto(conn, photo);
				updateListCount++;
				 System.out.println("update : " + photo);
				 System.out.println("updatePhotoResult : " + updatePhotoResult);
			}else {
				/* 새로 첨부 된 파일을 추가하기 - insert */
				insertPhotoResult += boardDao.insertAddedPhoto(conn, board.getBid(), photo);  // 이미 부여된 bid => board.getBid()
				insertListCount++;
				 System.out.println("insert : " + photo);
				 System.out.println("insertPhotoResult : " + insertPhotoResult);
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
		
		close(conn);
		
		return result;
	}

	public List<Image> deleteGallery(int bid) {
		Connection conn = getConnection();  // DB와의 연결 위해.
		
		/* Attachment 테이블 정보 조회 */
		List<Image> deletedPhotoList = boardDao.selectPhotoList(conn, bid);
		
		int boardResult = boardDao.deleteBoard(conn, bid);
		
		int photoResult = boardDao.deletePhoto(conn, bid);
		
		if(boardResult > 0 && photoResult == deletedPhotoList.size()) {
			commit(conn);
		}else {
			rollback(conn);
			deletedPhotoList = null;
		}
		
		close(conn);
				
		return deletedPhotoList;
	}

	public Map<String, Object> selectInformationList(int page, Search search) {
		Connection conn = getConnection();

		// 1. 게시글 총 개수 구하기
		int listCount = boardDao.getListCount(conn, search);
		System.out.println("listCount = " + listCount);

		// 2. PageInfo 객체 만들기
		PageInfo pi = new PageInfo(page, listCount, 10, 8);//

		// 3. 페이징 처리 된 게시글 목록 조회
		List<Board> boardList = boardDao.selectInformationList(conn, pi, search);

				System.out.println("pi = " + pi);
				System.out.println("boardList = " + boardList);
		
		//선생님		
		List<Board> bestBoardList = boardDao.selectBestGalleryList(conn);		

		Map<String, Object> returnMap = new HashMap<>();

		returnMap.put("pi", pi);
		returnMap.put("boardList", boardList);
		
		System.out.println("bestBoardList = " + bestBoardList);
		//선생님	
		returnMap.put("bestBoardList", bestBoardList);
		
		
		close(conn);
		return returnMap;
	}

	
	
	/*
	 * public List<Board> selectBestGalleryList() { Connection conn =
	 * getConnection(); // DB와의 연결 위해
	 * 
	 * List<Board> bestBoardList = boardDao.selectBestGalleryList(conn);
	 * 
	 * close(conn);
	 * 
	 * return bestBoardList; }
	 */

	public List<Reply> insertReply(Reply reply) {
		Connection conn = getConnection();
		List<Reply> replyList = null;
		
		/* 댓글 입력 */
		int result = boardDao.insertReply(conn, reply);
		
		if(result > 0) {
			commit(conn);
			/* 갱신 된 댓글 목록 조회 */
			replyList = boardDao.selectReplyList(conn, reply.getBid());
		} else {
			rollback(conn);
		}
		close(conn);
		
		
		return replyList;   // 댓글 목록 리턴
	}

	public List<Reply> deleteReply(Reply reply) {
		Connection conn = getConnection();
		List<Reply> replyList = null;
		
		/* 댓글 삭제 */
		int result= boardDao.deleteReply(conn, reply);
		
		
		if (result > 0) {
			commit(conn);
			/* 갱신 된 댓글 목록 조회 */
			replyList = boardDao.selectReplyList(conn, reply.getBid());
		} else {
			rollback(conn);
		}
		
		
		close(conn);
				
		return replyList;
	}

	
	
	
	
	
	
	
	
	
	
	

}

