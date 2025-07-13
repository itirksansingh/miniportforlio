import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

class Account {
    String name, accNumber;
    double balance;

    Account(String name, String accNumber, double balance) {
        this.name = name;
        this.accNumber = accNumber;
        this.balance = balance;
    }

    String toFileString() {
        return name + "," + accNumber + "," + balance;
    }

    static Account fromFileString(String line) {
        String[] parts = line.split(",");
        return new Account(parts[0], parts[1], Double.parseDouble(parts[2]));
    }
}

public class BankingSystemGUI extends JFrame {
    ArrayList<Account> accounts = new ArrayList<>();
    final String FILE_NAME = "accounts.txt";

    JTextField nameField = new JTextField(15);
    JTextField accField = new JTextField(15);
    JTextField amountField = new JTextField(15);
    JTextArea output = new JTextArea(8, 30);

    public BankingSystemGUI() {
        setTitle("Banking System");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(450, 400);
        setLayout(new BorderLayout());

        loadAccounts();

        JPanel panel = new JPanel(new GridLayout(6, 2, 5, 5));

        panel.add(new JLabel("Name:"));
        panel.add(nameField);

        panel.add(new JLabel("Account Number:"));
        panel.add(accField);

        panel.add(new JLabel("Amount:"));
        panel.add(amountField);

        JButton createBtn = new JButton("Create Account");
        JButton depositBtn = new JButton("Deposit");
        JButton withdrawBtn = new JButton("Withdraw");
        JButton displayBtn = new JButton("Display Info");

        panel.add(createBtn);
        panel.add(depositBtn);
        panel.add(withdrawBtn);
        panel.add(displayBtn);

        add(panel, BorderLayout.CENTER);
        add(new JScrollPane(output), BorderLayout.SOUTH);

        createBtn.addActionListener(e -> createAccount());
        depositBtn.addActionListener(e -> deposit());
        withdrawBtn.addActionListener(e -> withdraw());
        displayBtn.addActionListener(e -> displayInfo());
    }

    Account findAccount(String accNumber) {
        for (Account acc : accounts) {
            if (acc.accNumber.equals(accNumber)) return acc;
        }
        return null;
    }

    void createAccount() {
        String name = nameField.getText();
        String accNo = accField.getText();
        if (findAccount(accNo) != null) {
            output.setText("Account already exists.");
        } else {
            accounts.add(new Account(name, accNo, 0));
            saveAccounts();
            output.setText("Account created successfully.");
        }
    }

    void deposit() {
        String accNo = accField.getText();
        Account acc = findAccount(accNo);
        if (acc != null) {
            try {
                double amt = Double.parseDouble(amountField.getText());
                acc.balance += amt;
                saveAccounts();
                output.setText("₹" + amt + " deposited to account " + accNo);
            } catch (Exception ex) {
                output.setText("Invalid amount.");
            }
        } else {
            output.setText("Account not found.");
        }
    }

    void withdraw() {
        String accNo = accField.getText();
        Account acc = findAccount(accNo);
        if (acc != null) {
            try {
                double amt = Double.parseDouble(amountField.getText());
                if (amt > acc.balance) {
                    output.setText("Insufficient balance.");
                } else {
                    acc.balance -= amt;
                    saveAccounts();
                    output.setText("₹" + amt + " withdrawn from account " + accNo);
                }
            } catch (Exception ex) {
                output.setText("Invalid amount.");
            }
        } else {
            output.setText("Account not found.");
        }
    }

    void displayInfo() {
        String accNo = accField.getText();
        Account acc = findAccount(accNo);
        if (acc != null) {
            output.setText("Account Info:\nName: " + acc.name + "\nAccount No: " + acc.accNumber + "\nBalance: ₹" + acc.balance);
        } else {
            output.setText("Account not found.");
        }
    }

    void loadAccounts() {
        try {
            File file = new File(FILE_NAME);
            if (!file.exists()) return;
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                accounts.add(Account.fromFileString(line));
            }
            reader.close();
        } catch (Exception e) {
            output.setText("Error loading accounts.");
        }
    }

    void saveAccounts() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME));
            for (Account acc : accounts) {
                writer.write(acc.toFileString());
                writer.newLine();
            }
            writer.close();
        } catch (Exception e) {
            output.setText("Error saving accounts.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new BankingSystemGUI().setVisible(true);
        });
    }
}
