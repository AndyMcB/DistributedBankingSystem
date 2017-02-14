package bank;

import interfaces.OperationsInterface;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/**
 * Created by AMCBR on 06/02/2017.
 */
public class BankServer extends UnicastRemoteObject implements OperationsInterface {

    static String rmiName = "BankServer";
    static private int rmiPort = 8000; //TODO-  add as CLI param
    static Registry registry;
    private ArrayList<Account> users = new ArrayList<>();
    static Account activeAcc;


    public BankServer() throws RemoteException {
        super();

        Account a1 = new Account("user1", "pass1", 100);
        Account a2 = new Account("user2", "pass2", 250);
        Account a3 = new Account("user3", "pass3", 500);

        users.add(a1);
        users.add(a2);
        users.add(a3);
    }

    public boolean login(String usr, String pass) {

        for (Account a : users)
            if (a.getName().equals(usr) && a.getPassword().equals(pass)) {
                activeAcc = a;
                return true;
            }

        return false;
    }

    public double deposit(double amt) {
        System.out.println("Deposit");

        Transaction t = new Transaction(amt, activeAcc.getAccountNum(), "Deposit");
        activeAcc.depositTransaction(t);
        return activeAcc.getBalance();
    }

    public double withdraw(double amt) {
        System.out.println("Withdraw");

        Transaction t = new Transaction(amt, activeAcc.getAccountNum(), "Withdraw");
        activeAcc.withdrawTransaction(t);
        return activeAcc.getBalance();
    }

    public double getBalance() {
        System.out.println("Balance: " + activeAcc.getBalance());
        return activeAcc.getBalance();
    }

    public Statement getStatement() {

        return activeAcc.getStatement();
    }

    public void exit() throws RemoteException, NotBoundException {

        // Unregister ourself
        registry.unbind(rmiName);

        // Unexport; this will also remove us from the RMI runtime
        UnicastRemoteObject.unexportObject(this, true);
        System.exit(0);
    }


    public static void main(String[] args) { //CLI Args: Name, Password, Option

        String user, pass;

        try {

            //Set up RMI server

            OperationsInterface bank = new BankServer();
            registry = LocateRegistry.createRegistry(rmiPort);
            registry.rebind(rmiName, bank);
            System.out.println("BankServer bound");

//            //TODO - Factor out, login sent from client
//            if ((bank.login(args[0], args[1])) == null) {
//                System.out.println("Invalid Credentials!");
//                System.exit(0);
//
//                //TODO - add option to resubmit username and password
//            } else {
//                activeAcc = bank.login(args[0], args[1]);
//            }

        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

}
