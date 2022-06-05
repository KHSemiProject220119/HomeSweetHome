package member.model.dto;

public class Address {

	private int addressNo;
	private String memberId;
	private String addressTitle;
	private String postCode;
	private String address;
	private String addressDetail;
	private String addressExtra;
	
	public Address() {
		super();
	}

	public Address(int addressNo, String memberId, String addressTitle, String postCode, String address,
			String addressDetail, String addressExtra) {
		super();
		this.addressNo = addressNo;
		this.memberId = memberId;
		this.addressTitle = addressTitle;
		this.postCode = postCode;
		this.address = address;
		this.addressDetail = addressDetail;
		this.addressExtra = addressExtra;
	}

	public int getAddressNo() {
		return addressNo;
	}

	public void setAddressNo(int addressNo) {
		this.addressNo = addressNo;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getAddressTitle() {
		return addressTitle;
	}

	public void setAddressTitle(String addressTitle) {
		this.addressTitle = addressTitle;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddressDetail() {
		return addressDetail;
	}

	public void setAddressDetail(String addressDetail) {
		this.addressDetail = addressDetail;
	}

	public String getAddressExtra() {
		return addressExtra;
	}

	public void setAddressExtra(String addressExtra) {
		this.addressExtra = addressExtra;
	}

	@Override
	public String toString() {
		return "Address [addressNo=" + addressNo + ", memberId=" + memberId + ", addressTitle=" + addressTitle
				+ ", postCode=" + postCode + ", address=" + address + ", addressDetail=" + addressDetail
				+ ", addressExtra=" + addressExtra + "]";
	}
}