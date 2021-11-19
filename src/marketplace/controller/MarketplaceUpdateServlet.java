package marketplace.controller;

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
import marketplace.model.service.MarketplaceService;
import marketplace.model.vo.Board;
import marketplace.model.vo.Image;
import marketplace.model.vo.UpdateImage;

/**
 * Servlet implementation class MarketplaceUpdateServlet
 */
@WebServlet("/marketplace/update")
public class MarketplaceUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MarketplaceUpdateServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * form 전송 시 enctype을 multipart/form-data로 전송하는 경우 기존 방식
		 * request.getParameter("name")과 같은 방식으로 값을 추출할 수 없음 String btitle =
		 * request.getParameter("title"); String bcontent =
		 * request.getParameter("content"); System.out.println("btitle : "+btitle);
		 * System.out.println("bcontent : "+bcontent);
		 */

		/* enctype이 multipart/form-data로 전송 되었는지 확인하고 아닐 경우 에러페이지 이동 */
		if (!ServletFileUpload.isMultipartContent(request)) {
			request.setAttribute("message", "잘못 된 전송입니다.");
			request.getRequestDispatcher("/WEB-INF/views/common/errorpage.jsp").forward(request, response);
			return;
		}

		/*
		 * com.orelilly.servlet의 약자로 cos.jar 라이브러리 사용하여 파일 업로드 처리
		 * http://www.servlets.com > com.oreilly.servlet > cos-20.08.zip 다운로드 후 압축풀고
		 * cos.jar 파일 lib 경로 아래 추가
		 */

		/* 1. 전송 파일 용량 제한 : 10MB로 제한 */
		int maxSize = 1024 * 1024 * 10;

		/* 2. 웹 서버 컨테이너 경로 추출 */
		String root = request.getSession().getServletContext().getRealPath("/"); // application객체(request.getSession().getServletContext())로
																					// 부터 path 값 받아온다.
		String savePath = root + "resources\\uploadFiles\\";

		/* HttpServletRequest => MultipartRequest 변경 */
		// 인자로 request, 저장할경로, 이파일저장할때 최대 저장할 수 있는 용량제한, utf-8이라고하는 인코딩타입, cos.jar라이브러리에
		// 있는 DefaultFileRenamePolicy() 요소
		MultipartRequest multiRequest
//		= new MultipartRequest(request, savePath, maxSize, "UTF-8", new DefaultFileRenamePolicy());
				= new MultipartRequest(request, savePath, maxSize, "UTF-8", new MyfileRenamePolicy());

		/*
		 * 위의 MultipartRequest 객체 생성과 동시에 업로드한 파일들이 서버의 저장 경로에 저장 됨 즉, 이후의 프로세스에 문제가 있건
		 * 없건 우선 서버에 저장 되므로 추후 프로세스에서 문제가 생길 경우 업로드 된 파일을 삭제할 것
		 * 
		 * 사용자가 올린 파일명을 그대로 저장할 경우 같은 파일명이 있다면 덮어쓰기 처리 되므로 파일명을 수정하여 저장하는 것이 일반적임
		 * 
		 * DefaultFileRenamePolicy는 cos.jar가 제공하는 클래스로 같은 파일명이 있을 경우 파일명 뒤에 카운팅 된 숫자를
		 * 붙여줌 user.png -> user1.png -> user2.png
		 * 
		 * DefaultFileRenamePolicy -> MyFileRenamePolicy (변경)
		 */

		/* DB의 Board와 Image에 데이터 저장 */
		String btitle = multiRequest.getParameter("title");
		int cid = Integer.parseInt(multiRequest.getParameter("category"));
		String[] addressArr = multiRequest.getParameterValues("address");

		String condition = multiRequest.getParameter("condition");
		long price = Long.parseLong(multiRequest.getParameter("price"));
		String bcontent = multiRequest.getParameter("content");
		int bwriter = 1; // 임시 값 나중에 수정해야함
							// ((Member)request.getSession().getAttribute("loginUser")).getUserNo();

		/* 수정으로 인해 삭제할 이미지 파일 이름 */
		String deletecontentImg1=multiRequest.getParameter("deletecontentImg1");
		String deletecontentImg2=multiRequest.getParameter("deletecontentImg2");
		String deletThumnail=multiRequest.getParameter("deletThumnail");
		UpdateImage updateOldImage =new UpdateImage(deletecontentImg1,deletecontentImg2,deletThumnail);
		
//		System.out.println(deletThumnail);
//		System.out.println(deletecontentImg1);
//		System.out.println(deletecontentImg2);
		
		
		String address = "";

		/* 주소값이 입력 된 경우 문자열 합치기 */
		if (addressArr != null && !addressArr[0].equals("")) {
			address = String.join("|", addressArr);
		}

		Board board = new Board();
		board.setBid(Integer.parseInt(multiRequest.getParameter("bid")));
		board.setCid(cid);
		board.setBtitle(btitle);
		board.setLocation(address);
		board.setCondition(condition);
		board.setPrice(price);
		board.setBcontent(bcontent);
		board.setBwriter(bwriter);
		board.setBtype(2); // 사진 게시글

		List<Image> photoList = new ArrayList<>();
		String[] fileNames = { "thumbnail", "contentImg1", "contentImg2" };
		String[] changeNames= multiRequest.getParameterValues("changeName");
		for (int i = 0; i < fileNames.length; i++) {
			/*
			 * contentImg는 optional이므로 파일 첨부가 되지 않았을 수도 있음 해당 태그에 파일이 업로드 되지 않았을 경우 다음
			 * fileName으로 넘어감
			 */
			if (multiRequest.getFilesystemName(fileNames[i]) == null) {
				continue;
			}
			Image image = new Image();
			image.setFilePath("/resources/uploadFiles/");
			/* getOriginalFileName(): 실제 사용자가 업로드한 파일명 */
			image.setOriginName(multiRequest.getOriginalFileName(fileNames[i]));
			/* getFilesystemName() : MyRenamePolicy의 rename 메소드에서 작성한대로 rename 된 파일명 */
			image.setChangeName(multiRequest.getFilesystemName(fileNames[i]));
			/* thumbnail file_level=> 0, contentImg file_level => 1 */
			if (i == 0)
				image.setFileLevel(0);
			else
				image.setFileLevel(1);

			// 원래 저장 된 파일이 있었다면 -> DB에서 update 처리 & 서버에서 기존 파일 delete처리
			if (changeNames.length >= i + 1) { // 원래 가지고 있는 파일에 대한 덮어쓰기 예시로 기존 파일이 2개 있었다면 length는 2일 것이고 2개에 파일은 덮어쓰기
												// 해야한다.
				image.setDeletedName(changeNames[i]);
			}

			/* 파일이 첨부 된 개수 만큼 attachment 객체 photoList에 담김 */
			photoList.add(image);
		}

		/* board에 만들어진 attachment 데이터 설정 */
		board.setImageList(photoList);
//		System.out.println(photoList);
		/* 사진 게시판 작성 비즈니스 로직 처리할 서비스 요청 */
		int result = new MarketplaceService().updateMarketplace(board);

		if (result > 0) {
			/* 수정 성공 시 덮어쓰기 된 사진 삭제 */
			for (Image photo : photoList) {
				if (photo.getDeletedName() != null) {
					File deletedFile = new File(savePath + photo.getDeletedName());
					deletedFile.delete();
				}
			}
			
			/* updateImage 처리*/
			int finalResult=new MarketplaceService().updateImage(board.getBid(),updateOldImage);
			if(finalResult>0) System.out.println("success");
			
			response.sendRedirect(request.getContextPath() + "/marketplace/detail?bid="
					+ Integer.parseInt(multiRequest.getParameter("bid")));
		} else {
			/* 수정 실패 시 수정을 위해 첨부 된 사진 삭제 */
			for (Image photo : photoList) {
				File failedFile = new File(savePath + photo.getChangeName());
				failedFile.delete();
			}
			request.setAttribute("message", "사진 게시글 수정에 실패했습니다.");
			request.getRequestDispatcher("/WEB-INF/views/common/errorpage.jsp").forward(request, response);
		}
	}

}
