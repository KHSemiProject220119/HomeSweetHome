package common.filter;

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

import member.model.dto.Member;

/**
 * Servlet Filter implementation class LoginFilter
 */
@WebFilter({ 
		/*
		 * "/member/memberView", "/member/memberUpdate", "/member/memberDelete",
		 * "/member/passwordUpdate", "/board/boardEnroll", "/board/boardUpdate",
		 * "/board/boardDelete",
		 */
})
public class LoginFilter implements Filter {

    /**
     * Default constructor. 
     */
    public LoginFilter() {
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
		HttpServletRequest httpReq = (HttpServletRequest) request; 
		HttpServletResponse httpRes = (HttpServletResponse) response; 
		
		// 로그인여부 검사
		HttpSession session = httpReq.getSession();
		Member loginMember = (Member) session.getAttribute("loginMember");
		
		if(loginMember == null) {
			session.setAttribute("msg", "로그인 후 사용가능합니다.");
			httpRes.sendRedirect(httpReq.getContextPath() + "/");
			return; // 조기리턴
		}
		
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
