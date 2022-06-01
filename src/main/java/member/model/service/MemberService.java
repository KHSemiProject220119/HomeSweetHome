package member.model.service;

import static common.JdbcTemplate.*;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import member.model.dao.MemberDao;
import member.model.dto.Member;

public class MemberService {
	
	public static final int NUM_PER_PAGE = 10; // 한페이지에 표시할 컨텐츠수
	private MemberDao memberDao = new MemberDao();
	
	public List<Member> findAllMembers(Map<String, Object> pageBarPoint) {
		Connection conn = getConnection();
		List<Member> memberList = memberDao.findAllMembers(conn, pageBarPoint);
		close(conn);
		return memberList;
	}

	public int getTotalContents() {
		Connection conn = getConnection();
		int totalContents = memberDao.getTotalContents(conn);
		close(conn);
		return totalContents;
	}

	public int updateMemberRole(Member member) {
		int result = 0;
		Connection conn = getConnection();
		try {
			result = memberDao.updateMemberRole(conn, member);
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}
}
