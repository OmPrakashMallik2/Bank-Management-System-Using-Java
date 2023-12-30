import java.util.Date;

public class TransactionStatement {

    static int sn = 0;
    private final int serialNumber = sn + 1;
    private Date date;
    private String statement;
    private long amount;
    private double totalBalance;

    public TransactionStatement(String statement, long amount, double totalBalance) {
        sn++;
        this.date = new Date();
        this.statement = statement;
        this.amount = amount;
        this.totalBalance = totalBalance;
    }

    @Override
    public String toString() {
        return "Transaction" + serialNumber + " - " + date + " - " + statement + " - " + amount + " - " + totalBalance;
    }
}
