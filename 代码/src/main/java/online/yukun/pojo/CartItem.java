package online.yukun.pojo;

public class CartItem {
    private int userId;
    private int productId;
    private int num;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "userId=" + userId +
                ", productId=" + productId +
                ", num=" + num +
                '}';
    }
}
