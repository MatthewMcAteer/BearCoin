import java.security.PublicKey;

public class BlockHandler {
    private BlockChain blockChain;

    // assume blockChain has the genesis block
    public BlockHandler(BlockChain blockChain) {
        this.blockChain = blockChain;
    }
    
    public boolean processBlock(Block block) {
        if (block == null)
            return false;
        return blockChain.addBlock(block);
    }

    public Block createBlock(PublicKey myAddress) {
        Block parent = blockChain.getMaxHeightBlock();
        byte[] parentHash = parent.getHash();
        Block current = new Block(parentHash, myAddress);
        UTXOPool uPool = blockChain.getMaxHeightUTXOPool();
        TransactionPool txPool = blockChain.getTransactionPool();
        TxHandler handler = new TxHandler(uPool);
        Transaction[] txs = txPool.getTransactions().toArray(new Transaction[0]);
        Transaction[] rTxs = handler.handleTxs(txs);
        for (int i = 0; i < rTxs.length; i++)
            current.addTransaction(rTxs[i]);

        current.finalize();
        if (blockChain.addBlock(current))
            return current;
        else
            return null;
    }

    public void processTx(Transaction tx) {
        blockChain.addTransaction(tx);
    }
}
