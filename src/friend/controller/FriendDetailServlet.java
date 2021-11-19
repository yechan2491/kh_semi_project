package friend.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import friend.model.service.FriendService;
import friend.model.vo.Board;
import member.model.vo.Member;

/**
 * Servlet implementation class FriendDetail
 */
@WebServlet("/friend/detail")
public class FriendDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FriendDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int bid = Integer.parseInt(request.getParameter("bid"));
		FriendService friendService = new FriendService();
		
		// 조회수
		Cookie[] cookies = request.getCookies();
		
		String bcount = "";  // 
		
		if(cookies != null && cookies.length > 0) {
			for(Cookie c : cookies) {
				if(c.getName().equals("bcount")) {
					bcount = c.getValue();
				}
			}
		} 
		
		if(bcount.indexOf("|" + bid + "|") == -1) {

			Cookie newBcount = new Cookie("bcount", bcount + "|" + bid + "|");

			response.addCookie(newBcount);
			
			int result = friendService.increaseCount(bid);
			
			if(result > 0 ) {
				System.out.println("조회수 증가 성공");			// 성공 나옴
			} else {
				System.out.println("조회수 증가 실패");
			}
		}		
		
		// 사진 게시판 게시글 조회
		Board board = friendService.selectGalleryList(bid);
		// System.out.println(board); 
		
		// 좋아요 여부 조회
		if((Member)request.getSession().getAttribute("loginUser") != null) {
		int userNo = ((Member)request.getSession().getAttribute("loginUser")).getUserNo();
		int likeBid = friendService.selectLikeUser(userNo,bid);
		
		if(likeBid != 0) {
			request.setAttribute("likeBid", likeBid);
		}
		}
		if(board != null) {
			request.setAttribute("board", board);
			request.getRequestDispatcher("/WEB-INF/views/friend/friendDatailView.jsp").forward(request, response);
		} else {
			request.setAttribute("message", "사진 게시판 상세 조회에 실패하였습니다.");
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
