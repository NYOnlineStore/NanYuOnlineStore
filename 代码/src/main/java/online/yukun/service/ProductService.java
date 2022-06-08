package online.yukun.service;

import online.yukun.pojo.PageBean;
import online.yukun.pojo.Product;
import online.yukun.pojo.ProductPlus;

import java.util.List;

public interface ProductService {
    // 分页查询
    PageBean<Product> selectByPageAndCondition(int currentPage, int pageSize, Product product);

    // 首页随机展示
    PageBean<Product> selectByRandom(int number);

    ProductPlus selectById(int id);

    // 按照用户id查询用户购物车中的商品
    List<ProductPlus> selectByUserId(int id);

    List<ProductPlus> selectByOrderId(String id);

    List<ProductPlus> selectBySaleId(int id);

    void delete(int id);

    void deleteByIds(int[] ids);

    void add(ProductPlus productPlus);

    void update(ProductPlus productPlus);

    void takeDown(int id);

    List<ProductPlus> selectAll();
}
