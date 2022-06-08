package online.yukun.pojo;

import java.util.ArrayList;
import java.util.List;

public class Order {
	private String id;
	private double money;
	private String receiverAddress;
	private String receiverName;
	private String receiverPhone;
	private int payState;
	private String orderTime;
	private int userId;
	private User user;
	private List<OrderItem> orderItems = new ArrayList<>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	public String getReceiverAddress() {
		return receiverAddress;
	}

	public void setReceiverAddress(String receiverAddress) {
		this.receiverAddress = receiverAddress;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public String getReceiverPhone() {
		return receiverPhone;
	}

	public void setReceiverPhone(String receiverPhone) {
		this.receiverPhone = receiverPhone;
	}

	public int getPayState() {
		return payState;
	}

	public void setPayState(int payState) {
		this.payState = payState;
	}

	public String getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	@Override
	public String toString() {
		return "Order{" +
				"id='" + id + '\'' +
				", money=" + money +
				", receiverAddress='" + receiverAddress + '\'' +
				", receiverName='" + receiverName + '\'' +
				", receiverPhone='" + receiverPhone + '\'' +
				", payState=" + payState +
				", orderTime=" + orderTime +
				", userId=" + userId +
				", user=" + user +
				", orderItems=" + orderItems +
				'}';
	}
}
