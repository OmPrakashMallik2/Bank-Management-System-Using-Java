import java.sql.*;
import java.util.Random;
import java.util.Scanner;

public class BankManagementSystem {
    final static String url = "jdbc:mysql://localhost:3306/bank_db";
    final static String username = "root";
    final static String password = "java@12345";

    public static ResultSet getDetails(Connection connection, int accountNumber, int pinNumber) {
        ResultSet resultSet = null;
        try {
            String query = "SELECT * FROM customer WHERE account_number = " + accountNumber + " AND pinNumber = "
                    + pinNumber + ";";
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }
        return resultSet;
    }

    public static ResultSet getDetailsTransactions(Connection connection, int accountNumber, int pinNumber) {
        ResultSet resultSet = null;
        try {
            String query = "SELECT * FROM transactions WHERE account_number = " + accountNumber + " AND pin = "
                    + pinNumber + ";";
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }
        return resultSet;
    }

    public static void addDetailsTransactions(long account_number, int pin, String statement_text, double amount,
            double balance, Connection connection) {
        int resultSet = 0;
        try {
            String query = "INSERT INTO transactions (account_number, pin,email, statement_text, amount, balance, transaction_date) VALUES ("
                    + account_number + ", " + pin + ", 'email not fount', '" + statement_text + "', " + amount + ", "
                    + balance + ", CURRENT_DATE);";
            Statement statement = connection.createStatement();
            resultSet = statement.executeUpdate(query);
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }
    }

    public static void forgotPin(Scanner scanner, Connection connection) {
        try {
            // forget pin code
            while (true) {
                System.out.println("-----------------------------------");
                System.out.println("Reset pin through ?");
                System.out.println("1. Account number");
                System.out.println("2. Date of birth");
                System.out.println("3. Aadhaar number");
                System.out.println("4. Mobile number");
                System.out.println("0. Exit");
                int choice3 = readIntegerInput("Choose an option: ", scanner);

                if (choice3 == 1) {
                    System.out.println("-----------------------------------");
                    long inputAccountNumber = readLongInput("Enter your Account number: ", scanner);
                    // code to search in database
                    String query = "SELECT * FROM customer WHERE  account_number = " + inputAccountNumber + ";";
                    Statement statement = connection.createStatement();
                    ResultSet rs1 = statement.executeQuery(query);
                    if (rs1.next()) {
                        System.out.println("Account found: " + rs1.getString(2));
                        System.out.println("Your Account number is "
                                + rs1.getLong(1) + "\nYour pin number is "
                                + rs1.getInt(9));
                        break;
                    } else {
                        System.out.println("-----------------------------------");
                        System.out.println("Account not found. try another way.");
                    }
                } else if (choice3 == 2) {
                    System.out.println("-----------------------------------");
                    long dob = readLongInput("Enter your DOB (DDMMYYYY): ", scanner);
                    // code to search in database
                    String query = "SELECT * FROM customer WHERE  dob = " + dob + ";";
                    Statement statement = connection.createStatement();
                    ResultSet rs2 = statement.executeQuery(query);
                    if (rs2.next()) {
                        System.out.println("Account found: " + rs2.getString(2));
                        System.out.println("Your Account number is "
                                + rs2.getLong(1) + "\nYour pin number is "
                                + rs2.getInt(9));
                        break;
                    } else {
                        System.out.println("-----------------------------------");
                        System.out.println("Account not found. try another way.");
                    }
                } else if (choice3 == 3) {
                    System.out.println("-----------------------------------");
                    long aadhaar = readLongInput("Enter your Aadhaar number: ", scanner);
                    // code to search in database
                    String query = "SELECT * FROM customer WHERE  aadhaarNumber = " + aadhaar + ";";
                    Statement statement = connection.createStatement();
                    ResultSet rs3 = statement.executeQuery(query);
                    if (rs3.next()) {
                        System.out.println("Account found: " + rs3.getString(2));
                        System.out.println("Your Account number is "
                                + rs3.getLong(1) + "\nYour pin number is "
                                + rs3.getInt(9));
                        break;
                    } else {
                        System.out.println("-----------------------------------");
                        System.out.println("Account not found. try another way.");
                    }
                } else if (choice3 == 4) {
                    System.out.println("-----------------------------------");
                    long mobile = readLongInput("Enter registered mobile number: ", scanner);
                    // code to search in database
                    String query = "SELECT * FROM customer WHERE   mobileNumber = " + mobile + ";";
                    Statement statement = connection.createStatement();
                    ResultSet rs3 = statement.executeQuery(query);
                    if (rs3.next()) {
                        System.out.println("Account found: " + rs3.getString(2));
                        System.out.println("Your Account number is "
                                + rs3.getLong(1) + "\nYour pin number is "
                                + rs3.getInt(9));
                        break;
                    } else {
                        System.out.println("-----------------------------------");
                        System.out.println("Account not found. try another way.");
                    }
                } else if (choice3 == 0) {
                    break;
                } else {
                    System.out.println("-----------------------------------");
                    System.out.println("Enter valid option");
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void logInAccount(Scanner scanner, Connection connection) {
        // (account_number, name, dob, aadhaarNumber, mobileNumber, email, address,
        // balance, pinNumber)
        try {
            System.out.println("-----------------------------------");
            int accountNumber = readIntegerInput("Enter account number: ", scanner);

            int pinNumber = readIntegerInput("Enter Pin number: ", scanner);

            String query = "SELECT * FROM customer WHERE account_number = " + accountNumber + " AND pinNumber = "
                    + pinNumber + ";";

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            if (resultSet.next()) {
                System.out.println("-----------------------------------");
                System.out.println("Welcome " + resultSet.getString(2) + " !");

                while (true) {
                    System.out.println("-----------------------------------");
                    System.out.println("1. Cash withdrawal");
                    System.out.println("2. Check balance");
                    System.out.println("3. Account details");
                    System.out.println("4. Credit to account");
                    System.out.println("5. Transition statements");
                    System.out.println("6. Change Pin");
                    System.out.println("0. Logout");
                    int choice2 = readIntegerInput("Choose option: ", scanner);

                    if (choice2 == 1) {
                        System.out.println("-----------------------------------");
                        double amount = readDoubleInput("Enter Amount: ", scanner);
                        ResultSet rs11 = getDetails(connection, accountNumber, pinNumber);
                        if (rs11.next() && amount > rs11.getDouble(8)) {
                            System.out.println("Insufficient balance in your account");
                        } else {
                            double balanceLeft = rs11.getDouble(8) - amount;
                            String query1 = "UPDATE customer SET balance = " + balanceLeft + " WHERE account_number = "
                                    + accountNumber + " AND pinNumber = " + pinNumber + ";";
                            int done = statement.executeUpdate(query1);
                            ResultSet rs12 = getDetails(connection, accountNumber, pinNumber);
                            if (rs12.next()) {
                                System.out.println("You Debited " + amount + " from account " + rs12.getInt(1));
                                System.out.println("Available Balance: " + rs12.getDouble(8));
                            }
                            // add transaction row
                            addDetailsTransactions(accountNumber, pinNumber, "Debited from account", amount,
                                    rs12.getDouble(8), connection);
                        }
                    } else if (choice2 == 2) {
                        ResultSet rs2 = getDetails(connection, accountNumber, pinNumber);
                        if (rs2.next()) {
                            System.out.println("-----------------------------------");
                            System.out.println("Available Balance: " + rs2.getDouble(8));
                        }
                    } else if (choice2 == 3) {
                        ResultSet rs3 = getDetails(connection, accountNumber, pinNumber);
                        if (rs3.next()) {
                            System.out.println("-----------------------------------");
                            System.out.println("Customer name: " + rs3.getString(2)); // Use getString for varchar
                            System.out.println("Account number: " + rs3.getInt(1));
                            System.out.println("Pin number: " + rs3.getInt(9));
                            System.out.println("Date of birth: " + rs3.getLong(3)); // Assuming dob is a bigint
                            System.out.println("Aadhaar number: " + rs3.getLong(4)); // Assuming aadhaarNumber is a
                                                                                     // bigint
                            System.out.println("Mobile: " + rs3.getLong(5)); // Assuming mobileNumber is a bigint
                            System.out.println("Email address: " + rs3.getString(6));
                            System.out.println("Address: " + rs3.getString(7));
                            System.out.println("Available Balance: " + rs3.getDouble(8));
                        }
                    } else if (choice2 == 4) {
                        System.out.println("-----------------------------------");
                        double amount = readDoubleInput("Enter Amount: ", scanner);
                        ResultSet rs41 = getDetails(connection, accountNumber, pinNumber);

                        if (rs41.next()) {
                            double balanceLeft = rs41.getDouble(8) + amount;
                            String query4 = "UPDATE customer SET balance = " + balanceLeft + " WHERE account_number = "
                                    + accountNumber + " AND pinNumber = " + pinNumber + ";";
                            int done = statement.executeUpdate(query4);
                            ResultSet rs42 = getDetails(connection, accountNumber, pinNumber);
                            if (rs42.next()) {
                                System.out.println("You Credited " + amount + " to your account " + rs42.getInt(1));
                                System.out.println("Available Balance: " + rs42.getDouble(8));
                            }
                            // add transaction row
                            addDetailsTransactions(accountNumber, pinNumber, "Credited to account", amount,
                                    rs42.getDouble(8), connection);
                        }
                    } else if (choice2 == 5) {
                        // transaction statements

                        System.out.println("-----------------------------------");
                        ResultSet rs5 = getDetailsTransactions(connection, accountNumber, pinNumber);
                        int count = 1;
                        while (rs5.next()) {
                            System.out.print(count + ". ");
                            System.out.print(rs5.getString(4));
                            System.out.print(" | Amount: " + rs5.getDouble(5));
                            System.out.print(" | Total Balance: " + rs5.getDouble(6));
                            System.out.print(" | Date: " + rs5.getDate(7));
                            System.out.println();
                            count++;
                        }
                    } else if (choice2 == 6) {
                        // change pin
                        System.out.println("-----------------------------------");
                        int newPin = readIntegerInput("Enter new Pin: ", scanner);

                        if (newPin < 1000 || newPin > 9999) {
                            System.out.println("Enter Four Digits Pin!");
                        } else {
                            ResultSet rs6 = getDetails(connection, accountNumber, newPin);
                            // pin change in customer table
                            String query61 = "UPDATE customer SET pinNumber = " + newPin + " WHERE account_number = "
                                    + accountNumber + " AND pinNumber = " + pinNumber + ";";
                            statement.executeUpdate(query61);
                            // pin change in transaction table
                            String query62 = "UPDATE transactions SET pin = " + newPin + " WHERE account_number = "
                                    + accountNumber + " AND pin = " + pinNumber + ";";
                            statement.executeUpdate(query62);
                            System.out.println("-----------------------------------");
                            System.out.println("Pin Changed Successfully.");
                            System.out.println("You New Pin is " + newPin);
                            pinNumber = newPin;
                        }
                    } else if (choice2 == 0) {
                        // logout
                        return;
                    } else {
                        System.out.println("-----------------------------------");
                        System.out.println("Invalid input, try again!");
                    }
                }

            } else {
                System.out.println("-----------------------------------");
                System.out.println("Invalid account number and pin. try again!");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void newAccount(Scanner scanner, Connection connection) {
        try {
            System.out.println("-----------------------------------");
            System.out.println("Create you account ");

            System.out.print("Name : ");
            String name = scanner.next();
            scanner.nextLine(); // new line

            long dob = readLongInput("DOB (DDMMYYYY) : ", scanner);
            long aadhaar = readLongInput("Aadhaar Number : ", scanner);
            long mobile = readLongInput("Mobile Number : ", scanner);

            System.out.print("Email : ");
            String email = scanner.next();
            scanner.nextLine(); // new line

            System.out.print("Address : ");
            String address = scanner.next();
            scanner.nextLine(); // new line

            double firstBalance = readDoubleInput("First credit balance : ", scanner);

            int pinNumber = (new Random()).nextInt(9000) + 1000;

            // sql query to add account into database
            String query = "INSERT INTO Customer (account_number, name, dob, aadhaarNumber, mobileNumber, email, address, balance, pinNumber) VALUES ( "
                    +
                    dob + ", '" + name + "', " + dob + ", " + aadhaar + ", " + mobile + ", '" + email + "', '" + address
                    + "', " + firstBalance + ", " + pinNumber + ");";

            Statement statement = connection.createStatement();
            int insertAccount = statement.executeUpdate(query);

            // code to add a transaction row
            addDetailsTransactions(dob, pinNumber, "Created new account", firstBalance, firstBalance, connection);

            // successfully created account
            System.out.println("-----------------------------------");
            System.out.println("Your Account is Created Successfully.");
            System.out.println("Account Number: " + dob);
            System.out.println("Pin number: " + pinNumber);
            System.out.println("Note: remember your pin and account number");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) throws SQLException {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            Scanner scanner = new Scanner(System.in);
            System.out.println("==== Banking Management System ====");

            while (true) {
                System.out.println("-----------------------------------");
                System.out.println("1. Create new account");
                System.out.println("2. Login to account");
                System.out.println("3. Forgot pin");
                System.out.println("0. Exit");

                int choice = readIntegerInput("Choose your option: ", scanner);

                if (choice == 1) {
                    newAccount(scanner, connection);
                } else if (choice == 2) {
                    logInAccount(scanner, connection);
                }

                else if (choice == 3) {
                    forgotPin(scanner, connection);
                } else if (choice == 0) {
                    break;
                } else {
                    System.out.println("-----------------------------------");
                    System.out.println("Enter the Valid option");
                }
            }

            System.out.println("Thank you! visit again.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public static int readIntegerInput(String prompt, Scanner scanner) {
        int input = 0;
        while (true) {
            try {
                System.out.print(prompt);
                input = scanner.nextInt();
                break;
            } catch (Exception ex) {
                System.out.println("Invalid input, try again!");
                scanner.nextLine();
            }
        }
        return input;
    }

    public static double readDoubleInput(String prompt, Scanner scanner) {
        double input = 0;
        while (true) {
            try {
                System.out.print(prompt);
                input = scanner.nextDouble();
                break;
            } catch (Exception ex) {
                System.out.println("Invalid input, try again!");
                scanner.nextLine();
            }
        }
        return input;
    }

    public static long readLongInput(String prompt, Scanner scanner) {
        long input = 0;
        while (true) {
            try {
                System.out.print(prompt);
                input = scanner.nextLong();
                break;
            } catch (Exception ex) {
                System.out.println("Invalid input, try again!");
                scanner.nextLine();
            }
        }
        return input;
    }
}
