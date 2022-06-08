package online.yukun.service.impl;

import online.yukun.mapper.ProductMapper;
import online.yukun.pojo.PageBean;
import online.yukun.pojo.Product;
import online.yukun.pojo.ProductPlus;
import online.yukun.service.ProductService;
import online.yukun.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class ProductServiceImpl implements ProductService {
    // 1. 创建SqlSessionFactory对象
    SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();

    @Override
    public PageBean<Product> selectByPageAndCondition(int currentPage, int pageSize, Product product) {
        // 2. 获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        // 3. 获取ProductMapper
        ProductMapper mapper = sqlSession.getMapper(ProductMapper.class);
        // 4. 调用方法
        if (product != null) {
            String productName = product.getName();
            if (productName != null && productName.length() > 0) {
                product.setName("%" + productName + "%");
            }
        }
        List<Product> rows = mapper.selectByPageAndCondition((currentPage - 1) * pageSize, pageSize, product);
        int totalCount = mapper.selectTotalCountByCondition(product);
        //int totalCount = 1000;
        PageBean<Product> pageBean = new PageBean<>();
        pageBean.setRows(rows);
        pageBean.setTotalCount(totalCount);
        pageBean.setCurrentPage(currentPage);
        pageBean.setPageSize(pageSize);
        // 5. 释放资源，提交事务
        sqlSession.close();

        return pageBean;
    }

    @Override
    public PageBean<Product> selectByRandom(int number) {
        // 2. 获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        // 3. 获取ProductMapper
        ProductMapper mapper = sqlSession.getMapper(ProductMapper.class);
        // 4. 调用方法
        List<Product> rows = mapper.selectByRandom(number);
        PageBean<Product> pageBean = new PageBean<>();
        pageBean.setRows(rows);
        pageBean.setTotalCount(number);
        pageBean.setCurrentPage(1);
        pageBean.setPageSize(number);
        // 5. 释放资源，提交事务
        sqlSession.close();

        return pageBean;
    }

    @Override
    public ProductPlus selectById(int id) {
        // 2. 获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        // 3. 获取ProductMapper
        ProductMapper mapper = sqlSession.getMapper(ProductMapper.class);
        // 4. 调用方法
        ProductPlus productPlus = mapper.selectById(id);
        // 5. 释放资源，提交事务
        sqlSession.close();
        return productPlus;
    }

    @Override
    public List<ProductPlus> selectByUserId(int id) {
        // 2. 获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        // 3. 获取ProductMapper
        ProductMapper mapper = sqlSession.getMapper(ProductMapper.class);
        // 4. 调用方法
        List<ProductPlus> products = mapper.selectByUserId(id);
        // 5. 释放资源，提交事务
        sqlSession.close();
        return products;
    }

    @Override
    public List<ProductPlus> selectByOrderId(String id) {
        // 2. 获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        // 3. 获取ProductMapper
        ProductMapper mapper = sqlSession.getMapper(ProductMapper.class);
        // 4. 调用方法
        List<ProductPlus> products = mapper.selectByOrderId(id);
        // 5. 释放资源，提交事务
        sqlSession.close();
        return products;
    }

    @Override
    public List<ProductPlus> selectBySaleId(int id) {
        // 2. 获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        // 3. 获取ProductMapper
        ProductMapper mapper = sqlSession.getMapper(ProductMapper.class);
        // 4. 调用方法
        List<ProductPlus> products = mapper.selectBySaleId(id);
        // 5. 释放资源，提交事务
        sqlSession.close();
        return products;
    }

    @Override
    public void delete(int id) {
        // 2. 获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        // 3. 获取ProductMapper
        ProductMapper mapper = sqlSession.getMapper(ProductMapper.class);
        // 4. 调用方法
        mapper.delete(id);
        // 5. 释放资源，提交事务
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void deleteByIds(int[] ids) {
        // 2. 获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        // 3. 获取ProductMapper
        ProductMapper mapper = sqlSession.getMapper(ProductMapper.class);
        // 4. 调用方法
        mapper.deleteByIds(ids);
        // 5. 释放资源，提交事务
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void add(ProductPlus productPlus) {
        // 2. 获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        // 3. 获取ProductMapper
        ProductMapper mapper = sqlSession.getMapper(ProductMapper.class);
        // 4. 调用方法
        mapper.add(productPlus);
        // 5. 释放资源，提交事务
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void update(ProductPlus productPlus) {
        // 2. 获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        // 3. 获取ProductMapper
        ProductMapper mapper = sqlSession.getMapper(ProductMapper.class);
        // 4. 调用方法
        mapper.update(productPlus);
        // 5. 释放资源，提交事务
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void takeDown(int id) {
        // 2. 获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        // 3. 获取ProductMapper
        ProductMapper mapper = sqlSession.getMapper(ProductMapper.class);
        // 4. 调用方法
        mapper.takeDown(id);
        // 5. 释放资源，提交事务
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public List<ProductPlus> selectAll() {
        // 2. 获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        // 3. 获取ProductMapper
        ProductMapper mapper = sqlSession.getMapper(ProductMapper.class);
        // 4. 调用方法
        List<ProductPlus> products = mapper.selectAll();
        // 5. 释放资源，提交事务
        sqlSession.close();
        return products;
    }
}
