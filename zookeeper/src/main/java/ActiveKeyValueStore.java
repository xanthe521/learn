import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.data.Stat;

import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

/**
 * Created by ayang on 17-6-28.
 */
public class ActiveKeyValueStore extends ConnectionWatcher {
    private static final Charset CHARSET = Charset.forName("UTF-8");
    public static final int MAX_RETRIES = 20;
    public static final int RETRY_PERIOD_SECONDS = 5;

    public void write(String path,String value) throws InterruptedException, KeeperException {
        int reties = 0;
        while (true){
            try {
                Stat stat = zk.exists(path, false);
                if (stat == null) {
                    zk.create(path,value.getBytes(CHARSET), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
                }else {
                    zk.setData(path,value.getBytes(CHARSET),-1);
                }
                return;
            }catch (KeeperException.SessionExpiredException e){
                throw e;
            }catch (KeeperException e){
                if (reties ++ == MAX_RETRIES) {
                    throw e;
                }else {
                    TimeUnit.SECONDS.sleep(RETRY_PERIOD_SECONDS);
                }
            }

        }

    }

    public String read(String path, Watcher watcher) throws KeeperException, InterruptedException {
        byte[] data = zk.getData(path, watcher, null);
        return new String(data);
    }

}
