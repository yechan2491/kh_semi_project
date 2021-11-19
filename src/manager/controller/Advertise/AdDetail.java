package manager.controller.Advertise;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manager.model.service.AdService;
import manager.model.vo.Advertise;

/**
 * Servlet implementation class AdDetail
 */
@WebServlet("/ad/detail")
public class AdDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdDetail() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int adNo = Integer.parseInt(request.getParameter("adNo"));
		
		AdService adService = new AdService();
		 
		 Cookie[] cookies = request.getCookies();
		 String bcount = "";
		 
		 if(cookies != null && cookies.length > 0) {
			 for(Cookie c : cookies) {
				 if(c.getName().equals("bcount")) {
					 bcount = c.getValue();
				 }
			 }
		 }
		 
		 if(bcount.indexOf("|"+adNo+"|") == -1) {
			 
			 Cookie newBcount = new Cookie("bcount", bcount + "|"+ adNo + "|");
			 response.addCookie(newBcount);
			 
			 int result = adService.increaseRead(adNo);
			 
		 }
		 
		 Advertise ad = adService.selectAd(adNo);

		 if(ad != null) {
			 request.setAttribute("ad", ad);
			 RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/manager/advertise/adDetailView.jsp");
			 view.forward(request, response);
			 
		 }else {
			 request.setAttribute("message", "광고문의 상세 조회에 실패하였습니다.");
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
