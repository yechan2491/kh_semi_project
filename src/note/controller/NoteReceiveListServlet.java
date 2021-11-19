package note.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import note.model.vo.Search;
import member.model.vo.Member;
import note.model.service.NoteService;


/**
 * Servlet implementation class NoteReceiveListServlet
 */
@WebServlet("/note/receive/list")
public class NoteReceiveListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public NoteReceiveListServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Member loginUser = (Member) request.getSession().getAttribute("loginUser");
		int userNo= loginUser.getUserNo();
		
		// *page : 현재 요청 페이지
		// 기본적으로 게시판은 1페이지부터 시작
		int page = 1;

		
			
		
		// 하지만 페이지 전환 시 전달 받은 현재 페이지가 있을 경우 해당 페이지를 page로 적용
		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		
		}
		
		// 검색 파라미터 추출
		String searchCondition = request.getParameter("searchCondition");
		String searchValue = request.getParameter("searchValue");
		
		// 페이징과 관련된 데이터, 조회 된 boardList를 map에 담아 리턴
		Map<String, Object> map = new NoteService().selectNoteReceiveList(userNo,page,new Search(searchCondition, searchValue));

		request.setAttribute("pi", map.get("pi"));
		request.setAttribute("noteReceiveList", map.get("noteReceiveList"));

		request.getRequestDispatcher("/WEB-INF/views/note/noteReceiveListView.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
