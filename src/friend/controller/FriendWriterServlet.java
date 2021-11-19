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
import member.model.vo.Member;



/**
 * Servlet implementation class FriendWriter
 */
@WebServlet("/friend/writer")
public class FriendWriterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FriendWriterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/friend/friendWriter.jsp").forward(request, response);
  		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//	if(!ServletFileUpload.isMultipartContent(request)) {
		//		request.setAttribute("message", "잘못 된 전송입니다.");
		//		request.getRequestDispatcher("/WEB-INF/views/common/errorpage.jsp").forward(request, response);
		//		return;
		//	}
			
			/* 1. 전송 파일 용량 제한 : 10MB로 제한 */
			int maxSize = 1024*1024*10;
			
			/* 2. 웹 서버 컨테이너 경로 추출 후 파일이 실제 저장 될 경로 지정 */
			String root = request.getSession().getServletContext().getRealPath("/");
			String savePath = root + "resources\\uploadFiles\\";
					
			// System.out.println(root);  
			// --> 경로까지는 잘나옴
			
			
			MultipartRequest multiRequest 
					= new MultipartRequest(request, savePath, maxSize , "UTF-8" 
					, new MyfileRenamePolicy());
			
			
			 int cid = Integer.parseInt(multiRequest.getParameter("category"));		// 지역이름 
			 String btitle = multiRequest.getParameter("title"); 					// 제목
			 String bcontent = multiRequest.getParameter("content"); 				// 본문 내용
			 String bgender = multiRequest.getParameter("gender");					// 성별
			 int bwriter = ((Member)request.getSession().getAttribute("loginUser")).getUserNo(); 
			  
			 Board board = new Board(); 
			 board.setCid(cid); 
			 board.setBtitle(btitle);
			 board.setBcontent(bcontent); 
			 board.setGender(bgender);
			 board.setBtype(3); 	//꿀친 게시판
			 board.setBwriter(bwriter); 
				
			// System.out.println(board); 
			 
			 List<Image> FriendPhotoList = new ArrayList<>();
			 String[] fileNames =  { "thumbnail", "contentImg1", "contentImg2", "contentImg3", "contentImg4"};
			 for(int i = 0; i < fileNames.length; i++) {
				 if(multiRequest.getFilesystemName(fileNames[i]) == null) 
					 continue;
 
				 Image image = new Image();
				 image.setFilePath("/resources/uploadFiles/");
				 image.setOriginName(multiRequest.getOriginalFileName(fileNames[i]));	// 사용자가 업로드한 파일 명
				 image.setChangeName(multiRequest.getFilesystemName(fileNames[i]));		// 리네임한 파일 명
				 if(i == 0)
					 image.setFileLevel(0);
				 else
					 image.setFileLevel(1);
				 
				 FriendPhotoList.add(image);
				
			 }
			 
			 // System.out.println("photolist : "+FriendPhotoList); => 잘나옴
			 board.setFriendPhotoList(FriendPhotoList);
			 
			 
			 // System.out.println(board); => 잘나옴
			 

		      int result = new FriendService().insertGallery(board);
		      //System.out.println("result : "+result); => 잘나옴
		      
			
				
				 if(result > 0) {
			    	  // 성공
			    	  // System.out.println("성공함");
			    	  //request.setAttribute("message", "사진 게시간 등록 성공");
			    	  //request.setAttribute("board", board);
			    	  response.sendRedirect(request.getContextPath() + "/friend/main");
			    	  //response.sendRedirect("/WEB-INF/views/friend/friendMain.jsp");
			    	  //request.getRequestDispatcher("/WEB-INF/views/friend/friendMain.jsp").forward(request, response);
			      } else {
			    	  // 실패 시
			    	  // System.out.println("실패함");
			    	  for(Image photo : FriendPhotoList) {
			    		  File failedFile = new File(savePath + photo.getChangeName());
			    		  failedFile.delete();
			    	  }
			    	  
			    	  request.getRequestDispatcher("/WEB_INF/views/common/errorpage.jsp").forward(request, response);
		      

			      }
	}
}




