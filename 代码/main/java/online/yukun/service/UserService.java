package online.yukun.service;

import online.yukun.pojo.User;

import java.util.List;

public interface UserService {
    User selectById(int id);

    boolean selectByUsername(String username);

    void add(User user);

    int activate(String activeCode);

    User selectByUsernameAndPassword(String username, String password);

    void update(User user);

    List<User> selectAll();

    void deleteById(int id);

    void deleteByIds(int[] ids);

    void freeze(int id);

    void unfreeze(int id);
}
