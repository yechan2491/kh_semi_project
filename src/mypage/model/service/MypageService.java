package mypage.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import friend.model.vo.Board;
import manager.model.vo.PageInfo;
import manager.model.vo.Report;
import member.model.dao.MemberDao;
import member.model.vo.Member;
import mypage.model.dao.MypageDao;

public class MypageService {

	private MemberDao memberDao = new MemberDao();
	private MypageDao mypageDao = new MypageDao();
	
	public Member modifyMember(Member member) {
		Connection conn = getConnection();
		Member updatedMember = null;
		
		int result = mypageDao.modifyMember(conn, member);
		
		if(result > 0) {
			updatedMember=memberDao.selectMember(conn,member.getUserNo());
			commit(conn);
		} 
		else {
			rollback(conn);
		}
		
		close(conn);
		
		return updatedMember;
	}

	public int deleteAccount(int userNo) {
		  // DB와의 연동을 목적
		   Connection conn = getConnection();
		      
		   int result = mypageDao.deleteAccount(conn, userNo);
		   
		   if(result > 0) {
		      commit(conn);
		   }else {
		      rollback(conn);
		   }
		   close(conn);
		   
		   return result;
	}

	public int myScrapInsert(int bid, int userNo) {
		 Connection conn = getConnection();
	      
		   int result = mypageDao.myScrapInsert(conn, bid,userNo);
		   
		   if(result > 0) {
		      commit(conn);
		   }else {
		      rollback(conn);
		   }
		   close(conn);
		   
		   return result;
	}

	public int myScrapDelete(int bid, int userNo) {
		Connection conn = getConnection();
	      
		   int result = mypageDao.myScrapDelete(conn, bid,userNo);
		   
		   if(result > 0) {
		      commit(conn);
		   }else {
		      rollback(conn);
		   }
		   close(conn);
		   
		   return result;
	}

	public Map<String, Object> selectLikeList(int page, int userNo) {
		 Connection conn = getConnection();
	      
	      // 1. 게시글 총 개수 구하기(게시글 총수에 맞춰 페이징바 개수 설정 위해)
	      int listCount = mypageDao.getListCount(conn,userNo);
	      
//	      System.out.println(listCount);
	      
	      // 2. pageInfo 객체 만들기
	      PageInfo pi = new PageInfo(page, listCount, 10, 10);
	      
	      // 3. 페이징 처리된 게시글 목록 조회
	      List<Board> boardList = mypageDao.selectLikeList(conn, pi, userNo);
	      
	      Map<String, Object> returnMap = new HashMap<>();
	      
	      // Object가 두 개이기 때문에 map으로 전달하면 편리(pi=쪽수, boardlist=게시글리스트)
	      returnMap.put("pi", pi);      // <String, Object>
	      returnMap.put("boardList", boardList);
	      
	      close(conn);
	      
	      return returnMap;
	}

	public int myProductInsert(int bid, int userNo) {
		 Connection conn = getConnection();
	      
		   int result = mypageDao.myProductInsert(conn, bid,userNo);
		   
		   if(result > 0) {
		      commit(conn);
		   }else {
		      rollback(conn);
		   }
		   close(conn);
		   
		   return result;
	}

	public int myProductDelete(int mid, int userNo) {
		Connection conn = getConnection();
	      
		   int result = mypageDao.myProductDelete(conn, mid,userNo);
		   
		   if(result > 0) {
		      commit(conn);
		   }else {
		      rollback(conn);
		   }
		   close(conn);
		   
		   return result;
	}

	public Map<String, Object> selectProductList(int page, int userNo) {
		 Connection conn = getConnection();
	      
	      // 1. 게시글 총 개수 구하기(게시글 총수에 맞춰 페이징바 개수 설정 위해)
	      int listCount = mypageDao.getProductListCount(conn,userNo);
	      
//	      System.out.println(listCount);
	      
	      // 2. pageInfo 객체 만들기
	      PageInfo pi = new PageInfo(page, listCount, 10, 10);
	      
	      // 3. 페이징 처리된 게시글 목록 조회
	      List<Board> boardList = mypageDao.selectProductList(conn, pi, userNo);
	      
	      Map<String, Object> returnMap = new HashMap<>();
	      
	      // Object가 두 개이기 때문에 map으로 전달하면 편리(pi=쪽수, boardlist=게시글리스트)
	      returnMap.put("pi", pi);      // <String, Object>
	      returnMap.put("boardList", boardList);
	      
	      close(conn);
	      
	      return returnMap;
	}

	public int selectLikeCount(int userNo) {
		Connection conn = getConnection();
		
		int likeCount = mypageDao.getlikeCount(conn,userNo);
		
		close(conn);
		
		return likeCount;
	}

	public int selectProductCount(int userNo) {
	Connection conn = getConnection();
		
		int productCount = mypageDao.getproductCount(conn,userNo);
		
		close(conn);
		
		return productCount;
	}

}
