package bank;

import interfaces.OperationsInterface;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by AMCBR on 06/02/2017.
 */
public class BankServer extends UnicastRemoteObject implements OperationsInterface {

    protected BankServer() throws RemoteException {
        super();

    }

    public void login() {
        System.out.println("test works");
    }

    public void deposit() {
        System.out.println("test works");
    }

    public void withdraw() {
        System.out.println("test works");
    }

    public void getBalance() {
        System.out.println("test works");
    }

    public Statement getStatement() {

        System.out.println("test works");
        return null;
    }

    public static void main(String[] args) {
       // if (System.getSecurityManager() == null) {
       //     System.setSecurityManager(new SecurityManager());
       // }
        try {
            String name = "BankServer";
            //BankServer engine = new BankServer();
            //OperationsInterface bank = (OperationsInterface) UnicastRemoteObject.exportObject(engine, 0);
            OperationsInterface bank = new BankServer();
            Registry registry = LocateRegistry.createRegistry(8000);
            registry.rebind(name, bank);
            System.out.println("ComputeEngine bound");
        } catch (Exception e) {
            System.err.println("ComputeEngine exception:");
            e.printStackTrace();
        }
    }
}
