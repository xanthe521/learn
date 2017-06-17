import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import dao.UserDao;
import dao.impl.UserDaoImpl;
import model.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import service.UserMapper;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by ayang on 2017/6/16.
 */
public class UserDaoImplTest {
    public static void main(String[] args)throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        UserDao userDao = new UserDaoImpl(sqlSessionFactory);
        User user = userDao.findUserById(334);
        System.out.println(user);

        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        user = mapper.findUserById(334);
        System.out.println(user);
    }

    public static SqlSessionFactory getSqlSessionFactory() throws IOException{
        String resource = "SqlMapConfig.xml";
        InputStream resourceAsStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        return sqlSessionFactory;
    }
}
