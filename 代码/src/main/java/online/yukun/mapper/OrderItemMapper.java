package online.yukun.mapper;

import online.yukun.pojo.OrderItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderItemMapper {
    void add(@Param("orderItems") List<OrderItem> orderItems);
}
