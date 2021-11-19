package member.controller;

import java.io.IOException;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class PwdModifyServlet
 */
@WebServlet("/pwdModify")
public class PwdModifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PwdModifyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher view = request.getRequestDispatcher("WEB-INF/views/member/pwdModifyForm.jsp");
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userPwd = request.getParameter("userPwd");
		String newPwd = request.getParameter("newPwd");
		
		int userNo = ((Member)request.getSession().getAttribute("loginUser")).getUserNo();
		
		String encPwd1 = null;
		String encPwd2= null;
		
		try {
			MessageDigest md1 = MessageDigest.getInstance("SHA-512");
			MessageDigest md2 = MessageDigest.getInstance("SHA-512");
			
			byte[] bytes1 = userPwd.getBytes(Charset.forName("UTF-8"));
			byte[] bytes2 = newPwd.getBytes(Charset.forName("UTF-8"));
			
			md1.update(bytes1);
			md2.update(bytes2);
			
			encPwd1 = Base64.getEncoder().encodeToString(md1.digest());
			encPwd2 = Base64.getEncoder().encodeToString(md2.digest());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		Member updatedMember = new MemberService().updatePwd(userNo, encPwd1, encPwd2);
		
		if(updatedMember != null) {
			request.setAttribute("result", "success");
			
			request.getSession().setAttribute("loginUser", updatedMember);
		}
		else {
			request.setAttribute("result", "fail");
		}
		
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/member/pwdModifyForm.jsp");
		view.forward(request, response);
	}
}
