public class ErrorService {

    // Global variable to import the ATM index methods.
    public static ATMGameV2 index = new ATMGameV2();

    // Global variable to import the mapping methods
    public static Mapping mappingService = new Mapping();

    // Method to show the transfer status message.
    public static String moneyTransferStatus(int balance, int userInput)
    {
        boolean bool = index.checkTransferPossible(balance, userInput);
        String transferStatus = (bool) ? "You can transfer this." : "Cannot transfer";
        return transferStatus;
    }

    // Method to state if the bank account exists
    public static String bankAccountExists(String userInput)
    {
        String error = (mappingService.checkBankAccountExists(userInput)) ? "Bank account exists" : "Bank account does" +
                " not exist";
        return error;
    }
}
