package bank;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by AMCBR on 08/02/2017.
 */
public class Transaction implements Serializable {

    double amount = 0;
    int accountNum = 0;
    String transactionType = "";
    Date transDate = new Date();

    public Transaction(double amt, int accNum, String type){

        this.amount = amt;
        this.accountNum = accNum;
        this.transactionType = type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getAccountNum() {
        return accountNum;
    }

    public void setAccountNum(int accountNum) {
        this.accountNum = accountNum;
    }

    public Date getTransDate() {
        return transDate;
    }

    public void setTransDate(Date transDate) {
        this.transDate = transDate;
    }

    public void setType(String type) {
        this.transactionType = type;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public String toString(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        String info = "\nTransaction Type: " + this.transactionType
                + "\nAmount: "+ this.amount
                + "\nDate: "+ sdf.format(this.transDate) + "\n";

        return info;
    }

}
