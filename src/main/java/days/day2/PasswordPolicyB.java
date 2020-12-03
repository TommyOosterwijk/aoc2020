package days.day2;

public class PasswordPolicyB {

    private int pos1 = 0;
    private int pos2 = 0;
    private String password = "";
    private char charachterToBeChecked;
    private boolean isValid = false;

    public PasswordPolicyB(int pos1, int pos2, String password, char character) {
        this.pos1 = pos1 - 1; //index 0 concept
        this.pos2 = pos2 - 1; //index 0 concept
        this.password = password;
        this.charachterToBeChecked = character;
        calculateValidation();
    }

    private void calculateValidation() {
        isValid = password.charAt(pos1) == charachterToBeChecked ^ password.charAt(pos2) == charachterToBeChecked;
    }

    public boolean isValid() {
        return isValid;
    }
}
