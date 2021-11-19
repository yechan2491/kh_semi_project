package manager.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import manager.model.dao.ManagerDao;
import manager.model.vo.Member;
import manager.model.vo.PageInfo;
import manager.model.vo.Report;

public class ManagerService {

	private ManagerDao managerDao = new ManagerDao();
	
	public Member selectMember(String searchCondition, String searchValue) {
		Connection conn = getConnection();
		Member member = managerDao.selectMember(conn, searchCondition, searchValue);

	    close(conn);
	      
	    return member;
	}

	public int kickOutMember(int userNo) {
		Connection conn = getConnection();
		
		int result = managerDao.kickOutMember(conn,userNo);
		
		if(result>0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		
		return result;
	}

	

}
