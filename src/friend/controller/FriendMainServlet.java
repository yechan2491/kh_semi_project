package friend.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import friend.model.service.FriendService;
import friend.model.vo.Board;

/**
 * Servlet implementation class FriendWriter
 */
@WebServlet("/friend/main")
public class FriendMainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FriendMainServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int page = 1;	// 시작 요청 page
		
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		// 셀렉트 값
		String searchCondition = request.getParameter("searchCondition");
		/*System.out.println(request.getParameter("searchCondition"));*/
		
		// 페이징, 셀렉 값  
		Map<String, Object> map = new FriendService().selectList(page, searchCondition); 
		
		request.setAttribute("pi", map.get("pi"));
		request.setAttribute("boardList", map.get("boardList")); 
		// System.out.println("boardListMAp : "+map.get("boardList"));

	
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/friend/friendMain.jsp");
		view.forward(request, response);
		
		
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
