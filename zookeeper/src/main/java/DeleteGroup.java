import org.apache.zookeeper.KeeperException;

import java.util.List;

/**
 * Created by ayang on 17-6-28.
 */
public class DeleteGroup extends ConnectionWatcher {
    public void delete(String groupName){
        String path = "/" + groupName;
        try {
            List<String> children = zk.getChildren(path, false);
            for (String child : children) {
                zk.delete(path+"/"+child,-1);
            }
            zk.delete(path,-1);
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        DeleteGroup deleteGroup = new DeleteGroup();
        deleteGroup.connect("localhost");
        deleteGroup.delete("zoo");
        deleteGroup.close();
    }
}
