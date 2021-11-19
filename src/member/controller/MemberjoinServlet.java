package member.controller;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.oreilly.servlet.MultipartRequest;

import common.MyfileRenamePolicy;
import common.MyfileRenamePolicyProfile;
import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class MemberjoinServlet
 */
@WebServlet(name = "MemberJoinServlet", urlPatterns = "/memberjoin")
public class MemberjoinServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberjoinServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/member/memberJoinForm.jsp");
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		if(!ServletFileUpload.isMultipartContent(request)) {
			request.setAttribute("message", "잘못 된 전송입니다.");
			request.getRequestDispatcher("/WEB-INF/views/common/errorpage.jsp").forward(request, response);
			return;
		}

		int maxSize = 1024*1024*10;
		
		String root = request.getSession().getServletContext().getRealPath("/");
		String savePath = root + "resources\\uploadFiles\\";
		
		MultipartRequest multiRequest = new MultipartRequest(request, savePath, maxSize, "UTF-8", new MyfileRenamePolicyProfile());

		String userId = multiRequest.getParameter("userId");
		String userPwd = multiRequest.getParameter("userPwd");
		String userName = multiRequest.getParameter("userName");
		String nickName = multiRequest.getParameter("nickName");
		String phone = multiRequest.getParameter("phone");
		String email = multiRequest.getParameter("email");
		String[] addressArr = multiRequest.getParameterValues("address");
		String address = "";
		String year = multiRequest.getParameter("year");
		String month = multiRequest.getParameter("month");
		String day = multiRequest.getParameter("day");
		String birth = year + "-" + month + "-" + day;
		/*
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date birth = null;
		try {
			birth = new Date(sdf.parse(bir).getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		*/
		if(addressArr != null && !addressArr[0].contentEquals(""))
			address = String.join("|", addressArr);
		String fileNames = "profile";
		String rename = multiRequest.getFilesystemName(fileNames);
		String profilePath = "/resources/uploadFiles/" + rename;
		
		String encPwd = null;
		
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-512");
			
			byte[] bytes = userPwd.getBytes(Charset.forName("UTF-8"));
			
			md.update(bytes);
			
			encPwd = Base64.getEncoder().encodeToString(md.digest());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
//		System.out.println(userId + userPwd + userName + nickName +  birth  + phone + email + address + profilePath);
		Member member = new Member(userId, encPwd, userName, birth, nickName, phone, address, email, profilePath);
		
		int result = new MemberService().insertMember(member);
		
		if(result > 0) {
			request.getSession().setAttribute("message", "회원 가입이 완료 되었습니다. 로그인 해주세요.");
			response.sendRedirect(request.getContextPath());
		} 
		else {
			File failedFile = new File(savePath + rename);
			failedFile.delete();
			request.setAttribute("message", "회원 가입에 실패하였습니다");
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/errorpage.jsp");
			view.forward(request, response);
		}
		
	}

}
