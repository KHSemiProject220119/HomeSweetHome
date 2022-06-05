<%@page import="java.util.List"%>
<%@ page import="member.model.dto.Member, java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/signin.css" />	
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<script>
<% 
String saveId = null; 
Cookie[] cookies = request.getCookies();
if(cookies != null){
	for(Cookie cookie : cookies){
		if("saveId".equals(cookie.getName())){
			saveId = cookie.getValue();
		}
	}
}
else {
}

if(loginMember == null) { %>
document.querySelector("#loginFrm").onsubmit = (e) => {
	const memberIdVal = input-user-id.value;
	const passwordVal = input-password.value;
	
    if(!memberIdVal) {
	      alert("아이디를 작성해주세요.");
	      return false;
	    }
    
    if(!passwordVal) {
	      alert("패스워드를 작성해주세요.");
	      return false;
	    }
    //1.아이디검사
    //아이디의 길이는(6~16자 영문, 숫자포함)
    if(!/^[a-zA-Z0-9]{6,16}$/.test(memberIdVal)){
        alert('규칙에 맞게 아이디를 6-16자 사이의 숫자를 포함하는 영문자로 만들어 주세요.');
        return false;
    }

    //2.비밀번호 확인 검사 
    //숫자/문자/특수문자 포함 형태의 8~16자리 이내의 암호 정규식 
    if(!/(?=.*[a-z])(?=.*[0-9])(?=.*[!@#$%&*])[a-z0-9!@#$%&*]{8,16}/i.test(passwordVal)){
        alert('규칙에 맞게 비밀번호를 8-16자 사이의 영문, 숫자, 특수문자를 포함시켜 만들어 주세요.');
        return false;
    }
};	

//온블러 - 아이디
document.querySelector("#input-user-id").onblur = () => {
	if(!id.value){
		document.querySelector("[#id-container]").style.borderColor = "red";
		document.querySelector("[#id-container]").style.animationName = "signUpBlurframes";
		document.querySelector("[#id-container]").style.animationDuration = ".2s";
		document.querySelector("[#id-container]").style.animationIterationCount = "3";
		document.querySelector("[#id-container]").focus();
		return false;
	}	
	document.querySelector("[#id-container]").style.borderColor = "#CACACA";
	return true;
};

// 온블러 - 비밀번호
document.querySelector("#input-user-id").onblur = () => {
	if(!password.value){
		document.querySelector("[#pw-container]").style.borderColor = "red";
		document.querySelector("[#pw-container]").style.animationName = "signUpBlurframes";
		document.querySelector("[#pw-container]").style.animationDuration = ".2s";
		document.querySelector("[#pw-container]").style.animationIterationCount = "3";	
		document.querySelector("[#pw-container]").focus();
		return false;
	}	
	document.querySelector("[#pw-container]").style.borderColor = "#CACACA";
	return true;
};
<% } %>
</script>
				<div>
					<article class="signin">
						<br><br><br>
						<div class="signinTtitle">
							<h3>로그인</h3>
							<br><br><br>
								<form id="loginFrm" name="loginFrm" method="POST" class="form-area" action="<%= request.getContextPath() %>/member/signin">
									<div class="input-wrapper">
										<div class="input-container" id="id-container">
											<input id="input-user-id" type="text" name="input-user-id" placeholder="아이디" required>
										</div>
									</div>
									<div class="input-wrapper">
										<div class="input-container" id="pw-container">
											<input id="input-password" type="password"  name="input-password"  placeholder="비밀번호" required>
										</div>
									</div>
	
									<div class="row">
										<div class="openBtn">
											<button type="button" class="openButton" onclick="openFindId()">아이디 찾기</button>
											<button type="button" class="openButton" onclick="openFindPassword()">비밀번호 찾기</button>
										</div>
									</div>
									<br>
									<div>
										<button id="btn-login">로그인</button>
									</div>
									<div class="social-login">
										<p>SNS계정으로 간편 로그인/회원가입</p>	
										<div class="social-site">
											<div id="kakao"><img id="google-icon" src="<%=request.getContextPath() %>/images/google.png" alt="카카오 아이콘" /></div>
											<div id="google"><img id="kakao-icon" src="<%=request.getContextPath() %>/images/kakaotalk.png" alt="구글 아이콘" /></div>
										</div>								
									</div>
									<hr />
									<div class="non-member-order-view">
										<div id="btn-order-finder-wrapper">
											<button type="button" id="btn-non-member-order" onclick="showOrderFinder()">비회원 주문 조회하기</button>
											<input type="hidden" id="btnValid" value="0" />
										</div>
										<div id="order-finder-wrapper">
											<form name="non-member-order-finderFrm" id="non-member-order-finderFrm" action="<%=request.getContextPath()%>/member/nonMemberOrderFinder">
												<div class="input-wrapper">
													<div class="input-container">
														<input id="input-order-no" type="text" name="orderno" placeholder="주문번호">
													</div>
												</div>
												<div class="input-wrapper">
													<div class="input-container">
														<input id="input-email" name="email" type="email" placeholder="이메일">
													</div>
												</div>
												<button id="btn-non-member-finder">주문조회</button>	
											</form>
										</div>
									</div>
								</form>
						</div>
					</article>
				</div>
<script>
showOrderFinder = () => {
	const changeVal = document.querySelector("#btnValid").value;
	if(changeVal === 0){
		document.querySelector("#order-finder-wrapper").style.display = "block";
		changeVal = 1;
	}
	else{
		document.querySelector("#order-finder-wrapper").style.display = "hidden";
		changeVal = 0;
	}
}
</script>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>