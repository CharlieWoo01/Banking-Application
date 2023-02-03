public class ValidationService {

    // Global variable to import the index service.
    public static ATMGameV2 index = new ATMGameV2();
    // Method to check the input has been confirmed by the user.

    public static boolean checkConfirmed()
    {
        boolean status = false;
        boolean formStatus = true;
        String userAnswer = index.storeUserAnswer();
        while (formStatus) {
            if (userAnswer.toLowerCase().equals("y")) {
                status = true;
                formStatus = false;
            } else if (userAnswer.toLowerCase().equals("n")) {
                status = false;
                formStatus = false;
            } else {
                System.out.println("Please repeat that, we didn't understand your response.");
            }
        }
        return status;
    }

    // Method to check if the transfer is a valid number.
    public static boolean checkValidNumber(int userInput) {
        boolean status = false;
        if (userInput >= 0) {
            status = true;
        }
        else {
            status = false;
        }
        return status;
    }

    public static void main(String args[]) {

    }
}
