package manager.controller.Report;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manager.model.service.ReportService;
import manager.model.vo.Report;

/**
 * Servlet implementation class ReportDetail
 */
@WebServlet("/report/detail")
public class ReportDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReportDetail() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int rid = Integer.parseInt(request.getParameter("rid"));
		
		 Cookie[] cookies = request.getCookies();
		 String bcount = "";
		 
		 if(cookies != null && cookies.length > 0) {
			 for(Cookie c : cookies) {
				 if(c.getName().equals("bcount")) {
					 bcount = c.getValue();
				 }
			 }
		 }
		 
		 if(bcount.indexOf("|"+rid+"|") == -1) {
			 
			 Cookie newBcount = new Cookie("bcount", bcount + "|"+ rid + "|");
			 response.addCookie(newBcount);
			 
			 int result = new ReportService().increaseRead(rid);
			 
		 }
		
		Report report = new ReportService().reportDetail(rid);
		
		System.out.println(report);
		
		if(report != null) {
			request.setAttribute("report", report);
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/manager/report/reportDetailView.jsp");
			view.forward(request, response);
		}else {
			request.getSession().setAttribute("message", "게시글 조회에 실패했습니다.");
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
