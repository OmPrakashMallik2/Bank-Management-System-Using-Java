import java.util.ArrayList;
import java.util.Random;

public class Customer {

    private ArrayList<TransactionStatement> transactionStatements;
    private static int serial = 0;
    private final int serialNumber = serial+1;
    private int pinNumber;
    private long accountNumber;
    private String customerName;
    private long dateOfBirth;
    private long aadhaarNumber;

    // contact information
    private long phoneNumber;
    private String emailAddress;
    private String address;
    private double balance;

    public Customer(String customerName, long dateOfBirth, long aadhaarNumber,
                    long phoneNumber, String emailAddress, String address, double balance) {
        serial++;
        this.transactionStatements = new ArrayList<>();
        this.customerName = customerName;
        this.dateOfBirth = dateOfBirth;
        this.aadhaarNumber = aadhaarNumber;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.address = address;
        this.balance = balance;
        this.accountNumber = 2024 * 100 + serialNumber;
        this.pinNumber = (new Random()).nextInt(9000)+1000;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public long getAadhaarNumber() {
        return aadhaarNumber;
    }

    public long getDateOfBirth() {
        return dateOfBirth;
    }

    public int getPinNumber() {
        return pinNumber;
    }

    public double getBalance() {
        return balance;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setPinNumber(int pinNumber) {
        this.pinNumber = pinNumber;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void customerDetails() {
        System.out.println("Customer name: "+customerName);
        System.out.println("Account number: "+accountNumber);
        System.out.println("Pin number: "+pinNumber);
        System.out.println("Date of birth: "+dateOfBirth);
        System.out.println("Aadhaar number: "+aadhaarNumber);
        System.out.println("Mobile: "+phoneNumber);
        System.out.println("Email address: "+emailAddress);
        System.out.println("Address: "+address);
        System.out.println("Available Balance: "+balance);
    }

    public void setTransactionStatements(TransactionStatement transactionStatement) {
        transactionStatements.add(transactionStatement);
    }

    public ArrayList<TransactionStatement> getTransactionStatements() {
        return transactionStatements;
    }
}
