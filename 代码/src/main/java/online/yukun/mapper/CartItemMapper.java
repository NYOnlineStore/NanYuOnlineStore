package online.yukun.mapper;

import online.yukun.pojo.CartItem;
import org.apache.ibatis.annotations.*;

public interface CartItemMapper {
    @Select("select * from cartItem where userId = #{userId} and productId = #{productId};")
    CartItem select(@Param("userId") int userId,@Param("productId") int productId);

    @Insert("insert into cartItem(userId,productId,num) values" +
            "(#{userId},#{productId},#{num})")
    void add(CartItem cartItem);

    @Delete("delete from cartItem where userId = #{userId} and productId = #{productId};")
    int delete(@Param("userId") int userId,@Param("productId") int productId);

    @Update("update cartItem set num = #{num} where userId = #{userId} and productId = #{productId};")
    void update(CartItem cartItem);

    void deleteByIds(@Param("ids") int[] ids,@Param("userId") int userId);

}
