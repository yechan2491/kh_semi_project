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
 * Servlet implementation class NoteSendDetailServlet
 */
@WebServlet("/note/send/detail")
public class NoteSendDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoteSendDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int nno=Integer.parseInt(request.getParameter("nno"));		
		Member member=(Member)request.getSession().getAttribute("loginUser");
		int userNo = member.getUserNo();
		Note note = new NoteService().selectSendNote(nno, userNo);
		if (note != null) {
			request.setAttribute("note", note);
			request.getRequestDispatcher("/WEB-INF/views/note/noteSendDetailView.jsp").forward(request, response);
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
