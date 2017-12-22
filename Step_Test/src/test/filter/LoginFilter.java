package test.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*
 *   중간에 클라이언트의 요청을 가로채서 작업을 할 객체 설계
 *   
 *   	- javax.servlet.Filter 인터페이스를 구현해서 만든다.
 */

@WebFilter({"/users/private/*","/member/*"})	//* 는 하위의 모든 요청 의미
//LoginFilter 에 mouseon 해서 add.. 클릭 (추상메소드 override)
public class LoginFilter implements Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
	// 필터가 수행이 될 때 호출되는 메소드
		// req, res, chain 으로 수정
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		// 원래 type(자식 type) 으로 casting
			//현재 부모 type임. 아래아래 줄에서 .getSession() 메소드는 부모 type 에 정의되어 있고
			//자식 type 에서는 정의 안 되어 있어. 그래서 .getSession() 메소드 호출하려면
			//자식 type 으로 casting 해야 함
		HttpServletRequest request= (HttpServletRequest)req;
		HttpServletResponse response= (HttpServletResponse)res;
		// HttpSession 객체의 참조값 얻어오기
		HttpSession session= request.getSession();
		// Context 경로 얻어오기 (원래 가려던 url 주소를 요청 객체를 통해 얻어냄)
		String cPath= request.getContextPath();
		// 원래 요청 uri(url) 정보 얻어오기
		String url= request.getRequestURI();
		// session 에 로그인 정보가 있는지 여부를 확인해서
		if(session.getAttribute("id")==null) {
			// 로그인 페이지의 경로 구성 (로그인 하러 가면서도 원래 가려던 url 주소를 들고 감
				//위에서 얻은 url 이 login_form.jsp 로 get 방식 요청으로 전달됨
				/*
				 * http://item.gmarket.co.kr/Item?goodscode=651636905
				 * 구매하기 누르면  
				 * https://signinssl.gmarket.co.kr/Login/Login?url=https://escrow.gmarket.co.kr/ko/order?orderIdx=13477b1fa&itemNo=651636905&Entrance=PAY&Entrance=PAY
				 * 로그인 페이지로 넘어갔지만, 주소창에 url 로 원래 가려던 페이지 주소(주문하기)를 같이 들고 간다!
				 */
			String path= cPath+ "/users/login_form.jsp?url=" + url;
			// 로그인 페이지로 이동 시킨다. ('특정 경로로 요청을 다시 해' 라고 응답)(.sendRedirect() 도 응답이야) 
			response.sendRedirect(path);
		}else {
			// 원래 하려던 작업 진행 시키기
			chain.doFilter(req, res);
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}
	
}
