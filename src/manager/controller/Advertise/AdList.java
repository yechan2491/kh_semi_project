package manager.controller.Advertise;

import java.io.IOException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manager.model.service.AdService;

/**
 * Servlet implementation class AdList
 */
@WebServlet("/ad/list")
public class AdList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdList() {
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
		 
		 
		 Map<String, Object> map = new AdService().selectList(page);
		
		 request.setAttribute("pi", map.get("pi"));
	     request.setAttribute("adList", map.get("adList"));
	      
		
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/manager/advertise/adListlView.jsp");
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
