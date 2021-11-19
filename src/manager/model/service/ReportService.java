package manager.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import manager.model.dao.ReportDao;
import manager.model.vo.PageInfo;
import manager.model.vo.Report;


public class ReportService {

	private ReportDao reportDao = new ReportDao();

	public int boardReport(int bid, Report report) {
		Connection conn = getConnection();
		
		int result = reportDao.boardReport(conn,report);
		
		int result2 = reportDao.ridSortBid(conn,bid);
		
		if(result>0 && result2>0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		
		return result;
	}

	public Map<String, Object> selectList(int page) {
		 Connection conn = getConnection();
	      
	      // 1. 게시글 총 개수 구하기(게시글 총수에 맞춰 페이징바 개수 설정 위해)
	      int listCount = reportDao.getListCount(conn);
	      
//	      System.out.println(listCount);
	      
	      // 2. pageInfo 객체 만들기
	      PageInfo pi = new PageInfo(page, listCount, 10, 10);
	      
	      // 3. 페이징 처리된 게시글 목록 조회
	      List<Report> reportList = reportDao.selectList(conn, pi);
	      
//	      System.out.println(pi);
//	      System.out.println(boardList);
	      
	      Map<String, Object> returnMap = new HashMap<>();
	      
	      // Object가 두 개이기 때문에 map으로 전달하면 편리(pi=쪽수, boardlist=게시글리스트)
	      returnMap.put("pi", pi);      // <String, Object>
	      returnMap.put("reportList", reportList);
	      
	      close(conn);
	      
	      return returnMap;
	}

	public Report reportDetail(int rid) {
		Connection conn = getConnection();
		
		Report report = reportDao.reportDetail(conn,rid);
		
		close(conn);
		
		return report;
	}

	public int increaseRead(int rid) {
		Connection conn = getConnection();
	      
	      int result = reportDao.increaseRead(conn, rid);
	      
	      if(result > 0) {
	         commit(conn);
	      } else {
	         rollback(conn);
	      }
	      
	      close(conn);
	      
	      return result;
	}

	public int replyReport(int aid, Report report) {
		Connection conn = getConnection();
		
		int result = reportDao.replyReport(conn,report);
		
		int result2 = reportDao.ridSortAid(conn,aid);
		
		if(result>0 && result2>0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		
		return result;
	}

	public int noteReport(Report report) {
		Connection conn = getConnection();
		
		int result = reportDao.noteReport(conn,report);
		
		int result2 = reportDao.ridSortNno(conn,report.getNno());
		
		if(result>0 && result2>0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		
		return result;
	}

	public Report reportNoteDetail(int rid) {
		Connection conn = getConnection();
		
		Report report = reportDao.reportNoteDetail(conn,rid);
		
		close(conn);
		
		return report;
	}
	
	
}
