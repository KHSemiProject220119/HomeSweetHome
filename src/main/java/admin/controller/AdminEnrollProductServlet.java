package admin.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.HomeSweetHomeUtils;
import product.model.dto.Product;
import product.model.service.ProductService;

/**
 * Servlet implementation class AdminEnrollProductServlet
 */
@WebServlet("/admin/manageProduct")
public class AdminEnrollProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductService productService = new ProductService();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// 1. 사용자 입력값 처리
			int numPerPage = 10;
			int cPage = 1;
			try {
				cPage = Integer.parseInt(request.getParameter("cPage"));
			} catch(NumberFormatException e) {}
			Map<String, Object> param = new HashMap<>();
			int start = (cPage - 1) * numPerPage + 1;
			int end = cPage * numPerPage;
			param.put("start", start);
			param.put("end", end);
			
			// 2. 업무로직
			// 2.a content영역
			List<Product> productList = productService.findAllProducts(param);
//			System.out.println(productList);
			// 2.b pagebar영역
			int totalContents = productService.getTotalContents();
			String pagebar = HomeSweetHomeUtils.getPagebar(cPage, numPerPage, totalContents, request.getRequestURI());
			System.out.println(pagebar);
			
			// 3. view단 처리
			request.setAttribute("productList", productList);
			request.setAttribute("pagebar", pagebar);
			request.getRequestDispatcher("/WEB-INF/views/admin/productManage.jsp")
				.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
	}


}
