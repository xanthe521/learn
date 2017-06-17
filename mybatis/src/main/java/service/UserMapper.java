package service;

import model.User;

/**
 * Created by ayang on 2017/6/16.
 */
public interface UserMapper {
    public User findUserById(int id);
}
