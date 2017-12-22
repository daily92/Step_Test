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

//이렇게 해 놓으면 request.setCharacterEncoding("utf-8") 필요 없다!

// 모든 요청에 대해서 필터 동작 하도록 설정
//@WebFilter("/*")		//web.xml 에 만들어놔서 @ 없이도 동작!
//LoginFilter 에 mouseon 해서 add.. 클릭 (추상메소드 override)
public class EncodingFilter implements Filter{
	// 인코딩 정보를 저장할 맴버 필드
	//이 안에서 정의된 지역변수는 다른 메소드에서 참조할 수 없으므로 멤버 필드에 저장하면, 다른 메소드에서도 참조 가능!(2)
		String encoding;

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		// 인코딩 설정이 되어 있지 않으면
		if(req.getCharacterEncoding()==null) {
			// 인코딩 설정을 한다
			req.setCharacterEncoding("utf-8");	
		}		
		// 다음 작업 수행하기
		chain.doFilter(req, res);
	}
	
	// 필터가 최초 동작 될 때 호출되는 메소드
	@Override
	public void init(FilterConfig config) throws ServletException {
		// 메소드에 전달되는 config 객체에 초기화 정보가 전달된다
		
		// web.xml 에 정의된 인코딩 정보를 읽어온다 (utf-8)
		//String encoding= config.getInitParameter("encoding");
		//이 안에서 정의된 지역변수는 다른 메소드에서 참조할 수 없으므로 멤버 필드에 저장하면, 다른 메소드에서도 참조 가능!(1)
		encoding= config.getInitParameter("encoding");
	}
	
	
	
}
