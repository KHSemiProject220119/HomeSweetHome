package admin.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.dto.Member;
import member.model.service.MemberService;

/**
 * Servlet implementation class AdminMemberSuspensionServlet
 */
@WebServlet("/admin/memberSuspension")
public class AdminMemberSuspensionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberService();
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String memberId = request.getParameter("memberId");
		 
//		 int result = memberService.memberSuspension(memberId);
		 String msg = memberId + " 회원의 활동정지 처리를 완료하였습니다.";
		 
		 request.getSession().setAttribute("msg", msg);
		 response.sendRedirect(request.getContextPath() + "/admin/memberList");
	}

}
