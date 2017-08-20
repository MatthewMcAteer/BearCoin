import java.util.ArrayList;
import java.util.Set;

public interface Node {

    void setFollowees(boolean[] followees);

    void setPendingTransaction(Set<Transaction> pendingTransactions);

    Set<Transaction> sendToFollowers();

    void receiveFromFollowees(Set<Candidate> candidates);
}
