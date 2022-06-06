package common;


import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Base64.Encoder;

public class HomeSweetHomeUtils {

	public static String encrypt(String password, String salt) {
		// 1. 암호화 Hashing
		MessageDigest md = null;
		byte[] encrypted = null;
		try {
				md = MessageDigest.getInstance("SHA-512");
				byte[] input = password.getBytes("utf-8");
				byte[] saltBytes = salt.getBytes("utf-8");
				md.update(saltBytes); 
				encrypted = md.digest(input);
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			e.printStackTrace();
		} 
	
		// 2. 인코딩 (단순문자변환)
		Encoder encoder = Base64.getEncoder();
		return encoder.encodeToString(encrypted);
	}

	public static String getPagebar(int cPage, int numPerPage, int totalContents, String url) {
		StringBuilder pagebar = new StringBuilder();
		int totalPages = (int) Math.ceil((double) totalContents / numPerPage); // 전체 페이지수
		int pagebarSize = 5;
		int pagebarStart = (cPage -1) / pagebarSize * pagebarSize + 1;
		int pagebarEnd = pagebarStart + pagebarSize - 1;
		int pageNo = pagebarStart;
		
		url += "?cPage=";
		
		// 이전- prev 버튼
		if(pageNo == 1) {
			// prev  버튼 비활성화
		}
		else {
			// prev 버튼 활성화
			pagebar.append("<a href='" + url + (pageNo - 1) +"'>prev</a>");
			pagebar.append("\n");
		}	
	    // 페이지 번호 pageNo
		while(pageNo <= pagebarEnd && pageNo <= totalPages) {
			if(pageNo == cPage) {
				// 현재 페이지인 경우
				pagebar.append("<span class='cPage'>" + pageNo + "</span>");
				pagebar.append("\n");			
			}
			else {
				// 현재 페이지가 아닌 경우
				pagebar.append("<a href='" + url + pageNo +"'>"+ pageNo +"</a>");
				pagebar.append("\n");
			}
			pageNo++;
		}
		
		// 다음 next
		if(pageNo > totalPages) {
			// next 버튼 비활성화
		}
		else {
			// next 버튼 활성화
			pagebar.append("<a href='" + url + pageNo +"'>next</a>");
			pagebar.append("\n");
		}
		return pagebar.toString();
	}
}
