package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by AMCBR on 06/02/2017.
 */
public interface OperationsInterface extends Remote {

    public void login() throws RemoteException;
    public void deposit() throws RemoteException;
    public void withdraw() throws RemoteException;
    public void getBalance() throws RemoteException;
    public Statement getStatement() throws RemoteException;

}
