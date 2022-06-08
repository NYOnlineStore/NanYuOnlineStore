package online.yukun.service.impl;

import online.yukun.mapper.OrderItemMapper;
import online.yukun.mapper.OrderMapper;
import online.yukun.pojo.Order;
import online.yukun.service.OrderService;
import online.yukun.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class OrderServiceImpl implements OrderService {
    // 1. 创建SqlSessionFactory对象
    SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();
    @Override
    public void add(Order order) {
        // 2. 获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        // 3. 获取BrandMapper
        OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);
        OrderItemMapper orderItemMapper = sqlSession.getMapper(OrderItemMapper.class);
        // 4. 调用方法
        orderMapper.add(order);
        orderItemMapper.add(order.getOrderItems());
        // 5. 释放资源，提交事务
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public boolean selectByIdAndUserId(String id, int userId) {
        // 2. 获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        // 3. 获取BrandMapper
        OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);
        // 4. 调用方法
        Order order = orderMapper.selectByIdAndUserId(id, userId);
        // 5. 释放资源，提交事务
        sqlSession.close();
        return order != null;
    }

    @Override
    public Order selectById(String id) {
        // 2. 获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        // 3. 获取BrandMapper
        OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);
        // 4. 调用方法
        Order order = orderMapper.selectById(id);
        // 5. 释放资源，提交事务
        sqlSession.close();
        return order;
    }

    @Override
    public void update(String name, String address, String telephone, String id) {
        // 2. 获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        // 3. 获取BrandMapper
        OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);
        // 4. 调用方法
        orderMapper.update(name, address, telephone, id);
        // 5. 释放资源，提交事务
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void pay(String id) {
        // 2. 获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        // 3. 获取BrandMapper
        OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);
        // 4. 调用方法
        orderMapper.pay(id);
        // 5. 释放资源，提交事务
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public List<Order> selectByUserId(int id) {
        // 2. 获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        // 3. 获取BrandMapper
        OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);
        // 4. 调用方法
        List<Order> orders = orderMapper.selectByUserId(id);
        // 5. 释放资源，提交事务
        sqlSession.close();
        return orders;
    }

    @Override
    public void deleteById(String id) {
        // 2. 获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        // 3. 获取BrandMapper
        OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);
        // 4. 调用方法
        orderMapper.deleteById(id);
        // 5. 释放资源，提交事务
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void deleteByIds(String[] ids) {
        // 2. 获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        // 3. 获取BrandMapper
        OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);
        // 4. 调用方法
        orderMapper.deleteByIds(ids);
        // 5. 释放资源，提交事务
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public Order selectPayStateById(String id) {
        // 2. 获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        // 3. 获取BrandMapper
        OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);
        // 4. 调用方法
        Order order = orderMapper.selectPayStateById(id);
        // 5. 释放资源，提交事务
        sqlSession.close();
        return order;
    }

    @Override
    public List<Order> selectBySaleId(int id) {
        // 2. 获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        // 3. 获取BrandMapper
        OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);
        // 4. 调用方法
        List<Order> orders = orderMapper.selectBySaleId(id);
        // 5. 释放资源，提交事务
        sqlSession.close();
        return orders;
    }
}
