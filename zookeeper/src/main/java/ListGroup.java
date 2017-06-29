import org.apache.zookeeper.KeeperException;

import java.util.List;

/**
 * Created by ayang on 17-6-28.
 */
public class ListGroup extends ConnectionWatcher {
    public void list(String groupName){
        String path = "/"+groupName;
        try {
            List<String> children = zk.getChildren(path, false);
            if (children.isEmpty()) {
                System.out.printf("no members in group %s\n",groupName);
                System.exit(1);
            }
            for (String child : children) {
                System.out.println(child);
            }

        } catch (KeeperException e) {
            e.printStackTrace();
            System.exit(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        ListGroup listGroup = new ListGroup();
        listGroup.connect("ayang-ubuntu");
        listGroup.list("config");
        listGroup.close();
    }
}
