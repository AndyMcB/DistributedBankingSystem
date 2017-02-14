package interfaces;

import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

import bank.Statement;

/**
 * Created by AMCBR on 06/02/2017.
 */
public interface OperationsInterface extends Remote {

    boolean login(String usr, String pass) throws RemoteException;

    double deposit(double amt) throws RemoteException; //return new balance

    double withdraw(double amt) throws RemoteException; // return new balance

    double getBalance() throws RemoteException;

    void exit() throws RemoteException, NotBoundException;

    Statement getStatement() throws RemoteException;

}
