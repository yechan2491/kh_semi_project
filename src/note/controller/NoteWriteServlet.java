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
 * Servlet implementation class NoteWriteServlet
 */
@WebServlet("/note/write")
public class NoteWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoteWriteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Member loginUser = (Member) request.getSession().getAttribute("loginUser");
		int userNo= loginUser.getUserNo();
		String receiveUser= request.getParameter("receiveUser");
		
		/* userNickname 조회 */
		Member member = new NoteService().getUserNickname(userNo);
		request.setAttribute("member", member);
		request.setAttribute("receiveUser", receiveUser);
		request.getRequestDispatcher("/WEB-INF/views/note/noteWriteView.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		Member loginUser = (Member) request.getSession().getAttribute("loginUser");
		int userNo= loginUser.getUserNo();
		String sendUserNickname =request.getParameter("sendUserNickname");
		String receiveUserNickname=request.getParameter("receiveUserNickname");
		String ntitle=request.getParameter("title");
		String ncontent=request.getParameter("content");
		
		Note note= new Note();
		note.setSendId(userNo);
		
		note.setReceiveNickname(receiveUserNickname);
		note.setSendNickname(sendUserNickname);
		note.setNtitle(ntitle);
		note.setNcontent(ncontent);
		
		/* 쪽지보내기 */
		int result = new NoteService().noteWrite(note);
		if(result>0) {
			response.sendRedirect(request.getContextPath()+"/note/send/list");
		} else {
			request.setAttribute("message", "쪽지 전송에 실패하였습니다.");
			request.getRequestDispatcher("/WEB-INF/views/common/errorpage.jsp").forward(request, response);
		}
		
//		System.out.println(note);
		
		
	}

}
