package online.yukun.mapper;

import online.yukun.pojo.Order;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface OrderMapper {
    @Insert("insert into orders (id, money, payState, userId) values " +
            "(#{id},#{money},0,#{userId});")
    void add(Order order);

    @Select("select * from orders where id = #{id} and userId = #{userId};")
    Order selectByIdAndUserId(@Param("id") String id, @Param("userId") int userId);

    @Select("select * from orders where id = #{id};")
    Order selectById(@Param("id") String id);

    @Update("update orders set receiverName = #{name}, receiverAddress = #{address}, receiverPhone = #{phone} where id=#{id};")
    void update(@Param("name") String name, @Param("address") String address,@Param("phone") String telephone,@Param("id") String id);

    @Update("update orders set payState = 1 where id=#{id};")
    void pay(String id);

    @Select("select * from orders where userId = #{id} order by orderTime desc")
    List<Order> selectByUserId(int id);

    @Delete("delete from orders where id=#{id};")
    void deleteById(String id);

    void deleteByIds(@Param("ids") String[] ids);

    @Select("select * from orders where id = #{id}")
    Order selectPayStateById(String id);

    @Select("select orders.id,orders.receiverName,orders.receiverPhone,orders.receiverAddress," +
            "orderItem.buyNumber as payState,orders.orderTime,product.id as userId," +
            "product.price*orderItem.buyNumber as money " +
            "from orders,orderItem,product " +
            "where product.id=orderItem.productId and orderItem.orderId=orders.id " +
            "and product.saleId=#{id} and orders.payState = 1;")
    List<Order> selectBySaleId(int id);

}
