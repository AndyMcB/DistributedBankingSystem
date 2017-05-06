package bank;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by AMCBR on 13/02/2017.
 */
public class Account implements Serializable {

    static int accNum = 133800;
    static int counter = 0;
    private String name, password;
    private int accountNum;
    private double balance;
    private ArrayList<Transaction> transactions = new ArrayList<>();

    public Account(String nme, String pass, double bal) {
        this.name = nme;
        this.password = pass;
        this.balance = bal;
        this.accountNum = accNum + counter;
        counter = counter++;
    }

    /**
     * Creates a Transaction which deposits an amount
     * @param t
     */
    public void depositTransaction(Transaction t) {
        this.balance = this.balance + t.getAmount();
        transactions.add(t);
    }

    /**
     * Creates a Transaction which withdraws an amount
     * @param t
     */
    public void withdrawTransaction(Transaction t) {
        this.balance = this.balance - t.getAmount();
        if (this.balance < 0)
            System.out.println("You are overdrawn.");
        transactions.add(t);
    }

    /**
     * Gets statement of transactions
     * @return - Statement for Account
     */
    public Statement getStatement(){
        Statement s = new Statement(this);

        return s;
    }

    /**
     * Gets statement of transactions back to specified Date
     * @return - Statement for Account
     */
    public Statement getStatement(Date d){
        Statement s = new Statement(this, d);

        return s;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String pass) {
        this.password = pass;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getAccountNum() {
        return accountNum;
    }

    public void setAccountNum(int accountNum) {
        this.accountNum = accountNum;
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }
}
