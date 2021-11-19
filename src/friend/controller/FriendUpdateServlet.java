package friend.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.oreilly.servlet.MultipartRequest;

import common.MyfileRenamePolicy;
import friend.model.service.FriendService;
import friend.model.vo.Board;
import friend.model.vo.Image;


/**
 * Servlet implementation class FriendUpdateServlet
 */
@WebServlet("/friend/update")
public class FriendUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FriendUpdateServlet() {
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
		/* 파일 경로 지정 */
		int maxSize = 1024*1024*10;
		String root = request.getSession().getServletContext().getRealPath("/");
		String savePath = root + "resources\\uploadFiles\\";
		/* 사진 이름 설정 */
		MultipartRequest multiRequest 
				= new MultipartRequest(request, savePath, maxSize , "UTF-8" 
				, new MyfileRenamePolicy());
		
		/* board 수정 값 */
		 Board board = new Board(); 
		 board.setBid(Integer.parseInt(multiRequest.getParameter("bid")));		// 게시물 번호
		 board.setCid(Integer.parseInt(multiRequest.getParameter("category")));		// 지역이름 
		 board.setBtitle(multiRequest.getParameter("title")); 					// 제목
		 board.setBcontent(multiRequest.getParameter("content")); 				// 본문 내용
		 board.setGender(multiRequest.getParameter("gender"));					// 성별
		 
		 List<Image> FriendPhotoList = new ArrayList<>();
		 String[] fileNames =  { "thumbnail", "contentImg1", "contentImg2", "contentImg3", "contentImg4"};
		 String[] changeNames = multiRequest.getParameterValues("changeName");
		 
		 for(int i = 0; i < fileNames.length; i++) {
			 if(multiRequest.getFilesystemName(fileNames[i]) == null) 
				 continue;

			 Image image = new Image();
			 image.setFilePath("/resources/uploadFiles/");
			 image.setOriginName(multiRequest.getOriginalFileName(fileNames[i]));	// 사용자가 업로드한 파일 명
			 image.setChangeName(multiRequest.getFilesystemName(fileNames[i]));		// 리네임한 파일 명
			 
			 if(i == 0) {
				 image.setFileLevel(0);
			 } else {
				 image.setFileLevel(1);
			 }
			 
			// System.out.println(changeNames);
			if(changeNames.length >= i+1) {  		
				image.setDeletedName(changeNames[i]);  		
															
			}
			 FriendPhotoList.add(image);
			
		 }
		 
		 // System.out.println("photolist : "+FriendPhotoList);
		 board.setFriendPhotoList(FriendPhotoList);
		 
		 
		 // System.out.println(board);
		 

	      int result = new FriendService().updateGallery(board);
	      //System.out.println("result : "+result);
	      
	      
	  	if(result > 0) {
			/* 수정 성공 시 덮어쓰기 된 사진 삭제 */
			for(Image image : FriendPhotoList) {
				if(image.getDeletedName() != null) {
					File deletedFile = new File(savePath + image.getDeletedName());
					deletedFile.delete();
				}
			}
			response.sendRedirect(request.getContextPath() + "/friend/detail?bid="
									+ Integer.parseInt(multiRequest.getParameter("bid")));
		} else {
			/* 수정 실패 시 수정을 위해 첨부 된 사진 삭제 */
			for(Image image : FriendPhotoList) {
				File failedFile = new File(savePath + image.getChangeName());
				failedFile.delete();
			}
			request.setAttribute("message", "사진 게시글 수정에 실패했습니다.");
			request.getRequestDispatcher("/WEB-INF/views/common/errorpage.jsp").forward(request, response);
		}
	    	  
	      }
	}


