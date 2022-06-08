package online.yukun.mapper;

import online.yukun.pojo.Product;
import online.yukun.pojo.ProductPlus;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface ProductMapper {

    List<Product> selectByPageAndCondition(@Param("begin") int begin, @Param("size") int size, @Param("product") Product product);

    int selectTotalCountByCondition(@Param("product") Product product);

    @Select("SELECT * FROM product where status = 1 ORDER BY  RAND() LIMIT #{number}")
    List<Product> selectByRandom(int number);

    @Select("select * from product where id = #{id};")
    ProductPlus selectById(int id);

    @Select("select product.*,user.name as saleName,user.address as saleAddress,cartItem.num as num from product,user,cartItem where product.saleId = user.id and product.id=productId and userId=#{id} order by time desc;")
    List<ProductPlus> selectByUserId(int id);

    @Select("select product.id,product.name,product.description,product.price,orderItem.buyNumber as pnum," +
            "product.categoryName,product.imgurl,product.saleId,product.status," +
            "user.name as saleName,user.address as saleAddress " +
            "from product,orderItem,user " +
            "where orderItem.productId = product.id and product.saleId = user.id and orderItem.orderId = #{id} ")
    List<ProductPlus> selectByOrderId(String id);

    // 后期实现
    List<Product> selectByPageAndSale(@Param("begin") int begin, @Param("size") int size, @Param("product") Product product);

    @Select("select * from product where saleId = #{id} order by time desc;")
    List<ProductPlus> selectBySaleId(int id);

    @Delete("delete from product where id=#{id};")
    void delete(int id);

    void deleteByIds(@Param("ids") int[] ids);

    @Insert("insert into product (name, description, price, category, categoryName, pnum, imgurl, saleId, ordered, status) " +
            "values (#{name},#{description},#{price},#{category},#{categoryName},#{pnum},#{imgurl},#{saleId},1,#{status});")
    void add(ProductPlus productPlus);

    void update(ProductPlus productPlus);

    @Update("update product set status = 0 where id=#{id};")
    void takeDown(int id);

    @Select("select * from product order by time desc")
    List<ProductPlus> selectAll();
}
