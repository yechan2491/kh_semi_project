package filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import member.model.vo.Member;

/**
 * Servlet Filter implementation class LoginCheckFilter
 */
@WebFilter("/*")
public class LoginCheckFilter implements Filter {
   private List<String> permitList;
   private List<String> resourceList;
    /**
     * Default constructor. 
     */
    public LoginCheckFilter() {
        // TODO Auto-generated constructor stub
    }

   /**
    * @see Filter#destroy()
    */
   public void destroy() {
      // TODO Auto-generated method stub
   }

   /**
    * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
    */
   public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
      HttpServletRequest req = (HttpServletRequest)request;
      
      String uri = req.getRequestURI();
//      System.out.println(uri);
      
      // 로그인 없이 요청이 허가 된 리스트에 현재 요청이 포함되지 않았을 때
      if(!permitList.contains(uri)) {
         boolean isResourceFile = false;
         // 요청안에 /resources/ 라는 문자열을 포함하고 있는지 확인
         for(String str : resourceList) {
            if(uri.contains(str)) {
               isResourceFile = true;
               break;
            }
         }
         
         // 허가 리스트에도 없는 요청이면서 리소스 파일도 아닌 경우
         if(!isResourceFile) {
            Member loginUser = (Member)req.getSession().getAttribute("loginUser");
            // 로그인 상태가 아니면
            if(loginUser == null) {
               req.setAttribute("message", "올바르지 않은 요청입니다.");
               req.getRequestDispatcher("/WEB-INF/views/member/loginpage.jsp").forward(request, response);
               return;
            }
         }
      }
      
      // pass the request along the filter chain
      chain.doFilter(request, response);
   }

   /**
    * @see Filter#init(FilterConfig)
    */
   public void init(FilterConfig fConfig) throws ServletException {
      // TODO Auto-generated method stub
      // 로그인 없이도 접근 가능한 주소를 적으시면 됩니다...
	   
	  permitList = new ArrayList<String>();
      permitList.add("/kh_semi/");
      permitList.add("/kh_semi/login");
      permitList.add("/kh_semi/idfind");
      permitList.add("/kh_semi/pwdfind");
      permitList.add("/kh_semi/findfail");
      permitList.add("/kh_semi/findresult");
      permitList.add("/kh_semi/sendemail");
	  permitList.add("/kh_semi/memberjoin");
	  permitList.add("/kh_semi/idCheck");
	  permitList.add("/kh_semi/nickCheck");
      permitList.add("/kh_semi/marketplace/list");
      permitList.add("/kh_semi/marketplace/detail");
      permitList.add("/kh_semi/friend/main"); 			// 꿀친 메인
      permitList.add("/kh_semi/friend/detail");			// 꿀친 상세 페이지
      permitList.add("/kh_semi/friend/deleteReply");	
      permitList.add("/kh_semi/total/list");
      permitList.add("/kh_semi/info/detail");
      permitList.add("/kh_semi/info/list");
      permitList.add("/kh_semi/ad/insert");
      
     
      resourceList = new ArrayList<String>();
      resourceList.add("/resources/");
   }

}
