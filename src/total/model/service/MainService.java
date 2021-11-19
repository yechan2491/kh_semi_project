package total.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.List;

import total.model.dao.MainDao;
import total.model.vo.Board;

public class MainService {
	private MainDao boardDao = new MainDao();

	public List<Board> selectInfoList() {
		Connection conn = getConnection();
		
		List<Board> board = boardDao.selectInfoList(conn);
		
		close(conn);
		
		return board;
	}

	public Board selectToday() {
		Connection conn = getConnection();
		
		Board board = boardDao.selectToday(conn);
		
		close(conn);
		
		return board;
	}

	public List<Board> selectMarketList() {
		Connection conn = getConnection();
		
		List<Board> board = boardDao.selectMarketList(conn);
		
		close(conn);
		
		return board;
	}

	
}
