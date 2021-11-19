package manager.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import manager.model.dao.AdDao;
import manager.model.vo.Advertise;
import manager.model.vo.PageInfo;

public class AdService {

	private AdDao adDao = new AdDao();

	public int insertAd(Advertise ad) {
		Connection conn = getConnection();

		int result = adDao.insertAd(conn, ad);

		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}

		close(conn);

		return result;
	}

	public Map<String, Object> selectList(int page) {
		
		 Connection conn = getConnection();
	      
	      // 1. 게시글 총 개수 구하기(게시글 총수에 맞춰 페이징바 개수 설정 위해)
	      int listCount = adDao.getListCount(conn);
	      
//	      System.out.println(listCount);
	      
	      // 2. pageInfo 객체 만들기
	      PageInfo pi = new PageInfo(page, listCount, 10, 10);
	      
	      // 3. 페이징 처리된 게시글 목록 조회
	      List<Advertise> adList = adDao.selectList(conn, pi);
	      
//	      System.out.println(pi);
//	      System.out.println(boardList);
	      
	      Map<String, Object> returnMap = new HashMap<>();
	      
	      // Object가 두 개이기 때문에 map으로 전달하면 편리(pi=쪽수, boardlist=게시글리스트)
	      returnMap.put("pi", pi);      // <String, Object>
	      returnMap.put("adList", adList);
	      
	      close(conn);
	      
	      return returnMap;
	}

	public int increaseRead(int adNo) {
		  Connection conn = getConnection();
	      
	      int result = adDao.increaseRead(conn, adNo);
	      
	      if(result > 0) {
	         commit(conn);
	      } else {
	         rollback(conn);
	      }
	      
	      close(conn);
	      
	      return result;

	}

	public Advertise selectAd(int adNo) {
		  Connection conn = getConnection();
		  Advertise ad = adDao.selectAd(conn, adNo);

	      close(conn);
	      
	      return ad;
	}

}
