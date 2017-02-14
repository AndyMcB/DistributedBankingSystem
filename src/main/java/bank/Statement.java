package bank;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by AMCBR on 06/02/2017.
 */
public class Statement implements Serializable{

    private int accNum;
    private String accName;
    private ArrayList transactions;
    private Date startDate;
    private Date endDate;

    public Statement(Account a){
        this.accName = a.getName();
        this.accNum = a.getAccountNum();
        this.transactions = a.getTransactions();
        this.startDate = getStartDate();
        this.endDate = getEndDate();
    }

    public Date getStartDate(){ //Gets start data of transaction for the period of 3 months before the current date

        Date pastDate = new Date();

        Calendar c = Calendar.getInstance();
        c.setTime(pastDate);
        c.add(Calendar.MONTH, -3);

        return c.getTime();
    } // returns start Date of bank.Statement, 3 months ago

    //TODO - add ability for custom date
    public int getAccountNum(){return this.accNum;}  // returns account number associated with this statement

    public Date getEndDate(){return new Date();} // returns end Date of bank.Statement i.e. the current date

    public String getAccoutName(){return this.accName;} // returns name of account holder

    public List getTransations(){return this.transactions;}

    public String toString(){ //TODO - Pretty print
        String info = "Account Number: " + accNum
                + "\nAccount Name: " + accName
                + "\nThis statement is for the period " + startDate + " until " + endDate
                + "\nTransactions for this period:\n" + transactions.toString() ;

        return info;
    }

}
