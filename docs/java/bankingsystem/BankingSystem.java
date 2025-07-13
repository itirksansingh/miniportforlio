import java.io.*;
import java.util.*;

class Account {
    String name;
    String accNumber;
    double balance;

    Account(String name, String accNumber, double balance) {
        this.name = name;
        this.accNumber = accNumber;
        this.balance = balance;
    }

    void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("₹" + amount + " deposited successfully.");
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("₹" + amount + " withdrawn successfully.");
        } else {
            System.out.println("Insufficient balance or invalid amount.");
        }
    }

    void display() {
        System.out.println("\n--- Account Info ---");
        System.out.println("Name: " + name);
        System.out.println("Account Number: " + accNumber);
        System.out.println("Balance: ₹" + balance);
    }

    String toFileString() {
        return name + "," + accNumber + "," + balance;
    }

    static Account fromFileString(String line) {
        String[] parts = line.split(",");
        return new Account(parts[0], parts[1], Double.parseDouble(parts[2]));
    }
}

public class BankingSystem {
    static final String FILE_NAME = "accounts.txt";
    static ArrayList<Account> accounts = new ArrayList<>();

    public static void main(String[] args) {
        loadAccounts();

        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- Banking System Menu ---");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Display Account Info");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter account number: ");
                    String accNumber = sc.nextLine();
                    if (findAccount(accNumber) == null) {
                        Account newAcc = new Account(name, accNumber, 0);
                        accounts.add(newAcc);
                        saveAccounts();
                        System.out.println("Account created successfully.");
                    } else {
                        System.out.println("Account already exists.");
                    }
                    break;

                case 2:
                    System.out.print("Enter account number: ");
                    accNumber = sc.nextLine();
                    Account acc = findAccount(accNumber);
                    if (acc != null) {
                        System.out.print("Enter amount to deposit: ");
                        double amount = sc.nextDouble();
                        acc.deposit(amount);
                        saveAccounts();
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;

                case 3:
                    System.out.print("Enter account number: ");
                    accNumber = sc.nextLine();
                    acc = findAccount(accNumber);
                    if (acc != null) {
                        System.out.print("Enter amount to withdraw: ");
                        double amount = sc.nextDouble();
                        acc.withdraw(amount);
                        saveAccounts();
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;

                case 4:
                    System.out.print("Enter account number: ");
                    accNumber = sc.nextLine();
                    acc = findAccount(accNumber);
                    if (acc != null) {
                        acc.display();
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;

                case 5:
                    System.out.println("Thank you! Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 5);

        sc.close();
    }

    static void loadAccounts() {
        try {
            File file = new File(FILE_NAME);
            if (!file.exists()) {
                file.createNewFile();
            }

            BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME));
            String line;
            while ((line = reader.readLine()) != null) {
                Account acc = Account.fromFileString(line);
                accounts.add(acc);
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Error loading accounts: " + e.getMessage());
        }
    }

    static void saveAccounts() {
        try {
            FileWriter writer = new FileWriter(FILE_NAME);
            for (Account acc : accounts) {
                writer.write(acc.toFileString() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving accounts: " + e.getMessage());
        }
    }

    static Account findAccount(String accNumber) {
        for (Account acc : accounts) {
            if (acc.accNumber.equals(accNumber)) {
                return acc;
            }
        }
        return null;
    }
}
