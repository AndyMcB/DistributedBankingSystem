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
    static private int rmiPort = 8000;
    private ArrayList<Account> users = new ArrayList<Account>();
    static Account activeAcc;

    public BankServer() throws RemoteException {
        super();

        Account a1 = new Account("user1","password1", 100);
        Account a2 = new Account("user2","password2", 250);
        Account a3 = new Account("user3","password3", 500);

        users.add(a1);
        users.add(a2);
        users.add(a3);
    }

    public Account login(String usr, String pass) {

        for(Account a : users)
            if(a.getName().equals(usr) && a.getPassword().equals(pass))
                return a;

        return null;

    }

    public void deposit(int amt) {
        System.out.println("Old Balance: "+ activeAcc.getBalance());
        Transaction t = new Transaction(amt, activeAcc.getAccountNum());
        activeAcc.depositTransaction(t);
        System.out.println("New Balance: "+ activeAcc.getBalance());
    }

    public void withdraw(int amt) {
        System.out.println("withdraw");
        Transaction t = new Transaction(amt, activeAcc.getAccountNum());
        activeAcc.withdrawTransaction(t);
        System.out.println("Old Balance: "+ activeAcc.getBalance());
    }

    public void getBalance() {
        System.out.println("balance");
        System.out.println("Balance: "+ activeAcc.getBalance());
    }

    public Statement getStatement() {
        System.out.println("statement");
        //System.out.println(activeAcc.get());

        return null;
    }

    public void exit() throws RemoteException, NotBoundException {
        System.exit(0);
    }


    public static void main(String[] args) { //CLI Args: Name, Password, Option

        String user, pass;

        try {

            //Set up RMI server

            OperationsInterface bank = new BankServer();
            Registry registry = LocateRegistry.createRegistry(rmiPort);
            registry.rebind(rmiName, bank);
            System.out.println("BankServer bound");


            checkArgs(args); //Makes sure there are enough args, if not will throw an error

            if ((bank.login(args[0], args[1])) == null ) {
                System.out.println("Invalid Credentials!");
                System.exit(0);

                //TODO - add option to resubmit username and password
            }else{
                activeAcc = bank.login(args[0], args[1]);
            }

        } catch (Exception  e1) {
            e1.printStackTrace();
        }
    }

    private static void checkArgs(String[] args) throws Exception {

        for(int i=0; i<args.length; i++)
            System.out.println(args[i] + "\n" + args.length);


        if (args.length < 2){
            throw new Exception(); //TODO - create custom error
        }

    }

}
