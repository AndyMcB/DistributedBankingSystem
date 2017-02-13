package interfaces;

import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

import bank.Account;
import bank.Statement;

/**
 * Created by AMCBR on 06/02/2017.
 */
public interface OperationsInterface extends Remote {

    public Account login(String usr, String pass) throws RemoteException;
    public void deposit(int amt) throws RemoteException;
    public void withdraw(int amt) throws RemoteException;
    public void getBalance() throws RemoteException;
    public void exit() throws RemoteException, NotBoundException;
    public Statement getStatement() throws RemoteException;

}
