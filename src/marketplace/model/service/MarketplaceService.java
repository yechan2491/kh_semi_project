package marketplace.model.service;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import marketplace.model.vo.PageInfo;
import marketplace.model.vo.Reply;
import marketplace.model.vo.Search;
import marketplace.model.vo.UpdateImage;
import marketplace.model.dao.MarketplaceDao;
import marketplace.model.vo.Board;
import marketplace.model.vo.Image;

import static common.JDBCTemplate.*;

public class MarketplaceService {
	private MarketplaceDao boardDao = new MarketplaceDao();

	public Map<String, Object> selectMarketplaceList(int page, Search search) {
		Connection conn = getConnection();

		// 1. 게시글 총 개수 구하기
		int listCount = boardDao.getListCount(conn, search);
//		System.out.println(listCount);

		// 2. PageInfo 객체 만들기
		PageInfo pi = new PageInfo(page, listCount, 10, 12);

		// 3. 페이징 처리 된 게시글 목록 조회
		List<Board> boardList = boardDao.selectMarketplaceList(conn, pi, search);

//				System.out.println(pi);
//				System.out.println(boardList);
		
		// 4. 시간 처리 (몇분전 몇시간전 등등)
		boardList =boardDao.timeprocessingBoardList(boardList);

		Map<String, Object> returnMap = new HashMap<>();

		returnMap.put("pi", pi);
		returnMap.put("boardList", boardList);
		close(conn);
		return returnMap;
	}

	public int insertMarketplace(Board board) {
		Connection conn = getConnection();
		
		// Board 테이블에 삽입
		int boardResult = boardDao.insertBoard(conn, board);
		
		
		
		// Marketplace 테이블에 삽입
		int marketplaceResult = boardDao.insertMarketplace(conn,board);
		
		// image 테이블에 삽입
		int imageResult=0;
		for(Image photo : board.getImageList()) {
			imageResult += boardDao.insertImage(conn,photo);
		} 

		
		int result=0; // 2가지 로직이 모두 잘 수행 되었음을 나타내는 변수
		if(boardResult>0 && imageResult==board.getImageList().size() && marketplaceResult>0) {
			commit(conn);
			result=1;
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
		
	}

	public int increaseCount(int bid) {
		Connection conn = getConnection();

		int result = boardDao.increaseCount(conn, bid);

		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}

		close(conn);
		return result;
	}

	public Board selectMarketplace(int bid) {
		Connection conn = getConnection();
		
		/* Board 테이블 정보 조회 */
		Board board = boardDao.selectMarketplace(conn, bid);
	
		// 4. 시간 처리 (몇분전 몇시간전 등등)
		board =boardDao.timeprocessingBoard(board);
		
		/* 댓글 조회 추가 */
		board.setReplyList(boardDao.selectReplyList(conn,bid));
		// 댓글 시간처리
		board.setReplyList(boardDao.timeprocessingReplyList(board.getReplyList()));
		
		
		/* 찜한 유저 수 조회 */
		int likeCount = boardDao.getLikeCount(conn,board.getMid());
		board.setLikeCount(likeCount);
		
		/* Image 테이블 정보 조회 */
		List<Image> photoList = boardDao.selectImageList(conn, bid);
		board.setImageList(photoList);
		
		/* 연관된 카테고리 아이템 조회 최대 4개 */
		List<Board> relationList = boardDao.selectRelationList(conn, bid, board.getCid());
		board.setRelationList(relationList);
		
		
		
		close(conn);
		
		return board;
	}

	public List<Image> deleteMarketplace(int bid) {
		Connection conn = getConnection();
		List<Image> deletedImageList = boardDao.selectImageList(conn, bid);
		
		int boardResult = boardDao.deleteBoard(conn, bid);

//		int marketplaceResult = boardDao.deleteMarketplace(conn, bid);
		
		int imageResult = boardDao.deleteImage(conn,bid);
		
		
		if(boardResult>0 && imageResult== deletedImageList.size()) {
			commit(conn);
		} else {
			rollback(conn);
			deletedImageList=null;
		}
		close(conn);
		
		return deletedImageList;
	}

	public int updateMarketplace(Board board) {
		Connection conn = getConnection();
		
		/* Board 테이블 수정 */
		int boardResult = boardDao.updateBoard(conn, board);
		
		/* marketplace 테이블 수정 */
		int marketplaceResult = boardDao.updateMarketplace(conn, board);
		
		int updatePhotoResult=0;
		int insertPhotoResult=0;
		int updateListCount = 0;
		int insertListCount = 0;
		
		
		/* Attachment 테이블 수정 및 삽입 */
		for(Image photo : board.getImageList()) {
			if(photo.getDeletedName()!=null) {
				/* 기존에 있던 파일을 덮어쓰기  - update */
				updatePhotoResult+=boardDao.updatePhoto(conn, photo);
				updateListCount++;
//				System.out.println("update : "+photo);
//				System.out.println("updatePhotoResult : "+updatePhotoResult);
			} else {
				/* 새로 첨부 된 파일을 추가하기 - insert */
				insertPhotoResult+=boardDao.insertAddedPhoto(conn,board.getBid(), photo);
				insertListCount++;
//				System.out.println("insert : "+photo);
//				System.out.println("insertPhotoResult : " + insertPhotoResult);
			}
		}
		
		int result=0;
		
		if(boardResult>0 && marketplaceResult>0
				&& updatePhotoResult == updateListCount
				&& insertPhotoResult==insertListCount) {
			commit(conn);
			result=1;
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	public int updateImage(int bid, UpdateImage updateOldImage) {
		Connection conn = getConnection();
		String[] name= {updateOldImage.getDeletThumnail(), updateOldImage.getDeletecontentImg1(), updateOldImage.getDeletecontentImg2()};
//		System.out.println("name"+name);
		int result=0;
		for(int i=0; i<name.length; i++) {
			if(name[i].equals("") || name[i].equals(null)) {
				continue;
			}
			result=boardDao.updateImage(conn, bid, name[i]);
		}
		
		
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}

		close(conn);
		return result;
	}

	public List<Reply> insertReply(Reply reply) {
		Connection conn = getConnection();
		List<Reply> replyList = null;
		
		/* 댓글 입력 */
		int result= boardDao.insertReply(conn, reply);
		
		
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

	public List<Reply> moreReply(Reply reply) {
		Connection conn = getConnection();
		List<Reply> replyList = null;
		replyList = boardDao.selectReplyList(conn, reply.getBid());
		
		close(conn);
		return replyList;
	}

	public List<Reply> reduceReply(Reply reply) {
		Connection conn = getConnection();
		List<Reply> replyList = null;
		replyList = boardDao.selectReplyListReduce(conn, reply.getBid());
		
		close(conn);
		return replyList;
	}

	public int selectLikeUser(int userNo, int bid) {
		Connection conn = getConnection();
		
		int likeUser = boardDao.selectLikeUser(conn,userNo,bid);
		
		close(conn);
		return likeUser;
	}

}
