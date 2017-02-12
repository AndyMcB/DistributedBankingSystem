import java.io.Serializable;
import java.util.Date;

/**
 * Created by AMCBR on 08/02/2017.
 */
public class Transaction implements Serializable {

    int amount = 0;
    int accountNum = 0;
    Date transDate = new Date();

    public Transaction(int amt, int accNum){

        this.amount = amt;
        this.accountNum = accNum;
    }

    public int getAmount() {
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

}
