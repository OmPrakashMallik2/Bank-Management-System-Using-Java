import java.util.ArrayList;
import java.util.Scanner;

public class BankManagementSystem {

    private ArrayList<Customer> customers;

    public BankManagementSystem() {
        this.customers = new ArrayList<>();
    }

    public void addCustomer(Customer newCustomer) {
        customers.add(newCustomer);
    }

    public void removeCustomer(Customer newCustomer) {
        customers.remove(newCustomer);
    }

    public void menu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("==== Banking Management System ====");

        while (true) {
            System.out.println("-----------------------------------");
            System.out.println("1. Create new account");
            System.out.println("2. Login to account");
            System.out.println("3. Forgot pin");
            System.out.println("4. Exit");

            int choice = readIntegerInput("Choose your option: ", scanner);

            if (choice == 1) {
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


                Customer newCustomer = new Customer(name, dob, aadhaar, mobile, email, address, firstBalance);
                customers.add(newCustomer);

                newCustomer.setTransactionStatements(
                        new TransactionStatement("A New Account Created", firstBalance, newCustomer.getBalance()));

                // successfully created account
                System.out.println("-----------------------------------");
                System.out.println("Your Account is Created Successfully.");
                System.out.println("Account Number: " + newCustomer.getAccountNumber());
                System.out.println("Pin number: " + newCustomer.getPinNumber());
                System.out.println("Note: remember your pin and account number");

            } else if (choice == 2) {
                System.out.println("-----------------------------------");
                int accountNumber = readIntegerInput("Enter account number: ",scanner);

                int pinNumber = readIntegerInput("Enter Pin number: ",scanner);

                // search this account detail in database
                Customer targetCustomer = null;
                for (Customer customer : customers) {
                    if (customer.getAccountNumber() == accountNumber && customer.getPinNumber() == pinNumber) {
                        targetCustomer = customer;
                        break;
                    }
                }

                if (targetCustomer != null) {

                    System.out.println("-----------------------------------");
                    System.out.println("Welcome " + targetCustomer.getCustomerName() + " !");

                    while (true) {
                        System.out.println("-----------------------------------");
                        System.out.println("1. Cash withdrawal");
                        System.out.println("2. Check balance");
                        System.out.println("3. Account details");
                        System.out.println("4. Credit to account");
                        System.out.println("5. Change Pin");
                        System.out.println("6. Transition statements");
                        System.out.println("7. Logout");
                        int choice2 = readIntegerInput("Choose option: ",scanner);

                        if (choice2 == 1) {
                            System.out.println("-----------------------------------");
                            double amount = readDoubleInput("Enter Amount: ", scanner);
                            if (amount > targetCustomer.getBalance()) {
                                System.out.println("Insufficient balance in your account");
                            } else {
                                targetCustomer.setBalance(targetCustomer.getBalance() - amount);
                                targetCustomer.setTransactionStatements(
                                        new TransactionStatement("Debited from your account", amount, targetCustomer.getBalance()));
                                System.out.println(
                                        "You Debited " + amount + " from account " + targetCustomer.getAccountNumber());
                                System.out.println("Available Balance: " + targetCustomer.getBalance());
                            }
                        } else if (choice2 == 2) {
                            System.out.println("-----------------------------------");
                            System.out.println("Available Balance: " + targetCustomer.getBalance());
                        } else if (choice2 == 3) {
                            System.out.println("-----------------------------------");
                            targetCustomer.customerDetails();
                        } else if (choice2 == 4) {
                            System.out.println("-----------------------------------");
                            double amount = readDoubleInput("Enter Amount: ", scanner);
                            targetCustomer.setBalance(targetCustomer.getBalance() + amount);
                            targetCustomer.setTransactionStatements(
                                    new TransactionStatement("Credited to your account", amount, targetCustomer.getBalance()));
                            System.out.println(
                                    "You Credited " + amount + " To account " + targetCustomer.getAccountNumber());
                            System.out.println("Available Balance: " + targetCustomer.getBalance());
                        } else if (choice2 == 5) {
                            System.out.println("-----------------------------------");
                            int newPin = readIntegerInput("Enter new Pin: ", scanner);
                            targetCustomer.setPinNumber(newPin);

                        } else if (choice2 == 6) {
                            System.out.println("-----------------------------------");
                            // Transaction statements code
                            System.out.println("Transactions: ");
                            for (TransactionStatement tr : targetCustomer.getTransactionStatements()) {
                                System.out.println(tr.toString());
                            }

                        } else if (choice2 == 7) {
                            break;
                        } else {
                            System.out.println("Enter the Valid option");
                        }
                    }
                } else {
                    System.out.println("-----------------------------------");
                    System.out.println("Invalid account number and pin. try again!");
                }

            } else if (choice == 3) {

                // forget pin code
                while (true) {
                    System.out.println("-----------------------------------");
                    System.out.println("Reset pin through ?");
                    System.out.println("1. Account number");
                    System.out.println("2. Date of birth");
                    System.out.println("3. Aadhaar number");
                    System.out.println("4. Mobile number");
                    System.out.println("5. Exit");
                    int choice3 = readIntegerInput("Choose an option: ", scanner);

                    if (choice3 == 1) {
                        System.out.println("-----------------------------------");
                        long inputAccountNumber = readLongInput("Enter your Account number: ", scanner);
                        Customer targetCustomer = null;
                        for (Customer customer : customers) {
                            if (customer.getAccountNumber() == inputAccountNumber) {
                                targetCustomer = customer;
                                break;
                            }
                        }
                        if (targetCustomer != null) {
                            System.out.println("Account found: " + targetCustomer.getCustomerName());
                            int newPin = readIntegerInput("Set new Pin: ", scanner);
                            targetCustomer.setPinNumber(newPin);
                            System.out.println("Pin changed successfully.\nYour Account number is "
                                    + targetCustomer.getAccountNumber() + "\nYour new pin is "
                                    + targetCustomer.getPinNumber());
                            break;
                        } else {
                            System.out.println("-----------------------------------");
                            System.out.println("Account not found. try another way.");
                        }
                    } else if (choice3 == 2) {
                        System.out.println("-----------------------------------");
                        long dob = readLongInput("Enter your DOB (DDMMYYYY): ", scanner);
                        Customer targetCustomer = null;
                        for (Customer customer : customers) {
                            if (customer.getDateOfBirth() == dob) {
                                targetCustomer = customer;
                                break;
                            }
                        }
                        if (targetCustomer != null) {
                            System.out.println("Account found: " + targetCustomer.getCustomerName());
                            int newPin = readIntegerInput("Set new Pin: ", scanner);
                            targetCustomer.setPinNumber(newPin);
                            System.out.println("Pin changed successfully.\nYour Account number is "
                                    + targetCustomer.getAccountNumber() + "\nYour new pin is "
                                    + targetCustomer.getPinNumber());
                            //
                            break;
                        } else {
                            System.out.println("-----------------------------------");
                            System.out.println("Account not found. try another way.");
                        }
                    } else if (choice3 == 3) {
                        System.out.println("-----------------------------------");
                        long aadhaar = readLongInput("Enter your Aadhaar number: ",scanner);
                        Customer targetCustomer = null;
                        for (Customer customer : customers) {
                            if (customer.getAadhaarNumber() == aadhaar) {
                                targetCustomer = customer;
                                break;
                            }
                        }
                        if (targetCustomer != null) {
                            System.out.println("Account found: " + targetCustomer.getCustomerName());
                            int newPin = readIntegerInput("Set new Pin: ", scanner);
                            targetCustomer.setPinNumber(newPin);
                            System.out.println("Pin changed successfully.\nYour Account number is "
                                    + targetCustomer.getAccountNumber() + "\nYour new pin is "
                                    + targetCustomer.getPinNumber());
                            //
                            break;
                        } else {
                            System.out.println("-----------------------------------");
                            System.out.println("Account not found. try another way.");
                        }
                    } else if (choice3 == 4) {
                        System.out.println("-----------------------------------");
                        long mobile = readLongInput("Enter registered mobile number: ", scanner);
                        Customer targetCustomer = null;
                        for (Customer customer : customers) {
                            if (customer.getPhoneNumber() == mobile) {
                                targetCustomer = customer;
                                break;
                            }
                        }
                        if (targetCustomer != null) {
                            System.out.println("Account found: " + targetCustomer.getCustomerName());
                            int newPin = readIntegerInput("Set new Pin: ", scanner);
                            targetCustomer.setPinNumber(newPin);
                            System.out.println("Pin changed successfully.\nYour Account number is "
                                    + targetCustomer.getAccountNumber() + "\nYour new pin is "
                                    + targetCustomer.getPinNumber());
                            //
                            break;
                        } else {
                            System.out.println("-----------------------------------");
                            System.out.println("Account not found. try another way.");
                        }
                    } else if (choice3 == 5) {
                        break;
                    } else {
                        System.out.println("-----------------------------------");
                        System.out.println("Enter valid option");
                    }
                }

            } else if (choice == 4) {
                break;
            } else {
                System.out.println("-----------------------------------");
                System.out.println("Enter the Valid option");
            }
        }

        System.out.println("Thank you! visit again.");
    }

    public int readIntegerInput(String prompt, Scanner scanner){
        int input = 0;
        while(true){
            try {
                System.out.print(prompt);
                input = scanner.nextInt();
                break;
            } catch (Exception ex){
                System.out.println("Invalid input, try again!");
                scanner.nextLine();
            }
        }
        return input;
    }
    public double readDoubleInput(String prompt, Scanner scanner){
        double input = 0;
        while(true){
            try {
                System.out.print(prompt);
                input = scanner.nextDouble();
                break;
            } catch (Exception ex){
                System.out.println("Invalid input, try again!");
                scanner.nextLine();
            }
        }
        return input;
    }
    public long readLongInput(String prompt, Scanner scanner){
        long input = 0;
        while(true){
            try {
                System.out.print(prompt);
                input = scanner.nextLong();
                break;
            } catch (Exception ex){
                System.out.println("Invalid input, try again!");
                scanner.nextLine();
            }
        }
        return input;
    }
}
