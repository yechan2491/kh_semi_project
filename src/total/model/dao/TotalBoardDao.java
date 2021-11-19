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
import total.model.vo.PageInfo;

public class TotalBoardDao {
	private Properties boardQuery = new Properties(); // map 타입 일종으로 키와 value가 하나의 엔트리이고 둘다 스트링 타입이다.

	public TotalBoardDao() {
		String fileName = TotalBoardDao.class.getResource("/sql/totalboard/totalboard-query.xml").getPath();

		try {
			boardQuery.loadFromXML(new FileInputStream(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int getListCount(Connection conn, String search) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int listCount = 0;
		String sql = boardQuery.getProperty("getListCount");
		
		if(search!= null) {
			sql = boardQuery.getProperty("getSearchListCount");
		}
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			if(search != null) {
				pstmt.setString(1, search);
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

	public List<Board> selectBoardList(Connection conn, PageInfo pi, String search) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = boardQuery.getProperty("selectList");
		List<Board> boardList = new ArrayList<>();
		
		if(search!= null) {
			sql = boardQuery.getProperty("selectSearchList");
		}
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			int startRow = (pi.getPage() - 1) * pi.getBoardLimit() + 1;
			int endRow = startRow + pi.getBoardLimit() - 1;
			
			int index = 1;
			
			if(search!= null) {
				pstmt.setString(index++, search);
			}
			
			pstmt.setInt(index++, startRow);
			pstmt.setInt(index, endRow);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Board board = new Board();
				board.setBid(rset.getInt("bid"));
				board.setBtitle(rset.getString("btitle"));
				board.setNickname(rset.getString("nickname"));
				board.setBcount(rset.getInt("bcount"));
				
				if(rset.getInt("btype") == 1)
					board.setBtype("자취는 꿀팁");
				else if(rset.getInt("btype") == 2)
					board.setBtype("자취는 꿀템");
				else
					board.setBtype("자취는 꿀친");
				
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
	
}





