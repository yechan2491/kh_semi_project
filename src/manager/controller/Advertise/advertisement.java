package manager.controller.Advertise;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manager.model.service.AdService;
import manager.model.vo.Advertise;

/**
 * Servlet implementation class advertisement
 */
@WebServlet("/ad/insert")
public class advertisement extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public advertisement() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/manager/advertise/advertisement.jsp");
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String company = request.getParameter("companyname");
		String writer = request.getParameter("writer");
		String phone =request.getParameter("phonenumber");
		
		
		Advertise ad = new Advertise();
		
		ad.setAdTitle(title);
		ad.setAdContent(content);
		ad.setAdCompany(company);
		ad.setAdPhone(phone);
		ad.setAdWriter(writer);
		
		
		
		int result = new AdService().insertAd(ad);
		
		
		if(result>0) {
			request.getSession().setAttribute("message", "광고문의 등록이 완료되었습니다.");
			response.sendRedirect(request.getContextPath());
		}else {
			request.getSession().setAttribute("message", "광고문의 등록이 실패하였습니다.");
			response.sendRedirect(request.getContextPath());
		}

		
	}

}
