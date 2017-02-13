package bank;

import interfaces.OperationsInterface;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by AMCBR on 06/02/2017.
 */
public class BankServer extends UnicastRemoteObject implements OperationsInterface {

    private ArrayList<Account> users = new ArrayList<Account>();


    public BankServer() throws RemoteException {
        super();

        Account a1 = new Account("user1","password1", 100);
        Account a2 = new Account("user2","password2", 250);
        Account a3 = new Account("user3","password3", 500);

        users.add(a1);
        users.add(a2);
        users.add(a3);
    }

    public boolean login(String usr, String pass) {

        for(Account a : users)
            if(a.getName().equals(usr) && a.getPassword().equals(pass))
                return true;

        return false;

    }

    public void deposit(int amt) {

    }

    public void withdraw(int amt) {

    }

    public void getBalance() {

    }

    public Statement getStatement() {


        return null;
    }

    public static void main(String[] args) { //CLI Args: Name, Password, Option

        String user, pass;

        try {

            String name = "BankServer";
            OperationsInterface bank = new BankServer();
            Registry registry = LocateRegistry.createRegistry(8000);
            registry.rebind(name, bank);
            System.out.println("BankServer bound");

            bank.login(args[0], args[1]);
            boolean running = true;
            while(running){
                Scanner input = new Scanner(System.in);
            }
        } catch (Exception e) {
            System.err.println("BankServer exception:");
            e.printStackTrace();
        }
    }

    private static String getArgs(String[] args){

        for(int i=0; i<args.length; i++)
            System.out.println(args[i] + "\n" + args.length);


        if (args.length > 2){

        }

    }

}
