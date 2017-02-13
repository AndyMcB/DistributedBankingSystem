package bank;

import java.io.Serializable;

/**
 * Created by AMCBR on 13/02/2017.
 */
public class Account implements Serializable{

    static int accNum = 133800;
    static int counter = 0;
    String name, password;
    int balance, accountNum;

    public Account(String nme, String pass, int bal){
        this.name = nme;
        this.password = pass;
        this.balance = bal;
        this.accountNum = accNum+counter;
        counter = counter++;
    }

    public static int getAccNum() {
        return accNum;
    }

    public static void setAccNum(int accNum) {
        Account.accNum = accNum;
    }

    public static int getCounter() {
        return counter;
    }

    public static void setCounter(int counter) {
        Account.counter = counter;
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

    public int getBalance() {
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
}
