package bank;

import interfaces.OperationsInterface;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

/**
 * Created by AMCBR on 06/02/2017.
 */
public class BankServer extends UnicastRemoteObject implements OperationsInterface {

    private static final long FIVE_MINUTES = 5*60*1000; //5 minutes in milliseconds

    static String rmiName = "BankServer";
    static private int rmiPort;
    static Registry registry;
    private ArrayList<Account> users = new ArrayList<>();
    private HashMap<UUID, SessionId> activeIds = new HashMap<>();
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

    /**
     * checks if a users details exist, if yes then log them in
     * @param usr - username
     * @param pass - password
     * @return true if logged in, else false
     */
    public boolean login(String usr, String pass) {

        for (Account a : users)
            if (a.getName().equals(usr) && a.getPassword().equals(pass)) {
                activeAcc = a;
                return true;
            }

        return false;
    }

    /***
     * deposits an amount in the users balance
     * @param amt
     * @return new user balance
     */
    public double deposit(double amt) {
        System.out.println("Deposit");

        Transaction t = new Transaction(amt, activeAcc.getAccountNum(), "Deposit");
        activeAcc.depositTransaction(t);
        return activeAcc.getBalance();
    }

    /**
     * withdraws an amount from the user balance
     * @param amt
     * @return new user balance
     */
    public double withdraw(double amt) {
        System.out.println("Withdraw");

        Transaction t = new Transaction(amt, activeAcc.getAccountNum(), "Withdraw");
        activeAcc.withdrawTransaction(t);
        return activeAcc.getBalance();
    }

    /**
     * Get current active user balance
     * @return
     */
    public double getBalance() {
        System.out.println("Balance: " + activeAcc.getBalance());
        return activeAcc.getBalance();
    }

    /**
     * Get statement of user transactions
     * @return
     */
    public Statement getStatement() {

        return activeAcc.getStatement();
    }

    /**
     * Get copy of user interaction back to desired date
     * @param d
     * @return
     */
    public Statement getStatement(Date d) {

        return activeAcc.getStatement(d);
    }


    /***
     * Check if the current UUID is still valid
     * @param id
     * @return true if session ID still valid, else false
     * @throws RemoteException
     */
    public boolean checkSessionId(UUID id) throws RemoteException {

        System.out.println("Active IDs" + activeIds.toString());
        if(activeIds.containsKey(id)) {

            System.out.println("");
            SessionId s = activeIds.get(id);
            long fiveAgo = System.currentTimeMillis() - FIVE_MINUTES;

            System.out.println("Time Elapsed: " + (s.getTimeStamp() - fiveAgo));
            if (s.getTimeStamp() < fiveAgo) {
                activeIds.remove(s.getId()); //remove expired IDs
                return false; //No longer valid
            }

            return true;
        }else{
            activeIds.put(id, new SessionId(id)); //Add newly logged in users to the map
            return true;
        }
    }

    /***
     * Unbind our rmi Host from the registry, stop exporting and shut down the process
     * @throws RemoteException
     * @throws NotBoundException
     */
    public void exit() throws RemoteException, NotBoundException {

        // Unregister ourself
        registry.unbind(rmiName);

        // Unexport; this will also remove us from the RMI runtime
        UnicastRemoteObject.unexportObject(this, true);
        System.exit(0);
    }


    /**
     * Starts up rmi Server on specified port. If none available it defaults to 1099
     * @param args
     */
    public static void main(String[] args) { //CLI Args: Name, Password, Option

        //parse port
        try{
            rmiPort = Integer.parseInt(args[0]);
            System.out.println("RMI Port set to "+rmiPort);
        }catch(ArrayIndexOutOfBoundsException e){
            rmiPort = 1099; //Default to port 1099
            System.out.println("RMI Port defaulting to "+rmiPort);
        }

        try {

            //Set up RMI server

            OperationsInterface bank = new BankServer();
            registry = LocateRegistry.createRegistry(rmiPort);
            registry.rebind(rmiName, bank);
            System.out.println("BankServer bound");

        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

}
