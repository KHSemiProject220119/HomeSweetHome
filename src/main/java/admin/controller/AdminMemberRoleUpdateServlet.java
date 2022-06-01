package admin.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.dto.Member;
import member.model.dto.MemberRole;
import member.model.service.MemberService;

/**
 * Servlet implementation class AdminMemberRoleUpdateServlet
 */
@WebServlet("/admin/memberRoleUpdate")
public class AdminMemberRoleUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberService();
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// 1. 사용자 입력값 처리
			String memberId = request.getParameter("memberId");
			String _memberRole = request.getParameter("memberRole");
			MemberRole memberRole = MemberRole.valueOf(_memberRole);
			
			Member member = new Member();
			member.setMemberId(memberId);
			member.setMemberRole(memberRole);
			
			// 2. 업무로직
			int result = memberService.updateMemberRole(member);
			String msg = "권한정보를 성공적으로 수정했습니다.";
			
			// 3. redirect
			request.getSession().setAttribute("msg", msg);
			response.sendRedirect(request.getContextPath() + "/admin/memberList");
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
}