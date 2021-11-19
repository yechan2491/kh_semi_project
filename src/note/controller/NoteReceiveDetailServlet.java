package note.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.vo.Member;
import note.model.service.NoteService;
import note.model.vo.Note;

/**
 * Servlet implementation class NoteReceiveServlet
 */
@WebServlet("/note/receive/detail")
public class NoteReceiveDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoteReceiveDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// DB의 해당 쪽지 읽음 여부 변경
		int nno=Integer.parseInt(request.getParameter("nno"));
		int userNo;
		if(request.getParameter("userNo")!=null) {  // 관리자가 신고 링크로 접근했을때
			userNo=Integer.parseInt(request.getParameter("userNo"));
		} else { // 일반상황
			Member member=(Member)request.getSession().getAttribute("loginUser");
			userNo = member.getUserNo();
		}
		
		
		
		// 읽음 상태로 변경
		int result= new NoteService().readStateChange(nno, userNo);  
		if(result>0) {
			System.out.println("읽음 상태로 변경됨");
		} else {
			System.out.println("읽음 상태로 변경 실패이거나 이미 읽은 상태인 쪽지");
		}
		
		Note note = new NoteService().selectReceiveNote(nno, userNo);
		if (note != null) {
			request.setAttribute("note", note);
			request.getRequestDispatcher("/WEB-INF/views/note/noteReceiveDetailView.jsp").forward(request, response);
		} else {
			request.setAttribute("message", "쪽지 상세보기에 실패하였습니다.");
			request.getRequestDispatcher("/WEB-INF/views/common/errorpage.jsp").forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
