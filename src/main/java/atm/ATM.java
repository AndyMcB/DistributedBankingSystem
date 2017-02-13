package atm;
import bank.Statement;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import interfaces.OperationsInterface;

/**
 * Created by AMCBR on 06/02/2017.
 */
public class ATM implements OperationsInterface {

    static OperationsInterface bank;
    static Registry registry;

    public void login() {

    }

    public void deposit() {

    }

    public void withdraw() {

    }

    public void getBalance() {

    }

    public Statement getStatement() throws RemoteException {
        return new Statement();
    }

    public static void main(String[] args) throws RemoteException, NotBoundException {
        String name = "BankServer";

        registry = LocateRegistry.getRegistry(8000);
        if (registry != null)
            System.out.println("registry found");

        for(String s : registry.list())
            System.out.println(s);


        bank = (OperationsInterface) registry.lookup("Bank");
        if(bank != null)
            System.out.println("bank created");

        System.out.println("");
    }

}
