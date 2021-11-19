package friend.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import friend.model.service.FriendService;
import friend.model.vo.Answer;
import member.model.vo.Member;

/**
 * Servlet implementation class ReplyInsertServlet
 */
@WebServlet("/friend/deleteReply")
public class ReplydeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReplydeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int bid = Integer.parseInt(request.getParameter("bid"));
		int aid = Integer.parseInt(request.getParameter("aid"));
		int awriter = ((Member)request.getSession().getAttribute("loginUser")).getUserNo();
		
		Answer answer = new Answer();
		answer.setBid(bid);
		answer.setAid(aid);
		answer.setWriter(awriter);	
		
		// 댓글 insert 후 갱신 된 댓글 리스트 select하려 리턴
		List<Answer> friendAnswerList = new FriendService().deleteReply(answer);
		
		/* GSON 라이브러리 추가 후 GSON 객체의 toJson 메소드로 처리 */
		response.setContentType("application/json; charset=utf-8");
		// new Gson().toJson(replyList, response.getWriter());
		/* GSON 사용 시 날짜 값은 Date 포맷에 대한 컨트롤이 필요함 
		 * GsonBuilder 클래스의 setDateFormat 메소드 사용 */
		Gson gson = new GsonBuilder().setDateFormat("yyyy.MM.dd HH:mm:ss").create();
		gson.toJson(friendAnswerList, response.getWriter());
		
		
	}

}


















