package bank;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by AMCBR on 06/02/2017.
 */
public class Statement {

    private int accNum;
    private Date startDate;
    private Date endDate;
    private String accName;
    private ArrayList transactions;

    public Statement(){

    }

    public int getAccountnum(){return 0;}  // returns account number associated with this statement

    public Date getStartDate(){ //Gets start data of transaction for the period of 3 months before the current date

        Date pastDate = new Date();

        Calendar c = Calendar.getInstance();
        c.setTime(pastDate);
        c.add(Calendar.MONTH, -3);

        return c.getTime();} // returns start Date of bank.Statement

    public Date getEndDate(){return new Date();} // returns end Date of bank.Statement

    public String getAccoutName(){return "";} // returns name of account holder

    public List getTransations(){return new ArrayList();} //TODO - implement a transaction class

    public String toString(){
        String info = "Account Number: " + accNum
                + "\nAccount Name: " + accName
                + "\nThis statement is for the period " + startDate + " until " + endDate
                + "\nTransactions for this period:\n" + transactions ;

        return info;
    }

}
