package note.model.dao;

import static common.JDBCTemplate.close;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import member.model.vo.Member;
import note.model.vo.Note;
import note.model.vo.PageInfo;
import note.model.vo.Search;

public class NoteDao {

	private Properties noteQuery = new Properties(); // map 타입 일종으로 키와 value가 하나의 엔트리이고 둘다 스트링 타입이다.

	public NoteDao() {
		String fileName = NoteDao.class.getResource("/sql/note/note-query.xml").getPath();

		try {
			noteQuery.loadFromXML(new FileInputStream(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int getNoteReceiveListCount(Connection conn, int userNo, Search search) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int listCount = 0;
		String sql = noteQuery.getProperty("getNoteReceiveListCount");

		// 검색 된 목록을 조회해야 하는 경우 다른 SQL문 수행
		if (search.getSearchCondition() != null && search.getSearchValue() != null) {
			if (search.getSearchCondition().equals("title")) {
				sql = noteQuery.getProperty("getTitletNoteReceiveListCount");
			} else if (search.getSearchCondition().equals("content")) {
				sql = noteQuery.getProperty("getContentNoteReceiveListCount");
			} else if (search.getSearchCondition().equals("writer")) {
				sql = noteQuery.getProperty("getWritertNoteReceiveListCount");
			}

		}

		int index = 1;

		try {
			pstmt = conn.prepareStatement(sql);
			if (search.getSearchCondition() != null && search.getSearchValue() != null) {
				pstmt.setString(index++, search.getSearchValue());
			}

			pstmt.setInt(index, userNo);
			rset = pstmt.executeQuery();

			if (rset.next()) {
				listCount = rset.getInt(1); // count(*) 같은 경우는 컬럼헤드를 부를수 없음 조회할때 별칭을 사용하거나 해야함
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return listCount;
	}

	public List<Note> selectNoteReceiveList(Connection conn, int userNo, PageInfo pi, Search search) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = noteQuery.getProperty("selectNoteReceiveList");
		List<Note> noteReceiveList = new ArrayList<>();

		// 검색 시 수행할 쿼리문 변경
		if (search.getSearchCondition() != null && search.getSearchValue() != null) {
			if (search.getSearchCondition().equals("title")) {
				sql = noteQuery.getProperty("selectNoteTitleReceiveList");
			} else if (search.getSearchCondition().equals("content")) {
				sql = noteQuery.getProperty("selectNoteContentReceiveList");
			} else if (search.getSearchCondition().equals("writer")) {
				sql = noteQuery.getProperty("selectNoteWriterReceiveList");
			}
		}
		int index = 1;

		try {
			pstmt = conn.prepareStatement(sql);

			int startRow = (pi.getPage() - 1) * pi.getBoardLimit() + 1;
			int endRow = startRow + pi.getBoardLimit() - 1;
			if (search.getSearchCondition() != null && search.getSearchValue() != null) {
				pstmt.setString(index++, search.getSearchValue());
			}

			pstmt.setInt(index++, userNo);
			pstmt.setInt(index++, startRow);
			pstmt.setInt(index, endRow);

			rset = pstmt.executeQuery();
			while (rset.next()) {
				Note note = new Note();
				note.setNno(rset.getInt("nno"));
				note.setNtitle(rset.getString("ntitle"));
				note.setNcontent(rset.getString("ncontent"));
				note.setSendDeleteStatus(rset.getString("send_delete_status"));
				note.setReceiveDeleteStatus(rset.getString("receive_delete_status"));
				note.setSendDate(rset.getTimestamp("send_date"));
				note.setReadCheck(rset.getString("read_check"));
				note.setReceiveId(rset.getInt("receive_id"));
				note.setSendId(rset.getInt("send_id"));
				note.setNickName(rset.getString("nickname"));
				note.setRnum(rset.getInt("rnum"));

				noteReceiveList.add(note);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return noteReceiveList;
	}

	public int getNoteSendListCount(Connection conn, int userNo, Search search) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int listCount = 0;
		String sql = noteQuery.getProperty("getNoteSendListCount");

		// 검색 된 목록을 조회해야 하는 경우 다른 SQL문 수행
		if (search.getSearchCondition() != null && search.getSearchValue() != null) {
			if (search.getSearchCondition().equals("title")) {
				sql = noteQuery.getProperty("getTitletNoteSendListCount");
			} else if (search.getSearchCondition().equals("content")) {
				sql = noteQuery.getProperty("getContentNoteSendListCount");
			} else if (search.getSearchCondition().equals("writer")) {
				sql = noteQuery.getProperty("getWritertNoteSendListCount");
			}

		}

		int index = 1;

		try {
			pstmt = conn.prepareStatement(sql);
			if (search.getSearchCondition() != null && search.getSearchValue() != null) {
				pstmt.setString(index++, search.getSearchValue());
			}

			pstmt.setInt(index, userNo);
			rset = pstmt.executeQuery();

			if (rset.next()) {
				listCount = rset.getInt(1); // count(*) 같은 경우는 컬럼헤드를 부를수 없음 조회할때 별칭을 사용하거나 해야함
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return listCount;
	}

	public List<Note> selectNoteSendList(Connection conn, int userNo, PageInfo pi, Search search) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = noteQuery.getProperty("selectNoteSendList");
		List<Note> noteSendList = new ArrayList<>();

		// 검색 시 수행할 쿼리문 변경
		if (search.getSearchCondition() != null && search.getSearchValue() != null) {
			if (search.getSearchCondition().equals("title")) {
				sql = noteQuery.getProperty("selectNoteTitleSendList");
			} else if (search.getSearchCondition().equals("content")) {
				sql = noteQuery.getProperty("selectNoteContentSendList");
			} else if (search.getSearchCondition().equals("writer")) {
				sql = noteQuery.getProperty("selectNoteWriterSendList");
			}
		}
		int index = 1;

		try {
			pstmt = conn.prepareStatement(sql);

			int startRow = (pi.getPage() - 1) * pi.getBoardLimit() + 1;
			int endRow = startRow + pi.getBoardLimit() - 1;
			if (search.getSearchCondition() != null && search.getSearchValue() != null) {
				pstmt.setString(index++, search.getSearchValue());
			}

			pstmt.setInt(index++, userNo);
			pstmt.setInt(index++, startRow);
			pstmt.setInt(index, endRow);

			rset = pstmt.executeQuery();
			while (rset.next()) {
				Note note = new Note();
				note.setNno(rset.getInt("nno"));
				note.setNtitle(rset.getString("ntitle"));
				note.setNcontent(rset.getString("ncontent"));
				note.setSendDeleteStatus(rset.getString("send_delete_status"));
				note.setReceiveDeleteStatus(rset.getString("receive_delete_status"));
				note.setSendDate(rset.getTimestamp("send_date"));
				note.setReadCheck(rset.getString("read_check"));
				note.setReceiveId(rset.getInt("receive_id"));
				note.setSendId(rset.getInt("send_id"));
				note.setNickName(rset.getString("nickname"));
				note.setRnum(rset.getInt("rnum"));

				noteSendList.add(note);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return noteSendList;
	}

	public Note selectReceiveNote(Connection conn, int nno, int userNo) {
		Note note = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = noteQuery.getProperty("selectReceiveNote");

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userNo);
			pstmt.setInt(2, nno);
			rset = pstmt.executeQuery();

			if (rset.next()) {
				note = new Note();
				note.setRnum(rset.getInt("rnum"));
				note.setNno(rset.getInt("nno"));
				note.setNtitle(rset.getString("ntitle"));
				note.setNcontent(rset.getString("ncontent"));
				note.setSendDate(rset.getTimestamp("send_date"));
				note.setReceiveDeleteStatus(rset.getString("receive_delete_status"));
				note.setSendDeleteStatus(rset.getString("send_delete_status"));
				note.setReadCheck(rset.getString("read_check"));
				note.setReceiveId(rset.getInt("receive_id"));
				note.setSendId(rset.getInt("send_id"));
				note.setSendNickname(rset.getString("send_nickname"));
				note.setReceiveNickname(rset.getString("receive_nickname"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}

		return note;
	}

	public int readStateChange(Connection conn, int nno, int userNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = noteQuery.getProperty("readStateChange");

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, nno);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}

	public Note selectSendNote(Connection conn, int nno, int userNo) {
		Note note = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = noteQuery.getProperty("selectSendNote");

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userNo);
			pstmt.setInt(2, nno);
			rset = pstmt.executeQuery();

			if (rset.next()) {
				note = new Note();
				note.setRnum(rset.getInt("rnum"));
				note.setNno(rset.getInt("nno"));
				note.setNtitle(rset.getString("ntitle"));
				note.setNcontent(rset.getString("ncontent"));
				note.setSendDate(rset.getTimestamp("send_date"));
				note.setReceiveDeleteStatus(rset.getString("receive_delete_status"));
				note.setSendDeleteStatus(rset.getString("send_delete_status"));
				note.setReadCheck(rset.getString("read_check"));
				note.setReceiveId(rset.getInt("receive_id"));
				note.setSendId(rset.getInt("send_id"));
				note.setSendNickname(rset.getString("send_nickname"));
				note.setReceiveNickname(rset.getString("receive_nickname"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}

		return note;
	}

	public List<Note> timeprocessingNoteList(List<Note> noteReceiveList) {
		
		for(Note note : noteReceiveList) {
			 
			String result=formatTimeString(note.getSendDate());
			note.setTimeView(result);
//			System.out.println(result);
		}
		
		return noteReceiveList;
	}
	
	public Note timeprocessingNote(Note note) {
		 
		String result=formatTimeString(note.getSendDate());
		note.setTimeView(result);
		
		
		return note;
	}

	private static class TIME_MAXIMUM {
		public static final int SEC = 60;
		public static final int MIN = 60;
		public static final int HOUR = 24;
		public static final int DAY = 30;
		public static final int MONTH = 12;
	}
	public static String formatTimeString(Date tempDate) {
		long curTime = System.currentTimeMillis();
		long regTime = tempDate.getTime();
		long diffTime = (curTime - regTime) / 1000;
		String msg = null;
		if (diffTime < TIME_MAXIMUM.SEC) {
			// sec
			msg = "방금 전";
		} else if ((diffTime /= TIME_MAXIMUM.SEC) < TIME_MAXIMUM.MIN) {
			// min
			msg = diffTime + "분전";
		} else if ((diffTime /= TIME_MAXIMUM.MIN) < TIME_MAXIMUM.HOUR) {
			// hour
			msg = (diffTime) + "시간전";
		} else if ((diffTime /= TIME_MAXIMUM.HOUR) < TIME_MAXIMUM.DAY) {
			// day
			msg = (diffTime) + "일전";
		} else if ((diffTime /= TIME_MAXIMUM.DAY) < TIME_MAXIMUM.MONTH) {
			// day
			msg = (diffTime) + "달전";
		} else {
			msg = (diffTime) + "년전";
		}
		return msg;

	}

	public int deleteReceiveNote(Connection conn, int nno) {
		String sql = noteQuery.getProperty("deleteReceiveNote");
		PreparedStatement pstmt = null;
		int result = 0;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, nno);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}

	public int deleteSendNote(Connection conn, int nno) {
		String sql = noteQuery.getProperty("deleteSendNote");
		PreparedStatement pstmt = null;
		int result = 0;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, nno);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}

	public int nicknameSearch(Connection conn, String userNickname) {
		PreparedStatement pstmt = null;
		ResultSet rset= null;
		int result=0;
		String sql = noteQuery.getProperty("nicknameSearch");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userNickname);
			
			rset=pstmt.executeQuery();
			
			if(rset.next()) {
				result=rset.getInt(1);				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}
		return result;
	}

	public Member getUserNickname(Connection conn, int userNo) {
		PreparedStatement pstmt = null;
		ResultSet rset= null;
		Member member =null;
		String sql = noteQuery.getProperty("getUserNickname");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userNo);
			
			rset=pstmt.executeQuery();
			
			if(rset.next()) {
				member =new Member();
				member.setNickName(rset.getString(1));		
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}
		return member;
	}

	public int getReceiveUserId(Connection conn, String receiveUserNickname) {
		PreparedStatement pstmt = null;
		ResultSet rset= null;
		String sql = noteQuery.getProperty("getReceiveUserId");
		int receiveUserId=0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, receiveUserNickname);
			
			rset=pstmt.executeQuery();
			
			if(rset.next()) {
				receiveUserId=rset.getInt("user_no");	
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}
		return receiveUserId;
	}

	public int insertNote(Connection conn, Note note) {
		PreparedStatement pstmt=null;
		String sql = noteQuery.getProperty("insertNote");
		int result=0;
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, note.getNtitle());
			pstmt.setString(2, note.getNcontent());
			pstmt.setInt(3, note.getReceiveId());
			pstmt.setInt(4, note.getSendId());
			
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	
}
