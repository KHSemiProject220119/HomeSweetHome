package member.model.dto;

import java.sql.Date;

public class Member {

	private String memberId;
	private String password;
	private String memberName;
	private String nickname;
	private MemberRole memberRole; // enum U A
	private String phone;
	private String email;
	private Date birthday;
	private String gender;
	private Date enrollDate;

	public Member() {
		super();
	}

	public Member(String memberId, String password, String memberName, String nickname, MemberRole memberRole,
			String phone, String email, Date birthday, String gender, Date enrollDate) {
		super();
		this.memberId = memberId;
		this.password = password;
		this.memberName = memberName;
		this.nickname = nickname;
		this.memberRole = memberRole;
		this.phone = phone;
		this.email = email;
		this.birthday = birthday;
		this.gender = gender;
		this.enrollDate = enrollDate;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public MemberRole getMemberRole() {
		return memberRole;
	}

	public void setMemberRole(MemberRole memberRole) {
		this.memberRole = memberRole;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getEnrollDate() {
		return enrollDate;
	}

	public void setEnrollDate(Date enrollDate) {
		this.enrollDate = enrollDate;
	}

	@Override
	public String toString() {
		return "Member [memberId=" + memberId + ", password=" + password + ", memberName=" + memberName + ", nickname="
				+ nickname + ", memberRole=" + memberRole + ", phone=" + phone + ", email=" + email + ", birthday="
				+ birthday + ", gender=" + gender + ", enrollDate=" + enrollDate + "]";
	}
}