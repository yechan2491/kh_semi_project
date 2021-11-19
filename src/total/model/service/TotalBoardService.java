package total.model.service;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import total.model.dao.TotalBoardDao;
import total.model.vo.Board;
import total.model.vo.PageInfo;

import static common.JDBCTemplate.*;

public class TotalBoardService {
	private TotalBoardDao boardDao = new TotalBoardDao();

	public Map<String, Object> selectBoardList(int page) {
		
		return null;
	}

	public Map<String, Object> selectBoardList(int page, String search) {
		Connection conn = getConnection();

		// 1. 게시글 총 개수 구하기
		int listCount = boardDao.getListCount(conn, search);
		System.out.println("list : " + listCount);

		// 2. PageInfo 객체 만들기
		PageInfo pi = new PageInfo(page, listCount, 10, 15);

		// 3. 페이징 처리 된 게시글 목록 조회
		List<Board> boardList = boardDao.selectBoardList(conn, pi, search);

		Map<String, Object> returnMap = new HashMap<>();

		returnMap.put("pi", pi);
		returnMap.put("boardList", boardList);
		close(conn);
		
		return returnMap;
	}

	
}
