package online.yukun.service.impl;

import online.yukun.mapper.UserMapper;
import online.yukun.pojo.User;
import online.yukun.service.UserService;
import online.yukun.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;


public class UserServiceImpl implements UserService {
    // 1. 创建SqlSessionFactory对象
    SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();

    @Override
    public User selectById(int id) {
        // 2. 获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        // 3. 获取ProductMapper
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        // 4. 调用方法
        User user = mapper.selectById(id);
        // 5. 释放资源，提交事务
        sqlSession.close();
        return user;
    }

    @Override
    public boolean selectByUsername(String username) {
        // 获取sqlSession
        SqlSession sqlSession = factory.openSession();
        // 获取UserMapper
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        // 调用方法
        User u = mapper.selectByUsername(username);
        return u != null;
    }

    @Override
    public void add(User user) {
        // 2. 获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        // 3. 获取BrandMapper
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        // 4. 调用方法
        mapper.add(user);
        // 5. 释放资源，提交事务
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public int activate(String activeCode) {
        // 2. 获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        // 3. 获取BrandMapper
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        // 4. 调用方法
        int row = mapper.activate(activeCode);
        // 5. 释放资源，提交事务
        sqlSession.commit();
        sqlSession.close();
        return row;
    }

    @Override
    public User selectByUsernameAndPassword(String username, String password) {
        // 获取sqlSession
        SqlSession sqlSession = factory.openSession();
        // 获取UserMapper
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        // 调用方法
        User user = mapper.select(username, password);
        // 释放资源，提交
        sqlSession.close();
        return user;
    }

    @Override
    public void update(User user) {
        // 获取sqlSession
        SqlSession sqlSession = factory.openSession();
        // 获取UserMapper
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        // 调用方法
        mapper.update(user);
        // 释放资源，提交
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public List<User> selectAll() {
        // 获取sqlSession
        SqlSession sqlSession = factory.openSession();
        // 获取UserMapper
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        // 调用方法
        List<User> users = mapper.selectAll();
        // 释放资源，提交
        sqlSession.close();
        return users;
    }

    @Override
    public void deleteById(int id) {
        // 获取sqlSession
        SqlSession sqlSession = factory.openSession();
        // 获取UserMapper
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        // 调用方法
        mapper.deleteById(id);
        // 释放资源，提交
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void deleteByIds(int[] ids) {
        // 获取sqlSession
        SqlSession sqlSession = factory.openSession();
        // 获取UserMapper
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        // 调用方法
        mapper.deleteByIds(ids);
        // 释放资源，提交
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void freeze(int id) {
        // 获取sqlSession
        SqlSession sqlSession = factory.openSession();
        // 获取UserMapper
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        // 调用方法
        mapper.freeze(id);
        // 释放资源，提交
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void unfreeze(int id) {
        // 获取sqlSession
        SqlSession sqlSession = factory.openSession();
        // 获取UserMapper
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        // 调用方法
        mapper.unfreeze(id);
        // 释放资源，提交
        sqlSession.commit();
        sqlSession.close();
    }
}
