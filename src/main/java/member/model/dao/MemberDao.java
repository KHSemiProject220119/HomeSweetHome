package member.model.dao;

import static common.JdbcTemplate.*;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import admin.model.exception.AdminException;
import member.model.dto.Address;
import member.model.dto.Member;
import member.model.dto.MemberRole;
import member.model.exception.MemberException;


public class MemberDao {
	private Properties prop = new Properties();
	
	public MemberDao() {
		// buildpath의 sql/member-query.properties파일의 내용을 불러오기
		String fileName = MemberDao.class.getResource("/sql/member-query.properties").getPath();
		try {
			prop.load(new FileReader(fileName));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private Member handleMemberResultSet(ResultSet rset) throws SQLException {
		Member member = new Member();
		member.setMemberId(rset.getString("member_id"));
		member.setPassword(rset.getString("password"));
		member.setMemberName(rset.getString("member_name"));
		member.setNickname(rset.getString("nickname"));
		member.setMemberRole(MemberRole.valueOf(rset.getString("member_role")));
		member.setPhone(rset.getString("phone"));
		member.setEmail(rset.getString("email"));
		member.setBirthday(rset.getDate("birthday"));
		member.setGender(rset.getString("gender"));
		member.setEnrollDate(rset.getDate("enroll_date"));
		return member;
	}

	public List<Member> findAllMembers(Connection conn, Map<String, Object> pageBarPoint) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Member> memberList = new ArrayList<>();
		String sql = prop.getProperty("findAllMembers");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, (int) pageBarPoint.get("start"));
			pstmt.setInt(2, (int) pageBarPoint.get("end"));
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Member member = handleMemberResultSet(rset);
				memberList.add(member);
			}
		} catch (Exception e) {
			throw new AdminException("회원목록 조회 오류", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return memberList;
	}

	public int getTotalContents(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int totalContents = 0;
		String sql = prop.getProperty("getTotalContents");
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			while(rset.next())
				totalContents = rset.getInt(1);  
		} catch (Exception e) {
			throw new AdminException("전체회원수 조회 오류!", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return totalContents;
	}

	public int updateMemberRole(Connection conn, Member member) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("updateMemberRole");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getMemberRole().toString());
			pstmt.setString(2, member.getMemberId());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			throw new AdminException("관리자 - 회원 권한 변경 오류", e);
		} finally {
			close(pstmt);
		}
		return result;
	}

	public Member findByMemberId(Connection conn, String memberId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("findByMemberId");
		Member member = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				member = handleMemberResultSet(rset);
			}
		} catch (Exception e) {
			throw new MemberException("회원 - 아이디 중복확인 조회 오류", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return member;
	}

	public Member findByMemberEmail(Connection conn, String memberEmail) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("findByMemberEmail");
		Member member = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberEmail);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				member = handleMemberResultSet(rset);
			}
		} catch (Exception e) {
			throw new MemberException("회원 - 이메일 중복 확인 오류", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return member;
	}

	public int insertMember(Connection conn, Member member) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("insertMember");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getMemberId());
			pstmt.setString(2, member.getPassword());
			pstmt.setString(3, member.getMemberName());
			pstmt.setString(4, member.getNickname());
			pstmt.setString(5, member.getMemberRole().toString()); // "U" "A"
			pstmt.setString(6, member.getPhone());
			pstmt.setString(7, member.getEmail());
			pstmt.setDate(8, member.getBirthday());
			pstmt.setString(9, member.getGender());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			throw new MemberException("회원 가입 오류", e);
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int insertMemberAddr(Connection conn, Address addressInfo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("insertMemberAddr");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, addressInfo.getMemberId());
			pstmt.setString(2, addressInfo.getPostCode());
			pstmt.setString(3, addressInfo.getAddress()); 
			pstmt.setString(4, addressInfo.getAddressDetail());
			pstmt.setString(5, addressInfo.getAddressExtra());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			throw new MemberException("회원 가입 오류", e);
		} finally {
			close(pstmt);
		}
		return result;
	}

	public Member findByMemberNickname(Connection conn, String memberNickname) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("findByMemberNickname");
		Member member = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberNickname);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				member = handleMemberResultSet(rset);
			}
		} catch (Exception e) {
			throw new MemberException("닉네임 중복확인 오류", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return member;
	}

}
