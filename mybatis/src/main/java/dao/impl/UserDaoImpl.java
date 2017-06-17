package dao.impl;

import dao.UserDao;
import model.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

/**
 * Created by ayang on 2017/6/16.
 */
public class UserDaoImpl implements UserDao{
    private SqlSessionFactory sqlSessionFactory;

    public UserDaoImpl(SqlSessionFactory sqlSessionFactory){
        this.sqlSessionFactory = sqlSessionFactory;
    }


    public User findUserById(int id) {
        SqlSession sqlSession = this.sqlSessionFactory.openSession();
        User user = (User) sqlSession.selectOne("test.findUserById", id);
        sqlSession.close();
        return user;
    }
}
