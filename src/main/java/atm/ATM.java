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

    public static void main(String[] args) throws RemoteException, NotBoundException {
        String name = "BankServer";

        registry = LocateRegistry.getRegistry(8000);

        bank = (OperationsInterface) registry.lookup(name);
        if (bank != null)
            System.out.println("bank created");

        while (true) {
            System.out.println("Please enter an option: (deposit / withdraw / balance / statement / exit)");
            Scanner input = new Scanner(System.in);
            String operation = input.next();

            switch (operation.trim().toLowerCase()) {

                case "deposit":
                    System.out.println("Enter an amount to deposit");
                    bank.deposit(0);
                    break;

                case "withdraw":
                    System.out.println("Enter an amount to withdraw");
                    bank.withdraw(0);
                    break;

                case "balance":
                    System.out.println("Your balance is €");
                    bank.getBalance();
                    break;

                case "statement":
                    System.out.println("Displaying statement:");
                    bank.getStatement();
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


