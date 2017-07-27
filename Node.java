import java.util.ArrayList;
import java.util.Set;

public interface Node {

    /** {@code followees[i]} is true if and only if this node follows node {@code i} */
    void setFollowees(boolean[] followees);

    /** initialize proposal list of transactions */
    void setPendingTransaction(Set<Transaction> pendingTransactions);

    /**
     * @return proposals to send to my followers. REMEMBER: After final round, behavior of
     *         {@code getProposals} changes and it should return the transactions upon which
     *         consensus has been reached.
     */
    Set<Transaction> sendToFollowers();

    /** receive candidates from other nodes. */
    void receiveFromFollowees(Set<Candidate> candidates);
}