package manager.controller.UserSearch;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import manager.model.service.ManagerService;
import manager.model.vo.Member;

/**
 * Servlet implementation class UserSearch
 */
@WebServlet("/userSearch")
public class UserSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserSearch() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/manager/userSearch/userSearchView.jsp");
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String searchCondition = request.getParameter("searchCondition");
	    String searchValue = request.getParameter("searchValue");
	    
	    Member member = new ManagerService().selectMember(searchCondition,searchValue);
	     
	     
	    if(member != null) {
	    	response.setContentType("application/json; charset=utf-8");
	        Gson gson = new GsonBuilder().setDateFormat("yyyy.MM.dd").create();
	        gson.toJson(member, response.getWriter());
	     }
	      
	}

}
