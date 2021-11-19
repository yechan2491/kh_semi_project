package member.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.Math;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.service.MemberService;

/**
 * Servlet implementation class PwdFindServlet
 */
@WebServlet("/pwdfind")
public class PwdFindServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PwdFindServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/member/pwdFind.jsp");
		view.forward(request, response);	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("userId");
		String email = request.getParameter("email");

	    String result = new MemberService().pwdFind(userId, email);
	    
	    String change_pwd = "";
	    
	    PrintWriter out = response.getWriter();

		if(result != null) {
			out.print("success");
			
			for(int i = 0 ; i < 5 ; i++) {
				if((int)(Math.random()*2) == 1)
					change_pwd += (char)((int)(Math.random()*26)+65);
				else
					change_pwd += (char)((int)(Math.random()*26)+97);
			}
			for(int i = 0 ; i < 5 ; i++) {
				change_pwd += (int)(Math.random()*10);
			}
			if((int)(Math.random()*2) == 1)
				change_pwd += '!';
			else
				change_pwd += '@';
			
			System.out.println("change : " + change_pwd);
			
			String encPwd = null;
			
			try {
				MessageDigest md = MessageDigest.getInstance("SHA-512");
				
				byte[] bytes = change_pwd.getBytes(Charset.forName("UTF-8"));
				
				md.update(bytes);
				
				encPwd = Base64.getEncoder().encodeToString(md.digest());
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}

			int result2 = new MemberService().changePwd(encPwd, userId);

			System.out.println(result2);
			
			//semi2team@gmail.com semi123!@
			
			String user = "semi2team@gmail.com"; // 네이버일 경우 네이버 계정, gmail경우 gmail 계정
	        String password = "semi123!@";   // 패스워드
	        
	        System.setProperty("https.protocols", "TLSv1.2");
	             
	        Properties prop = new Properties();
	        prop.put("mail.smtp.host", "smtp.gmail.com"); 
	        prop.put("mail.smtp.port", 465); 
	        prop.put("mail.smtp.auth", "true"); 
	        prop.put("mail.smtp.ssl.enable", "true"); 
	        prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
	        prop.put("mail.smtp.ssl.protocols", "TLSv1.2");
	        
	        Session session = Session.getDefaultInstance(prop, new javax.mail.Authenticator() {
	            protected PasswordAuthentication getPasswordAuthentication() {
	                return new PasswordAuthentication(user, password);
	            }
	        });

	        try {
	            MimeMessage message = new MimeMessage(session);
	            message.setFrom(new InternetAddress(user));

	            //수신자메일주소
	            message.addRecipient(Message.RecipientType.TO, new InternetAddress(email)); 

	            // Subject
	            message.setSubject("자취는 꿀팁 : 임시 비밀번호가 발송되었습니다."); //메일 제목을 입력

	            // Text
	            message.setText("임시 비밀번호 : " + change_pwd);    //메일 내용을 입력

	            // send the message
	            Transport.send(message); ////전송
	            System.out.println("message sent successfully...");
	        } catch (AddressException e) {
	            e.printStackTrace();
	        } catch (MessagingException e) {
	            e.printStackTrace();
	        }
	        
		}
		else {
			out.print("fail");
		}
	}

}
