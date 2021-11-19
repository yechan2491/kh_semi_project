package total.model.dao;

import static common.JDBCTemplate.close;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import total.model.vo.Board;

public class MainDao {
	private Properties boardQuery = new Properties(); // map 타입 일종으로 키와 value가 하나의 엔트리이고 둘다 스트링 타입이다.

	public MainDao() {
		String fileName = MainDao.class.getResource("/sql/totalboard/main-query.xml").getPath();

		try {
			boardQuery.loadFromXML(new FileInputStream(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<Board> selectInfoList(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Board> boardList = new ArrayList<>();
		String sql = boardQuery.getProperty("selectInfo");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();

			while (rset.next()) {
				Board board = new Board();
				board.setBid(rset.getInt("bid"));
				board.setBtitle(rset.getString("btitle"));
				board.setFilePath(rset.getString("file_path"));
				board.setChangeName(rset.getString("change_name"));
				board.setProfilePath(rset.getString("profile_path"));
				board.setNickname(rset.getString("nickname"));
				boardList.add(board);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}
		
		return boardList;
	}

	public Board selectToday(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Board board = new Board();
		String sql = boardQuery.getProperty("selectToday");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();

			if(rset.next()) {
				board.setBid(rset.getInt("bid"));
				board.setBtitle(rset.getString("btitle"));
				board.setFilePath(rset.getString("file_path"));
				board.setChangeName(rset.getString("change_name"));
				board.setBtype(rset.getString("btype"));
				board.setProfilePath(rset.getString("profile_path"));
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

	public List<Board> selectMarketList(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Board> boardList = new ArrayList<>();
		String sql = boardQuery.getProperty("selectMarket");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();

			while (rset.next()) {
				Board board = new Board();
				board.setBid(rset.getInt("bid"));
				board.setBtitle(rset.getString("btitle"));
				board.setFilePath(rset.getString("file_path"));
				board.setChangeName(rset.getString("change_name"));
				board.setProfilePath(rset.getString("PROFILE_PATH"));
				board.setNickname(rset.getString("nickname"));
				boardList.add(board);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}
		
		return boardList;
	}

}
