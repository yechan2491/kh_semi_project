package note.model.service;
import static common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import member.model.vo.Member;
import note.model.dao.NoteDao;
import note.model.vo.Note;
import note.model.vo.PageInfo;
import note.model.vo.Search;


public class NoteService {

	private NoteDao noteDao= new NoteDao();

	public Map<String, Object> selectNoteReceiveList(int userNo, int page, Search search) {
		Connection conn = getConnection();

		// 1. 게시글 총 개수 구하기
		int listCount = noteDao.getNoteReceiveListCount(conn, userNo, search);
//		System.out.println(listCount);

		// 2. PageInfo 객체 만들기
		PageInfo pi = new PageInfo(page, listCount, 10, 10);

		// 3. 페이징 처리 된 게시글 목록 조회
		List<Note> noteReceiveList = noteDao.selectNoteReceiveList(conn, userNo, pi, search);

		// 4. 시간 처리 (몇분전 몇시간전 등등)
		noteReceiveList =noteDao.timeprocessingNoteList(noteReceiveList);
		
//		System.out.println(pi);
//		System.out.println(noteReceiveList);

		Map<String, Object> returnMap = new HashMap<>();

		returnMap.put("pi", pi);
		returnMap.put("noteReceiveList", noteReceiveList);

		close(conn);
		return returnMap;
	}

	public Map<String, Object> selectNoteSendList(int userNo, int page, Search search) {
		Connection conn = getConnection();

		// 1. 게시글 총 개수 구하기
		int listCount = noteDao.getNoteSendListCount(conn, userNo, search);
//		System.out.println(listCount);

		// 2. PageInfo 객체 만들기
		PageInfo pi = new PageInfo(page, listCount, 10, 10);

		// 3. 페이징 처리 된 게시글 목록 조회
		List<Note> noteSendList = noteDao.selectNoteSendList(conn, userNo, pi, search);
		
		// 4. 시간 처리 (몇분전 몇시간전 등등)
		noteSendList =noteDao.timeprocessingNoteList(noteSendList);
		
//		System.out.println(pi);
//		System.out.println(noteSendList);

		Map<String, Object> returnMap = new HashMap<>();

		returnMap.put("pi", pi);
		returnMap.put("noteSendList", noteSendList);

		close(conn);
		return returnMap;
	}

	public Note selectReceiveNote(int nno, int userNo) {
		Connection conn = getConnection();
		Note note = noteDao.selectReceiveNote(conn, nno, userNo);
		note=noteDao.timeprocessingNote(note);
		
		close(conn);
		return note;
	}

	public int readStateChange(int nno, int userNo) {
		Connection conn = getConnection();
		int result = noteDao.readStateChange(conn, nno, userNo);

		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}

		close(conn);
		return result;
	
	}

	public Note selectSendNote(int nno, int userNo) {
		Connection conn = getConnection();
		Note note = noteDao.selectSendNote(conn, nno, userNo);
		note = noteDao.timeprocessingNote(note);
		close(conn);
		return note;
	}

	public int deleteReceiveNote(int nno) {
		Connection conn = getConnection();

		int result = noteDao.deleteReceiveNote(conn, nno);

		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}

		close(conn);
		return result;
	}

	public int deleteSendNote(int nno) {
		Connection conn = getConnection();

		int result = noteDao.deleteSendNote(conn, nno);

		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}

		close(conn);
		return result;
	}

	public int nicknameSearch(String userNickname) {
		Connection conn = getConnection();
		int result = noteDao.nicknameSearch(conn, userNickname);
		
		close(conn);
		return result;
	}

	public Member getUserNickname(int userNo) {
		Connection conn = getConnection();
		Member member = noteDao.getUserNickname(conn, userNo);
		
		close(conn);
		return member;
	}

	public int noteWrite(Note note) {
		Connection conn= getConnection();
		/* 받는 사람 닉네임을 가지고 해당 유저의 번호 조회 */
		int receiveId= noteDao.getReceiveUserId(conn,note.getReceiveNickname());
		int result=0;
		
		
		note.setReceiveId(receiveId);
		result=noteDao.insertNote(conn,note);
		
		
		if(receiveId>0 && result>0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		
		close(conn);
		return result;
	}

}
