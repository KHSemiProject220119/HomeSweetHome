package member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.HomeSweetHomeUtils;
import member.model.dto.Member;
import member.model.service.MemberService;

/**
 * Servlet implementation class MemberSignInServlet
 */
@WebServlet("/member/signin")
public class MemberSignInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberService();
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 사용자입력값 처리
		String memberId = request.getParameter("input-user-id");
		String password = HomeSweetHomeUtils.encrypt(request.getParameter("input-password"), memberId);
		String saveId = request.getParameter("saveId"); // "on" | null
		System.out.println("memberId@MemberLoginServlet = " + memberId);
		System.out.println("password@MemberLoginServlet = " + password);
		System.out.println("saveId@MemberLoginServlet = " + saveId);
		
		// 3. 업무로직
		Member member = memberService.findByMemberId(memberId);
		System.out.println("member@MemberLoginServlet = " + member);

		HttpSession session = request.getSession();
		
		if(member != null && password.equals(member.getPassword())) {
			// 로그인 성공!
			session.setAttribute("loginMember", member);
			
			// saveId 쿠키 처리
			Cookie cookie = new Cookie("saveId", memberId);
			cookie.setPath(request.getContextPath()); 
			if(saveId != null) {
				cookie.setMaxAge(7 * 24 * 60 * 60); // 초단위 일주일후 폐기
			}
			else {
				cookie.setMaxAge(0); // 0 즉시삭제
			}
			response.addCookie(cookie); 			
		}
		else {
			// 로그인 실패!
			session.setAttribute("msg", "아이디 또는 비밀번호가 일치하지 않습니다.");
		}
		
		// 4. 응답처리 : 리다이렉트
		//String Referer = request.getHeader("Referer"); 
			response.sendRedirect(request.getContextPath()+"/");			

	}

}
