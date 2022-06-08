package online.yukun.mapper;

import online.yukun.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserMapper {
    @Select("select * from user where id = #{id}")
    User selectById(int id);

    @Select("select * from user where username=#{username} and password=#{password} and (state = 1 or 2)")
    User select(@Param("username") String username, @Param("password") String password);

    @Select("select * from user where username = #{username}")
    User selectByUsername(String username);

    @Insert("insert into user(name,username,password,gender,email,telephone,address,introduce,activecode,state,role)" +
            " values (#{name},#{username},#{password},#{gender},#{email}," +
            "#{telephone},#{address},#{introduce},#{activeCode},#{state},#{role})")
    void add(User user);

    @Update("update user set state = 1 where activecode = #{activeCode};")
    int activate(String activeCode);

    @Update("update user set name = #{name},username=#{username},gender=#{gender}," +
            "telephone=#{telephone},address=#{address},introduce=#{introduce} where id=#{id};")
    void update(User user);

    @Select("select * from user;")
    List<User> selectAll();

    @Delete("delete from user where id=#{id};")
    void deleteById(int id);

    void deleteByIds(@Param("ids") int[] ids);

    @Update("update user set state = 2 where id=#{id};")
    void freeze(int id);

    @Update("update user set state = 1 where id=#{id};")
    void unfreeze(int id);
}
