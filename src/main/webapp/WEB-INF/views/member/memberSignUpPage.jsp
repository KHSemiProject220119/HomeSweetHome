<%@page import="java.util.List"%>
<%@ page import="member.model.dto.Member, java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/signup.css" />
<script	src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="<%= request.getContextPath() %>/js/memberSignUp.js"></script>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<form class="form" id="signupFrm" name="signUpFrm" method="POST">
	<p class="signupTitle">회원정보 입력</p>
	<div class="signupInfoNecessary">
		<p class="input_id">아이디<span class="neccesary-star">*</span>
		</p>
		<div class="row_area">
			<div class="input_wrapper_check">
				<div class="input_container" name="id-cont">
					<input type="text" id="id" name="id" placeholder="아이디 입력(6~16자 영문)" tabindex="1" required >
					<input type="hidden" id="idValid" value="0" />
				</div>
			</div>
			<button type="button" onclick="openCheckId();" class="btn-id-check">중복확인</button>
		</div>
		<p class="input_password">비밀번호<span class="neccesary-star">*</span>
		</p>
		<div>
			<span class="notice-pw-text">숫자/문자/특수문자 각1개 이상 포함하여 8~16자리를 입력해주세요.</span>
		</div>
		<div class="input_wrapper">
			<div class="input_container" name="pw-cont">
				<input type="password" id="password" name="password" placeholder="비밀번호 입력" tabindex="2" required>
			</div>
		</div>
		<div class="input_wrapper">
			<div class="input_container" name="pwConf-cont">
				<input type="password" id="password_confirm" name="password_confirm" placeholder="비밀번호 재입력" tabindex="3" required>
			</div>
		</div>
		<p class="input_name">이름<span class="neccesary-star">*</span>
		</p>
		<div class="input_wrapper">
			<div class="input_container" name="name-cont">
				<input type="text" id="user_name" name="user_name" placeholder="이름 입력" tabindex="4" required>
			</div>
		</div>
		<p class="input_nickname">닉네임<span class="neccesary-star">*</span>
		</p>
		<div class="input_wrapper">
			<div>
				<span class="notice-nick-text">다른 회원과 겹치지 않는 별명을 입력해주세요.(2~15자)</span>
			</div>
			<div class="input_container" name="nickname-cont">
				<input type="text" id="user_nickname" name="user_nickname" placeholder="별명 (2~15자)" tabindex="5" required>
			</div>
			<div class="input_container" id="nickname-hidden-msg">
				
			</div>
		</div>
		<p class="input_phone">휴대폰 번호<span class="neccesary-star">*</span>
		</p>
		<div class="input_wrapper">
			<div class="input_container" name="phone-cont">
				<input type="tel" id="phone" name="phone" placeholder="휴대폰 번호 입력(- 없이)" tabindex="6" required>
			</div>
		</div>
		<p class="input_email" name="email-cont">
			이메일 <span class="neccesary-star">*</span>
		</p>
		<div class="row_area">
			<div class="input_wrapper_check">
				<div class="input_container">
					<input type="text" id="email" name="email" placeholder="이메일 입력" tabindex="7" required >
					<input type="hidden" id="emailValid" value="0" />
				</div>
			</div>
			<button onclick="openEmailCert()" type="button" class="btn_blue_id-email">본인 인증</button>
		</div>
				<p class="input_id">배송지 주소</p>
		<div class="row_area">
			<div class="address">
				<div class="input_container_postCode">
					<input type="text" id="postcode" name="postcode" placeholder="우편번호" readonly>
				</div>
			</div>
			<button onclick="searchAddress();" type="button" class="btn_blue">주소 찾기</button>
		</div>
		<div class="row_area">
			<div class="address">
				<div class="input_container">
					<input type="text" id="address" name="address" placeholder="기본주소" readonly>
				</div>
				<div class="input_container">
					<input type="text" id="address_detail" name="address_detail"placeholder="상세주소">
				</div>
				<div class="input_container">
					<input type="text" id="extraAddress" name="extraAddress" placeholder="참고사항" readonly>
				</div>
			</div>
		</div>
	</div>

	<div class="signupInfoOption">
		<div class="signupInfoOptionTitle">
			<p class="signupTitle">선택사항 입력</p>
			<hr>
		</div>

		<p class="input_birth">생년월일</p>
		<div class="row_area_bir">
			<div class="input_container_birth">
				<div id="birth_year">
					<input type="number" id="year" name="year" min="1900" max="" placeholder="년">
				</div>
				<span class="birth-text">년</span>
			</div>
			<div class="input_container_birth">
				<div id="birth_month">
					<select id="month" name="month">
						<option>월</option>
						<option value="01">1</option>
						<option value="02">2</option>
						<option value="03">3</option>
						<option value="04">4</option>
						<option value="05">5</option>
						<option value="06">6</option>
						<option value="07">7</option>
						<option value="08">8</option>
						<option value="09">9</option>
						<option value="10">10</option>
						<option value="11">11</option>
						<option value="12">12</option>
					</select>
				</div>
				<span class="birth-text">월</span>
			</div>
			<div class="input_container_birth">
				<div id="birth_day">
					<input type="number" id="day" name="day" min = "1" max="31" placeholder="일">
				</div>
				<span class="birth-text">일</span>
			</div>
		</div>
		<p class="input_gender">성별</p>
		<div class="input_wrapper-gender">
			<div class="input_container-gender">
				<input type="radio" name="gender" value="M" id="gender-male"> 
				<span>
					<label for="gender-male">남</label>
				</span> 
				<input type="radio" name="gender" value="F" id="gender-female"> 
				<span id="gender-f-text">
					<label for="gender-female">여</label>
				</span>
			</div>
		</div>
	</div>
	<br />
	<button type="submit" class="btn-signup" onclick="enrollMemberInfo();">회원가입</button>
</form>
<form name="idCheckFrm"
	action="<%= request.getContextPath() %>/member/memberIdCheck">
	<input type="hidden" name="memberId" />
</form>
<form name="emailCertFrm"
	action="<%= request.getContextPath() %>/member/memberEmailCert">
	<input type="hidden" name="memberEmail" />
	<input type="hidden" name="memberName" />
</form>
<script>

window.onload = () =>{
	const today = new Date();
	const year = today.getFullYear();
	document.querySelector("#year").max = year;
}

//아이디 중복검사
const openCheckId = () => {
	const useridVal = document.querySelector("#id").value;
	if(!useridVal) {
		alert("아이디가 입력되지 않았습니다.");
	      return false;
	}
    if(!/^[a-zA-Z0-9]{6,16}$/.test(useridVal)){
        alert('규칙에 맞게 아이디를 6-16자 사이의 숫자를 포함하는 영문자로 만들어 주세요.');
        return false;
    }
	const title = "MemberIdCheckPopup";
	const spec = "width=510px, height=330px";
	const popup = open("", title, spec);
	
	const frm = document.idCheckFrm;
	frm.target = title; // 해당팝업에서 폼을 제출!
	frm.memberId.value = id.value;
	frm.submit();
};

//이메일 인증
const openEmailCert = () => {
	const userEmailVal = document.querySelector("#email").value;
	if(!userEmailVal) {
		alert("이메일이 입력되지 않았습니다.");
	      return false;
	}
    if(!/^[a-z0-9]{4,12}[@].+[.][a-zA-Z]{2,3}$/i.test(userEmailVal)){
        alert('이메일은 @가 포함되어야 하며, 아이디의 길이는 4~12자리이어야 합니다.');
        return false;
    }
	const title = "EmailCertification";
	const spec = "width=520px, height=500px";
	const popup = open("", title, spec);
	
	const frm = document.emailCertFrm;
	frm.target = title; // 해당팝업에서 폼을 제출!
	frm.memberEmail.value = email.value;
	frm.memberName.value = user_name.value;
	frm.submit(); 
	
};

//온블러 - 아이디
id.onblur = () => {
	if(!id.value){
		document.querySelector(".input_id").style.color= "red";
		document.querySelector("[name=id-cont]").style.color = "red";
		document.querySelector("[name=id-cont]").style.borderColor = "red";
		document.querySelector("[name=id-cont]").style.animationName = "signUpBlurframes";
		document.querySelector("[name=id-cont]").style.animationDuration = ".2s";
		document.querySelector("[name=id-cont]").style.animationIterationCount = "3";
		document.querySelector("[name=id-cont]").focus();
		return false;
	}	
	document.querySelector(".input_id").style.color= "black";
	document.querySelector("[name=id-cont]").style.color = "#CACACA";
	document.querySelector("[name=id-cont]").style.borderColor = "#CACACA";
	return true;
};

// 온블러 - 비밀번호, 비밀번호 확인
password.onblur = () => {
	if(!password.value){
		document.querySelector(".input_password").style.color= "red";
		document.querySelector(".notice-pw-text").style.color= "red";
		document.querySelector("[name=pw-cont]").style.borderColor = "red";
		document.querySelector("[name=pw-cont]").style.animationName = "signUpBlurframes";
		document.querySelector("[name=pw-cont]").style.animationDuration = ".2s";
		document.querySelector("[name=pw-cont]").style.animationIterationCount = "3";	
		document.querySelector("[name=pw-cont]").focus();
       
		return false;
	}	
	document.querySelector(".input_password").style.color= "black";
	document.querySelector(".notice-pw-text").style.color= "black";
	document.querySelector("[name=pw-cont]").style.borderColor = "#CACACA";
	return true;
};


password_confirm.onblur = () => {
	if(password.value !== password_confirm.value){
 		document.querySelector(".input_password").style.color= "red";
		document.querySelector(".notice-pw-text").style.color= "red";
		document.querySelector("[name=pwConf-cont]").style.borderColor = "red";
		document.querySelector("[name=pwConf-cont]").style.animationName = "signUpBlurframes";
		document.querySelector("[name=pwConf-cont]").style.animationDuration = ".2s";
		document.querySelector("[name=pwConf-cont]").style.animationIterationCount = "3";
		document.querySelector("[name=pw-cont]").focus();	
      
		return false;
	}	
	document.querySelector(".input_password").style.color= "black";
	document.querySelector("[name=pwConf-cont]").style.borderColor = "#CACACA";
	return true;
};

//이름 온블러
user_name.onblur = () => {
	if(!user_name.value){
		document.querySelector(".input_name").style.color= "red";
		document.querySelector("[name=name-cont]").style.borderColor = "red";
		document.querySelector("[name=name-cont]").style.animationName = "signUpBlurframes";
		document.querySelector("[name=name-cont]").style.animationDuration = ".2s";
		document.querySelector("[name=name-cont]").style.animationIterationCount = "3";
		document.querySelector("[name=name-cont]").focus();
		return false;
	}	
	document.querySelector(".input_name").style.color= "black";
	document.querySelector("[name=name-cont]").style.borderColor = "#CACACA";
	return true;
};

// 닉네임 온블러
user_nickname.onblur = () => {
	if(!user_nickname.value){
		document.querySelector(".input_nickname").style.color= "red";
		document.querySelector(".notice-nick-text").style.color= "red";
		document.querySelector("[name=nickname-cont]").style.borderColor = "red";
		document.querySelector("[name=nickname-cont]").style.animationName = "signUpBlurframes";
		document.querySelector("[name=nickname-cont]").style.animationDuration = ".2s";
		document.querySelector("[name=nickname-cont]").style.animationIterationCount = "3";
		document.querySelector("[name=nickname-cont]").focus();
		return false;
	}	
	// 닉네임 중복체크 비동기화
		if(user_nickname.value){
			const nickname = document.querySelector("#user_nickname").value;
			console.log(nickname);
			$.ajax({
				url : "<%= request.getContextPath() %>/member/nicknameCheck",
				method : "GET",
				data : {
					memberNickname : nickname
				},
				dataType: "text",
				success(resp) {
					const nicknameVal = document.querySelector("#nickname-hidden-msg");
					nicknameVal.innerHTML = resp;
				},
				error : console.log
			});
		}
	
	document.querySelector(".input_nickname").style.color= "black";
	document.querySelector(".notice-nick-text").style.color= "black";
	document.querySelector("[name=nickname-cont]").style.borderColor = "#CACACA";
	return true;
};

// 닉네임 변경
const changeNick = () => {
	renamedNick = document.querySelector(".renamedNick").innerHTML
	user_nickname.value = renamedNick;
	user_nickname.readOnly = true;
}
// 휴대폰 온블러
phone.onblur = () => {
	if(!phone.value){
		document.querySelector(".input_phone").style.color= "red";
		document.querySelector("[name=phone-cont]").style.borderColor = "red";
		document.querySelector("[name=phone-cont]").style.animationName = "signUpBlurframes";
		document.querySelector("[name=phone-cont]").style.animationDuration = ".2s";
		document.querySelector("[name=phone-cont]").style.animationIterationCount = "3";
		document.querySelector("[name=phone-cont]").focus();
		return false;
	}	
	document.querySelector(".input_phone").style.color= "black";
	document.querySelector("[name=phone-cont]").style.borderColor = "#CACACA";
	return true;
};

// 이메일 온블러
email.onblur = () => {
	if(!email.value){
		document.querySelector(".input_email").style.color= "red";
		document.querySelector("[name=email-cont]").style.borderColor = "red";
		document.querySelector("[name=email-cont]").style.animationName = "signUpBlurframes";
		document.querySelector("[name=email-cont]").style.animationDuration = ".2s";
		document.querySelector("[name=email-cont]").style.animationIterationCount = "3";
		document.querySelector("[name=email-cont]").focus();
		return false;
	}	
	document.querySelector(".input_email").style.color= "black";
	document.querySelector("[name=email-cont]").style.borderColor = "#CACACA";
	return true;
};


/**
 * 회원가입폼 유효성 검사
 */
document.signUpFrm.onsubmit = () => {
	 	const useridVal = document.querySelector("#id").value;
	    const userpasswordVal = document.querySelector("#password").value;
	    const userpasswordCfVal = document.querySelector("#password_confirm").value;
	    const usernameVal = document.querySelector("#user_name").value;
	    const usernicknameVal = document.querySelector("#user_nickname").value;
	    const userphoneVal = document.querySelector("#phone").value;
	    const useremailVal = document.querySelector("#email").value;
	  
	    if(!useridVal) {
	      alert("아이디를 작성해주세요.");
	      return false;
	    }
		if(idValid.value !== "1") {
			alert("아이디 중복검사 해주세요.");
			return false;
		}
	    if(!userpasswordVal) {
	      alert("비밀번호를 작성해주세요.");
	      return false;
	    }
	    if(!userpasswordCfVal) {
	      alert("비밀번호 확인을 작성해주세요.");
	      return false;
	    }
	    if(!usernameVal) {
	      alert("이름을 작성해주세요.");
	      return false;
	    }
	    if(!usernicknameVal) {
	      alert("닉네임을 작성해주세요.");
	      return false;
	    }
	    if(!userphoneVal) {
	      alert("전화번호를 작성해주세요.");
	      return false;
	    }
	    if(!useremailVal) {
	      alert("이메일을 작성해주세요.");
	      return false;
	    }

	    //1.아이디검사
	    //아이디의 길이는(6~16자 영문, 숫자포함)
	    if(!/^[a-zA-Z0-9]{6,16}$/.test(useridVal)){
	        alert('규칙에 맞게 아이디를 6-16자 사이의 숫자를 포함하는 영문자로 만들어 주세요.');
	        return false;
	    }

	    //2.비밀번호 확인 검사 
	    //숫자/문자/특수문자 포함 형태의 8~16자리 이내의 암호 정규식 
	    if(!/(?=.*[a-z])(?=.*[0-9])(?=.*[!@#$%&*])[a-z0-9!@#$%&*]{8,16}/i.test(userpasswordVal)){
	        alert('규칙에 맞게 비밀번호를 8-16자 사이의 영문, 숫자, 특수문자를 포함시켜 만들어 주세요.');
	        return false;
	    }

	    //비밀번호일치여부 검사
	    if(!(userpasswordVal === userpasswordCfVal)){
	        alert('비밀번호가 비밀번호 재입력에 입력된 값과 일치하지 않습니다. 다시 입력해주세요.');
	        return false;
	    }

	    //3.이름검사 : 한글2글자 이상만 허용. 
	    // 한글 검사
	    if(!/^[가-힣]{2,}$/.test(usernameVal)){
	        alert('이름에는 2글자 이상의 한글만 사용할 수 있습니다.');
	        return false;
	    }
	    
	    //4. 닉네임 검사 : 한글2글자 이상만 허용. 
	    // 한글 검사
	    if(!/^[가-힣]{2,15}$/.test(usernameVal)){
	        alert('닉네임에는 2글자 이상 15글자 이하의 한글만 사용할 수 있습니다.');
	        return false;
	    }

	    //4. 휴대폰 번호 검사
	    // 01x 시작, 총 10~11자리
	    // 숫자 여부 검사
	    
	    if(!/^01([0|1|6|7|8|9])-?([0-9]{3,4})-?([0-9]{4})$/.test(userphoneVal)){
	        alert('전화번호에는 숫자만 입력해야 합니다.');
	        return false;
	    }

	    //5.이메일 검사
	    if(!/^[a-z0-9]{4,12}[@].+[.][a-zA-Z]{2,3}$/i.test(useremailVal)){
	        alert('이메일은 @가 포함되어야 하며, 아이디의 길이는 4~12자리이어야 합니다.');
	        return false;
	    }
	}
</script>
<style>
@keyframes signUpBlurframes {
      25% { transform: translateX(4px);}
      50% { transform: translateX(-4px);}
      75% {transform: translateX(4px); }
      100% {transform: translateX(-4px);}
}
</style>
<%@ include file="/WEB-INF/views/common/footer.jsp"%>