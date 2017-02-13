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

            //Set up RMI server
            String name = "BankServer";
            OperationsInterface bank = new BankServer();
            Registry registry = LocateRegistry.createRegistry(8000);
            registry.rebind(name, bank);
            System.out.println("BankServer bound");


            checkArgs(args);
            bank.login(args[0], args[1]);
            boolean running = true;
            while(running){
                System.out.println("Please enter an option: (deposit / withdraw / balance / statement / exit)");
                Scanner input = new Scanner(System.in);
                String operation = input.next();

                switch(operation.trim().toLowerCase()){

                    case "deposit":
                        System.out.println("Enter an amount to deposit");
                        break;

                    case "withdraw":
                        System.out.println("Enter an amount to withdraw");
                        break;

                    case "balance":
                        System.out.println("Your balance is €");
                        break;

                    case "statement":
                        System.out.println("Displaying statement:");
                        break;

                    case "exit":
                        System.out.println("Have a nice day");
                        System.exit(0);

                    default:
                        System.out.println("Input not recognised, please try again");
                        break;


                }
            }

        } catch (Exception e) {
            System.err.println("BankServer exception:");
            e.printStackTrace();
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
