# Bank Management System

## Overview
This Java-based Bank Management System is designed to facilitate secure and efficient banking operations. The system includes features such as account creation, secure login, transaction processing, and account recovery.

## Key Features
- **Account Management:** Unique account creation with random PIN generation.
- **Secure Login:** Robust login system ensuring customer data security.
- **Transaction Operations:** Perform cash withdrawals, check balance, credit to account, and change PIN.
- **User-Friendly Interface:** Menu-driven interface for easy interaction.

## Technologies Used
- Java
- Object-Oriented Programming (OOP)
- LocalDateTime for date and time handling
- Scanner for input processing

## Usage
1. **Create New Account:**
    - User selects the option to create a new account.
    - Inputs personal details such as name, date of birth, Aadhaar number, mobile number, email, address, and initial balance.
    - The system generates a unique account number and a random PIN for the new customer.

2. **Login:**
    - User chooses the option to login and provides the generated account number and PIN.
    - The system validates the credentials and allows access to the account.

3. **Perform Transactions:**
    - User selects transaction options:
        - **Cash Withdrawal:** Enters the withdrawal amount. The system checks if the balance is sufficient and processes the withdrawal, updating the balance and generating a transaction statement.
        - **Check Balance:** Displays the current account balance.
        - **Credit to Account:** Enters the amount to credit. The system updates the balance and generates a transaction statement.
        - **Change PIN:** Enters a new PIN. The system updates the PIN for the account.

4. **View Account Details:**
    - User selects the option to view account details.
    - The system displays the customer's name, account number, PIN, date of birth, Aadhaar number, mobile number, email, address, and available balance.

5. **Transaction Statements:**
    - User chooses to view transaction statements.
    - The system displays a list of transaction statements including serial number, amount, transaction type (debit or credit), available balance after the transaction, and the date of the transaction.

6. **Forgot PIN:**
    - If the user forgets the PIN, they can choose the option to reset it.
    - Options include resetting through account number, date of birth, Aadhaar number, or mobile number.
    - After selecting an option, the system prompts the user to enter the necessary details and then allows them to set a new PIN.

7. **Logout:**
    - User selects the option to logout, ending the current session.

## How to Run
1. Clone the repository.
2. Compile and run the `Main.java` file.

## Project Structure
- `Customer.java`: Class defining customer details and methods.
- `TransactionStatement.java`: Class for transaction statements.
- `BankManagementSystem.java`: Main class containing the banking functionalities.
- `Main.java`: Entry point for the application.

## Output Interface

### Creating a New Account

1. **Choose your option:**
   - Select '1' to create a new account.

2. **Enter your details:**
   - Provide your name, date of birth, Aadhaar number, mobile number, email, address, and initial balance.

3. **Account Created Successfully:**
   - A new account will be created with a unique account number and pin.

### Logging In

1. **Choose your option:**
   - Select '2' to log in to your account.

2. **Enter Account Number and Pin:**
   - Input your account number and pin for authentication.

3. **Access Account Menu:**
   - Upon successful login, you'll have options like cash withdrawal, balance check, account details, etc.

### Forgot Pin

1. **Choose your option:**
   - Select '3' if you forgot your pin.

2. **Reset Options:**
   - Reset your pin using account number, date of birth, Aadhaar number, or mobile number.

3. **Confirmation:**
   - Confirm the pin reset and receive new pin details.

### Exiting the System

1. **Choose your option:**
   - Select '4' to exit the system.

2. **Thank You Message:**
   - Receive a thank you message and exit the system.

### Transactions and Statements

1. **Choose your option:**
   - Inside the account menu, select '6' to view transaction statements.

2. **Transaction Details:**
   - Displayed details include transaction number, amount, statement, available balance, and date.

Feel free to explore various options in the interactive menu to manage your account efficiently.


## Contributing
Contributions are welcome! Feel free to open issues or submit pull requests.

## License
