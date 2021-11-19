package marketplace.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import marketplace.model.service.MarketplaceService;
import marketplace.model.vo.Image;



/**
 * Servlet implementation class MarketplaceDeleteServlet
 */
@WebServlet("/marketplace/delete")
public class MarketplaceDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MarketplaceDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int bid = Integer.parseInt(request.getParameter("bid"));
		/* Board, Image, Marketplace 테이블의 bid 일치하는 행 status N - > Y 으로 변경 
		 * 서버에 저장 된 이미지 파일의 정보 알아와서 처리 */
		List<Image> deletedPhotoList = new MarketplaceService().deleteMarketplace(bid);
		
		if(deletedPhotoList!=null) {
			/* DB에서 Y -> N update 수행 완료 되었으므로 서버의 파일 삭제 */
			String root = request.getSession().getServletContext().getRealPath("/"); // application의 진짜 경로, /는 가지고 있는 기본 경로를 가져온다는 의미
			for(Image photo : deletedPhotoList) {
				File deletePhoto = new File(root+photo.getFilePath()+photo.getChangeName());
				deletePhoto.delete();
			}
			
			response.sendRedirect(request.getContextPath()+"/marketplace/list");
		} else {
			request.setAttribute("message", "사진 게시판 게시글 삭제에 실패하였습니다.");
			request.getRequestDispatcher("/WEB-INF/views/common/errorpage.jsp").forward(request, response);
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
