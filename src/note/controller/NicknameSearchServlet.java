package note.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import note.model.service.NoteService;

	

/**
 * Servlet implementation class NicknameSearchServlet
 */
@WebServlet("/nicknameSearch")
public class NicknameSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NicknameSearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userNickname = request.getParameter("userNickname");
//		System.out.println("userNickname : "+userNickname);
		
		/* Member 테이블에 해당 userNickname를 가진 인원 조회 
		 * 중복 아이디가 있으면 1, 중복 아이디가 없으면 0 리턴 */
		int result = new NoteService().nicknameSearch(userNickname);
//		
		PrintWriter out = response.getWriter();
		
		
		if(result>0) {
			out.print("success");
		} else {
			out.print("fail");
		}
	}

}
