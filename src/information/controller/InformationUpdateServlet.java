package information.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.oreilly.servlet.MultipartRequest;

import common.MyfileRenamePolicy;
import information.model.service.InformationService;
import information.model.vo.Board;
import information.model.vo.Image;



/**
 * Servlet implementation class InformationInsertServlet
 */
@WebServlet("/info/update")
public class InformationUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InformationUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//request.getRequestDispatcher("/WEB-INF/views/information/informationUpdate.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (!ServletFileUpload.isMultipartContent(request)) {
			request.setAttribute("message", "잘못 된 전송입니다.");
			request.getRequestDispatcher("/WEB-INF/views/common/errorpage.jsp").forward(request, response);
			return;
		}
		
		int maxSize = 1024*1024*10;
		String root = request.getSession().getServletContext().getRealPath("/");
		String savePath = root + "resources\\uploadFiles\\";
		/* input type file 에 첨부된 파일 서버에 저장 됨*/
		MultipartRequest multiRequest 
			= new MultipartRequest(request, savePath, maxSize, "UTF-8", new MyfileRenamePolicy());  
		
		/* Board 테이블 수정 값 설정 */
		Board board = new Board();
		board.setBid(Integer.parseInt(multiRequest.getParameter("bid")));
		board.setCid(Integer.parseInt(multiRequest.getParameter("category")));
		board.setBtitle(multiRequest.getParameter("title"));
		board.setBcontent(multiRequest.getParameter("content"));
		
		/* Attachment 테이블 수정 값 설정 */
		List<Image> photoList = new ArrayList<>();		
		String[] fileNames = {"thumbnail"};
		String[] changeNames = multiRequest.getParameterValues("changeName");
		
		for(int i = 0; i < fileNames.length; i++) {
			// 넘어온 파일이 없는 경우 수정 사항이 없으므로 continue로 반복문 다음으로 진행
			if(multiRequest.getFilesystemName(fileNames[i]) == null)
				continue;
			
			// 수정을 위해 첨부 된 파일이 있는 경우
			Image photo = new Image();
			photo.setFilePath("/resources/uploadFiles/");
			photo.setOriginName(multiRequest.getOriginalFileName(fileNames[i]));
			photo.setChangeName(multiRequest.getFilesystemName(fileNames[i]));
			
			if( i == 0) {
				photo.setFileLevel(0);  // (0) -> 썸네일이라는 뜻
			}/*else {
				photo.setFileLevel(1);  // (1) -> 그외 첨부사진이라는 뜻
			}*/
			
			// 원래 저장 된 파일이 있었다면 -> DB에서 update 처리 & 서버에서 기존 파일 delete 처리
			if(changeNames.length >= i+1) {
				photo.setDeletedName(changeNames[i]);
			}
			
			photoList.add(photo);	  // 넘어온 파일 photoList		
		}
		
		board.setPhotoList(photoList);
		
		int result = new InformationService().updateGallery(board);
		
		if(result > 0) {
			/* 수정 성공 시 덮어쓰기 된 사진 삭제 */
			for(Image photo : photoList) {
				if(photo.getDeletedName() != null) {
					File deletedFile = new File(savePath + photo.getDeletedName());
					deletedFile.delete();
				}
			}
			
			response.sendRedirect(request.getContextPath() + "/info/detail?bid=" 
								+ Integer.parseInt(multiRequest.getParameter("bid")));
		}else {
			/* 수정 실패 시 수정을 위해 첨부 된 사진 삭제 */
			for(Image photo : photoList) {
				File failedFile = new File(savePath + photo.getChangeName());
				failedFile.delete();
			}
			
			request.setAttribute("message", "사진 게시글 수정에 실패하였습니다.");
			request.getRequestDispatcher("/WEB-INF/views/common/errorpage.jsp").forward(request, response);			
		}
	}

	
}
