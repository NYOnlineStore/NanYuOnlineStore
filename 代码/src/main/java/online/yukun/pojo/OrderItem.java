package online.yukun.pojo;

public class OrderItem {

	private String orderId;
	private int productId;
	private Product product;
	private int buyNumber;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getBuyNumber() {
		return buyNumber;
	}

	public void setBuyNumber(int buyNumber) {
		this.buyNumber = buyNumber;
	}

	@Override
	public String toString() {
		return "OrderItem{" +
				"orderId='" + orderId + '\'' +
				", productId=" + productId +
				", product=" + product +
				", buyNumber=" + buyNumber +
				'}';
	}
}
