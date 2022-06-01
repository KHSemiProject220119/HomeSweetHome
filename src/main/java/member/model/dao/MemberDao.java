package member.model.dao;

import static common.JdbcTemplate.*;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import admin.model.exception.AdminException;
import member.model.dto.Member;
import member.model.dto.MemberRole;

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
		member.setMemberRole(MemberRole.valueOf(rset.getString("member_role")));
		member.setGender(rset.getString("gender"));
		member.setBirthday(rset.getDate("birthday"));
		member.setEmail(rset.getString("email"));
		member.setPhone(rset.getString("phone"));
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
}
