package atm;

import bank.Account;
import bank.Statement;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

import interfaces.OperationsInterface;

/**
 * Created by AMCBR on 06/02/2017.
 */
public class ATM {

    static OperationsInterface bank;
    static Registry registry;
    static Scanner in = new Scanner(System.in);


    public static void main(String[] args) throws RemoteException, NotBoundException { //TODO - implement session tracking
        String name = "BankServer";

        registry = LocateRegistry.getRegistry(8000);

        bank = (OperationsInterface) registry.lookup(name);
        if (bank != null)
            System.out.println("bank found");

        System.out.println("Please enter your username and password: (In form username-password)");
        String input = in.next();
        String[] credentials = input.split("-");

        boolean loggedIn = false;
        try {
            if (bank.login(credentials[0], credentials[1]))
                loggedIn = true;

            System.out.println("Successfully Logged In");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Error: Details entered incorrectly");
        }


        while (loggedIn) {

            System.out.println("\nPlease enter an option: (deposit / withdraw / balance / statement / exit)");
            String operation = in.next();

            switch (operation.trim().toLowerCase()) {

                case "deposit":
                    System.out.println("Enter an amount to deposit");
                    input = in.next();
                    try {
                        System.out.println("Successfully deposited €" + input
                                + "\nNew Balance: €" + bank.deposit(Double.parseDouble(input))); //Test
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid Input. Try again");
                        break;
                    }
                    break;

                case "withdraw":
                    System.out.println("Enter an amount to withdraw");
                    input = in.next();
                    try {
                        System.out.println("Successfully withdrew €" + input
                                + "\nNew Balance: €" + bank.withdraw(Double.parseDouble(input)));
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid Input. Try again");
                        break;
                    }
                    break;

                case "balance":
                    System.out.println("Your balance is €" + bank.getBalance());
                    break;

                case "statement":
                    System.out.println("Displaying statement:");
                    System.out.println(bank.getStatement().toString());
                    break;

                case "exit":
                    System.out.println("Have a nice day");

                    try {
                        bank.exit();
                    } catch (java.rmi.UnmarshalException e) {
                    }

                    System.exit(0);

                default:
                    System.out.println("Input not recognised, please try again");
                    break;
            }

        }
    }
}



