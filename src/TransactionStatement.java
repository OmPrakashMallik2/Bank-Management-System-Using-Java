import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TransactionStatement {


    static int sn = 0;
    private final int serialNumber = sn + 1;
    private String date;
    private String statement;
    private double amount;
    private double totalBalance;

    public TransactionStatement(String statement, double amount, double totalBalance) {
        sn++;
        this.date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm dd-MM-yyyy"));
        this.statement = statement;
        this.amount = amount;
        this.totalBalance = totalBalance;
    }

    @Override
    public String toString() {
//        return "Transaction" + serialNumber + " - " + date + " - " + statement + " - " + amount + " - " + totalBalance;
        return serialNumber + " - " + amount + " - " + statement + " - Avl Bal: " + totalBalance +" - Date: "+ date;
    }
}
