package manager.controller.Report;

import java.io.IOException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manager.model.service.ReportService;

/**
 * Servlet implementation class ReportList
 */
@WebServlet("/report/list")
public class ReportList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReportList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int page = 1;
		
		 if(request.getParameter("page") != null) {   // board/list?page=5 넘어오면 5로 요청
	         page = Integer.parseInt(request.getParameter("page"));
	      }
		 
		 Map<String, Object> map = new ReportService().selectList(page);
		
		 request.setAttribute("pi", map.get("pi"));
	     request.setAttribute("reportList", map.get("reportList"));
		
		
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/manager/report/reportListView.jsp");
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
