package service;

import model.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
 * Created by y430p on 2017/6/15 0015.
 */
public class UserService {


    public SqlSession createSession() throws IOException{
        String resource = "SqlMapConfig.xml";

        InputStream resourceAsStream = Resources.getResourceAsStream(resource);

        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        return sqlSession;
    }


    //根据id 查询用户记录
    @Test
    public void findUserByIdTest() throws IOException {
        SqlSession sqlSession = createSession();
        try {
            User result =(User) sqlSession.selectOne("test.findUserById", 334);
            System.out.println(result);
        }finally {
            sqlSession.close();
        }


    }

    @Test
    public void findUserByNameTest() throws IOException{
        SqlSession sqlSession = createSession();
        try {
            List<User> list = sqlSession.selectList("findUserByName", "xsy");
            System.out.println(list.size());
        }finally {
            sqlSession.close();
        }
    }


    @Test
    public void insertUser() throws IOException{
        SqlSession sqlSession = createSession();
        User user = new User();
        user.setUsername("xsy");
        user.setSex("男");
        user.setAddress("浅水半岛");
        user.setBirthday(new Date());
        try {
            sqlSession.insert("insertUser",user);
            sqlSession.commit();
            System.out.println(user.getId());
        }finally {
            sqlSession.close();
        }
    }


}
