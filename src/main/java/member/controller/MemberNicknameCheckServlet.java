package member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.dto.Member;
import member.model.service.MemberService;

/**
 * Servlet implementation class MemberNicknameCheckServlet
 */
@WebServlet("/member/nicknameCheck")
public class MemberNicknameCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 사용자 입력값
		String memberNickname = request.getParameter("memberNickname");
		System.out.println(memberNickname);
		// 2. 업무로직
		Member member = memberService.findByMemberNickname(memberNickname);
		boolean available = (member == null);
		
		// 3. view단 처리
		response.setContentType("text/plain; charset=utf-8");
		PrintWriter out = response.getWriter();
		String noticeText = "";
		// 3.1. available
		if(available == true) {
			noticeText = "<span class='passed-hidden-text'>사용할 수 있는 닉네임입니다.</span>";
			out.append(noticeText);
		}
		else {
			int randomNo = (int) (Math.random() * 100) + 1;
			String RenamedNickname = memberNickname + randomNo;
			System.out.println(RenamedNickname);
			noticeText ="<div class='failed-hidden-notice'>이미 사용중인 별명입니다.</div><div class = 'failed-hidden-wrapper'>";
			out.append(noticeText);
			noticeText ="<p class='failed-hidden-text'>[<span class='renamedNick'>" + RenamedNickname + "</span>]은 어떠신가요?<br>별명은 언제든 변경가능합니다.</p>";
			out.append(noticeText);
			noticeText ="<button type='button' class='btn-renamed' onclick='changeNick()'>사용하기</button>";
			out.append(noticeText + "</div>");
		}
	}

}
