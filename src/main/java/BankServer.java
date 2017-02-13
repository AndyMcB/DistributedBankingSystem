import interfaces.OperationsInterface;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by AMCBR on 06/02/2017.
 */
public class BankServer implements OperationsInterface {

    public void login() {

    }

    public void deposit() {

    }

    public void withdraw() {

    }

    public void getBalance() {

    }

    public Statement getStatement() {
        return null;
    }

    public static void main(String[] args) {
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
        try {
            String name = "BankServer";
            BankServer engine = new BankServer();
            OperationsInterface stub =
                    (OperationsInterface) UnicastRemoteObject.exportObject(engine, 0);
            Registry registry = LocateRegistry.getRegistry();
            registry.rebind(name, stub);
            System.out.println("ComputeEngine bound");
        } catch (Exception e) {
            System.err.println("ComputeEngine exception:");
            e.printStackTrace();
        }
    }
}
