import java.rmi.Remote;

/**
 * Created by AMCBR on 06/02/2017.
 */
public interface OperationsInterface extends Remote {

    public void login();
    public void deposit();
    public void withdraw();
    public void getBalance();
    public Statement getStatement();

}
