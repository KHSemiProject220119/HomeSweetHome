<%@page import="product.model.dto.Product"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%
	List<Product> productList = (List<Product>) request.getAttribute("productList");
	String pagebar = (String) request.getAttribute("pagebar");
	
	String searchType = request.getParameter("searchType");
	String searchKeyword = request.getParameter("searchKeyword");

%>
<section>
	<h2>회원관리</h2>	
	<div id="search-container">
    	<label for="searchType">검색타입 :</label> 
        <select id="searchType">
            <option value="product_id" <%="product_id".equals(searchType)?"selected":""%>>제품아이디</option>		
			<option value="product_name" <%="product_name".equals(searchType)?"selected":""%>>제품명</option>
        </select>
        <div id="search-memberId" class="search-type">
            <form action="<%=request.getContextPath()%>/admin/productFinder">
                <input type="hidden" name="searchType" value="product_id"/>
                <input type="text" name="searchKeyword"  size="25" placeholder="검색할 제품 아이디를 입력하세요." value="<%= "product_id".equals(searchType) ? searchKeyword : "" %>"/>
                <button type="submit">검색</button>			
            </form>	
        </div>
        <div id="search-memberName" class="search-type">
            <form action="<%=request.getContextPath()%>/admin/productFinder">
                <input type="hidden" name="searchType" value="product_name"/>
                <input type="text" name="searchKeyword" size="25" placeholder="검색할 이름을 입력하세요." value="<%= "product_name".equals(searchType) ? searchKeyword : "" %>"/>
                <button type="submit">검색</button>			
            </form>	
        </div>
    </div>
	
	<table id="tbl-product-manage">
		<thead>
			<tr>
				<th>상품 아이디</th>
				<th>상품명</th>
				<th>대분류</th>
				<th>소분류</th>
				<th>브랜드명</th>
				<th>높이</th>
				<th>너비</th>
				<th>깊이</th>
				<th>색상</th>
				<th>가격</th>
				<th>상품등록일</th>
				<th>상품정보수정</th>
				<th>상품삭제</th>
			</tr>
		</thead>
		<tbody>
<%
		if(productList != null && !productList.isEmpty()){
			for(Product product : productList){
%>			
			<tr>
				<td><%= product.getProductId() %></td>
				<td><%= product.getProductName() %></td>
				<td>
					<select class="product-main-category" data-product-id="<%=  product.getProductId() %>">
						<%-- <option value="<%= MainCode.A %>" <%= product.getMainCode() == MainCode.A ? "selected" : "" %>>가구</option>
						<option value="<%= MainCode.U %>" <%= product.getMainCode() == MainCode.U ? "selected" : "" %>>일반회원</option> --%>
					</select>
				</td>
				<td>
					<select class="product-sub-category" data-product-id="<%=  product.getProductId() %>">
						<%-- <option value="<%= SubCode.A %>" <%= product.getSubCode() == SubCode.A ? "selected" : "" %>>가구</option>
						<option value="<%= SubCode.U %>" <%= product.getSubCode() == SubCode.U ? "selected" : "" %>>일반회원</option> --%>
					</select>
				</td>
				<td>
					<select class="product-brand-id" data-product-id="<%=  product.getProductId() %>">
					<%-- 	<option value="<%= BrandId.A %>" <%= product.getBrandId() == BrandId.A ? "selected" : "" %>>가구</option>
						<option value="<%= BrandId.U %>" <%= product.getBrandId() == BrandId.U ? "selected" : "" %>>일반회원</option> --%>
					</select>
				</td>
				<td><%= product.getProductHeight() %></td>
				<td><%= product.getProductWidth() %></td>
				<td><%= product.getProductDepth() %></td>
					<td>
					<select class="product-brand-id" data-product-id="<%=  product.getProductId() %>">
						<%-- <option value="<%= ProductColor.A %>" <%= product.getProductColor() == ProductColor.A ? "selected" : "" %>>가구</option>
						<option value="<%= ProductColor.U %>" <%= product.getProductColor() == ProductColor.U ? "selected" : "" %>>일반회원</option> --%>
					</select>
				</td>
				<td><%= product.getRegDate() %></td>
				<td><button type="button" class="btn-suspension">상품정보 변경</button></td>
				<td><button type="button" class="btn-forced-withdrawal">상품 삭제</button> </td>
			</tr>			
<%
			}
		}
		else {
%>			
			<tr>
				<td colspan="13">조회된 상품이 없습니다.</td>
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


<%@ include file="/WEB-INF/views/common/footer.jsp" %>