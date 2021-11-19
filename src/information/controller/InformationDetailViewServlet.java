package information.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import friend.model.service.FriendService;
import information.model.service.InformationService;
import information.model.vo.Board;
import member.model.vo.Member;



/**
 * Servlet implementation class InformationInsertServlet
 */
@WebServlet("/info/detail")
public class InformationDetailViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InformationDetailViewServlet() {
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
		InformationService boardService = new InformationService();
		/* 동일 게시글에 대한 조회수 무한 증가 방지 처리 -> cookie 활용  */
		
		// 요청으로부터 Cookie 정보들을 얻어옴
		Cookie[] cookies = request.getCookies();
		
		// bcount라는 쿠키의 값 담을 변수 선언
		String bcount = "";
		
		// 쿠키가 잘 넘어 왔다면
		if(cookies != null && cookies.length > 0) {
			// 넘어온 쿠키 값 배열을 처음부터 끝까지 반복하며 탐색
			for(Cookie c : cookies) {
				// 읽은 게시물 bid 를 저장해두는 쿠키의 이름 bcount가 있는지 확인
				if(c.getName().equals("bcount")) {
					bcount = c.getValue();
				}
			}
		}
		
		// 처음 읽은 게시글일 경우(해당 게시글을 찾지 못했다 => -1)
		// Ex. "|1| |22| |100|" 와 같이 bcount cookie의 value 값에서 indexOf로 해당 문자열 찾기
		if(bcount.indexOf("|" + bid + "|") == -1) {
			// 기본 bcount 값에 지금 요청한 bid 값 추가하여 새로운 쿠키 생성
			Cookie newBcount = new Cookie("bcount", bcount + "|" + bid + "|");
			// 초 단위로 유효기간 설정 가능 (Ex. 하루동안)
			// 설정하지 않을 시 sessin cookie
			// newBcount.setMaxAge(1 * 24 * 60 * 60);    => 이거 설정 안 하면 session 단위만큼 유효하게 됨
			// 클라이언트가 저장하고 있을 수 있도록 응답에 담는다
			response.addCookie(newBcount);
			
			// DB의 해당 게시글 조회수 증가
			int result = boardService.increaseCount(bid);
			
			if(result > 0) {
				System.out.println("조회수 증가 성공");
			} else {
				System.out.println("조회수 증가 실패");
			}			
		}
		
		
		
		
		/* 사진 게시판 게시글 조회 */
		Board board = boardService.selectGallery(bid);
		
		// System.out.println(board);
		// 좋아요 여부 조회
		if((Member)request.getSession().getAttribute("loginUser") != null) {
				
			int userNo = ((Member)request.getSession().getAttribute("loginUser")).getUserNo();
			int likeBid = new FriendService().selectLikeUser(userNo,bid);
			if(likeBid != 0) {
				request.setAttribute("likeBid", likeBid);
			}
		}
		
		if(board != null) {
			request.setAttribute("board", board);
			request.getRequestDispatcher("/WEB-INF/views/information/informationDetailView.jsp").forward(request, response);
		}else {
			request.setAttribute("message", "사진 게시판 게시글 상세보기에 실패하였습니다.");
			request.getRequestDispatcher("/WEB-INF/views/common/errorpage.jsp").forward(request, response);
		}
		
	

		
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
