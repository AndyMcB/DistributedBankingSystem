import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by AMCBR on 06/02/2017.
 */
public class Statement {

    public int getAccountnum(){return 0;}  // returns account number associated with this statement

    public Date getStartDate(){return new Date();} // returns start Date of Statement

    public Date getEndDate(){return new Date();} // returns end Date of Statement

    public String getAccoutName(){return "";} // returns name of account holder

    public List getTransations(){return new ArrayList();}
}
