package marketplace.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import marketplace.model.service.MarketplaceService;
import marketplace.model.vo.Board;
import marketplace.model.vo.Search;

/**
 * Servlet implementation class MarketplaceListServlet
 */
@WebServlet("/marketplace/list")
public class MarketplaceListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MarketplaceListServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// *page : 현재 요청 페이지
		// 기본적으로 게시판은 1페이지부터 시작
		int page = 1;

		// 하지만 페이지 전환 시 전달 받은 현재 페이지가 있을 경우 해당 페이지를 page로 적용
		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		// 검색 파라미터 추출
		String searchCategory = request.getParameter("category");
		String searchSort = request.getParameter("sort");
		
		// 페이징과 관련된 데이터, 조회 된 boardList를 map에 담아 리턴
		Map<String, Object> map = new MarketplaceService().selectMarketplaceList(page, new Search(searchCategory, searchSort));

		
		
		request.setAttribute("pi", map.get("pi"));
		request.setAttribute("boardList", map.get("boardList"));
//		request.setAttribute("boardList", boardList);
//		System.out.println(boardList);

		request.getRequestDispatcher("/WEB-INF/views/marketplace/marketplaceListView.jsp").forward(request, response);
		//
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
