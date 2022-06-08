package online.yukun.service.impl;

import online.yukun.mapper.CartItemMapper;
import online.yukun.pojo.CartItem;
import online.yukun.service.CartService;
import online.yukun.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class CartServiceImpl implements CartService {
    // 1. 创建SqlSessionFactory对象
    SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();
    @Override
    public int delete(int userId, int productId) {
        // 2. 获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        // 3. 获取BrandMapper
        CartItemMapper mapper = sqlSession.getMapper(CartItemMapper.class);
        // 4. 调用方法
        int delete = mapper.delete(userId, productId);
        // 5. 释放资源，提交事务
        sqlSession.commit();
        sqlSession.close();
        return delete;
    }

    @Override
    public void add(CartItem cartItem) {
        // 2. 获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        // 3. 获取BrandMapper
        CartItemMapper mapper = sqlSession.getMapper(CartItemMapper.class);
        // 4. 调用方法
        CartItem cartItem1 = mapper.select(cartItem.getUserId(), cartItem.getProductId());
        if (cartItem1 != null) {
            cartItem1.setNum(cartItem1.getNum() + cartItem.getNum());
            mapper.update(cartItem1);
        } else {
            mapper.add(cartItem);
        }
        // 5. 释放资源，提交事务
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public CartItem select(int userId, int productId) {
        // 2. 获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        // 3. 获取BrandMapper
        CartItemMapper mapper = sqlSession.getMapper(CartItemMapper.class);
        // 4. 调用方法
        CartItem cartItem = mapper.select(userId, productId);
        // 5. 释放资源，提交事务
        sqlSession.close();
        return cartItem;
    }

    @Override
    public void update(CartItem cartItem) {
        // 2. 获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        // 3. 获取BrandMapper
        CartItemMapper mapper = sqlSession.getMapper(CartItemMapper.class);
        // 4. 调用方法
        mapper.update(cartItem);
        // 5. 释放资源，提交事务
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void deleteByIds(int[] productId, int userId) {
        // 2. 获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        // 3. 获取BrandMapper
        CartItemMapper mapper = sqlSession.getMapper(CartItemMapper.class);
        // 4. 调用方法
        mapper.deleteByIds(productId, userId);
        // 5. 释放资源，提交事务
        sqlSession.commit();
        sqlSession.close();
    }
}
