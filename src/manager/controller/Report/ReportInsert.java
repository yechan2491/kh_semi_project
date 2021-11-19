package manager.controller.Report;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manager.model.service.ReportService;
import manager.model.vo.Report;
import member.model.vo.Member;

/**
 * Servlet implementation class ReportInsert
 */
@WebServlet("/report/insert")
public class ReportInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReportInsert() {
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
		request.setCharacterEncoding("utf-8");
		int bid = Integer.parseInt(request.getParameter("bid"));
		int writer = Integer.parseInt(request.getParameter("bwriter"));
		String title = request.getParameter("select_reason");
		String content = request.getParameter("detail_reason");
		int rwriter = ((Member)request.getSession().getAttribute("loginUser")).getUserNo();
		
		Report report = new Report();

		report.setrTitle("[게시물] "+title);
		report.setrContent(content);
		report.setWriter(writer);
		report.setRwriter(rwriter);
	
			
		int result = new ReportService().boardReport(bid,report);
		
		if(result>0) {
			request.getSession().setAttribute("message", "게시물 신고가 완료되었습니다.");
			response.sendRedirect(request.getHeader("referer"));
		}else {
			request.getSession().setAttribute("message", "게시물 신고가 실패되었습니다.");
		}
		
		
		
	}

}
