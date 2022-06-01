package admin.controller;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.service.MemberService;

/**
 * Servlet implementation class AdminMemberForcedWithdrawalServlet
 */
@WebServlet("/admin/memberForcedWithdrawal")
public class AdminMemberForcedWithdrawalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberService();

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//1. 사용자 입력값 처리
			String memberId = request.getParameter("memberId");
			
			//2. 서비스로직호출
//			int result = memberService.deleteMember(memberId);
			
			String msg = memberId + " 회원의 강제 탈퇴 처리를 완료하였습니다.";
			 
			request.getSession().setAttribute("msg", msg);
			response.sendRedirect(request.getContextPath() + "/admin/memberList");
			
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
	}

}
