package online.yukun.service;

import online.yukun.pojo.Order;

import java.util.List;

public interface OrderService {
    void add(Order order);

    boolean selectByIdAndUserId(String id,int userId);

    Order selectById(String id);

    void update(String name, String address, String telephone, String id);

    void pay(String id);

    List<Order> selectByUserId(int id);

    void deleteById(String id);

    void deleteByIds(String[] ids);

    Order selectPayStateById(String id);

    List<Order> selectBySaleId(int id);
}
