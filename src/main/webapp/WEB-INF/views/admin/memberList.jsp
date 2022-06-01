<%@page import="java.util.List"%>
<%@ page import="member.model.dto.Member, java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%
	List<Member> list = (List<Member>) request.getAttribute("list");
	String pagebar = (String) request.getAttribute("pagebar");
	
	String searchType = request.getParameter("searchType");
	String searchKeyword = request.getParameter("searchKeyword");

%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/common.css" />

<section id="memberList-container">
	<h2>회원관리</h2>	
	<div id="search-container">
    	<label for="searchType">검색타입 :</label> 
        <select id="searchType">
            <option value="member_id" <%="member_id".equals(searchType)?"selected":""%>>아이디</option>		
			<option value="member_name" <%="member_name".equals(searchType)?"selected":""%>>회원명</option>
        </select>
        <div id="search-memberId" class="search-type">
            <form action="<%=request.getContextPath()%>/admin/memberFinder">
                <input type="hidden" name="searchType" value="member_id"/>
                <input type="text" name="searchKeyword"  size="25" placeholder="검색할 아이디를 입력하세요." value="<%= "member_id".equals(searchType) ? searchKeyword : "" %>"/>
                <button type="submit">검색</button>			
            </form>	
        </div>
        <div id="search-memberName" class="search-type">
            <form action="<%=request.getContextPath()%>/admin/memberFinder">
                <input type="hidden" name="searchType" value="member_name"/>
                <input type="text" name="searchKeyword" size="25" placeholder="검색할 이름을 입력하세요." value="<%= "member_name".equals(searchType) ? searchKeyword : "" %>"/>
                <button type="submit">검색</button>			
            </form>	
        </div>
    </div>
	
	<table id="tbl-member">
		<thead>
			<tr>
				<th>아이디</th>
				<th>이름</th>
				<th>회원권한</th>
				<th>성별</th>
				<th>생년월일</th>
				<th>이메일</th>
				<th>전화번호</th>
				<th>가입일</th>
				<th>활동정지</th>
				<th>강제탈퇴</th>
			</tr>
		</thead>
		<tbody>
<%
		if(list != null && !list.isEmpty()){
			for(Member member : list){
%>			
			<tr>
				<td><%= member.getMemberId() %></td>
				<td><%= member.getMemberName() %></td>
				<td>
					<select class="member-role" data-member-id="<%= member.getMemberId() %>">
						<option value="<%= MemberRole.A %>" <%= member.getMemberRole() == MemberRole.A ? "selected" : "" %>>관리자</option>
						<option value="<%= MemberRole.U %>" <%= member.getMemberRole() == MemberRole.U ? "selected" : "" %>>일반회원</option>
					</select>
				</td>
				<td><%= member.getGender() != null ? member.getGender() : "" %></td>
				<td><%= member.getBirthday() != null ? member.getBirthday() : "" %></td>
				<td><%= member.getEmail() != null ? member.getEmail() : "" %></td>
				<td><%= member.getPhone() %></td>
				<td><%= member.getEnrollDate() %></td>
				<td><button type="button" class="btn-suspension">활동정지</button></td>
				<td><button type="button" class="btn-forced-withdrawal">강제탈퇴</button> </td>
			</tr>			
<%
			}
		}
		else {
%>			
			<tr>
				<td colspan="10">조회된 회원이 없습니다.</td>
			</tr>
<%
		}
%>		
		</tbody>
	</table>
	<div id="pagebar">
		<%= pagebar %>
	</div>
</section>
<form 
	action="<%= request.getContextPath() %>/admin/memberRoleUpdate" 
	name="updateMemberRoleFrm"
	method="POST">
	<input type="hidden" name="memberId" />
	<input type="hidden" name="memberRole" />
</form>
<form 
	action="<%= request.getContextPath() %>/admin/memberForcedWithdrawal" 
	name="withdrawMemberFrm"
	method="POST">
	<input type="hidden" name="memberId" />
</form>
<form 
	action="<%= request.getContextPath() %>/admin/memberSuspension" 
	name="suspendMemberRoleFrm"
	method="POST">
	<input type="hidden" name="memberId" />
</form>

<script>

btn-suspension.addEventListener('onclick', (e) => {
	const mebmerId = e.target.dataset.memberId;
	if(confirm(`정말로 [\${memberId}] 회원의 활동을 정지하시겠습니까?`)){
		const frm = document.suspendMemberRoleFrm;
		frm.memberId.value = memberId;
		frm.submit();
	}
});

btn-forced-withdrawal.addEventListener('onclick', (e) => {
	const mebmerId = e.target.dataset.memberId;
	if(confirm(`정말로 [\${memberId}] 회원의 계정을 강제 탈퇴시키시겠습니까?`)){
		const frm = document.withdrawMemberFrm;
		frm.memberId.value = memberId;
		frm.submit();
	}
});	

searchType.addEventListener('change', (e) => {
	const {value} = e.target;  
	console.log(value);
	
	document.querySelectorAll(".search-type").forEach((div) => {
		div.style.display = "none";
	});
	let id = "";
	switch(value){
		case "member_id": id = "search-memberId"; break; 
		case "member_name": id = "search-memberName"; break; 
	}
	document.querySelector(`#\${id}`).style.display = "inline-block";
});


document.querySelectorAll(".member-role").forEach((select) => {
	select.addEventListener('change', (e) => {
		const memberId = e.target.dataset.memberId;
		const memberRole = e.target.value;

		if(confirm(`[\${memberId}]의 권한을 [\${memberRole}]로 변경하시겠습니까?`)){
			const frm = document.updateMemberRoleFrm;
			frm.memberId.value = memberId;
			frm.memberRole.value = memberRole;
			frm.submit();
		}
		else {
			e.target.querySelector("[selected]").selected = true;
		}
		
	});
});


</script>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>