import java.util.*;

public class Mapping {

    // Add global variable of creating a mapping object.
    public static Mapping map = new Mapping();

    // Add global variable of to create the bank treemap.
    public static TreeMap<String, Integer> bank = new TreeMap<>();

    // Add a global variable of scanner import.
    public static Scanner scanner = new Scanner(System.in);

    // Method to automatically add some default accounts for bank to the tree map.
    public static Map createBankAccountTable() {
        // Add records to hash table
        bank.put("Charlie", 2000);
        bank.put("Charlie2", 3000);
        bank.put("Charlie3", 4000);
        bank.put("Charlie4", 5000);
        return bank;
    }

    // Global variable to build the tree map of the bank accounts.
    public static Map mappingData = map.createBankAccountTable();

    // Method to fetch all the bank account detail's keys and values.
    public static void fetchBankAccounts()
    {
        Iterator<Map.Entry<String, Integer>> iterator = mappingData.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Integer> entry = iterator.next();
            System.out.println("Name: " + entry.getKey() + ", Balance: " + entry.getValue());
        }
    }

    // Method to check if the bank account exists in the tree map.
    public static boolean checkBankAccountExists(String userInput)
    {
        return bank.containsKey(userInput);
    }

    // Method to add an account to the tree map
    public static void addBankAccount() {
        System.out.println("\n What account name would you like to add?: ");
        String user = scanner.next();
        System.out.println("\n What value would you like to add a value to new account?: ");
        int balance = scanner.nextInt();
        bank.put(user, balance);
        System.out.println("\n Successfully added a new account!");
    }

    // Method to fetch the bank account balance from the selected user.
    public static int fetchBankAccount(String userInput)
    {
       return bank.get(userInput);
    }

    public static void main(String args[]) {

        // Call the method to add an account.
        addBankAccount();

        bank.replace("Charlie2", 300);

        // Call the method to fetch all the available bank accounts.
        fetchBankAccounts();

        // Close scanner before end of the program to save memory.
        scanner.close();
    }
}
