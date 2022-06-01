package product.model.service;

import static common.JdbcTemplate.*;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import product.model.dao.ProductDao;
import product.model.dto.Product;

public class ProductService {

	private ProductDao productDao = new ProductDao();
	
	public List<Product> findAllProducts(Map<String, Object> param) {
		Connection conn = getConnection();
		List<Product> productList = productDao.findAllProducts(conn, param);
		close(conn);
		return productList;
	}

	public int getTotalContents() {
		Connection conn = getConnection();
		int totalContents = productDao.getTotalContents(conn);
		close(conn);
		return totalContents;
	}

}
