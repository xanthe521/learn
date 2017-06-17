package dao;

import model.User;

/**
 * Created by ayang on 2017/6/16.
 */
public interface UserDao {
    public User findUserById(int id);
}
