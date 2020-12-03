package days.day2;

public class PasswordPolicyA {

    private int minPresent = 0;
    private int maxPresent = 0;
    private String password = "";
    private char charachterToBeChecked;
    private boolean isValid = false;

    public PasswordPolicyA(int min, int max, String password, char character) {
        this.minPresent = min;
        this.maxPresent = max;
        this.password = password;
        this.charachterToBeChecked = character;
        calculateValidation();
    }

    private void calculateValidation() {
        long count = password.chars().filter(ch -> ch == charachterToBeChecked).count();
        isValid = count >= minPresent && count <= maxPresent;

    }

    public boolean isValid() {
        return isValid;
    }
}
