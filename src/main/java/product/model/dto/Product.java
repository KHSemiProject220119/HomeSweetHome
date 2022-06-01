package product.model.dto;

import java.sql.Date;

public class Product {

	private String productId;
	private String productName;
	private MainCode mainCode;
	private SubCode subCode;
	private BrandId brandId;
	private double productHeight;
	private double productWidth;
	private double productDepth;
	private ProductColor productColor;
	private int productPrice;
	private Date regDate;
	
	public Product() {
		super();
	}

	public Product(String productId, String productName, MainCode mainCode, SubCode subCode, BrandId brandId,
			double productHeight, double productWidth, double productDepth, ProductColor productColor, int productPrice,
			Date regDate) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.mainCode = mainCode;
		this.subCode = subCode;
		this.brandId = brandId;
		this.productHeight = productHeight;
		this.productWidth = productWidth;
		this.productDepth = productDepth;
		this.productColor = productColor;
		this.productPrice = productPrice;
		this.regDate = regDate;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public MainCode getMainCode() {
		return mainCode;
	}

	public void setMainCode(MainCode mainCode) {
		this.mainCode = mainCode;
	}

	public SubCode getSubCode() {
		return subCode;
	}

	public void setSubCode(SubCode subCode) {
		this.subCode = subCode;
	}

	public BrandId getBrandId() {
		return brandId;
	}

	public void setBrandId(BrandId brandId) {
		this.brandId = brandId;
	}

	public double getProductHeight() {
		return productHeight;
	}

	public void setProductHeight(double productHeight) {
		this.productHeight = productHeight;
	}

	public double getProductWidth() {
		return productWidth;
	}

	public void setProductWidth(double productWidth) {
		this.productWidth = productWidth;
	}

	public double getProductDepth() {
		return productDepth;
	}

	public void setProductDepth(double productDepth) {
		this.productDepth = productDepth;
	}

	public ProductColor getProductColor() {
		return productColor;
	}

	public void setProductColor(ProductColor productColor) {
		this.productColor = productColor;
	}

	public int getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productName=" + productName + ", mainCode=" + mainCode
				+ ", subCode=" + subCode + ", brandId=" + brandId + ", productHeight=" + productHeight
				+ ", productWidth=" + productWidth + ", productDepth=" + productDepth + ", productColor=" + productColor
				+ ", productPrice=" + productPrice + ", regDate=" + regDate + "]";
	}
}