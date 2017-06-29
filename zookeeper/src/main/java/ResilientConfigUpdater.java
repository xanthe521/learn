import org.apache.zookeeper.KeeperException;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by ayang on 17-6-28.
 */
public class ResilientConfigUpdater {
    public static final String PATH="/config";
    private ActiveKeyValueStore store;
    private Random random = new Random();

    public ResilientConfigUpdater(String hosts) throws IOException, InterruptedException {
        store = new ActiveKeyValueStore();
        store.connect(hosts);
    }
    public void run() throws KeeperException, InterruptedException {
        while (true){
            String value = random.nextInt() + "";
            store.write(PATH,value);
            System.out.printf("Set %s to %s\n",PATH,value);
            TimeUnit.SECONDS.sleep(random.nextInt(10));
        }
    }

    public static void main(String[] args)throws Exception {

        while (true){
            try{
                System.out.println("aaa");
                ResilientConfigUpdater resilientConfigUpdater = new ResilientConfigUpdater("acer");
                resilientConfigUpdater.run();
                System.out.println("done");
            }catch (KeeperException.SessionExpiredException e){

            }catch (KeeperException e){
                e.printStackTrace();
                break;
            }
        }


    }
}
