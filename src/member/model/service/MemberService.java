package member.model.service;

import java.sql.Connection;
import static common.JDBCTemplate.*;

import member.model.dao.MemberDao;
import member.model.vo.Member;

public class MemberService 
{
	private MemberDao memberDao = new MemberDao();
	
	public Member loginMember(String userId, String userPwd) 
	{
		Connection conn = getConnection();
		
		Member loginMember = memberDao.loginMember(conn,userId,userPwd);
		close(conn);
		return loginMember;
	}

	public int insertMember(Member member) {
		Connection conn = getConnection();
		
		int result = memberDao.insertMember(conn, member);
		
		if(result > 0) {
			commit(conn);
		} 
		else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}

	public Member updateMember(Member member) {
		Connection conn = getConnection();
		Member updatedMember = null;
		
		int result = memberDao.updateMember(conn, member);
		
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

	public Member updatePwd(int userNo, String userPwd, String newPwd) {
		Connection conn = getConnection();
		Member updatedMember = null;
		
		int result = memberDao.updatePwd(conn, userNo, userPwd, newPwd);
		
		if(result > 0) {
			updatedMember = memberDao.selectMember(conn, userNo);
			commit(conn);
		}
		else
			rollback(conn);
		
		return updatedMember;
	}

	public int deleteAccount(int userNo) {
		Connection conn = getConnection();
		
		int result = memberDao.deleteAccount(conn, userNo);
		if(result > 0)
			commit(conn);
		else
			rollback(conn);
		
		return result;
	}

	public int idCheck(String userId) {
		Connection conn = getConnection();
		
		int result = memberDao.idCheck(conn, userId);
		
		close(conn);
		
		return result;
	}

	public int nickCheck(String nickName) {
		Connection conn = getConnection();
		
		int result = memberDao.nickCheck(conn, nickName);
		
		close(conn);
		
		return result;
	}

	public String idFind(String userName, String phone) {
		Connection conn = getConnection();
		
		String result = memberDao.idFind(conn, userName, phone);
		
		close(conn);

		return result;
	}

	public String pwdFind(String userId, String email) {
		Connection conn = getConnection();
		
		String result = memberDao.pwdFind(conn, userId, email);
		
		close(conn);

		return result;
	}

	public int changePwd(String encPwd, String userId) {
		Connection conn = getConnection();
		
		int result = memberDao.changePwd(conn, encPwd, userId);
		
		if(result > 0) {
			commit(conn);
		} 
		else {
			rollback(conn);
		}
		
		close(conn);

		return result;
	}

	public String getEmail(String userId, String phone) {
		Connection conn = getConnection();
		
		String result = memberDao.getEmail(conn, userId, phone);
		
		close(conn);

		return result;
	}

	public String pwdCheck(String userId) {
		Connection conn = getConnection();
		
		String result = memberDao.pwdCheck(conn,userId);
		
		close(conn);
		
		return result;
	}
}
