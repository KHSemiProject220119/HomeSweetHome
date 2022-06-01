package product.model.dao;

import static common.JdbcTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import product.model.dto.BrandId;
import product.model.dto.MainCode;
import product.model.dto.Product;
import product.model.dto.ProductColor;
import product.model.dto.SubCode;
import product.model.exception.ProductException;

public class ProductDao {
	
	private Properties prop = new Properties();
	
	public ProductDao() {
		String fileName = ProductDao.class.getResource("/sql/product-query.properties").getPath();
		try {
			prop.load(new FileReader(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private Product handleProductResultSet(ResultSet rset) throws SQLException {
		Product product = new Product();
		product.setProductId(rset.getString("product_id"));
		product.setProductName(rset.getString("product_name"));
		product.setMainCode(MainCode.valueOf(rset.getString("main_code")));
		product.setSubCode(SubCode.valueOf(rset.getString("sub_code")));
		product.setBrandId(BrandId.valueOf(rset.getString("brand_id")));				
		product.setProductHeight(rset.getDouble("product_height"));
		product.setProductWidth(rset.getDouble("product_width"));
		product.setProductDepth(rset.getDouble("product_depth"));
		product.setProductColor(ProductColor.valueOf(rset.getString("product_color")));				
		product.setProductPrice(rset.getInt("product_price"));				
		product.setRegDate(rset.getDate("reg_date"));				
		return product;
	}

	public List<Product> findAllProducts(Connection conn, Map<String, Object> param) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Product> productList = new ArrayList<>();
		String sql = prop.getProperty("findAllProducts");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, (int) param.get("start"));
			pstmt.setInt(2, (int) param.get("end"));
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Product product = handleProductResultSet(rset);
				productList.add(product);
			}
		} catch (Exception e) {
			throw new ProductException("관리자 상품 리스트 조회 오류", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return productList;
	}

	public int getTotalContents(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int totalContents = 0;
		String sql = prop.getProperty("getTotalContents");
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				totalContents = rset.getInt(1);
			}
		} catch (Exception e) {
			throw new ProductException("총 등록 상품수 조회 오류", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return totalContents;
	}

}
