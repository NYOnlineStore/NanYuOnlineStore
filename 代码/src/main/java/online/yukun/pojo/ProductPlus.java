package online.yukun.pojo;

public class ProductPlus {

	private Integer id;
	private String name;
	private Double price;
	private String category;
	private String categoryName;
	private String description;
	private Integer pnum;
	private Integer saleId;
	private String saleName;
	private String saleAddress;
	private User salePerson;
	private Integer num;
	private String imgurl;
	private Integer ordered;
	// 状态：0：下架  1：销售
	private Integer status;
	private String time;

	public Integer getSaleId() {
		return saleId;
	}

	public void setSaleId(Integer saleId) {
		this.saleId = saleId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSaleName() {
		return saleName;
	}

	public void setSaleName(String saleName) {
		this.saleName = saleName;
	}

	public String getSaleAddress() {
		return saleAddress;
	}

	public void setSaleAddress(String saleAddress) {
		this.saleAddress = saleAddress;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getPnum() {
		return pnum;
	}

	public void setPnum(Integer pnum) {
		this.pnum = pnum;
	}

	public User getSalePerson() {
		return salePerson;
	}

	public void setSalePerson(User salePerson) {
		this.salePerson = salePerson;
	}

	public String getImgurl() {
		return imgurl;
	}

	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}

	public Integer getOrdered() {
		return ordered;
	}

	public void setOrdered(Integer ordered) {
		this.ordered = ordered;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "ProductPlus{" +
				"id=" + id +
				", name='" + name + '\'' +
				", price=" + price +
				", category='" + category + '\'' +
				", categoryName='" + categoryName + '\'' +
				", description='" + description + '\'' +
				", pnum=" + pnum +
				", saleId=" + saleId +
				", saleName='" + saleName + '\'' +
				", saleAddress='" + saleAddress + '\'' +
				", salePerson=" + salePerson +
				", num=" + num +
				", imgurl='" + imgurl + '\'' +
				", ordered=" + ordered +
				", status=" + status +
				", time='" + time + '\'' +
				'}';
	}
}
