package mypage.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.oreilly.servlet.MultipartRequest;

import common.MyfileRenamePolicyProfile;
import member.model.service.MemberService;
import member.model.vo.Member;
import mypage.model.service.MypageService;

/**
 * Servlet implementation class MyModify
 */
@WebServlet("/mypage/modify")
public class MyModify extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyModify() {
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
		if(!ServletFileUpload.isMultipartContent(request)) {
			request.setAttribute("message", "잘못된 전송입니다.");
			return;
		}
		
		int maxSize = 1024*1024*10;
		
		String root = request.getSession().getServletContext().getRealPath("/");
		String savePath = root + "resources\\uploadFiles\\";
		
		MultipartRequest multiRequest = new MultipartRequest(request, savePath, maxSize, "UTF-8", new MyfileRenamePolicyProfile());
		
		int userNo = ((Member)request.getSession().getAttribute("loginUser")).getUserNo();
		String nickName = multiRequest.getParameter("user_nickname");
		String userName = multiRequest.getParameter("user_name");
		String phoneArr[] = multiRequest.getParameterValues("user_phone");
		String emailArr[] = multiRequest.getParameterValues("user_email");
		
		
		String phone = "";
		String email = "";
		if(phoneArr != null) {
			phone = phoneArr[0]+phoneArr[1]+phoneArr[2];
		}
		
	
		if(emailArr[1].contains("direct")) {
			email = emailArr[0]+"@"+multiRequest.getParameter("user_email_direct");
		}else {
			email = String.join("@", emailArr);
		}
	
		
		Member member = new Member();
		member.setUserNo(userNo);
		member.setUserName(userName);
		member.setNickName(nickName);
		
		member.setPhone(phone);
		member.setEmail(email);
		
		String profilePath ="";
		
		if(multiRequest.getFilesystemName("profile")== null) {
			profilePath = ((Member)request.getSession().getAttribute("loginUser")).getProfilePath();
		}else {
		String rename = multiRequest.getFilesystemName("profile");
		profilePath = "/resources/uploadFiles/" + rename;
		}
		member.setProfilePath(profilePath);
		
		Member updatedMember = new MypageService().modifyMember(member);
		
		if(updatedMember != null) {
			request.getSession().setAttribute("message", "회원 정보 수정이 완료 되었습니다.");
			request.getSession().setAttribute("loginUser", updatedMember);			
			response.sendRedirect(request.getContextPath());
		}else {
			request.getSession().setAttribute("message", "회원 정보 수정에 실패되었습니다.");
		}
		
		
	}

}
