import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

public class ATMGameV2 {

    // Refactored: Global variable of scanner
    public static Scanner scanner = new Scanner(System.in);

    // Refactored: Global variable to add the validation service.
    public static ValidationService validationService = new ValidationService();

    // Refactored: Global variable to add the error service.
    public static ErrorService errorService = new ErrorService();

    // Refactored: Global variable to add the mapping service.
    public static Mapping mappingService = new Mapping();

    // Refactored: Method to fetch all the bank account data from the hash map structure instead of array.
    public static void fetchBankAccounts() {
        Iterator<Map.Entry<String, Integer>> iterator = mappingService.mappingData.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Integer> entry = iterator.next();
            System.out.println("Name: " + entry.getKey() + ", Balance: " + entry.getValue());
        }
    }

    // Refactored: Method to return the balance of the bank account name with the key that they user has searched.
    static int fetchAccountBalance(String userAccountInput) {
        System.out.println("Refactor: " + Integer.parseInt(mappingService.mappingData.get(userAccountInput).toString()));
        return Integer.parseInt(mappingService.mappingData.get(userAccountInput).toString());
    }

    // Refactored: Method to transfer the money to the recipient.
    static int refactoredAddRecipientMoney(int userInput, String userAccountInput) {
        int accountBalance = fetchAccountBalance(userAccountInput);
        int newBalance = accountBalance + userInput;
        mappingService.mappingData.replace(userAccountInput, newBalance);
        System.out.println("\n Refactor: " + newBalance);
        return accountBalance + userInput;
    }

    // Refactored: Method to check if there is enough money to transfer to the other user.
    static boolean checkTransferPossible(int balance, int userInput){
        int result = balance - userInput;
        boolean balanceStatus = (result >= 0) ? true : false;
        return balanceStatus;
    }

    // Refactored: Method to prompt the user to enter yes or no to questions
    static String userAnswerCollection() {
        String userAnswer = scanner.next();
        return userAnswer;
    }

    // Refactored: Method to remove the money from the sender.
    static int removeSenderMoney(int userInput, int balance) {
        return balance - userInput;
    }

    // Refactored: Method to fetch all the bank account detail's keys and values.
    static void availableAccounts() {
        System.out.println("Available Accounts Are: \n");
        Iterator<Map.Entry<String, Integer>> iterator = mappingService.mappingData.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Integer> entry = iterator.next();
            System.out.println("Name: " + entry.getKey() + ", Balance: " + entry.getValue());
        }
    }

    // Refactored: Method to collect and validate the bank account name exists
    static String collectNameInput()
    {
        boolean status = true;
        System.out.println("Please enter an account name from the list above: ");
        String userInput = scanner.next();
        while (status) {
            System.out.println(errorService.bankAccountExists(userInput));
            if (mappingService.checkBankAccountExists(userInput)) {
                status = false;
            }
            else {
                userInput = scanner.next();
                status = true;
            }
        }
        return userInput;
    }

    // Refactored: Method to store the selection of the user account.
    static String storeUserBank() {
        return collectNameInput();
    }

    // Refactored: Method to store the selection of the user decision.
    static String storeUserAnswer() {
        return userAnswerCollection();
    }

    public static void main(String args[]) {

        // Define variables.
        int balance = 1000;
        boolean systemStatus = true;

        // Run program until asked by the user to do so.
        while (systemStatus) {

            // Prompt user to enter the account to transfer to and store it.
            availableAccounts();

            String currentAccount = storeUserBank();

            // Only continue if the account exists in the hash map.
            if (mappingService.checkBankAccountExists(currentAccount)) {

                // Collect and check if the user input is a valid amount to transfer.
                boolean amountStatus = true;
                System.out.println("Please enter the amount you wish to transfer. ");
                int userInput = scanner.nextInt();
                while (amountStatus) {
                    if (validationService.checkValidNumber(userInput)) {
                        amountStatus = false;
                    } else {
                        System.out.println("Invalid number to transfer. Please try again. ");
                        userInput = scanner.nextInt();
                    }
                }

                // Output if the user can transfer the money.
                System.out.println(errorService.moneyTransferStatus(balance, userInput));

                // Check if the transfer is possible without being overdraft else show unsuccessful error.
                if (checkTransferPossible(balance, userInput)) {

                    // Make the user confirm their decision.
                    System.out.println("Do you wish to proceed? y/n");

                    // Check if the transfer decision is yes and proceed else show unsuccessful.
                    if (validationService.checkConfirmed()) {
                        System.out.println("Transfer was success. ");

                        // Transfer the user's input for the money to the recipient.
                        balance = removeSenderMoney(
                                userInput,
                                balance
                        );
                        int recipientBalance = refactoredAddRecipientMoney(
                                userInput,
                                currentAccount
                        );
                        System.out.println("Your new balance is: " + balance + " and " +
                                "Recipient is now: " + recipientBalance);
                    } else {
                        System.out.println("Transfer was unsuccessful");
                    }
                } else {
                    System.out.println("Transfer was unsuccessful as you do not have sufficient funds. ");
                }

                // Ask if the user would like to add an account to the hash map
                System.out.println("Do you wish to add another account? y/n");

                if (validationService.checkConfirmed()) {
                    mappingService.addBankAccount();
                }

                // Ask if the user wishes to do another transaction
                System.out.println("Do you wish to do another transaction? y/n");

                // Check the user decision to do another transaction else terminate the program.
                if (validationService.checkConfirmed()) {
                    systemStatus = true;
                } else {
                    System.out.println("Thank you for using this service.");
                    System.exit(0);
                }
            }
            else {
                System.out.println("Unexpected error! Account does not exist. ");
            }
        }
        scanner.close();
    }
}