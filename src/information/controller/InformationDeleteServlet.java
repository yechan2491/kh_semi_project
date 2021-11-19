package information.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import information.model.service.InformationService;
import information.model.vo.Image;

/**
 * Servlet implementation class GalleryDeleteServlet
 */
@WebServlet("/info/delete")
public class InformationDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InformationDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int bid = Integer.parseInt(request.getParameter("bid"));
		
		/* Board, Attachment 테이블의 bid 일치하는 행 status Y -> N 으로 변경 */
		/* 서버에 저장 된 이미지 파일의 정보 알아와서 삭제 처리 */
		List<Image> deletedPhotoList = new InformationService().deleteGallery(bid);
		
		
		if(deletedPhotoList != null) {
			/* DB에서 Y -> N update 수행 완료 되었으므로 서버의 파일 삭제 */
			String root = request.getSession().getServletContext().getRealPath("/");
			//request.getSession().getServletContext() 어플리케이션 객체 
			// getRealPath("/") => getServletContext() 경로를 가져온다?
			for(Image photo : deletedPhotoList) {
				File deletedPhoto = new File(root + photo.getFilePath() + photo.getChangeName());
				deletedPhoto.delete();
			}
			response.sendRedirect(request.getContextPath() + "/info/list");
			
			
		}else {
			request.setAttribute("message", "사진 게시판 게시글 삭제에 실패했습니다.");
			request.getRequestDispatcher("/WEB-INF/views/common/errorpage.jsp").forward(request, response);
		}
		
		
		
		
		
		
		
	}

	
}
