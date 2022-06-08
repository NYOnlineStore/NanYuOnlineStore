package online.yukun.service;

import online.yukun.pojo.CartItem;

public interface CartService {
    int delete(int userId,int productId);

    void add(CartItem cartItem);

    CartItem select(int userId, int productId);

    void update(CartItem cartItem);

    void deleteByIds(int[] productId, int userId);
}
