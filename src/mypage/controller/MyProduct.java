package mypage.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.vo.Member;
import mypage.model.service.MypageService;

/**
 * Servlet implementation class MyProduct
 */
@WebServlet("/myproduct")
public class MyProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyProduct() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int userNo = ((Member)request.getSession().getAttribute("loginUser")).getUserNo();
		
		int page = 1;
		
		 if(request.getParameter("page") != null) {   
	         page = Integer.parseInt(request.getParameter("page"));
	      }
		 
		 Map<String, Object> map = new MypageService().selectProductList(page,userNo);
		
		 request.setAttribute("pi", map.get("pi"));
	     request.setAttribute("boardList", map.get("boardList"));
	     
	     System.out.println(map.get("pi"));
	     
	     System.out.println(map.get("boardList"));

		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/mypage/myproductView.jsp");
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
