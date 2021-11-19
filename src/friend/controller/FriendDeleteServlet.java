package friend.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import friend.model.service.FriendService;
import friend.model.vo.Image;

/**
 * Servlet implementation class FriendDeleteServlet
 */
@WebServlet("/friend/delete")
public class FriendDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FriendDeleteServlet() {
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
		
		List<Image> deletedPhotoList = new FriendService().deleteGallery(bid);
		
		if(deletedPhotoList != null) {
			/* DB에서 Y-> N update 수행 완료 되었으므로 서버의 파일 삭제 */
			String root =  request.getSession().getServletContext().getRealPath("/");
			for(Image photo : deletedPhotoList) {
				File deletedPhoto = new File(root + photo.getFilePath() + photo.getChangeName());
				deletedPhoto.delete();
			}
			response.sendRedirect(request.getContextPath() + "/friend/main");
		} else {
			request.setAttribute("message", "사진 게시판 게시글 삭제에 실패했습니다.");
			request.getRequestDispatcher("/WEB-INF/views/common/errorpage.jsp").forward(request, response);
		}
	}

}
