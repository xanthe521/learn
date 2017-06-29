import org.apache.zookeeper.server.quorum.FastLeaderElection;
import org.apache.zookeeper.server.quorum.QuorumPeer;

import javax.security.sasl.SaslException;

/**
 * Created by ayang on 17-6-28.
 */
public class Main {
    public static void main(String[] args) throws SaslException {
        QuorumPeer quorumPeer = QuorumPeer.testingQuorumPeer();
        quorumPeer.run();
        quorumPeer.startLeaderElection();
        Class<FastLeaderElection> fastLeaderElectionClass = FastLeaderElection.class;
    }
}
