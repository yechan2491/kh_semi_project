 package marketplace.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import marketplace.model.service.MarketplaceService;
import marketplace.model.vo.Board;
import member.model.vo.Member;

/**
 * Servlet implementation class MarketplaceDetailServlet
 */
@WebServlet("/marketplace/detail")
public class MarketplaceDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MarketplaceDetailServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int bid = Integer.parseInt(request.getParameter("bid"));
		MarketplaceService marketservice = new MarketplaceService();

		/* 동일 게시글에 대한 조회수 무한 증가 방지 처리 -> cookie 활용 */

		/* 요청으로부터 Cookie 정보들을 얻어옴 */
		// 쿠키는 name=value로 구성
		Cookie[] cookies = request.getCookies(); // servletHttp 타입으로 import

		// bcount라는 쿠키의 값 담을 변수 선언
		String bcount = "";

		// 쿠키가 잘 넘어 왔다면
		if (cookies != null && cookies.length > 0) {
			// 넘어온 쿠키 값 배열을 처음부터 끝까지 반복하며 탐색
			for (Cookie c : cookies) {
				// 읽은 게시물 bid를 저장해두는 쿠키의 이름 bcount가 있는지 확인
				if (c.getName().equals("bcount")) {
					bcount = c.getValue();
				}
			}
		}

		// 처음 읽는 게시글일 경우
		// Ex. "|1||22||100|" 와 같은 bcount cookie의 value 값에서 indexOf로 해당 문자열 찾기
		if (bcount.indexOf("|" + bid + "|") == -1) {
			// 기본 bcount 값에 지금 요청한 bid 값 추가하여 새로운 쿠키 생성
			Cookie newBcount = new Cookie("bcount", bcount + "|" + bid + "|");
			// 초 단위로 유효 기간 설정 가능 (Ex. 하루동안)
			// 설정하지 않을 시 session cookie
			// newBcount.setMaxAge(1 * 24 * 60 * 60);
			// 지금은 주석처리 해서 세션단위
			// 클라이언트가 저장하고 있을 수 있도록 응답에 담는다
			response.addCookie(newBcount);

			// DB의 해당 게시글 조회수 증가
			int result = marketservice.increaseCount(bid);

			if (result > 0) {
				System.out.println("조회수 증가 성공");
			} else {
				System.out.println("조회수 증가 실패");
			}
		}

		Board board = marketservice.selectMarketplace(bid);
		System.out.println(board.getProfilePath());
		
		
		// 좋아요 여부 조회
		if((Member)request.getSession().getAttribute("loginUser")!=null) {
			
		int userNo = ((Member)request.getSession().getAttribute("loginUser")).getUserNo();
		int likeBid = marketservice.selectLikeUser(userNo,bid);
		if(likeBid != 0) {
			request.setAttribute("likeBid", likeBid);
		}
		}
		
		
//		System.out.println(board);
		
		if (board != null) {
			int imageCount= board.getImageList().size();
			request.setAttribute("imageCount", imageCount);
			request.setAttribute("board", board);
			request.getRequestDispatcher("/WEB-INF/views/marketplace/marketplaceDetailView.jsp").forward(request, response);
		} else {
			request.setAttribute("message", "게시글 상세 조회에 실패하였습니다.");
			request.getRequestDispatcher("/WEB-INF/views/common/errorpage.jsp").forward(request, response);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
