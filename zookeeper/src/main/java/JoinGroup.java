import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * Created by ayang on 17-6-28.
 */
public class JoinGroup extends ConnectionWatcher  {
    public void join(String groupName,String memberName) throws KeeperException, InterruptedException {
        String path = "/"+groupName+"/"+memberName;
        String createdPath = zk.create(path, null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
        System.out.println("created "+createdPath);
    }

    public static void main(String[] args) throws Exception {
        JoinGroup joinGroup = new JoinGroup();
        joinGroup.connect("localhost");
        joinGroup.join("zoo","teseMember");
        Thread.sleep(Long.MAX_VALUE);
    }


}

